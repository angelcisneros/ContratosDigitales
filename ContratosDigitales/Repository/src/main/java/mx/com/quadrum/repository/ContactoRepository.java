/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository;

import java.util.List;
import mx.com.quadrum.entity.Contacto;

/**
 *
 * @author vcisneros
 */
public interface ContactoRepository {
    Boolean agregar(Contacto contacto);
    Boolean editar(Contacto contacto);
    Boolean eliminar(Contacto contacto);
    
    Contacto buscarPorId(Integer id);
    List<Contacto> buscarTodos();

    Contacto buscarPorCorreo(String rfc);

    Boolean cambiarPassword(Contacto contacto);
}
