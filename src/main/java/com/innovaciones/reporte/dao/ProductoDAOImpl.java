/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.Producto;
import com.innovaciones.reporte.util.EstadosEnum;
import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class ProductoDAOImpl implements ProductoDAO, Serializable {

    private SessionFactory sessionFactory;

    @Override
    public Producto addProducto(Producto producto) {
        sessionFactory.getCurrentSession().saveOrUpdate(producto);
        return producto;
    }

    @Override
    public List<Producto> getProductos() {
        return sessionFactory.getCurrentSession().createQuery("from Producto p WHERE "
                + "     p.idModelo.estado =" + EstadosEnum.ACTIVO.getValue()
                + " AND p.idModelo.idMarca.estado = " + EstadosEnum.ACTIVO.getValue()
                + " AND p.idCategoria.estado = " + EstadosEnum.ACTIVO.getValue()
                + " ORDER BY p.idModelo.idMarca.marca, p.idModelo.modelo, p.equipo ")
                .list();
    }

    @Override
    public Producto getProductoById(Integer id) {
        List<Producto> productos = sessionFactory.getCurrentSession().createQuery("from Producto WHERE id=" + id + "").list();
        Producto producto = null;

        if (productos != null && !productos.isEmpty()) {
            producto = productos.get(0);
        }
        return producto;

    }

    @Override
    public Producto getProductoByEquipo(String equipo) {
        Producto cliente = (Producto) sessionFactory.getCurrentSession().createQuery("from Producto WHERE equipo='" + equipo + "'").uniqueResult();
        return cliente != null ? cliente : null;
    }

    @Override
    public List<Producto> getProductosByEstado(Integer estado) {
        return sessionFactory.getCurrentSession().createQuery("from Producto p WHERE "
                + "     p.idModelo.estado =" + EstadosEnum.ACTIVO.getValue()
                + " AND p.idModelo.idMarca.estado = " + EstadosEnum.ACTIVO.getValue()
                + " AND p.idCategoria.estado = " + EstadosEnum.ACTIVO.getValue()
                + " AND p.estado = " + estado
                + " ORDER BY p.idModelo.idMarca.marca, p.idModelo.modelo, p.equipo ")
                .list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
