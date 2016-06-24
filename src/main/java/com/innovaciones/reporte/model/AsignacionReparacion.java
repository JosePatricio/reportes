/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "asignacion_reparacion")
@NamedQueries({
    @NamedQuery(name = "AsignacionReparacion.findAll", query = "SELECT a FROM AsignacionReparacion a")})
public class AsignacionReparacion implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Integer id;

    @Column(name = "fecha_inicio_atencion")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioAtencion;

    @Column(name = "hora_inicio_atencion")
    @Temporal(TemporalType.TIME)
    private Date horaInicioAtencion;

    @Column(name = "fecha_fin_atencion")
    @Temporal(TemporalType.DATE)
    private Date fechaFinAtencion;

    @Column(name = "hora_fin_atencion")
    @Temporal(TemporalType.TIME)
    private Date horaFinAtencion;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @Column(name = "id_usuario_registro")
    private Integer idUsuarioRegistro;

    @Column(name = "nombre_usuario_registro")
    private String nombreUsuarioRegistro;

    @Column(name = "prioridad")
    private int prioridad;

    @Column(name = "observacion")
    private String observacion;

    @Column(name = "tipo_reporte")
    private String tipoReporte;

    @Column(name = "tipo_notificacion")
    private String tipoNotificacion;

    @Column(name = "estado")
    private String estado;

    @Column(name = "id_reporte")
    private Integer idReporte;

    @Column(name = "codigo")
    private String codigo;

    @JoinColumn(name = "id_producto_cliente", referencedColumnName = "id")
    @ManyToOne
    private ProductoCliente idProductoCliente;

    @JoinColumn(name = "id_usuario_atencion", referencedColumnName = "id")
    @ManyToOne
    private Usuarios idUsuarioAtencion;

    public AsignacionReparacion() {
    }

    public AsignacionReparacion(Integer id) {
        this.id = id;
    }

    public AsignacionReparacion(Integer id, Date fechaInicioAtencion, Date horaInicioAtencion, Date fechaFinAtencion, Date horaFinAtencion, Date fechaRegistro, Integer idUsuarioRegistro, String nombreUsuarioRegistro, int prioridad, String observacion, String tipoReporte, String estado, ProductoCliente idProductoCliente, Usuarios idUsuarioAtencion) {
        this.id = id;
        this.fechaInicioAtencion = fechaInicioAtencion;
        this.horaInicioAtencion = horaInicioAtencion;
        this.fechaFinAtencion = fechaFinAtencion;
        this.horaFinAtencion = horaFinAtencion;
        this.fechaRegistro = fechaRegistro;
        this.idUsuarioRegistro = idUsuarioRegistro;
        this.nombreUsuarioRegistro = nombreUsuarioRegistro;
        this.prioridad = prioridad;
        this.observacion = observacion;
        this.tipoReporte = tipoReporte;
        this.estado = estado;
        this.idProductoCliente = idProductoCliente;
        this.idUsuarioAtencion = idUsuarioAtencion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicioAtencion() {
        return fechaInicioAtencion;
    }

    public void setFechaInicioAtencion(Date fechaInicioAtencion) {
        this.fechaInicioAtencion = fechaInicioAtencion;
    }

    public Date getHoraInicioAtencion() {
        return horaInicioAtencion;
    }

    public void setHoraInicioAtencion(Date horaInicioAtencion) {
        this.horaInicioAtencion = horaInicioAtencion;
    }

    public Date getFechaFinAtencion() {
        return fechaFinAtencion;
    }

    public void setFechaFinAtencion(Date fechaFinAtencion) {
        this.fechaFinAtencion = fechaFinAtencion;
    }

    public Date getHoraFinAtencion() {
        return horaFinAtencion;
    }

    public void setHoraFinAtencion(Date horaFinAtencion) {
        this.horaFinAtencion = horaFinAtencion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Integer idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }

    public String getNombreUsuarioRegistro() {
        return nombreUsuarioRegistro;
    }

    public void setNombreUsuarioRegistro(String nombreUsuarioRegistro) {
        this.nombreUsuarioRegistro = nombreUsuarioRegistro;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ProductoCliente getIdProductoCliente() {
        return idProductoCliente;
    }

    public void setIdProductoCliente(ProductoCliente idProductoCliente) {
        this.idProductoCliente = idProductoCliente;
    }

    public Usuarios getIdUsuarioAtencion() {
        return idUsuarioAtencion;
    }

    public void setIdUsuarioAtencion(Usuarios idUsuarioAtencion) {
        this.idUsuarioAtencion = idUsuarioAtencion;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public Integer getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Integer idReporte) {
        this.idReporte = idReporte;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsignacionReparacion)) {
            return false;
        }
        AsignacionReparacion other = (AsignacionReparacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "AsignacionReparacion{" + "id=" + id + ", fechaInicioAtencion=" + fechaInicioAtencion + ", horaInicioAtencion=" + horaInicioAtencion + ", fechaFinAtencion=" + fechaFinAtencion + ", horaFinAtencion=" + horaFinAtencion + ", fechaRegistro=" + fechaRegistro + ", idUsuarioRegistro=" + idUsuarioRegistro + ", nombreUsuarioRegistro=" + nombreUsuarioRegistro + ", prioridad=" + prioridad + ", observacion=" + observacion + ", tipoReporte=" + tipoReporte + ", tipoNotificacion=" + tipoNotificacion + ", estado=" + estado + ", idReporte=" + idReporte + ", codigo=" + codigo + ", idProductoCliente=" + idProductoCliente + ", idUsuarioAtencion=" + idUsuarioAtencion + '}';
    }

}
