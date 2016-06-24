/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.Cliente;
import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class ClienteDAOImpl   implements ClienteDAO, Serializable{

    private SessionFactory sessionFactory;

    @Override
    public Cliente addCliente(Cliente cliente) {
        sessionFactory.getCurrentSession().saveOrUpdate(cliente);
        return cliente;
    }

    @Override
    public Cliente getClienteByRuc(String ruc) {
        Cliente cliente = (Cliente) sessionFactory.getCurrentSession().createQuery("from Cliente WHERE ruc='" + ruc + "'").uniqueResult();
        return cliente != null ? cliente : null;
    }

    @Override
    public Cliente getClienteByNombre(String nombre) {
     Cliente cliente = (Cliente) sessionFactory.getCurrentSession().createQuery("from Cliente WHERE cliente='" + nombre + "'").uniqueResult();
     return cliente != null ? cliente : null;
    }

    @Override
    public List<Cliente> getClientes() {
        return sessionFactory.getCurrentSession().createQuery("from Cliente c Order by c.cliente")
                .list();
    }

    @Override
    public List<Cliente> getClientesByEstado(Integer estado) {
        return sessionFactory.getCurrentSession().createQuery("from Cliente WHERE estado = " + estado)
                .list();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
