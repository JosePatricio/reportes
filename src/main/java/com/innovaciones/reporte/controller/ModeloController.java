/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.DetalleCatalogoReporteBean;
import com.innovaciones.reporte.beans.ModeloBean;
import com.innovaciones.reporte.beans.RepuestoModeloBean;
import com.innovaciones.reporte.model.DetalleCatalogoReporte;
import com.innovaciones.reporte.model.Marca;
import com.innovaciones.reporte.model.Modelo;
import com.innovaciones.reporte.model.RepuestoModelo;
import com.innovaciones.reporte.service.DetalleCatalogoReporteService;
import com.innovaciones.reporte.service.MarcaService;
import com.innovaciones.reporte.service.ModeloService;
import com.innovaciones.reporte.service.RepuestoModeloService;
import com.innovaciones.reporte.util.EstadosEnum;
import com.innovaciones.reporte.util.Utilities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
@ManagedBean(name = "modeloController")
@ViewScoped
public class ModeloController extends Utilities implements Serializable {

    @Getter
    @Setter
    @ManagedProperty("#{modeloService}")
    private ModeloService modeloService;

    @Getter
    @Setter
    @ManagedProperty("#{marcaService}")
    private MarcaService marcaService;

    @Getter
    @Setter
    @ManagedProperty("#{repuestoModeloService}")
    private RepuestoModeloService repuestoModeloService;

    @Getter
    @Setter
    @ManagedProperty("#{detalleCatalogoReporteService}")
    private DetalleCatalogoReporteService detalleCatalogoReporteService;

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
    @ManagedProperty("#{repuestoModeloBean}")
    private RepuestoModeloBean repuestoModeloBean;

    @Getter
    @Setter
    @ManagedProperty("#{sesionController}")
    private SesionController sesionController;

    /**
     * Creates a new instance of UsuariosController
     */
    public ModeloController() {
    }

    @PostConstruct
    void init() {
        modeloBean.setListModelos(modeloService.getModelos());
        modeloBean.setListEstados(cargarEstadosBoolean());
        modeloBean.setListMarcas(marcaService.getMarcasByEstado(EstadosEnum.ACTIVO.getValue()));
        modeloBean.setListDetalleCatalogoReportes(detalleCatalogoReporteService.getDetalleCatalogoReporteByEstado(EstadosEnum.ACTIVO.getValue()));
        modeloBean.setIdMarca(null);
        modeloBean.setListRepuestoModelo(new ArrayList<RepuestoModelo>());
        modeloBean.setModelo(new Modelo());
        modeloBean.getModelo().setIdMarca(new Marca());
        repuestoModeloBean.setListRepuestoModelo(repuestoModeloService.getRepuestoModelos());
        repuestoModeloBean.setRepuestoModelo(new RepuestoModelo());
        repuestoModeloBean.setCodigoRepuesto(new String());

    }

    public void abrirDialogModeloRepuesto(RepuestoModelo modelo) {

        repuestoModeloBean.setRepuestoModelo(modelo);
        update("idFormRepuestoModelo");
        openModalBS("dlgModelosRepuestos");
    }

    public void abrirDialogModelo(Modelo modelo) {

        modeloBean.setListRespuestoModelosByModelo(new ArrayList<RepuestoModelo>());

        if (modelo == null) {
            modeloBean.setIdMarca(null);

            modeloBean.setModelo(new Modelo());
            modeloBean.setEstado(EstadosEnum.ACTIVO.getValue());
            //   modeloBean.setHabilitarCampoStock(false);
        } else {
            modeloBean.setListRespuestoModelosByModelo(repuestoModeloService.getRepuestoModeloByIdModelo(modelo.getId()));
            modeloBean.setModelo(modelo);
            modeloBean.setModeloSelected(modelo);
            modeloBean.setIdMarca(modelo.getIdMarca().getId());
            modeloBean.setEstado(modelo.getEstado());
            // modeloBean.setHabilitarCampoStock(true);
        }
        modeloBean.setTabAsignacion(modeloBean.getIdMarca() == null);

        update("formEditModelos");
        update("tblModelos");
        openModalBS("dlgModelos");

    }

    private boolean verificarRepuestoRepetido(String codigoRepuesto) {

        Boolean existe = Boolean.FALSE;
        for (RepuestoModelo repuestoModelo : modeloBean.getListRespuestoModelosByModelo()) {
            if (codigoRepuesto.equals(repuestoModelo.getCodigoRepuesto())) {
                existe = Boolean.TRUE;
                break;
            }
        }
        return existe;
    }

