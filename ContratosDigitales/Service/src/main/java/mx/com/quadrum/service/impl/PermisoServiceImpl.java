/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service.impl;

import java.util.List;
import mx.com.quadrum.service.*;
import mx.com.quadrum.entity.Permiso;
import mx.com.quadrum.repository.PermisoRepository;
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
public class PermisoServiceImpl implements PermisoService {
   @Autowired
    PermisoRepository permisoRepository;

     private static final String PERMISO = "una permiso.#";
    
    @Override
    public String agregar(Permiso permiso) {
        if(permisoRepository.agregar(permiso)){
            return ADD_CORRECT + PERMISO + permiso.getId();
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String editar(Permiso permiso) {
        if(permisoRepository.editar(permiso)){
            return UPDATE_CORRECT + PERMISO;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String eliminar(Permiso permiso) {
        if(permisoRepository.eliminar(permiso)){
            return DELETE_CORRECT + PERMISO;
        }
        return ERROR_HIBERNATE;
    }


    @Override
    public Boolean esValid(Permiso permiso) {
        return true;
    }

    @Override
    public Permiso buscarPorId(Integer id) {
        return permisoRepository.buscarPorId(id);
    }

    @Override
    public List<Permiso> buscarTodos() {
        return permisoRepository.buscarTodos();
    }

    @Override
    public List<Permiso> buscarPorUsuario(Integer idUsuario) {
        return permisoRepository.buscarPorUsuario(idUsuario);
    }

    @Override
    public Long obtenNumeroDePermisos() {
        return permisoRepository.obtenNumeroDePermisos();
    }

}
