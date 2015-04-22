/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.service.util.firma;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.cert.CertificateException;
import javax.security.cert.CertificateExpiredException;
import javax.security.cert.CertificateNotYetValidException;
import javax.security.cert.X509Certificate;
import mx.bigdata.sat.security.KeyLoader;
import org.apache.commons.io.IOUtils;
import org.apache.commons.ssl.PKCS8Key;

/**
 *
 * @author vcisneros
 */
public class ValidacionesCertificado {

    private byte[] clavePrivada;
    private byte[] clavePublica;
    private String password;
    private String subject;
    private String PARAMETRO = "OU";
    private boolean correcto = true;
    private boolean error = false;
    private X509Certificate certificado;

    private InputStream cerInputStream;
    private InputStream keyInputStream;

    // ********* Constructores ************
    public ValidacionesCertificado(InputStream archivoClavePublica, InputStream archivoClavePrivada, String password) {

        try {
            this.clavePrivada = IOUtils.toByteArray(archivoClavePrivada);
            this.clavePublica = IOUtils.toByteArray(archivoClavePublica);
            //this.clavePublica2=IOUtils.toString(archivoClavePublica);
            this.password = password;
            this.certificado = X509Certificate.getInstance(this.clavePublica);
            cerInputStream = archivoClavePublica;
            keyInputStream = archivoClavePrivada;
            //this.cadenaOriginal= cadenaOrigin;
            /* X509Certificate cert = X509Certificate.getInstance(this.clavePublica);
             boolean statusFecha;
             int statusFIEL=-1;
             boolean statusCorresp;
             boolean statusLCO;
             statusFecha=validaFecha(cert);
             System.out.println("\nel status fecha es: \n" + statusFecha);
             //statusFIEL=validaFIEL(cert);
             //System.out.println("el status FIEL es: " + statusFIEL);
             statusCorresp=validaCorrespondencias();
             System.out.println("\nel status de que el .key y el .cer correspondan es: \n" + statusCorresp);
             statusLCO=validaLCO();
             System.out.println("\nel status LCO es: \n" + statusLCO); */
        } catch (Exception ea) {
            ea.printStackTrace();
        }
    }

    public ValidacionesCertificado(InputStream archivoCertificado) {

        try {
            this.clavePublica = IOUtils.toByteArray(archivoCertificado);

            try {
                certificado = X509Certificate.getInstance(this.clavePublica);
            } catch (CertificateException ex) {
                Logger.getLogger(ValidacionesCertificado.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(ValidacionesCertificado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Método que valida la correspondencia del certificado con el RFC del
     * emisor
     *
     * @param rfc RFC del emisor
     * @return true si corresponde el certificado al emisor, en otro caso false
     */
    public boolean validaCertificado(String rfc) {
        String Issuer = certificado.getSubjectDN().getName();
        if (!Issuer.contains(rfc)) {
            return false;
        }
        return true;
    }

    /**
     * Método que revisa la valides del certificado (fecha y hora)
     *
     * @param cert Certificado correspondiente al emisor
     * @return caduco, fechaErronea si el certificado esta mal, en otro caso
     * cadena vacia
     */
    public String validaFecha() {
        String validacion = "";

        try {
            certificado.checkValidity();
        } catch (CertificateExpiredException ex) {
            Logger.getLogger(ValidacionesCertificado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CertificateNotYetValidException ex) {
            Logger.getLogger(ValidacionesCertificado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return validacion;
    }

    /**
     * Método que valida si el certificado es valido ante el SAT
     *
     * @param cert Certificado a validar
     * @return true si el certificado es valido, en otro caso false
     */
    public boolean validaCertificado() {
        int i = 0;
        int j = 0;
        String word, subword;
        StringTokenizer token, subToken;
        subject = certificado.getSubjectDN().toString();
        token = new StringTokenizer(subject, ",");
        while (token.hasMoreTokens()) {
            word = token.nextToken();
            i = 1;
            subToken = new StringTokenizer(word, "=");
            while (subToken.hasMoreTokens()) {
                subword = subToken.nextToken();
                i++;
                if (subword.contains(PARAMETRO)) {
                    j++;
                }
            }
        }
        if (j > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que valida el password y que la llave privada corresponda a la
     * llave publica
     *
     * @return true si el password y llave privada corresponden, en otro caso
     * false
     */
    public boolean validaCorrespondencias() {

        try {

            PKCS8Key pkcs8 = new PKCS8Key(this.clavePrivada, this.password.toCharArray());
            //valida el pass
            PrivateKey pk = pkcs8.getPrivateKey();
            //valida que la llave privada corresponda  a la llave publica
            X509Certificate cert = X509Certificate.getInstance(this.clavePublica);
            Signature firma = Signature.getInstance("SHA1withRSA");
            firma.initSign(pk);
            byte[] firmado = firma.sign();
            firma.initVerify(cert.getPublicKey());
            if (firma.verify(firmado)) {
                return this.correcto;
            } else {
                return this.error;
            }
        } catch (GeneralSecurityException e) {

            return this.error;
        } catch (CertificateException e) {

            return this.error;
        }
    }
    
    /**
     * Método que valida si el certificado es apocrifo, no valido ante el SAT
     *
     * @param cert Certificado a validar
     * @return true si el certificado es apocrifo, en otro caso false
     */
    public boolean validateCertificate() {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            List mylist = new ArrayList();
            TrustAnchor anchor = new TrustAnchor((java.security.cert.X509Certificate) importCertificate(cerInputStream), null);
            mylist.add(certificado);
            CertPath cp = cf.generateCertPath(mylist);
            PKIXParameters params = new PKIXParameters(Collections.singleton(anchor));
            params.setRevocationEnabled(false);
            CertPathValidator cpv = CertPathValidator.getInstance("PKIX");
            return true;
        } catch (Exception ex) {
            System.out.println("Expecion causada a proposito :P");
        }
        return false;
    }

    /**
     * Método donde se realiza la importacion de un certificado InputStream a un
     * X509Certificate
     *
     * @param cer Certificado del emisor
     * @return Certiticado en el estandar X509Certificate
     * @throws CertificateException
     * @throws IOException
     */
    private static java.security.cert.X509Certificate importCertificate(InputStream cer) throws CertificateException, IOException {
        try {
            return KeyLoader.loadX509Certificate(cer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Método donde se realiza la importacion de un certificado InputStream a un
     * X509Certificate
     *
     * @param path Ruta del certificado emisor
     * @return Certiticado en el estandar X509Certificate
     * @throws CertificateException
     * @throws IOException
     */
    public static java.security.cert.X509Certificate importCertificate(String path) throws CertificateException,
            IOException {
        File file = new File(path);
        FileInputStream is = new FileInputStream(file);

        try {
            return KeyLoader.loadX509Certificate(is);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
