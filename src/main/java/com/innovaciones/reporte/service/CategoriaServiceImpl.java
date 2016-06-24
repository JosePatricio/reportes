/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.CategoriaDAO;
import com.innovaciones.reporte.model.Categoria;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fernando
 */
@Service
@ManagedBean(name = "categoriaService")
@ViewScoped
public class CategoriaServiceImpl implements CategoriaService, Serializable {

    private CategoriaDAO categoriaDAO;

    public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    @Transactional
    public Categoria addCategoria(Categoria categoria) {
        return categoriaDAO.addCategoria(categoria);
    }

    @Override
    @Transactional
    public List<Categoria> getCategorias() {
        return categoriaDAO.getCategorias();
    }

    @Override
    @Transactional
    public Categoria getCategoriaById(Integer id) {
        return categoriaDAO.getCategoriaById(id);
    }

    @Override
    @Transactional
    public List<Categoria> getCategoriasByEstado(Integer estado) {
        return categoriaDAO.getCategoriasByEstado(estado);
    }

}
