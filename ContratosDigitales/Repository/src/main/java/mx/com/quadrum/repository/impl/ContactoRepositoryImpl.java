/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.quadrum.repository.impl;

import mx.com.quadrum.repository.*;
import java.util.List;
import mx.com.quadrum.entity.Contacto;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
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
public class ContactoRepositoryImpl implements ContactoRepository {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Boolean agregar(Contacto contacto) {
        Boolean guardado = null;
        try {
            sessionFactory.getCurrentSession().save(contacto);
            guardado = true;
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return guardado;
    }

    @Override
    public Boolean editar(Contacto contacto) {
        Boolean actualizado = null;
        try {
            sessionFactory.getCurrentSession().update(contacto);
            actualizado = true;
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return actualizado;
    }

    @Override
    public Boolean eliminar(Contacto contacto) {
        Boolean eliminado = null;
        try {
            sessionFactory.getCurrentSession().delete(contacto);
            eliminado = true;
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return eliminado;
    }

    @Override
    public List<Contacto> buscarTodos() {
        return (List<Contacto>) sessionFactory.getCurrentSession().createCriteria(Contacto.class)
                .createAlias("empresa", "e", JoinType.LEFT_OUTER_JOIN)
                .createAlias("grado", "g", JoinType.LEFT_OUTER_JOIN)
                .list();
    }

    @Override
    public Contacto buscarPorId(Integer id) {
        return (Contacto) sessionFactory.getCurrentSession().createCriteria(Contacto.class)
                .createAlias("empresa", "e", JoinType.LEFT_OUTER_JOIN)
                .createAlias("grado", "g", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public Contacto buscarPorCorreo(String correo) {
        return (Contacto) sessionFactory.getCurrentSession().createCriteria(Contacto.class)
                .add(Restrictions.eq("mail", correo))
                .uniqueResult();
    }

    @Override
    public Boolean cambiarPassword(Contacto contacto) {
        return editar(contacto);
    }

}
