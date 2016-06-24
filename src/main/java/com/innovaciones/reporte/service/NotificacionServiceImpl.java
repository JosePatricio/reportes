/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.NotificacionDAO;
import com.innovaciones.reporte.model.DTO.NotificacionDTO;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fernando
 */
@Service
@ManagedBean(name = "notificacionService")
@ViewScoped
public class NotificacionServiceImpl implements NotificacionService, Serializable {

    private NotificacionDAO notificacionDAO;

    @Override
    @Transactional
    public List<NotificacionDTO> getNotificacionesByEstadoReporte() {
        return notificacionDAO.getNotificacionesByEstadoReporte();
    }

    @Override
    @Transactional
    public List<NotificacionDTO> getNotificacionesByEstadoReporteByIdUsuario(Integer idUsuario) {
        return notificacionDAO.getNotificacionesByEstadoReporteByIdUsuario(idUsuario);
    }

    public NotificacionDAO getNotificacionDAO() {
        return notificacionDAO;
    }

    public void setNotificacionDAO(NotificacionDAO notificacionDAO) {
        this.notificacionDAO = notificacionDAO;
    }

}
