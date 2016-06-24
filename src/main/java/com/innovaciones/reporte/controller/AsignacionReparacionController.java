/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.AsignacionReparacionBean;
import com.innovaciones.reporte.model.AsignacionReparacion;
import com.innovaciones.reporte.model.Cliente;
import com.innovaciones.reporte.model.ProductoCliente;
import com.innovaciones.reporte.model.Usuarios;
import com.innovaciones.reporte.service.AsignacionReparacionService;
import com.innovaciones.reporte.service.ClienteService;
import com.innovaciones.reporte.service.ProductoClienteService;
import com.innovaciones.reporte.service.UsuariosService;
import com.innovaciones.reporte.util.Enums;
import com.innovaciones.reporte.util.EstadosEnum;
import com.innovaciones.reporte.util.Utilities;
import static com.innovaciones.reporte.util.Utilities.cargarEstadosBoolean;
import static com.innovaciones.reporte.util.Utilities.closeModalBS;
import static com.innovaciones.reporte.util.Utilities.info;
import static com.innovaciones.reporte.util.Utilities.openModalBS;
import static com.innovaciones.reporte.util.Utilities.setValueInSession;
import static com.innovaciones.reporte.util.Utilities.toUpperCaseStrings;
import static com.innovaciones.reporte.util.Utilities.update;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author Fernando
 */
@ManagedBean(name = "asignacionReparacionController")
@ViewScoped
public class AsignacionReparacionController extends Utilities implements Serializable {

    private final static long NOVENTA_MINUTOS = 90;
    static final long UN_MINUTO_EN_MILISEGUNDOS = 60000;

    @Getter
    @Setter
    @ManagedProperty("#{asignacionReparacionService}")
    private AsignacionReparacionService asignacionReparacionService;

    @Getter
    @Setter
    @ManagedProperty("#{clienteService}")
    private ClienteService clienteService;

    @Getter
    @Setter
    @ManagedProperty("#{usuariosService}")
    private UsuariosService usuarioService;

    @Getter
    @Setter
    @ManagedProperty("#{productoClienteService}")
    private ProductoClienteService productoClienteService;

    @Getter
    @Setter
    @ManagedProperty("#{asignacionReparacionBean}")
    private AsignacionReparacionBean asignacionReparacionBean;

    @Getter
    @Setter
    @ManagedProperty("#{sesionController}")
    private SesionController sesionController;

    /**
     * Creates a new instance of AsignacionReparacionController
     */
    public AsignacionReparacionController() {
    }