    public void agregarRepuesto() {
        RepuestoModelo repuestoModelo = new RepuestoModelo();
        repuestoModelo.setIdDetalleCatalogoReporte(new DetalleCatalogoReporte());

        if (repuestoModeloBean.getCodigoRepuesto() == null || repuestoModeloBean.getCodigoRepuesto().isEmpty()) {
            warn("Ingrese Código de repuesto");
            return;
        }

        if (repuestoModeloBean.getStockRepuesto() == null || repuestoModeloBean.getStockRepuesto() < 0) {
            warn("Ingrese Stock de repuesto");
            return;
        }

        if (!verificarRepuestoRepetido(repuestoModeloBean.getCodigoRepuesto())) {

            repuestoModelo.setIdDetalleCatalogoReporte(detalleCatalogoReporteBean.getDetalleCatalogoReporteSelected());

           
            repuestoModelo.setStock(repuestoModeloBean.getStockRepuesto());
            repuestoModelo.setCodigoRepuesto(repuestoModeloBean.getCodigoRepuesto());
            repuestoModelo.setUsuarioCreacion(sesionController.getSesionBean().getUsuarios().getNombreCompleto());
            repuestoModelo.setFechaCreacion(new Date());

            repuestoModeloBean.setStockRepuesto(new Integer(0));
            repuestoModeloBean.setCodigoRepuesto(new String());
         
            
            modeloBean.getListRespuestoModelosByModelo().add((RepuestoModelo) toUpperCaseStrings(repuestoModelo));
            repuestoModelo.setEstado(EstadosEnum.ACTIVO.getValue());
            closeModalBS("dlgCodigoStock");
            info("Repuesto agregado existosamente");
        } else {
            warn("El repuesto ya fue agregado");
        }
    }

    public void abrirModalRepuestoSeleccionado(DetalleCatalogoReporte repuesto) {

        detalleCatalogoReporteBean.setDetalleCatalogoReporte(new DetalleCatalogoReporte());
        detalleCatalogoReporteBean.setDetalleCatalogoReporteSelected(new DetalleCatalogoReporte());
        detalleCatalogoReporteBean.setDetalleCatalogoReporteSelected(repuesto);

        modeloBean.getModelo().setIdMarca(new Marca());
        modeloBean.getModelo().getIdMarca().setId(+modeloBean.getIdMarca());
        modeloBean.setHabilitarCampoStock(true);
        repuestoModeloBean.setStockRepuesto(0);
        filtrarCodigos();
        
        for (Marca marca : modeloBean.getListMarcas()) {
            if (marca.getId() == modeloBean.getIdMarca()) {
                repuestoModeloBean.setMarcaRepuesto(marca.getMarca());
                break;
            }
        }

        update("idFormCodigoStock");

        openModalBS("dlgCodigoStock");

    }

    public void buscarRepuestoModeloPorCodigo(){
    
        System.out.println("el reputos  cpdigo es "+repuestoModeloBean.getRepuestoModelo().getId());
        repuestoModeloBean.setRepuestoModeloSelected(repuestoModeloService.getRepuestoModeloById(repuestoModeloBean.getRepuestoModelo().getId()));
        
        repuestoModeloBean.setStockRepuesto(repuestoModeloBean.getRepuestoModeloSelected().getStock());
        repuestoModeloBean.setCodigoRepuesto(repuestoModeloBean.getRepuestoModeloSelected().getCodigoRepuesto());
        
    }
    
    public void eliminarRepuesto(RepuestoModelo repuestoModelo) {
        modeloBean.getListRespuestoModelosByModelo().remove(repuestoModelo);
        info("Repuesto eliminado existosamente");
        //update("formEditModelos");
    }

    public void guardar() {
        modeloBean.getModelo().setEstado(modeloBean.getEstado());
        modeloBean.getModelo().setIdMarca(new Marca(modeloBean.getIdMarca()));

        modeloBean.setModelo((Modelo) toUpperCaseStrings(modeloBean.getModelo()));

        modeloService.addModelo(modeloBean.getModelo());
        repuestoModeloService.addRepuestosModelo(modeloBean.getModelo(), modeloBean.getListRespuestoModelosByModelo());
        modeloBean.setListModelos(modeloService.getModelos());
        update("tblModelos");
        info("Guardado exitósamente");
        cerrarDialog();
    }

    public void cerrarDialog() {
        closeModalBS("dlgModelos");
    }

    public void filtrarCodigos() {

        System.out.println("EL ID MARCA " + modeloBean.getModelo().getIdMarca().getId());
        if (modeloBean.getModelo().getIdMarca().getId() != null) {
            repuestoModeloBean.setListRepuestoModelo(repuestoModeloService.getRepuestoModeloByIdMarca(modeloBean.getModelo().getIdMarca().getId()));
            repuestoModeloBean.setCodigosRepuestoModelo(new HashMap<String, String>());
            for (RepuestoModelo repuestoModelo : repuestoModeloBean.getListRepuestoModelo()) {
                if (repuestoModelo.getCodigoRepuesto() != null) {
                    repuestoModeloBean.getCodigosRepuestoModelo().put(repuestoModelo.getCodigoRepuesto(), repuestoModelo.getIdDetalleCatalogoReporte().getId().toString());
                }
            }
            update("idFormCodigoStock");
        }
    }

    public void cambioMarcaEvento() {

        if (modeloBean.getIdMarca() == null) {
            modeloBean.setTabAsignacion(Boolean.TRUE);
        } else {
            modeloBean.setTabAsignacion(Boolean.FALSE);

        }

        update("formEditModelos");
    }

}
