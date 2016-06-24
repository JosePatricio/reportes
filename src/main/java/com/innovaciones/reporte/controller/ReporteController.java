/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.AsignacionReparacionBean;
import com.innovaciones.reporte.beans.ConsultasBean;
import com.innovaciones.reporte.beans.DetalleCatalogoReporteBean;
import com.innovaciones.reporte.beans.ReporteBean;
import com.innovaciones.reporte.beans.RepuestoModeloBean;
import com.innovaciones.reporte.model.AsignacionReparacion;
import com.innovaciones.reporte.model.CabeceraCatalogoReporte;
import com.innovaciones.reporte.model.Cliente;
import com.innovaciones.reporte.model.DTO.NotificacionDTO;
import com.innovaciones.reporte.model.DetalleCatalogoReporte;
import com.innovaciones.reporte.model.Marca;
import com.innovaciones.reporte.model.Modelo;
import com.innovaciones.reporte.model.Producto;
import com.innovaciones.reporte.model.ProductoCliente;
import com.innovaciones.reporte.model.ProductoClienteReporte;
import com.innovaciones.reporte.model.ProductoDetalleReporte;
import com.innovaciones.reporte.model.Reporte;
import com.innovaciones.reporte.model.TipoVisita;
import com.innovaciones.reporte.model.ReporteMantenimiento;
import com.innovaciones.reporte.model.DTO.ReportesDTO;
import com.innovaciones.reporte.model.RepuestoModelo;
import com.innovaciones.reporte.service.AsignacionReparacionService;
import com.innovaciones.reporte.service.CabeceraCatalogoReporteService;
import com.innovaciones.reporte.service.ClienteService;
import com.innovaciones.reporte.service.DetalleCatalogoReporteService;
import com.innovaciones.reporte.service.MarcaService;
import com.innovaciones.reporte.service.ModeloService;
import com.innovaciones.reporte.service.ProductoClienteReporteService;
import com.innovaciones.reporte.service.ProductoClienteService;
import com.innovaciones.reporte.service.ProductoDetalleReporteService;
import com.innovaciones.reporte.service.ProductoService;
import com.innovaciones.reporte.service.ReporteMantenimientoService;
import com.innovaciones.reporte.service.ReporteService;
import com.innovaciones.reporte.service.TipoVisitaService;
import com.innovaciones.reporte.util.Enums;
import com.innovaciones.reporte.util.Utilities;
import com.innovaciones.reporte.service.ConsultasService;
import com.innovaciones.reporte.service.RepuestoModeloService;
import com.innovaciones.reporte.util.EstadosEnum;
import com.innovaciones.reporte.util.ReporteTecnico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author pisama
 */
@ManagedBean(name = "reporteController")
@ViewScoped
public class ReporteController extends Utilities implements Serializable {
    
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
    @ManagedProperty("#{tipoVisitaService}")
    private TipoVisitaService tipoVisitaService;
    
    @Getter
    @Setter
    @ManagedProperty("#{productoClienteService}")
    private ProductoClienteService productoClienteService;
    
    @Getter
    @Setter
    @ManagedProperty("#{productoClienteReporteService}")
    private ProductoClienteReporteService productoClienteReporteService;
    
    @Getter
    @Setter
    @ManagedProperty("#{productoDetalleReporteService}")
    private ProductoDetalleReporteService productoDetalleReporteService;
    
    @Getter
    @Setter
    @ManagedProperty("#{reporteService}")
    private ReporteService reporteService;
    
    @Getter
    @Setter
    @ManagedProperty("#{modeloService}")
    private ModeloService modeloService;
    
    @Getter
    @Setter
    @ManagedProperty("#{clienteService}")
    private ClienteService clienteService;
    
    @Getter
    @Setter
    @ManagedProperty("#{marcaService}")
    private MarcaService marcaService;
    
    @Getter
    @Setter
    @ManagedProperty("#{productoService}")
    private ProductoService productoService;
    
    @Getter
    @Setter
    @ManagedProperty("#{reporteMantenimientoService}")
    private ReporteMantenimientoService reporteMantenimientoService;
    
    @Getter
    @Setter
    @ManagedProperty("#{reporteBean}")
    private ReporteBean reporteBean;
    
    @Getter
    @Setter
    @ManagedProperty("#{consultasService}")
    private ConsultasService consultasService;
    
    @Getter
    @Setter
    @ManagedProperty("#{consultasBean}")
    private ConsultasBean consultasBean;
    
    @Getter
    @Setter
    @ManagedProperty("#{sesionController}")
    private SesionController sesionController;
    
    @Getter
    @Setter
    @ManagedProperty("#{detalleCatalogoReporteBean}")
    private DetalleCatalogoReporteBean detalleCatalogoReporteBean;
    
    @Getter
    @Setter
    @ManagedProperty("#{asignacionReparacionService}")
    private AsignacionReparacionService asignacionReparacionService;
    
    @Getter
    @Setter
    @ManagedProperty("#{asignacionReparacionBean}")
    private AsignacionReparacionBean asignacionReparacionBean;
    
    @Getter
    @Setter
    @ManagedProperty("#{repuestoModeloService}")
    private RepuestoModeloService repuestoModeloService;
    
    @Getter
    @Setter
    @ManagedProperty("#{repuestoModeloBean}")
    private RepuestoModeloBean repuestoModeloBean;
    
    public void llenarDatos() {
        
        reporteBean.getReporte().setFactura("255555");
        
        reporteBean.getReporte().setReferencia("referr");
        reporteBean.setCliente(clienteService.getClienteByRuc("1791983580001"));
        reporteBean.getProductoClienteReporte().setAtencion("Atencjion  ");
        reporteBean.getProductoClienteReporte().setCiudad("QUITO  ");
        reporteBean.getProductoClienteReporte().setIpEquipo("192.16.18.156");
        reporteBean.getProductoClienteReporte().setDepartamento("SISTEMAS");
        reporteBean.getProductoClienteReporte().setDireccionEquipo("DIRECIO PRUWEBA");
        reporteBean.getProductoClienteReporte().setTelefonoEquipo("00454564425");
        reporteBean.getProductoClienteReporte().setCorreoEquipo("COM");
        
        reporteBean.getProductoCliente().setSerie(java.util.UUID.randomUUID().toString().substring(1, 10));
        reporteBean.setProducto(productoService.getProductoById(12));
        reporteBean.getProducto().setVersionFirmware("VE54545");
        reporteBean.setModelo(reporteBean.getProducto().getIdModelo());
        reporteBean.setMarca(reporteBean.getModelo().getIdMarca());
        reporteBean.setIdTipoVisita(1);
        reporteBean.getReporte().setNombreCliente("NOMBRE");
        
    }
    
