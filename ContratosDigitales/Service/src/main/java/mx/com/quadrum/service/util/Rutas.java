/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.service.util;

import mx.com.quadrum.entity.Contrato;

/**
 *
 * @author angel
 */
public class Rutas {

    //public static final String JASPER = "C:/Users/vcisneros/Documents/NetBeansProjects/Repositorio/Nomifast/src/main/webapp/WEB-INF/jasper/mainNomina.jasper";  ;
    public static final String SERVER = System.getProperty("user.dir") + "/ContratosDigitales/";
    public static final String FORMATOS = System.getProperty("user.dir") + "/ContratosDigitales/formatos/";
    public static final String USUARIOS = System.getProperty("user.dir") + "/ContratosDigitales/usuarios/";

    public static final String ASUNTO_MAIL_TIMBRADO = "Timbrado de Recibo de Nómina";
    public static final String MENSAJE_MAIL_TIMBRADO = "Estimado usuario, a continuación se anexan sus recibos de nómina (Representación Impresa y XML ), Gracias por utilizar nuestro servicio.";
    public static final String ASUNTO_MAIL_CANCELADO = "Cancelación de Recibo de Nómina";
    public static final String MENSAJE_MAIL_CANCELADO = "Estimado usuario, a continuación se anexa su recibo de cancelación (Archivo XML).";

    public static final String ACCESO_SISTEMA = "Credenciales del sistema Quadrum Contratos";
    public static final String CADENA_ORIGINAL = System.getProperty("user.dir") + "/ContratosDigitales/cadenaoriginal_3_2.xslt";

    public static final String ASUNTO_DISPONIBLE_FIRMAR = "Ahora puede firmar su contrato.";
    public static final String ASUSNTO_FIRMADO = "Un cliente ah firmado :D";

    public static String acomodaTexto(Contrato contrato) {
        String mensaje = "";
        mensaje += "Equipo Quadrum le notifica que el contrato de servicios con clave " + contrato.getNombre();
        mensaje += " que hace referencia a la prestación de servicios de " + contrato.getEstatus().getNombre();
        mensaje += " y con un monto de $" + contrato.getMonto() + " ya esta disponible para que pueda firmarlo.";
        mensaje += "\n\nRecuerde que necesitara varios archivos";
        mensaje += "\n\nVisite\t\t";
        return mensaje;
    }

    public static String clienteFirmo(Contrato contrato) {
        String mensaje = "";
        if (contrato.getContacto().getEmpresa() != null) {
            mensaje += "El cliente " + contrato.getContacto().getEmpresa().getRazonSocial() + " a firmado el contrato" + contrato.getNombre();
        } else {
            mensaje += "El cliente " + contrato.getContacto().getNombre() + " ";
            mensaje += contrato.getContacto().getPaterno()+ " ";
            mensaje += contrato.getContacto().getMaterno()+ " ";
        }
        mensaje += " con un monto de $" + contrato.getMonto() + ". Compruebe que los documentos que subio son válidos";
        return mensaje;
    }

    public static String regresarLeyenda(String clave) {
        return "HE LEDO, Y ACEPTO LAS CONDICIONES DESCRITAS EN EL CONTRATO CON CLAVE " + clave + " DE CENTRO DE VALIDACIÓN DIGITAL CVDSA S.A DE C.V.";
    }
}