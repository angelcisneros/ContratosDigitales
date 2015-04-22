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
import mx.com.quadrum.entity.Usuario;
import static mx.com.quadrum.service.util.Llave.USUARIO;
import static mx.com.quadrum.service.util.ManejadorArchivos.convierteArchivoToArregloBytes;
import static mx.com.quadrum.service.util.Rutas.USUARIOS;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author vcisneros
 */
@Controller
public class DescargarArchivos extends HttpServlet {

    @RequestMapping(value = "descargarArchivos/{idContrato}", method = RequestMethod.GET)
    public void descargarArchivos(@PathVariable("idContrato") String idContrato, HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/zip");
        Usuario usuario = (Usuario) session.getAttribute(USUARIO);

        response.setHeader("Content-Disposition", "attachment; filename=\"archivos.zip\"");
        ServletOutputStream sos;
        String pathFiles = USUARIOS + usuario.getMail() + "/" + idContrato;
        try {
            ZipFile folder = new ZipFile(pathFiles + "/archivos.zip");
            
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // set compression method to deflate compression
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            folder.addFolder(pathFiles, parameters);
            sos = response.getOutputStream();
            File zipFile = new File(pathFiles + "/archivos.zip");
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
