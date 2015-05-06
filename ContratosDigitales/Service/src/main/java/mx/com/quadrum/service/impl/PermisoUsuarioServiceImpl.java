/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.service.impl;

import java.util.List;
import mx.com.quadrum.entity.Permiso;
import mx.com.quadrum.service.*;
import mx.com.quadrum.entity.PermisoUsuario;
import mx.com.quadrum.entity.PermisoUsuarioId;
import mx.com.quadrum.entity.Usuario;
import mx.com.quadrum.repository.PermisoUsuarioRepository;
import static mx.com.quadrum.service.util.MensajesCrud.ADD_CORRECT;
import static mx.com.quadrum.service.util.MensajesCrud.DELETE_CORRECT;
import static mx.com.quadrum.service.util.MensajesCrud.ERROR_HIBERNATE;
import static mx.com.quadrum.service.util.MensajesCrud.UPDATE_CORRECT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author vcisneros
 */
@Service
public class PermisoUsuarioServiceImpl implements PermisoUsuarioService {

    @Autowired
    PermisoUsuarioRepository permisoUsuarioRepository;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    PermisoService permisoService;

    private static final String GRADO = "una permisoUsuario.#";

    @Override
    public String agregar(PermisoUsuario permisoUsuario) {
        if (permisoUsuarioRepository.agregar(permisoUsuario)) {
            return ADD_CORRECT + GRADO + permisoUsuario.getId();
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String editar(PermisoUsuario permisoUsuario) {
        if (permisoUsuarioRepository.editar(permisoUsuario)) {
            return UPDATE_CORRECT + GRADO;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public String eliminar(PermisoUsuario permisoUsuario) {
        if (permisoUsuarioRepository.eliminar(permisoUsuario)) {
            return DELETE_CORRECT + GRADO;
        }
        return ERROR_HIBERNATE;
    }

    @Override
    public Boolean esValid(PermisoUsuario permisoUsuario) {
        return true;
    }

    @Override
    public PermisoUsuario buscarPorId(Integer idPermiso, Integer idUsuario) {
        return permisoUsuarioRepository.buscarPorId(idPermiso, idUsuario);
    }

    @Override
    public List<PermisoUsuario> buscarTodos() {
        return permisoUsuarioRepository.buscarTodos();
    }

    @Override
    public Boolean eliminarPermisosDeUsuario(Integer idUsuario) {
        return permisoUsuarioRepository.eliminarPermisosDeUsuario(idUsuario);
    }

    @Override
    public String agregar(String id) {
        String[] nuevosPermisos = id.split(",");
        Usuario usuario = usuarioService.buscarPorId(Integer.parseInt(nuevosPermisos[0]));
        permisoUsuarioRepository.eliminarPermisosDeUsuario(usuario.getId());
        for (int i = 1; i < nuevosPermisos.length; i++) {
            Integer idPermiso = Integer.parseInt(nuevosPermisos[i]);
            Permiso permiso = permisoService.buscarPorId(idPermiso);
            permisoUsuarioRepository.agregar(new PermisoUsuario(new PermisoUsuarioId(idPermiso, usuario.getId()), permiso, usuario));
        }
        if (permisoService.obtenNumeroDePermisos() == nuevosPermisos.length - 1) {
            usuario.setEsAdmin(Boolean.TRUE);
        }
        return usuarioService.editar(usuario);
    }

    @Override
    public String buscarPorUsuario(Integer id) {
        String permisos = "";
        for (Permiso p : permisoService.buscarPorUsuario(id)) {
            permisos = "," + p.getId() + permisos;
        }
        if(!permisos.equalsIgnoreCase("")){
            permisos = permisos.substring(1);
        }
        return permisos;
    }

    

}
