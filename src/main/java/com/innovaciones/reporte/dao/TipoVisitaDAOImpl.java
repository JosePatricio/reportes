/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.TipoVisita;
import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class TipoVisitaDAOImpl implements TipoVisitaDAO, Serializable {

    private SessionFactory sessionFactory;

    @Override
    public List<TipoVisita> getAllTipoVisitas() {
        return sessionFactory.getCurrentSession().createQuery("from TipoVisita t order by t.orden")
                .list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
