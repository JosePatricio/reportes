/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.model.DetalleCatalogoReporte;
import com.innovaciones.reporte.model.Modelo;
import com.innovaciones.reporte.model.ProductoClienteReporte;
import com.innovaciones.reporte.model.ReporteMantenimiento;
import com.innovaciones.reporte.model.Usuarios;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface ReporteMantenimientoService {

    public ReporteMantenimiento saveOrUpdateReporteMantenimiento(ReporteMantenimiento reporteMantenimiento);

    public void removeReporteMantenimiento(ReporteMantenimiento reporteMantenimiento);

    public void addReporteMantenimientoCorrectivo(ProductoClienteReporte productoClienteReporte,
            List<DetalleCatalogoReporte> suministros,
            List<DetalleCatalogoReporte> imagen,
            List<DetalleCatalogoReporte> fijacion,
            List<DetalleCatalogoReporte> revelado,
            List<DetalleCatalogoReporte> alimentaqcion,
            List<DetalleCatalogoReporte> otros,
            String tipoReparacion, Usuarios usuario);

    public void addReporteMantenimientoPreventivo(ProductoClienteReporte productoClienteReporte,
            List<DetalleCatalogoReporte> procesamiento,
            List<DetalleCatalogoReporte> imagen,
            List<DetalleCatalogoReporte> fijacion,
            List<DetalleCatalogoReporte> limpieza);

    public void addReporteMantenimientoInstalacionNueva(ProductoClienteReporte productoClienteReporte,
            List<DetalleCatalogoReporte> preguntas, Usuarios usuario);

    public void updateReporteMantenimientoInstalacionNueva(ProductoClienteReporte productoClienteReporte,
            List<DetalleCatalogoReporte> preguntas, Usuarios usuario);

    public void updateReporteMantenimientoCorrectivo(ProductoClienteReporte productoClienteReporte,
            List<DetalleCatalogoReporte> suministros,
            List<DetalleCatalogoReporte> imagen,
            List<DetalleCatalogoReporte> fijacion,
            List<DetalleCatalogoReporte> revelado,
            List<DetalleCatalogoReporte> alimentaqcion,
            List<DetalleCatalogoReporte> otros,Modelo modelo, Usuarios usuario);

    public void updateReporteMantenimientoPreventivo(ProductoClienteReporte productoClienteReporte,
            List<DetalleCatalogoReporte> procesamiento,
            List<DetalleCatalogoReporte> imagen,
            List<DetalleCatalogoReporte> fijacion,
            List<DetalleCatalogoReporte> limpieza);

    public List<ReporteMantenimiento> getReporteMantenimientoByReporteId(Integer id);

    public List<ReporteMantenimiento> getReporteMantenimientoByDetalleCatalogoId(Integer id);

}
