/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository;

import java.util.List;
import mx.com.quadrum.entity.Estatus;

/**
 *
 * @author vcisneros
 */
public interface EstatusRepository {
    Boolean agregar(Estatus estatus);
    Boolean editar(Estatus estatus);
    Boolean eliminar(Estatus estatus);
    
    Estatus buscarPorId(Integer id);
    List<Estatus> buscarTodos();
}
