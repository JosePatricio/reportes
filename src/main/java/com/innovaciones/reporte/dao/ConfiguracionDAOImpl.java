/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.Configuracion;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fernando
 */
@Repository
public class ConfiguracionDAOImpl implements ConfiguracionDAO, Serializable {

    @Getter
    @Setter
    private SessionFactory sessionFactory;

    @Override
    public Configuracion addConfiguracion(Configuracion configuracion) {
        sessionFactory.getCurrentSession().saveOrUpdate(configuracion);
        return configuracion;
    }

    @Override
    public List<Configuracion> getConfiguraciones() {
        List<Configuracion> configuraciones = sessionFactory.getCurrentSession().createQuery("from Configuracion").list();
        return configuraciones;
    }

    @Override
    public Configuracion getConfiguracionById(Integer id) {
        List<Configuracion> configuraciones = sessionFactory.getCurrentSession().createQuery("from Configuracion c Where c.id = " + id)
                .list();
        return configuraciones != null && !configuraciones.isEmpty() ? configuraciones.get(0) : null;
    }

    @Override
    public Configuracion getConfiguracionById(String id) {
        List<Configuracion> configuraciones = sessionFactory.getCurrentSession().createQuery("from Configuracion c Where c.id = '" + id + "'")
                .list();
        return configuraciones != null && !configuraciones.isEmpty() ? configuraciones.get(0) : null;
    }

    @Override
    public Configuracion getConfiguracionByRuc(String ruc) {
        List<Configuracion> configuraciones = sessionFactory.getCurrentSession().createQuery("from Configuracion c Where c.ruc = '" + ruc + "'")
                .list();
        return configuraciones != null && !configuraciones.isEmpty() ? configuraciones.get(0) : null;
    }

    @Override
    public Configuracion getConfiguracionByCodigo(String codigo) {
        List<Configuracion> configuraciones = sessionFactory.getCurrentSession().createQuery("from Configuracion c Where c.codigo = '" + codigo + "'")
                .list();
        return configuraciones != null && !configuraciones.isEmpty() ? configuraciones.get(0) : null;
    }

    @Override
    public List<Configuracion> getConfiguracionsByEstado(Integer estado) {
        List<Configuracion> configuraciones = sessionFactory.getCurrentSession().createQuery("from Configuracion c Where c.estado = " + estado + " Order by c.nombreEmpresa").list();
        return configuraciones;
    }

}
