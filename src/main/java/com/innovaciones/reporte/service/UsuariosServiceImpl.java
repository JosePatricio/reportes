/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.UsuariosDAO;
import com.innovaciones.reporte.model.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pisama
 */
@Service
@ManagedBean(name = "usuariosService")
//@SessionScoped
@ViewScoped
public class UsuariosServiceImpl implements UsuariosService, Serializable {

    private UsuariosDAO usuariosDAO;

    @Override
    @Transactional
    public Usuarios login(String usuario, String clave, Integer estado) {
        return this.usuariosDAO.getUsuarioByUsuarioByClaveByEstado(usuario, clave, estado);
    }

    @Override
    @Transactional
    public Usuarios addUsuarios(Usuarios cliente) {
        return this.usuariosDAO.addUsuarios(cliente);
    }

    @Override
    @Transactional
    public Usuarios getUsuariosById(Integer id) {
        return this.usuariosDAO.getUsuariosById(id);
    }

    @Override
    @Transactional
    public List<Usuarios> getUsuarios() {
        return this.usuariosDAO.getUsuarios();
    }

    @Override
    @Transactional
    public List<Usuarios> getUsuariosByEstado(Integer estado) {
        return this.usuariosDAO.getUsuariosByEstado(estado);
    }

    @Override
    @Transactional
    public List<Usuarios> getUsuariosByRolByEstado(String nombreRol, Integer estado) {
        return this.usuariosDAO.getUsuariosByRolByEstado(nombreRol, estado);
    }

    public void setUsuariosDAO(UsuariosDAO usuariosDAO) {
        this.usuariosDAO = usuariosDAO;
    }

}
