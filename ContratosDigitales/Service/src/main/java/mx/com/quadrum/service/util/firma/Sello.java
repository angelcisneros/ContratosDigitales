package mx.com.quadrum.service.util.firma;

import java.security.PrivateKey;
import java.security.Signature;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.ssl.PKCS8Key;
//import sun.misc.BASE64Encoder;

/**
 * Clase que genera el sello correspondiente al comprobante que se genera en el sistema de información
 */

public class Sello {
    private byte[] clavePrivada;
        private String password;
        private byte[] textoCodificado;
        private String res;
        private String cadenaOriginal;


    /**
     * Se sobrecarga al constructor para generar el sello digital
     * 
     * @param pass
     * @param clavePrivada
     * @param cadenaOrigin
     */
    
    public Sello(String pass, byte[] clavePrivada, String cadenaOrigin){
            try{
                this.cadenaOriginal= cadenaOrigin;
                this.textoCodificado=cadenaOriginal.getBytes("UTF-8"); 
                this.password= pass;
                this.clavePrivada=clavePrivada; 
            }catch(Exception ea){
                ea.printStackTrace();
            }
        }

  
    public Sello(String pass, byte[] clavePrivada, byte[] cadenaOrigin){
            try{
                this.textoCodificado=cadenaOrigin; 
                this.password= pass;
                this.clavePrivada=clavePrivada; 
            }catch(Exception ea){
                ea.printStackTrace();
            }
        }
  
    /**
     * Método que genera el sello digital
     * implementando la firma SHA1withRSA
     * 
     * @return      Sello generado para el comprobante
     */
    
    public String GeneraSelloDigital(){            
            try{
                
                /* System.out.println("Sello PAssword: "+this.password);
                System.out.println("Sello PAssword char array: "+this.password.toCharArray());
                System.out.println("Sello PAssword char array tamaño: "+this.password.toCharArray().length);
                System.out.println("Sello Key privada: "+this.clavePrivada); */
                
                PKCS8Key pkcs8= new PKCS8Key(this.clavePrivada,this.password.toCharArray());
                PrivateKey privateKey = pkcs8.getPrivateKey();
                Signature firma= Signature.getInstance("SHA1withRSA"); 
                firma.initSign(privateKey);  
                firma.update(this.textoCodificado);
                byte[] signed=firma.sign();
                Base64 base64=new Base64(-1); 
                return base64.encodeToString(signed);
                }catch(Exception ea){
            }           
            return this.res;
    }
}