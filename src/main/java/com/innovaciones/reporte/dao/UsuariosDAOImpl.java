/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.Usuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;


import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class UsuariosDAOImpl implements UsuariosDAO, Serializable {

    private SessionFactory sessionFactory;
  
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Usuarios addUsuarios(Usuarios usuario) {
        sessionFactory.getCurrentSession().saveOrUpdate(usuario);
        return usuario;
    }

    @Override
    public Usuarios getUsuariosById(Integer ruc) {
        Usuarios user = (Usuarios) sessionFactory.getCurrentSession().
                createQuery("from Usuarios WHERE id='" + ruc + "'").
                uniqueResult();
        return user != null ? user : null;
    }

    @Override
    public List<Usuarios> getUsuarios() {
        return sessionFactory.getCurrentSession().createQuery("from Usuarios u Order By u.nombre, u.apellido")
                .list();
    }

    @Override
    public Usuarios getUsuarioByUsuarioByClaveByEstado(String usuario, String clave, Integer estado) {
        Usuarios user = (Usuarios) sessionFactory.getCurrentSession().
                createQuery("from Usuarios WHERE usuario='" + usuario + "' AND clave='" + clave + "' AND estado=" + estado).
                uniqueResult();
        return user != null ? user : null;
    }

    @Override
    public List<Usuarios> getUsuariosByEstado(Integer estado) {
        return sessionFactory.getCurrentSession().createQuery("from Usuarios u Where u.estado = " + estado + " Order By u.nombre, u.apellido")
                .list();
    }

    @Override
    public List<Usuarios> getUsuariosByRolByEstado(String nombreRol, Integer estado) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("SELECT u.id, u.dni, u.codigo, u.nombre, u.apellido, u.telefono, ");
        stringBuilder.append("u.celular, u.mail, u.direccion, u.usuario, u.clave, u.estado, ");
        stringBuilder.append("u.firma_base64, u.firma, u.imagen ");
        stringBuilder.append("FROM usuarios u ");
        stringBuilder.append("inner join usuario_roles ur on u.id = ur.id_usuario ");
        stringBuilder.append("inner join rol r on ur.id_rol = r.id ");
        stringBuilder.append("Where r.rol = '");
        stringBuilder.append(nombreRol);
        stringBuilder.append("' AND u.estado = ");
        stringBuilder.append(estado);
        stringBuilder.append(" AND r.estado = ");
        stringBuilder.append(estado);
        stringBuilder.append(" Order By u.nombre, u.apellido");

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(stringBuilder.toString());
        List<Object[]> result = query.list();

        List<Usuarios> usuariosLista = new ArrayList<Usuarios>();

        Usuarios usuario;
        for (int i = 0; i < result.size(); i++) {
            Object[] data = result.get(i);

            usuario = new Usuarios();

            usuario.setId(Integer.parseInt(data[0].toString()));
            usuario.setDni(data[1].toString());
            usuario.setCodigo(data[2].toString());
            usuario.setNombre(data[3].toString());
            usuario.setApellido(data[4].toString());
            usuario.setTelefono(data[5] != null ? data[5].toString() : "");
            usuario.setCelular(data[6] != null ? data[6].toString() : "");
            usuario.setMail(data[7].toString());
            usuario.setDireccion(data[8] != null ? data[8].toString() : "");
            usuario.setUsuario(data[9].toString());
            usuario.setClave(data[10].toString());
            //usuario.setEstado(Integer.parseInt(data[11].toString()));
            usuario.setFirmaBase64(data[12] != null ? data[12].toString() : "");
            usuario.setFirma(data[13] != null ? data[13].toString() : "");
            usuariosLista.add(usuario);
        }

        return usuariosLista;
    }

}
