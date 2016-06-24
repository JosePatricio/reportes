/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.UsuarioRoles;
import com.innovaciones.reporte.model.Usuarios;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class UsuarioRolesDAOImpl implements UsuarioRolesDAO, Serializable {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UsuarioRoles addUsuarioRoles(UsuarioRoles usuarioRoles) {
        sessionFactory.getCurrentSession().saveOrUpdate(usuarioRoles);
        return usuarioRoles;
    }

    @Override
    public UsuarioRoles deleteUsuarioRoles(UsuarioRoles usuarioRoles) {
        Query q = sessionFactory.getCurrentSession().createQuery("delete UsuarioRoles where id = " + usuarioRoles.getId());
        q.executeUpdate();
        return usuarioRoles;
    }

    @Override
    public List<UsuarioRoles> getUsuariosRolesByUsuario(Usuarios usuarios) {
        return sessionFactory.getCurrentSession().createQuery("from UsuarioRoles ur WHERE ur.idUsuario.id = " + usuarios.getId()).list();
    }

}
