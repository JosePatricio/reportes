/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.DetalleReporteInstalacionNueva;
import java.io.Serializable;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class DetalleReporteInstalacionNuevaDAOImpl implements DetalleReporteInstalacionNuevaDAO, Serializable {

    @Setter
    private SessionFactory sessionFactory;

    @Override
    public DetalleReporteInstalacionNueva addDetalleReporteInstalacionNueva(DetalleReporteInstalacionNueva detalleReporteInstalacionNueva) {

        sessionFactory.getCurrentSession().saveOrUpdate(detalleReporteInstalacionNueva);
        return detalleReporteInstalacionNueva;
    }

    @Override
    public DetalleReporteInstalacionNueva updateDetalleReporteInstalacionNueva(DetalleReporteInstalacionNueva detalleReporteInstalacionNueva) {

        sessionFactory.getCurrentSession().update(detalleReporteInstalacionNueva);
        return detalleReporteInstalacionNueva;
    }

}
