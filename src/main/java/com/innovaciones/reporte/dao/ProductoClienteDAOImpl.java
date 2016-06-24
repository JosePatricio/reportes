/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.ProductoCliente;
import com.innovaciones.reporte.util.EstadosEnum;
import java.io.Serializable;
import java.util.List;
import lombok.Setter;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class ProductoClienteDAOImpl implements ProductoClienteDAO, Serializable {

    @Setter
    private SessionFactory sessionFactory;

    @Override
    public ProductoCliente addProductoCliente(ProductoCliente productoCliente) {
        sessionFactory.getCurrentSession().saveOrUpdate(productoCliente);
        return productoCliente;
    }

    @Override
    public ProductoCliente updateProductoCliente(ProductoCliente productoCliente) {
        sessionFactory.getCurrentSession().update(productoCliente);
        return productoCliente;
    }

    @Override
    public ProductoCliente getProductoClienteBySerial(String serial) {
        ProductoCliente cliente = (ProductoCliente) sessionFactory.getCurrentSession().createQuery("from ProductoCliente p WHERE upper(p.serie)='" + serial.trim().toUpperCase() + "'").uniqueResult();
        return cliente != null ? cliente : null;
    }

    @Override
    public List<ProductoCliente> getProductoClientes() {
        return sessionFactory.getCurrentSession().createQuery("from ProductoCliente pc ORDER BY pc.fecha DESC, pc.idCliente.cliente")
                .list();
    }

    @Override
    public List<ProductoCliente> getProductoClienteByIdCliente(Integer id) {
        StringBuilder query = new  StringBuilder();
        
        query.append("from ProductoCliente pc Where pc.idCliente.id = ");
        query.append(id);
        query.append(" AND pc.estado = ");
        query.append(EstadosEnum.ACTIVO.getValue());
        query.append(" ORDER BY pc.idProducto.equipo");        
        return sessionFactory.getCurrentSession().createQuery(query.toString()).list();
    }

 
    

    @Override
    public ProductoCliente getProductoClienteById(Integer id) {
      ProductoCliente productoCliente = (ProductoCliente) sessionFactory.getCurrentSession().createQuery("from ProductoCliente p WHERE p.id=" +id+ "").uniqueResult();
        return productoCliente != null ? productoCliente : null;
    }

}
