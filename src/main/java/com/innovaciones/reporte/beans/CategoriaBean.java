/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.beans;

import com.innovaciones.reporte.model.Categoria;
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
@ManagedBean(name = "categoriaBean")
@ViewScoped
public class CategoriaBean implements Serializable{

    /**
     * Creates a new instance of CategoriaBean
     */
    
    @Getter
    @Setter
    private Categoria categoria;

    @Getter
    @Setter
    private List<Categoria> listCategorias;

    @Getter
    @Setter
    private List<Categoria> listFiltrados;

    @Getter
    @Setter
    private Categoria categoriaSelected;

    @Getter
    @Setter
    private List<EstadosEnum> listEstados;

    @Getter
    @Setter
    private Integer estado;
    
    public CategoriaBean() {
    }
    
}
