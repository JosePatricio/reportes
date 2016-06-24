/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.beans;

import com.innovaciones.reporte.model.DetalleReporteInstalacionNueva;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pisama
 */
@ManagedBean(name = "detalleReporteInstalacionNuevaBean")
@ViewScoped
public class DetalleReporteInstalacionNuevaBean implements Serializable {

    @Getter
    @Setter
    private DetalleReporteInstalacionNueva detalleReporteInstalacionNueva;
    
    @Getter
    @Setter
    private DetalleReporteInstalacionNueva detalleReporteInstalacionNuevaSelected;

}
