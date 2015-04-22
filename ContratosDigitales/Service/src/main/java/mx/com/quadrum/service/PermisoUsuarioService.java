/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service;

import java.util.List;
import mx.com.quadrum.entity.PermisoUsuario;
import mx.com.quadrum.entity.Usuario;

/**
 *
 * @author vcisneros
 */
public interface PermisoUsuarioService {
    String agregar(PermisoUsuario permisoUsuario);
    String agregar(String permisoUsuario);
    String editar(PermisoUsuario permisoUsuario);
    String eliminar(PermisoUsuario permisoUsuario);
    
    PermisoUsuario buscarPorId(Integer idPermiso, Integer idUsuario);
    List<PermisoUsuario> buscarTodos();
    Boolean eliminarPermisosDeUsuario(Integer idUsuario); 
    
    Boolean esValid(PermisoUsuario permisoUsuario);

    String buscarPorUsuario(Integer parseInt);

    
}
