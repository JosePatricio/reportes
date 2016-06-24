/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.CategoriaBean;
import com.innovaciones.reporte.model.Categoria;
import com.innovaciones.reporte.service.CategoriaService;
import com.innovaciones.reporte.util.EstadosEnum;
import com.innovaciones.reporte.util.Utilities;
import static com.innovaciones.reporte.util.Utilities.cargarEstadosBoolean;
import static com.innovaciones.reporte.util.Utilities.closeModalBS;
import static com.innovaciones.reporte.util.Utilities.info;
import static com.innovaciones.reporte.util.Utilities.openModalBS;
import static com.innovaciones.reporte.util.Utilities.toUpperCaseStrings;
import static com.innovaciones.reporte.util.Utilities.update;
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
@ManagedBean(name = "categoriaController")
@ViewScoped
public class CategoriaController extends Utilities implements Serializable{

    /**
     * Creates a new instance of CategoriaController
     */
    
    @Getter
    @Setter
    @ManagedProperty("#{categoriaService}")
    private CategoriaService categoriaService;

    @Getter
    @Setter
    @ManagedProperty("#{categoriaBean}")
    private CategoriaBean categoriaBean;
    
    public CategoriaController() {
    }
    
    @PostConstruct
    void init() {
        categoriaBean.setListCategorias(categoriaService.getCategorias());
        categoriaBean.setListEstados(cargarEstadosBoolean());
    }

    public void abrirDialogCategoria(Categoria categoria) {

        if (categoria == null) {
            categoriaBean.setCategoria(new Categoria());
            categoriaBean.setEstado(EstadosEnum.ACTIVO.getValue());
        } else {
            categoriaBean.setCategoria(categoria);
            categoriaBean.setCategoriaSelected(categoria);
            categoriaBean.setEstado(categoria.getEstado());
        }
        update("formEditCategorias");
        openModalBS("dlgCategorias");
    }

    public void guardar() {

        categoriaBean.getCategoria().setEstado(categoriaBean.getEstado());
        categoriaBean.setCategoria((Categoria) toUpperCaseStrings(categoriaBean.getCategoria()));
        categoriaService.addCategoria(categoriaBean.getCategoria());

        categoriaBean.setListCategorias(categoriaService.getCategorias());
        info("Guardado exitósamente");

        update("tblCategorias");
        closeModalBS("dlgCategorias");
    }

    public void cerrarDialog() {
        closeModalBS("dlgCategorias");
    }

    
}
