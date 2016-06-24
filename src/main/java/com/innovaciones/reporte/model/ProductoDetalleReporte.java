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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pisama
 */
@Entity
@Table(name = "producto_detalle_reporte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductoDetalleReporte.findAll", query = "SELECT p FROM ProductoDetalleReporte p")})
public class ProductoDetalleReporte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "contador_total_anterior")
    private Integer contadorTotalAnterior;
    @Column(name = "contador_color_anterior")
    private Integer contadorColorAnterior;
    @Column(name = "contador_bn_anterior")
    private Integer contadorBnAnterior;
    @Column(name = "contador_total_actual")
    private Integer contadorTotalActual;
    @Column(name = "contador_color_actual")
    private Integer contadorColorActual;
    @Column(name = "contador_bn_actual")
    private Integer contadorBnActual;
    @Column(name = "contador_total_imp_real")
    private Integer contadorTotalImpReal;
    @Column(name = "contador_color_imp_real")
    private Integer contadorColorImpReal;
    @Column(name = "contador_bn_imp_real")
    private Integer contadorBnImpReal;
    @Column(name = "mantenimiento")
    private Integer mantenimiento;
    @Column(name = "otros")
    private Integer otros;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "servicio_facturar")
    private BigDecimal servicioFacturar;
    @Column(name = "servicio_facturar_estado")
    private Boolean servicioFacturarEstado;
    @OneToMany(mappedBy = "idProductoDetalleReporte")
    private List<ProductoClienteReporte> productoClienteReporteList;

    public ProductoDetalleReporte() {
    }

    public ProductoDetalleReporte(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContadorTotalAnterior() {
        return contadorTotalAnterior;
    }

    public void setContadorTotalAnterior(Integer contadorTotalAnterior) {
        this.contadorTotalAnterior = contadorTotalAnterior;
    }

    public Integer getContadorColorAnterior() {
        return contadorColorAnterior;
    }

    public void setContadorColorAnterior(Integer contadorColorAnterior) {
        this.contadorColorAnterior = contadorColorAnterior;
    }

    public Integer getContadorBnAnterior() {
        return contadorBnAnterior;
    }

    public void setContadorBnAnterior(Integer contadorBnAnterior) {
        this.contadorBnAnterior = contadorBnAnterior;
    }

    public Integer getContadorTotalActual() {
        return contadorTotalActual;
    }

    public void setContadorTotalActual(Integer contadorTotalActual) {
        this.contadorTotalActual = contadorTotalActual;
    }

    public Integer getContadorColorActual() {
        return contadorColorActual;
    }

    public void setContadorColorActual(Integer contadorColorActual) {
        this.contadorColorActual = contadorColorActual;
    }

    public Integer getContadorBnActual() {
        return contadorBnActual;
    }

    public void setContadorBnActual(Integer contadorBnActual) {
        this.contadorBnActual = contadorBnActual;
    }

    public Integer getContadorTotalImpReal() {
        return contadorTotalImpReal;
    }

    public void setContadorTotalImpReal(Integer contadorTotalImpReal) {
        this.contadorTotalImpReal = contadorTotalImpReal;
    }

    public Integer getContadorColorImpReal() {
        return contadorColorImpReal;
    }

    public void setContadorColorImpReal(Integer contadorColorImpReal) {
        this.contadorColorImpReal = contadorColorImpReal;
    }

    public Integer getContadorBnImpReal() {
        return contadorBnImpReal;
    }

    public void setContadorBnImpReal(Integer contadorBnImpReal) {
        this.contadorBnImpReal = contadorBnImpReal;
    }

    public Integer getMantenimiento() {
        return mantenimiento;
    }

    public void setMantenimiento(Integer mantenimiento) {
        this.mantenimiento = mantenimiento;
    }

    public Integer getOtros() {
        return otros;
    }

    public void setOtros(Integer otros) {
        this.otros = otros;
    }

    public BigDecimal getServicioFacturar() {
        return servicioFacturar;
    }

    public void setServicioFacturar(BigDecimal servicioFacturar) {
        this.servicioFacturar = servicioFacturar;
    }

    public Boolean getServicioFacturarEstado() {
        return servicioFacturarEstado;
    }

    public void setServicioFacturarEstado(Boolean servicioFacturarEstado) {
        this.servicioFacturarEstado = servicioFacturarEstado;
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
        if (!(object instanceof ProductoDetalleReporte)) {
            return false;
        }
        ProductoDetalleReporte other = (ProductoDetalleReporte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductoDetalleReporte{" + "id=" + id + ", contadorTotalAnterior=" + contadorTotalAnterior + ", contadorColorAnterior=" + contadorColorAnterior 
                + ", contadorBnAnterior=" + contadorBnAnterior + ", contadorTotalActual=" + contadorTotalActual + ", contadorColorActual=" + contadorColorActual 
                + ", contadorBnActual=" + contadorBnActual + ", contadorTotalImpReal=" + contadorTotalImpReal + ", contadorColorImpReal=" + contadorColorImpReal
                + ", contadorBnImpReal=" + contadorBnImpReal + ", mantenimiento=" + mantenimiento + ", otros=" + otros + ", servicioFacturar=" + servicioFacturar + 
                ", servicioFacturarEstado=" + servicioFacturarEstado + '}';
    }

  
    
    
}
