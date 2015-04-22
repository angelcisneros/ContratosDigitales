package mx.com.quadrum.service.util.firma;

import java.io.InputStream;

import java.math.BigInteger;

import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.apache.commons.codec.binary.Base64;


/**
 * Clase que permite obtener los datos de un archivo con extensión cer.
 */

public class AtributosCertificado {
    
    private InputStream miInputStream;
    private String emisor;
    private String fechaInicial;
    private String finalFecha;
    private String subject;
    private String noCertificado;
    private String certificado; 
    // ******** Constructor *********
    public AtributosCertificado(InputStream miInputStream) {
        this.miInputStream = miInputStream;
    }
    
    /**
     * Método que obtiene datos del certificado para
     * utilizarce en operaciones posteriores
     */

    public void obtenDatosCertificado() {

        try {

            CertificateFactory cf = CertificateFactory.getInstance("X509");
            X509Certificate c = (X509Certificate)cf.generateCertificate(this.miInputStream);

            this.setEmisor(c.getIssuerDN().toString());
            this.setFechaInicial(c.getNotBefore().toString());
            
            this.setFinalFecha(c.getNotAfter().toString());
            this.setSubject(c.getSubjectDN().toString());

            BigInteger byteArray = c.getSerialNumber();
            this.setNoCertificado(new String(byteArray.toByteArray()));
            
            String temp = "";
            Base64 b64 = new Base64(-1);
            temp = b64.encodeToString(c.getEncoded());
            this.setCertificado(temp);

        } catch (CertificateException e) {
            e.printStackTrace();

        }
    }
    
    // ******** SETTERS Y GETTERS **********

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFinalFecha(String finalFecha) {
        this.finalFecha = finalFecha;
    }

    public String getFinalFecha() {
        return finalFecha;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setNoCertificado(String noCertificado) {
        this.noCertificado = noCertificado;
    }

    public String getNoCertificado() {
        return noCertificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public String getCertificado() {
        return certificado;
    }
}