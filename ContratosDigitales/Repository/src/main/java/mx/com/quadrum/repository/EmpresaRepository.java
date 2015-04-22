/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository;

import java.util.List;
import mx.com.quadrum.entity.Empresa;

/**
 *
 * @author vcisneros
 */
public interface EmpresaRepository {
    Boolean agregar(Empresa empresa);
    Boolean editar(Empresa empresa);
    Boolean eliminar(Empresa empresa);
    
    Empresa buscarPorId(Integer id);
    List<Empresa> buscarTodos();
}
