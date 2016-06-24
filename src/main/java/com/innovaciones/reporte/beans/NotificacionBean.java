/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.beans;

import com.innovaciones.reporte.model.DTO.NotificacionDTO;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Fernando
 */
@ManagedBean(name = "notificacionBean")
@ViewScoped
public class NotificacionBean implements Serializable {

    /**
     * Creates a new instance of MarcaBean
     */
    @Getter
    @Setter
    private List<NotificacionDTO> listNotificaciones;

    @Getter
    @Setter
    private NotificacionDTO notificacionSelected;
    
    @Getter
    @Setter
    private Integer contadorNotificacionReportes;

    public NotificacionBean() {

    }
}
