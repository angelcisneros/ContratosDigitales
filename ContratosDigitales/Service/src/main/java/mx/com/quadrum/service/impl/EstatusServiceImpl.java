/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service.impl;

import java.util.List;
import mx.com.quadrum.entity.Estatus;
import mx.com.quadrum.service.*;
import mx.com.quadrum.entity.Estatus;
import mx.com.quadrum.repository.EstatusRepository;
import static mx.com.quadrum.service.util.MensajesCrud.ADD_CORRECT;
import static mx.com.quadrum.service.util.MensajesCrud.DELETE_CORRECT;
import static mx.com.quadrum.service.util.MensajesCrud.ERROR_HIBERNATE;
import static mx.com.quadrum.service.util.MensajesCrud.UPDATE_CORRECT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vcisneros
 */
@Service
public class EstatusServiceImpl implements EstatusService {
   @Autowired
    EstatusRepository estatusRepository;

    private static final String ESTATUS = "un estatus.#";
    
    @Override
    public String agregar(Estatus estatus) {
        if(estatusRepository.agregar(estatus)){
            return ADD_CORRECT + ESTATUS + estatus.getId();
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String editar(Estatus estatus) {
        if(estatusRepository.editar(estatus)){
            return UPDATE_CORRECT + ESTATUS;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String eliminar(Estatus estatus) {
        if(estatusRepository.eliminar(estatus)){
            return DELETE_CORRECT + ESTATUS;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public Boolean esValid(Estatus estatus) {
        return true;
    }

    @Override
    public Estatus buscarPorId(Integer id) {
        return estatusRepository.buscarPorId(id);
    }

    @Override
    public List<Estatus> buscarTodos() {
        return estatusRepository.buscarTodos();
    }

}
