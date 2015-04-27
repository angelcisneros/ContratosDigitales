/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.contratos.controller.crud;

import java.util.List;
import javax.servlet.http.HttpSession;
import mx.com.quadrum.entity.Permiso;
import mx.com.quadrum.entity.TipoContrato;
import mx.com.quadrum.entity.Usuario;
import mx.com.quadrum.service.EstatusService;
import mx.com.quadrum.service.GradoService;
import mx.com.quadrum.service.TipoContratoService;
import mx.com.quadrum.service.UsuarioService;
import static mx.com.quadrum.service.util.Llave.PERMISOS;
import static mx.com.quadrum.service.util.Llave.USUARIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author vcisneros
 */

@Controller
public class CatalogosController {
    
    @Autowired
    TipoContratoService tipoContratoService;
    
    @Autowired
    GradoService gradoService;
    
    @Autowired
    EstatusService estatusService;
    
    @Autowired
    UsuarioService usuarioService;
    
    @RequestMapping(value = "catalogo", method = RequestMethod.GET)
    public String tipoContrato(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        List<Permiso> permisos = (List<Permiso>) session.getAttribute(PERMISOS);
        
        if(usuario == null || permisos == null){
            return "templates/index";
        }
//        if(!usuarioService.tienePermiso(usuario, "catalogo")){
//            return "templates/noAutorizado";
//        }
        model.addAttribute("permisos", permisos);
        model.addAttribute("estatus", estatusService.buscarTodos());
        model.addAttribute("grado", gradoService.buscarTodos());
        model.addAttribute("tipoContratos", tipoContratoService.buscarTodos());
        model.addAttribute("tipocontrato", new TipoContrato());
        return "crud/catalogo";
    }
}
