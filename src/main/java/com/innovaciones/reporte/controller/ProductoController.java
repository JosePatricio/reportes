/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.ProductoBean;
import com.innovaciones.reporte.model.Categoria;
import com.innovaciones.reporte.model.Marca;
import com.innovaciones.reporte.model.Modelo;
import com.innovaciones.reporte.model.Producto;
import com.innovaciones.reporte.service.CategoriaService;
import com.innovaciones.reporte.service.MarcaService;
import com.innovaciones.reporte.service.ModeloService;
import com.innovaciones.reporte.service.ProductoService;
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
@ManagedBean(name = "productoController")
@ViewScoped
public class ProductoController extends Utilities implements Serializable {
    
    @Getter
    @Setter
    @ManagedProperty("#{productoService}")
    private ProductoService productoService;
    
    @Getter
    @Setter
    @ManagedProperty("#{marcaService}")
    private MarcaService marcaService;
    
    @Getter
    @Setter
    @ManagedProperty("#{modeloService}")
    private ModeloService modeloService;
    
    @Getter
    @Setter
    @ManagedProperty("#{productoBean}")
    private ProductoBean productoBean;

    @Getter
    @Setter
    @ManagedProperty("#{categoriaService}")
    private CategoriaService categoriaService;

    /**
     * Creates a new instance of ProductoController
     */
    public ProductoController() {
    }
    
    @PostConstruct
    void init() {
        
        productoBean.setListProductos(productoService.getProductos());
        productoBean.setListMarcas(marcaService.getMarcas());
        productoBean.setListEstados(cargarEstadosBoolean());
        productoBean.setListCategorias(categoriaService.getCategoriasByEstado(EstadosEnum.ACTIVO.getValue()));
        productoBean.setIdMarca(null);
    }
    
    public void cargarModelosByIdMarca() {
        productoBean.setIdModelo(null);
        productoBean.setListModelos(modeloService.getModelosByIdMarca(productoBean.getIdMarca()));
    }
    
    public void abrirDialogProducto(Producto producto) {
        
        if (producto == null) {
            productoBean.setIdMarca(null);
            productoBean.setIdModelo(null);
            productoBean.setProducto(new Producto());
            productoBean.setEstado(EstadosEnum.ACTIVO.getValue());
            productoBean.setIdCategoria(null);
            productoBean.getProducto().setIdCategoria(new Categoria());

        } else {
            productoBean.setProducto(producto);
            productoBean.setProductoSelected(producto);
            productoBean.setIdMarca(producto.getIdModelo().getIdMarca().getId());
            productoBean.setListModelos(modeloService.getModelosByIdMarca(productoBean.getIdMarca()));
            productoBean.setIdCategoria(producto.getIdCategoria().getId());
            productoBean.setIdModelo(producto.getIdModelo().getId());
            // productoBean.setEstado(producto.getEstado());
            productoBean.setEstado(producto.getEstado());
        }
        update("formEditProductos");
        openModalBS("dlgProductos");
        
    }
    
    public void guardar() {
        
        productoBean.getProducto().setEstado(productoBean.getEstado() );
        productoBean.getProducto().setIdModelo(new Modelo(productoBean.getIdModelo()));
        productoBean.getProducto().getIdModelo().setIdMarca(new Marca(productoBean.getIdMarca()));
        productoBean.getProducto().getIdCategoria().setId(productoBean.getIdCategoria());
        productoBean.setProducto((Producto) toUpperCaseStrings(productoBean.getProducto()));

        productoService.addProducto(productoBean.getProducto());
        productoBean.setListProductos(productoService.getProductos());
        
        info("Guardado exitósamente");
        update("tblProductos");
        cerrarDialog();
    }
    
    public void cerrarDialog() {
        closeModalBS("dlgProductos");
    }
}
