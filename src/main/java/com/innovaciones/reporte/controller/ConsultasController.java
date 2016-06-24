/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.AsignacionReparacionBean;
import com.innovaciones.reporte.beans.ConsultasBean;
import com.innovaciones.reporte.beans.ReporteBean;
import com.innovaciones.reporte.model.AsignacionReparacion;
import com.innovaciones.reporte.model.DTO.NotificacionDTO;
import com.innovaciones.reporte.model.Reporte;
import com.innovaciones.reporte.model.DTO.ReportesDTO;
import com.innovaciones.reporte.service.AsignacionReparacionService;
import com.innovaciones.reporte.service.ConsultasService;
import com.innovaciones.reporte.service.ReporteService;
import com.innovaciones.reporte.util.Enums;
import com.innovaciones.reporte.util.ReporteTecnico;
import com.innovaciones.reporte.util.Utilities;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pisama
 */
@ManagedBean(name = "consultasController")
@ViewScoped
public class ConsultasController extends Utilities implements Serializable {

    @Getter
    @Setter
    @ManagedProperty("#{consultasService}")
    private ConsultasService consultasService;

    @Getter
    @Setter
    @ManagedProperty("#{reporteService}")
    private ReporteService reporteService;

    @Getter
    @Setter
    @ManagedProperty("#{consultasBean}")
    private ConsultasBean consultasBean;

    @Getter
    @Setter
    @ManagedProperty("#{reporteBean}")
    private ReporteBean reporteBean;

    @Getter
    @Setter
    @ManagedProperty("#{reporteController}")
    private ReporteController reporteController;

    @Getter
    @Setter
    @ManagedProperty("#{sesionController}")
    private SesionController sesionController;

    @Getter
    @Setter
    @ManagedProperty("#{asignacionReparacionService}")
    private AsignacionReparacionService asignacionReparacionService;

    @Getter
    @Setter
    @ManagedProperty("#{asignacionReparacionBean}")
    private AsignacionReparacionBean asignacionReparacionBean;

    @PostConstruct
    void init() {
       
        consultasBean.setReportesDTOs(new ArrayList<ReportesDTO>());
        reporteBean.setReporte(new Reporte());
        consultasBean.setGestionEstadoReporte(habilitarGestionReporte(sesionController.getSesionBean().getUsuarios()));

        Object valueSessionNotificacion = getValueInSession("notificacionSeleccionada");
        Object valueSessionAsignacion = getValueInSession("asignacionReparacion");

        //INICIO DE VALIDACION DE SESSION getValueInSession("notificacionSeleccionada");
        if (valueSessionNotificacion == null && valueSessionAsignacion == null) {
            consultasBean.setReportesDTOs(consultasService.reportes());
        } else {
            if (valueSessionNotificacion != null) {

                consultasBean.setNotificacionSelected((NotificacionDTO) valueSessionNotificacion);
                setValueInSession("notificacionSeleccionada", null);

                if (consultasBean.getNotificacionSelected().getTipoNotificacion().equals(Enums.REPORTE.getPropertyName())) {

                    consultasBean.setReportesDTOs(new ArrayList<ReportesDTO>());
                    ReportesDTO reporte = consultasService.reportesById(consultasBean.getNotificacionSelected().getId());

                    if (reporte != null) {
                        consultasBean.getReportesDTOs().add(reporte);
                        consultasBean.setReportesDTOsFiltered(new ArrayList<ReportesDTO>());
                        consultasBean.getReportesDTOsFiltered().add(reporte);
                    }
                }

            }
            //FIN DE VALIDACION DE SESSION getValueInSession("notificacionSeleccionada");

            //INICIO DE VALIDACION DE SESSION getValueInSession("asignacionReparacion");
            if (valueSessionAsignacion != null) {

                setValueInSession("asignacionReparacion", null);
                Integer idReporte = Integer.parseInt(valueSessionAsignacion.toString());

                consultasBean.setReportesDTOs(new ArrayList<ReportesDTO>());

                ReportesDTO reporte = consultasService.reportesById(idReporte);

                if (reporte != null) {
                    consultasBean.getReportesDTOs().add(reporte);
                    consultasBean.setReportesDTOsFiltered(new ArrayList<ReportesDTO>());
                    consultasBean.getReportesDTOsFiltered().add(reporte);
                }

            }
        }

        //FIN DE VALIDACION DE SESSION getValueInSession("asignacionReparacion");
    }

    public String fomatearNumeroReporte(Integer idReporte) {
        return numeroFactura(idReporte);
    }

    public void PDF(ReportesDTO reportesDTO) {
        reporteController.llenarReportePorId(reportesDTO.getId());
        new ReporteTecnico().descargarReporte(reporteBean,Enums.REPORTE.getValue());

    }

    public void mensajeSession() {
        showSessionMensaje();
    }

    public String redirectToForm(ReportesDTO reportesDTO, Boolean esEditar, Boolean esVer, Boolean esReparacion) throws IOException {
        setIdReporteSession(reportesDTO.getId());

        sesionController.getSesionBean().setIsReporteEdit(esEditar);
        if (esReparacion) {
              sesionController.getSesionBean().setIsReporteTipo(Enums.TIPO_REPORTE_REPARACION.getValue());
        }
        
        sesionController.getSesionBean().setIsReporteVer(esVer);

        return "/paginas/reporte.jsf?faces-redirect=true";
    }
    
    public void onEstadoChange(ReportesDTO reportesDTO) {

        reporteBean.setReporteSelected(new Reporte());

        reporteBean.setReporteSelected(reporteService.getReporteById(reportesDTO.getId()));
        reporteBean.getReporteSelected().setEstado(reportesDTO.getEstado());
        reporteBean.getReporteSelected().setFechaModificacion(new Date());
        reporteBean.getReporteSelected().setUsuarioModificacion(sesionController.getSesionBean().getUsuarios().getUsuario());

        asignacionReparacionService.getAsignacionReparacionByIdReporte(reportesDTO.getId());

        asignacionReparacionBean.setAsignacionReparacionSelected(asignacionReparacionService.getAsignacionReparacionByIdReporte(reportesDTO.getId()));
        if (asignacionReparacionBean.getAsignacionReparacionSelected() != null) {
            asignacionReparacionBean.setAsignacionReparacion(new AsignacionReparacion());
            asignacionReparacionBean.setAsignacionReparacion(asignacionReparacionBean.getAsignacionReparacionSelected());
            asignacionReparacionBean.getAsignacionReparacion().setEstado(reporteBean.getReporteSelected().getEstado());
            asignacionReparacionService.addAsignacionReparacion(asignacionReparacionBean.getAsignacionReparacion());
        }

        reporteService.saveReporte(reporteBean.getReporteSelected());

        info("Estado actualizado a " + reporteBean.getReporteSelected().getEstado());
    }

}
