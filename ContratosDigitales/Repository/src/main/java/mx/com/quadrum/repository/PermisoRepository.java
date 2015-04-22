/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository;

import java.util.List;
import mx.com.quadrum.entity.Permiso;

/**
 *
 * @author vcisneros
 */
public interface PermisoRepository {
    Boolean agregar(Permiso permiso);
    Boolean editar(Permiso permiso);
    Boolean eliminar(Permiso permiso);
    
    Permiso buscarPorId(Integer id);
    List<Permiso> buscarPorUsuario(Integer idUsuario);
    Long obtenNumeroDePermisos();
    List<Permiso> buscarTodos();
}
