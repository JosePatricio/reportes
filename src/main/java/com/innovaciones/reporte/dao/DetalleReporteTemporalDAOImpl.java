/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.DetalleReporteInstalacionNueva;
import com.innovaciones.reporte.model.DetalleReporteTemporal;
import java.io.Serializable;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class DetalleReporteTemporalDAOImpl implements DetalleReporteTemporalDAO, Serializable {

    @Setter
    private SessionFactory sessionFactory;

    @Override
    public DetalleReporteTemporal addDetalleReporteTemporal(DetalleReporteTemporal detalleReporteTemporal) {
        sessionFactory.getCurrentSession().saveOrUpdate(detalleReporteTemporal);
        return detalleReporteTemporal;
    }

    @Override
    public DetalleReporteTemporal updateDetalleReporteTemporal(DetalleReporteTemporal detalleReporteTemporal) {
        sessionFactory.getCurrentSession().update(detalleReporteTemporal);
        return detalleReporteTemporal;
    }

}
