/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.model.AsignacionReparacion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fernando
 */
public interface AsignacionReparacionService {

    public AsignacionReparacion addAsignacionReparacion(AsignacionReparacion asignacionReparacion);

    public List<AsignacionReparacion> getAsignacionReparaciones();

    public AsignacionReparacion getAsignacionReparacionById(Integer id);

    public AsignacionReparacion getAsignacionReparacionByIdReporte(Integer id);

    public List<AsignacionReparacion> getAsignacionReparacionesByEstado(String estado);

    public List<AsignacionReparacion> getAsignacionReparacionesNoEliminados();

    public List<AsignacionReparacion> getAsignacionReparacionesByFechaByIdUsuario(Date fecha, Integer idUsuario);

    public List<AsignacionReparacion> getAsignacionReparacionesFiltradasByTecnicosFechas(String listIdTecnicos, Date fechaInicioFiltro, Date fechaFinFiltro);

}
