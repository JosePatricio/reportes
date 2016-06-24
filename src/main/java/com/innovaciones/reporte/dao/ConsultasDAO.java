/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;


import com.innovaciones.reporte.model.DTO.ReportesDTO;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface ConsultasDAO {

    public List<ReportesDTO> reportes();

    public List<ReportesDTO> reportesPorRuc(String ruc);

    public ReportesDTO reportesById(Integer id);
    
    public List<ReportesDTO> reportesInstalaciones();

    public List<ReportesDTO> reportesInstalacionesPorRuc(String ruc);

    public ReportesDTO reportesInstalacionesById(Integer id);
    
}
