/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.DTO.NotificacionDTO;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface NotificacionDAO {

    public List<NotificacionDTO> getNotificacionesByEstadoReporte();
    public List<NotificacionDTO> getNotificacionesByEstadoReporteByIdUsuario(Integer idUsuario);

}
