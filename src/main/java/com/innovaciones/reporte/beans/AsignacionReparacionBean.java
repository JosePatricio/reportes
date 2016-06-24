/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.beans;

import com.innovaciones.reporte.model.AsignacionReparacion;
import com.innovaciones.reporte.model.Cliente;
import com.innovaciones.reporte.model.ProductoCliente;
import com.innovaciones.reporte.model.Usuarios;
import com.innovaciones.reporte.util.AsignacionReparacionEnum;
import com.innovaciones.reporte.util.Enums;
import com.innovaciones.reporte.util.EstadosEnum;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author Fernando
 */
@ManagedBean(name = "asignacionReparacionBean")
@ViewScoped
public class AsignacionReparacionBean implements Serializable {

    /**
     * Creates a new instance of AsignacionReparacionBean
     */
    /**
     * Creates a new instance of AsignacionReparacionBean
     */
    @Getter
    @Setter
    private AsignacionReparacion asignacionReparacion;

    @Getter
    @Setter
    private List<AsignacionReparacion> listAsignacionReparaciones;

    @Getter
    @Setter
    private List<AsignacionReparacion> listFiltrados;

    @Getter
    @Setter
    private AsignacionReparacion asignacionReparacionSelected;

    @Getter
    @Setter
    private List<EstadosEnum> listEstados;

    @Getter
    @Setter
    private List<AsignacionReparacionEnum> listPrioridades;

    @Getter
    @Setter
    private String estado;

    @Getter
    @Setter
    private Integer prioridad;

    @Getter
    @Setter
    private List<Cliente> listClientes;

    @Getter
    @Setter
    private Cliente clienteSelected;

    @Getter
    @Setter
    private List<Usuarios> listUsuarios;

    @Getter
    @Setter
    private List<Usuarios> listFilterUsuarios;

    @Getter
    @Setter
    private Date fechaInicioFilter;

    @Getter
    @Setter
    private Date fechaFinFilter;

    @Getter
    @Setter
    private Usuarios usuarioSelected;

    @Getter
    @Setter
    private ProductoCliente productoClienteSelected;

    @Getter
    @Setter
    private List<ProductoCliente> listProductoClientes;

    @Getter
    @Setter
    private ScheduleModel lazyEventoModel;

    @Getter
    @Setter
    private ScheduleModel listEventoModels;

    @Getter
    @Setter
    private ScheduleEvent evento;

    @Getter
    @Setter
    private int indiceTabView;

    @Getter
    @Setter
    private List<Enums> tiposReporte;

    @Getter
    @Setter
    private String tipoReporteSelected;

    @Getter
    @Setter
    private Date fechaInicial;

    @Getter
    @Setter
    private Date horaInicial;

    public AsignacionReparacionBean() {
    }

    @PostConstruct
    public void init() {
    }

}
