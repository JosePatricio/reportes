/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.ModeloDAO;
import com.innovaciones.reporte.model.Modelo;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pisama
 */
@Service
@ManagedBean(name = "modeloService")
@ViewScoped
public class ModeloServiceImpl implements ModeloService, Serializable {

    private ModeloDAO modeloDAO;

    @Override
    @Transactional
    public Modelo addModelo(Modelo modelo) {
        return modeloDAO.addModelo(modelo);
    }

    public void setModeloDAO(ModeloDAO modeloDAO) {
        this.modeloDAO = modeloDAO;
    }

    @Override
    @Transactional
    public List<Modelo> getModelos() {
        return modeloDAO.getModelos();
    }

    @Override
    @Transactional
    public Modelo getModeloById(Integer id) {
        return modeloDAO.getModeloById(id);
    }

    @Override
    @Transactional
    public Modelo getModeloByModelo(String modelo) {
        return modeloDAO.getModeloByModelo(modelo);
    }

    @Override
    @Transactional
    public List<Modelo> getModelosByIdMarca(Integer id) {
        return modeloDAO.getModelosByIdMarca(id);
    }

    @Override
    @Transactional
    public Modelo saveModelo(Modelo modelo) {
        return modeloDAO.saveModelo(modelo);
    }

}
