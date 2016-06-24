/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.beans;

import com.innovaciones.reporte.model.Rol;
import com.innovaciones.reporte.model.UsuarioRoles;
import com.innovaciones.reporte.model.Usuarios;
import com.innovaciones.reporte.util.EstadosEnum;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pisama
 */
@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

    /**
     * Creates a new instance of UsuarioBean
     */
    @Getter
    @Setter
    private List<Usuarios> listUsuarios;

    @Getter
    @Setter
    private Usuarios usuario;

    @Getter
    @Setter
    private Usuarios usuarioSelected;

    @Getter
    @Setter
    private List<Rol> listRol;

    @Getter
    @Setter
    private Boolean isEdit;

    @Getter
    @Setter
    private UsuarioRoles usuarioRoles;

    @Getter
    @Setter
    private List<EstadosEnum> listEstados;

    @Getter
    @Setter
    private List<Integer> listIdRolesByUsuario;

    @Getter
    @Setter
    private List<UsuarioRoles> listUsuarioRoles;
    
    @Getter
    @Setter
    private String nuevaClave;
    
    @Getter
    @Setter
    private String confirmarNuevaClave;
    
    @Getter
    @Setter
    private Integer estado;

    public UsuarioBean() {
    }

}
