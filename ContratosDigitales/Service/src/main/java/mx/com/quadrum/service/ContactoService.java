/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service;

import java.util.List;
import mx.com.quadrum.entity.Contacto;

/**
 *
 * @author vcisneros
 */
public interface ContactoService {
    String agregar(Contacto contacto);
    String editar(Contacto contacto);
    String eliminar(Contacto contacto);
    
    Contacto buscarPorId(Integer id);
    List<Contacto> buscarTodos();
    Boolean esValid(Contacto contacto);

    String cambiarEstadoCuenta(Integer id);

    Boolean estaRegistrado(String rfc, String password);

    Contacto buscarPorCorreo(String rfc);

    Boolean cambiarPassword(Contacto contacto, String password);

    
}
