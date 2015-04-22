/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository;

import java.util.List;
import mx.com.quadrum.entity.PermisoUsuario;

/**
 *
 * @author vcisneros
 */
public interface PermisoUsuarioRepository {
    Boolean agregar(PermisoUsuario permisoUsuario);
    Boolean editar(PermisoUsuario permisoUsuario);
    Boolean eliminar(PermisoUsuario permisoUsuario);
    
    PermisoUsuario buscarPorId(Integer idPermiso, Integer idUsuario);
    Boolean eliminarPermisosDeUsuario(Integer idUsuario);
    
    List<PermisoUsuario> buscarTodos();
}
