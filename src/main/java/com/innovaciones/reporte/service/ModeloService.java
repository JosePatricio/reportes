/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.model.Modelo;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface ModeloService {

    public Modelo addModelo(Modelo modelo);

    public List<Modelo> getModelos();

    public Modelo getModeloById(Integer id);

    public Modelo getModeloByModelo(String id);

    public List<Modelo> getModelosByIdMarca(Integer id);

    public Modelo saveModelo(Modelo modelo);
}
