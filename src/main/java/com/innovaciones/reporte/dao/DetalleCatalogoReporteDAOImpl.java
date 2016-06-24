/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.DetalleCatalogoReporte;
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
public class DetalleCatalogoReporteDAOImpl implements DetalleCatalogoReporteDAO, Serializable {

    @Setter
    private SessionFactory sessionFactory;

    @Override
    public List<DetalleCatalogoReporte> getDetalleCatalogoReporteByCabeceraCodigo(String codigo) {
        return sessionFactory.getCurrentSession().
                createQuery("from DetalleCatalogoReporte d WHERE d.idCabecera.codigo='" + codigo + "' ORDER BY orden")
                .list();
    }

    @Override
    public List<DetalleCatalogoReporte> getDetalleCatalogoReporte() {
        return sessionFactory.getCurrentSession().createQuery("from DetalleCatalogoReporte ORDER BY idCabecera,orden")
                .list();
    }

    @Override
    public List<DetalleCatalogoReporte> getDetalleCatalogoReporteByEstado(Integer estado) {
        return sessionFactory.getCurrentSession().createQuery("from DetalleCatalogoReporte d WHERE d.estado=" + (estado == 1) + " ORDER BY orden")
                .list();
    }

    @Override
    public DetalleCatalogoReporte saveOrUpdateDetalleCatalogoReporte(DetalleCatalogoReporte detalleCatalogoReporte) {
        sessionFactory.getCurrentSession().saveOrUpdate(detalleCatalogoReporte);
        return detalleCatalogoReporte;
    }

    @Override
    public DetalleCatalogoReporte update(DetalleCatalogoReporte detalleCatalogoReporte) {
        sessionFactory.getCurrentSession().update(detalleCatalogoReporte);
        return detalleCatalogoReporte;
    }

    @Override
    public void removeDetalleCatalogoReporte(DetalleCatalogoReporte detalleCatalogoReporte) {
        sessionFactory.getCurrentSession().delete(detalleCatalogoReporte);
    }

    @Override
    public List<DetalleCatalogoReporte> getDetalleCatalogoReporteByIdCabeceraByDescripcion(Integer idCabecera, String descripcion) {
        return sessionFactory.getCurrentSession().createQuery("from DetalleCatalogoReporte d WHERE d.idCabecera.id=" + idCabecera + " AND d.descripcion='" + descripcion + "'")
                .list();
    }

    @Override
    public DetalleCatalogoReporte getDetalleCatalogoReporteById(Integer id) {
        DetalleCatalogoReporte detalleCatalogoReporte = (DetalleCatalogoReporte) sessionFactory.getCurrentSession().createQuery("from DetalleCatalogoReporte d WHERE d.id=" + id).uniqueResult();
        return detalleCatalogoReporte != null ? detalleCatalogoReporte : null;
    }

    @Override
    public DetalleCatalogoReporte getDetalleCatalogoReporteByDescripcionByIdCabecera(String descripcion, Integer idCabecera) {
        DetalleCatalogoReporte detalleCatalogoReporte = (DetalleCatalogoReporte) sessionFactory.getCurrentSession().createQuery("from DetalleCatalogoReporte d WHERE d.descripcion='" + descripcion + "' AND d.idCabecera.id=" + idCabecera).uniqueResult();
        return detalleCatalogoReporte != null ? detalleCatalogoReporte : null;
    }

}
