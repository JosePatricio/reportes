/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pisama
 */
@Entity
@Table(name = "detalle_reporte_instalacion_nueva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleReporteInstalacionNueva.findAll", query = "SELECT d FROM DetalleReporteInstalacionNueva d")})
public class DetalleReporteInstalacionNueva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "fase_neutro")
    private String faseNeutro;
    @Size(max = 100)
    @Column(name = "fase_tierra")
    private String faseTierra;
    @Size(max = 100)
    @Column(name = "neutro")
    private String neutro;
    @Size(max = 500)
    @Column(name = "observacion")
    private String observacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "voltaje_prevencion")
    private BigDecimal voltajePrevencion;
    @Size(max = 500)
    @Column(name = "descripcion_consideraciones")
    private String descripcionConsideraciones;
    @Size(max = 250)
    @Column(name = "nombre_operario")
    private String nombreOperario;
    @Size(max = 250)
    @Basic(optional = false)
    @Column(name = "nota",nullable = false)
    private String nota;
    
    @OneToMany(mappedBy = "idDetalleReporteInstalacionNueva")
    private List<ProductoClienteReporte> productoClienteReporteList;

    public DetalleReporteInstalacionNueva() {
    }

    public DetalleReporteInstalacionNueva(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFaseNeutro() {
        return faseNeutro;
    }

    public void setFaseNeutro(String faseNeutro) {
        this.faseNeutro = faseNeutro;
    }

    public String getFaseTierra() {
        return faseTierra;
    }

    public void setFaseTierra(String faseTierra) {
        this.faseTierra = faseTierra;
    }

    public String getNeutro() {
        return neutro;
    }

    public void setNeutro(String neutro) {
        this.neutro = neutro;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public BigDecimal getVoltajePrevencion() {
        return voltajePrevencion;
    }

    public void setVoltajePrevencion(BigDecimal voltajePrevencion) {
        this.voltajePrevencion = voltajePrevencion;
    }

    public String getDescripcionConsideraciones() {
        return descripcionConsideraciones;
    }

    public void setDescripcionConsideraciones(String descripcionConsideraciones) {
        this.descripcionConsideraciones = descripcionConsideraciones;
    }

    public String getNombreOperario() {
        return nombreOperario;
    }

    public void setNombreOperario(String nombreOperario) {
        this.nombreOperario = nombreOperario;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @XmlTransient
    public List<ProductoClienteReporte> getProductoClienteReporteList() {
        return productoClienteReporteList;
    }

    public void setProductoClienteReporteList(List<ProductoClienteReporte> productoClienteReporteList) {
        this.productoClienteReporteList = productoClienteReporteList;
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
        if (!(object instanceof DetalleReporteInstalacionNueva)) {
            return false;
        }
        DetalleReporteInstalacionNueva other = (DetalleReporteInstalacionNueva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DetalleReporteInstalacionNueva{" + "id=" + id + ", faseNeutro=" + faseNeutro + ", faseTierra=" + faseTierra + ", neutro=" + neutro + ", observacion=" + observacion
                + ", voltajePrevencion=" + voltajePrevencion + ", descripcionConsideraciones=" + descripcionConsideraciones + ", nombreOperario=" + nombreOperario
                + ", nota=" + nota + '}';
    }

}