    @PostConstruct
    private void init() {
        
        NotificacionDTO notificacionDTO = (NotificacionDTO) getValueInSession("notificacionSeleccionada");
        
        if (notificacionDTO != null) {
            
            this.inicializarBeans();
            asignacionReparacionBean.setAsignacionReparacionSelected(asignacionReparacionService.getAsignacionReparacionById(notificacionDTO.getId()));
            
            reporteBean.setProductoClienteReporteSelected(new ProductoClienteReporte());
            reporteBean.setProductoClienteReporteSelected(productoClienteReporteService.getProductoClienteReporteByReportId(notificacionDTO.getIdClienteProducto()));
            sesionController.getSesionBean().setIsReporteEdit(Boolean.FALSE);
            
            if (reporteBean.getProductoClienteReporteSelected() != null) {
                reporteBean.setProductoClienteReporte(reporteBean.getProductoClienteReporteSelected());
                this.llenarReporte();
            } else {
                reporteBean.setProductoCliente(productoClienteService.getProductoClienteById(notificacionDTO.getIdClienteProducto()));
                reporteBean.setCliente(reporteBean.getProductoCliente().getIdCliente());
                reporteBean.setProducto(reporteBean.getProductoCliente().getIdProducto());
                reporteBean.setModelo(reporteBean.getProducto().getIdModelo());
                reporteBean.setMarca(reporteBean.getModelo().getIdMarca());
                
            }
            
            reporteBean.setUsuarios(sesionController.getSesionBean().getUsuarios());
            reporteBean.setNumeroFacturaTecnico(this.numeroReporte(notificacionDTO.getTipoReporte()));
            reporteBean.setReporteActual(numeroFactura(reporteBean.getNumeroFacturaTecnico() + 1));
            this.llenarCatalogos();
            
            reporteBean.setEstadoActualMantenimiento(Enums.PREVENTIVO.getPropertyName());
            reporteBean.getReporte().setTipo(notificacionDTO.getTipoReporte());
            reporteBean.setHabilitarGuardarConSerial((NotificacionDTO) getValueInSession("notificacionSeleccionada") == null);
            
        } else {
            reporteBean.setHabilitarGuardarConSerial((NotificacionDTO) getValueInSession("notificacionSeleccionada") == null);
        }
        
        if (getIdReporteSession() != 0) {
            reporteBean.setDisable(sesionController.getSesionBean().getIsReporteEdit());
            this.llenarReportePorId(getIdReporteSession());
            
            reporteBean.setNumeroFacturaTecnico(this.numeroReporte(reporteBean.getReporte().getTipo()));
            reporteBean.setReporteActual(numeroFactura(reporteBean.getReporte().getNumerofactura()));
            
        } else if (!getSessionText(Enums.PARAMETRO_TIPO_REPORTE.getValue()).isEmpty()) {
            
            reporteBean.setNumeroFacturaTecnico(this.numeroReporte(getSessionText(Enums.PARAMETRO_TIPO_REPORTE.getValue())));

            //  openModalBS("dlgFiltros");
            this.inicializarBeans();
            
            reporteBean.setUsuarios(sesionController.getSesionBean().getUsuarios());
            reporteBean.setReporteActual(numeroFactura(reporteBean.getNumeroFacturaTecnico() + 1));
            this.llenarCatalogos();
            reporteBean.setEstadoActualMantenimiento(Enums.PREVENTIVO.getPropertyName());
            reporteBean.getReporte().setSubtipo(sesionController.getSesionBean().getIsSubReporteTipo());
            reporteBean.getReporte().setTipo(sesionController.getSesionBean().getIsReporteTipo());
            
            if (getSessionText(Enums.PARAMETRO_TIPO_REPORTE.getValue()).equals(Enums.TIPO_REPORTE_CONTADORES.getValue())) {
                reporteBean.setIdTipoVisita(5);
            }
            
            this.llenarDatos();
            
        } else if (getSessionText(Enums.PARAMETRO_TIPO_REPORTE.getValue()).isEmpty() && Enums.PAGINA_REPORTE_NUEVA_INSTALACION.getValue().equals(getCurrentPage())) {
            
            redireccionar("paginas/menu/menu-principal.jsf");
        }
        
    }
    
    protected Integer numeroReporte(String subtipo) {
        return reporteService.getReporteByUserByTipo(sesionController.getSesionBean().getUsuarios(), Enums.REPORTE.getValue(), subtipo).size();
    }
    
    protected Integer numeroReporte(Boolean subtipo) {
        if (subtipo) {
            return reporteService.getReporteByUserByTipo(sesionController.getSesionBean().getUsuarios(), Enums.REPORTE.getValue(), Enums.TIPO_REPORTE_REPARACION.getValue()).size();
        }
        return reporteService.getReporteByUserByTipo(sesionController.getSesionBean().getUsuarios(), Enums.REPORTE.getValue(), Enums.TIPO_REPORTE_DIAGNOSTICO.getValue()).size();
    }
    
    public void rowDblSelectRepuesto(SelectEvent event) {
        RepuestoModelo seleccion = new RepuestoModelo();
        seleccion = (RepuestoModelo) event.getObject();
        seleccion.setSeleccion(Boolean.TRUE);
        
        if (seleccion.getStock() == null || seleccion.getStock() == 0) {
            warn("No hay en Stock para el Repuesto " + seleccion.getIdDetalleCatalogoReporte().getDescripcion() + " (" + seleccion.getCodigoRepuesto() + ")");
            //    return;
        }
        
        if (seleccion.getCodigoRepuesto() == null || seleccion.getCodigoRepuesto().isEmpty()) {
            warn("No tiene Código");
            return;
        }
        
        if (seleccion.getIdDetalleCatalogoReporte().getIdCabecera().getCodigo().equals(Enums.MANTENIMIENTO_SUMINISTROS.getValue())) {
            reporteBean.setListCorrectivoSuministros(repuestosCodigoActualizado(reporteBean.getListCorrectivoSuministros(), seleccion));
            update("accordionmantenimiento:idDatatableProcesamiento");
        }
        
        if (seleccion.getIdDetalleCatalogoReporte().getIdCabecera().getCodigo().equals(Enums.MANTENIMIENTO_CORRECTIVO_IMAGEN.getValue())) {
            reporteBean.setListCorrectivoImagen(repuestosCodigoActualizado(reporteBean.getListCorrectivoImagen(), seleccion));
            update("accordionmantenimiento:idDatatableImagen");
        }
        
        if (seleccion.getIdDetalleCatalogoReporte().getIdCabecera().getCodigo().equals(Enums.MANTENIMIENTO_CORRECTIVO_FIJACION.getValue())) {
            reporteBean.setListCorrectivoFijacion(repuestosCodigoActualizado(reporteBean.getListCorrectivoFijacion(), seleccion));
            update("accordionmantenimiento:idDatatableFijacion");
        }
        
        if (seleccion.getIdDetalleCatalogoReporte().getIdCabecera().getCodigo().equals(Enums.MANTENIMIENTO_CORRECTIVO_REVELADO.getValue())) {
            reporteBean.setListCorrectivoRevelado(repuestosCodigoActualizado(reporteBean.getListCorrectivoRevelado(), seleccion));
            update("accordionmantenimiento:idDatatableRevelado");
        }
        
        if (seleccion.getIdDetalleCatalogoReporte().getIdCabecera().getCodigo().equals(Enums.MANTENIMIENTO_ALIMENTACION.getValue())) {
            reporteBean.setListCorrectivoAlimentacion(repuestosCodigoActualizado(reporteBean.getListCorrectivoAlimentacion(), seleccion));
            update("accordionmantenimiento:idDatatableAlimentacion");
        }
        
        if (seleccion.getIdDetalleCatalogoReporte().getIdCabecera().getCodigo().equals(Enums.MANTENIMIENTO_OTROS.getValue())) {
            reporteBean.setListCorrectivoOtros(repuestosCodigoActualizado(reporteBean.getListCorrectivoOtros(), seleccion));
            update("accordionmantenimiento:idDatatableOtros");
        }
        
        closeModalBS("dlgCorrectivosSuministros");
    }
    
