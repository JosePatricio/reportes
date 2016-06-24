/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.ReporteDAO;
import com.innovaciones.reporte.model.Reporte;
import com.innovaciones.reporte.model.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pisama
 */
@Service
@ManagedBean(name = "reporteService")
@ViewScoped
public class ReporteServiceImpl implements ReporteService, Serializable {

    private ReporteDAO reporteDAO;

    @Override
    @Transactional
    public Reporte saveReporte(Reporte reporte) {
        return reporteDAO.saveReporte(reporte);
    }

    @Override
    @Transactional
    public Reporte updateReporte(Reporte reporte) {
        return reporteDAO.updateReporte(reporte);
    }

    @Override
    @Transactional
    public Reporte getReporteById(Integer idReporte) {
        return reporteDAO.getReporteById(idReporte);
    }

    @Override
    @Transactional
    public List<Reporte> getReporteByUserByTipo(Usuarios usuario, String tipo, String subtipo) {
        return reporteDAO.getReporteByUserByTipo(usuario, tipo, subtipo);
    }

    public void setReporteDAO(ReporteDAO reporteDAO) {
        this.reporteDAO = reporteDAO;
    }

}
