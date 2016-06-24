/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.AsignacionReparacion;
import com.innovaciones.reporte.util.Enums;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fernando
 */
@Repository
public class AsignacionReparacionDAOImpl implements AsignacionReparacionDAO, Serializable {

    private SessionFactory sessionFactory;

    @Override
    public AsignacionReparacion addAsignacionReparacion(AsignacionReparacion asignacionReparacion) {
        sessionFactory.getCurrentSession().saveOrUpdate(asignacionReparacion);
        return asignacionReparacion;
    }

    @Override
    public List<AsignacionReparacion> getAsignacionReparaciones() {
        return sessionFactory.getCurrentSession().createQuery("from AsignacionReparacion a Order By a.fechaInicioAtencion Desc, a.prioridad desc, a.horaInicioAtencion Asc")
                .list();
    }

    @Override
    public AsignacionReparacion getAsignacionReparacionById(Integer id) {
        AsignacionReparacion asignacionReparacion = (AsignacionReparacion) sessionFactory.getCurrentSession().createQuery("from AsignacionReparacion WHERE id=" + id + "").uniqueResult();
        return asignacionReparacion != null ? asignacionReparacion : null;
    }

    @Override
    public List<AsignacionReparacion> getAsignacionReparacionesByEstado(String estado) {
        return sessionFactory.getCurrentSession().createQuery("from AsignacionReparacion a WHERE estado = '" + estado + "' Order By a.fechaInicioAtencion Desc, a.prioridad desc, a.horaInicioAtencion Asc").
                list();
    }

    @Override
    public List<AsignacionReparacion> getAsignacionReparacionesNoEliminados() {
        return sessionFactory.getCurrentSession().createQuery("from AsignacionReparacion a WHERE estado <> '" + Enums.ESTADO_ASIGNACION_ELIMINADO.getValue() + "' Order By a.fechaInicioAtencion Desc, a.prioridad desc, a.horaInicioAtencion Asc").
                list();
    }

    @Override
    public List<AsignacionReparacion> getAsignacionReparacionesByFechaByIdUsuario(Date fecha, Integer idUsuario) {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String formatoFecha = formato.format(fecha);
        formato = new SimpleDateFormat("dd-MM-yyyy");
        String formatoFecha1 = formato.format(fecha);

        return sessionFactory.getCurrentSession().createQuery("from AsignacionReparacion a WHERE a.fechaInicioAtencion = '" + formatoFecha + "'").// AND a.idUsuarioAtencion.id = "+idUsuario).
                list();
    }

    @Override
    public List<AsignacionReparacion> getAsignacionReparacionesFiltradasByTecnicosFechas(String listIdTecnicos, Date fechaInicioFiltro, Date fechaFinFiltro) {

        StringBuilder query = new StringBuilder();

        query.append("from AsignacionReparacion a WHERE a.estado <> '");
        query.append(Enums.ESTADO_ASIGNACION_ELIMINADO.getValue());
        query.append("'");

        if (!listIdTecnicos.isEmpty()) {
            query.append(" AND a.idUsuarioAtencion IN ( ");
            query.append(listIdTecnicos);
            query.append(") ");
        }

        if (fechaInicioFiltro != null && fechaFinFiltro != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String formatoFechaInicio = formato.format(fechaInicioFiltro);
            String formatoFechaFin = formato.format(fechaFinFiltro);

            formato = new SimpleDateFormat("dd-MM-yyyy");
            String formatoFechaInicio1 = formato.format(fechaInicioFiltro);
            String formatoFechaFin1 = formato.format(fechaFinFiltro);

            query.append(" AND a.fechaInicioAtencion >= '");
            query.append(formatoFechaInicio);
            query.append("' ");
            query.append(" AND a.fechaInicioAtencion <= '");
            query.append(formatoFechaFin);
            query.append("' ");
        }
        return sessionFactory.getCurrentSession().createQuery(query.toString()).list();

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public AsignacionReparacion getAsignacionReparacionByIdReporte(Integer id) {
        AsignacionReparacion asignacionReparacion = (AsignacionReparacion) sessionFactory.getCurrentSession().createQuery("from AsignacionReparacion WHERE idReporte=" + id + "").uniqueResult();
        return asignacionReparacion != null ? asignacionReparacion : null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