    public void cargarVersionesPorRepuestoPorModelo(DetalleCatalogoReporte catalogoReporte) {
        
        if (reporteBean.getModelo().getId() != null) {
            repuestoModeloBean.setListRepuestoModelo(repuestoModeloService.getIdModeloByIdRepuestoByRepuesto(reporteBean.getModelo().getId(), catalogoReporte.getId(), catalogoReporte.getDescripcion()));
            
            if (!repuestoModeloBean.getListRepuestoModelo().isEmpty() && repuestoModeloBean.getListRepuestoModelo().size() >= 1) {
                detalleCatalogoReporteBean.setModalLabel(catalogoReporte.getDescripcion());
                
                reporteBean.setListRepuestosHistorial(repuestoModeloBean.getListRepuestoModelo());
                
                update("formCorrectivosSuministros");
                openModalBS("dlgCorrectivosSuministros");
            }
        }
        
    }
    
    public void actualizarListaRadioButton(DetalleCatalogoReporte catalogoReporte, String grupo) {
        
        this.cargarVersionesPorRepuestoPorModelo(catalogoReporte);
        
        if (grupo.equals(Enums.MANTENIMIENTO_SUMINISTROS.getValue())) {
            reporteBean.setListCorrectivoSuministros(listaDeRadioButton(catalogoReporte, reporteBean.getListCorrectivoSuministros()));
            update("accordionmantenimiento:idDatatableProcesamiento");
        }
        
        if (grupo.equals(Enums.MANTENIMIENTO_CORRECTIVO_IMAGEN.getValue())) {
            reporteBean.setListCorrectivoImagen(listaDeRadioButton(catalogoReporte, reporteBean.getListCorrectivoImagen()));
            update("accordionmantenimiento:idDatatableImagen");
        }
        
        if (grupo.equals(Enums.MANTENIMIENTO_CORRECTIVO_FIJACION.getValue())) {
            reporteBean.setListCorrectivoFijacion(listaDeRadioButton(catalogoReporte, reporteBean.getListCorrectivoFijacion()));
            update("accordionmantenimiento:idDatatableFijacion");
        }
        
        if (grupo.equals(Enums.MANTENIMIENTO_CORRECTIVO_REVELADO.getValue())) {
            reporteBean.setListCorrectivoRevelado(listaDeRadioButton(catalogoReporte, reporteBean.getListCorrectivoRevelado()));
            update("accordionmantenimiento:idDatatableRevelado");
        }
        
        if (grupo.equals(Enums.MANTENIMIENTO_ALIMENTACION.getValue())) {
            reporteBean.setListCorrectivoAlimentacion(listaDeRadioButton(catalogoReporte, reporteBean.getListCorrectivoAlimentacion()));
            update("accordionmantenimiento:idDatatableAlimentacion");
        }
        
        if (grupo.equals(Enums.MANTENIMIENTO_OTROS.getValue())) {
            reporteBean.setListCorrectivoOtros(listaDeRadioButton(catalogoReporte, reporteBean.getListCorrectivoOtros()));
            update("accordionmantenimiento:idDatatableOtros");
        }
        
    }
    
    public void actualizarListaTextfield(DetalleCatalogoReporte catalogoReporte, String grupo) {
        
        if (grupo.equals(Enums.MANTENIMIENTO_OTROS.getValue())) {
            reporteBean.setListCorrectivoOtros(listaDeTextField(catalogoReporte, reporteBean.getListCorrectivoOtros(), reporteBean.getModelo()));
            update("accordionmantenimiento:idDatatableOtros");
        }
        if (grupo.equals(Enums.MANTENIMIENTO_PROCESAMIENTO.getValue())) {
            reporteBean.setListProcesamiento(listaDeTextField(catalogoReporte, reporteBean.getListProcesamiento(), reporteBean.getModelo()));
            update("accordionmantenimiento:idDatatableProcesamiento");
        }
        if (grupo.equals(Enums.MANTENIMIENTO_CORRECTIVO_IMAGEN.getValue())) {
            reporteBean.setListCorrectivoImagen(listaDeTextField(catalogoReporte, reporteBean.getListCorrectivoImagen(), reporteBean.getModelo()));
            update("accordionmantenimiento:idDatatableImagen");
        }
        
        if (grupo.equals(Enums.MANTENIMIENTO_CORRECTIVO_FIJACION.getValue())) {
            reporteBean.setListCorrectivoFijacion(listaDeTextField(catalogoReporte, reporteBean.getListCorrectivoFijacion(), reporteBean.getModelo()));
            update("accordionmantenimiento:idDatatableFijacion");
        }
        
        if (grupo.equals(Enums.MANTENIMIENTO_CORRECTIVO_REVELADO.getValue())) {
            reporteBean.setListCorrectivoRevelado(listaDeTextField(catalogoReporte, reporteBean.getListCorrectivoRevelado(), reporteBean.getModelo()));
            update("accordionmantenimiento:idDatatableRevelado");
        }
        if (grupo.equals(Enums.MANTENIMIENTO_ALIMENTACION.getValue())) {
            reporteBean.setListCorrectivoAlimentacion(listaDeTextField(catalogoReporte, reporteBean.getListCorrectivoAlimentacion(), reporteBean.getModelo()));
            update("accordionmantenimiento:idDatatableAlimentacion");
        }
    }
    
