/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service;

import java.util.List;
import mx.com.quadrum.entity.Contrato;

/**
 *
 * @author vcisneros
 */
public interface BusquedasContratos {
    
    Contrato buscarPorId(Integer id);
    
    List<Contrato> buscarTodos();
    List<Contrato> buscarPorUsuario(String mail);
    List<Contrato> buscarPorContacto(Integer id);
    List<Contrato> buscarPorTipoDeContrato(Integer idTipoContrato);
    List<Contrato> buscarPorEstado(Integer idEstado);
    List<Contrato> buscarPorEmpresa(String nombre);
    List<Contrato> buscarPorContacto(String nombre);
    List<Contrato> buscarPorContacto(String nombre, String paterno);
    List<Contrato> buscarPorContaco(String nombre, String paterno, String materno);
    List<Contrato> buscarPorEmpleado(Integer idEmpleado);
  
    List<Contrato> buscarPorTipoDeContratoEmpleado(Integer idTipoContrato, Integer idEmpleado);
    List<Contrato> buscarParaContactoEmpleado(Integer id, Integer idEmpleado);
    List<Contrato> buscarPorEstadoEmpleado(Integer idEstado, Integer idEmpleado);
    List<Contrato> buscarPorEmpresaEmpleado(String nombre, Integer idEmpleado);
    List<Contrato> buscarPorContactoEmpleado(String nombre, Integer idEmpleado);
    List<Contrato> buscarPorContactoEmpleado(String nombre, String paterno, Integer idEmpleado);
    List<Contrato> buscarPorContacoEmpleado(String nombre, String paterno, String materno, Integer idEmpleado);
    
    List<Contrato> buscarPorTipoDeContratoCliente(Integer idTipoContrato, Integer idCliente);
    List<Contrato> buscarPorEstadoCliente(Integer idEstado, Integer idCliente);
}