/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.ClienteBean;
import com.innovaciones.reporte.model.Cliente;
import com.innovaciones.reporte.service.ClienteService;
import com.innovaciones.reporte.util.AsignacionReparacionEnum;
import com.innovaciones.reporte.util.EstadosEnum;
import com.innovaciones.reporte.util.Utilities;

import java.io.Serializable;
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
@ManagedBean(name = "clienteController")
@ViewScoped
public class ClienteController extends Utilities implements Serializable {

    @Getter
    @Setter
    @ManagedProperty("#{clienteService}")
    private ClienteService clienteService;

    @Getter
    @Setter
    @ManagedProperty("#{clienteBean}")
    private ClienteBean clienteBean;

    /**
     * Creates a new instance of UsuariosController
     */
    public ClienteController() {
    }

    @PostConstruct
    void init() {
        clienteBean.setListClientes(clienteService.getClientes());
        clienteBean.setListEstados(cargarEstadosBoolean());
        clienteBean.setListPrioridades(cargarPrioridadAsignaciones());
    }

    public void abrirDialogCliente(Cliente cliente) {

        if (cliente == null) {
            clienteBean.setCliente(new Cliente());
            clienteBean.setEstado(EstadosEnum.ACTIVO.getValue());
            clienteBean.setPrioridadSelected(null);
        } else {
            clienteBean.setCliente(cliente);
            clienteBean.setClienteSelected(cliente);
            clienteBean.setEstado(cliente.getEstado());
             clienteBean.setPrioridadSelected(clienteBean.getCliente().getPrioridad());
            clienteBean.getCliente().setPrioridad(clienteBean.getPrioridadSelected());
        }
        update("formEditClientes");
        openModalBS("dlgClientes");

    }

    public void guardar() {
        clienteBean.getCliente().setEstado(clienteBean.getEstado());
        clienteBean.getCliente().setPrioridad(clienteBean.getPrioridadSelected());
        clienteBean.setCliente((Cliente) toUpperCaseStrings(clienteBean.getCliente()));

        clienteService.addCliente(clienteBean.getCliente());
        clienteBean.setListClientes(clienteService.getClientes());
        info("Guardado exitósamente");
        update("tblClientes");
        cerrarDialog();
    }

    public void cerrarDialog() {
        closeModalBS("dlgClientes");
    }
}
