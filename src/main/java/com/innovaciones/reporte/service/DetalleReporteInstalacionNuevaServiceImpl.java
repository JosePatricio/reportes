/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.DetalleReporteInstalacionNuevaDAO;
import com.innovaciones.reporte.model.DetalleReporteInstalacionNueva;
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
@ManagedBean(name = "detalleReporteInstalacionNuevaDAOService")
@ViewScoped
public class DetalleReporteInstalacionNuevaServiceImpl implements DetalleReporteInstalacionNuevaService, Serializable {

    @Setter
    private DetalleReporteInstalacionNuevaDAO detalleReporteInstalacionNuevaDAO;

    @Override
    @Transactional
    public DetalleReporteInstalacionNueva addDetalleReporteInstalacionNueva(DetalleReporteInstalacionNueva detalleReporteInstalacionNueva) {
        System.out.println("A INGRESWAR __ | =  "+detalleReporteInstalacionNueva);
        return detalleReporteInstalacionNuevaDAO.addDetalleReporteInstalacionNueva(detalleReporteInstalacionNueva);
    }

    @Override
    @Transactional
    public DetalleReporteInstalacionNueva updateDetalleReporteInstalacionNueva(DetalleReporteInstalacionNueva detalleReporteInstalacionNueva) {
        return detalleReporteInstalacionNuevaDAO.updateDetalleReporteInstalacionNueva(detalleReporteInstalacionNueva);
    }

}
