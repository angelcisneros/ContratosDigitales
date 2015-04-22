/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository.impl;

import java.util.List;
import mx.com.quadrum.entity.Permiso;
import mx.com.quadrum.entity.Usuario;
import mx.com.quadrum.repository.*;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vcisneros
 */

@Repository
@Transactional
public class UsuarioRepositoryImpl implements UsuarioRepository{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Boolean agregar(Usuario usuario) {
        Boolean guardado = null;
        try{
            sessionFactory.getCurrentSession().save(usuario);
            guardado = true;
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return guardado;
    }

    @Override
    public Boolean editar(Usuario usuario) {
        Boolean actualizado = null;
        try{
            sessionFactory.getCurrentSession().update(usuario);
            actualizado = true;
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return actualizado;
    }

    @Override
    public Boolean eliminar(Usuario usuario) {
        Boolean eliminado = null;
        try{
            sessionFactory.getCurrentSession().delete(usuario);
            eliminado = true;
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return eliminado;
    }

    @Override
    public List<Usuario> buscarTodos() {
        return (List<Usuario>) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .list();
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public Usuario buscarPorCorreo(String correo) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("mail", correo)).uniqueResult();
    }

    @Override
    public Usuario buscarConPermisos(String correo) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("mail", correo))
                .setFetchMode("permisos", FetchMode.JOIN)
                .uniqueResult();
    }

    @Override
    public Boolean cambiarPassword(Usuario usuario) {
        return editar(usuario);
    }
}
