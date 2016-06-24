/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.ConsultasDAO;
import com.innovaciones.reporte.model.DTO.ReportesDTO;
import java.io.Serializable;
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
@ManagedBean(name = "consultasService")
@ViewScoped
public class ConsultasServiceImpl implements ConsultasService, Serializable {

    @Setter
    private ConsultasDAO consultasDAO;

    @Transactional
    @Override
    public List<ReportesDTO> reportes() {
        return consultasDAO.reportes();
    }

    @Transactional
    @Override
    public List<ReportesDTO> reportesPorRuc(String ruc) {
        return consultasDAO.reportesPorRuc(ruc);
    }

    @Transactional
    @Override
    public ReportesDTO reportesById(Integer id) {
        return consultasDAO.reportesById(id);
    }

    @Override
    @Transactional
    public List<ReportesDTO> reportesInstalaciones() {
        return consultasDAO.reportesInstalaciones();
    }

    @Override
    @Transactional
    public List<ReportesDTO> reportesInstalacionesPorRuc(String ruc) {
        return consultasDAO.reportesInstalacionesPorRuc(ruc);
    }

    @Override
    @Transactional
    public ReportesDTO reportesInstalacionesById(Integer id) {
        return consultasDAO.reportesInstalacionesById(id);
    }

   

}
