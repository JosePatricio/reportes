/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.CabeceraCatalogoReporte;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface CabeceraCatalogoReporteDAO {

    public List<CabeceraCatalogoReporte> getCabeceraCatalogoReportesByTipo(String tipo);

    public List<CabeceraCatalogoReporte> getCabeceraCatalogoReportes();


}
