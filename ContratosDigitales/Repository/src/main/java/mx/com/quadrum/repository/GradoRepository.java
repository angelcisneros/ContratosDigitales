/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository;

import java.util.List;
import mx.com.quadrum.entity.Grado;

/**
 *
 * @author vcisneros
 */
public interface GradoRepository {
    Boolean agregar(Grado grado);
    Boolean editar(Grado grado);
    Boolean eliminar(Grado grado);
    
    Grado buscarPorId(Integer id);
    List<Grado> buscarTodos();
}
