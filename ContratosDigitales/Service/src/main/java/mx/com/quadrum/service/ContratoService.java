/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service;

import java.util.List;
import mx.com.quadrum.entity.Contrato;
import mx.com.quadrum.entity.Usuario;
import mx.com.quadrum.service.util.firma.Firma;

/**
 *
 * @author vcisneros
 */
public interface ContratoService {
    
    String agregar(Contrato contrato);
    String editar(Contrato contrato);
    String eliminar(Contrato contrato);
    
    Boolean esValid(Contrato contrato);
    String agregar(Contrato contrato, Usuario usuario, Firma firma);
    String mostrarOcultarCliente(Integer id);
    Boolean firmar(Firma firma);
    
    List<Contrato> buscarTodos();
    List<Contrato> buscarPorUsuario(String mail);
    List<Contrato> buscarPorContacto(Integer id);
    Contrato buscarPorId(Integer id);

}
