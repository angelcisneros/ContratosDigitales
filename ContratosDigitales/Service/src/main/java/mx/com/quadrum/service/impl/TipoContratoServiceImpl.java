/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.com.quadrum.entity.TipoContrato;
import mx.com.quadrum.repository.TipoContratoRepository;
import mx.com.quadrum.service.*;
import static mx.com.quadrum.service.util.ManejadorArchivos.crearArchivoContenido;
import static mx.com.quadrum.service.util.MensajesCrud.ADD_CORRECT;
import static mx.com.quadrum.service.util.MensajesCrud.DELETE_CORRECT;
import static mx.com.quadrum.service.util.MensajesCrud.ERROR_HIBERNATE;
import static mx.com.quadrum.service.util.MensajesCrud.UPDATE_CORRECT;
import static mx.com.quadrum.service.util.Rutas.FORMATOS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vcisneros
 */
@Service
public class TipoContratoServiceImpl implements TipoContratoService {

    @Autowired
    TipoContratoRepository tipoContratoRepository;

    private static final String TIPO_CONTRATO = "una tipoContrato.";

    @Override
    public String agregar(TipoContrato tipoContrato) {
        if (tipoContratoRepository.agregar(tipoContrato)) {
            return ADD_CORRECT + TIPO_CONTRATO;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String editar(TipoContrato tipoContrato, MultipartFile formato) {
        boolean actualizadFormato = false;
        if(!formato.isEmpty()){
            String path = FORMATOS + "/" + tipoContrato.getId() + ".jasper";
            File jasper = new File(path);
            if(jasper.exists()){
                try {
                    jasper.delete();
                    crearArchivoContenido(path, formato.getBytes());
                    actualizadFormato = true;
                } catch (IOException ex) {
                    Logger.getLogger(TipoContratoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (tipoContratoRepository.editar(tipoContrato)) {
            if(!actualizadFormato){
                return UPDATE_CORRECT + TIPO_CONTRATO + " pero la plantilla no pudo ser modificada, compruebe que no esta siendo usada en otra pestaña o navegador.";
            }
            return UPDATE_CORRECT + TIPO_CONTRATO;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String eliminar(TipoContrato tipoContrato) {
        if (tipoContratoRepository.editar(tipoContrato)) {
            return DELETE_CORRECT + TIPO_CONTRATO;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public Boolean esValid(TipoContrato tipoContrato) {
        return true;
    }

    @Override
    public TipoContrato buscarPorId(Integer id) {
        return tipoContratoRepository.buscarPorId(id);
    }

    @Override
    public List<TipoContrato> buscarTodos() {
        return tipoContratoRepository.buscarTodos();
    }

    @Override
    public String agregar(TipoContrato tipoContrato, MultipartFile formato) {
        
        if (!formato.isEmpty()) {
            String path = FORMATOS + tipoContrato.getNombre() + ".jasper";
            try {
                if (crearArchivoContenido(path, formato.getBytes())) {
                    if (tipoContratoRepository.agregar(tipoContrato)) {
                        File file = new File(path);
                        file.renameTo(new File(FORMATOS + tipoContrato.getId() + ".jasper"));
                        return ADD_CORRECT + TIPO_CONTRATO;
                    }
                    return ERROR_HIBERNATE;
                } else {
                    return "Ups!...#No fue posible guardar el formato, intente mas tarde.";
                }
            } catch (IOException ex) {
                Logger.getLogger(TipoContratoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "Ups!...#Encontramos que el archivo que subio esta vacio.";

    }

}
