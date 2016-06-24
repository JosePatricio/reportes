/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.ProductoClienteReporteDAO;
import com.innovaciones.reporte.model.ProductoClienteReporte;
import java.io.Serializable;
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
@ManagedBean(name = "productoClienteReporteService")
@ViewScoped
public class ProductoClienteReporteServiceImpl implements ProductoClienteReporteService, Serializable {

    @Setter
    private ProductoClienteReporteDAO productoClienteReporteDAO;

    @Override
    @Transactional
    public ProductoClienteReporte saveOrUpdateProductoClienteReporte(ProductoClienteReporte productoClienteReporte) {
        return productoClienteReporteDAO.saveOrUpdateProductoClienteReporte(productoClienteReporte);
    }

    @Override
    @Transactional
    public ProductoClienteReporte updateProductoClienteReporte(ProductoClienteReporte productoClienteReporte) {
        return productoClienteReporteDAO.updateProductoClienteReporte(productoClienteReporte);
    }

    @Override
    @Transactional
    public ProductoClienteReporte saveProductoClienteReporte(ProductoClienteReporte productoClienteReporte) {
        return productoClienteReporteDAO.saveProductoClienteReporte(productoClienteReporte);
    }

    @Override
    @Transactional
    public ProductoClienteReporte getProductoClienteReporteByReportId(Integer ids) {
        return productoClienteReporteDAO.getProductoClienteReporteByReportId(ids);
    }

    @Override
    @Transactional
    public ProductoClienteReporte getProductoClienteReporteByUsuarioRuc(String ruc) {
        return productoClienteReporteDAO.getProductoClienteReporteByUsuarioRuc(ruc);
    }

    @Override
    @Transactional
    public ProductoClienteReporte getProductoClienteReporteBySerial(String serial) {
        return productoClienteReporteDAO.getProductoClienteReporteBySerial(serial);
    }

    @Override
    @Transactional
    public ProductoClienteReporte getProductoClienteReporteByIdProductoCliente(Integer idProductoCliente) {
        return productoClienteReporteDAO.getProductoClienteReporteByIdProductoCliente(idProductoCliente);
    }

}
