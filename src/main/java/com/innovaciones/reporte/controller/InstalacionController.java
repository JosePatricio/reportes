/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.AsignacionReparacionBean;
import com.innovaciones.reporte.beans.ClienteBean;
import com.innovaciones.reporte.beans.ConsultasBean;
import com.innovaciones.reporte.beans.DetalleCatalogoReporteBean;
import com.innovaciones.reporte.beans.DetalleReporteInstalacionNuevaBean;
import com.innovaciones.reporte.beans.ReporteBean;
import com.innovaciones.reporte.model.AsignacionReparacion;
import com.innovaciones.reporte.model.CabeceraCatalogoReporte;
import com.innovaciones.reporte.model.Cliente;
import com.innovaciones.reporte.model.DTO.NotificacionDTO;
import com.innovaciones.reporte.model.DetalleCatalogoReporte;
import com.innovaciones.reporte.model.DetalleReporteInstalacionNueva;
import com.innovaciones.reporte.model.Marca;
import com.innovaciones.reporte.model.Modelo;
import com.innovaciones.reporte.model.Producto;
import com.innovaciones.reporte.model.ProductoCliente;
import com.innovaciones.reporte.model.ProductoClienteReporte;
import com.innovaciones.reporte.model.ProductoDetalleReporte;
import com.innovaciones.reporte.model.Reporte;
import com.innovaciones.reporte.model.DTO.ReportesDTO;
import com.innovaciones.reporte.model.DetalleReporteTemporal;
import com.innovaciones.reporte.model.RepuestoModelo;
import com.innovaciones.reporte.service.AsignacionReparacionService;
import com.innovaciones.reporte.service.CabeceraCatalogoReporteService;
import com.innovaciones.reporte.service.ClienteService;
import com.innovaciones.reporte.service.ConsultasService;
import com.innovaciones.reporte.service.DetalleCatalogoReporteService;
import com.innovaciones.reporte.service.DetalleReporteInstalacionNuevaService;
import com.innovaciones.reporte.service.DetalleReporteTemporalService;
import com.innovaciones.reporte.service.MarcaService;
import com.innovaciones.reporte.service.ModeloService;
import com.innovaciones.reporte.service.ProductoClienteReporteService;
import com.innovaciones.reporte.service.ProductoClienteService;
import com.innovaciones.reporte.service.ProductoDetalleReporteService;
import com.innovaciones.reporte.service.ProductoService;
import com.innovaciones.reporte.service.ReporteMantenimientoService;
import com.innovaciones.reporte.service.ReporteService;
import com.innovaciones.reporte.service.RepuestoModeloService;
import com.innovaciones.reporte.util.Enums;
import com.innovaciones.reporte.util.EstadosEnum;
import com.innovaciones.reporte.util.ReporteTecnico;
import com.innovaciones.reporte.util.Utilities;
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
import java.io.Serializable;

/**
 *
 * @author Fernando
 */
@ManagedBean(name = "instalacionController")
@ViewScoped
public class InstalacionController extends Utilities implements Serializable {

