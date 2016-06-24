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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pisama
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")})
public class Usuarios implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "dni")
    private String dni;
    @Size(max = 50)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 150)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 150)
    @Column(name = "apellido")
    private String apellido;
    @Size(max = 50)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 50)
    @Column(name = "celular")
    private String celular;
    @Size(max = 150)
    @Column(name = "mail")
    private String mail;
    @Size(max = 250)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 50)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "clave")
    private String clave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado")
    private Integer estado;
    @Lob
    @Size(max = 65535)
    @Column(name = "firma_base64")
    private String firmaBase64;
    @Lob
    @Size(max = 65535)
    @Column(name = "firma")
    private String firma;
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.EAGER)
    private List<UsuarioRoles> usuarioRolesList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuario", fetch = FetchType.EAGER)
    private List<Reporte> reporteList;

    @OneToMany(mappedBy = "idUsuarioAtencion")
    private List<AsignacionReparacion> asignacionReparacionList;

    @Transient
    private String nombreCompleto;

    public Usuarios() {
        this.nombre = this.apellido = "";
    }

    public Usuarios(Integer id) {
        this.id = id;
        this.nombre = this.apellido = "";
    }

    public Usuarios(Integer id, String clave, Integer estado) {
        this.id = id;
        this.clave = clave;
        this.estado = estado;
        this.nombre = this.apellido = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getFirmaBase64() {
        return firmaBase64;
    }

    public void setFirmaBase64(String firmaBase64) {
        this.firmaBase64 = firmaBase64;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getNombreCompleto() {
        this.nombreCompleto = nombre + " " + apellido;
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    @XmlTransient
    public List<UsuarioRoles> getUsuarioRolesList() {
        return usuarioRolesList;
    }

    public void setUsuarioRolesList(List<UsuarioRoles> usuarioRolesList) {
        this.usuarioRolesList = usuarioRolesList;
    }

    @XmlTransient
    public List<Reporte> getReporteList() {
        return reporteList;
    }

    public void setReporteList(List<Reporte> reporteList) {
        this.reporteList = reporteList;
    }

    @XmlTransient
    public List<AsignacionReparacion> getAsignacionReparacionList() {
        return asignacionReparacionList;
    }

    public void setAsignacionReparacionList(List<AsignacionReparacion> asignacionReparacionList) {
        this.asignacionReparacionList = asignacionReparacionList;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
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
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Usuarios{" + "id=" + id + ", dni=" + dni + ", codigo=" + codigo + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", celular=" + celular + ", mail=" + mail + ", direccion=" + direccion + ", usuario=" + usuario + ", clave=" + clave + ", estado=" + estado + ", firmaBase64=" + firmaBase64 + ", firma=" + firma + ", nombreCompleto=" + nombreCompleto + " ,Imagen= " + imagen + "" + '}';
    }

}
