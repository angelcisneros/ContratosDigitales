/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.contratos.controller.crud;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import mx.com.quadrum.entity.Contacto;
import mx.com.quadrum.entity.Permiso;
import mx.com.quadrum.entity.Usuario;
import mx.com.quadrum.service.ContactoService;
import mx.com.quadrum.service.EmpresaService;
import mx.com.quadrum.service.GradoService;
import mx.com.quadrum.service.UsuarioService;
import static mx.com.quadrum.service.util.Llave.PERMISOS;
import static mx.com.quadrum.service.util.MensajesCrud.ERROR_DATOS;
import static mx.com.quadrum.service.util.MensajesCrud.SESION_CADUCA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author vcisneros
 */
@Controller
public class ContactoController {

    @Autowired
    ContactoService contactoService;

    @Autowired
    GradoService gradoService;

    @Autowired
    EmpresaService empresaService;

    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "contacto", method = RequestMethod.GET)
    public String contactos(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Permiso> permisos = (List<Permiso>) session.getAttribute(PERMISOS);

        if (usuario == null || permisos == null) {
            return "templates/index";
        }
        if (usuario.getEsAdmin()) {
            model.addAttribute("esAdmin", "esAdmin");
        }
        if (usuarioService.tienePermiso(usuario, "contacto")) {
            return "templates/noAutorizado";
        }
        model.addAttribute("permisos", permisos);
        model.addAttribute("contactos", contactoService.buscarTodos());
        model.addAttribute("grado", gradoService.buscarTodos());
        model.addAttribute("empresa", empresaService.buscarTodos());
        return "crud/contacto";
    }

    @ResponseBody
    @RequestMapping(value = "agregarContacto", method = RequestMethod.POST)
    public String agregarContacto(@Valid @ModelAttribute("contacto") Contacto contacto, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            for(ObjectError e: bindingResult.getAllErrors()){
                System.out.println(e.getCode());
                System.out.println(e.getDefaultMessage());
                System.out.println(e.getObjectName());
                System.out.println(e.toString());
            }
            return ERROR_DATOS;
        }
        if (contactoService.existeCorreo(contacto.getMail())) {
            return "Error...#Ya existe un usuario con el correo que quiere ingresar.";
        }
        return contactoService.agregar(contacto);
    }

    @ResponseBody
    @RequestMapping(value = "editarContacto", method = RequestMethod.POST)
    public String editarContacto(@Valid @ModelAttribute("contacto") Contacto contacto, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            return ERROR_DATOS;
        }
        if (contactoService.existeCorreo(contacto.getMail())) {
            Contacto c = contactoService.buscarPorCorreo(contacto.getMail());
            if (c.getId() != contacto.getId()) {
                return "Error...#El correo pertencece a otra persona.";
            }
        }
        return contactoService.editar(contacto);
    }

    @ResponseBody
    @RequestMapping(value = "eliminarContacto", method = RequestMethod.POST)
    public String eliminarContacto(Contacto contacto, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return SESION_CADUCA;
        }
        return contactoService.eliminar(contacto);
    }
}
