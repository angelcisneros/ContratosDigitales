/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.contratos.controller.crud;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import mx.com.quadrum.entity.Permiso;
import mx.com.quadrum.entity.Usuario;
import mx.com.quadrum.service.ContactoService;
import mx.com.quadrum.service.PermisoService;
import mx.com.quadrum.service.UsuarioService;
import static mx.com.quadrum.service.util.Llave.PERMISOS;
import static mx.com.quadrum.service.util.MensajesCrud.ERROR_DATOS;
import static mx.com.quadrum.service.util.MensajesCrud.SESION_CADUCA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author vcisneros
 */
@Controller
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PermisoService permisoService;

    @RequestMapping(value = "usuario", method = RequestMethod.GET)
    public String usuarios(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Permiso> permisos = (List<Permiso>) session.getAttribute(PERMISOS);

        if (usuario == null && permisos == null) {
            return "templates/index";
        }
        if (usuario.getEsAdmin()) {
            model.addAttribute("esAdmin", "esAdmin");
        }
        if (usuarioService.tienePermiso(usuario, "usuario")) {
            return "templates/noAutorizado";
        }
        model.addAttribute("permisos", permisos);
        model.addAttribute("asignarPermisos", permisoService.buscarTodos());
        model.addAttribute("usuarios", usuarioService.buscarTodos());
        return "crud/usuario";
    }

    @ResponseBody
    @RequestMapping(value = "agregarUsuario", method = RequestMethod.POST)
    public String agregarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            return ERROR_DATOS;
        }
        if (usuarioService.existeCorreo(usuario.getMail())) {
            return "Error...#Ya existe un usuario con el correo que quiere ingresar.";
        }
        return usuarioService.agregar(usuario);
    }

    @ResponseBody
    @RequestMapping(value = "editarUsuario", method = RequestMethod.POST)
    public String editarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            return ERROR_DATOS;
        }
        if (usuarioService.existeCorreo(usuario.getMail())) {
            Usuario u = usuarioService.buscarPorCorreo(usuario.getMail());
            if (u.getId() != usuario.getId()) {
                return "Error...#El correo pertencece a otra persona.";
            }
        }
        return usuarioService.editar(usuario);
    }

    @ResponseBody
    @RequestMapping(value = "eliminarUsuario", method = RequestMethod.POST)
    public String eliminarUsuario(Usuario usuario, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return SESION_CADUCA;
        }
        return usuarioService.eliminar(usuario);
    }

    @RequestMapping(value = "inicioCambioPassword", method = RequestMethod.POST)
    public String cambiarPassword(String password, HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            model.addAttribute("error", "No se pudo cambiar el password");
            return "templates/error";
        }
        if (usuarioService.cambiarPassword(usuario, password)) {
            if (usuario.getEsAdmin()) {
                model.addAttribute("esAdmin", "esAdmin");
            }
            model.addAttribute("permisos", session.getAttribute("permisos"));
            return "templates/inicio";
        }
        model.addAttribute("error", "No se pudo cambiar el password");
        return "templates/error";
    }

}
