/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.MarcaBean;
import com.innovaciones.reporte.model.Marca;
import com.innovaciones.reporte.service.MarcaService;
import com.innovaciones.reporte.util.EstadosEnum;
import com.innovaciones.reporte.util.Utilities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Fernando
 */
@ManagedBean(name = "marcaController")
@ViewScoped
public class MarcaController extends Utilities implements Serializable {

    @Getter
    @Setter
    @ManagedProperty("#{marcaService}")
    private MarcaService marcaService;

    @Getter
    @Setter
    @ManagedProperty("#{marcaBean}")
    private MarcaBean marcaBean;

    /**
     * Creates a new instance of UsuariosController
     */
    public MarcaController() {
    }

    @PostConstruct
    void init() {
        marcaBean.setListMarcas(marcaService.getMarcas());
        marcaBean.setListEstados(cargarEstadosBoolean());
    }

    public void abrirDialogMarca(Marca marca) {

        if (marca == null) {
            marcaBean.setMarca(new Marca());
            marcaBean.setEstado(EstadosEnum.ACTIVO.getValue());
        } else {
            marcaBean.setMarca(marca);
            marcaBean.setMarcaSelected(marca);
            marcaBean.setEstado(marca.getEstado());
        }
        update("formEditMarcas");
        openModalBS("dlgMarcas");
    }

    public void guardar() {

        marcaBean.getMarca().setEstado(marcaBean.getEstado());
        marcaBean.setMarca((Marca) toUpperCaseStrings(marcaBean.getMarca()));
        marcaService.addMarca(marcaBean.getMarca());

        marcaBean.setListMarcas(marcaService.getMarcas());
        info("Guardado exitósamente");

        update("tblMarcas");
        closeModalBS("dlgMarcas");
    }

    public void cerrarDialog() {
        closeModalBS("dlgMarcas");
    }

   

}
