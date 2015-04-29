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
    public String homeController(HttpSession session, Model model) {
        Usuario u = (Usuario) session.getAttribute(USUARIO);
        if (u != null) {

            if (u.getEsAdmin()) {
                model.addAttribute("esAdmin", "esAdmin");
            }
            return "templates/inicio";
        }
        return "templates/index";
    }

    @RequestMapping(value = "*")
    public String error404(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "templates/index";
        }

        if (usuario.getEsAdmin()) {
            model.addAttribute("esAdmin", "esAdmin");
        }
        return "templates/404";
    }

    @RequestMapping(value = "inicio")
    public String inicio(String rfc, String password, Model model, HttpSession session) {
        if (usuarioService.estaRegistrado(rfc, password)) {
            Usuario usuario = usuarioService.buscarPorCorreo(rfc);
            List<Permiso> permisos = permisoService.buscarPorUsuario(usuario.getId());
            System.out.println("permisos: " + permisos.size());
            session.setAttribute(USUARIO, usuario);
            session.setAttribute(PERMISOS, permisos);
            session.setAttribute(CLIENTE, null);

            if (usuario.getPrimeraSesion()) {
                return ("usuario/cambiarPass");
            }
            model.addAttribute("permisos", permisos);
            if (usuario.getEsAdmin()) {
                model.addAttribute("esAdmin", "esAdmin");
            }
            return ("templates/inicio");
        }
        model.addAttribute("estaRegistrado", false);
        return ("templates/index");
    }

    @RequestMapping(value = "cliente")
    public String homeClienteController() {
        return "cliente/index";
    }

    @RequestMapping(value = "cerrarSesion")
    public String cerrarSession(HttpSession session) {
        session.invalidate();
        return "templates/index";
    }

}
