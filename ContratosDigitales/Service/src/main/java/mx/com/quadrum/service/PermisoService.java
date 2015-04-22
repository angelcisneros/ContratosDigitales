/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service;

import java.util.List;
import mx.com.quadrum.entity.Permiso;

/**
 *
 * @author vcisneros
 */
public interface PermisoService {
    String agregar(Permiso permiso);
    String editar(Permiso permiso);
    String eliminar(Permiso permiso);
    
    Permiso buscarPorId(Integer id);
    List<Permiso> buscarTodos();
    List<Permiso> buscarPorUsuario(Integer idUsuario);
    Long obtenNumeroDePermisos();
    Boolean esValid(Permiso permiso);
}
