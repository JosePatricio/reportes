/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.DetalleCatalogoReporteBean;
import com.innovaciones.reporte.beans.ModeloBean;
import com.innovaciones.reporte.beans.RepuestoModeloBean;
import com.innovaciones.reporte.model.CabeceraCatalogoReporte;
import com.innovaciones.reporte.model.DetalleCatalogoReporte;
import com.innovaciones.reporte.model.Modelo;
import com.innovaciones.reporte.model.RepuestoModelo;
import com.innovaciones.reporte.service.CabeceraCatalogoReporteService;
import com.innovaciones.reporte.service.DetalleCatalogoReporteService;
import com.innovaciones.reporte.service.ModeloService;
import com.innovaciones.reporte.service.RepuestoModeloService;
import com.innovaciones.reporte.util.EstadosEnum;
import com.innovaciones.reporte.util.Utilities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Fernando
 */
@ManagedBean
@ViewScoped
public class RepuestoController extends Utilities implements Serializable {

    /**
     * Creates a new instance of RepuestoController
     */
    @Getter
    @Setter
    @ManagedProperty("#{detalleCatalogoReporteService}")
    private DetalleCatalogoReporteService detalleCatalogoReporteService;
    
    @Getter
    @Setter
    @ManagedProperty("#{cabeceraCatalogoReporteService}")
    private CabeceraCatalogoReporteService cabeceraCatalogoReporteService;
    
    @Getter
    @Setter
    @ManagedProperty("#{repuestoModeloService}")
    private RepuestoModeloService repuestoModeloService;
    
    @Getter
    @Setter
    @ManagedProperty("#{repuestoModeloBean}")
    private RepuestoModeloBean repuestoModeloBean;
    
    @Getter
    @Setter
    @ManagedProperty("#{modeloBean}")
    private ModeloBean modeloBean;
    
    @Getter
    @Setter
    @ManagedProperty("#{detalleCatalogoReporteBean}")
    private DetalleCatalogoReporteBean detalleCatalogoReporteBean;
    
    @Getter
    @Setter
    @ManagedProperty("#{modeloService}")
    private ModeloService modeloService;
    
    @Getter
    @Setter
    @ManagedProperty("#{sesionController}")
    private SesionController sesionController;
    
    public RepuestoController() {
    }
    
    @PostConstruct
    void init() {
        
        detalleCatalogoReporteBean.setDetalleCatalogoReporte(new DetalleCatalogoReporte());
        detalleCatalogoReporteBean.setListDetalleCatalogoReporte(new ArrayList<DetalleCatalogoReporte>());
        
        repuestoModeloBean.setRepuestoModelo(new RepuestoModelo());
        
        repuestoModeloBean.getRepuestoModelo().setIdDetalleCatalogoReporte(new DetalleCatalogoReporte());
        repuestoModeloBean.getRepuestoModelo().setIdModelo(new Modelo());
        detalleCatalogoReporteBean.setListDetalleCatalogoReporte(new ArrayList<DetalleCatalogoReporte>());
        modeloBean.setListModelos(new ArrayList<Modelo>());
        
        detalleCatalogoReporteBean.getDetalleCatalogoReporte().setIdCabecera(new CabeceraCatalogoReporte());
        
        detalleCatalogoReporteBean.setListDetalleCatalogoReporte(detalleCatalogoReporteService.getDetalleCatalogoReporte());
        
        detalleCatalogoReporteBean.setListEstados(cargarEstadosBoolean());
        detalleCatalogoReporteBean.setListCabeceraCatalogoReportes(cabeceraCatalogoReporteService.getCabeceraCatalogoReportes());
        
        detalleCatalogoReporteBean.setGroupSelectCatalogosMantenimiento(DetalleCatalogoReporteBean.groupSelectMantenimientos(detalleCatalogoReporteBean.getListCabeceraCatalogoReportes(), true));
        detalleCatalogoReporteBean.setGroupSelectCatalogosMantenimientoFilter(DetalleCatalogoReporteBean.groupSelectMantenimientos(detalleCatalogoReporteBean.getListCabeceraCatalogoReportes(), false));
        
    }
    
    public void abrirDialogRepuesto(DetalleCatalogoReporte repuesto) {
        
        detalleCatalogoReporteBean.setDetalleCatalogoReporte(new DetalleCatalogoReporte());
        
        if (repuesto == null) {
            detalleCatalogoReporteBean.setIsNew(Boolean.TRUE);
            detalleCatalogoReporteBean.getDetalleCatalogoReporte().setIdCabecera(new CabeceraCatalogoReporte());
            detalleCatalogoReporteBean.getDetalleCatalogoReporte().setEstado(true);
            
        } else {
            detalleCatalogoReporteBean.setIsNew(Boolean.FALSE);
            detalleCatalogoReporteBean.setDetalleCatalogoReporte(repuesto);
            detalleCatalogoReporteBean.setDetalleCatalogoReporteSelected(repuesto);
            
            detalleCatalogoReporteBean.setCambioDeVersionReparacion(Boolean.TRUE);
        }
        
        update("tblRepuestos");
        update("formEditRepuestos");
        openModalBS("dlgRepuestos");
        
    }
    
