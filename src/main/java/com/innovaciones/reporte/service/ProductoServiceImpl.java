/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.ProductoDAO;
import com.innovaciones.reporte.model.Producto;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pisama
 */
@Service
@ManagedBean(name = "productoService")
@ViewScoped
public class ProductoServiceImpl implements ProductoService, Serializable {

    private ProductoDAO productoDAO;

    @Override
    @Transactional
    public Producto addProducto(Producto producto) {
        return productoDAO.addProducto(producto);
    }

    public void setProductoDAO(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    @Override
    @Transactional
    public List<Producto> getProductos() {
        return productoDAO.getProductos();
    }

    @Override
    @Transactional
    public Producto getProductoById(Integer id) {
        return productoDAO.getProductoById(id);
    }

    @Override
    @Transactional
    public Producto getProductoByEquipo(String equipo) {
        return productoDAO.getProductoByEquipo(equipo);
    }

    @Override
    @Transactional
    public List<Producto> getProductosByEstado(Integer estado) {
        return productoDAO.getProductosByEstado(estado);
    }

}
