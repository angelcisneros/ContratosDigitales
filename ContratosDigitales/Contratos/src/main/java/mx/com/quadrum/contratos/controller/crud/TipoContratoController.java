/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.contratos.controller.crud;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import mx.com.quadrum.entity.TipoContrato;
import mx.com.quadrum.service.TipoContratoService;
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
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vcisneros
 */
@Controller
public class TipoContratoController {

    @Autowired
    TipoContratoService tipoContratoService;
    
    @ResponseBody
    @RequestMapping(value = "agregarTipoContrato", method = RequestMethod.POST)
    public String agregarTipoContrato(@Valid @ModelAttribute("tipoContrato") TipoContrato tipoContrato, MultipartFile formato, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            return ERROR_DATOS;
        }
        return tipoContratoService.agregar(tipoContrato, formato);
    }

    @ResponseBody
    @RequestMapping(value = "editarTipoContrato", method = RequestMethod.POST)
    public String editarTipoContrato(@Valid @ModelAttribute("tipoContrato") TipoContrato tipoContrato, BindingResult bindingResult, MultipartFile formato, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            return ERROR_DATOS;
        }
        
        return tipoContratoService.editar(tipoContrato, formato);
    }

    @ResponseBody
    @RequestMapping(value = "eliminarTipoContrato", method = RequestMethod.POST)
    public String eliminarTipoContrato(TipoContrato tipoContrato, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        return tipoContratoService.eliminar(tipoContrato);
    }
}
