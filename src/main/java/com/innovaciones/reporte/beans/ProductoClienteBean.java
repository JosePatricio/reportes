/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.beans;

import com.innovaciones.reporte.model.Cliente;
import com.innovaciones.reporte.model.Producto;
import com.innovaciones.reporte.model.ProductoCliente;
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
@ManagedBean(name = "productoClienteBean")
@ViewScoped
public class ProductoClienteBean implements Serializable {

    /**
     * Creates a new instance of ClienteBean
     */
    @Getter
    @Setter
    private ProductoCliente productoCliente;

    @Getter
    @Setter
    private List<ProductoCliente> listProductoClientes;

    @Getter
    @Setter
    private ProductoCliente productoClienteSelected;

    @Getter
    @Setter
    private List<Producto> listProductos;

    @Getter
    @Setter
    private Producto productoSelected;

    @Getter
    @Setter
    private List<Cliente> listClientes;

    @Getter
    @Setter
    private Cliente clienteSelected;

    @Getter
    @Setter
    private List<EstadosEnum> listEstados;

    @Getter
    @Setter
    private Integer estado;

    @Getter
    @Setter
    private int indiceTabView;

    public ProductoClienteBean() {

    }
}
