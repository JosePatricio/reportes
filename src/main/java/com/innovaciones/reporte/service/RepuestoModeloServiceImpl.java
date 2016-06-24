/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.RepuestoModeloDAO;
import com.innovaciones.reporte.model.Modelo;
import com.innovaciones.reporte.model.RepuestoModelo;
import com.innovaciones.reporte.util.EstadosEnum;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fernando
 */
@Service
@ManagedBean(name = "repuestoModeloService")
@ViewScoped
public class RepuestoModeloServiceImpl implements RepuestoModeloService, Serializable {

    @Setter
    private RepuestoModeloDAO repuestoModeloDAO;

    @Override
    @Transactional
    public RepuestoModelo addRepuestoModelo(RepuestoModelo repuestoModelo) {
        return repuestoModeloDAO.addRepuestoModelo(repuestoModelo);
    }

    @Override
    @Transactional
    public RepuestoModelo updateRepuestoModelo(RepuestoModelo repuestoModelo) {
        return repuestoModeloDAO.updateRepuestoModelo(repuestoModelo);
    }

    @Override
    @Transactional
    public List<RepuestoModelo> addRepuestosModelo(Modelo modelo, List<RepuestoModelo> repuestoModelos) {

        for (RepuestoModelo repuestoModelo : repuestoModelos) {
            repuestoModelo.setIdModelo(new Modelo());
            repuestoModelo.setIdModelo(modelo);
        }

        List<RepuestoModelo> busquedaRepuestoModelos = getRepuestoModeloByIdModeloInterno(modelo.getId());

        //VERIFICA ELIMINACIONES 
        for (RepuestoModelo busqueda : busquedaRepuestoModelos) {

            Boolean existe = Boolean.FALSE;

            RepuestoModelo repuestoModeloEncontrado = null;

            for (RepuestoModelo repuestoModelo : repuestoModelos) {

                if (busqueda.getIdModelo().getId().equals(repuestoModelo.getIdModelo().getId())
                        && busqueda.getIdDetalleCatalogoReporte().getId().equals(repuestoModelo.getIdDetalleCatalogoReporte().getId())) {
                    existe = Boolean.TRUE;
                    repuestoModeloEncontrado = repuestoModelo;
                    break;
                }
            }

            if (!existe) {
                busqueda.setEstado(EstadosEnum.INACTIVO.getValue());
                repuestoModeloDAO.addRepuestoModelo(busqueda);
                // repuestoModeloDAO.deleteRepuestoModelo(busqueda);
            } else {
                busqueda.setEstado(EstadosEnum.ACTIVO.getValue());
                repuestoModeloDAO.addRepuestoModelo(busqueda);
                repuestoModelos.remove(repuestoModeloEncontrado);
            }
        }

        //VERIFICA ADICIONES
        for (RepuestoModelo repuestoModelo : repuestoModelos) {
            repuestoModeloDAO.addRepuestoModelo(repuestoModelo);
        }

//        if (!repuestoModelos.isEmpty()) {
//
//            List<RepuestoModelo> busquedaRepuestoModelos = getRepuestoModeloByIdModelo(modelo.getId());
//            if (!busquedaRepuestoModelos.isEmpty()) {
//
//                for (RepuestoModelo repuestoModelo : repuestoModelos) {
//
//                    Boolean existe = Boolean.FALSE;
//                    for (RepuestoModelo busqueda : busquedaRepuestoModelos) {
//
//                        if (busqueda.getIdModelo().getId().equals(repuestoModelo.getIdModelo().getId())
//                                && busqueda.getIdRepuesto().getId().equals(repuestoModelo.getIdRepuesto().getId())) {
//                            existe = Boolean.TRUE;
//                            break;
//                        }
//                    }
//                    if (!existe) {
//                        repuestoModeloDAO.deleteRepuestoModelo(repuestoModelo);
//                    } else {
//                        repuestoModeloDAO.addRepuestoModelo(repuestoModelo);
//                    }
//                }
//
//                repuestoModelos = getRepuestoModeloByIdModelo(repuestoModelos.get(0).getIdModelo().getId());
//
//            } else {
//                for (RepuestoModelo repuestoModelo : repuestoModelos) {
//                    repuestoModeloDAO.addRepuestoModelo(repuestoModelo);
//                }
//            }
//
//        }
        return repuestoModelos;
    }

    @Override
    @Transactional
    public List<RepuestoModelo> getRepuestoModeloByIdModelo(Integer idModelo) {
        return repuestoModeloDAO.getRepuestoModeloByIdModelo(idModelo);
    }

    @Override
    @Transactional
    public List<RepuestoModelo> getRepuestoModeloByIdModeloInterno(Integer idModelo) {
        return repuestoModeloDAO.getRepuestoModeloByIdModeloInterno(idModelo);
    }

    @Override
    @Transactional
    public List<RepuestoModelo> getRepuestoModeloByIdRepuesto(Integer idRepuesto) {
        return repuestoModeloDAO.getRepuestoModeloByIdRepuesto(idRepuesto);
    }

    @Override
    @Transactional
    public List<RepuestoModelo> getRepuestoModelos() {
        return repuestoModeloDAO.getRepuestoModelos();
    }

    @Override
    @Transactional
    public List<RepuestoModelo> getRepuestoModelosByEstado(Integer estado
    ) {
        return repuestoModeloDAO.getRepuestoModelosByEstado(estado);
    }

    @Override
    @Transactional
    public List<RepuestoModelo> getRepuestoModeloByIdMarca(Integer idMarca) {
        return repuestoModeloDAO.getRepuestoModeloByIdMarca(idMarca);
    }

    @Override
    @Transactional
    public RepuestoModelo getRepuestoModeloById(Integer id) {
        return repuestoModeloDAO.getRepuestoModeloById(id);
    }

    @Override
    @Transactional
    public List<RepuestoModelo> getRepuestoModeloByIdRepuestoIdModelo(Integer idRepuesto, Integer idModelo) {
        return repuestoModeloDAO.getRepuestoModeloByIdRepuestoIdModelo(idRepuesto, idModelo);
    }

    @Override
    @Transactional
    public List<RepuestoModelo> getIdModeloByIdRepuestoByRepuesto(Integer idModelo, Integer id, String repuesto) {
        return repuestoModeloDAO.getIdModeloByIdRepuestoByRepuesto(idModelo, id, repuesto);
    }

    @Override
    @Transactional
    public List<RepuestoModelo> getRepuestoModelosByTipoDetalleCatalogo(String tipo, Boolean esCatalogo) {
        return repuestoModeloDAO.getRepuestoModelosByTipoDetalleCatalogo(tipo, esCatalogo);
    }

    @Override
    @Transactional
    public RepuestoModelo getRepuestoModeloByIdDetalleByIdModeloByCodigo(Integer idDetalle, Integer idModelo, String codigos) {
        return repuestoModeloDAO.getRepuestoModeloByIdDetalleByIdModeloByCodigo(idDetalle, idModelo, codigos);
    }

}
