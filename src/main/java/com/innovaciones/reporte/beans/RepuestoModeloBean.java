/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.beans;

import com.innovaciones.reporte.model.RepuestoModelo;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pisama
 */
@ManagedBean(name = "repuestoModeloBean")
@ViewScoped
public class RepuestoModeloBean implements Serializable {

    @Getter
    @Setter
    private RepuestoModelo repuestoModelo;

    @Getter
    @Setter
    private RepuestoModelo repuestoModeloSelected;

    @Getter
    @Setter
    private List<RepuestoModelo> listRepuestoModelo;

    @Getter
    @Setter
    private Map<String, String> codigosRepuestoModelo = new HashMap<String, String>();

    @Getter
    @Setter
    private String codigoRepuesto;

    @Getter
    @Setter
    private String marcaRepuesto;

    @Getter
    @Setter
    private Integer stockRepuesto;

}
