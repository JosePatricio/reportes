/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.DTO.NotificacionDTO;
import com.innovaciones.reporte.util.AsignacionReparacionEnum;
import com.innovaciones.reporte.util.Enums;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fyaulema
 */
@Repository
public class NotificacionlDAOImp implements NotificacionDAO, Serializable {

    private SessionFactory sessionFactory;

    @Override
    public List<NotificacionDTO> getNotificacionesByEstadoReporte() {

        StringBuilder sbQuery = new StringBuilder();

        sbQuery.append("select q.id, q.fecha_inicio, q.hora_inicio, q.fecha_fin, q.hora_fin, q.tecnico, q.cliente, q.tipo_reporte, q. estado_notificacion, prioridad, tipo, id_cliente, id_cliente_producto, id_tecnico ");
        sbQuery.append("from ( ");
        sbQuery.append("select r.id, DATE_FORMAT(r.fecha_creacion,'%d-%m-%Y')  as fecha_inicio, DATE_FORMAT(r.hora_inicio,'%h:%i') hora_inicio, DATE_FORMAT( r.fecha,'%d-%m-%Y') fecha_fin, DATE_FORMAT( r.hora_fin,'%h:%i') hora_fin, ");
        sbQuery.append("concat(u.nombre,' ', u.apellido) as tecnico, c.cliente, subtipo as tipo_reporte, r.estado as estado_notificacion, c.prioridad, ");        
        sbQuery.append("r.tipo , c.id as id_cliente, pc.id as id_cliente_producto, r.id_usuario as id_tecnico ");
        sbQuery.append("from reporte r ");
        sbQuery.append("inner join usuarios u  on r.id_usuario = u.id ");
        sbQuery.append("inner join producto_cliente_reporte pcr on r.id = pcr.id_reporte ");
        sbQuery.append("inner join producto_cliente pc on pcr.id_producto_cliente = pc.id ");
        sbQuery.append("inner join cliente c on pc.id_cliente = c.id ");
        sbQuery.append("where r.estado = :estado ");
        sbQuery.append("union all ");
        sbQuery.append("select ar.id, DATE_FORMAT(fecha_inicio_atencion ,'%d-%m-%Y') as fecha_inicio, DATE_FORMAT(ar.hora_inicio_atencion ,'%h:%i') as hora_inicio, DATE_FORMAT(ar.fecha_fin_atencion ,'%d-%m-%Y') fecha_fin, DATE_FORMAT(ar.hora_fin_atencion,'%h:%i') hora_fin, ");
        sbQuery.append("concat(u.nombre,' ', u.apellido) as tecnico, c.cliente, ar.tipo_reporte as tipo_reporte, ");
        sbQuery.append("'ASIGNADO' as estado_notificacion, ar.prioridad,");        
        sbQuery.append("ar.tipo_notificacion as tipo, c.id as id_cliente, pc.id as id_cliente_producto, ar.id_usuario_atencion as id_tecnico ");
        sbQuery.append("from asignacion_reparacion ar ");
        sbQuery.append("inner join usuarios u  on ar.id_usuario_atencion = u.id ");
        sbQuery.append("inner join producto_cliente pc on ar.id_producto_cliente = pc.id ");
        sbQuery.append("inner join cliente c on pc.id_cliente = c.id ");
        sbQuery.append("where ar.estado = :estadoAsignacion) as q ");
//        sbQuery.append("Order by q.fecha_inicio desc, q.prioridad desc, q.hora_inicio desc");
        sbQuery.append("Order by substring(cast(q.fecha_inicio as nchar(10)),7,10) desc,");
        sbQuery.append("substring(cast(q.fecha_inicio as nchar(10)),4,2) desc,");
        sbQuery.append("substring(cast(q.fecha_inicio as nchar(10)),1,2) desc,");
        sbQuery.append("q.prioridad desc");

        Query query = sessionFactory.getCurrentSession().createSQLQuery(sbQuery.toString())
                .setParameter("estado", Enums.ESTADO_REPORTE_PROCESO.getValue())
                .setParameter("estadoAsignacion", Enums.ESTADO_ASIGNACION_ASIGNADO.getValue());

        List<Object[]> resultObject = (List<Object[]>) query.list();

        List<NotificacionDTO> result = new ArrayList<NotificacionDTO>();

        Integer count = 1;
        for (Object[] object : resultObject) {

            NotificacionDTO notificacionDTO = new NotificacionDTO();

            notificacionDTO.setCount(count++);
            notificacionDTO.setId(Integer.parseInt(object[0].toString()));
            notificacionDTO.setFechaInicio(object[1] != null ? object[1].toString() : "");
            notificacionDTO.setHoraInicio(object[2] != null ? object[2].toString() : "");
            notificacionDTO.setFechaFin(object[3] != null ? object[3].toString() : "");
            notificacionDTO.setHoraFin(object[4] != null ? object[4].toString() : "");
            notificacionDTO.setTecnico(object[5] != null ? object[5].toString() : "");
            notificacionDTO.setCliente(object[6] != null ? object[6].toString() : "");
            notificacionDTO.setTipoReporte(object[7] != null ? object[7].toString() : "");
            notificacionDTO.setEstadoNotificacion(object[8] != null ? object[8].toString() : "");
            notificacionDTO.setPrioridad(object[9] != null ? Integer.parseInt(object[9].toString()) : AsignacionReparacionEnum.PRIORIDAD_DEFAULT.getValue());
            notificacionDTO.setTipoNotificacion(object[10] != null ? object[10].toString() : "");
            notificacionDTO.setIdCliente(object[11] != null ? Integer.parseInt(object[11].toString()) : 0);
            notificacionDTO.setIdClienteProducto(object[12] != null ? Integer.parseInt(object[12].toString()) : 0);
            notificacionDTO.setIdTecnico(object[13] != null ? Integer.parseInt(object[13].toString()) : 0);

            result.add(notificacionDTO);
        }

        return result;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<NotificacionDTO> getNotificacionesByEstadoReporteByIdUsuario(Integer idUsuario) {
        StringBuilder sbQuery = new StringBuilder();

        sbQuery.append("select q.id, q.fecha_inicio, q.hora_inicio, q.fecha_fin, q.hora_fin, q.tecnico, q.cliente, q.tipo_reporte, q. estado_notificacion, prioridad, tipo_notificacion, id_cliente, id_cliente_producto, id_tecnico ");
        sbQuery.append("from ( ");
        sbQuery.append("select r.id, DATE_FORMAT(r.fecha_creacion,'%d-%m-%Y')  as fecha_inicio, DATE_FORMAT(r.hora_inicio,'%h:%i') hora_inicio, DATE_FORMAT( r.fecha,'%d-%m-%Y') fecha_fin, DATE_FORMAT( r.hora_fin,'%h:%i') hora_fin, ");
        sbQuery.append("concat(u.nombre,' ', u.apellido) as tecnico, c.cliente, subtipo as tipo_reporte, r.estado as estado_notificacion, c.prioridad, ");        
        sbQuery.append("r.tipo , c.id as id_cliente, pc.id as id_cliente_producto, r.id_usuario as id_tecnico ");
        sbQuery.append("from reporte r   ");
        sbQuery.append("inner join usuarios u  on r.id_usuario = u.id ");
        sbQuery.append("inner join producto_cliente_reporte pcr on r.id = pcr.id_reporte ");
        sbQuery.append("inner join producto_cliente pc on pcr.id_producto_cliente = pc.id ");
        sbQuery.append("inner join cliente c on pc.id_cliente = c.id ");
        sbQuery.append("where r.estado = :estado AND u.id =:idUsuario ");
        sbQuery.append("union all   ");
        sbQuery.append("select ar.id, DATE_FORMAT(fecha_inicio_atencion ,'%d-%m-%Y') as fecha_inicio, DATE_FORMAT(ar.hora_inicio_atencion ,'%h:%i') as hora_inicio, DATE_FORMAT(ar.fecha_fin_atencion ,'%d-%m-%Y') fecha_fin, DATE_FORMAT(ar.hora_fin_atencion,'%h:%i') hora_fin, ");
        sbQuery.append("concat(u.nombre,' ', u.apellido) as tecnico, c.cliente, ar.tipo_reporte as tipo_reporte, ");
        sbQuery.append("'ASIGNADO' as estado_notificacion, ar.prioridad,");        
        sbQuery.append("ar.tipo_notificacion tipo, c.id as id_cliente, pc.id as id_cliente_producto, ar.id_usuario_atencion as id_tecnico ");
        sbQuery.append("from asignacion_reparacion ar  ");
        sbQuery.append("inner join usuarios u  on ar.id_usuario_atencion = u.id ");
        sbQuery.append("inner join producto_cliente pc on ar.id_producto_cliente = pc.id ");
        sbQuery.append("inner join cliente c on pc.id_cliente = c.id ");
        sbQuery.append("where ar.id_usuario_atencion=:idTecnico AND ar.estado = :estadoAsignacion) as q ");
        //sbQuery.append("Order by q.fecha_inicio desc, q.prioridad desc, q.hora_inicio desc");
        sbQuery.append("Order by substring(cast(q.fecha_inicio as nchar(10)),7,10) desc,");
        sbQuery.append("substring(cast(q.fecha_inicio as nchar(10)),4,2) desc,");
        sbQuery.append("substring(cast(q.fecha_inicio as nchar(10)),1,2) desc,");
        sbQuery.append("q.prioridad desc");

        Query query = sessionFactory.getCurrentSession().createSQLQuery(sbQuery.toString())
                .setParameter("estado", Enums.ESTADO_REPORTE_PROCESO.getValue())
                .setParameter("idUsuario", idUsuario)
                .setParameter("idTecnico", idUsuario)
                .setParameter("estadoAsignacion", Enums.ESTADO_ASIGNACION_ASIGNADO.getValue());
        List<Object[]> resultObject = (List<Object[]>) query.list();

        List<NotificacionDTO> result = new ArrayList<NotificacionDTO>();

        Integer count = 1;
        for (Object[] object : resultObject) {

            NotificacionDTO notificacionDTO = new NotificacionDTO();

            notificacionDTO.setCount(count++);
            notificacionDTO.setId(Integer.parseInt(object[0].toString()));
            notificacionDTO.setFechaInicio(object[1] != null ? object[1].toString() : "");
            notificacionDTO.setHoraInicio(object[2] != null ? object[2].toString() : "");
            notificacionDTO.setFechaFin(object[3] != null ? object[3].toString() : "");
            notificacionDTO.setHoraFin(object[4] != null ? object[4].toString() : "");
            notificacionDTO.setTecnico(object[5] != null ? object[5].toString() : "");
            notificacionDTO.setCliente(object[6] != null ? object[6].toString() : "");
            notificacionDTO.setTipoReporte(object[7] != null ? object[7].toString() : "");
            notificacionDTO.setEstadoNotificacion(object[8] != null ? object[8].toString() : "");
            notificacionDTO.setPrioridad(object[9] != null ? Integer.parseInt(object[9].toString()) : AsignacionReparacionEnum.PRIORIDAD_DEFAULT.getValue());
            notificacionDTO.setTipoNotificacion(object[10] != null ? object[10].toString() : "");
            notificacionDTO.setIdCliente(object[11] != null ? Integer.parseInt(object[11].toString()) : 0);
            notificacionDTO.setIdClienteProducto(object[12] != null ? Integer.parseInt(object[12].toString()) : 0);
            notificacionDTO.setIdTecnico(object[13] != null ? Integer.parseInt(object[13].toString()) : 0);

            result.add(notificacionDTO);
        }

        return result;
    }

}
