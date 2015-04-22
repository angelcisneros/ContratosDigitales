/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.contratos.controller.crud;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import mx.com.quadrum.entity.Contrato;
import mx.com.quadrum.entity.Permiso;
import mx.com.quadrum.entity.Usuario;
import mx.com.quadrum.service.ContratoService;
import mx.com.quadrum.service.EstatusService;
import mx.com.quadrum.service.TipoContratoService;
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

/**
 *
 * @author vcisneros
 */
@Controller
public class ContratoController{

    @Autowired
    ContratoService contratoService;
    
    @Autowired
    TipoContratoService tipoContratoService;    
    
    @Autowired
    EstatusService estatusService;
    
    @Autowired
    UsuarioService usuarioService;

    @RequestMapping(value = "contratoUsuario", method = RequestMethod.GET)
    public String contrato(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Permiso> permisos = (List<Permiso>) session.getAttribute(PERMISOS);
                
        if(usuario == null || permisos == null){
            return "templates/index";
        }
        
        model.addAttribute("permisos", permisos);
        model.addAttribute("tipoContrato", tipoContratoService.buscarTodos());
        model.addAttribute("estado", estatusService.buscarTodos());
        model.addAttribute("contratos", contratoService.buscarTodos());
        model.addAttribute("usuarios", usuarioService.buscarTodos());
        return "crud/contrato";
    }

    @ResponseBody
    @RequestMapping(value = "agregarContrato", method = RequestMethod.POST)
    public String agregarContrato(@Valid @ModelAttribute("contrato") Contrato contrato, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            return ERROR_DATOS;
        }
        return contratoService.agregar(contrato);
    }

    @ResponseBody
    @RequestMapping(value = "editarContrato", method = RequestMethod.POST)
    public String editarContrato(@Valid @ModelAttribute("contrato") Contrato contrato, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            return ERROR_DATOS;
        }
        return contratoService.editar(contrato);
    }

    @ResponseBody
    @RequestMapping(value = "eliminarContrato", method = RequestMethod.POST)
    public String eliminarContrato(Contrato contrato, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        return contratoService.eliminar(contrato);
    }
}
