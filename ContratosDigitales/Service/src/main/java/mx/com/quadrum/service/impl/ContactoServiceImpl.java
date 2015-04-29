/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.service.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import mx.com.quadrum.entity.Contacto;
import mx.com.quadrum.entity.Contrato;
import mx.com.quadrum.repository.ContactoRepository;
import mx.com.quadrum.service.*;
import mx.com.quadrum.service.util.EnviarCorreo;
import static mx.com.quadrum.service.util.GeneradorPassword.generaPassword;
import static mx.com.quadrum.service.util.Llave.MENSAJE;
import static mx.com.quadrum.service.util.MensajesCrud.ADD_CORRECT;
import static mx.com.quadrum.service.util.MensajesCrud.DELETE_CORRECT;
import static mx.com.quadrum.service.util.MensajesCrud.ERROR_HIBERNATE;
import static mx.com.quadrum.service.util.MensajesCrud.UPDATE_CORRECT;
import static mx.com.quadrum.service.util.Rutas.ACCESO_SISTEMA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vcisneros
 */
@Service
public class ContactoServiceImpl implements ContactoService {

    @Autowired
    ContactoRepository contactoRepository;

    private static final String CONTACTO = "un contacto.#";
    

    @Override
    public String agregar(Contacto contacto) {
        contacto.setActivo(Boolean.FALSE);
        contacto.setPrimeraSesion(Boolean.TRUE);
        if(contacto.getGrado() != null){
            if(contacto.getGrado().getId() == 0){
                contacto.setGrado(null);
            }
        }
        if (contactoRepository.agregar(contacto)) {
            return ADD_CORRECT + CONTACTO + contacto.getId();
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String editar(Contacto contacto) {
        Contacto antiguo = contactoRepository.buscarPorId(contacto.getId());
        contacto.setActivo(antiguo.getActivo());
        contacto.setPrimeraSesion(antiguo.getPrimeraSesion());
        if(contacto.getGrado() != null){
            if(contacto.getGrado().getId() == 0){
                contacto.setGrado(null);
            }
        }
        if (contactoRepository.editar(contacto)) {
            return UPDATE_CORRECT + CONTACTO;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String eliminar(Contacto contacto) {
        if (contactoRepository.eliminar(contacto)) {
            return DELETE_CORRECT + CONTACTO;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public Boolean esValid(Contacto contacto) {
        return true;
    }

    @Override
    public List<Contacto> buscarTodos() {
        return contactoRepository.buscarTodos();

    }

    @Override
    public String cambiarEstadoCuenta(Integer id) {
        Contacto contacto = contactoRepository.buscarPorId(id);
        
        if(!contacto.getActivo()){
            if(contacto.getPrimeraSesion()){
                contacto.setPassword(generaPassword());
                
                EnviarCorreo enviarCorreo = new EnviarCorreo();
                try {
                    enviarCorreo.enviaCredenciales(contacto.getMail(), ACCESO_SISTEMA, MENSAJE + "\n\t\tUsuario:" + contacto.getMail() + "\n\t\tPassword:" + contacto.getPassword());
                    contacto.setPrimeraSesion(Boolean.FALSE);
                } catch (MessagingException ex) {
                    Logger.getLogger(ContactoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        contacto.setActivo(!contacto.getActivo());
        if(contactoRepository.editar(contacto)){
            return "Correcto...#Se ha actualizado el estado de la cuenta";
        }
        return "Error...#Se produjo un error al intentar cambia el estado de la cuenta. Intente más tarde";
    }

    @Override
    public Boolean estaRegistrado(String rfc, String password) {
        Contacto contacto = contactoRepository.buscarPorCorreo(rfc);
        if (contacto == null) {
            return false;
        } else {
            return password.equals(contacto.getPassword()) && contacto.getActivo();
        }
    }

    @Override
    public Contacto buscarPorCorreo(String correo) {
        return contactoRepository.buscarPorCorreo(correo);
    }

    @Override
    public Boolean cambiarPassword(Contacto contacto, String password) {
        contacto.setPassword(password);
        contacto.setPrimeraSesion(Boolean.FALSE);
        return contactoRepository.cambiarPassword(contacto);
    }

    @Override
    public Contacto buscarPorId(Integer id) {
        return contactoRepository.buscarPorId(id);
    }


}