    public void actualizarStockRepuesto() {
        System.out.println("");
        System.out.println("EDITADO  " + repuestoModeloBean.getRepuestoModelo());
        repuestoModeloService.addRepuestoModelo(repuestoModeloBean.getRepuestoModelo());
        update("formModelosByRepuesto");
        closeModalBS("dlgEdicionStockReparacion");
        info("Stock actualizado");
        
    }
    
    public void guardar() {
        
        if (detalleCatalogoReporteBean.getIsNew()) {
            detalleCatalogoReporteBean.getDetalleCatalogoReporte().setFechaCreacion(new Date());
            detalleCatalogoReporteBean.getDetalleCatalogoReporte().setUsuarioCreacion(sesionController.getSesionBean().getUsuarios().getUsuario());
            
        } else {
            detalleCatalogoReporteBean.getDetalleCatalogoReporte().setFechaCreacion(new Date());
            detalleCatalogoReporteBean.getDetalleCatalogoReporte().setUsuarioCreacion(sesionController.getSesionBean().getUsuarios().getUsuario());
        }
        
        detalleCatalogoReporteBean.getDetalleCatalogoReporte().setCatalogo(detalleCatalogoReporteBean.getDetalleCatalogoReporte().getIdCabecera().getId() != 10);
        detalleCatalogoReporteService.saveOrUpdateDetalleCatalogoReporte(detalleCatalogoReporteBean.getDetalleCatalogoReporte());
        detalleCatalogoReporteBean.setListDetalleCatalogoReporte(detalleCatalogoReporteService.getDetalleCatalogoReporte());
        
        detalleCatalogoReporteBean.setDetalleCatalogoReporte(new DetalleCatalogoReporte());
        detalleCatalogoReporteBean.getDetalleCatalogoReporte().setIdCabecera(new CabeceraCatalogoReporte());
        info("Guardado exitósamente");
        update("tblRepuestos");
        cerrarDialog();
    }
    
    public void verModelosByRepuesto(DetalleCatalogoReporte repuesto) {
        detalleCatalogoReporteBean.setDetalleCatalogoReporteSelected(repuesto);
        detalleCatalogoReporteBean.setListRepuestoModelosByIdRepuesto(repuestoModeloService.getRepuestoModeloByIdRepuesto(repuesto.getId()));
        update("tblRepuestos");
        update("formModelosByRepuesto");
        openModalBS("dlgModeloByRepuesto");
    }
    
    public void abrirModalEdicionStockCodigo(RepuestoModelo repuestoModelo) {
        
        repuestoModeloBean.setRepuestoModelo(repuestoModelo);
        update("formEdicionStockReparacion");
        openModalBS("dlgEdicionStockReparacion");
    }
    
    public void abrirModalRepuestoModelo() {
        repuestoModeloBean.setRepuestoModelo(new RepuestoModelo());
        repuestoModeloBean.getRepuestoModelo().setEstado(EstadosEnum.ACTIVO.getValue());
        repuestoModeloBean.getRepuestoModelo().setIdModelo(new Modelo());
        modeloBean.setListModelos(new ArrayList<Modelo>());
        
        modeloBean.setListModelos(modeloService.getModelos());
        update("formModelosRepuesto");
        openModalBS("dlgModeloRepuesto");
    }
    
    public void guardarRepuestoModelo() {
        
        repuestoModeloBean.getRepuestoModelo().setIdDetalleCatalogoReporte(detalleCatalogoReporteBean.getDetalleCatalogoReporteSelected());
        repuestoModeloBean.getRepuestoModelo().setFechaCreacion(new Date());
        repuestoModeloBean.getRepuestoModelo().setUsuarioCreacion(sesionController.getSesionBean().getUsuarios().getNombreCompleto());
        repuestoModeloBean.getRepuestoModelo().setEsCatalogo(Boolean.FALSE);
        System.out.println("EL REPUESTO MODELO ES " + repuestoModeloBean.getRepuestoModelo());
        
        repuestoModeloService.addRepuestoModelo(repuestoModeloBean.getRepuestoModelo());
        detalleCatalogoReporteBean.setListRepuestoModelosByIdRepuesto(repuestoModeloService.getRepuestoModeloByIdRepuesto(detalleCatalogoReporteBean.getDetalleCatalogoReporteSelected().getId()));
        
        closeModalBS("dlgModeloRepuesto");
        update("formModelosByRepuesto");
        
    }
    
    public void cerrarDialogModeloByRepuesto() {
        closeModalBS("dlgModeloByRepuesto");
    }
    
    public void cerrarDialog() {
        closeModalBS("dlgRepuestos");
    }
}
