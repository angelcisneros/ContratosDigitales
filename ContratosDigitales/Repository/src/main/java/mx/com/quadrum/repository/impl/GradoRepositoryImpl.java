/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository.impl;

import mx.com.quadrum.repository.*;
import java.util.List;
import mx.com.quadrum.entity.Grado;
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
public class GradoRepositoryImpl implements GradoRepository{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Boolean agregar(Grado grado) {
        try{
            sessionFactory.getCurrentSession().save(grado);
            return true;
        }catch(HibernateException he){
            he.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean editar(Grado grado) {
        try{
            sessionFactory.getCurrentSession().update(grado);
            return true;
        }catch(HibernateException he){
            he.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean eliminar(Grado grado) {
        try{
            sessionFactory.getCurrentSession().delete(grado);
            return true;
        }catch(HibernateException he){
            he.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Grado> buscarTodos() {
        return (List<Grado>) sessionFactory.getCurrentSession().createCriteria(Grado.class)
                .list();
    }

    @Override
    public Grado buscarPorId(Integer id) {
        return (Grado) sessionFactory.getCurrentSession().createCriteria(Grado.class)
                .add(Restrictions.eq("id", id));
    }
}
