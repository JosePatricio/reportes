/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.model;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pisama
 */
@Entity
@Table(name = "producto_cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoCliente.findAll", query = "SELECT p FROM ProductoCliente p")})
public class ProductoCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "serie")
    private String serie;
    @Basic(optional = false)
    @Column(name = "estado")
    private int estado;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Producto idProducto;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente idCliente;
    @OneToMany(mappedBy = "idProductoCliente")
    private List<AsignacionReparacion> asignacionReparacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProductoCliente")
    private List<ProductoClienteReporte> productoClienteReporteList;

    public ProductoCliente() {
    }

    public ProductoCliente(Integer id) {
        this.id = id;
    }

    public ProductoCliente(Integer id, int estado) {
        this.id = id;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public List<AsignacionReparacion> getAsignacionReparacionList() {
        return asignacionReparacionList;
    }

    public void setAsignacionReparacionList(List<AsignacionReparacion> asignacionReparacionList) {
        this.asignacionReparacionList = asignacionReparacionList;
    }

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
        if (!(object instanceof ProductoCliente)) {
            return false;
        }
        ProductoCliente other = (ProductoCliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "ProductoCliente{" + "id=" + id + ", serie=" + serie + ", idProducto=" + idProducto + ", idCliente=" + idCliente + '}';
    }

}