    public void resetMantenimiento(DetalleCatalogoReporte catalogoReporte, String grupo) {
        if (grupo.equals(Enums.MANTENIMIENTO_OTROS.getValue())) {
            reporteBean.setListCorrectivoOtros(listaDeReset(catalogoReporte, reporteBean.getListCorrectivoOtros()));
        }
        
        if (grupo.equals(Enums.MANTENIMIENTO_PROCESAMIENTO.getValue())) {
            reporteBean.setListProcesamiento(listaDeReset(catalogoReporte, reporteBean.getListProcesamiento()));
        }
        
        if (grupo.equals(Enums.MANTENIMIENTO_CORRECTIVO_IMAGEN.getValue())) {
            reporteBean.setListCorrectivoImagen(listaDeReset(catalogoReporte, reporteBean.getListCorrectivoImagen()));
        }
        
        if (grupo.equals(Enums.MANTENIMIENTO_CORRECTIVO_FIJACION.getValue())) {
            reporteBean.setListCorrectivoFijacion(listaDeReset(catalogoReporte, reporteBean.getListCorrectivoFijacion()));
        }
        
        if (grupo.equals(Enums.MANTENIMIENTO_CORRECTIVO_REVELADO.getValue())) {
            reporteBean.setListCorrectivoRevelado(listaDeReset(catalogoReporte, reporteBean.getListCorrectivoRevelado()));
        }
        if (grupo.equals(Enums.MANTENIMIENTO_ALIMENTACION.getValue())) {
            reporteBean.setListCorrectivoAlimentacion(listaDeReset(catalogoReporte, reporteBean.getListCorrectivoAlimentacion()));
        }
    }
    
    public void deshabilitarFirma(Boolean estado) {
        reporteBean.setEstadoFirma(estado);
    }
    
    public void llenarCamposPorFiltro() {
        
        if (!reporteBean.getFiltroSerial().isEmpty()) {
            reporteBean.setProductoClienteSelected(productoClienteService.getProductoClienteBySerial(reporteBean.getFiltroSerial().trim()));
            if (reporteBean.getProductoClienteSelected() != null) {
                reporteBean.setProductoCliente(reporteBean.getProductoClienteSelected());
                reporteBean.setCliente(reporteBean.getProductoCliente().getIdCliente());
                reporteBean.setProducto(reporteBean.getProductoCliente().getIdProducto());
                reporteBean.setModelo(reporteBean.getProducto().getIdModelo());
                reporteBean.setMarca(reporteBean.getProducto().getIdModelo().getIdMarca());
                
                reporteBean.setHabilitarGuardarConSerial(Boolean.FALSE);
            } else {
                warn("No se encontraron resultados");
            }
            
        }
        
        if (!reporteBean.getFiltroCedula().isEmpty()) {
            reporteBean.setClienteSelected(clienteService.getClienteByRuc(reporteBean.getFiltroCedula().trim()));
            if (reporteBean.getClienteSelected() != null) {
                reporteBean.setCliente(reporteBean.getClienteSelected());
            } else {
                warn("No se encontraron resultados");
            }
            
        }
        
        if (!reporteBean.getFiltroProducto().isEmpty()) {
            reporteBean.setProductoSelected(productoService.getProductoByEquipo(reporteBean.getFiltroProducto().trim()));
            if (reporteBean.getProductoSelected() != null) {
                reporteBean.setProducto(reporteBean.getProductoSelected());
                reporteBean.setModelo(reporteBean.getProducto().getIdModelo());
                reporteBean.setMarca(reporteBean.getProducto().getIdModelo().getIdMarca());
            } else {
                warn("No se encontraron resultados");
            }
            
        }
        
        closeModalBS("dlgFiltros");
        
    }
    
    public void llenarReportePorRuc() {
        consultasBean.setReportesDTOs(new ArrayList<ReportesDTO>());
        consultasBean.setReportesDTOs(consultasService.reportesPorRuc(reporteBean.getCliente().getRuc()));
        
        openDialog("dlgReportesRuc");
    }
    
    public void llenarClientePorRuc() {
        
        if (reporteBean.getCliente().getRuc() != null && !reporteBean.getCliente().getRuc().isEmpty()) {
            reporteBean.setClienteSelected(clienteService.getClienteByRuc(reporteBean.getCliente().getRuc()));
            if (reporteBean.getClienteSelected() != null) {
                reporteBean.setCliente(reporteBean.getClienteSelected());
            } else {
                info("No se encontró resultados");
            }
            
        } else {
            info("El campo ruc está vacio");
        }
    }
    
    public void llenarProductoPorSerial() {
        
        if (reporteBean.getProductoCliente().getSerie() != null && !reporteBean.getProductoCliente().getSerie().isEmpty()) {
            
            reporteBean.setProductoClienteReporteSelected(productoClienteReporteService.getProductoClienteReporteBySerial(reporteBean.getProductoCliente().getSerie()));
            
            if (reporteBean.getProductoClienteReporteSelected() != null) {
                
                reporteBean.setProductoClienteReporte(reporteBean.getProductoClienteReporteSelected());
                this.llenarReporte();
                return;
            }
            
            reporteBean.setProductoClienteSelected(productoClienteService.getProductoClienteBySerial(reporteBean.getProductoCliente().getSerie()));
            
            if (reporteBean.getProductoClienteSelected() != null) {
                reporteBean.setProductoCliente(reporteBean.getProductoClienteSelected());
                reporteBean.setProducto(reporteBean.getProductoCliente().getIdProducto());
                reporteBean.setCliente(reporteBean.getProductoCliente().getIdCliente());
                reporteBean.setModelo(reporteBean.getProducto().getIdModelo());
                reporteBean.setHabilitarGuardarConSerial(Boolean.FALSE);
                
            } else {
                info("No se encontró resultados");
            }
            
        } else {
            info("El campo serie está vacio");
        }
    }
    
    public void seleccionarDialogReporte(ReportesDTO reportesDTO) {
        
        reporteBean.setProductoClienteReporte(new ProductoClienteReporte());
        reporteBean.setProductoClienteReporte(productoClienteReporteService.getProductoClienteReporteByReportId(reportesDTO.getId()));
        
        this.llenarReporte();
        
        closeDialog("dlgReportesRuc");
    }
    
    public void llenarReportePorId(Integer id) {
        
        this.inicializarBeans();
        reporteBean.setProductoClienteReporte(new ProductoClienteReporte());
        reporteBean.setProductoClienteReporte(productoClienteReporteService.getProductoClienteReporteByReportId(id));
        
        this.llenarCatalogos();
        
        if (reporteBean.getProductoClienteReporte() != null) {
            this.llenarReporte();
        }
        
    }
    
