/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.ConfiguracionBean;
import com.innovaciones.reporte.model.Configuracion;
import com.innovaciones.reporte.service.ConfiguracionService;
import com.innovaciones.reporte.util.Enums;
import com.innovaciones.reporte.util.EstadosEnum;
import static com.innovaciones.reporte.util.Utilities.comparePropertiesObject;
import static com.innovaciones.reporte.util.Utilities.error;
import static com.innovaciones.reporte.util.Utilities.info;
import static com.innovaciones.reporte.util.Utilities.toUpperCaseStrings;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Fernando
 */
@ManagedBean(name = "configuracionController")
@ViewScoped
public class ConfiguracionController implements Serializable {

    /**
     * Creates a new instance of ConfiguracionController
     */
    @Setter
    @Getter
    @ManagedProperty(value = "#{configuracionService}")
    private ConfiguracionService configuracionService;

    @Setter
    @Getter
    @ManagedProperty(value = "#{configuracionBean}")
    private ConfiguracionBean configuracionBean;

    public ConfiguracionController() {
    }

    @PostConstruct
    public void init() {

        try {

            configuracionBean.setConfiguracion(configuracionService.getConfiguracionByCodigo(Enums.CODIGO_EMPRESA.getValue()));

            if (configuracionBean.getConfiguracion() == null) {
                configuracionBean.setConfiguracion(new Configuracion());
                configuracionBean.getConfiguracion().setCodigo(Enums.CODIGO_EMPRESA.getValue());
                configuracionBean.getConfiguracion().setEstado(EstadosEnum.ACTIVO.getValue());
                configuracionBean.setConfiguracionSelected(new Configuracion());
            } else {
                configuracionBean.setConfiguracionSelected((Configuracion) configuracionBean.getConfiguracion().clone());
//                configuracionBean.setConfiguracionSelected(configuracionService.getConfiguracionByCodigo(Enums.CODIGO_EMPRESA.getValue()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            error("No se pudo inicializar la pantalla de configuración");
        }

    }

    
    public void handleFileUpload(FileUploadEvent event) {

        try {
            if (event.getFile() != null) {
                configuracionBean.getConfiguracion().setLogo(IOUtils.toByteArray(event.getFile().getInputstream()));
                info("Logo cargado exitósamente");
            }

        } catch (Exception ex) {
            error("No se pudo cargar el logo");
            ex.printStackTrace();
        }

    }
    
    public void guardar() {
        try {
            if (!comparePropertiesObject(configuracionBean.getConfiguracion(), configuracionBean.getConfiguracionSelected())) {
                configuracionBean.setConfiguracion((Configuracion) toUpperCaseStrings(configuracionBean.getConfiguracion()));
                configuracionService.addConfiguracion(configuracionBean.getConfiguracion());
                info("Guardado exitósamente");
            } else {
                info("No se modificó el registro");
            }
        } catch (Exception e) {
            e.printStackTrace();
            error("No se pudo guardar la configuración");

        }
    }

}
