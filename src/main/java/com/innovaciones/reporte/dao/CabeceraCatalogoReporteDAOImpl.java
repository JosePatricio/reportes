/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.CabeceraCatalogoReporte;
import java.io.Serializable;
import java.util.List;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class CabeceraCatalogoReporteDAOImpl implements CabeceraCatalogoReporteDAO, Serializable {

    @Setter
    private SessionFactory sessionFactory;

    @Override
    public List<CabeceraCatalogoReporte> getCabeceraCatalogoReportesByTipo(String tipo) {
        return sessionFactory.getCurrentSession().createQuery("from CabeceraCatalogoReporte c WHERE c.tipo='" + tipo + "'")
                .list();
    }

  
    @Override
    public List<CabeceraCatalogoReporte> getCabeceraCatalogoReportes() {
        return sessionFactory.getCurrentSession().createQuery("from CabeceraCatalogoReporte")
                .list();
    }

}
