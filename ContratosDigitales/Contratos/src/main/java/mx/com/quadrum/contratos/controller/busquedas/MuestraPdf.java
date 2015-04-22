/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.contratos.controller.busquedas;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.com.quadrum.entity.Usuario;
import mx.com.quadrum.service.UsuarioService;
import static mx.com.quadrum.service.util.Llave.USUARIO;
import static mx.com.quadrum.service.util.Rutas.USUARIOS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author vcisneros
 */
@Controller
public class MuestraPdf  extends HttpServlet {
    
    @Autowired
    UsuarioService usuarioService;
    
    @RequestMapping(value = "muestraPdf/{idContrato}/{idEmpleado}", method = RequestMethod.GET)
    public void muestraPdf(@PathVariable("idContrato") String idContrato, @PathVariable("idEmpleado") Integer idEmpleado,
            HttpSession session, HttpServletRequest request, HttpServletResponse response){
        
        response.setContentType("application/pdf");
        
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        System.out.println(idContrato + "  " + idEmpleado);
        Usuario usuario = usuarioService.buscarPorId(idEmpleado);
        File pdf = new File(USUARIOS + usuario.getMail() + "\\" + idContrato + "\\"+ idContrato + ".pdf");
        try {
            InputStream in = new FileInputStream(pdf);
            byte[] data = new byte[in.available()];
            in.read(data);
            javax.servlet.ServletOutputStream servletoutputstream = response.getOutputStream();

            servletoutputstream.write(data);
            servletoutputstream.flush();
            servletoutputstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    @RequestMapping(value = "muestraPdf/{idContrato}", method = RequestMethod.GET)
    public void muestraPdf(@PathVariable("idContrato") String idContrato, HttpSession session, HttpServletRequest request, HttpServletResponse response){
        
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        response.setContentType("application/pdf");
        
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        File pdf = new File(USUARIOS + usuario.getMail() + "\\" + idContrato + "\\"+ idContrato + ".pdf");
        try {
            InputStream in = new FileInputStream(pdf);
            byte[] data = new byte[in.available()];
            in.read(data);
            javax.servlet.ServletOutputStream servletoutputstream = response.getOutputStream();

            servletoutputstream.write(data);
            servletoutputstream.flush();
            servletoutputstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
