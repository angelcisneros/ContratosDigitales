/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.service;

import java.util.List;
import mx.com.quadrum.entity.TipoContrato;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author vcisneros
 */
public interface TipoContratoService {
    String agregar(TipoContrato tipoContrato);
    String agregar(TipoContrato tipoContrato, MultipartFile formato);
    String editar(TipoContrato tipoContrato, MultipartFile formato);
    String eliminar(TipoContrato tipoContrato);
    
    TipoContrato buscarPorId(Integer id);
    List<TipoContrato> buscarTodos();
    Boolean esValid(TipoContrato tipoContrato);
}
