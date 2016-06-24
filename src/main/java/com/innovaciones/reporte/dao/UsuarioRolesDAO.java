/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.UsuarioRoles;
import com.innovaciones.reporte.model.Usuarios;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface UsuarioRolesDAO {

    public UsuarioRoles addUsuarioRoles(UsuarioRoles usuarioRoles);

    public UsuarioRoles deleteUsuarioRoles(UsuarioRoles usuarioRoles);

    public List<UsuarioRoles> getUsuariosRolesByUsuario(Usuarios usuarios);

}
