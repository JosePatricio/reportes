/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.UsuarioRolesDAO;
import com.innovaciones.reporte.model.UsuarioRoles;
import com.innovaciones.reporte.model.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
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
@ManagedBean(name = "usuarioRolesService")
@ViewScoped
public class UsuarioRolesServiceImpl implements UsuarioRolesService, Serializable {

    private UsuarioRolesDAO usuarioRolesDAO;

    public void setUsuarioRolesDAO(UsuarioRolesDAO usuarioRolesDAO) {
        this.usuarioRolesDAO = usuarioRolesDAO;
    }

    @Override
    @Transactional
    public UsuarioRoles addUsuarioRoles(UsuarioRoles usuarioRoles) {
        return usuarioRolesDAO.addUsuarioRoles(usuarioRoles);
    }

    @Override
    @Transactional
    public List<UsuarioRoles> addUsuarioRoles(List<UsuarioRoles> listUsuarioRoles) {
        List<UsuarioRoles> result = new ArrayList<UsuarioRoles>();

        if (!listUsuarioRoles.isEmpty()) {
            List<UsuarioRoles> rolesActuales = getUsuariosRolesByUsuario(listUsuarioRoles.get(0).getIdUsuario());
            
            for (UsuarioRoles rolesActuale : rolesActuales) {
                deleteUsuarioRoles(rolesActuale);
            }
            
            //deleteUsuarioRoles(rolesActuales);
        }
        
        for (int i = 0; i < listUsuarioRoles.size(); i++) {
            result.add(addUsuarioRoles(listUsuarioRoles.get(i)));
        }

        return result;
    }

    @Override
    @Transactional
    public UsuarioRoles deleteUsuarioRoles(UsuarioRoles usuarioRoles) {
        return usuarioRolesDAO.deleteUsuarioRoles(usuarioRoles);
    }

    @Override
    @Transactional
    public List<UsuarioRoles> deleteUsuarioRoles(List<UsuarioRoles> listUsuarioRoles) {
        
        for (int i = 0; i < listUsuarioRoles.size(); i++) {
            deleteUsuarioRoles(listUsuarioRoles.get(i));
        }
        return listUsuarioRoles;
    }

    @Override
    @Transactional
    public List<UsuarioRoles> getUsuariosRolesByUsuario(Usuarios usuarios) {
        return usuarioRolesDAO.getUsuariosRolesByUsuario(usuarios);
    }

}
