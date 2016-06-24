/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.TipoVisitaDAO;
import com.innovaciones.reporte.model.TipoVisita;
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
@ManagedBean(name = "tipoVisitaService")
@ViewScoped
public class TipoVisitaServiceImpl implements TipoVisitaService,Serializable {

 
    private TipoVisitaDAO tipoVisitaDAO;

    @Override
    @Transactional
    public List<TipoVisita> getAllTipoVisitas() {
        return tipoVisitaDAO.getAllTipoVisitas();
    }

    public void setTipoVisitaDAO(TipoVisitaDAO tipoVisitaDAO) {
        this.tipoVisitaDAO = tipoVisitaDAO;
    }

    
    
}
