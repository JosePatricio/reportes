/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.beans;

import com.innovaciones.reporte.model.Configuracion;
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
@ManagedBean(name = "configuracionBean")
@ViewScoped
public class ConfiguracionBean implements Serializable {

    /**
     * Creates a new instance of ConfiguracionBean
     */
    @Setter
    @Getter
    private Configuracion configuracion;

    @Setter
    @Getter
    private Configuracion configuracionSelected;

    @Setter
    @Getter
    private List<Configuracion> listConfiguraciones;

    public ConfiguracionBean() {
    }

}