    private void llenarReporte() {
        
        reporteBean.setProductoCliente(reporteBean.getProductoClienteReporte().getIdProductoCliente());
        
        reporteBean.setReporte(
                reporteBean.getProductoClienteReporte().
                        getIdReporte());
        
        reporteBean.setEstadoActualMantenimiento(reporteBean.getReporte().getMantenimiento());
        
        reporteBean.setReporteSelected(reporteBean.getReporte());
        
        reporteBean.setIdTipoVisita(reporteBean.getReporte().getIdVisita().getId());
        
        reporteBean.setProductoDetalleReporte(
                reporteBean.getProductoClienteReporte().
                        getIdProductoDetalleReporte());
        
        reporteBean.setCliente(
                reporteBean.getProductoClienteReporte().
                        getIdProductoCliente().getIdCliente());
        
        reporteBean.setProducto(
                reporteBean.getProductoClienteReporte()
                        .getIdProductoCliente().getIdProducto());
        
        reporteBean.setModelo(
                reporteBean.getProductoClienteReporte().
                        getIdProductoCliente().getIdProducto().getIdModelo()
        );
        
        reporteBean.setMarca(
                reporteBean.getProductoClienteReporte().
                        getIdProductoCliente().getIdProducto().getIdModelo().getIdMarca()
        );
        
        reporteBean.setUsuarios(reporteBean.getReporte().getIdUsuario());
        
        reporteBean.setListProcesamiento(llenarRepuestosPreventivos(reporteBean.getListProcesamiento(), reporteBean));
        reporteBean.setListPreventivoImagen(llenarRepuestosPreventivos(reporteBean.getListPreventivoImagen(), reporteBean));
        reporteBean.setListPreventivoFijacion(llenarRepuestosPreventivos(reporteBean.getListPreventivoFijacion(), reporteBean));
        reporteBean.setListPreventivoLimpieza(llenarRepuestosPreventivos(reporteBean.getListPreventivoLimpieza(), reporteBean));
        
        reporteBean.setListCorrectivoSuministros(llenarRepuestosCorrectivos(reporteBean.getListCorrectivoSuministros(), reporteBean));
        reporteBean.setListCorrectivoImagen(llenarRepuestosCorrectivos(reporteBean.getListCorrectivoImagen(), reporteBean));
        reporteBean.setListCorrectivoFijacion(llenarRepuestosCorrectivos(reporteBean.getListCorrectivoFijacion(), reporteBean));
        reporteBean.setListCorrectivoRevelado(llenarRepuestosCorrectivos(reporteBean.getListCorrectivoRevelado(), reporteBean));
        reporteBean.setListCorrectivoAlimentacion(llenarRepuestosCorrectivos(reporteBean.getListCorrectivoAlimentacion(), reporteBean));
        reporteBean.setListCorrectivoOtros(llenarRepuestosOtros(reporteBean.getListCorrectivoOtros(), reporteBean));
        
    }
    
    public void PDF() {
        
        reporteBean.getReporte().setFecha(new Date());
        if (getIdReporteSession() != 0) {
            llenarReportePorId(getIdReporteSession());
        }
        
        new ReporteTecnico().descargarReporte(reporteBean, Enums.REPORTE.getValue());
        
    }
    
    public void cancelar() {
        try {
            inicializarBeans();
            sesionController.getSesionBean().setIsReporteVer(Boolean.FALSE);
            sesionController.getSesionBean().setIsSubReporteTipo(null);
            sesionController.getSesionBean().setIsReporteTipo(null);
            // sesionController.getSesionBean().setIsReporteVer(null);
            killSession(Enums.PARAMETRO_TIPO_REPORTE.getValue());
            redirectToPage("/paginas/consultas.jsf");
        } catch (Exception e) {
            System.out.println("Error cancelar " + e.getMessage());
        }
    }
    
