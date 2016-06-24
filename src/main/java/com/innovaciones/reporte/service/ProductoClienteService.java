/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.model.ProductoCliente;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface ProductoClienteService {

    public ProductoCliente addProductoCliente(ProductoCliente productoCliente);

    public ProductoCliente updateProductoCliente(ProductoCliente productoCliente);

    public ProductoCliente getProductoClienteBySerial(String serial);

    public List<ProductoCliente> getProductoClientes();

    public List<ProductoCliente> getProductoClienteByIdCliente(Integer id);

    public ProductoCliente getProductoClienteById(Integer id);
}
