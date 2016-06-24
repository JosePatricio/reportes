/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pisama
 */
@Entity
@Table(name = "reporte_mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReporteMantenimiento.findAll", query = "SELECT r FROM ReporteMantenimiento r")})
public class ReporteMantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 15)
    @Column(name = "codigo_repuesto")
    private String codigoRepuesto;

    @Size(max = 1)
    @Column(name = "tipo_repuesto")
    private String tipoRepuesto;

    @Column(name = "porcentaje")
    private BigDecimal porcentaje;

    @JoinColumn(name = "id_producto_cliente_reporte", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProductoClienteReporte idProductoClienteReporte;

    
    
    
    
    
    @JoinColumn(name = "id_repuesto_modelo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RepuestoModelo idRepuestoModelo;

    @Basic(optional = false)
    @Column(name = "estado")
    private Integer estado;

    public ReporteMantenimiento() {
    }

    public ReporteMantenimiento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public ProductoClienteReporte getIdProductoClienteReporte() {
        return idProductoClienteReporte;
    }

    public void setIdProductoClienteReporte(ProductoClienteReporte idProductoClienteReporte) {
        this.idProductoClienteReporte = idProductoClienteReporte;
    }

    public RepuestoModelo getIdRepuestoModelo() {
        return idRepuestoModelo;
    }

    public void setIdRepuestoModelo(RepuestoModelo idRepuestoModelo) {
        this.idRepuestoModelo = idRepuestoModelo;
    }


    

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
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
        if (!(object instanceof ReporteMantenimiento)) {
            return false;
        }
        ReporteMantenimiento other = (ReporteMantenimiento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReporteMantenimiento{" + "id=" + id + ","
                + " codigoRepuesto=" + codigoRepuesto+" , porcentaje ="+porcentaje
                + ", tipoRepuesto=" + tipoRepuesto + ", " + ", ESTADO=" + estado + " ,"
                + "idProductoClienteReporte=" + idProductoClienteReporte.getId()
                + ", idRepuestoModelo=(" + idRepuestoModelo.getId() + " )" + '}';
    }

}
