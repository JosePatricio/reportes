/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.model.UsuarioRoles;
import com.innovaciones.reporte.model.Usuarios;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface UsuarioRolesService {

    public UsuarioRoles addUsuarioRoles(UsuarioRoles usuarioRoles);

    public List<UsuarioRoles> addUsuarioRoles(List<UsuarioRoles> usuarioRoles);

    public UsuarioRoles deleteUsuarioRoles(UsuarioRoles usuarioRoles);

    public List<UsuarioRoles> deleteUsuarioRoles(List<UsuarioRoles> listUsuarioRoles);

    public List<UsuarioRoles> getUsuariosRolesByUsuario(Usuarios usuarios);

}
