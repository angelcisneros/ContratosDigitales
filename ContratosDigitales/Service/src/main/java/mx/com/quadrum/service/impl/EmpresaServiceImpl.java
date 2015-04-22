/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service.impl;

import java.util.List;
import mx.com.quadrum.entity.Empresa;
import mx.com.quadrum.service.*;
import mx.com.quadrum.entity.Empresa;
import mx.com.quadrum.repository.EmpresaRepository;
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
public class EmpresaServiceImpl implements  EmpresaService{
  @Autowired
    EmpresaRepository empresaRepository;

     private static final String EMPRESA = "una empresa.#";
    
    @Override
    public String agregar(Empresa empresa) {
        if(empresaRepository.agregar(empresa)){
            return ADD_CORRECT + EMPRESA + empresa.getId();
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String editar(Empresa empresa) {
        if(empresaRepository.editar(empresa)){
            return UPDATE_CORRECT + EMPRESA;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String eliminar(Empresa empresa) {
        if(empresaRepository.eliminar(empresa)){
            return DELETE_CORRECT + EMPRESA;
        }
        return ERROR_HIBERNATE;
    }


    @Override
    public Boolean esValid(Empresa empresa) {
        return true;
    }

    @Override
    public Empresa buscarPorId(Integer id) {
        return empresaRepository.buscarPorId(id);
    }

    @Override
    public List<Empresa> buscarTodos() {
        return empresaRepository.buscarTodos();
    }

}
