/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.DTO.ReportesDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Setter;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pisama
 */
@Repository
public class ConsultasDAOImpl implements ConsultasDAO, Serializable {

    @Setter
    private SessionFactory sessionFactory;
    final static String COLUMNAS_REPORTE = "select a.id,a.numero_factura,a.factura,e.equipo,d.ruc,d.cliente,b.ciudad,d.email,CONCAT(g.nombre,' ',g.apellido) as nombre_soporte,a.fecha,f.descripcion as tipo_reporte,c.serie as serial, a.tipo,a.estado ,a.mantenimiento, a.subtipo ";
    final static String COLUMNAS_REPORTE_INSTALACION_NUEVA = "select a.id,a.numero_factura,a.factura,e.equipo,d.ruc,d.cliente,b.ciudad,d.email,CONCAT(g.nombre,' ',g.apellido) as nombre_soporte,a.fecha,f.nota as tipo_reporte,c.serie as serial, a.tipo,a.estado ,a.mantenimiento, a.subtipo ";
    final static String COLUMNAS_REPORTE_INSTALACION_TEMPORAL = "select a.id,a.numero_factura,a.factura,e.equipo,d.ruc,d.cliente,b.ciudad,d.email,CONCAT(g.nombre,' ',g.apellido) as nombre_soporte,a.fecha,c.serie as serial, a.tipo,a.estado ,a.mantenimiento, a.subtipo ";

    @Override
    public List<ReportesDTO> reportes() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(COLUMNAS_REPORTE);
        stringBuilder.append("from reporte as a ");
        stringBuilder.append("inner join producto_cliente_reporte as b on a.id=b.id_reporte ");
        stringBuilder.append("inner join producto_cliente as c on b.id_producto_cliente=c.id ");
        stringBuilder.append("inner join cliente as d on  c.id_cliente= d.id ");
        stringBuilder.append("inner join producto e on c.id_producto=e.id ");
        stringBuilder.append("inner join tipo_visita as f on f.id=a.id_visita ");
        stringBuilder.append("inner join usuarios as g  on g.id=a.id_usuario ");
        stringBuilder.append("order by a.fecha_creacion desc");
       
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(stringBuilder.toString());
        List<Object[]> result = query.list();

        List<ReportesDTO> reporteLista = new ArrayList<ReportesDTO>();

