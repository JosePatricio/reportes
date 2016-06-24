/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "repuesto_modelo", catalog = "reportes", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepuestoModelo.findAll", query = "SELECT r FROM RepuestoModelo r")})
public class RepuestoModelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    private String descripcion;

    
    @Basic(optional = false)
    private Integer estado;

  @Basic(optional = false)
      @Column(name = "es_catalogo")
    private Boolean esCatalogo;   
    
    
    

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;

    @Column(name = "usuario_modificacion")
    private String usuarioModificacion;
    @Basic(optional = false)

    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @Size(max = 20, message = "El tamaño máximo de código es de 20 caracteres")
    @Column(name = "codigo_repuesto")
    private String codigoRepuesto;

    @JoinColumn(name = "id_modelo", referencedColumnName = "id")
    @ManyToOne
    private Modelo idModelo;

    @JoinColumn(name = "id_detalle_catalogo_reporte", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DetalleCatalogoReporte idDetalleCatalogoReporte;

     @Transient
    private BigDecimal porcentaje;

    @Transient
    private Boolean seleccion;

    @Transient
    private String tipoRepuesto;
    
    public RepuestoModelo() {
    }

    public RepuestoModelo(Modelo idModelo, DetalleCatalogoReporte idDetalleCatalogoReporte) {
        this.idModelo = idModelo;
        this.idDetalleCatalogoReporte = idDetalleCatalogoReporte;
    }

    
    
    public RepuestoModelo(Integer id) {
        this.id = id;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

  
    

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Modelo getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Modelo idModelo) {
        this.idModelo = idModelo;
    }

    public DetalleCatalogoReporte getIdDetalleCatalogoReporte() {
        return idDetalleCatalogoReporte;
    }

    public void setIdDetalleCatalogoReporte(DetalleCatalogoReporte idDetalleCatalogoReporte) {
        this.idDetalleCatalogoReporte = idDetalleCatalogoReporte;
    }

    

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RepuestoModelo)) {
            return false;
        }
        RepuestoModelo other = (RepuestoModelo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    

    

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(String usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getCodigoRepuesto() {
        return codigoRepuesto;
    }

    public void setCodigoRepuesto(String codigoRepuesto) {
        this.codigoRepuesto = codigoRepuesto;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Boolean getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Boolean seleccion) {
        this.seleccion = seleccion;
    }

    public String getTipoRepuesto() {
        return tipoRepuesto;
    }

    public void setTipoRepuesto(String tipoRepuesto) {
        this.tipoRepuesto = tipoRepuesto;
    }

    public Boolean getEsCatalogo() {
        return esCatalogo;
    }

    public void setEsCatalogo(Boolean esCatalogo) {
        this.esCatalogo = esCatalogo;
    }

    
    
    @Override
    public String toString() {
        return "RepuestoModelo{" + "id=" + id +  ", estado=" + estado + ", stock=" + stock + ", codigoRepuesto=" + codigoRepuesto +
                ", idModelo=" + idModelo.getId() + ", idDetalleCatalogoReporte=" + idDetalleCatalogoReporte.getId()+" , "+idDetalleCatalogoReporte.getDescripcion()
                + ", porcentaje=" + porcentaje + ", seleccion=" + seleccion + ", tipoRepuesto=" + tipoRepuesto+" ,UsuarioCreacion="+usuarioCreacion+" , fechaCreacion"+fechaCreacion + '}';
    }

 
    
    
    
    
}
