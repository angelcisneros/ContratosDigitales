/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.com.quadrum.repository.impl;

import mx.com.quadrum.repository.*;
import java.util.List;
import mx.com.quadrum.entity.Contrato;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
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
public class ContratoRepositoryImpl implements ContratoRepository{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Boolean agregar(Contrato contrato) {
        Boolean guardado = null;
        try{
            sessionFactory.getCurrentSession().save(contrato);
            guardado = true;
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return guardado;
    }

    @Override
    public Boolean editar(Contrato contrato) {
        Boolean actualizado = null;
        try{
            sessionFactory.getCurrentSession().update(contrato);
            actualizado = true;
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return actualizado;
    }

    @Override
    public Boolean eliminar(Contrato contrato) {
        Boolean eliminado = null;
        try{
            sessionFactory.getCurrentSession().delete(contrato);
            eliminado = true;
        }catch(HibernateException he){
            he.printStackTrace();
        }
        return eliminado;
    }

    @Override
    public List<Contrato> buscarTodos() {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .list();
    }

    @Override
    public Contrato buscarPorId(Integer id) {
        return (Contrato) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .add(Restrictions.eq("id", id))
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .uniqueResult();
    }

    @Override
    public List<Contrato> buscarPorUsuario(String mail) {
        return sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.eq("u.mail", mail))
                .list();
    }

    @Override
    public List<Contrato> buscarParaContacto(Integer id) {
        return sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.eq("visibleCliente", Boolean.TRUE))
                .add(Restrictions.eq("c.id", id))
                .list();
    }

    @Override
    public List<Contrato> buscarPorTipoDeContrato(Integer idTipoContrato) {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.eq("t.id", idTipoContrato))
                .list();
    }

    @Override
    public List<Contrato> buscarPorEstado(Integer idEstado) {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.eq("e.id", idEstado))
                .list();
    }

    @Override
    public List<Contrato> buscarPorEmpresa(String nombre) {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.like("ce.razonSocial", nombre, MatchMode.ANYWHERE))
                .list();
    }

    @Override
    public List<Contrato> buscarPorContacto(String nombre) {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.like("c.nombre", nombre, MatchMode.ANYWHERE))
                .list();
    }

    @Override
    public List<Contrato> buscarPorContacto(String nombre, String paterno) {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.like("c.nombre", nombre, MatchMode.ANYWHERE))
                .add(Restrictions.like("c.paterno", paterno, MatchMode.ANYWHERE))
                .list();
    }

    @Override
    public List<Contrato> buscarPorContaco(String nombre, String paterno, String materno) {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.like("c.nombre", nombre, MatchMode.ANYWHERE))
                .add(Restrictions.like("c.paterno", paterno, MatchMode.ANYWHERE))
                .add(Restrictions.like("c.materno", materno, MatchMode.ANYWHERE))
                .list();
    }

    @Override
    public List<Contrato> buscarPorEmpleado(Integer idEmpleado) {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.like("u.id", idEmpleado))
                .list();
    }
/////////////////////////////////////////////////////////////////////////////
    @Override
    public List<Contrato> buscarPorTipoDeContratoEmpleado(Integer idTipoContrato, Integer idEmpleado) {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.eq("t.id", idTipoContrato))
                .add(Restrictions.eq("u.id", idEmpleado))
                .list();
    }

    @Override
    public List<Contrato> buscarParaContactoEmpleado(Integer id, Integer idEmpleado) {
        return sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.eq("visibleCliente", Boolean.TRUE))
                .add(Restrictions.eq("u.id", idEmpleado))
                .add(Restrictions.eq("c.id", id))
                .list();
    }

    @Override
    public List<Contrato> buscarPorEstadoEmpleado(Integer idEstado, Integer idEmpleado) {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.eq("u.id", idEmpleado))
                .add(Restrictions.eq("e.id", idEstado))
                .list();
    }

    @Override
    public List<Contrato> buscarPorEmpresaEmpleado(String nombre, Integer idEmpleado) {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.like("ce.razonSocial", nombre, MatchMode.ANYWHERE))
                .add(Restrictions.eq("u.id", idEmpleado))
                .list();
    }

    @Override
    public List<Contrato> buscarPorContactoEmpleado(String nombre, Integer idEmpleado) {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.like("c.nombre", nombre, MatchMode.ANYWHERE))
                .add(Restrictions.eq("u.id", idEmpleado))
                .list();
    }

    @Override
    public List<Contrato> buscarPorContactoEmpleado(String nombre, String paterno, Integer idEmpleado) {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.like("c.nombre", nombre, MatchMode.ANYWHERE))
                .add(Restrictions.like("c.paterno", paterno, MatchMode.ANYWHERE))
                .add(Restrictions.eq("u.id", idEmpleado))
                .list();
    }

    @Override
    public List<Contrato> buscarPorContacoEmpleado(String nombre, String paterno, String materno, Integer idEmpleado) {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.like("c.nombre", nombre, MatchMode.ANYWHERE))
                .add(Restrictions.like("c.paterno", paterno, MatchMode.ANYWHERE))
                .add(Restrictions.like("c.materno", materno, MatchMode.ANYWHERE))
                .add(Restrictions.eq("u.id", idEmpleado))
                .list();
    }

    @Override
    public List<Contrato> buscarPorTipoDeContratoCliente(Integer idTipoContrato, Integer idCliente) {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.eq("t.id", idTipoContrato))
                .add(Restrictions.eq("visibleCliente", Boolean.TRUE))
                .add(Restrictions.eq("c.id", idCliente))
                .list();
    }

    @Override
    public List<Contrato> buscarPorEstadoCliente(Integer idEstado, Integer idCliente) {
        return (List<Contrato>) sessionFactory.getCurrentSession().createCriteria(Contrato.class)
                .createAlias("contacto", "c", JoinType.INNER_JOIN)
                .createAlias("estatus", "e", JoinType.INNER_JOIN)
                .createAlias("tipoContrato", "t", JoinType.INNER_JOIN)
                .createAlias("usuario", "u", JoinType.INNER_JOIN)
                .createAlias("c.empresa", "ce", JoinType.LEFT_OUTER_JOIN)
                .add(Restrictions.eq("c.id", idCliente))
                .add(Restrictions.eq("e.id", idEstado))
                .add(Restrictions.eq("visibleCliente", Boolean.TRUE))
                .list();
    }

}
