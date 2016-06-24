/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.Categoria;
import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fernando
 */
@Repository
public class CategoriaDAOImpl implements CategoriaDAO, Serializable {

    private SessionFactory sessionFactory;

    @Override
    public Categoria addCategoria(Categoria categoria) {
        sessionFactory.getCurrentSession().saveOrUpdate(categoria);
        return categoria;
    }

    @Override
    public List<Categoria> getCategorias() {
        return sessionFactory.getCurrentSession().createQuery("from Categoria c ORDER BY c.nombre")
                .list();
    }

    @Override
    public Categoria getCategoriaById(Integer id) {
        List<Categoria> categorias = sessionFactory.getCurrentSession().createQuery("from Categoria WHERE id=" + id + "").list();
        Categoria categoria = null;
        if (categorias != null && !categorias.isEmpty()) {
            categoria = categorias.get(0);
        }
        return categoria;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Categoria> getCategoriasByEstado(Integer estado) {

        return sessionFactory.getCurrentSession().createQuery("from Categoria c WHERE c.estado =" + estado + " ORDER by c.nombre").
                list();
    }

}
