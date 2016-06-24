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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "equipo")
    private String equipo;
    @Size(max = 250)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
   // @Size(min = 1, max = 50)
    @Column(name = "version_firmware")
    private String versionFirmware;
    @Basic(optional = false)
    @NotNull
   // @Size(min = 1, max = 50)
    @Column(name = "campo_1")
    private String campo1;
    @Basic(optional = false)
    @NotNull
    //@Size(min = 1, max = 50)
    @Column(name = "campo_2")
    private String campo2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private Integer estado;
    @JoinColumn(name = "id_modelo", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Modelo idModelo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private List<ProductoCliente> productoClienteList;

      @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Categoria idCategoria;
      
    public Producto() {
    }

    public Producto(Integer id) {
        this.id = id;
    }

    public Producto(Integer id, String equipo, String versionFirmware, String campo1, String campo2, Integer estado) {
        this.id = id;
        this.equipo = equipo;
        this.versionFirmware = versionFirmware;
        this.campo1 = campo1;
        this.campo2 = campo2;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getVersionFirmware() {
        return versionFirmware;
    }

    public void setVersionFirmware(String versionFirmware) {
        this.versionFirmware = versionFirmware;
    }

    public String getCampo1() {
        return campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }

    public String getCampo2() {
        return campo2;
    }

    public void setCampo2(String campo2) {
        this.campo2 = campo2;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Modelo getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(Modelo idModelo) {
        this.idModelo = idModelo;
    }

    @XmlTransient
    public List<ProductoCliente> getProductoClienteList() {
        return productoClienteList;
    }

    public void setProductoClienteList(List<ProductoCliente> productoClienteList) {
        this.productoClienteList = productoClienteList;
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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", equipo=" + equipo 
                + ", descripcion=" + descripcion + ", versionFirmware="
                + versionFirmware + ", campo1=" + campo1 + ", campo2=" + campo2 + 
                ", estado=" + estado + ", idModelo=" + idModelo + '}';
    }


    
    
}
