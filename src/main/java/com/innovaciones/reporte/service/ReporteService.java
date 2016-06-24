/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.model.Reporte;
import com.innovaciones.reporte.model.Usuarios;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface ReporteService {

    public Reporte saveReporte(Reporte reporte);

    public Reporte updateReporte(Reporte reporte);

    public Reporte getReporteById(Integer idReporte);

    public List<Reporte> getReporteByUserByTipo(Usuarios usuario, String tipo, String subtipo);
}
