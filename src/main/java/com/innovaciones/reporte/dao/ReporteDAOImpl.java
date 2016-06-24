/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.Reporte;
import com.innovaciones.reporte.model.Usuarios;
import com.innovaciones.reporte.util.Utilities;
import java.io.Serializable;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class ReporteDAOImpl extends Utilities implements ReporteDAO,Serializable {

    private SessionFactory sessionFactory;

    @Override
    public Reporte saveReporte(Reporte reporte) {
        sessionFactory.getCurrentSession().saveOrUpdate(reporte);
        return reporte;
    }
    @Override
    public Reporte updateReporte(Reporte reporte) {
      sessionFactory.getCurrentSession().update(reporte);
        return reporte;
    }

    @Override
    public Reporte getReporteById(Integer idReporte) {
        Reporte reporte = (Reporte) sessionFactory.getCurrentSession().
                createQuery("from Reporte WHERE id=" + idReporte).uniqueResult();
        return reporte != null ? reporte : null;
    }

 

      @Override
    public List<Reporte> getReporteByUserByTipo(Usuarios usuario, String tipo, String subtipo) {
      return sessionFactory.getCurrentSession().createQuery("from Reporte r WHERE r.idUsuario.id="+usuario.getId()+" AND r.tipo='"+tipo+"' AND r.subtipo = '" +subtipo+"'" )
                .list();
    }

    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

  

}
