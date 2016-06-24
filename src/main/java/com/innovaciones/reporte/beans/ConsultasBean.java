/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.beans;

import com.innovaciones.reporte.model.AsignacionReparacion;
import com.innovaciones.reporte.model.DTO.NotificacionDTO;
import com.innovaciones.reporte.model.DTO.ReportesDTO;
import com.innovaciones.reporte.util.Enums;
import com.innovaciones.reporte.util.Utilities;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pisama
 */
@ManagedBean(name = "consultasBean")
@ViewScoped
public class ConsultasBean extends Utilities implements Serializable {

    @Getter
    @Setter
    private List<ReportesDTO> reportesDTOs;

    @Getter
    @Setter
    private List<ReportesDTO> reportesDTOsFiltered;

    @Getter
    @Setter
    private ReportesDTO reporteDTO;

    @Getter
    @Setter
    private ReportesDTO reportesDTOSelected;

    @Getter
    @Setter
    private Boolean gestionEstadoReporte;

    @Getter
    private final String[] estadosReporte = {Enums.ESTADO_REPORTE_PROCESO.getValue(), Enums.ESTADO_REPORTE_FINALIADO.getValue()};

    @Getter
    private final String[] tipoReporte = {Enums.TIPO_REPORTE_DIAGNOSTICO.getValue(), Enums.TIPO_REPORTE_REPARACION.getValue()};

    @Getter
    @Setter
    private NotificacionDTO notificacionSelected;

    @Getter
    @Setter
    private AsignacionReparacion asignacionReparacionSelected;

    public ConsultasBean() {

    }

}
