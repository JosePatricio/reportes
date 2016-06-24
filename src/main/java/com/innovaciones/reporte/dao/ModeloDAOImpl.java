/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.Modelo;
import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class ModeloDAOImpl implements ModeloDAO, Serializable {

    private SessionFactory sessionFactory;

    @Override
    public Modelo addModelo(Modelo modelo) {
        sessionFactory.getCurrentSession().saveOrUpdate(modelo);
        return modelo;
    }

    @Override
    public List<Modelo> getModelos() {
        return sessionFactory.getCurrentSession().createQuery("from Modelo AS m Order By m.idMarca.marca, m.modelo")
                .list();
    }

    @Override
    public Modelo getModeloById(Integer id) {
        Modelo modelo = (Modelo) sessionFactory.getCurrentSession().createQuery("from Modelo WHERE id=" + id + "").uniqueResult();
        return modelo != null ? modelo : null;
    }

    @Override
    public Modelo getModeloByModelo(String modelo_) {
        Modelo modelo = (Modelo) sessionFactory.getCurrentSession().createQuery("from Modelo WHERE modelo='" + modelo_ + "'").uniqueResult();
        return modelo != null ? modelo : null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Modelo> getModelosByIdMarca(Integer id) {
        return sessionFactory.getCurrentSession().createQuery("from Modelo WHERE id_marca=" + id + "").list();
    }

    @Override
    public Modelo saveModelo(Modelo modelo) {
        sessionFactory.getCurrentSession().save(modelo);
        return modelo;
    }

}
