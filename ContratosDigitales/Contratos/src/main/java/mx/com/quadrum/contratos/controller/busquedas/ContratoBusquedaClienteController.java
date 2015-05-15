/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.contratos.controller.busquedas;

import java.util.List;
import javax.servlet.http.HttpSession;
import mx.com.quadrum.entity.Contacto;
import mx.com.quadrum.entity.Contrato;
import mx.com.quadrum.service.BusquedasContratos;
import static mx.com.quadrum.service.util.Llave.CLIENTE;
import static mx.com.quadrum.service.util.Llave.USUARIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author vcisneros
 */
@Controller
public class ContratoBusquedaClienteController {
        
    @Autowired
    BusquedasContratos busquedasContratos;
    
    @RequestMapping(value = "buscarTodosContratosCliente", method = RequestMethod.GET)
    public ModelAndView buscarTodosContratosCliente(ModelMap model, HttpSession session){
        if(session.getAttribute(USUARIO) == null && session.getAttribute(CLIENTE) == null){
            return new ModelAndView("templates/index");
        }
        
        Contacto contacto = (Contacto) session.getAttribute(CLIENTE);
        
        List<Contrato> contratos = busquedasContratos.buscarPorContacto(contacto.getId());
        model.put("contratos", contratos);
        return new ModelAndView("cliente/contratoAux", model);
    }
    
    @RequestMapping(value = "buscarPorTipoDeContratoCliente/{idTipoContrato}", method = RequestMethod.GET)
    public ModelAndView buscarPorTipoDeContratoCliente(@PathVariable("idTipoContrato")Integer idTipoContrato, ModelMap model, HttpSession session){
        if(session.getAttribute(USUARIO) == null && session.getAttribute(CLIENTE) == null){
            return new ModelAndView("templates/index");
        }
        
        Contacto contacto = (Contacto) session.getAttribute(CLIENTE);
        
        List<Contrato> contratos = busquedasContratos.buscarPorTipoDeContratoCliente((idTipoContrato), contacto.getId());
        model.put("contratos", contratos);
        return new ModelAndView("cliente/contratoAux", model);
    }
    
    @RequestMapping(value = "buscarPorEstadoCliente/{idEstado}", method = RequestMethod.GET)
    public ModelAndView buscarPorEstadoCliente(@PathVariable("idEstado")Integer idEstado, ModelMap model, HttpSession session){
        if(session.getAttribute(USUARIO) == null && session.getAttribute(CLIENTE) == null){
            return new ModelAndView("templates/index");
        }
        
        Contacto contacto = (Contacto) session.getAttribute(CLIENTE);
        
        List<Contrato> contratos = busquedasContratos.buscarPorEstadoCliente((idEstado), contacto.getId());
        model.put("contratos", contratos);
        return new ModelAndView("cliente/contratoAux", model);
    }
    
}
