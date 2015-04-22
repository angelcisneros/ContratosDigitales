/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.quadrum.service.util.Rutas;
import mx.com.quadrum.service.util.firma.Firma;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author vcisneros
 */
public class RepresentacionImpresa {

    private Firma firma;
    private String jasper;
    private String path;
    private Map<String, Object> parametros;

    public RepresentacionImpresa(String mail) {
        parametros = new HashMap<String, Object>();
        path = "C:\\jboss-as-7.1.1.Final\\bin\\ContratosDigitales\\usuarios\\" + mail + "/" + 33;
        
    }

    public String ejecutaJasper() {
        jasper = "C:\\jboss-as-7.1.1.Final\\bin\\ContratosDigitales\\formatos\\13";
        new File(path).mkdir();
        path += "/" + 33;
        rellenaParametros();
        ejecutaJasperOculto();
        return "";
    }

    private void rellenaParametros() {
        parametros.put("nombre_contrato", "Miguel");
        parametros.put("monto", "123.45");
        parametros.put("empresa", "ESCOM");
        parametros.put("fecha_inicio", "12/45/89");
        parametros.put("fecha_fin", "78/96/25");
        parametros.put("representante_legal", "JAIMITO EL CARTERO");
//        parametros.put("rfc_empresa", firma.ge);
//        parametros.put("sello",);
//        parametros.put("firma",);

    }

    private boolean ejecutaJasperOculto() {
        try {
            System.out.println(jasper);
            for (Map.Entry<String, Object> entry : parametros.entrySet()) {
                String string = entry.getKey();
                String object = (String) entry.getValue();
                System.out.println(object);
                
            }
            File file = new File(jasper + ".jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros);//, new JRBeanCollectionDataSource(new ArrayList<String>()));

            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File(path + ".pdf"));
            exporter.exportReport();

            return true;
        } catch (JRException ex) {
            Logger.getLogger(RepresentacionImpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
