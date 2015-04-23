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
import mx.com.quadrum.entity.Usuario;
import mx.com.quadrum.repository.UsuarioRepository;
import mx.com.quadrum.service.*;
import static mx.com.quadrum.service.util.CarpetaArchivos.aditarCarpetaArchivosUsuarios;
import static mx.com.quadrum.service.util.CarpetaArchivos.crearCarpetaArchivosUsuarios;
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
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PermisoUsuarioService permisoUsuarioService;
    
    private static final String USUARIO = "una usuario.#";
    

    @Override
    public String agregar(Usuario usuario) {
        usuario.setPrimeraSesion(true);
        usuario.setPassword(generaPassword());
        usuario.setEsAdmin(Boolean.FALSE);
        if (usuarioRepository.agregar(usuario)) {
            try {
                EnviarCorreo enviarCorreo = new EnviarCorreo();
                enviarCorreo.enviaCredenciales(usuario.getMail(), ACCESO_SISTEMA, MENSAJE + "\n\t\tUsuario:" + usuario.getMail() + "\n\t\tPassword:" + usuario.getPassword());
                crearCarpetaArchivosUsuarios(usuario.getMail());
                return ADD_CORRECT + USUARIO + usuario.getId();
            } catch (MessagingException ex) {
                Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String editar(Usuario usuario) {
        Usuario antiguo = usuarioRepository.buscarPorId(usuario.getId());
        String mail = antiguo.getMail();
        antiguo.setNombres(usuario.getNombres());
        antiguo.setPaterno(usuario.getPaterno());
        antiguo.setMaterno(usuario.getMaterno());
        if(!mail.equalsIgnoreCase(usuario.getMail())){
            antiguo.setMail(usuario.getMail());
            aditarCarpetaArchivosUsuarios(mail, usuario.getMail());
        }
        
        antiguo.setEstado(usuario.getEstado());
        if (usuarioRepository.editar(antiguo)) {
            return UPDATE_CORRECT + USUARIO;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String eliminar(Usuario usuario) {
        permisoUsuarioService.eliminarPermisosDeUsuario(usuario.getId());
        if (usuarioRepository.eliminar(usuario)) {
            return DELETE_CORRECT + USUARIO;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public Boolean estaRegistrado(String correo, String password) {
        Usuario usuario = usuarioRepository.buscarPorCorreo(correo);
        if (usuario == null) {
            return false;
        } else {
            return password.equals(usuario.getPassword()) && usuario.getEstado();
        }
    }

    @Override
    public Boolean esValid(Usuario usuario) {
        return true;
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.buscarPorId(id);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.buscarTodos();
    }

    @Override
    public Usuario buscarPorCorreo(String correo) {
        return usuarioRepository.buscarPorCorreo(correo);
    }

    @Override
    public Boolean cambiarPassword(Usuario usuario, String password) {
        usuario.setPassword(password);
        usuario.setPrimeraSesion(Boolean.FALSE);
        return usuarioRepository.cambiarPassword(usuario);
    }

    @Override
    public Boolean tienePermiso(Usuario u, String nombre) {
        return usuarioRepository.buscarPermisoPorUsuario(u.getId(), nombre) != null;
    }

}
