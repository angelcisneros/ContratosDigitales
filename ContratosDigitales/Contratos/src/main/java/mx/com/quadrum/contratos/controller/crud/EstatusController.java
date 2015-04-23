/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.contratos.controller.crud;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import mx.com.quadrum.entity.Estatus;
import mx.com.quadrum.service.EstatusService;
import mx.com.quadrum.service.UsuarioService;
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
public class EstatusController {

    @Autowired
    EstatusService estatusService;

    @ResponseBody
    @RequestMapping(value = "agregarEstatus", method = RequestMethod.POST)
    public String agregarEstatus(@Valid @ModelAttribute("estatus") Estatus estatus, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            return ERROR_DATOS;
        }
        return estatusService.agregar(estatus);
    }

    @ResponseBody
    @RequestMapping(value = "editarEstatus", method = RequestMethod.POST)
    public String editarEstatus(@Valid @ModelAttribute("estatus") Estatus estatus, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            return ERROR_DATOS;
        }
        return estatusService.editar(estatus);
    }

    @ResponseBody
    @RequestMapping(value = "eliminarEstatus", method = RequestMethod.POST)
    public String eliminarEstatus(Estatus estatus, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        return estatusService.eliminar(estatus);
    }
}
