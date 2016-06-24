/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.beans;

import com.innovaciones.reporte.model.Marca;
import com.innovaciones.reporte.model.RepuestoModelo;
import com.innovaciones.reporte.util.EstadosEnum;
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
@ManagedBean(name = "marcaBean")
@ViewScoped
public class MarcaBean implements Serializable{

    /**
     * Creates a new instance of MarcaBean
     */
    @Getter
    @Setter
    private Marca marca;

    @Getter
    @Setter
    private List<Marca> listMarcas;

    @Getter
    @Setter
    private List<Marca> listFiltrados;

    @Getter
    @Setter
    private Marca marcaSelected;

    @Getter
    @Setter
    private List<EstadosEnum> listEstados;

    @Getter
    @Setter
    private Integer estado;

    public MarcaBean() {

    }
}
