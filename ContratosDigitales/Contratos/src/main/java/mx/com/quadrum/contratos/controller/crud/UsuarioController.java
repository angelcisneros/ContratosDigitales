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
    public String usuario(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Permiso> permisos = (List<Permiso>) session.getAttribute(PERMISOS);

        if (usuario == null && permisos == null) {
            return "templates/index";
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
    public ModelAndView cambiarPassword(String password, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        System.out.println("Password");
        if (usuario == null) {
            return new ModelAndView("templates/error", "error", "No se pudo cambiar el password");
        }
        if (usuarioService.cambiarPassword(usuario, password)) {
            return new ModelAndView("templates/inicio", "permisos", session.getAttribute("permisos"));
        }
        return new ModelAndView("templates/error", "error", "No se pudo cambiar el password");
    }
    
  }
