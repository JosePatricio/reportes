/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.NotificacionBean;
import com.innovaciones.reporte.model.DTO.NotificacionDTO;
import com.innovaciones.reporte.service.NotificacionService;
import com.innovaciones.reporte.util.Enums;
import com.innovaciones.reporte.util.Utilities;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Fernando
 */
@ManagedBean(name = "notificacionController")
@ViewScoped
public class NotificacionController extends Utilities implements Serializable {

    /**
     * Creates a new instance of NotificacionController*
     */
    @Getter
    @Setter
    @ManagedProperty("#{notificacionService}")
    private NotificacionService notificacionService;

    @Getter
    @Setter
    @ManagedProperty("#{notificacionBean}")
    private NotificacionBean notificacionBean;

    @Getter
    @Setter
    @ManagedProperty("#{sesionController}")
    private SesionController sesionController;

    public NotificacionController() {
    }

    @PostConstruct
    public void init() {
        cargarNotificacionReportesByEstado();
    }

    public void cargarNotificacionReportesByEstado() {

        notificacionBean.setListNotificaciones(new ArrayList<NotificacionDTO>());

        if (sesionController.getSesionBean().getIsAdminPostVentaRole()) {
            notificacionBean.setListNotificaciones(notificacionService.getNotificacionesByEstadoReporte());

        } else {
            notificacionBean.setListNotificaciones(
                    notificacionService.getNotificacionesByEstadoReporteByIdUsuario(sesionController.getSesionBean().getUsuarios().getId()));
        }
        notificacionBean.setContadorNotificacionReportes(notificacionBean.getListNotificaciones().size());
    }

    public String redireccionarToSeleccionado(NotificacionDTO notificacionDTO) {

        String result = "#";

        setValueInSession("notificacionSeleccionada", notificacionDTO);

        System.out.println("notificacionDTO.getTipoNotificacion(): " + notificacionDTO.getTipoNotificacion());

        if (notificacionDTO.getTipoNotificacion().equals(Enums.REPORTE.getValue())) {
            result = "/paginas/consultas.jsf?faces-redirect=true";
        } else {
            if (notificacionDTO.getTipoNotificacion().equals(Enums.INSTALACION.getValue())) {

                if (notificacionDTO.getTipoReporte().equalsIgnoreCase(Enums.INSTALACION_NUEVA.getValue())) {
                    result = "/paginas/instalaciones/instalacion-nueva.jsf?faces-redirect=true";
                } else {
                    if (notificacionDTO.getTipoReporte().equalsIgnoreCase(Enums.INSTALACION_TEMPORAL.getValue())) {
                        result = "/paginas/instalaciones/instalacion-temporal.jsf?faces-redirect=true";
                    } else {
                        result = "/paginas/reporte.jsf?faces-redirect=true";

                    }
                }
            }
        }

        System.out.println("com.innovaciones.reporte.controller.NotificacionController.redireccionarToSeleccionado(): " + result);
        return result;
    }

}
