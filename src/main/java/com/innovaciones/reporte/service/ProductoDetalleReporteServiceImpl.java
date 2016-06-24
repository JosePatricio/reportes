/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.ProductoDetalleReporteDAO;
import com.innovaciones.reporte.model.ProductoDetalleReporte;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pisama
 */
@Service
@ManagedBean(name = "productoDetalleReporteService")
@ViewScoped
public class ProductoDetalleReporteServiceImpl implements ProductoDetalleReporteService, Serializable {

    @Autowired
    private ProductoDetalleReporteDAO productoDetalleReporteDAO;

    @Override
    @Transactional
    public ProductoDetalleReporte addProductoDetalleReporte(ProductoDetalleReporte productoDetalleReporte) {
        productoDetalleReporteDAO.addProductoDetalleReporte(productoDetalleReporte);
        return productoDetalleReporte;

    }

    @Override
    @Transactional
    public ProductoDetalleReporte updateProductoDetalleReporte(ProductoDetalleReporte productoDetalleReporte) {
        productoDetalleReporteDAO.updateProductoDetalleReporte(productoDetalleReporte);
        return productoDetalleReporte;
    }

    public void setProductoDetalleReporteDAO(ProductoDetalleReporteDAO productoDetalleReporteDAO) {
        this.productoDetalleReporteDAO = productoDetalleReporteDAO;
    }

}
