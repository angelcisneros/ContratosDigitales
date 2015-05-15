/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.contratos.controller.busquedas;

import java.util.List;
import javax.servlet.http.HttpSession;
import mx.com.quadrum.entity.Contrato;
import mx.com.quadrum.entity.Usuario;
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
public class ContratoBusquedaEmpleadoController {
        
    @Autowired
    BusquedasContratos busquedasContratos;
    
    @RequestMapping(value = "buscarTodosContratosEmpleado", method = RequestMethod.GET)
    public ModelAndView buscarTodosContratosEmpleado(ModelMap model, HttpSession session){
        if(session.getAttribute(USUARIO) == null && session.getAttribute(CLIENTE) == null){
            return new ModelAndView("templates/index");
        }
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        List<Contrato> contratos = busquedasContratos.buscarPorEmpleado(usuario.getId());
        model.put("contratos", contratos);
        return new ModelAndView("usuario/contratoAux", model);
    }
    
    @RequestMapping(value = "buscarPorTipoDeContratoEmpleado/{idTipoContrato}", method = RequestMethod.GET)
    public ModelAndView buscarPorTipoDeContratoEmpleado(@PathVariable("idTipoContrato")Integer idTipoContrato, ModelMap model, HttpSession session){
        if(session.getAttribute(USUARIO) == null && session.getAttribute(CLIENTE) == null){
            return new ModelAndView("templates/index");
        }
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        List<Contrato> contratos = busquedasContratos.buscarPorTipoDeContratoEmpleado((idTipoContrato), usuario.getId());
        model.put("contratos", contratos);
        return new ModelAndView("usuario/contratoAux", model);
    }
    
    @RequestMapping(value = "buscarPorEstadoEmpleado/{idEstado}", method = RequestMethod.GET)
    public ModelAndView buscarPorEstadoEmpleado(@PathVariable("idEstado")Integer idEstado, ModelMap model, HttpSession session){
        if(session.getAttribute(USUARIO) == null && session.getAttribute(CLIENTE) == null){
            return new ModelAndView("templates/index");
        }
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        List<Contrato> contratos = busquedasContratos.buscarPorEstadoEmpleado((idEstado), usuario.getId());
        model.put("contratos", contratos);
        return new ModelAndView("usuario/contratoAux", model);
    }
    
    @RequestMapping(value = "buscarPorContactoEmpleado/{nombre}/{paterno}/{materno}", method = RequestMethod.GET)
    public ModelAndView buscarPorContactoEmpleado(@PathVariable("nombre")String nombre,@PathVariable("paterno")String paterno,@PathVariable("materno")String materno, ModelMap model, HttpSession session){
        if(session.getAttribute(USUARIO) == null && session.getAttribute(CLIENTE) == null){
            return new ModelAndView("templates/index");
        }
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        List<Contrato> contratos = busquedasContratos.buscarPorContacoEmpleado(nombre, paterno, materno, usuario.getId());
        model.put("contratos", contratos);
        return new ModelAndView("usuario/contratoAux", model);
    }
    
    @RequestMapping(value = "buscarPorContactoEmpleado/{nombre}/{paterno}", method = RequestMethod.GET)
    public ModelAndView buscarPorContactoEmpleado(@PathVariable("nombre")String nombre,@PathVariable("paterno")String paterno, ModelMap model, HttpSession session){
        if(session.getAttribute(USUARIO) == null && session.getAttribute(CLIENTE) == null){
            return new ModelAndView("templates/index");
        }
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        List<Contrato> contratos = busquedasContratos.buscarPorContactoEmpleado(nombre, paterno, usuario.getId());
        model.put("contratos", contratos);
        return new ModelAndView("usuario/contratoAux", model);
    }
    
    @RequestMapping(value = "buscarPorContactoEmpleado/{nombre}", method = RequestMethod.GET)
    public ModelAndView buscarPorContactoEmpleado(@PathVariable("nombre")String nombre, ModelMap model, HttpSession session){
        if(session.getAttribute(USUARIO) == null && session.getAttribute(CLIENTE) == null){
            return new ModelAndView("templates/index");
        }
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        List<Contrato> contratos = busquedasContratos.buscarPorContactoEmpleado(nombre, usuario.getId());
        model.put("contratos", contratos);
        return new ModelAndView("usuario/contratoAux", model);
    }
    
    @RequestMapping(value = "buscarPorEmpresaEmpleado/{nombre}", method = RequestMethod.GET)
    public ModelAndView buscarPorEmpresaEmpleado(@PathVariable("nombre")String nombre, ModelMap model, HttpSession session){
        if(session.getAttribute(USUARIO) == null && session.getAttribute(CLIENTE) == null){
            return new ModelAndView("templates/index");
        }
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        List<Contrato> contratos = busquedasContratos.buscarPorEmpresaEmpleado(nombre, usuario.getId());
        model.put("contratos", contratos);
        return new ModelAndView("usuario/contratoAux", model);
    }
}
