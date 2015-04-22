/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.service.util.firma.jasper;

import java.io.File;
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
    private boolean creado;
    private Map<String, Object> parametros;

    public RepresentacionImpresa(Firma firma, String mail) {
        this.firma = firma;
        parametros = new HashMap<>();
        path = Rutas.USUARIOS + mail + "/" + firma.getContrato().getId();
        if(new File(path).mkdir()){
            creado = true;
        }else{
            creado = false;
        }
    }

    public String ejecutaJasper() {
        jasper = Rutas.FORMATOS + firma.getContrato().getTipoContrato().getId();
        path += "/" + firma.getContrato().getId();
        System.out.println(path);
        rellenaParametros();
        ejecutaJasperOculto();
        return "";
    }

    private void rellenaParametros() {
        parametros.put("nombre_contrato", firma.getContrato().getNombre());
        parametros.put("monto", firma.getContrato().getMonto().toString());
        parametros.put("empresa", firma.getContacto().getEmpresa().getRazonSocial());
        parametros.put("fecha_inicio", firma.getContrato().getFechaCreacion().toString());
        parametros.put("fecha_fin", firma.getContrato().getFechaVencimiento().toString());
        parametros.put("representante_legal", firma.getContacto().getNombre());
//        parametros.put("rfc_empresa", firma.ge);
//        parametros.put("sello",);
//        parametros.put("firma",);

    }

    private boolean ejecutaJasperOculto() {
        try {
            System.out.println(jasper);
            File file = new File(jasper + ".jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros);

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
