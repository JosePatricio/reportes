/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.model;

import com.innovaciones.reporte.util.Enums;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "configuracion", catalog = "reportes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Configuracion.findAll", query = "SELECT c FROM Configuracion c")})
public class Configuracion implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false, length = 15)
    private String codigo;

    @Column(nullable = false, length = 13)
    private String ruc;

    @Column(name = "nombre_empresa", nullable = false, length = 250)
    private String nombreEmpresa;

    @Column(nullable = true, length = 150)
    private String acronimo;

    @Column(nullable = false, length = 150)
    private String direccion;

    @Column(nullable = false, length = 20)
    private String telefono1;
    @Column(length = 20)
    private String telefono2;
    @Column(length = 20)
    private String telefono3;
    @Column(length = 20)
    private String telefono4;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false, length = 50)
    private String ciudad;

    @Column(nullable = false, length = 50)
    private String pais;

    @Column(length = 50)
    private String url;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @Column(name = "cedula_representante", nullable = false, length = 15)
    private String cedulaRepresentante;

    @Column(name = "nombre_representante", nullable = false, length = 150)
    private String nombreRepresentante;

    @Column(name = "telefono_representante", nullable = true, length = 20)
    private String telefonoRepresentante;

    @Column(name = "celular_representante", nullable = false, length = 20)
    private String celularRepresentante;

    @Column(name = "email_representante", nullable = false, length = 150)
    private String emailRepresentante;

    @Column(nullable = false)
    private int estado;

    public Configuracion() {
    }

    public Configuracion(Integer id) {
        this.id = id;
    }

    public Configuracion(Integer id, String codigo, String ruc, String nombreEmpresa, String acronimo, String direccion, String telefono1, String email, String ciudad, String pais, byte[] logo, String cedulaRepresentante, String nombreRepresentante, String telefonoRepresentante, String celularRepresentante, String emailRepresentante, int estado) {
        this.id = id;
        this.codigo = codigo;
        this.ruc = ruc;
        this.nombreEmpresa = nombreEmpresa;
        this.acronimo = acronimo;
        this.direccion = direccion;
        this.telefono1 = telefono1;
        this.email = email;
        this.ciudad = ciudad;
        this.pais = pais;
        this.logo = logo;
        this.cedulaRepresentante = cedulaRepresentante;
        this.nombreRepresentante = nombreRepresentante;
        this.telefonoRepresentante = telefonoRepresentante;
        this.celularRepresentante = celularRepresentante;
        this.emailRepresentante = emailRepresentante;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getTelefono3() {
        return telefono3;
    }

    public void setTelefono3(String telefono3) {
        this.telefono3 = telefono3;
    }

    public String getTelefono4() {
        return telefono4;
    }

    public void setTelefono4(String telefono4) {
        this.telefono4 = telefono4;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getCedulaRepresentante() {
        return cedulaRepresentante;
    }

    public void setCedulaRepresentante(String cedulaRepresentante) {
        this.cedulaRepresentante = cedulaRepresentante;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public String getTelefonoRepresentante() {
        return telefonoRepresentante;
    }

    public void setTelefonoRepresentante(String telefonoRepresentante) {
        this.telefonoRepresentante = telefonoRepresentante;
    }

    public String getCelularRepresentante() {
        return celularRepresentante;
    }

    public void setCelularRepresentante(String celularRepresentante) {
        this.celularRepresentante = celularRepresentante;
    }

    public String getEmailRepresentante() {
        return emailRepresentante;
    }

    public void setEmailRepresentante(String emailRepresentante) {
        this.emailRepresentante = emailRepresentante;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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
        if (!(object instanceof Configuracion)) {
            return false;
        }
        Configuracion other = (Configuracion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Configuracion{" + "id=" + id + ", codigo=" + codigo + ", ruc=" + ruc + ", nombreEmpresa=" + nombreEmpresa + ", acronimo=" + acronimo + ", direccion=" + direccion + ", telefono1=" + telefono1 + ", telefono2=" + telefono2 + ", telefono3=" + telefono3 + ", telefono4=" + telefono4 + ", email=" + email + ", ciudad=" + ciudad + ", pais=" + pais + ", url=" + url + ", logo=" + logo + ", cedulaRepresentante=" + cedulaRepresentante + ", nombreRepresentante=" + nombreRepresentante + ", telefonoRepresentante=" + telefonoRepresentante + ", celularRepresentante=" + celularRepresentante + ", emailRepresentante=" + emailRepresentante + ", estado=" + estado + '}';
    }

    public void setCodigo(Enums enums) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
