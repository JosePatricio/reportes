/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.model;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pisama
 */
@Entity
@Table(name = "cabecera_catalogo_reporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CabeceraCatalogoReporte.findAll", query = "SELECT c FROM CabeceraCatalogoReporte c")})
public class CabeceraCatalogoReporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "codigo")
    private String codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private boolean estado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCabecera")
    private List<DetalleCatalogoReporte> detalleCatalogoReporteList;

    public CabeceraCatalogoReporte() {
    }

    public CabeceraCatalogoReporte(Integer id) {
        this.id = id;
    }

    public CabeceraCatalogoReporte(Integer id, String codigo) {
        this.id = id;
        this.codigo = codigo;
    }
    
  

    public CabeceraCatalogoReporte(Integer id, String tipo, String codigo, String descripcion, boolean estado) {
        this.id = id;
        this.tipo = tipo;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<DetalleCatalogoReporte> getDetalleCatalogoReporteList() {
        return detalleCatalogoReporteList;
    }

    public void setDetalleCatalogoReporteList(List<DetalleCatalogoReporte> detalleCatalogoReporteList) {
        this.detalleCatalogoReporteList = detalleCatalogoReporteList;
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
        if (!(object instanceof CabeceraCatalogoReporte)) {
            return false;
        }
        CabeceraCatalogoReporte other = (CabeceraCatalogoReporte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CabeceraCatalogoReporte{" + "id=" + id + ", tipo=" + tipo + ", codigo=" + codigo + ", descripcion=" + descripcion + ", estado=" + estado + '}';
    }

   
    
    
}
