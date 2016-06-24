/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pisama
 */
@Entity
@Table(name = "detalle_catalogo_reporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleCatalogoReporte.findAll", query = "SELECT d FROM DetalleCatalogoReporte d")})
public class DetalleCatalogoReporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)

    @Column(name = "descripcion")
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "estado")
    private boolean estado;

    @Column(name = "catalogo")
    private Boolean catalogo;

    @Column(name = "orden")
    private Integer orden;

    @Basic(optional = false)

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRepuestoModelo")
    private List<ReporteMantenimiento> reporteMantenimientoList;

    @JoinColumn(name = "id_cabecera", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CabeceraCatalogoReporte idCabecera;

    @Transient
    private String codigoRepuesto;

    @Transient
    private Integer stock;

    @Transient
    private BigDecimal porcentaje;

    @Transient
    private Boolean seleccion;

    @Transient
    private String tipoRepuesto;

    @Getter
    @Setter
    @Transient
    private Integer idRepuestoModelo;

    @Getter
    @Setter
    @Transient
    private Integer idModelo;

    public DetalleCatalogoReporte() {
    }

    public DetalleCatalogoReporte(Integer id) {
        this.id = id;
    }

    public DetalleCatalogoReporte(Integer id, String descripcion, boolean estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
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

    public String getCodigoRepuesto() {
        return codigoRepuesto;
    }

    public void setCodigoRepuesto(String codigoRepuesto) {
        this.codigoRepuesto = codigoRepuesto;
    }

    public String getTipoRepuesto() {
        return tipoRepuesto;
    }

    public void setTipoRepuesto(String tipoRepuesto) {
        this.tipoRepuesto = tipoRepuesto;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Boolean getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Boolean seleccion) {
        this.seleccion = seleccion;
    }

    public Boolean getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Boolean catalogo) {
        this.catalogo = catalogo;
    }

    @XmlTransient
    public List<ReporteMantenimiento> getReporteMantenimientoList() {
        return reporteMantenimientoList;
    }

    public void setReporteMantenimientoList(List<ReporteMantenimiento> reporteMantenimientoList) {
        this.reporteMantenimientoList = reporteMantenimientoList;
    }

    public CabeceraCatalogoReporte getIdCabecera() {
        return idCabecera;
    }

    public void setIdCabecera(CabeceraCatalogoReporte idCabecera) {
        this.idCabecera = idCabecera;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    /* public static Comparator<DetalleCatalogoReporte> sortByDescripcion = new Comparator<DetalleCatalogoReporte>() {
        @Override
        public int compare(DetalleCatalogoReporte s1, DetalleCatalogoReporte s2) {
            String detalle1 = s1.getDescripcion().toUpperCase();
            String detalle2 = s2.getDescripcion().toUpperCase();
            //ascending order
            return detalle1.compareTo(detalle2);
            //descending order
            //return detalle2.compareTo(detalle1);
        }
    };
     */
    public static Comparator<DetalleCatalogoReporte> sortByOrden = new Comparator<DetalleCatalogoReporte>() {
        @Override
        public int compare(DetalleCatalogoReporte s1, DetalleCatalogoReporte s2) {
            int ordern1 = s1.getOrden();
            int orden2 = s2.getOrden();
            /*For ascending order*/
            return ordern1 - orden2;
            /*For descending order*/
            //detalle2-detalle1;
        }
    };

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleCatalogoReporte)) {
            return false;
        }
        DetalleCatalogoReporte other = (DetalleCatalogoReporte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleCatalogoReporte{" + "id=" + id
                + ", descripcion=" + descripcion + ", seleccion =" + seleccion
                + ", codigoRepuesto=" + codigoRepuesto + " , tipoRepuesto=" + tipoRepuesto + " , estado=" + estado + ", orden " + orden
                + ", catalogo=" + catalogo + ", stock=" + stock
                + ", idCabecera=" + idCabecera.getId() + " ," + idCabecera.getCodigo() + " , idRepuestoModelo " + idRepuestoModelo + " , idModelo=" + idModelo + " ,UsuarioCreacion=" + usuarioCreacion + " , fechaCreacion" + fechaCreacion + '}';
    }

}
