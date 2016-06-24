/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.DetalleReporteInstalacionNuevaDAO;
import com.innovaciones.reporte.dao.DetalleReporteTemporalDAO;
import com.innovaciones.reporte.model.DetalleReporteInstalacionNueva;
import com.innovaciones.reporte.model.DetalleReporteTemporal;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pisama
 */
@Service
@ManagedBean(name = "detalleReporteTemporalDAO")
@ViewScoped
public class DetalleReporteTemporalServiceImpl implements DetalleReporteTemporalService, Serializable {

    @Setter
    private DetalleReporteTemporalDAO detalleReporteTemporalDAO;

    @Override
    @Transactional
    public DetalleReporteTemporal addDetalleReporteTemporal(DetalleReporteTemporal detalleReporteTemporal) {
        return detalleReporteTemporalDAO.addDetalleReporteTemporal(detalleReporteTemporal);
    }

    @Override
    @Transactional
    public DetalleReporteTemporal updateDetalleReporteTemporal(DetalleReporteTemporal detalleReporteTemporal) {
        return detalleReporteTemporalDAO.updateDetalleReporteTemporal(detalleReporteTemporal);
    }

}
