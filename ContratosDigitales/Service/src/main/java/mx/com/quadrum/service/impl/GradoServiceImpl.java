/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service.impl;

import java.util.List;
import mx.com.quadrum.service.*;
import mx.com.quadrum.entity.Grado;
import mx.com.quadrum.repository.GradoRepository;
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
public class GradoServiceImpl implements GradoService {
    @Autowired
    GradoRepository gradoRepository;

     private static final String GRADO = "una grado.#";
    
    @Override
    public String agregar(Grado grado) {
        if(gradoRepository.agregar(grado)){
            return ADD_CORRECT + GRADO + grado.getId();
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String editar(Grado grado) {
        if(gradoRepository.editar(grado)){
            return UPDATE_CORRECT + GRADO;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String eliminar(Grado grado) {
        if(gradoRepository.eliminar(grado)){
            return DELETE_CORRECT + GRADO;
        }
        return ERROR_HIBERNATE;
    }


    @Override
    public Boolean esValid(Grado grado) {
        return true;
    }

    @Override
    public Grado buscarPorId(Integer id) {
        return gradoRepository.buscarPorId(id);
    }

    @Override
    public List<Grado> buscarTodos() {
        return gradoRepository.buscarTodos();
    }

}