    public void guardar() {
        
        try {
            if (sesionController.getSesionBean().getIsReporteEdit()) {
                reporteBean.getReporte().setId(reporteBean.getReporteSelected().getId());
                reporteBean.getReporte().setMantenimiento(reporteBean.getReporteSelected().getMantenimiento());
                reporteBean.getReporte().setFecha(reporteBean.getReporteSelected().getFecha());
                
                reporteBean.getReporte().setEstado(reporteBean.getReporteSelected().getEstado());
                reporteBean.getReporte().setUsuarioCreacion(reporteBean.getReporteSelected().getUsuarioCreacion());
                reporteBean.getReporte().setFechaCreacion(reporteBean.getReporteSelected().getFechaCreacion());
                reporteBean.getReporte().setUsuarioModificacion(sesionController.getSesionBean().getUsuarios().getUsuario());
                reporteBean.getReporte().setFechaModificacion(new Date());
                reporteBean.getReporte().setIdVisita(new TipoVisita(reporteBean.getIdTipoVisita()));
                reporteBean.getReporte().setIdUsuario(reporteBean.getUsuarios());
                
                reporteBean.setProductoCliente(reporteBean.getProductoClienteReporte().getIdProductoCliente());
                reporteBean.getProductoCliente().setIdProducto(reporteBean.getProducto());
                reporteBean.getProductoCliente().setIdCliente(reporteBean.getCliente());
                reporteBean.setProductoCliente(productoClienteService.updateProductoCliente(reporteBean.getProductoCliente()));
                
                if (sesionController.getSesionBean().getIsReporteTipo() != null && Enums.TIPO_REPORTE_REPARACION.getValue().equals(sesionController.getSesionBean().getIsReporteTipo())) {
                    reporteBean.getReporte().setEstado(Enums.ESTADO_REPORTE_FINALIADO.getValue());
                    reporteService.updateReporte(reporteBean.getReporte());
                    
                    reporteBean.getReporte().setId(null);
                    reporteBean.getProductoDetalleReporte().setId(null);
                    reporteBean.getProductoClienteReporte().setId(null);
                    
                    reporteBean.getReporte().setFecha(new Date());
                    reporteBean.getReporte().setFechaCreacion(new Date());
                    reporteBean.getReporte().setUsuarioModificacion(null);
                    reporteBean.getReporte().setFechaModificacion(null);
                    
                    reporteBean.getReporte().setSubtipo(Enums.TIPO_REPORTE_REPARACION.getValue());
                    reporteBean.getReporte().setTipo(Enums.REPORTE.getValue());
                    reporteBean.getReporte().setEstado(Enums.ESTADO_REPORTE_PROCESO.getValue());
                    reporteBean.getReporte().setNumerofactura(reporteBean.getNumeroFacturaTecnico() + 1);
                    reporteBean.getProductoClienteReporte().setReporteMantenimientoList(new ArrayList<ReporteMantenimiento>());
                    
                    reporteBean.setReporte(reporteService.saveReporte(reporteBean.getReporte()));
                    reporteBean.getProductoClienteReporte().setIdProductoCliente(reporteBean.getProductoCliente());
                    reporteBean.getProductoClienteReporte().setIdProductoDetalleReporte(reporteBean.getProductoDetalleReporte());
                    reporteBean.getProductoClienteReporte().setIdReporte(reporteBean.getReporte());
                    reporteBean.setProductoDetalleReporte(productoDetalleReporteService.addProductoDetalleReporte(reporteBean.getProductoDetalleReporte()));
                    reporteBean.setProductoClienteReporte(productoClienteReporteService.saveProductoClienteReporte(reporteBean.getProductoClienteReporte()));
                    
                    reporteMantenimientoService.addReporteMantenimientoPreventivo(
                            reporteBean.getProductoClienteReporte(),
                            reporteBean.getListProcesamiento(),
                            reporteBean.getListPreventivoImagen(),
                            reporteBean.getListPreventivoFijacion(),
                            reporteBean.getListPreventivoLimpieza());
                    
                    reporteMantenimientoService.addReporteMantenimientoCorrectivo(reporteBean.getProductoClienteReporte(),
                            reporteBean.getListCorrectivoSuministros(),
                            reporteBean.getListCorrectivoImagen(),
                            reporteBean.getListCorrectivoFijacion(),
                            reporteBean.getListCorrectivoRevelado(),
                            reporteBean.getListCorrectivoAlimentacion(),
                            reporteBean.getListCorrectivoOtros(), sesionController.getSesionBean().getIsReporteTipo(),
                            sesionController.getSesionBean().getUsuarios());
                    
                    sesionController.getSesionBean().setIsReporteTipo(null);
                    
                } else {
                    
                    reporteBean.setProductoDetalleReporte(productoDetalleReporteService.updateProductoDetalleReporte(reporteBean.getProductoDetalleReporte()));
                    reporteBean.setReporte(reporteService.updateReporte(reporteBean.getReporte()));
                    reporteBean.getProductoClienteReporte().setIdReporte(reporteBean.getReporte());
                    reporteBean.getProductoClienteReporte().setIdProductoDetalleReporte(reporteBean.getProductoDetalleReporte());
                    reporteBean.getProductoClienteReporte().setIdProductoCliente(reporteBean.getProductoCliente());
                    
                    reporteBean.setProductoClienteReporte(productoClienteReporteService.updateProductoClienteReporte(reporteBean.getProductoClienteReporte()));
                    
                    reporteMantenimientoService.updateReporteMantenimientoCorrectivo(reporteBean.getProductoClienteReporte(),
                            reporteBean.getListCorrectivoSuministros(),
                            reporteBean.getListCorrectivoImagen(),
                            reporteBean.getListCorrectivoFijacion(),
                            reporteBean.getListCorrectivoRevelado(),
                            reporteBean.getListCorrectivoAlimentacion(),
                            reporteBean.getListCorrectivoOtros(), reporteBean.getModelo(),
                            sesionController.getSesionBean().getUsuarios());
                    reporteMantenimientoService.updateReporteMantenimientoPreventivo(
                            reporteBean.getProductoClienteReporte(),
                            reporteBean.getListProcesamiento(),
                            reporteBean.getListPreventivoImagen(),
                            reporteBean.getListPreventivoFijacion(),
                            reporteBean.getListPreventivoLimpieza());
                    
                }
            } else {
                
                if (reporteBean.getHabilitarGuardarConSerial()) {
                    
                    if (productoClienteService.getProductoClienteBySerial(reporteBean.getProductoCliente().getSerie()) != null) {
                        warn("Este serial ya existe");
                        return;
                    }
                } else {
                    setValueInSession("notificacionSeleccionada", null);
                }
                
                reporteBean.setClienteSelected(clienteService.getClienteByRuc(reporteBean.getCliente().getRuc()));
                if (reporteBean.getClienteSelected() == null) {
                    reporteBean.getCliente().setEstado(1);
                    reporteBean.setCliente(clienteService.addCliente(reporteBean.getCliente()));
                    
                } else {
                    reporteBean.setCliente(reporteBean.getClienteSelected());
                }
                
                reporteBean.setMarcaSelected(marcaService.getMarcaByMarca(reporteBean.getMarca().getMarca()));
                if (reporteBean.getMarcaSelected() == null) {
                    reporteBean.getMarca().setEstado(1);
                    reporteBean.setMarca((Marca) toUpperCaseStrings(marcaService.addMarca(reporteBean.getMarca())));
                } else {
                    reporteBean.setMarca(reporteBean.getMarcaSelected());
                }
                
                reporteBean.setModeloSelected(modeloService.getModeloByModelo(reporteBean.getModelo().getModelo()));
                if (reporteBean.getModeloSelected() == null) {
                    reporteBean.getModelo().setEstado(1);
                    reporteBean.getModelo().setIdMarca(reporteBean.getMarca());
                    reporteBean.setModelo(modeloService.addModelo((Modelo) toUpperCaseStrings(reporteBean.getModelo())));
                } else {
                    reporteBean.setModelo(reporteBean.getModeloSelected());
                }
                
                reporteBean.getProducto().setIdModelo((Modelo) toUpperCaseStrings(reporteBean.getModelo()));
                reporteBean.getProducto().getIdModelo().setIdMarca(new Marca());
                reporteBean.getProducto().getIdModelo().setIdMarca((Marca) toUpperCaseStrings(reporteBean.getMarca()));
                
                reporteBean.setProductoSelected(productoService.getProductoByEquipo(reporteBean.getProducto().getEquipo()));
                if (reporteBean.getProductoSelected() == null) {
                    reporteBean.getProducto().setEstado(EstadosEnum.ACTIVO.getValue());
                    reporteBean.setProducto(productoService.addProducto((Producto) toUpperCaseStrings(reporteBean.getProducto())));
                } else {
                    reporteBean.setProducto(reporteBean.getProductoSelected());
                }
                
                if (reporteBean.getEstadoActualMantenimiento().equals(Enums.CORRECTIVO.getPropertyName())) {
                    reporteBean.setEstadoActualMantenimiento(Enums.CORRECTIVO.getValue());
                }
                if (reporteBean.getEstadoActualMantenimiento().equals(Enums.PREVENTIVO.getPropertyName())) {
                    reporteBean.setEstadoActualMantenimiento(Enums.PREVENTIVO.getValue());
                }
                
                reporteBean.getProducto().getIdModelo().setIdMarca(new Marca());
                reporteBean.getProducto().getIdModelo().setIdMarca(reporteBean.getMarca());
                
                reporteBean.getReporte().setFecha(new Date());
                reporteBean.getReporte().setFechaCreacion(new Date());
                reporteBean.getReporte().setIdVisita(new TipoVisita(reporteBean.getIdTipoVisita()));
                reporteBean.getReporte().setUsuarioCreacion(sesionController.getSesionBean().getUsuarios().getUsuario());
                reporteBean.getReporte().setMantenimiento(reporteBean.getEstadoActualMantenimiento());
                reporteBean.getReporte().setTipo(sesionController.getSesionBean().getIsReporteTipo());
                reporteBean.getReporte().setSubtipo(sesionController.getSesionBean().getIsSubReporteTipo());
                reporteBean.getReporte().setEstado(Enums.ESTADO_REPORTE_PROCESO.getValue());
                reporteBean.getReporte().setIdUsuario(reporteBean.getUsuarios());
                reporteBean.getReporte().setNumerofactura(reporteBean.getNumeroFacturaTecnico() + 1);
                reporteBean.setReporte((Reporte) toUpperCaseStrings(reporteService.saveReporte(reporteBean.getReporte())));
                
                reporteBean.getProductoCliente().setEstado(1);
                reporteBean.getProductoCliente().setSerie(reporteBean.getProductoCliente().getSerie());
                reporteBean.getProductoCliente().setIdProducto(reporteBean.getProducto());
                reporteBean.getProductoCliente().setIdCliente(reporteBean.getCliente());
                reporteBean.getProductoCliente().setFecha(new Date());
                reporteBean.setProductoCliente((ProductoCliente) toUpperCaseStrings(
                        productoClienteService.addProductoCliente(reporteBean.getProductoCliente())));
                
                reporteBean.getProductoClienteReporte().setIdProductoCliente(reporteBean.getProductoCliente());
                reporteBean.getProductoClienteReporte().setIdReporte(reporteBean.getReporte());
                
                reporteBean.setProductoDetalleReporte(productoDetalleReporteService.addProductoDetalleReporte(reporteBean.getProductoDetalleReporte()));
                
                reporteBean.getProductoClienteReporte().setIdProductoDetalleReporte(reporteBean.getProductoDetalleReporte());
                
                reporteBean.setProductoClienteReporte(productoClienteReporteService.saveOrUpdateProductoClienteReporte(reporteBean.getProductoClienteReporte()));
                
                if (asignacionReparacionBean.getAsignacionReparacionSelected() != null) {
                    
                    asignacionReparacionBean.setAsignacionReparacion(new AsignacionReparacion());
                    asignacionReparacionBean.setAsignacionReparacion(asignacionReparacionBean.getAsignacionReparacionSelected());
                    asignacionReparacionBean.getAsignacionReparacion().setIdReporte(reporteBean.getReporte().getId());
                    asignacionReparacionBean.getAsignacionReparacion().setEstado(reporteBean.getReporte().getEstado());
                    asignacionReparacionBean.setAsignacionReparacion(asignacionReparacionService.addAsignacionReparacion(asignacionReparacionBean.getAsignacionReparacion()));
                    
                }
                
                reporteMantenimientoService.addReporteMantenimientoPreventivo(
                        reporteBean.getProductoClienteReporte(),
                        reporteBean.getListProcesamiento(),
                        reporteBean.getListPreventivoImagen(),
                        reporteBean.getListPreventivoFijacion(),
                        reporteBean.getListPreventivoLimpieza());
                sesionController.getSesionBean().setIsReporteTipo(getSessionText(Enums.PARAMETRO_TIPO_REPORTE.getValue()));
                
                reporteMantenimientoService.addReporteMantenimientoCorrectivo(reporteBean.getProductoClienteReporte(),
                        reporteBean.getListCorrectivoSuministros(),
                        reporteBean.getListCorrectivoImagen(),
                        reporteBean.getListCorrectivoFijacion(),
                        reporteBean.getListCorrectivoRevelado(),
                        reporteBean.getListCorrectivoAlimentacion(),
                        reporteBean.getListCorrectivoOtros(), sesionController.getSesionBean().getIsReporteTipo(),
                        sesionController.getSesionBean().getUsuarios());
                
            }

            /*  if (!new ReporteTecnico().enviarMailReporte(reporteBean, reporteBean.getCliente().getEmail(), "Reporte")) {
                System.out.println("No se pudo enviar el mail");
            }*/
            killSession(Enums.PARAMETRO_TIPO_REPORTE.getValue());
            killIdReporteSession();
            setMensajeSession("Guardado exitósamente");
            // sesionBean.setIsSubReporteTip
            sesionController.getSesionBean().setIsSubReporteTipo(null);
            sesionController.getSesionBean().setIsReporteTipo(null);
            sesionController.getSesionBean().setIsReporteVer(null);
            redirectToPage("/paginas/consultas.jsf");
        } catch (ConstraintViolationException e) {
            System.out.println("Error ConstraintViolationException  " + e.getSQLException().getMessage());
        } catch (Exception e) {
            error("No se pudo guardar");
            System.out.println(" error guardar " + e.getMessage());
            e.getStackTrace();
        }
        
    }
    