    /**
     * Creates a new instance of InstalacionController
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
    @ManagedProperty("#{clienteBean}")
    private ClienteBean clienteBean;

    @Getter
    @Setter
    @ManagedProperty("#{detalleReporteInstalacionNuevaBean}")
    private DetalleReporteInstalacionNuevaBean detalleReporteInstalacionNuevaBean;

    @Getter
    @Setter
    @ManagedProperty("#{detalleReporteInstalacionNuevaService}")
    private DetalleReporteInstalacionNuevaService detalleReporteInstalacionNuevaService;

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
    @ManagedProperty("#{detalleReporteTemporalService}")
    private DetalleReporteTemporalService detalleReporteTemporalService;

    @Getter
    @Setter
    @ManagedProperty("#{repuestoModeloService}")
    private RepuestoModeloService repuestoModeloService;

    @Getter
    @Setter
    @ManagedProperty("#{asignacionReparacionBean}")
    private AsignacionReparacionBean asignacionReparacionBean;

    public void llenarDatos() {

        reporteBean.getReporte().setFactura("255555");

        reporteBean.getReporte().setReferencia("referr");
        clienteBean.setCliente(clienteService.getClienteByRuc("1791983580001"));
        reporteBean.getProductoClienteReporte().setAtencion("Atencjion  ");
        reporteBean.getProductoClienteReporte().setCiudad("QUITO  ");
        reporteBean.getProductoClienteReporte().setIpEquipo("192.16.18.156");
        reporteBean.getProductoClienteReporte().setDepartamento("SISTEMAS");
        reporteBean.getProductoClienteReporte().setDireccionEquipo("DIRECIO PRUWEBA");
        reporteBean.getProductoClienteReporte().setTelefonoEquipo("00454564425");
        reporteBean.getProductoClienteReporte().setCorreoEquipo("COM");
        detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().setDescripcionConsideraciones("DESCRIPCION CONSIDERACIONES  ");
        detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().setFaseNeutro("FASE NEUTRO");
        detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().setFaseTierra("FASE TIERRA ");
        detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().setNeutro("NEUTRO ");
        detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().setObservacion("OBSERVACIONES ");
        detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().setNombreOperario("NOMBRE OPERARIO ");
        detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().setNota("NOTAA ");
        detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().setVoltajePrevencion(new java.math.BigDecimal("45.02"));

        reporteBean.getProductoCliente().setSerie(java.util.UUID.randomUUID().toString().substring(1, 10));
        /* reporteBean.setProducto(productoService.getProductoById(12));
        reporteBean.getProducto().setVersionFirmware("VE54545");
        reporteBean.setModelo(reporteBean.getProducto().getIdModelo());
        reporteBean.setMarca(reporteBean.getModelo().getIdMarca());*/
        reporteBean.getReporte().setNombreCliente("NOMBRE");

        reporteBean.getDetalleReporteTemporal().setObservaciones("opbservaciones  temportal");

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
            reporteBean.getReporte().setSubtipo(sesionController.getSesionBean().getIsSubReporteTipo());
            reporteBean.getReporte().setTipo(sesionController.getSesionBean().getIsReporteTipo());
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

            //openModalBS("dlgFiltros");
            this.inicializarBeans();

