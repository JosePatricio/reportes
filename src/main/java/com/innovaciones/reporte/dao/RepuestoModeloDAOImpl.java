/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.RepuestoModelo;
import java.io.Serializable;
import java.util.List;
import lombok.Setter;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fernando
 */
@Repository
public class RepuestoModeloDAOImpl implements RepuestoModeloDAO, Serializable {

    @Setter
    private SessionFactory sessionFactory;

    @Override
    public RepuestoModelo addRepuestoModelo(RepuestoModelo repuestoModelo) {
        sessionFactory.getCurrentSession().saveOrUpdate(repuestoModelo);
        return repuestoModelo;
    }

    @Override
    public RepuestoModelo updateRepuestoModelo(RepuestoModelo repuestoModelo) {
        sessionFactory.getCurrentSession().update(repuestoModelo);
        return repuestoModelo;
    }

    @Override
    public RepuestoModelo deleteRepuestoModelo(RepuestoModelo repuestoModelo) {
        Query q = sessionFactory.getCurrentSession().createQuery("delete RepuestoModelo where id = " + repuestoModelo.getId());
        q.executeUpdate();
        return repuestoModelo;
    }

    @Override
    public List<RepuestoModelo> getRepuestoModelos() {
        return sessionFactory.getCurrentSession().createQuery("from RepuestoModelo r ORDER BY r.idDetalleCatalogoReporte.descripcion")
                .list();
    }

    @Override
    public List<RepuestoModelo> getRepuestoModeloByIdModelo(Integer idModelo) {
        return sessionFactory.getCurrentSession().createQuery("from RepuestoModelo r Where r.idModelo.id = " + idModelo + " AND estado = 1 ORDER BY r.idDetalleCatalogoReporte.descripcion")
                .list();
    }

    @Override
    public List<RepuestoModelo> getRepuestoModeloByIdModeloInterno(Integer idModelo) {
        return sessionFactory.getCurrentSession().createQuery("from RepuestoModelo r Where r.idModelo.id = " + idModelo + " ORDER BY r.idDetalleCatalogoReporte.descripcion")
                .list();
    }

    @Override
    public List<RepuestoModelo> getRepuestoModeloByIdRepuesto(Integer idRepuesto) {
        return sessionFactory.getCurrentSession().createQuery("from RepuestoModelo r Where r.idDetalleCatalogoReporte.id = " + idRepuesto + " ORDER BY r.idDetalleCatalogoReporte.fechaCreacion")
                .list();
    }

    @Override
    public List<RepuestoModelo> getRepuestoModeloByIdRepuestoIdModelo(Integer idRepuesto, Integer idModelo) {
        return sessionFactory.getCurrentSession().createQuery("from RepuestoModelo r WHERE r.idDetalleCatalogoReporte.id=" + idRepuesto + " AND r.idModelo.id=" + idModelo + " ORDER BY r.idDetalleCatalogoReporte.fechaCreacion")
                .list();
    }

    @Override
    public List<RepuestoModelo> getRepuestoModelosByEstado(Integer estado) {
        return sessionFactory.getCurrentSession().createQuery("from RepuestoModelo r Where r.estado =" + estado + " ORDER BY r.idDetalleCatalogoReporte.descripcion")
                .list();
    }

    @Override
    public List<RepuestoModelo> getRepuestoModeloByIdMarca(Integer idMarca) {
        return sessionFactory.getCurrentSession().createQuery("from RepuestoModelo r WHERE r.idModelo.idMarca.id=" + idMarca)
                .list();
    }

    @Override
    public RepuestoModelo getRepuestoModeloById(Integer id) {
        RepuestoModelo repuestoModelo = (RepuestoModelo) sessionFactory.getCurrentSession().createQuery("from RepuestoModelo WHERE id=" + id + "").uniqueResult();
        return repuestoModelo != null ? repuestoModelo : null;
    }

    @Override
    public List<RepuestoModelo> getIdModeloByIdRepuestoByRepuesto(Integer idModelo, Integer id, String repuesto) {
        return sessionFactory.getCurrentSession().createQuery("from RepuestoModelo r WHERE r.idModelo.id=" + idModelo + " AND r.idDetalleCatalogoReporte.descripcion='" + repuesto + "' AND r.idDetalleCatalogoReporte.id=" + id + "")
                .list();
    }

    @Override
    public List<RepuestoModelo> getRepuestoModelosByTipoDetalleCatalogo(String tipo, Boolean esCatalogo) {
        return sessionFactory.getCurrentSession().createQuery("from RepuestoModelo r WHERE r.idDetalleCatalogoReporte.idCabecera.codigo='" + tipo + "' AND esCatalogo=" + esCatalogo)
                .list();
    }

    @Override
    public RepuestoModelo getRepuestoModeloByIdDetalleByIdModeloByCodigo(Integer idDetalle, Integer idModelo, String codigos) {
        RepuestoModelo repuestoModelo = (RepuestoModelo) sessionFactory.getCurrentSession().createQuery("from RepuestoModelo r WHERE r.idDetalleCatalogoReporte.id=" + idDetalle + " AND r.idModelo.id=" + idModelo + " AND r.codigoRepuesto='" + codigos + "'").uniqueResult();
        return repuestoModelo != null ? repuestoModelo : null;
    }

}