    protected void inicializarBeans() {
        reporteBean.setReporte(new Reporte());
        reporteBean.setDetalleCatalogoReporte(new DetalleCatalogoReporte());
        reporteBean.setProductoCliente(new ProductoCliente());
        reporteBean.setProductoClienteReporte(new ProductoClienteReporte());
        reporteBean.setProductoDetalleReporte(new ProductoDetalleReporte());
        reporteBean.setCliente(new Cliente());
        reporteBean.getReporte().setFecha(new Date());
        
        reporteBean.setProducto(new Producto());
        reporteBean.setMarca(new Marca());
        reporteBean.setModelo(new Modelo());
        reporteBean.getReporte().setIdVisita(new TipoVisita());
        reporteBean.getProductoClienteReporte().setIdProductoCliente(new ProductoCliente());
        reporteBean.setFiltroCedula(new String());
        reporteBean.setFiltroProducto(new String());
        reporteBean.setFiltroSerial(new String());
        
        reporteBean.setListCliente(new ArrayList<Cliente>());
        reporteBean.setListProducto(new ArrayList<Producto>());
        reporteBean.setListMarca(new ArrayList<Marca>());
        reporteBean.setListModelo(new ArrayList<Modelo>());
        
        reporteBean.getReporte().setHoraInicio(new Date());
        reporteBean.getReporte().setHoraFin(new Date());
        reporteBean.getReporte().setFecha(new Date());
        reporteBean.setDisable(Boolean.FALSE);
        reporteBean.setEstadoActualMantenimiento(new String());
        
        reporteBean.setListcabeceraCatalogoReportesPreventivo(new ArrayList<CabeceraCatalogoReporte>());
        reporteBean.setListProcesamiento(new ArrayList<DetalleCatalogoReporte>());
        reporteBean.setListPreventivoImagen(new ArrayList<DetalleCatalogoReporte>());
        reporteBean.setListPreventivoFijacion(new ArrayList<DetalleCatalogoReporte>());
        reporteBean.setListPreventivoLimpieza(new ArrayList<DetalleCatalogoReporte>());
        reporteBean.setListCorrectivoSuministros(new ArrayList<DetalleCatalogoReporte>());
        
        reporteBean.setListCorrectivoImagen(new ArrayList<DetalleCatalogoReporte>());
        reporteBean.setListCorrectivoFijacion(new ArrayList<DetalleCatalogoReporte>());
        reporteBean.setListCorrectivoRevelado(new ArrayList<DetalleCatalogoReporte>());
        reporteBean.setListCorrectivoAlimentacion(new ArrayList<DetalleCatalogoReporte>());
        reporteBean.setListCorrectivoAlimentacionSelected(new ArrayList<DetalleCatalogoReporte>());
        
        reporteBean.setListCorrectivoOtros(new ArrayList<DetalleCatalogoReporte>());
        reporteBean.setListTipoVisitas(new ArrayList<TipoVisita>());
        
        reporteBean.setListCliente(new ArrayList<Cliente>());
        
        reporteBean.setListProducto(new ArrayList<Producto>());
        reporteBean.setListMarca(new ArrayList<Marca>());
        reporteBean.setListModelo(new ArrayList<Modelo>());
        
    }
    
