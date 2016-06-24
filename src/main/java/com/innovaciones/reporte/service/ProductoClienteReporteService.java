/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.model.ProductoClienteReporte;

/**
 *
 * @author pisama
 */
public interface ProductoClienteReporteService {

    public ProductoClienteReporte saveOrUpdateProductoClienteReporte(ProductoClienteReporte productoClienteReporte);

    public ProductoClienteReporte saveProductoClienteReporte(ProductoClienteReporte productoClienteReporte);

    public ProductoClienteReporte updateProductoClienteReporte(ProductoClienteReporte productoClienteReporte);

 
    public ProductoClienteReporte getProductoClienteReporteByReportId(Integer ids);

    public ProductoClienteReporte getProductoClienteReporteByUsuarioRuc(String ruc);

    public ProductoClienteReporte getProductoClienteReporteBySerial(String serial);

    public ProductoClienteReporte getProductoClienteReporteByIdProductoCliente(Integer idProductoCliente);
}
