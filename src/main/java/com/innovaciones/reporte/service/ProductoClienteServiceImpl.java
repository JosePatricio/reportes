/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.ProductoClienteDAO;
import com.innovaciones.reporte.model.ProductoCliente;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pisama
 */
@Service
@ManagedBean(name = "productoClienteService")
@ViewScoped
public class ProductoClienteServiceImpl implements ProductoClienteService, Serializable {

    @Setter
    private ProductoClienteDAO productoClienteDAO;

    @Override
    @Transactional
    public ProductoCliente addProductoCliente(ProductoCliente productoCliente) {
        return productoClienteDAO.addProductoCliente(productoCliente);

    }

    @Override
    @Transactional
    public ProductoCliente updateProductoCliente(ProductoCliente productoCliente) {
        return productoClienteDAO.updateProductoCliente(productoCliente);
    }

    @Override
    @Transactional
    public ProductoCliente getProductoClienteBySerial(String serial) {
        return productoClienteDAO.getProductoClienteBySerial(serial);
    }

    @Override
    @Transactional
    public List<ProductoCliente> getProductoClientes() {
        return productoClienteDAO.getProductoClientes();
    }

    @Override
    @Transactional
    public List<ProductoCliente> getProductoClienteByIdCliente(Integer id) {
        return productoClienteDAO.getProductoClienteByIdCliente(id);
    }

    @Override
    @Transactional
    public ProductoCliente getProductoClienteById(Integer id) {
        return productoClienteDAO.getProductoClienteById(id);
    }

}
