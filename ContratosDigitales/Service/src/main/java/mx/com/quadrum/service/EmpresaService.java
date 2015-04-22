/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service;

import java.util.List;
import mx.com.quadrum.entity.Empresa;

/**
 *
 * @author vcisneros
 */
public interface EmpresaService {
    String agregar(Empresa empresa);
    String editar(Empresa empresa);
    String eliminar(Empresa empresa);
    
    Empresa buscarPorId(Integer id);
    List<Empresa> buscarTodos();
    Boolean esValid(Empresa empresa);
}