        ReportesDTO reporte;
        for (int i = 0; i < result.size(); i++) {
            Object[] data = result.get(i);
            reporte = new ReportesDTO();

            reporte.setId(Integer.parseInt(data[0].toString()));
            reporte.setNumeroFactura(data[1] == null ? "" : data[1].toString());
            reporte.setFactura(data[2] == null ? "" : data[2].toString());
            reporte.setEquipo(data[3] == null ? "" : data[3].toString());
            reporte.setRuc(data[4].toString());
            reporte.setCliente(data[5].toString());
            reporte.setCiudad(data[6].toString());
            reporte.setEmail(data[7].toString());
            reporte.setSoporte(data[8].toString());
            reporte.setFecha((Date) data[9]);
            reporte.setTipoReporte(data[10].toString());
            reporte.setSerial(data[11].toString());
            reporte.setTipo(data[12].toString());
            reporte.setEstado(data[13].toString());
            reporte.setMantenimiento(data[14].toString());
            reporte.setSubtipo(data[15].toString());
            reporteLista.add(reporte);

        }
        return reporteLista;

    }

    @Override
    public List<ReportesDTO> reportesPorRuc(String ruc) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(COLUMNAS_REPORTE);
        stringBuilder.append("from reporte as a ");
        stringBuilder.append("inner join producto_cliente_reporte as b on a.id=b.id_reporte ");
        stringBuilder.append("inner join producto_cliente as c on b.id_producto_cliente=c.id ");
        stringBuilder.append("inner join cliente as d on  c.id_cliente= d.id ");
        stringBuilder.append("inner join producto e on c.id_producto=e.id ");
        stringBuilder.append("inner join tipo_visita as f on f.id=a.id_visita ");
        stringBuilder.append("inner join usuarios as g  on g.id=a.id_usuario ");
        stringBuilder.append("where d.ruc=':ruc ");
        stringBuilder.append("order by a.fecha, a.estado desc ");

        Query query = sessionFactory.getCurrentSession().createSQLQuery(stringBuilder.toString())
                .setParameter("ruc", ruc);
        List<Object[]> result = query.list();

        List<ReportesDTO> reporteLista = new ArrayList<ReportesDTO>();

        ReportesDTO reporte;
        for (int i = 0; i < result.size(); i++) {
            Object[] data = result.get(i);
            reporte = new ReportesDTO();
            reporte.setId(Integer.parseInt(data[0].toString()));

            reporte.setNumeroFactura(data[1] == null ? "" : data[1].toString());
            reporte.setFactura(data[2].toString());
            reporte.setEquipo(data[3].toString());
            reporte.setRuc(data[4].toString());
            reporte.setCliente(data[5].toString());
            reporte.setCiudad(data[6].toString());
            reporte.setEmail(data[7].toString());
            reporte.setSoporte(data[8].toString());
            reporte.setFecha((Date) data[9]);
            reporte.setTipoReporte(data[10].toString());
            reporte.setSerial(data[11].toString());
            reporte.setTipo(data[12].toString());
            reporte.setEstado(data[13].toString());
            reporte.setMantenimiento(data[14].toString());
            reporte.setSubtipo(data[15].toString());
            reporteLista.add(reporte);

        }

        return reporteLista;
    }

    @Override
    public ReportesDTO reportesById(Integer id) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(COLUMNAS_REPORTE);
        stringBuilder.append("from reporte as a ");
        stringBuilder.append("join producto_cliente_reporte as b on a.id=b.id_reporte ");
        stringBuilder.append("join producto_cliente as c on b.id_producto_cliente=c.id ");
        stringBuilder.append("join cliente as d on  c.id_cliente= d.id ");
        stringBuilder.append("join producto e on c.id_producto=e.id ");
        stringBuilder.append("join tipo_visita as f on f.id=a.id_visita ");
        stringBuilder.append("join usuarios as g  on g.id=a.id_usuario ");
        stringBuilder.append("where a.id=:id ");
        stringBuilder.append("order by a.fecha desc ");

        Query query = sessionFactory.getCurrentSession().createSQLQuery(stringBuilder.toString())
                .setParameter("id", id);

        List<Object[]> result = query.list();

        ReportesDTO reporte = new ReportesDTO();
        for (int i = 0; i < result.size(); i++) {
            Object[] data = result.get(i);
            reporte = new ReportesDTO();
            reporte.setId(Integer.parseInt(data[0].toString()));

            reporte.setNumeroFactura(data[1] == null ? "" : data[1].toString());
            reporte.setFactura(data[2] == null ? "" : data[2].toString());
            reporte.setEquipo(data[3] == null ? "" : data[3].toString());
            reporte.setRuc(data[4].toString());
            reporte.setCliente(data[5].toString());
            reporte.setCiudad(data[6].toString());
            reporte.setEmail(data[7].toString());
            reporte.setSoporte(data[8].toString());
            reporte.setFecha((Date) data[9]);
            reporte.setTipoReporte(data[10].toString());
            reporte.setSerial(data[11].toString());
            reporte.setTipo(data[12].toString());
            reporte.setEstado(data[13].toString());
            reporte.setMantenimiento(data[14].toString());
            reporte.setSubtipo(data[15].toString());
        }
        return reporte;
    }

    @Override
    public List<ReportesDTO> reportesInstalaciones() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("(select a.id,a.numero_factura,a.factura,e.equipo,d.ruc,d.cliente,b.ciudad,d.email,CONCAT(g.nombre,' ',g.apellido) as nombre_soporte,a.fecha,null as nota,c.serie as serial, a.tipo,a.estado , a.subtipo ");
        stringBuilder.append("from reporte as a inner join producto_cliente_reporte as b on a.id=b.id_reporte ");
        stringBuilder.append("inner join producto_cliente as c on b.id_producto_cliente=c.id ");
        stringBuilder.append("inner join cliente as d on c.id_cliente= d.id ");
        stringBuilder.append("inner join producto e on c.id_producto=e.id ");
        stringBuilder.append("inner join detalle_reporte_temporal as f on f.id=b.id_detalle_reporte_temporal ");
        stringBuilder.append("inner join usuarios as g on g.id=a.id_usuario) ");
        stringBuilder.append("UNION ALL ");
        stringBuilder.append("(select a.id,a.numero_factura,a.factura,e.equipo,d.ruc,d.cliente,b.ciudad,d.email,CONCAT(g.nombre,' ',g.apellido) as nombre_soporte,a.fecha,f.nota as nota,c.serie as serial, a.tipo,a.estado , a.subtipo ");
        stringBuilder.append("from reporte as a inner join producto_cliente_reporte as b on a.id=b.id_reporte ");
        stringBuilder.append("inner join producto_cliente as c on b.id_producto_cliente=c.id ");
        stringBuilder.append("inner join cliente as d on c.id_cliente= d.id ");
        stringBuilder.append("inner join producto e on c.id_producto=e.id ");
        stringBuilder.append("inner join detalle_reporte_instalacion_nueva as f on f.id=b.id_detalle_reporte_instalacion_nueva ");
        stringBuilder.append("inner join usuarios as g on g.id=a.id_usuario ) ");

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(stringBuilder.toString());
        List<Object[]> result = query.list();

      List<ReportesDTO> reporteLista = new ArrayList<ReportesDTO>();
        ReportesDTO reporte;
        for (int i = 0; i < result.size(); i++) {
            Object[] data = result.get(i);
            reporte = new ReportesDTO();

            reporte.setId(Integer.parseInt(data[0].toString()));
            reporte.setNumeroFactura(data[1] == null ? "" : data[1].toString());
            reporte.setFactura(data[2] == null ? "" : data[2].toString());
            reporte.setEquipo(data[3] == null ? "" : data[3].toString());
            reporte.setRuc(data[4].toString());
            reporte.setCliente(data[5].toString());
            reporte.setCiudad(data[6].toString());
            reporte.setEmail(data[7].toString());
            reporte.setSoporte(data[8].toString());
            reporte.setFecha((Date) data[9]);
            reporte.setNota(data[10] == null ? "" : data[10].toString());
            reporte.setSerial(data[11].toString());
            reporte.setTipo(data[12].toString());
            reporte.setEstado(data[13].toString());
            reporte.setSubtipo(data[14] == null ? "" : data[14].toString());
            reporteLista.add(reporte);

        }
        return reporteLista;
    }

    @Override
    public List<ReportesDTO> reportesInstalacionesPorRuc(String ruc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ReportesDTO reportesInstalacionesById(Integer id) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(COLUMNAS_REPORTE_INSTALACION_NUEVA);
        stringBuilder.append("from reporte as a ");
        stringBuilder.append("join producto_cliente_reporte as b on a.id=b.id_reporte ");
        stringBuilder.append("join producto_cliente as c on b.id_producto_cliente=c.id ");
        stringBuilder.append("join cliente as d on  c.id_cliente= d.id ");
        stringBuilder.append("join producto e on c.id_producto=e.id ");
        stringBuilder.append("inner join detalle_reporte_instalacion_nueva as f on f.id=b.id_detalle_reporte_instalacion_nueva ");
        stringBuilder.append("join usuarios as g  on g.id=a.id_usuario ");
        stringBuilder.append("where a.id=:id ");
        stringBuilder.append("order by a.fecha desc ");

        Query query = sessionFactory.getCurrentSession().createSQLQuery(stringBuilder.toString())
                .setParameter("id", id);

        List<Object[]> result = query.list();

        ReportesDTO reporte = new ReportesDTO();
        for (int i = 0; i < result.size(); i++) {
            Object[] data = result.get(i);
            reporte = new ReportesDTO();
            reporte.setId(Integer.parseInt(data[0].toString()));

            reporte.setNumeroFactura(data[1] == null ? "" : data[1].toString());
            reporte.setFactura(data[2] == null ? "" : data[2].toString());
            reporte.setEquipo(data[3] == null ? "" : data[3].toString());
            reporte.setRuc(data[4].toString());
            reporte.setCliente(data[5].toString());
            reporte.setCiudad(data[6].toString());
            reporte.setEmail(data[7].toString());
            reporte.setSoporte(data[8].toString());
            reporte.setFecha((Date) data[9]);
            reporte.setTipoReporte(data[10].toString());
            reporte.setSerial(data[11].toString());
            reporte.setTipo(data[12].toString());
            reporte.setEstado(data[13].toString());
            reporte.setMantenimiento(data[14].toString());
            reporte.setSubtipo(data[15].toString());
        }
        return reporte;

    }

}
