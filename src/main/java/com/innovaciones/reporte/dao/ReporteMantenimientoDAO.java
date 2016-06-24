/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.ReporteMantenimiento;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface ReporteMantenimientoDAO {

    public ReporteMantenimiento saveOrUpdateReporteMantenimiento(ReporteMantenimiento reporteMantenimiento);

    public void removeReporteMantenimiento(ReporteMantenimiento reporteMantenimiento);

    public List<ReporteMantenimiento> getReporteMantenimientoByReporteId(Integer id);

    public List<ReporteMantenimiento> getReporteMantenimientoByDetalleCatalogoId(Integer id);

}
