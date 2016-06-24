/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.beans;

import com.innovaciones.reporte.model.Categoria;
import com.innovaciones.reporte.model.Marca;
import com.innovaciones.reporte.model.Modelo;
import com.innovaciones.reporte.model.Producto;
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
@ManagedBean(name = "productoBean")
@ViewScoped
public class ProductoBean implements Serializable {

    /**
     * Creates a new instance of MarcaBean
     */
    @Getter
    @Setter
    private Producto producto;

    @Getter
    @Setter
    private List<Producto> listProductos;

    @Getter
    @Setter
    private List<Producto> listFiltrados;

    @Getter
    @Setter
    private Producto productoSelected;

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
    private List<Modelo> listModelos;

    @Getter
    @Setter
    private List<Categoria> listCategorias;

    @Getter
    @Setter
    private Integer idCategoria;

    @Getter
    @Setter
    private Integer idModelo;

    public ProductoBean() {

    }

}
