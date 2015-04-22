/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.contratos.controller.crud;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import mx.com.quadrum.entity.Grado;
import mx.com.quadrum.service.GradoService;
import static mx.com.quadrum.service.util.MensajesCrud.ERROR_DATOS;
import static mx.com.quadrum.service.util.MensajesCrud.SESION_CADUCA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class GradoController {

    @Autowired
    GradoService gradoService;

//    @RequestMapping(value = "grado", method = RequestMethod.GET)
//    public String grado(Model model, HttpSession session) {
//        Usuario usuario = (Usuario) session.getAttribute("usuario");
//        if(usuario == null){
//            return "templates/index";
//        }
//        model.addAttribute("permisos", usuario.getPermisos());
//        return "crud/grado";
//    }

    @ResponseBody
    @RequestMapping(value = "agregarGrado", method = RequestMethod.POST)
    public String agregarGrado(@Valid @ModelAttribute("grado") Grado grado, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            return ERROR_DATOS;
        }
        return gradoService.agregar(grado);
    }

    @ResponseBody
    @RequestMapping(value = "editarGrado", method = RequestMethod.POST)
    public String editarGrado(@Valid @ModelAttribute("grado") Grado grado, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            return ERROR_DATOS;
        }
        return gradoService.editar(grado);
    }

    @ResponseBody
    @RequestMapping(value = "eliminarGrado", method = RequestMethod.POST)
    public String eliminarGrado(Grado grado, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        return gradoService.eliminar(grado);
    }
}
