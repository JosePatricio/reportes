/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.model.Producto;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface ProductoService {

    public Producto addProducto(Producto producto);

    public List<Producto> getProductos();

    public Producto getProductoById(Integer id);

    public Producto getProductoByEquipo(String equipo);

    public List<Producto> getProductosByEstado(Integer estado);
}
