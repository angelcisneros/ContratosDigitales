/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository.impl;

import mx.com.quadrum.repository.*;
import java.util.List;
import mx.com.quadrum.entity.PermisoUsuario;
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
public class PermisoUsuarioRepositoryImpl implements PermisoUsuarioRepository{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Boolean agregar(PermisoUsuario permisoUsuario) {
        try{
            sessionFactory.getCurrentSession().save(permisoUsuario);
            return true;
        }catch(HibernateException he){
            he.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean editar(PermisoUsuario permisoUsuario) {
        try{
            sessionFactory.getCurrentSession().update(permisoUsuario);
            return true;
        }catch(HibernateException he){
            he.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean eliminar(PermisoUsuario permisoUsuario) {
        try{
            sessionFactory.getCurrentSession().delete(permisoUsuario);
            return true;
        }catch(HibernateException he){
            he.printStackTrace();
            return false;
        }
    }

    @Override
    public List<PermisoUsuario> buscarTodos() {
        return (List<PermisoUsuario>) sessionFactory.getCurrentSession().createCriteria(PermisoUsuario.class)
                .list();
    }

    @Override
    public PermisoUsuario buscarPorId(Integer idPermiso, Integer idUsuario) {
        return (PermisoUsuario) sessionFactory.getCurrentSession().createCriteria(PermisoUsuario.class)
                .add(Restrictions.eq("id.usuario", idUsuario))
                .add(Restrictions.eq("idpermiso", idPermiso))
                .list();
    }

    @Override
    public Boolean eliminarPermisosDeUsuario(Integer idUsuario) {
        return sessionFactory.getCurrentSession().createQuery("delete PermisoUsuario p where p.id.usuario = " + idUsuario)
                .executeUpdate() > 0;
    }
}
