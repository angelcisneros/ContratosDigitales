/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service;

import java.util.List;
import mx.com.quadrum.entity.Usuario;

/**
 *
 * @author vcisneros
 */
public interface UsuarioService {
    String agregar(Usuario usuario);
    String editar(Usuario usuario);
    String eliminar(Usuario usuario);
    
    Usuario buscarPorId(Integer id);
    Usuario buscarPorCorreo(String correo);
    List<Usuario> buscarTodos();
    Boolean esValid(Usuario usuario);
    Boolean estaRegistrado(String mail, String password);
    Boolean cambiarPassword(Usuario usuario, String password);
    Boolean tienePermiso(Usuario u, String nombre);
}
