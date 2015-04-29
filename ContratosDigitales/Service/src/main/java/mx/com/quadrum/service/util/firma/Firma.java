/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.service.util.firma;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;
import mx.com.quadrum.entity.Contacto;
import mx.com.quadrum.entity.Contrato;
import static mx.com.quadrum.service.util.ManejadorArchivos.crearArchivoContenido;
import static mx.com.quadrum.service.util.Rutas.USUARIOS;
import static mx.com.quadrum.service.util.Validaciones.esRfcValido;
import static mx.com.quadrum.service.util.Validaciones.isNullOrEmpty;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vcisneros
 */
public class Firma {

    @Getter @Setter private MultipartFile poderNotarial;
    @Getter @Setter private MultipartFile ife;
    @Getter @Setter private MultipartFile firma;
    @Getter @Setter private MultipartFile cer;
    @Getter @Setter private MultipartFile key;
    @Getter @Setter private String password;
    @Getter @Setter private String rfc;
    @Getter @Setter private Integer idContrato;
    @Getter @Setter private List<String> errores = new ArrayList<String>();
    @Getter @Setter private Contrato contrato;
    @Getter @Setter private Contacto contacto;

    public Boolean validaCampos() {
        Boolean isValid = true;
        if (poderNotarial.isEmpty()) {
            errores.add("El Poder Notarial no puede ser vacio");
            isValid = false;
        }
        if (ife.isEmpty()) {
            errores.add("El IFE no puede ser vacio");
            isValid = false;
        }
//        if(firma.isEmpty()){
//            errores.add("El Poder Notarial no puede ser vacio");
//            isValid = false;
//        }
        if (cer.isEmpty()) {
            errores.add("El .cer no puede ser vacio");
            isValid = false;
        }
        if (key.isEmpty()) {
            errores.add("El .key no puede ser vacio");
            isValid = false;
        }
        if (isNullOrEmpty(password)) {
            errores.add("El Password no puede ser vacio");
            isValid = false;
        }
        if (!esRfcValido(rfc)) {
            errores.add("El RFC no puede ser vacio");
            isValid = false;
        }
        return isValid;
    }

    public boolean esValidoCerKeyAndPassword() {

        ValidacionesCertificado validacionesCertificado;
        Boolean isValid = true;
        try {
            validacionesCertificado = new ValidacionesCertificado(cer.getInputStream(), key.getInputStream(), password);
            if (!validacionesCertificado.validaCertificado()) {
                errores.add("EL certificado no es valido");
                isValid = false;
            }
            if (validacionesCertificado.validateCertificate()) {
                errores.add("El certificado es apocrifo");
                isValid = false;
            }
            if (validacionesCertificado.validaFecha().equals("caduco")) {
                errores.add("El certificado a caducado");
                isValid = false;
            }
            if (!validacionesCertificado.validaCertificado(rfc)) {
                errores.add("El RFC no corresponde a la empresa en sesión");
                isValid = false;
            }
            if (!validacionesCertificado.validaCorrespondencias()) {
                errores.add("EL password o el archivo .key no corresponden al certificado");
                isValid = false;
            }

        } catch (IOException ex) {
            Logger.getLogger(Firma.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return isValid;
    }

    public boolean esExtencionValida(String cadena, String ext) {
        if (cadena.length() >= 5) {
            cadena = cadena.substring(cadena.length() - 3, cadena.length());
            return cadena.equalsIgnoreCase(ext);
        }
        return false;
    }

    public boolean firmar() {
        return true;
    }

    public boolean guardarArchivos() {
        boolean guardado = true;
        try {
            
            
            String path = USUARIOS +  contrato.getUsuario().getMail() + "/" + contrato.getId();
            System.out.println(path);
            String pn =  path + "/poderNotarial" + regresaExtencion(poderNotarial.getOriginalFilename());
            String ifeS = path + "/ife" + regresaExtencion(ife.getOriginalFilename());
            String cerS = path + "/cer" + regresaExtencion(cer.getOriginalFilename());
            String keyS =path +  "/key" + regresaExtencion(key.getOriginalFilename());
           
            if(!crearArchivoContenido(pn, poderNotarial.getBytes())){
                guardado = false;
            }
            if(!crearArchivoContenido(ifeS, ife.getBytes())){
                guardado = false;
            }
            if(!crearArchivoContenido(cerS, cer.getBytes())){
                guardado = false;
            }
            if(!crearArchivoContenido(keyS, key.getBytes())){
                guardado = false;
            }
        } catch (IOException ex) {
            Logger.getLogger(Firma.class.getName()).log(Level.SEVERE, null, ex);
            guardado = false;
        }
        return guardado;
    }

    private String regresaExtencion(String name) {
        int len = name.length();
        return name.substring(len-4, len);
    }
    
}
