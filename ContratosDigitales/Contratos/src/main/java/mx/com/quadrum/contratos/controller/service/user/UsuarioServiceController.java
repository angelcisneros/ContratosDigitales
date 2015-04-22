/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.contratos.controller.service.user;

import javax.servlet.http.HttpSession;
import mx.com.quadrum.service.PermisoUsuarioService;
import static mx.com.quadrum.service.util.MensajesCrud.SESION_CADUCA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author vcisneros
 */
@Controller
public class UsuarioServiceController {

    @Autowired
    PermisoUsuarioService permisoUsuarioService;

    @ResponseBody
    @RequestMapping(value = "agregarPermisosToUser/{id}", method = RequestMethod.POST)
    public String agregarPermisosUsuarios(@PathVariable("id") String id, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return SESION_CADUCA;
        }
        return permisoUsuarioService.agregar(id);
    }

    @ResponseBody
    @RequestMapping(value = "permisosPorUsuario/{id}", method = RequestMethod.POST)
    public String permisosPorUsuario(@PathVariable("id") String id, HttpSession session) {
        if (session.getAttribute("usuario") == null) {
            return SESION_CADUCA;
        }
        return permisoUsuarioService.buscarPorUsuario(Integer.parseInt(id));        
    }
    
    
   
}
