/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.contratos.controller.busquedas;

import java.util.List;
import javax.servlet.http.HttpSession;
import mx.com.quadrum.entity.Contrato;
import mx.com.quadrum.service.BusquedasContratos;
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
public class ContratoBusquedaAdminController {
        
    @Autowired
    BusquedasContratos busquedasContratos;
    
    @RequestMapping(value = "buscarTodosContratosAdmin", method = RequestMethod.GET)
    public ModelAndView buscarTodosContratosAdmin(ModelMap model, HttpSession session){
        List<Contrato> contratos = busquedasContratos.buscarTodos();
        model.put("contratos", contratos);
        return new ModelAndView("crud/contratoAux", model);
    }
    
    @RequestMapping(value = "buscarPorTipoDeContratoAdmin/{idTipoContrato}", method = RequestMethod.GET)
    public ModelAndView buscarPorTipoDeContratoAdmin(@PathVariable("idTipoContrato")Integer idTipoContrato, ModelMap model, HttpSession session){
        List<Contrato> contratos = busquedasContratos.buscarPorTipoDeContrato((idTipoContrato));
        model.put("contratos", contratos);
        return new ModelAndView("crud/contratoAux", model);
    }
    
    @RequestMapping(value = "buscarPorEstadoAdmin/{idEstado}", method = RequestMethod.GET)
    public ModelAndView buscarPorEstadoAdmin(@PathVariable("idEstado")Integer idEstado, ModelMap model, HttpSession session){
        List<Contrato> contratos = busquedasContratos.buscarPorEstado((idEstado));
        model.put("contratos", contratos);
        return new ModelAndView("crud/contratoAux", model);
    }
    
    @RequestMapping(value = "buscarPorContactoAdmin/{nombre}/{paterno}/{materno}", method = RequestMethod.GET)
    public ModelAndView buscarPorContactoAdmin(@PathVariable("nombre")String nombre,@PathVariable("paterno")String paterno,@PathVariable("materno")String materno, ModelMap model, HttpSession session){
        List<Contrato> contratos = busquedasContratos.buscarPorContaco(nombre, paterno, materno);
        model.put("contratos", contratos);
        return new ModelAndView("crud/contratoAux", model);
    }
    
    @RequestMapping(value = "buscarPorContactoAdmin/{nombre}/{paterno}", method = RequestMethod.GET)
    public ModelAndView buscarPorContactoAdmin(@PathVariable("nombre")String nombre,@PathVariable("paterno")String paterno, ModelMap model, HttpSession session){
        List<Contrato> contratos = busquedasContratos.buscarPorContacto(nombre, paterno);
        model.put("contratos", contratos);
        return new ModelAndView("crud/contratoAux", model);
    }
    
    @RequestMapping(value = "buscarPorContactoAdmin/{nombre}", method = RequestMethod.GET)
    public ModelAndView buscarPorContactoAdmin(@PathVariable("nombre")String nombre, ModelMap model, HttpSession session){
        List<Contrato> contratos = busquedasContratos.buscarPorContacto(nombre);
        model.put("contratos", contratos);
        return new ModelAndView("crud/contratoAux", model);
    }
    
    @RequestMapping(value = "buscarPorEmpleadoAdmin/{idEmpleado}", method = RequestMethod.GET)
    public ModelAndView buscarPorEmpleadoAdmin(@PathVariable("idEmpleado")Integer idEmpleado, ModelMap model, HttpSession session){
        List<Contrato> contratos = busquedasContratos.buscarPorEmpleado(idEmpleado);
        model.put("contratos", contratos);
        return new ModelAndView("crud/contratoAux", model);
    }
    
     @RequestMapping(value = "buscarPorEmpresaAdmin/{nombre}", method = RequestMethod.GET)
    public ModelAndView buscarPorEmpresaAdmin(@PathVariable("nombre")String nombre, ModelMap model, HttpSession session){
        List<Contrato> contratos = busquedasContratos.buscarPorEmpresa(nombre);
        model.put("contratos", contratos);
        return new ModelAndView("crud/contratoAux", model);
    }
}
