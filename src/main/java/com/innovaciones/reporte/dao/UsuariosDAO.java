/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.Usuarios;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface UsuariosDAO {

    public Usuarios addUsuarios(Usuarios cliente);

    public Usuarios getUsuariosById(Integer ruc);

    public List<Usuarios> getUsuarios();

    public Usuarios getUsuarioByUsuarioByClaveByEstado(String usuario, String clave, Integer estado);

    public List<Usuarios> getUsuariosByEstado(Integer estado);
    
    public List<Usuarios> getUsuariosByRolByEstado(String nombreRol, Integer estado);

}
