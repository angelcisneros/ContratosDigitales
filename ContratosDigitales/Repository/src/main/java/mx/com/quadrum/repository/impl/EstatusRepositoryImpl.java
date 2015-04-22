/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository.impl;

import mx.com.quadrum.repository.*;
import java.util.List;
import mx.com.quadrum.entity.Estatus;
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
public class EstatusRepositoryImpl implements EstatusRepository{
     @Autowired
    SessionFactory sessionFactory;

    @Override
    public Boolean agregar(Estatus Estatus) {
        
        try{
            sessionFactory.getCurrentSession().save(Estatus);
            return true;
        }catch(HibernateException he){
            he.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean editar(Estatus Estatus) {
        try{
            sessionFactory.getCurrentSession().update(Estatus);
            return true;
        }catch(HibernateException he){
            he.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean eliminar(Estatus Estatus) {
        try{
            sessionFactory.getCurrentSession().delete(Estatus);
            return true;
        }catch(HibernateException he){
            he.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Estatus> buscarTodos() {
        return (List<Estatus>) sessionFactory.getCurrentSession().createCriteria(Estatus.class)
                .list();
    }

    @Override
    public Estatus buscarPorId(Integer id) {
        return (Estatus) sessionFactory.getCurrentSession().createCriteria(Estatus.class)
                .add(Restrictions.eq("id", id));
    }
}
