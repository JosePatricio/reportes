/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.Marca;
import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class MarcaDAOImpl implements MarcaDAO, Serializable {

    private SessionFactory sessionFactory;

    @Override
    public Marca addMarca(Marca marca) {
        sessionFactory.getCurrentSession().saveOrUpdate(marca);
        return marca;
    }

    @Override
    public List<Marca> getMarcas() {
        return sessionFactory.getCurrentSession().createQuery("from Marca m ORDER BY m.marca")
                .list();
    }

    @Override
    public Marca getMarcaById(Integer id) {
        Marca marca = (Marca) sessionFactory.getCurrentSession().createQuery("from Marca WHERE id=" + id + "").uniqueResult();
        return marca != null ? marca : null;
    }

    @Override
    public Marca getMarcaByMarca(String marca_) {
        Marca marca = (Marca) sessionFactory.getCurrentSession().createQuery("from Marca WHERE marca='" + marca_ + "'").uniqueResult();
        return marca != null ? marca : null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Marca> getMarcasByEstado(Integer estado) {

        return sessionFactory.getCurrentSession().createQuery("from Marca WHERE estado =" + estado).
                list();
    }

    @Override
    public Marca saveMarca(Marca marca) {
        sessionFactory.getCurrentSession().save(marca);
        return marca;
    }

}
