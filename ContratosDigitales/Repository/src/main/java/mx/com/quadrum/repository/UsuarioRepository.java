/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository;

import java.util.List;
import mx.com.quadrum.entity.PermisoUsuario;
import mx.com.quadrum.entity.Usuario;

/**
 *
 * @author vcisneros
 */
public interface UsuarioRepository {
    Boolean agregar(Usuario usuario);
    Boolean editar(Usuario usuario);
    Boolean eliminar(Usuario usuario);
    
    Usuario buscarPorId(Integer id);
    Usuario buscarPorCorreo(String correo);
    Usuario buscarConPermisos(String correo);
    
//    List<Permiso> buscarPermisosPorUsuario(Integer idUsuario);
    
    List<Usuario> buscarTodos();

    Boolean cambiarPassword(Usuario usuario);
    PermisoUsuario buscarPermisoPorUsuario(Integer id, String nombre);
}



