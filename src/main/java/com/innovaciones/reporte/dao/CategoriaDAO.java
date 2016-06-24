/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.Categoria;
import java.util.List;

/**
 *
 * @author Fernando
 */
public interface CategoriaDAO {

    public Categoria addCategoria(Categoria categoria);

    public List<Categoria> getCategorias();

    public Categoria getCategoriaById(Integer id);

    public List<Categoria> getCategoriasByEstado(Integer estado);
}
