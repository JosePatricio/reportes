/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.model.DTO;

import java.io.Serializable;

/**
 *
 * @author Fernando
 */
public class NotificacionDTO implements Serializable {

    private Integer count;
    private Integer id;
    private String fechaInicio;
    private String horaInicio;
    private String fechaFin;
    private String horaFin;
    private String tecnico;
    private String cliente;
    private String tipoReporte;
    private String estadoNotificacion;
    private Integer prioridad;
    private String tipoNotificacion;
    private Integer idCliente;
    private Integer idClienteProducto;
    private Integer idTecnico;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getEstadoNotificacion() {
        return estadoNotificacion;
    }

    public void setEstadoNotificacion(String estadoNotificacion) {
        this.estadoNotificacion = estadoNotificacion;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdClienteProducto() {
        return idClienteProducto;
    }

    public void setIdClienteProducto(Integer idClienteProducto) {
        this.idClienteProducto = idClienteProducto;
    }

    public Integer getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(Integer idTecnico) {
        this.idTecnico = idTecnico;
    }

    @Override
    public String toString() {
        return "NotificacionDTO{" + "count=" + count + ", id=" + id + ", fechaInicio=" + fechaInicio + ", horaInicio=" + horaInicio + ", fechaFin=" + fechaFin + ", horaFin=" + horaFin + ", tecnico=" + tecnico + ", cliente=" + cliente + ", tipoReporte=" + tipoReporte + ", estadoNotificacion=" + estadoNotificacion + ", prioridad=" + prioridad + ", tipoNotificacion=" + tipoNotificacion + ", idCliente=" + idCliente + ", idClienteProducto=" + idClienteProducto + ", idTecnico=" + idTecnico + '}';
    }

}
