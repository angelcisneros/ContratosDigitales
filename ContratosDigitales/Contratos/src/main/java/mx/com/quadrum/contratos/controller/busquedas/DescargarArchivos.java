/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.contratos.controller.busquedas;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.com.quadrum.entity.Contrato;
import mx.com.quadrum.entity.Usuario;
import mx.com.quadrum.service.ContratoService;
import static mx.com.quadrum.service.util.Llave.CLIENTE;
import static mx.com.quadrum.service.util.Llave.USUARIO;
import static mx.com.quadrum.service.util.ManejadorArchivos.convierteArchivoToArregloBytes;
import static mx.com.quadrum.service.util.Rutas.USUARIOS;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
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
public class DescargarArchivos extends HttpServlet {

    @Autowired
    ContratoService contratoService;
    
    @ResponseBody
    @RequestMapping(value = "descargarArchivos/{idContrato}", method = RequestMethod.GET)
    public void descargarArchivos(@PathVariable("idContrato") Integer idContrato, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        if(session.getAttribute(USUARIO) == null && session.getAttribute(CLIENTE) == null){
            return;
        }
        
        response.setContentType("application/zip");
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);
        Contrato contrato = contratoService.buscarPorId(idContrato);
        response.setHeader("Content-Disposition", "attachment; filename=\""+ contrato.getNombre() + ".zip\"");
        ServletOutputStream sos;
        String pathFiles = USUARIOS + usuario.getMail() + "/" + contrato.getNombre();
        
        try {
            ZipFile folder = new ZipFile(pathFiles + "/" + contrato.getNombre() + ".zip");
            
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // set compression method to deflate compression
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            folder.addFolder(pathFiles, parameters);
            sos = response.getOutputStream();
            File zipFile = new File(pathFiles + "/" + contrato.getNombre() + ".zip");
            sos.write(convierteArchivoToArregloBytes(zipFile));
            sos.flush();
            zipFile.delete();
        } catch (IOException ex) {
            Logger.getLogger(DescargarArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ZipException ex) {
            Logger.getLogger(DescargarArchivos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
