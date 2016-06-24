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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "producto_cliente_reporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoClienteReporte.findAll", query = "SELECT p FROM ProductoClienteReporte p")})
public class ProductoClienteReporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "atencion")
    private String atencion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "departamento")
    private String departamento;
    @Size(max = 150)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "ip_equipo")
    private String ipEquipo;
    @Size(max = 200)
    @Column(name = "direccion_equipo")
    private String direccionEquipo;
    @Size(max = 50)
    @Column(name = "telefono_equipo")
    private String telefonoEquipo;
    @Size(max = 150)
    @Column(name = "correo_equipo")
    private String correoEquipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProductoClienteReporte", fetch = FetchType.EAGER)
    private List<ReporteMantenimiento> reporteMantenimientoList;
    @JoinColumn(name = "id_producto_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProductoCliente idProductoCliente;
    @JoinColumn(name = "id_reporte", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Reporte idReporte;
    @JoinColumn(name = "id_producto_detalle_reporte", referencedColumnName = "id")
    @ManyToOne
    private ProductoDetalleReporte idProductoDetalleReporte;
    
    @JoinColumn(name = "id_detalle_reporte_instalacion_nueva", referencedColumnName = "id")
    @OneToOne
    private DetalleReporteInstalacionNueva idDetalleReporteInstalacionNueva;
    
    @JoinColumn(name = "id_detalle_reporte_temporal", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private DetalleReporteTemporal idDetalleReporteTemporal;

    public ProductoClienteReporte() {
    }

    public ProductoClienteReporte(Integer id) {
        this.id = id;
    }

    public ProductoClienteReporte(Integer id, String atencion, String departamento, String ipEquipo) {
        this.id = id;
        this.atencion = atencion;
        this.departamento = departamento;
        this.ipEquipo = ipEquipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAtencion() {
        return atencion;
    }

    public void setAtencion(String atencion) {
        this.atencion = atencion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getIpEquipo() {
        return ipEquipo;
    }

    public void setIpEquipo(String ipEquipo) {
        this.ipEquipo = ipEquipo;
    }

    public String getDireccionEquipo() {
        return direccionEquipo;
    }

    public void setDireccionEquipo(String direccionEquipo) {
        this.direccionEquipo = direccionEquipo;
    }

    public String getTelefonoEquipo() {
        return telefonoEquipo;
    }

    public void setTelefonoEquipo(String telefonoEquipo) {
        this.telefonoEquipo = telefonoEquipo;
    }

    public String getCorreoEquipo() {
        return correoEquipo;
    }

    public void setCorreoEquipo(String correoEquipo) {
        this.correoEquipo = correoEquipo;
    }

    @XmlTransient
    public List<ReporteMantenimiento> getReporteMantenimientoList() {
        return reporteMantenimientoList;
    }

    public void setReporteMantenimientoList(List<ReporteMantenimiento> reporteMantenimientoList) {
        this.reporteMantenimientoList = reporteMantenimientoList;
    }

    public ProductoCliente getIdProductoCliente() {
        return idProductoCliente;
    }

    public void setIdProductoCliente(ProductoCliente idProductoCliente) {
        this.idProductoCliente = idProductoCliente;
    }

    public Reporte getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Reporte idReporte) {
        this.idReporte = idReporte;
    }

    public ProductoDetalleReporte getIdProductoDetalleReporte() {
        return idProductoDetalleReporte;
    }

    public void setIdProductoDetalleReporte(ProductoDetalleReporte idProductoDetalleReporte) {
        this.idProductoDetalleReporte = idProductoDetalleReporte;
    }

    public DetalleReporteInstalacionNueva getIdDetalleReporteInstalacionNueva() {
        return idDetalleReporteInstalacionNueva;
    }

    public void setIdDetalleReporteInstalacionNueva(DetalleReporteInstalacionNueva idDetalleReporteInstalacionNueva) {
        this.idDetalleReporteInstalacionNueva = idDetalleReporteInstalacionNueva;
    }

    public DetalleReporteTemporal getIdDetalleReporteTemporal() {
        return idDetalleReporteTemporal;
    }

    public void setIdDetalleReporteTemporal(DetalleReporteTemporal idDetalleReporteTemporal) {
        this.idDetalleReporteTemporal = idDetalleReporteTemporal;
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
        if (!(object instanceof ProductoClienteReporte)) {
            return false;
        }
        ProductoClienteReporte other = (ProductoClienteReporte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductoClienteReporte{" + "id=" + id + ", atencion=" + atencion + ", departamento=" + departamento 
                + ", ciudad=" + ciudad + ", ipEquipo=" + ipEquipo + ", direccionEquipo=" + direccionEquipo + ", telefonoEquipo=" + telefonoEquipo + 
                ", correoEquipo=" + correoEquipo + ", idProductoCliente=" + idProductoCliente.getId() + ", idReporte=" + idReporte.getId() + 
                ", idProductoDetalleReporte=" + idProductoDetalleReporte + ", idDetalleReporteInstalacionNueva=" + idDetalleReporteInstalacionNueva + ", idDetalleReporteTemporal=" 
                + idDetalleReporteTemporal + '}';
    }

    

}
