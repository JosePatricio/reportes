/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.CabeceraCatalogoReporteDAO;
import com.innovaciones.reporte.model.CabeceraCatalogoReporte;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pisama
 */
@Service
@ManagedBean(name = "cabeceraCatalogoReporteService")
@ViewScoped
public class CabeceraCatalogoReporteServiceImpl implements CabeceraCatalogoReporteService, Serializable {

    @Setter
    private CabeceraCatalogoReporteDAO cabeceraCatalogoReporteDAO;

    @Transactional
    @Override
    public List<CabeceraCatalogoReporte> getCabeceraCatalogoReportesByTipo(String tipo) {
        return cabeceraCatalogoReporteDAO.getCabeceraCatalogoReportesByTipo(tipo);
    }

    @Override
    @Transactional
    public List<CabeceraCatalogoReporte> getCabeceraCatalogoReportes() {
       return cabeceraCatalogoReporteDAO.getCabeceraCatalogoReportes();
    }

}
