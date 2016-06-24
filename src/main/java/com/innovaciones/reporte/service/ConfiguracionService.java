/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.model.Configuracion;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface ConfiguracionService {

    public Configuracion addConfiguracion(Configuracion marca);

    public List<Configuracion> getConfiguraciones();

    public Configuracion getConfiguracionById(Integer id);

    public Configuracion getConfiguracionById(String id);
    
    public Configuracion getConfiguracionByRuc(String ruc);
    
    public Configuracion getConfiguracionByCodigo(String codigo);
    
    public List<Configuracion> getConfiguracionsByEstado(Integer estado);
}
