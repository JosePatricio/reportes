/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.model.DetalleCatalogoReporte;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface DetalleCatalogoReporteService {

    public List<DetalleCatalogoReporte> getDetalleCatalogoReporteByCabeceraCodigo(String codigo);

    public List<DetalleCatalogoReporte> getDetalleCatalogoReporte();

    public DetalleCatalogoReporte getDetalleCatalogoReporteById(Integer id);

    public List<DetalleCatalogoReporte> getDetalleCatalogoReporteByEstado(Integer estado);

    public List<DetalleCatalogoReporte> getDetalleCatalogoReporteByEstadoNotCodigoNull();

    public List<DetalleCatalogoReporte> getDetalleCatalogoReporteByIdCabeceraByDescripcion(Integer idCabecera, String descripcion);

    public DetalleCatalogoReporte saveOrUpdateDetalleCatalogoReporte(DetalleCatalogoReporte detalleCatalogoReporte);

    public void removeDetalleCatalogoReporte(DetalleCatalogoReporte detalleCatalogoReporte);

    public DetalleCatalogoReporte getDetalleCatalogoReporteByDescripcionByIdCabecera(String descripcion, Integer idCabecera);

    public DetalleCatalogoReporte update(DetalleCatalogoReporte detalleCatalogoReporte);
    
    

}
