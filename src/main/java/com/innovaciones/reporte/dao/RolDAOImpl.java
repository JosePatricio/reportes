/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.Rol;
import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class RolDAOImpl implements RolDAO, Serializable {

    private SessionFactory sessionFactory;

    @Override
    public List<Rol> getRoles() {
        return sessionFactory.getCurrentSession().createQuery("from Rol")
                .list();
    }

    @Override
    public List<Rol> getRolesByestado(Integer estado) {
        return sessionFactory.getCurrentSession().createQuery("from Rol WHERE estado = " + estado+ " ORDER BY descripcion")
                .list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
