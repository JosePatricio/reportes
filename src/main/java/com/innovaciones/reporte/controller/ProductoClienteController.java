/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.ProductoClienteBean;
import com.innovaciones.reporte.model.Cliente;
import com.innovaciones.reporte.model.Producto;
import com.innovaciones.reporte.model.ProductoCliente;
import com.innovaciones.reporte.service.ClienteService;
import com.innovaciones.reporte.service.ProductoClienteService;
import com.innovaciones.reporte.service.ProductoService;
import com.innovaciones.reporte.util.EstadosEnum;
import com.innovaciones.reporte.util.Utilities;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author Fernando
 */
@ManagedBean(name = "productoClienteController")
@ViewScoped
public class ProductoClienteController extends Utilities implements Serializable {

    @Getter
    @Setter
    @ManagedProperty("#{productoClienteService}")
    private ProductoClienteService productoClienteService;

    @Getter
    @Setter
    @ManagedProperty("#{productoService}")
    private ProductoService productoService;

    @Getter
    @Setter
    @ManagedProperty("#{clienteService}")
    private ClienteService clienteService;

    @Getter
    @Setter
    @ManagedProperty("#{productoClienteBean}")
    private ProductoClienteBean productoClienteBean;

    /**
     * Creates a new instance of UsuariosController
     */
    public ProductoClienteController() {
    }

    @PostConstruct
    void init() {
        productoClienteBean.setListProductoClientes(productoClienteService.getProductoClientes());
        productoClienteBean.setListProductos(productoService.getProductosByEstado(EstadosEnum.ACTIVO.getValue()));
        productoClienteBean.setListClientes(clienteService.getClientesByEstado(EstadosEnum.ACTIVO.getValue()));
        productoClienteBean.setListEstados(cargarEstadosBoolean());
    }

    public void resetearPaginatorTabla(String idDataTable) {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(idDataTable);
        dataTable.reset();
    }

    public void abrirDialogProductoCliente(ProductoCliente productoCliente) {
        productoClienteBean.setIndiceTabView(0);
        if (productoCliente == null) {
            productoClienteBean.setProductoCliente(new ProductoCliente());
            productoClienteBean.setEstado(EstadosEnum.ACTIVO.getValue());
            productoClienteBean.setClienteSelected(new Cliente());
            productoClienteBean.setProductoSelected(new Producto());
            productoClienteBean.getProductoCliente().setFecha(getDateServer());

        } else {
            productoClienteBean.setProductoCliente(productoCliente);
            productoClienteBean.setClienteSelected(productoCliente.getIdCliente());
            productoClienteBean.setProductoSelected(productoCliente.getIdProducto());
            productoClienteBean.setProductoClienteSelected(productoCliente);
            productoClienteBean.setEstado(productoCliente.getEstado());

        }

        update("formEditProductoClientes");
        update("tblProductoClientes");

        update("tabSeleccion:tblClientes");
        update("tabSeleccion:tblProductos");
        update("tabSeleccion");
        resetearPaginatorTabla("formEditProductoClientes:tabSeleccion:tblClientes");
        resetearPaginatorTabla("formEditProductoClientes:tabSeleccion:tblProductos");

        openModalBS("dlgProductoClientes");
    }

    private void guardarProductoCliente() {

        productoClienteBean.getProductoCliente().setEstado(productoClienteBean.getEstado());
        //productoClienteBean.getProductoCliente().setIdCliente(productoClienteBean.getClienteSelected());
        //productoClienteBean.getProductoCliente().setIdProducto(productoClienteBean.getProductoSelected());

        productoClienteService.addProductoCliente(productoClienteBean.getProductoCliente());

        productoClienteBean.setListProductoClientes(productoClienteService.getProductoClientes());

        productoClienteBean.setProductoClienteSelected(new ProductoCliente());
        productoClienteBean.setClienteSelected(new Cliente());
        productoClienteBean.setProductoSelected(new Producto());

        info("Guardado exitósamente");
        update("tblProductoClientes");
        cerrarDialog();
    }

    public void guardar() {

        productoClienteBean.setProductoClienteSelected(productoClienteService.getProductoClienteBySerial(productoClienteBean.getProductoCliente().getSerie()));

        //System.out.println("com.innovaciones.reporte.controller.ProductoClienteController.guardar().selected: " + productoClienteBean.getProductoClienteSelected());

        if (productoClienteBean.getProductoClienteSelected() == null) {
            
            guardarProductoCliente();
        } else {
            if (productoClienteBean.getProductoClienteSelected().getId() != null) {
                if (productoClienteBean.getProductoClienteSelected().getId().equals(
                        productoClienteBean.getProductoCliente().getId())) {
                    
                    guardarProductoCliente();
                } else {
                    warn("El serial \"" + productoClienteBean.getProductoCliente().getSerie() + "\" ya existe");
                }
            }
        }

    }

    public void cerrarDialog() {
        closeModalBS("dlgProductoClientes");
    }

    public void seleccionarCliente(Cliente cliente) {
        productoClienteBean.setClienteSelected(cliente); 
        productoClienteBean.getProductoCliente().setIdCliente(cliente);
        info("Seleccione un producto e ingrese el serial");
        productoClienteBean.setIndiceTabView(1);
        update("tabSeleccion:tblClientes");
        update("tabSeleccion");
        update("opSeleccion");
    }

    public void seleccionarProducto(Producto producto) {
        productoClienteBean.setProductoSelected(producto);
        productoClienteBean.getProductoCliente().setIdProducto(producto);
        productoClienteBean.getProductoCliente().setSerie("");
        info("Ingrese el serial del producto");
        update("tabSeleccion:tblProductos");
        update("opSeleccion");
    }
}
