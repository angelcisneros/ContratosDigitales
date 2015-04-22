/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.contratos.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import mx.com.quadrum.entity.Permiso;
import mx.com.quadrum.entity.Usuario;
import mx.com.quadrum.service.PermisoService;
import mx.com.quadrum.service.UsuarioService;
import static mx.com.quadrum.service.util.Llave.CLIENTE;
import static mx.com.quadrum.service.util.Llave.PERMISOS;
import static mx.com.quadrum.service.util.Llave.USUARIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author vcisneros
 */
@Controller
public class HomeController {
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    PermisoService permisoService;

    @RequestMapping(value = "/")
    public String homeController() {
        return "templates/index";
    }

    @RequestMapping(value = "*")
    public String error404(HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return "templates/index";
        }
        return "templates/404";
    }

    @RequestMapping(value = "inicio")
    public ModelAndView inicio(String rfc, String password, Model model, HttpSession session) {
        if(usuarioService.estaRegistrado(rfc, password)){
            Usuario usuario = usuarioService.buscarPorCorreo(rfc);
            List<Permiso> permisos = permisoService.buscarPorUsuario(usuario.getId());
            System.out.println("permisos: " + permisos.size());
            session.setAttribute(USUARIO, usuario);
            session.setAttribute(PERMISOS, permisos);
            session.setAttribute(CLIENTE, null);
            if(usuario.getPrimeraSesion()){
                return new ModelAndView("usuario/cambiarPass");
            }
            return new ModelAndView("templates/inicio", "permisos", permisos);
        }
        return new ModelAndView("templates/index", "estaRegistrado", false);
    }
    
    @RequestMapping(value = "cliente")
    public String homeClienteController() {
        return "cliente/index";
    }
    
   
}
