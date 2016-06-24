/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.beans;

import com.innovaciones.reporte.model.DTO.NotificacionDTO;
import com.innovaciones.reporte.model.Usuarios;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Fernando
 */
@ManagedBean(name = "sesionBean")
@SessionScoped
public class SesionBean implements Serializable {

    /**
     * Creates a new instance of ClienteBean
     */
    @Getter
    @Setter
    private Usuarios usuarios;

    @Setter
    private StreamedContent imageUsuario;

    @Getter
    @Setter
    private Boolean isReporteEdit;

    @Getter
    @Setter
    private Boolean isReporteVer;

    @Getter
    @Setter
    private String isReporteTipo;

    @Getter
    @Setter
    private String isSubReporteTipo;

    @Getter
    @Setter
    private List<NotificacionDTO> notificacionReportes;

    @Getter
    @Setter
    private NotificacionDTO notificacionReporteSeleccionado;

    @Getter
    @Setter
    private Boolean isAdminPostVentaRole;

    @Getter
    @Setter
    private Boolean isHabilitadoMenuCorto;

    @Getter
    @Setter
    private List<String> themes;

    @Getter
    @Setter
    private String themeSelected;

    @Getter
    @Setter
    private String tipoInstalacion;

    
    
    public SesionBean() {

    }

    public StreamedContent getImageUsuario() {
        try {
            byte[] image;

            if (this.getUsuarios().getImagen() != null) {
                image = this.getUsuarios().getImagen();
                return new DefaultStreamedContent(new ByteArrayInputStream(image));
            } else {

                return new DefaultStreamedContent(
                        FacesContext.getCurrentInstance().
                                getExternalContext().getResourceAsStream("/resources/images/userlogo.png"), "image/png");

            }
        } catch (Exception e) {
            return new DefaultStreamedContent(new ByteArrayInputStream(new byte[0]));
        }
    }

}
