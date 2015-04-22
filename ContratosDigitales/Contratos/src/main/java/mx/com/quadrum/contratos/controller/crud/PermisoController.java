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

/**
 *
 * @author vcisneros
 */
@Controller
public class PermisoController {

    @Autowired
    PermisoService permisoService;

    @RequestMapping(value = "permiso", method = RequestMethod.GET)
    public String permiso(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Permiso> permisos = (List<Permiso>) session.getAttribute(PERMISOS);
                
        if(usuario == null && permisos == null){
            return "templates/index";
        }
        model.addAttribute("permisos", permisos);
        return "crud/permiso";
    }

    @ResponseBody
    @RequestMapping(value = "agregarPermiso", method = RequestMethod.POST)
    public String agregarPermiso(@Valid @ModelAttribute("permiso") Permiso permiso, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            return ERROR_DATOS;
        }
        return permisoService.agregar(permiso);
    }

    @ResponseBody
    @RequestMapping(value = "editarPermiso", method = RequestMethod.POST)
    public String editarPermiso(@Valid @ModelAttribute("permiso") Permiso permiso, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            return ERROR_DATOS;
        }
        return permisoService.editar(permiso);
    }

    @ResponseBody
    @RequestMapping(value = "eliminarPermiso", method = RequestMethod.POST)
    public String eliminarPermiso(Permiso permiso, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        return permisoService.eliminar(permiso);
    }
}
