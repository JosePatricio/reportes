/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.ReporteMantenimiento;
import com.innovaciones.reporte.util.Utilities;
import java.util.List;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class ReporteMantenimientoDAOImpl extends Utilities implements ReporteMantenimientoDAO {

    @Setter
    private SessionFactory sessionFactory;

    @Override
    public ReporteMantenimiento saveOrUpdateReporteMantenimiento(ReporteMantenimiento reporteMantenimiento) {
        sessionFactory.getCurrentSession().saveOrUpdate(reporteMantenimiento);
        return reporteMantenimiento;
    }

    @Override
    public List<ReporteMantenimiento> getReporteMantenimientoByReporteId(Integer id) {
        return sessionFactory.getCurrentSession().
                createQuery("from ReporteMantenimiento r WHERE r.idProductoClienteReporte.idReporte.id=" + id + "")
                .list();
    }

    @Override
    public void removeReporteMantenimiento(ReporteMantenimiento reporteMantenimiento) {
        sessionFactory.getCurrentSession().delete(reporteMantenimiento);
    }

    @Override
    public List<ReporteMantenimiento> getReporteMantenimientoByDetalleCatalogoId(Integer id) {
        System.out.println("IDdETALLE " + id);

        return sessionFactory.getCurrentSession().
                createQuery("from ReporteMantenimiento r WHERE r.idDetalleCatalogoReporte.id=" + id + "")
                .list();
    }

}
