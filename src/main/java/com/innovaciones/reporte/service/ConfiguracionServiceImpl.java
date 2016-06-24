/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.ConfiguracionDAO;
import com.innovaciones.reporte.model.Configuracion;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fernando
 */
public class ConfiguracionServiceImpl implements ConfiguracionService, Serializable {

    @Getter
    @Setter
    private ConfiguracionDAO configuracionDAO;

    @Transactional
    @Override
    public Configuracion addConfiguracion(Configuracion configuracion) {
        return configuracionDAO.addConfiguracion(configuracion);
    }

    @Transactional
    @Override
    public List<Configuracion> getConfiguraciones() {
        return configuracionDAO.getConfiguraciones();
    }

    @Transactional
    @Override
    public Configuracion getConfiguracionById(Integer id) {
        return configuracionDAO.getConfiguracionById(id);
    }

    @Transactional
    @Override
    public Configuracion getConfiguracionByRuc(String ruc) {
        return configuracionDAO.getConfiguracionByRuc(ruc);
    }

    @Transactional
    @Override
    public Configuracion getConfiguracionByCodigo(String codigo) {
        return configuracionDAO.getConfiguracionByCodigo(codigo);
    }

    @Transactional
    @Override
    public Configuracion getConfiguracionById(String id) {
        return configuracionDAO.getConfiguracionById(id);
    }

    @Transactional
    @Override
    public List<Configuracion> getConfiguracionsByEstado(Integer estado) {
        return configuracionDAO.getConfiguracionsByEstado(estado);
    }
}