            reporteBean.setUsuarios(sesionController.getSesionBean().getUsuarios());
            reporteBean.setReporteActual(numeroFactura(reporteBean.getNumeroFacturaTecnico() + 1));
            this.llenarCatalogos();
            reporteBean.setEstadoActualMantenimiento(Enums.PREVENTIVO.getPropertyName());
            reporteBean.getReporte().setSubtipo(sesionController.getSesionBean().getIsSubReporteTipo());
            reporteBean.getReporte().setTipo(sesionController.getSesionBean().getIsReporteTipo());
            llenarDatos();

        } else if (getSessionText(Enums.PARAMETRO_TIPO_REPORTE.getValue()).isEmpty() && Enums.PAGINA_REPORTE.getValue().equals(getCurrentPage())) {
            redireccionar("paginas/menu/menu-principal.jsf");
        }

    }

    protected Integer numeroReporte(String subtipo) {
        return reporteService.getReporteByUserByTipo(sesionController.getSesionBean().getUsuarios(), Enums.INSTALACION.getValue(), subtipo).size();
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
        consultasBean.setReportesDTOs(consultasService.reportesPorRuc(clienteBean.getCliente().getRuc()));

        openDialog("dlgReportesRuc");
    }

    public void llenarClientePorRuc() {

        if (clienteBean.getCliente().getRuc() != null && !clienteBean.getCliente().getRuc().isEmpty()) {
            clienteBean.setClienteSelected(clienteService.getClienteByRuc(clienteBean.getCliente().getRuc()));
            if (clienteBean.getClienteSelected() != null) {
                clienteBean.setCliente(clienteBean.getClienteSelected());
            } else {
                info("No se encontró resultados");
            }

        } else {
            info("El campo ruc está vacio");
        }
    }

    public void llenarProductoPorSerial(String tipo) {
        if (tipo.equals(Enums.DATOS_REEMPLAZO.getValue())) {

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

        if (tipo.equals(Enums.DATOS_TEMPORAL.getValue())) {

            if (reporteBean.getProductoClienteTemporal().getSerie() != null && !reporteBean.getProductoClienteTemporal().getSerie().isEmpty()) {

                reporteBean.setProductoClienteReporteTemporalSelected(productoClienteReporteService.getProductoClienteReporteBySerial(reporteBean.getProductoCliente().getSerie()));

                if (reporteBean.getProductoClienteReporteTemporalSelected() != null) {
                    reporteBean.setProductoClienteReporte(reporteBean.getProductoClienteReporteTemporalSelected());
                    this.llenarReporte();
                    return;
                }

                reporteBean.setProductoClienteTemporalSelected(productoClienteService.getProductoClienteBySerial(reporteBean.getProductoClienteTemporal().getSerie()));

                if (reporteBean.getProductoClienteTemporalSelected() != null) {
                    reporteBean.setProductoClienteTemporal(reporteBean.getProductoClienteTemporalSelected());
                    reporteBean.setProductoTemporal(reporteBean.getProductoClienteTemporalSelected().getIdProducto());

                    //errevisar     reporteBean.setHabilitarGuardarConSerial(Boolean.FALSE);
                } else {
                    info("No se encontró resultados");
                }

            } else {
                info("El campo serie está vacio");
            }

        }

    }

    public void llenarProductoTemporalPorSerial() {

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

        reporteBean.setProductoDetalleReporte(
                reporteBean.getProductoClienteReporte().
                        getIdProductoDetalleReporte());

        clienteBean.setCliente(
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

        if (sesionController.getSesionBean().getIsSubReporteTipo().equals(Enums.INSTALACION_NUEVA.getValue())) {
            detalleReporteInstalacionNuevaBean.setDetalleReporteInstalacionNueva(reporteBean.getProductoClienteReporte().getIdDetalleReporteInstalacionNueva());
        }
        if (sesionController.getSesionBean().getIsSubReporteTipo().equals(Enums.INSTALACION_TEMPORAL.getValue())) {
            reporteBean.setDetalleReporteTemporal(reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal());
            detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().setFaseNeutro(reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getFaseNeutro());
            detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().setFaseTierra(reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getFaseTierra());
            detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().setNeutro(reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getNeutro());
            detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().setObservacion(reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getObservacionMedicion());
            reporteBean.getDetalleReporteTemporal().setIdProductoCliente(reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getIdProductoCliente());
            //  reporteBean.getProductoClienteTemporal().setIdProducto(idProducto);
            reporteBean.setProductoClienteTemporal(reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getIdProductoCliente());

            reporteBean.setProductoTemporal(reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getIdProductoCliente().getIdProducto());
         

        }

        reporteBean.setListPreguntas(llenarRepuestosPreguntas(reporteBean.getListPreguntas(), reporteBean));

    }

    public void PDF() {

        reporteBean.getReporte().setFecha(new Date());
        if (getIdReporteSession() != 0) {
            llenarReportePorId(getIdReporteSession());
        }

        new ReporteTecnico().descargarReporte(reporteBean, Enums.INSTALACION.getValue());

    }

    public void cancelar() {
        try {
            inicializarBeans();
            sesionController.getSesionBean().setIsReporteVer(Boolean.FALSE);
            sesionController.getSesionBean().setIsReporteTipo(null);
            killSession(Enums.PARAMETRO_TIPO_REPORTE.getValue());
            redirectToPage("/paginas/instalaciones/administracion.jsf");
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
                reporteBean.getReporte().setSubtipo(reporteBean.getReporteSelected().getSubtipo());
                reporteBean.getReporte().setTipo(reporteBean.getReporteSelected().getTipo());
                reporteBean.getReporte().setEstado(reporteBean.getReporteSelected().getEstado());
                reporteBean.getReporte().setUsuarioCreacion(reporteBean.getReporteSelected().getUsuarioCreacion());
                reporteBean.getReporte().setFechaCreacion(reporteBean.getReporteSelected().getFechaCreacion());
                reporteBean.getReporte().setUsuarioModificacion(sesionController.getSesionBean().getUsuarios().getUsuario());
                reporteBean.getReporte().setFechaModificacion(new Date());
                reporteBean.getReporte().setIdUsuario(reporteBean.getUsuarios());

                reporteBean.setProductoCliente(reporteBean.getProductoClienteReporte().getIdProductoCliente());
                reporteBean.getProductoCliente().setIdProducto(reporteBean.getProducto());
                reporteBean.getProductoCliente().setIdCliente(clienteBean.getCliente());
                reporteBean.setProductoCliente(productoClienteService.updateProductoCliente(reporteBean.getProductoCliente()));

                reporteBean.setReporte(reporteService.updateReporte(reporteBean.getReporte()));
                reporteBean.getProductoClienteReporte().setIdReporte(reporteBean.getReporte());
                reporteBean.getProductoClienteReporte().setIdProductoCliente(reporteBean.getProductoCliente());

                if (sesionController.getSesionBean().getIsSubReporteTipo().equals(Enums.INSTALACION_NUEVA.getValue())) {

                    detalleReporteInstalacionNuevaBean.setDetalleReporteInstalacionNueva(detalleReporteInstalacionNuevaService.updateDetalleReporteInstalacionNueva(detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva()));
                    reporteBean.getProductoClienteReporte().setIdDetalleReporteInstalacionNueva(detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva());

                    reporteBean.setProductoClienteReporte(productoClienteReporteService.updateProductoClienteReporte(reporteBean.getProductoClienteReporte()));

                    reporteMantenimientoService.updateReporteMantenimientoInstalacionNueva(reporteBean.getProductoClienteReporte(), reporteBean.getListPreguntas(), sesionController.getSesionBean().getUsuarios());

                }

                if (reporteBean.getReporte().getSubtipo().equals(Enums.INSTALACION_TEMPORAL.getValue())) {

                    reporteBean.getDetalleReporteTemporal().setNeutro(detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().getNeutro());
                    reporteBean.getDetalleReporteTemporal().setFaseNeutro(detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().getFaseNeutro());
                    reporteBean.getDetalleReporteTemporal().setFaseTierra(detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().getFaseTierra());
                    reporteBean.getDetalleReporteTemporal().setObservacionMedicion(detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().getObservacion());

                    reporteBean.getProductoClienteTemporal().setIdProducto(reporteBean.getProductoTemporal());

                    reporteBean.setProductoClienteTemporal(productoClienteService.updateProductoCliente(reporteBean.getProductoClienteTemporal()));

                    reporteBean.getDetalleReporteTemporal().setIdProductoCliente(reporteBean.getProductoClienteTemporal());

                    reporteBean.setDetalleReporteTemporal(detalleReporteTemporalService.updateDetalleReporteTemporal(reporteBean.getDetalleReporteTemporal()));

                    ProductoClienteReporte productoClienteReporte = new ProductoClienteReporte();
                    productoClienteReporte.setId(reporteBean.getProductoClienteReporte().getId());
                    productoClienteReporte.setIdReporte(reporteBean.getReporte());
                    productoClienteReporte.setAtencion(reporteBean.getProductoClienteReporte().getAtencion());
                    productoClienteReporte.setDepartamento(reporteBean.getProductoClienteReporte().getDepartamento());
                    productoClienteReporte.setCiudad(reporteBean.getProductoClienteReporte().getCiudad());
                    productoClienteReporte.setIpEquipo(reporteBean.getProductoClienteReporte().getIpEquipo());
                    productoClienteReporte.setDireccionEquipo(reporteBean.getProductoClienteReporte().getDireccionEquipo());
                    productoClienteReporte.setTelefonoEquipo(reporteBean.getProductoClienteReporte().getTelefonoEquipo());
                    productoClienteReporte.setCorreoEquipo(reporteBean.getProductoClienteReporte().getCorreoEquipo());
                    productoClienteReporte.setIdProductoCliente(reporteBean.getProductoCliente());
                    productoClienteReporte.setIdDetalleReporteTemporal(reporteBean.getDetalleReporteTemporal());
                    productoClienteReporte.setIdProductoDetalleReporte(reporteBean.getProductoDetalleReporte());

                    reporteBean.setProductoClienteReporte(productoClienteReporteService.updateProductoClienteReporte(productoClienteReporte));

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

                clienteBean.setClienteSelected(clienteService.getClienteByRuc(clienteBean.getCliente().getRuc()));
                if (clienteBean.getClienteSelected() == null) {
                    clienteBean.getCliente().setEstado(1);
                    clienteBean.setCliente(clienteService.addCliente(clienteBean.getCliente()));

                } else {
                    clienteBean.setCliente(clienteBean.getClienteSelected());
                }

                reporteBean.setMarcaSelected(marcaService.getMarcaByMarca(reporteBean.getMarca().getMarca()));
                if (reporteBean.getMarcaSelected() == null) {
                    reporteBean.getMarca().setEstado(1);
                    reporteBean.setMarca((Marca) toUpperCaseStrings(marcaService.saveMarca(reporteBean.getMarca())));
                } else {
                    reporteBean.setMarca(reporteBean.getMarcaSelected());
                }

                reporteBean.setModeloSelected(modeloService.getModeloByModelo(reporteBean.getModelo().getModelo()));
                if (reporteBean.getModeloSelected() == null) {
                    reporteBean.getModelo().setEstado(1);
                    reporteBean.getModelo().setIdMarca(reporteBean.getMarca());
                    reporteBean.setModelo(modeloService.saveModelo((Modelo) toUpperCaseStrings(reporteBean.getModelo())));
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

                reporteBean.getReporte().setFecha(new Date());
                reporteBean.getReporte().setFechaCreacion(new Date());
                reporteBean.getReporte().setUsuarioCreacion(sesionController.getSesionBean().getUsuarios().getUsuario());

                reporteBean.getReporte().setSubtipo(sesionController.getSesionBean().getIsSubReporteTipo());
                reporteBean.getReporte().setTipo(sesionController.getSesionBean().getIsReporteTipo());
                reporteBean.getReporte().setIdUsuario(reporteBean.getUsuarios());

                reporteBean.getReporte().setNumerofactura(reporteBean.getNumeroFacturaTecnico() + 1);
                reporteBean.getReporte().setEstado(Enums.ESTADO_REPORTE_PROCESO.getValue());

                reporteBean.setReporte((Reporte) toUpperCaseStrings(reporteService.saveReporte(reporteBean.getReporte())));

                reporteBean.getProducto().getIdModelo().setIdMarca(new Marca());
                reporteBean.getProducto().getIdModelo().setIdMarca(reporteBean.getMarca());

                reporteBean.getProductoCliente().setEstado(1);
                reporteBean.getProductoCliente().setSerie(reporteBean.getProductoCliente().getSerie());
                reporteBean.getProductoCliente().setIdProducto(reporteBean.getProducto());
                reporteBean.getProductoCliente().setIdCliente(clienteBean.getCliente());
                reporteBean.getProductoCliente().setFecha(new Date());
                reporteBean.setProductoCliente((ProductoCliente) toUpperCaseStrings(
                        productoClienteService.addProductoCliente(reporteBean.getProductoCliente())));

                reporteBean.getProductoClienteReporte().setIdProductoCliente(reporteBean.getProductoCliente());
                reporteBean.getProductoClienteReporte().setIdReporte(reporteBean.getReporte());

                if (sesionController.getSesionBean().getIsSubReporteTipo().equals(Enums.INSTALACION_NUEVA.getValue())) {

                    //reporteBean.productoDetalleReporte.contadorTotalAnterior
                    detalleReporteInstalacionNuevaBean.setDetalleReporteInstalacionNueva(detalleReporteInstalacionNuevaService.addDetalleReporteInstalacionNueva(detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva()));
                    reporteBean.getProductoClienteReporte().setIdDetalleReporteInstalacionNueva(detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva());
                    reporteBean.setProductoClienteReporte(productoClienteReporteService.saveOrUpdateProductoClienteReporte(reporteBean.getProductoClienteReporte()));
                    reporteMantenimientoService.addReporteMantenimientoInstalacionNueva(reporteBean.getProductoClienteReporte(), reporteBean.getListPreguntas(), sesionController.getSesionBean().getUsuarios());

                }

                if (sesionController.getSesionBean().getIsSubReporteTipo().equals(Enums.INSTALACION_TEMPORAL.getValue())) {

                    reporteBean.setProductoDetalleReporte(productoDetalleReporteService.addProductoDetalleReporte(reporteBean.getProductoDetalleReporte()));
                    reporteBean.getProductoClienteTemporal().setIdCliente(clienteBean.getCliente());
                    reporteBean.getProductoClienteTemporal().setIdProducto(reporteBean.getProductoTemporal());

                    reporteBean.getProductoClienteTemporal().setEstado(1);
                    reporteBean.getProductoClienteTemporal().setFecha(new Date());
                    reporteBean.setProductoClienteTemporal(productoClienteService.addProductoCliente(reporteBean.getProductoClienteTemporal()));

                    reporteBean.getDetalleReporteTemporal().setNeutro(detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().getNeutro());
                    reporteBean.getDetalleReporteTemporal().setFaseNeutro(detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().getFaseNeutro());
                    reporteBean.getDetalleReporteTemporal().setFaseTierra(detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().getFaseTierra());
                    reporteBean.getDetalleReporteTemporal().setObservacionMedicion(detalleReporteInstalacionNuevaBean.getDetalleReporteInstalacionNueva().getObservacion());

                    reporteBean.getDetalleReporteTemporal().setIdProductoCliente(reporteBean.getProductoClienteTemporal());
                    reporteBean.setDetalleReporteTemporal(detalleReporteTemporalService.addDetalleReporteTemporal(reporteBean.getDetalleReporteTemporal()));
                    reporteBean.getProductoClienteReporte().setIdDetalleReporteTemporal(reporteBean.getDetalleReporteTemporal());

                    detalleReporteInstalacionNuevaBean.setDetalleReporteInstalacionNueva(new DetalleReporteInstalacionNueva());
                    reporteBean.getProductoClienteReporte().setIdDetalleReporteInstalacionNueva(new DetalleReporteInstalacionNueva());

                    ProductoClienteReporte productoClienteReporte = new ProductoClienteReporte();
                    productoClienteReporte.setIdReporte(reporteBean.getReporte());
                    productoClienteReporte.setAtencion(reporteBean.getProductoClienteReporte().getAtencion());
                    productoClienteReporte.setDepartamento(reporteBean.getProductoClienteReporte().getDepartamento());
                    productoClienteReporte.setCiudad(reporteBean.getProductoClienteReporte().getCiudad());
                    productoClienteReporte.setIpEquipo(reporteBean.getProductoClienteReporte().getIpEquipo());
                    productoClienteReporte.setDireccionEquipo(reporteBean.getProductoClienteReporte().getDireccionEquipo());
                    productoClienteReporte.setTelefonoEquipo(reporteBean.getProductoClienteReporte().getTelefonoEquipo());
                    productoClienteReporte.setCorreoEquipo(reporteBean.getProductoClienteReporte().getCorreoEquipo());
                    productoClienteReporte.setIdProductoCliente(reporteBean.getProductoCliente());
                    productoClienteReporte.setIdDetalleReporteTemporal(reporteBean.getDetalleReporteTemporal());
                    productoClienteReporte.setIdProductoDetalleReporte(reporteBean.getProductoDetalleReporte());

                    reporteBean.setProductoClienteReporte(productoClienteReporteService.saveOrUpdateProductoClienteReporte(productoClienteReporte));

                }

                if (asignacionReparacionBean.getAsignacionReparacionSelected() != null) {

                    asignacionReparacionBean.setAsignacionReparacion(new AsignacionReparacion());
                    asignacionReparacionBean.setAsignacionReparacion(asignacionReparacionBean.getAsignacionReparacionSelected());
                    asignacionReparacionBean.getAsignacionReparacion().setIdReporte(reporteBean.getReporte().getId());
                    asignacionReparacionBean.getAsignacionReparacion().setEstado(reporteBean.getReporte().getEstado());
                    asignacionReparacionBean.setAsignacionReparacion(asignacionReparacionService.addAsignacionReparacion(asignacionReparacionBean.getAsignacionReparacion()));

                }

            }

            /*  if (!new ReporteTecnico().enviarMailReporte(reporteBean, clienteBean.getCliente().getEmail(), "Reporte")) {
                System.out.println("No se pudo enviar el mail");
            }*/
            killSession(Enums.PARAMETRO_TIPO_REPORTE.getValue());
            killIdReporteSession();
            sesionController.getSesionBean().setIsSubReporteTipo(null);
            setMensajeSession("Guardado exitósamente");
            redirectToPage("/paginas/instalaciones/administracion.jsf");
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
        reporteBean.setDetalleReporteTemporal(new DetalleReporteTemporal());
        reporteBean.setProducto(new Producto());
        reporteBean.setProductoTemporal(new Producto());
        reporteBean.setMarca(new Marca());
        reporteBean.setModelo(new Modelo());

        reporteBean.setProductoClienteTemporal(new ProductoCliente());
        reporteBean.getProductoClienteTemporal().setIdProducto(new Producto());
        reporteBean.getProductoClienteTemporal().getIdProducto().setIdModelo(new Modelo());
        reporteBean.getProductoClienteTemporal().getIdProducto().getIdModelo().setIdMarca(new Marca());

        reporteBean.getProductoClienteReporte().setIdProductoCliente(new ProductoCliente());
        reporteBean.setFiltroCedula(new String());
        reporteBean.setFiltroProducto(new String());
        reporteBean.setFiltroSerial(new String());

        detalleReporteInstalacionNuevaBean.setDetalleReporteInstalacionNueva(new DetalleReporteInstalacionNueva());

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

        reporteBean.setListCliente(new ArrayList<Cliente>());

        reporteBean.setListProducto(new ArrayList<Producto>());
        reporteBean.setListMarca(new ArrayList<Marca>());
        reporteBean.setListModelo(new ArrayList<Modelo>());

    }

    protected void llenarCatalogos() {

        reporteBean.setListConsideraciones(detalleCatalogoReporteService.getDetalleCatalogoReporteByCabeceraCodigo(Enums.INSTALACION_CONSIDERACIONES.getValue()));
        reporteBean.setListPreguntas(repuestoModelo(repuestoModeloService.getRepuestoModelosByTipoDetalleCatalogo(Enums.INSTALACION_PREGUNTAS.getValue(), true)));
        reporteBean.setListCliente(clienteService.getClientesByEstado(EstadosEnum.ACTIVO.getValue()));
        reporteBean.setListProducto(productoService.getProductos());
        reporteBean.setListMarca(marcaService.getMarcas());
        reporteBean.setListModelo(modeloService.getModelos());

    }

    public void abrirModalMantenimientosOtros() {

        detalleCatalogoReporteBean.setListDetalleCatalogoReporte(new ArrayList<DetalleCatalogoReporte>());
        detalleCatalogoReporteBean.setListDetalleCatalogoReporte(detalleCatalogoReporteService.getDetalleCatalogoReporteByCabeceraCodigo(Enums.MANTENIMIENTO_OTROS.getValue()));

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
            clienteBean.setCliente(clienteService.getClienteByRuc(cliente.getRuc()));

        } else if (!cliente.getCliente().isEmpty()) {
            clienteBean.setCliente(clienteService.getClienteByNombre(cliente.getCliente()));

        }
        clearTableFilters("clienteswv");
        //   closeDialog("dlgClientes");
        closeModalBS("dlgClientes");
    }

    public void abrirDialogProductos(String tipo) {

        sesionController.getSesionBean().setTipoInstalacion(tipo);
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
        if (sesionController.getSesionBean().getTipoInstalacion().equals(Enums.DATOS_REEMPLAZO.getValue())) {
            reporteBean.setProducto(obj);
            reporteBean.setModelo(new Modelo());

            // reporteBean.getModelo().setRepuestoModeloList(new ArrayList<RepuestoModelo>());
            reporteBean.setModelo(obj.getIdModelo());
            reporteBean.setMarca(obj.getIdModelo().getIdMarca());

            System.out.println("PRODUCTO   " + reporteBean.getProducto());
            //update("idColumnaEquipoDatos");
            updateMany("idEquipo,idMarca,idModelo,idFirmware,idCampo1,idCampo2");
        }

        if (sesionController.getSesionBean().getTipoInstalacion().equals(Enums.DATOS_TEMPORAL.getValue())) {
            reporteBean.setProductoTemporal(obj);
            updateMany("idEquipoTemporal,idMarcaTemporal,idModeloTemporal,idFirmwareTemporal,idCampo1Temporal,idCampo2Temporal");

        }

        clearTableFilters("productoswv");
        closeModalBS("dlgProductos");
    }

    public void rowDblselectClientes(SelectEvent event) {

        reporteBean.setCliente(new Cliente());
        Cliente obj = (Cliente) event.getObject();

        if (!obj.getRuc().isEmpty()) {
            clienteBean.setCliente(clienteService.getClienteByRuc(obj.getRuc()));
        } else if (!obj.getCliente().isEmpty()) {
            clienteBean.setCliente(clienteService.getClienteByNombre(obj.getCliente()));
        }

        clearTableFilters("clienteswv");
        closeModalBS("dlgClientes");

    }
}
