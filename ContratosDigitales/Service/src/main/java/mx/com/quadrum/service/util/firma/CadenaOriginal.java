package mx.com.quadrum.service.util.firma;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


/**
 * Clase que hace uso del XSTL del SAT para formar la cadena original de un documento XML
 */

public class CadenaOriginal {
    // ******** Constructor *********
    public CadenaOriginal(){ }

 /*    public String generaCadena(InputStream xml, String xslt){
        try{
            // xslt archivo de ayuda del sat que esta en paqfiles siempre se utiliza 
            // xml al archivo que se envia para generar la cadena original
            StreamSource xmlFile = new StreamSource(xml);
            StreamSource xsltFile = new StreamSource(new File(xslt));

            TransformerFactory xsltFactory = TransformerFactory.newInstance();
            Transformer transformer = xsltFactory.newTransformer(xsltFile);
            //en este objecto se guardara el resultado de nuestra transformacion
            ByteArrayOutputStream output= new ByteArrayOutputStream();

            //Se le aplica la transformacion a nuestro xmlFile, es decir a nuestro archivo con extencion xml
            transformer.transform(xmlFile, new StreamResult(output));
            //el resultado lo convertimos a un string
            cad=output.toString();
            return new sun.misc.BASE64Encoder().encode(limpiaCadenaOriginal(cad.replace("\n", "").replace("\t", "")).getBytes());

        }catch(Exception exp){
            exp.printStackTrace();
        }
        return cad;
    } */
    
    /**
     * Método que genera la cadena original del XML utilizando la hoja de transformación dada por el 
     * SAT
     *  
     * @param xml       Documento XML 
     * @param xslt      Hoja de transformación 
     * @return          Cadena original del documento XML
     */
    
    public byte[] generaCadenaDos(InputStream xml, String xslt){
        ByteArrayOutputStream output= new ByteArrayOutputStream();
        try{
            StreamSource xmlFile = new StreamSource(xml);
            StreamSource xsltFile = new StreamSource(new File(xslt));
            TransformerFactory xsltFactory = TransformerFactory.newInstance();
            Transformer transformer = xsltFactory.newTransformer(xsltFile);
            transformer.transform(xmlFile, new StreamResult(output));
            return output.toByteArray();
        }catch(Exception exp){
            exp.printStackTrace(); 
        }
        
        return output.toByteArray();
    }
    
    /**
     * Método que genera la cadena original del XML utilizando la hoja de transformación dada por el 
     * SAT
     *  
     * @param xml       Documento XML 
     * @param xslt      Hoja de transformación 
     * @return          Cadena original del documento XML
     */
    
    public String formarCadenaOriginal(InputStream xml, String xslt){
        try{
            StreamSource xmlFile = new StreamSource(xml);
            StreamSource xsltFile = new StreamSource(new File(xslt));
            TransformerFactory xsltFactory = TransformerFactory.newInstance();
            Transformer transformer = xsltFactory.newTransformer(xsltFile);
            ByteArrayOutputStream output= new ByteArrayOutputStream();            
            transformer.transform(xmlFile, new StreamResult(output));
            return output.toString();
        }catch(Exception exp){
            exp.printStackTrace();
        }
        return null;
    }

   /*  public String limpiaCadenaOriginal(String cadenaOriginal){
        String cadenaAux = "||";
        String cad = cadenaOriginal.replace("||", "");
        String [] split = cad.replace("|", "#").split("#");
        for(String a : split){
            cadenaAux += a.trim() + "|";   
        }    
        cadenaAux += "|";
        return cadenaAux;
    } */
}