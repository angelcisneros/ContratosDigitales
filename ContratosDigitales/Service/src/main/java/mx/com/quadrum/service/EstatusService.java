/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service;

import java.util.List;
import mx.com.quadrum.entity.Estatus;

/**
 *
 * @author vcisneros
 */
public interface EstatusService {
    String agregar(Estatus estatus);
    String editar(Estatus estatus);
    String eliminar(Estatus estatus);
    
    Estatus buscarPorId(Integer id);
    List<Estatus> buscarTodos();
    Boolean esValid(Estatus estatus);
}
