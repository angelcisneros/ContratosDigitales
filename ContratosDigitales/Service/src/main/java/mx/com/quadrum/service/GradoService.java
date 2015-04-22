/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service;

import java.util.List;
import mx.com.quadrum.entity.Grado;

/**
 *
 * @author vcisneros
 */
public interface GradoService {
    String agregar(Grado grado);
    String editar(Grado grado);
    String eliminar(Grado grado);
    
    Grado buscarPorId(Integer id);
    List<Grado> buscarTodos();
    Boolean esValid(Grado grado);
}
