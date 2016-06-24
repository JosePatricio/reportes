/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.SesionBean;
import com.innovaciones.reporte.util.Enums;
import com.innovaciones.reporte.util.EstadosEnum;
import com.innovaciones.reporte.util.Utilities;

import java.io.IOException;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Fernando
 */
@ManagedBean(name = "sesionController")
@SessionScoped
public class SesionController extends Utilities implements Serializable {

    /**
     * Creates a new instance of SesionController
     */
    @Getter
    @Setter
    @ManagedProperty("#{sesionBean}")
    private SesionBean sesionBean;

    public SesionController() {
    }

    @PostConstruct
    void init() {
        //info("Bienvenido");
        sesionBean.setThemes(new ArrayList<String>());
        sesionBean.getThemes().add("cupertino");
        sesionBean.getThemes().add("bootstrap");
        sesionBean.getThemes().add("redmond");
        sesionBean.getThemes().add("casablanca");
        sesionBean.getThemes().add("delta");
        sesionBean.getThemes().add("start");
        sesionBean.getThemes().add("afterwork");
        sesionBean.getThemes().add("aristo");

        sesionBean.setThemeSelected("redmond");

        System.out.println("com.innovaciones.reporte.controller.SesionController.init(): " + sesionBean.getThemeSelected());
    }

    public void redirectToUrl(String url, String tipoGeneral) {
        try {
            sesionBean.setIsReporteTipo(tipoGeneral);
            redirectToPage(url);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void redirectToUrl(String url) {
        try {
            redirectToPage(url);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void redireccionHome() {
        try {
            if (getSesionBean().getIsHabilitadoMenuCorto() != null
                    && getSesionBean().getIsHabilitadoMenuCorto()) {
                redirectToPage("/paginas/menu/menu-principal.jsf");

            } else {
                redirectToPage("/paginas/consultas.jsf");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getStatus(Integer estado) {
        String result = null;

        if (estado == null) {
            result = "";
        } else if (EstadosEnum.ACTIVO.getValue().equals(estado)) {
            result = EstadosEnum.ACTIVO.getPropertyName();
        } else if (EstadosEnum.INACTIVO.getValue().equals(estado)) {
            result = EstadosEnum.INACTIVO.getPropertyName();
        }

        return result;
    }

    public String getClassByPrioridad(Integer idPrioridad) {
        return Utilities.obtenerClassByPrioridad(idPrioridad);
    }

    public String getNamePrioridad(Integer idPrioridad) {
        return Utilities.obtenerNameByPrioridad(idPrioridad);
    }

    public void verificarTheme() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getApplication().evaluateExpressionGet(context, sesionBean.getThemeSelected(), String.class);
    }

    public String redirect(String pagina, String parametro) throws IOException {
        killIdReporteSession();

        getSesionBean().setIsReporteEdit(false);

        String result = "";

        setSessionText(Enums.PARAMETRO_TIPO_REPORTE.getValue(), parametro);

        sesionBean.setIsSubReporteTipo(parametro);

        if (sesionBean.getIsReporteTipo().equalsIgnoreCase(pagina)) {

            result = "/paginas/reporte.xhtml?faces-redirect=true";

        } else {

            if ("instalacion-nueva".equalsIgnoreCase(pagina)) {
                result = "/paginas/instalaciones/instalacion-nueva.xhtml?faces-redirect=true";
            }
            if ("instalacion-temporal".equalsIgnoreCase(pagina)) {
                result = "/paginas/instalaciones/instalacion-temporal.xhtml?faces-redirect=true";
            }
             if ("instalacion-administracion".equalsIgnoreCase(pagina)) {
                result = "/paginas/instalaciones/administracion.xhtml?faces-redirect=true";
            }
            
            
        }

        return result;

    }

}
