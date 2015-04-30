/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.service.util.firma.jasper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.quadrum.service.util.Rutas;
import mx.com.quadrum.service.util.firma.ContratoDatos;
import mx.com.quadrum.service.util.firma.Firma;
import net.sf.jasperreports.engine.JRDataSource;
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
   
    private String jasper;
    private String path;
    private boolean creado;
    List<ContratoDatos> ds;
    
    public RepresentacionImpresa(Firma firma, String mail) {
//        jasper = Rutas.FORMATOS + firma.getContrato().getTipoContrato().getId() + "/main.jasper";
                jasper = Rutas.FORMATOS +  "1/main.jasper";
        path = Rutas.USUARIOS + mail + "/" + firma.getContrato().getId();
        if(new File(path).mkdir()){
            creado = true;
            path += "/" + firma.getContrato().getId();
            ds = new ArrayList<>();
            ds.add(new ContratoDatos(firma));
        }else{
            creado = false;
        }
    }

    public String ejecutaJasper() {
        
        ejecutaJasperOculto();
        return "";
    }

    private boolean ejecutaJasperOculto() {
        List<RepresentacionImpresa> dataSource = new ArrayList<RepresentacionImpresa>();
        dataSource.add(this);
        try {
            File file = new File(jasper);
            JasperReport reporte = (JasperReport) JRLoader.loadObject(file);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(dataSource));
            
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

    public JRDataSource getUno() {
        return new JRBeanCollectionDataSource(ds);
    }
    public JRDataSource getDos() {
        return new JRBeanCollectionDataSource(ds);
    }
    public JRDataSource getTres() {
        return new JRBeanCollectionDataSource(ds);
    }
    public JRDataSource getCuatro() {
        return new JRBeanCollectionDataSource(ds);
    }
    public JRDataSource getCinco() {
        return new JRBeanCollectionDataSource(ds);
    }
}
