/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository.impl;

import mx.com.quadrum.repository.*;
import java.util.List;
import mx.com.quadrum.entity.TipoContrato;
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
public class TipoContratoRepositoryImpl implements TipoContratoRepository{
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Boolean agregar(TipoContrato tipoContrato) {
        Boolean guardado = null;
        try{
            sessionFactory.getCurrentSession().save(tipoContrato);
            guardado = true;
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return guardado;
    }

    @Override
    public Boolean editar(TipoContrato tipoContrato) {
        Boolean actualizado = null;
        try{
            sessionFactory.getCurrentSession().update(tipoContrato);
            actualizado = true;
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return actualizado;
    }

    @Override
    public Boolean eliminar(TipoContrato tipoContrato) {
        Boolean eliminado = null;
        try{
            sessionFactory.getCurrentSession().delete(tipoContrato);
            eliminado = true;
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return eliminado;
    }

    @Override
    public List<TipoContrato> buscarTodos() {
        return (List<TipoContrato>) sessionFactory.getCurrentSession().createCriteria(TipoContrato.class)
                .list();
    }

    @Override
    public TipoContrato buscarPorId(Integer id) {
        return (TipoContrato) sessionFactory.getCurrentSession().createCriteria(TipoContrato.class)
                .add(Restrictions.eq("id", id));
    }
}
