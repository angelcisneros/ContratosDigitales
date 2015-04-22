/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository.impl;

import mx.com.quadrum.repository.*;
import java.util.List;
import mx.com.quadrum.entity.Empresa;
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
public class EmpresaRepositoryImpl implements EmpresaRepository{
     @Autowired
    SessionFactory sessionFactory;

    @Override
    public Boolean agregar(Empresa empresa) {
        Boolean guardado = null;
        try{
            sessionFactory.getCurrentSession().save(empresa);
            guardado = true;
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return guardado;
    }

    @Override
    public Boolean editar(Empresa empresa) {
        Boolean actualizado = null;
        try{
            sessionFactory.getCurrentSession().update(empresa);
            actualizado = true;
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return actualizado;
    }

    @Override
    public Boolean eliminar(Empresa empresa) {
        Boolean eliminado = null;
        try{
            sessionFactory.getCurrentSession().delete(empresa);
            eliminado = true;
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return eliminado;
    }

    @Override
    public List<Empresa> buscarTodos() {
        return (List<Empresa>) sessionFactory.getCurrentSession().createCriteria(Empresa.class)
                .list();
    }

    @Override
    public Empresa buscarPorId(Integer id) {
        return (Empresa) sessionFactory.getCurrentSession().createCriteria(Empresa.class)
                .add(Restrictions.eq("id", id));
    }
}