    protected void llenarCatalogos() {
        
        reporteBean.setListcabeceraCatalogoReportesPreventivo(cabeceraCatalogoReporteService.getCabeceraCatalogoReportesByTipo(Enums.PREVENTIVO.getValue()));
        
        reporteBean.setListProcesamiento(repuestoModelo(repuestoModeloService.getRepuestoModelosByTipoDetalleCatalogo(Enums.MANTENIMIENTO_PROCESAMIENTO.getValue(), true)));
        reporteBean.setListPreventivoImagen(repuestoModelo(repuestoModeloService.getRepuestoModelosByTipoDetalleCatalogo(Enums.MANTENIMIENTO_PREVENTIVO_IMAGEN.getValue(), true)));
        reporteBean.setListPreventivoFijacion(repuestoModelo(repuestoModeloService.getRepuestoModelosByTipoDetalleCatalogo(Enums.MANTENIMIENTO_PREVENTIVO_FIJACION.getValue(), true)));
        reporteBean.setListPreventivoLimpieza(repuestoModelo(repuestoModeloService.getRepuestoModelosByTipoDetalleCatalogo(Enums.MANTENIMIENTO_EXTERIORES.getValue(), true)));
        
        reporteBean.setListCorrectivoSuministros(detalleCatalogoReporteService.getDetalleCatalogoReporteByCabeceraCodigo(Enums.MANTENIMIENTO_SUMINISTROS.getValue()));
        reporteBean.setListCorrectivoImagen(detalleCatalogoReporteService.getDetalleCatalogoReporteByCabeceraCodigo(Enums.MANTENIMIENTO_CORRECTIVO_IMAGEN.getValue()));
        reporteBean.setListCorrectivoFijacion(detalleCatalogoReporteService.getDetalleCatalogoReporteByCabeceraCodigo(Enums.MANTENIMIENTO_CORRECTIVO_FIJACION.getValue()));
        reporteBean.setListCorrectivoRevelado(detalleCatalogoReporteService.getDetalleCatalogoReporteByCabeceraCodigo(Enums.MANTENIMIENTO_CORRECTIVO_REVELADO.getValue()));
        reporteBean.setListCorrectivoAlimentacion(detalleCatalogoReporteService.getDetalleCatalogoReporteByCabeceraCodigo(Enums.MANTENIMIENTO_ALIMENTACION.getValue()));
        reporteBean.setListCorrectivoAlimentacionSelected(reporteBean.getListCorrectivoAlimentacionSelected());
        
        reporteBean.setListCorrectivoOtros(listCorrectivoOtros());
        reporteBean.setListTipoVisitas(tipoVisitaService.getAllTipoVisitas());
        
        reporteBean.setListCliente(clienteService.getClientesByEstado(EstadosEnum.ACTIVO.getValue()));
        
        reporteBean.setListProducto(productoService.getProductos());
        reporteBean.setListMarca(marcaService.getMarcas());
        reporteBean.setListModelo(modeloService.getModelos());
        
    }
    
    public void abrirModalMantenimientosOtros() {
        
        detalleCatalogoReporteBean.setListDetalleCatalogoReporte(new ArrayList<DetalleCatalogoReporte>());
        detalleCatalogoReporteBean.setListDetalleCatalogoReporte(detalleCatalogoReporteService.getDetalleCatalogoReporteByCabeceraCodigo(Enums.MANTENIMIENTO_OTROS.getValue()));
        if (detalleCatalogoReporteBean.getListDetalleCatalogoReporte().isEmpty()) {
            warn("No se encontraron resultados");
            return;
        }
        
        update("formRepuestosOtros");
        openModalBS("dlgRepuestosOtros");
    }
    
    public void rowDblselectOtroRepuesto(SelectEvent event) {
        DetalleCatalogoReporte obj = (DetalleCatalogoReporte) event.getObject();
        if (obj.getTipoRepuesto() == null || obj.getTipoRepuesto().isEmpty()) {
            warn("Seleccione tipo de repuesto");
            return;
        }
        
        for (DetalleCatalogoReporte detalle : reporteBean.getListCorrectivoOtros()) {
            if (detalle.getDescripcion().equals(obj.getDescripcion())) {
                warn("El repuesto ya ha sido asignado");
                return;
            }
        }
        
        int c = 0;
        for (DetalleCatalogoReporte detalle : reporteBean.getListCorrectivoOtros()) {
            if (detalle.getTipoRepuesto() != null && !detalle.getTipoRepuesto().isEmpty()) {
                ++c;
            }
        }
        
        if (c >= reporteBean.getListCorrectivoOtros().size()) {
            warn("Repuestos fuera de límite");
        } else {
            reporteBean.getListCorrectivoOtros().set(c, obj);
            
        }
        
        closeModalBS("dlgRepuestosOtros");
        
    }
    
    public void abrirDialogClientes() {
        openModalBS("dlgClientes");
        // update("dialogCliente");;
    }
    
    public void seleccionarDialogCliente(Cliente cliente) {
        reporteBean.setCliente(new Cliente());
        
        if (!cliente.getRuc().isEmpty()) {
            reporteBean.setCliente(clienteService.getClienteByRuc(cliente.getRuc()));
        } else if (!cliente.getCliente().isEmpty()) {
            reporteBean.setCliente(clienteService.getClienteByNombre(cliente.getCliente()));
        }
        
        clearTableFilters("clienteswv");
        closeModalBS("dlgClientes");
    }
    
    public void abrirDialogProductos() {
        
        openModalBS("dlgProductos");
        
    }
    
    public void seleccionarDialogProducto(Producto producto) {
        reporteBean.setProducto(new Producto());
        
        reporteBean.setProducto(producto);
        reporteBean.setModelo(producto.getIdModelo());
        
        reporteBean.setMarca(producto.getIdModelo().getIdMarca());
        
        clearTableFilters("productoswv");
        closeModalBS("dlgProductos");
    }
    
    public void rowDblselectProducto(SelectEvent event) {
        reporteBean.setProducto(new Producto());
        Producto obj = (Producto) event.getObject();
        
        reporteBean.setProducto(obj);
        reporteBean.setModelo(obj.getIdModelo());
        
        reporteBean.setMarca(obj.getIdModelo().getIdMarca());
        updateMany("idEquipo,idMarca,idModelo,idFirmware,idCampo1,idCampo2");
     
        clearTableFilters("productoswv");
        closeModalBS("dlgProductos");
    }
    
    public void rowDblselectClientes(SelectEvent event) {
        
        reporteBean.setCliente(new Cliente());
        Cliente obj = (Cliente) event.getObject();
        if (!obj.getRuc().isEmpty()) {
            reporteBean.setCliente(clienteService.getClienteByRuc(obj.getRuc()));
        } else if (!obj.getCliente().isEmpty()) {
            reporteBean.setCliente(clienteService.getClienteByNombre(obj.getCliente()));
        }
        
        clearTableFilters("clienteswv");
        closeModalBS("dlgClientes");
        
    }
}