    @PostConstruct
    void init() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 00);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        asignacionReparacionBean.setHoraInicial(calendar.getTime());

        asignacionReparacionBean.setListAsignacionReparaciones(asignacionReparacionService.getAsignacionReparacionesNoEliminados());
        asignacionReparacionBean.setListPrioridades(cargarPrioridadAsignaciones());
        asignacionReparacionBean.setListUsuarios(usuarioService.getUsuariosByRolByEstado(Enums.ROLE_TECNICO.getValue(), EstadosEnum.ACTIVO.getValue()));
        asignacionReparacionBean.setListClientes(clienteService.getClientesByEstado(EstadosEnum.ACTIVO.getValue()));
        asignacionReparacionBean.setListEstados(cargarEstadosBoolean());
        asignacionReparacionBean.setTiposReporte(cargarTiposReporte());

        cargarDatosCalendario();
    }

    private Calendar DateToCalendar(Date date, Date hour) {

        Calendar calendar = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = (Date) formatter.parse(date.toString());
            calendar = Calendar.getInstance();
            calendar.set(Calendar.AM_PM, Calendar.AM);

            calendar.setTime(date);

            calendar.set(Calendar.HOUR_OF_DAY, hour.getHours());
            calendar.set(Calendar.MINUTE, hour.getMinutes());
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendar;
    }

    private void cargarDatosCalendario() {

        try {

            asignacionReparacionBean.setListEventoModels(new DefaultScheduleModel());

            for (AsignacionReparacion asignacionReparacion : asignacionReparacionBean.getListAsignacionReparaciones()) {

                Calendar calendarInicio = DateToCalendar(asignacionReparacion.getFechaInicioAtencion(), asignacionReparacion.getHoraInicioAtencion());

                Calendar calendarFin = DateToCalendar(asignacionReparacion.getFechaFinAtencion(), asignacionReparacion.getHoraFinAtencion());

                asignacionReparacionBean.setEvento(new DefaultScheduleEvent(
                        "\nCli: " + asignacionReparacion.getIdProductoCliente().getIdCliente().getCliente().toUpperCase()
                        + "\nTéc: " + asignacionReparacion.getIdUsuarioAtencion().getNombreCompleto().toUpperCase(),
                        new Date(calendarInicio.getTimeInMillis()),
                        new Date(calendarFin.getTimeInMillis()),
                        asignacionReparacion));

                asignacionReparacionBean.getListEventoModels().addEvent(asignacionReparacionBean.getEvento());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        asignacionReparacionBean.setLazyEventoModel(new LazyScheduleModel() {
//
//            @Override
//            public void loadEvents(Date start, Date end) {
//                Date random = new Date();
//                addEvent(new DefaultScheduleEvent("Lazy Event 1", random, random));
//
//                addEvent(new DefaultScheduleEvent("Lazy Event 2", random, random));
//            }
//        });
    }

    private void actualizarModal() {
        try {
            update(":formEditAsignacionReparaciones:opSeleccion");
            update("tabSeleccion");
            update("tabViewAsignaciones:tblAsignacionReparaciones");
            update("formEditAsignacionReparaciones");
            openModalBS("dlgAsignacionReparaciones");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarAsignacionReparacion() {

        asignacionReparacionBean.getAsignacionReparacionSelected().setEstado(Enums.ESTADO_ASIGNACION_ELIMINADO.getValue());
        asignacionReparacionService.addAsignacionReparacion(asignacionReparacionBean.getAsignacionReparacionSelected());
        asignacionReparacionBean.setListAsignacionReparaciones(asignacionReparacionService.getAsignacionReparacionesNoEliminados());
        asignacionReparacionBean.setAsignacionReparacionSelected(null);
        update("tabViewAsignaciones:tblAsignacionReparaciones");
        update("tabViewAsignaciones:schedule");
        update("tabViewAsignaciones");
        cargarDatosCalendario();
        info("Eliminado exitósamente");
        closeModalBS("dlgEliminacionAsignacionReparaciones");
    }

    public void mostrarAsignacionReparacion(AsignacionReparacion asignacionReparacion) {

        asignacionReparacionBean.setAsignacionReparacionSelected(asignacionReparacion);
        update("eliminacionForm");
        openModalBS("dlgEliminacionAsignacionReparaciones");
    }

    public void ocultarAsignacionReparacion() {
        closeModalBS("dlgEliminacionAsignacionReparaciones");
    }

    private void nuevaAsignacionReparacion(Date fechaInicio) {

        asignacionReparacionBean.setAsignacionReparacion(new AsignacionReparacion());
        asignacionReparacionBean.getAsignacionReparacion().setCodigo(UUID_CODE());
        asignacionReparacionBean.getAsignacionReparacion().setFechaRegistro(new Date());
        asignacionReparacionBean.getAsignacionReparacion().setHoraInicioAtencion(new Date());

        if (fechaInicio != null) {
            asignacionReparacionBean.getAsignacionReparacion().setFechaInicioAtencion(fechaInicio);
            asignacionReparacionBean.getAsignacionReparacion().setFechaFinAtencion(fechaInicio);
        } else {
            asignacionReparacionBean.getAsignacionReparacion().setFechaInicioAtencion(new Date());
            asignacionReparacionBean.getAsignacionReparacion().setFechaFinAtencion(new Date());
        }

        asignacionReparacionBean.getAsignacionReparacion().setHoraInicioAtencion(asignacionReparacionBean.getHoraInicial());
        asignacionReparacionBean.getAsignacionReparacion().setHoraFinAtencion(new Date(asignacionReparacionBean.getHoraInicial().getTime() + (NOVENTA_MINUTOS * UN_MINUTO_EN_MILISEGUNDOS)));

        asignacionReparacionBean.getAsignacionReparacion().setIdUsuarioAtencion(new Usuarios());
        asignacionReparacionBean.setPrioridad(null);
        asignacionReparacionBean.getAsignacionReparacion().setNombreUsuarioRegistro(sesionController.getSesionBean().getUsuarios().getNombreCompleto());
//            asignacionReparacionBean.setEstado(Enums.ESTADO_ASIGNACION_ASIGNADO.getValue());            
        asignacionReparacionBean.setProductoClienteSelected(new ProductoCliente());
        asignacionReparacionBean.setTipoReporteSelected(null);
        asignacionReparacionBean.setUsuarioSelected(new Usuarios());
        asignacionReparacionBean.setClienteSelected(new Cliente());
        asignacionReparacionBean.setListProductoClientes(new ArrayList<ProductoCliente>());
        resetColorPorPrioridad();
    }

    public void abrirDialogAsignacionReparacion(AsignacionReparacion asignacionReparacion) throws CloneNotSupportedException {
        asignacionReparacionBean.setIndiceTabView(0);

//        System.out.println("com.innovaciones.reporte.controller.AsignacionReparacionController.abrirDialogAsignacionReparacion().......1");
//        
//        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("tabSeleccion:tabClientes");
//        dataTable.reset();
//        
//        System.out.println("com.innovaciones.reporte.controller.AsignacionReparacionController.abrirDialogAsignacionReparacion().......2");
//        
//        dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent("tabSeleccion:tabProductos");
//        dataTable.reset();
//        
//        System.out.println("com.innovaciones.reporte.controller.AsignacionReparacionController.abrirDialogAsignacionReparacion().......3");
//        
        //resetDataTable("tabSeleccion:tabClientes");
        //resetDataTable("tabSeleccion:tabProductos");
        if (asignacionReparacion == null) {

//            asignacionReparacionBean.setAsignacionReparacion(new AsignacionReparacion());
//            asignacionReparacionBean.getAsignacionReparacion().setFechaRegistro(new Date());
//            asignacionReparacionBean.getAsignacionReparacion().setFechaInicioAtencion(new Date());
//            asignacionReparacionBean.getAsignacionReparacion().setHoraInicioAtencion(new Date());
//            asignacionReparacionBean.getAsignacionReparacion().setFechaFinAtencion(new Date());
//
//            asignacionReparacionBean.getAsignacionReparacion().setHoraInicioAtencion(asignacionReparacionBean.getHoraInicial());
//            asignacionReparacionBean.getAsignacionReparacion().setHoraFinAtencion(new Date(asignacionReparacionBean.getHoraInicial().getTime() + (NOVENTA_MINUTOS * UN_MINUTO_EN_MILISEGUNDOS)));
//
//            asignacionReparacionBean.getAsignacionReparacion().setIdUsuarioAtencion(new Usuarios());
//            asignacionReparacionBean.setPrioridad(null);
//            asignacionReparacionBean.getAsignacionReparacion().setNombreUsuarioRegistro(sesionController.getSesionBean().getUsuarios().getNombreCompleto());
//            asignacionReparacionBean.setEstado(Enums.ESTADO_ASIGNACION_ASIGNADO.getValue());
//            asignacionReparacionBean.setProductoClienteSelected(new ProductoCliente());
//            asignacionReparacionBean.setTipoReporteSelected(null);
//            asignacionReparacionBean.setUsuarioSelected(new Usuarios());
//            asignacionReparacionBean.setClienteSelected(new Cliente());
//            asignacionReparacionBean.setListProductoClientes(new ArrayList<ProductoCliente>());
//            resetColorPorPrioridad();
            nuevaAsignacionReparacion(null);

        } else {
            asignacionReparacionBean.setAsignacionReparacion(asignacionReparacion);
            asignacionReparacionBean.setAsignacionReparacionSelected((AsignacionReparacion) asignacionReparacion.clone());
            asignacionReparacionBean.setUsuarioSelected(asignacionReparacion.getIdUsuarioAtencion());
            asignacionReparacionBean.setProductoClienteSelected(asignacionReparacion.getIdProductoCliente());
            asignacionReparacionBean.setClienteSelected(asignacionReparacion.getIdProductoCliente().getIdCliente());
            asignacionReparacionBean.setPrioridad(asignacionReparacionBean.getAsignacionReparacion().getPrioridad());
            asignacionReparacionBean.setTipoReporteSelected(asignacionReparacion.getTipoReporte());
            //asignacionReparacionBean.setEstado(asignacionReparacion.getEstado());
            asignacionReparacionBean.setListProductoClientes(productoClienteService.getProductoClienteByIdCliente(asignacionReparacion.getIdProductoCliente().getIdCliente().getId()));
            cambiarColorPorPrioridad();
        }
        actualizarModal();
    }

    public void abrirDialogAsignacionReparacion(AsignacionReparacion asignacionReparacion, Date fechaInicio) {
        asignacionReparacionBean.setIndiceTabView(0);

        if (asignacionReparacion == null) {
            nuevaAsignacionReparacion(fechaInicio);

//            asignacionReparacionBean.setAsignacionReparacion(new AsignacionReparacion());
//            asignacionReparacionBean.getAsignacionReparacion().setFechaRegistro(new Date());
//            asignacionReparacionBean.getAsignacionReparacion().setFechaInicioAtencion(fechaInicio);
//            asignacionReparacionBean.getAsignacionReparacion().setHoraInicioAtencion(new Date());
//            asignacionReparacionBean.getAsignacionReparacion().setFechaFinAtencion(fechaInicio);
//            
//            asignacionReparacionBean.getAsignacionReparacion().setHoraInicioAtencion(asignacionReparacionBean.getHoraInicial());
//            asignacionReparacionBean.getAsignacionReparacion().setHoraFinAtencion(new Date(asignacionReparacionBean.getHoraInicial().getTime() + (NOVENTA_MINUTOS * UN_MINUTO_EN_MILISEGUNDOS)));
//            
//            asignacionReparacionBean.getAsignacionReparacion().setIdUsuarioAtencion(new Usuarios());
//            asignacionReparacionBean.setPrioridad(null);
//            asignacionReparacionBean.getAsignacionReparacion().setNombreUsuarioRegistro(sesionController.getSesionBean().getUsuarios().getNombreCompleto());
//            asignacionReparacionBean.setProductoClienteSelected(new ProductoCliente());
//            asignacionReparacionBean.setTipoReporteSelected(null);
//            asignacionReparacionBean.setUsuarioSelected(new Usuarios());
//            asignacionReparacionBean.setClienteSelected(new Cliente());
//            asignacionReparacionBean.setListProductoClientes(new ArrayList<ProductoCliente>());
//            resetColorPorPrioridad();
        }
        actualizarModal();

    }

    private void enviarEmail() {
        StringBuilder equipo = new StringBuilder();

        equipo.append(asignacionReparacionBean.getAsignacionReparacion().getIdProductoCliente().getIdProducto().getIdModelo().getIdMarca().getMarca());
        equipo.append("-");
        equipo.append(asignacionReparacionBean.getAsignacionReparacion().getIdProductoCliente().getIdProducto().getIdModelo().getModelo());

        StringBuilder destinatarios = new StringBuilder();
        destinatarios.append(asignacionReparacionBean.getAsignacionReparacion().getIdUsuarioAtencion().getMail());
        destinatarios.append(";");
        destinatarios.append(asignacionReparacionBean.getAsignacionReparacion().getIdProductoCliente().getIdCliente().getEmail());
        destinatarios.append(";");
        destinatarios.append(sesionController.getSesionBean().getUsuarios().getMail());

        List<Object> content = new ArrayList<Object>();

        content.add(asignacionReparacionBean.getAsignacionReparacion().getCodigo());
        content.add(asignacionReparacionBean.getAsignacionReparacion().getIdProductoCliente().getIdCliente().getCliente());
        content.add(equipo.toString());
        content.add(asignacionReparacionBean.getAsignacionReparacion().getIdProductoCliente().getSerie());
        content.add(asignacionReparacionBean.getAsignacionReparacion().getIdUsuarioAtencion().getNombreCompleto());
        content.add(asignacionReparacionBean.getAsignacionReparacion().getFechaInicioAtencion());
        content.add(asignacionReparacionBean.getAsignacionReparacion().getHoraInicioAtencion());
        content.add(asignacionReparacionBean.getAsignacionReparacion().getEstado());

        String title = "";
        String tipoMail = "";

        StringBuilder subject = new StringBuilder();
        subject.append("Asignación de Reparación No. ");
        subject.append(asignacionReparacionBean.getAsignacionReparacion().getCodigo());

        if (asignacionReparacionBean.getAsignacionReparacion().getTipoNotificacion().equals(Enums.REPORTE.getValue())) {
            title = "Notificaci&oacute;n de Asignaci&oacute;n de Reparaci&oacute;n";
            tipoMail = Enums.REPORTE.getValue();
        } else {
            if (asignacionReparacionBean.getAsignacionReparacion().getTipoNotificacion().equals(Enums.INSTALACION.getValue())) {
                title = "Notificaci&oacute;n de Instalaci&oacute;n de Equipo(s)";
                tipoMail = Enums.INSTALACION.getValue();
            }
        }

        enviarMail(destinatarios.toString(), title, content,
                "", subject.toString(), tipoMail);
    }

    private void guardarAsignacion() {

        if (asignacionReparacionBean.getTipoReporteSelected().equals(Enums.TIPO_REPORTE_DIAGNOSTICO.getValue())
                || asignacionReparacionBean.getTipoReporteSelected().equals(Enums.TIPO_REPORTE_REPARACION.getValue())
                || asignacionReparacionBean.getTipoReporteSelected().equals(Enums.TIPO_REPORTE_CONTADORES.getValue())) {
            asignacionReparacionBean.getAsignacionReparacion().setTipoNotificacion(Enums.REPORTE.getValue());

        } else if (asignacionReparacionBean.getTipoReporteSelected().equals(Enums.INSTALACION_NUEVA.getValue())
                || asignacionReparacionBean.getTipoReporteSelected().equals(Enums.INSTALACION_TEMPORAL.getValue())) {
            asignacionReparacionBean.getAsignacionReparacion().setTipoNotificacion(Enums.INSTALACION.getValue());
        }

        asignacionReparacionService.addAsignacionReparacion(asignacionReparacionBean.getAsignacionReparacion());

        asignacionReparacionBean.setListAsignacionReparaciones(asignacionReparacionService.getAsignacionReparacionesNoEliminados());

        asignacionReparacionBean.setIndiceTabView(0);

        update("formEditAsignacionReparaciones");
        update("tabViewAsignaciones:tblAsignacionReparaciones");
        update("tabViewAsignaciones:schedule");
        update("tabViewAsignaciones");
        closeModalBS("dlgAsignacionReparaciones");
        enviarEmail();
        cargarDatosCalendario();
        info("Guardado exitósamente");
        resetColorPorPrioridad();
    }

    public void guardar() {

        try {
            String verificarAsignacion = verificarDisponibilidad();

            if (verificarAsignacion.equals("")) {

                if (asignacionReparacionBean.getAsignacionReparacion().getId() == null) {
                    asignacionReparacionBean.getAsignacionReparacion().setEstado(Enums.ESTADO_ASIGNACION_ASIGNADO.getValue());
                }

                asignacionReparacionBean.getAsignacionReparacion().setTipoReporte(asignacionReparacionBean.getTipoReporteSelected());
                asignacionReparacionBean.getAsignacionReparacion().setIdUsuarioRegistro(sesionController.getSesionBean().getUsuarios().getId());
                asignacionReparacionBean.setAsignacionReparacion((AsignacionReparacion) toUpperCaseStrings(asignacionReparacionBean.getAsignacionReparacion()));
                asignacionReparacionBean.getAsignacionReparacion().setPrioridad(asignacionReparacionBean.getPrioridad());

                if (asignacionReparacionBean.getAsignacionReparacion().getId() != null) {
                    if (!comparePropertiesObject(asignacionReparacionBean.getAsignacionReparacion(), asignacionReparacionBean.getAsignacionReparacionSelected())) {
                        guardarAsignacion();
                    } else {
                        info("No se modificó el registro");
                        cerrarDialog();
                    }
                } else {
                    guardarAsignacion();
                }

            } else {
                warn(verificarAsignacion);
            }
        } catch (Exception e) {
            e.printStackTrace();
            error("No se pudo guardar la asignación");
        }
    }

    public void cerrarDialog() {

        resetColorPorPrioridad();
        asignacionReparacionBean.setIndiceTabView(0);

        update("tabViewAsignaciones:tblAsignacionReparaciones");
        update("tabViewAsignaciones:schedule");
        update("tabViewAsignaciones");
        update("formEditAsignacionReparaciones");
        closeModalBS("dlgAsignacionReparaciones");

    }

    private void seleccionTecnico(Object value) {

        Usuarios usuario = null;

        if (value instanceof Usuarios) {
            usuario = (Usuarios) value;
        } else if (value instanceof SelectEvent) {
            SelectEvent event = (SelectEvent) value;
            usuario = (Usuarios) event.getObject();
        }

        if (usuario != null) {
            asignacionReparacionBean.getAsignacionReparacion().setIdUsuarioAtencion(usuario);
            asignacionReparacionBean.setUsuarioSelected(usuario);
            asignacionReparacionBean.setIndiceTabView(1);
            info("Seleccione un cliente");
            update("tabSeleccion:tblTecnicos");
            update("tabSeleccion");
            update("opSeleccion");
        }
        cambiarColorPorPrioridad();
    }

    public void seleccionarTecnico(Usuarios usuario) {
        seleccionTecnico(usuario);
    }

    public void seleccionarTecnico(final SelectEvent event) {
        seleccionTecnico(event);
    }

    private void seleccionCliente(Object value) {

        Cliente cliente = null;

        if (value instanceof Cliente) {
            cliente = (Cliente) value;
        } else if (value instanceof SelectEvent) {
            SelectEvent event = (SelectEvent) value;
            cliente = (Cliente) event.getObject();
        }

        if (cliente != null) {
            asignacionReparacionBean.setProductoClienteSelected(new ProductoCliente());
            asignacionReparacionBean.setClienteSelected(cliente);
            asignacionReparacionBean.setPrioridad(cliente.getPrioridad());

            asignacionReparacionBean.setListProductoClientes(productoClienteService.getProductoClienteByIdCliente(cliente.getId()));

            if (asignacionReparacionBean.getListProductoClientes() != null
                    && !asignacionReparacionBean.getListProductoClientes().isEmpty()) {
                info("Seleccione un producto");
                asignacionReparacionBean.setIndiceTabView(2);
            } else {
                warn("El cliente seleccionado no tiene productos asignados");
            }

            update("tabSeleccion:tblClientes");
            update("tabSeleccion");
            update("opSeleccion");
        }
        cambiarColorPorPrioridad();
    }

    public void seleccionarCliente(final SelectEvent event) {
        seleccionCliente(event);
    }

    public void seleccionarCliente(Cliente event) {
        seleccionCliente(event);
    }

    private void seleccionProductoCliente(Object value) {

        ProductoCliente productoCliente = null;

        if (value instanceof ProductoCliente) {
            productoCliente = (ProductoCliente) value;
        } else if (value instanceof SelectEvent) {
            SelectEvent event = (SelectEvent) value;
            productoCliente = (ProductoCliente) event.getObject();
        }

        if (productoCliente != null) {
            asignacionReparacionBean.setProductoClienteSelected(productoCliente);
            asignacionReparacionBean.getAsignacionReparacion().setIdProductoCliente(productoCliente);

            info("Producto seleccionado exitósamente");
            update("tabSeleccion:tblProductos");
            update("opSeleccion");
        }
        cambiarColorPorPrioridad();
    }

    public void seleccionarProductoCliente(ProductoCliente productoCliente) {
        seleccionProductoCliente(productoCliente);
    }

    public void seleccionarProductoCliente(final SelectEvent event) {
        seleccionProductoCliente(event);
    }

    private String verificarDisponibilidad() {

        String result = "";

        Date fecha = asignacionReparacionBean.getAsignacionReparacion().getFechaInicioAtencion();
        Integer idAsignacionReparacion = asignacionReparacionBean.getAsignacionReparacion().getId();
        Integer idTecnico = asignacionReparacionBean.getAsignacionReparacion().getIdUsuarioAtencion().getId();

        String serialSeleccionado = asignacionReparacionBean.getAsignacionReparacion().getIdProductoCliente().getSerie();

        List<AsignacionReparacion> asignacionReparaciones = asignacionReparacionService.getAsignacionReparacionesByFechaByIdUsuario(fecha, idTecnico);

        Calendar calendar = GregorianCalendar.getInstance();

        calendar.setTime(asignacionReparacionBean.getAsignacionReparacion().getHoraInicioAtencion());

        Integer minutoSeleccionados = calendar.get(Calendar.HOUR_OF_DAY);

        minutoSeleccionados = (minutoSeleccionados * 60) + calendar.get(Calendar.MINUTE);

        //si la prioridad es alta no se debe validar
        for (AsignacionReparacion asignacionReparacion : asignacionReparaciones) {

            Integer minutosAlmacenados = (asignacionReparacion.getHoraInicioAtencion().getHours() * 60) + asignacionReparacion.getHoraInicioAtencion().getMinutes();
            Integer diferenciaMinutos = Math.abs(minutosAlmacenados - minutoSeleccionados);
            if (diferenciaMinutos < 30) {
                if ((idAsignacionReparacion == null || (idAsignacionReparacion != null && !asignacionReparacion.getId().equals(idAsignacionReparacion)))
                        && idTecnico.equals(asignacionReparacion.getIdUsuarioAtencion().getId())) {
                    result = "Debe haber al menos media hora de diferencia entre cada asignación.\nLa última asignación fue a las " + asignacionReparacion.getHoraInicioAtencion();
                    break;
                }
            }
            if (idAsignacionReparacion == null || (idAsignacionReparacion != null && !asignacionReparacion.getId().equals(idAsignacionReparacion))) {

                if (serialSeleccionado.equalsIgnoreCase(asignacionReparacion.getIdProductoCliente().getSerie())) {

                    result = "El equipo con serial \"" + serialSeleccionado + "\" ya fue asignado este día al técnico \""
                            + asignacionReparacion.getIdUsuarioAtencion().getNombreCompleto() + "\" a las " + asignacionReparacion.getHoraInicioAtencion();
                    break;

                }

            }
        }
        return result;
    }

    public void actualizarFechaFinal() {
        if (asignacionReparacionBean.getAsignacionReparacion() != null && asignacionReparacionBean.getAsignacionReparacion().getFechaInicioAtencion() != null) {
            asignacionReparacionBean.getAsignacionReparacion().setFechaFinAtencion(asignacionReparacionBean.getAsignacionReparacion().getFechaInicioAtencion());
        } else {
            warn("Seleccione una fecha de inicio de la reparación");
        }

    }

    public void actualizarHoraFinal() {
        if (asignacionReparacionBean.getAsignacionReparacion() != null && asignacionReparacionBean.getAsignacionReparacion().getHoraInicioAtencion() != null) {
            asignacionReparacionBean.getAsignacionReparacion().setHoraFinAtencion(new Date(asignacionReparacionBean.getAsignacionReparacion().getHoraInicioAtencion().getTime() + (NOVENTA_MINUTOS * UN_MINUTO_EN_MILISEGUNDOS)));

        } else {
            warn("Seleccione una hora de inicio de la reparación");
        }
    }

    public void onDateSelect(SelectEvent selectEvent) {

        Date fechaCalendario = ((Date) selectEvent.getObject());

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(calendar.DAY_OF_MONTH) - 1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        if (calendar.getTime().compareTo(fechaCalendario) < 0) {
            abrirDialogAsignacionReparacion(null, fechaCalendario);

        } else {
            warn("La fecha seleccionada es anterior a la actual");
        }
    }

    public void onEventSelect(SelectEvent selectEvent) {
        asignacionReparacionBean.setEvento((ScheduleEvent) selectEvent.getObject());

        try {
            asignacionReparacionBean.setAsignacionReparacion((AsignacionReparacion) asignacionReparacionBean.getEvento().getData());
            asignacionReparacionBean.setAsignacionReparacionSelected(asignacionReparacionBean.getAsignacionReparacion());
            abrirDialogAsignacionReparacion(asignacionReparacionBean.getAsignacionReparacion());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String redireccionarToSeleccionado(AsignacionReparacion asignacionReparacion) {

        setValueInSession("asignacionReparacion", asignacionReparacion.getIdReporte());
        String result = "/paginas/consultas.jsf?faces-redirect=true";
        return result;
    }

    private String formatearIdTecnicos() {
        String result = "";
        Integer n = asignacionReparacionBean.getListFilterUsuarios().size();
        for (int i = 0; i < n; i++) {
            result += asignacionReparacionBean.getListFilterUsuarios().get(i);
            if (i < (n - 1)) {
                result += ",";
            }
        }
        return result;
    }

    public void filtrarAsignaciones() {
        try {

            String idTecnicos = formatearIdTecnicos();
            asignacionReparacionBean.setListAsignacionReparaciones(
                    asignacionReparacionService.getAsignacionReparacionesFiltradasByTecnicosFechas(idTecnicos, asignacionReparacionBean.getFechaInicioFilter(), asignacionReparacionBean.getFechaFinFilter()));

            update("tabViewAsignaciones:tblAsignacionReparaciones");
            update("tabViewAsignaciones:schedule");
            update("tabViewAsignaciones");
            update("AsignacionReparacionForm");
            cargarDatosCalendario();
            resetColorPorPrioridad();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cambiarColorPorPrioridad() {
        RequestContext.getCurrentInstance()
                //                 .execute("$('.modal-body').css('background','red');");
                .execute("$('.ui-widget-header').css('background','" + obtenerColorByPrioridad(asignacionReparacionBean.getPrioridad()) + "');"
                        + "$('.ui-datatable .ui-datatable-header').css('background','" + obtenerColorByPrioridad(asignacionReparacionBean.getPrioridad()) + "');");
//                    .execute("$('#" + "dlgAsignacionReparaciones" + "').modal('show');");
    }

    public void resetColorPorPrioridad() {
        RequestContext.getCurrentInstance()
                .execute("$('.ui-widget-header').css('background','');"
                        + "$('.ui-datatable .ui-datatable-header').css('background','');");

        update("tabViewAsignaciones:tblAsignacionReparaciones");
        update("formEditAsignacionReparaciones");
    }
}
