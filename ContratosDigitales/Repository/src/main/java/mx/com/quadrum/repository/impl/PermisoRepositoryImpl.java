/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository.impl;

import java.util.List;
import mx.com.quadrum.entity.Permiso;
import mx.com.quadrum.repository.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author vcisneros
 */

@Repository
@Transactional
public class PermisoRepositoryImpl implements PermisoRepository{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Boolean agregar(Permiso permiso) {
        Boolean guardado = null;
        try{
            sessionFactory.getCurrentSession().save(permiso);
            guardado = true;
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return guardado;
    }

    @Override
    public Boolean editar(Permiso permiso) {
        Boolean actualizado = null;
        try{
            sessionFactory.getCurrentSession().update(permiso);
            actualizado = true;
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return actualizado;
    }

    @Override
    public Boolean eliminar(Permiso permiso) {
        Boolean eliminado = null;
        try{
            sessionFactory.getCurrentSession().delete(permiso);
            eliminado = true;
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return eliminado;
    }

    @Override
    public List<Permiso> buscarTodos() {
        return (List<Permiso>) sessionFactory.getCurrentSession().createCriteria(Permiso.class)
                .list();
    }

    @Override
    public Permiso buscarPorId(Integer id) {
        return (Permiso) sessionFactory.getCurrentSession().createCriteria(Permiso.class)
                .add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public List<Permiso> buscarPorUsuario(Integer idUsuario) {
        return sessionFactory.getCurrentSession().createCriteria(Permiso.class)
                .createAlias("permisoUsuarios", "pu", JoinType.INNER_JOIN)
                .add(Restrictions.eq("pu.id.usuario", idUsuario))
                .list();
    }

    @Override
    public Long obtenNumeroDePermisos() {
        return (Long) sessionFactory.getCurrentSession().createCriteria(Permiso.class)
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }
}
