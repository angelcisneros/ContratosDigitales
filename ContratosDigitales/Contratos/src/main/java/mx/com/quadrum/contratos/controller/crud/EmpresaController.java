/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.contratos.controller.crud;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import mx.com.quadrum.entity.Empresa;
import mx.com.quadrum.entity.Permiso;
import mx.com.quadrum.entity.Usuario;
import mx.com.quadrum.service.EmpresaService;
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
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @Autowired
    UsuarioService usuarioService;
    
    @RequestMapping(value = "empresa", method = RequestMethod.GET)
    public String empresa(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        List<Permiso> permisos = (List<Permiso>) session.getAttribute(PERMISOS);
                
        if(usuario == null || permisos == null){
            return "templates/index";
        }
         if(!usuarioService.tienePermiso(usuario, "catalogo")){
            return "templates/noAutorizado";
        }
        model.addAttribute("permisos", permisos);
        model.addAttribute("empresa",empresaService.buscarTodos());
        return "crud/empresa";
    }

    @ResponseBody
    @RequestMapping(value = "agregarEmpresa", method = RequestMethod.POST)
    public String agregarEmpresa(@Valid @ModelAttribute("empresa") Empresa empresa, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            return ERROR_DATOS;
        }
        return empresaService.agregar(empresa);
    }

    @ResponseBody
    @RequestMapping(value = "editarEmpresa", method = RequestMethod.POST)
    public String editarEmpresa(@Valid @ModelAttribute("empresa") Empresa empresa, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        if (bindingResult.hasErrors()) {
            return ERROR_DATOS;
        }
        return empresaService.editar(empresa);
    }

    @ResponseBody
    @RequestMapping(value = "eliminarEmpresa", method = RequestMethod.POST)
    public String eliminarEmpresa(Empresa empresa, BindingResult bindingResult, HttpSession session) {
        if (session.getAttribute("usuario") == null){
            return SESION_CADUCA;
        }
        return empresaService.eliminar(empresa);
    }
}
