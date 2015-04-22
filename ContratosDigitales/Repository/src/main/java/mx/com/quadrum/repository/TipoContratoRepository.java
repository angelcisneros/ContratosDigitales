/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository;

import java.util.List;
import mx.com.quadrum.entity.TipoContrato;

/**
 *
 * @author vcisneros
 */
public interface TipoContratoRepository {
    Boolean agregar(TipoContrato tipoContrato);
    Boolean editar(TipoContrato tipoContrato);
    Boolean eliminar(TipoContrato tipoContrato);
    
    TipoContrato buscarPorId(Integer id);
    List<TipoContrato> buscarTodos();
}
