/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.DetalleCatalogoReporteDAO;
import com.innovaciones.reporte.model.DetalleCatalogoReporte;
import com.innovaciones.reporte.util.Utilities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@ManagedBean(name = "detalleCatalogoReporteService")
@ViewScoped
public class DetalleCatalogoReporteServiceImpl extends Utilities implements DetalleCatalogoReporteService, Serializable {

    @Setter
    private DetalleCatalogoReporteDAO detalleCatalogoReporteDAO;

    @Override
    @Transactional
    public List<DetalleCatalogoReporte> getDetalleCatalogoReporte() {
        return detalleCatalogoReporteDAO.getDetalleCatalogoReporte();
    }

    @Override
    @Transactional
    public List<DetalleCatalogoReporte> getDetalleCatalogoReporteByCabeceraCodigo(String codigo) {
        List<DetalleCatalogoReporte> list = detalleCatalogoReporteDAO.getDetalleCatalogoReporteByCabeceraCodigo(codigo);
        List<DetalleCatalogoReporte> listRes = new ArrayList<DetalleCatalogoReporte>();
        DetalleCatalogoReporte reporte;
        for (DetalleCatalogoReporte detalleCatalogoReporte : list) {
            reporte = new DetalleCatalogoReporte();
            reporte = detalleCatalogoReporte;
            reporte.setCodigoRepuesto(null);
            listRes.add(reporte);
        }
        return listRes;
    }

    @Override
    @Transactional
    public List<DetalleCatalogoReporte> getDetalleCatalogoReporteByEstado(Integer estado) {
        return detalleCatalogoReporteDAO.getDetalleCatalogoReporteByEstado(estado);
    }

    @Override
    @Transactional
    public List<DetalleCatalogoReporte> getDetalleCatalogoReporteByEstadoNotCodigoNull() {
        List<DetalleCatalogoReporte> list = detalleCatalogoReporteDAO.getDetalleCatalogoReporteByEstado(1);
        List<DetalleCatalogoReporte> listRes = new ArrayList<DetalleCatalogoReporte>();
        for (DetalleCatalogoReporte detalleCatalogoReporte : list) {
            if (detalleCatalogoReporte.getCodigoRepuesto() != null) {
                listRes.add(detalleCatalogoReporte);
            }
        }
        return listRes;
    }

    @Transactional
    public DetalleCatalogoReporte saveOrUpdateDetalleCatalogoReporte(DetalleCatalogoReporte detalleCatalogoReporte) {
        return detalleCatalogoReporteDAO.saveOrUpdateDetalleCatalogoReporte(detalleCatalogoReporte);
    }

    @Override
    @Transactional
    public void removeDetalleCatalogoReporte(DetalleCatalogoReporte detalleCatalogoReporte) {
        detalleCatalogoReporteDAO.removeDetalleCatalogoReporte(detalleCatalogoReporte);
    }

    @Override
    @Transactional
    public List<DetalleCatalogoReporte> getDetalleCatalogoReporteByIdCabeceraByDescripcion(Integer idCabecera, String descripcion) {
        return detalleCatalogoReporteDAO.getDetalleCatalogoReporteByIdCabeceraByDescripcion(idCabecera, descripcion);
    }

    @Override
    @Transactional
    public DetalleCatalogoReporte getDetalleCatalogoReporteById(Integer id) {
        return detalleCatalogoReporteDAO.getDetalleCatalogoReporteById(id);
    }

    @Override
    @Transactional
    public DetalleCatalogoReporte update(DetalleCatalogoReporte detalleCatalogoReporte) {
        return detalleCatalogoReporteDAO.update(detalleCatalogoReporte);
    }

    @Override
    public DetalleCatalogoReporte getDetalleCatalogoReporteByDescripcionByIdCabecera(String descripcion, Integer idCabecera) {
        return detalleCatalogoReporteDAO.getDetalleCatalogoReporteByDescripcionByIdCabecera(descripcion, idCabecera);
    }

}
