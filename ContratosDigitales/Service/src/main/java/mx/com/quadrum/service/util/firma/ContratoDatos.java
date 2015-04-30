/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service.util.firma;

import static mx.com.quadrum.service.util.NumberToLetterConverter.convertNumberToLetterMethod;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author vcisneros
 */
public final class ContratoDatos {
    
    @Getter @Setter private String cliente;
    @Getter @Setter private String rfc;
    @Getter @Setter private String apoderado;
    @Getter @Setter private String folios;
    @Getter @Setter private String domicilio;
    @Getter @Setter private String monto;
    @Getter @Setter private String montoLetra;
    @Getter @Setter private String fechaInicio;
    @Getter @Setter private String fechaFin;
    @Getter @Setter private String sello;
    @Getter @Setter private boolean fisica;
    

    public ContratoDatos(Firma firma) {
        seteaDatos(firma);
    }
    
    public void seteaDatos(Firma firma){
        //si empresa es null entonces es una persona fisica
        if(firma.getContacto().getEmpresa() == null){
            cliente = firma.getContacto().getNombre() + " " + firma.getContacto().getPaterno() + " " + firma.getContacto().getMaterno();
            rfc = firma.getContacto().getRfc();
            domicilio = firma.getContacto().getDireccion();
            fisica = true;
        }
        folios = firma.getContrato().getFolios().toString();
        monto = firma.getContrato().getMonto().toString();
        montoLetra = convertNumberToLetterMethod(monto,"MX");
        fechaInicio = firma.getContrato().getFechaCreacion().toString();
        fechaFin = firma.getContrato().getFechaVencimiento().toString();
    }

    
    
}
