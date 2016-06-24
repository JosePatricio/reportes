/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.ProductoClienteReporte;
import java.io.Serializable;
import lombok.Setter;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class ProductoClienteReporteDAOImpl implements ProductoClienteReporteDAO, Serializable {

    @Setter
    private SessionFactory sessionFactory;

    @Override
    public ProductoClienteReporte saveOrUpdateProductoClienteReporte(ProductoClienteReporte productoClienteReporte) {
        sessionFactory.getCurrentSession().saveOrUpdate(productoClienteReporte);
        return productoClienteReporte;
    }

    @Override
    public ProductoClienteReporte saveProductoClienteReporte(ProductoClienteReporte productoClienteReporte) {
        sessionFactory.getCurrentSession().save(productoClienteReporte);
        return productoClienteReporte;
    }

    @Override
    public ProductoClienteReporte updateProductoClienteReporte(ProductoClienteReporte productoClienteReporte) {
        sessionFactory.getCurrentSession().update(productoClienteReporte);
        return productoClienteReporte;
    }

    @Override
    public ProductoClienteReporte getProductoClienteReporteByReportId(Integer ID) {
        ProductoClienteReporte productoClienteReporte
                = (ProductoClienteReporte) sessionFactory.getCurrentSession().
                createQuery("from ProductoClienteReporte pcr WHERE pcr.idReporte.id=" + ID + "")
                .uniqueResult();

        return productoClienteReporte != null ? productoClienteReporte : null;
    }

    @Override
    public ProductoClienteReporte getProductoClienteReporteByUsuarioRuc(String ruc) {

        ProductoClienteReporte productoClienteReporte
                = (ProductoClienteReporte) sessionFactory.getCurrentSession().
                createQuery("from ProductoClienteReporte p WHERE p.idProductoCliente.idCliente.ruc='" + ruc + "'")
                .uniqueResult();

        return productoClienteReporte != null ? productoClienteReporte : null;
    }

    @Override
    public ProductoClienteReporte getProductoClienteReporteBySerial(String serial) {
        ProductoClienteReporte productoClienteReporte
                = (ProductoClienteReporte) sessionFactory.getCurrentSession().
                createQuery("from ProductoClienteReporte p WHERE p.idProductoCliente.serie='" + serial + "'")
                .uniqueResult();

        return productoClienteReporte != null ? productoClienteReporte : null;
    }

    @Override
    public ProductoClienteReporte getProductoClienteReporteByIdProductoCliente(Integer idProductoCliente) {
        ProductoClienteReporte productoClienteReporte
                = (ProductoClienteReporte) sessionFactory.getCurrentSession().
                createQuery("from ProductoClienteReporte p WHERE p.idProductoCliente.id=" + idProductoCliente + "")
                .uniqueResult();

        return productoClienteReporte != null ? productoClienteReporte : null;
    }

}
