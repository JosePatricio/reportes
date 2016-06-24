/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.beans;

import com.innovaciones.reporte.model.DetalleCatalogoReporte;
import com.innovaciones.reporte.model.Marca;
import com.innovaciones.reporte.model.Modelo;
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
@ManagedBean(name = "modeloBean")
@ViewScoped
public class ModeloBean implements Serializable {

    /**
     * Creates a new instance of MarcaBean
     */
    @Getter
    @Setter
    private Modelo modelo;

    @Getter
    @Setter
    private List<Modelo> listModelos;

    @Getter
    @Setter
    private List<Modelo> listFiltrados;

    @Getter
    @Setter
    private Modelo modeloSelected;

    @Getter
    @Setter
    private List<EstadosEnum> listEstados;

    @Getter
    @Setter
    private Integer estado;

    @Getter
    @Setter
    private List<Marca> listMarcas;

    @Getter
    @Setter
    private Integer idMarca;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listDetalleCatalogoReportes;

    @Getter
    @Setter
    private DetalleCatalogoReporte detalleCatalogoReporteSelected;

    @Getter
    @Setter
    private List<RepuestoModelo> listRespuestoModelosByModelo;

    @Getter
    @Setter
    private List<RepuestoModelo> listRepuestoModelo;

    @Getter
    @Setter
    private RepuestoModelo respuestoModeloSelected;

    @Getter
    @Setter
    private Boolean tabAsignacion;

    @Getter
    @Setter
    private Boolean habilitarCampoStock;
    
    public ModeloBean() {

    }
}
