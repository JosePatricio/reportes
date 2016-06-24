/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.model.Modelo;
import com.innovaciones.reporte.model.RepuestoModelo;
import java.util.List;

/**
 *
 * @author Fernando
 */
public interface RepuestoModeloService {

    public RepuestoModelo addRepuestoModelo(RepuestoModelo repuestoModelo);

    public RepuestoModelo getRepuestoModeloById(Integer id);

    public List<RepuestoModelo> addRepuestosModelo(Modelo modelo, List<RepuestoModelo> repuestoModelos);

    public List<RepuestoModelo> getRepuestoModeloByIdModelo(Integer idModelo);

    public List<RepuestoModelo> getRepuestoModeloByIdModeloInterno(Integer idModelo);

    public List<RepuestoModelo> getRepuestoModeloByIdRepuesto(Integer idRepuesto);

    public List<RepuestoModelo> getRepuestoModeloByIdRepuestoIdModelo(Integer idRepuesto, Integer idModelo);

    public List<RepuestoModelo> getRepuestoModelos();

    public List<RepuestoModelo> getRepuestoModelosByEstado(Integer estado);

    public List<RepuestoModelo> getRepuestoModeloByIdMarca(Integer idMarca);

    public RepuestoModelo updateRepuestoModelo(RepuestoModelo repuestoModelo);

    public List<RepuestoModelo> getIdModeloByIdRepuestoByRepuesto(Integer idModelo, Integer id, String repuesto);

    public List<RepuestoModelo> getRepuestoModelosByTipoDetalleCatalogo(String tipo, Boolean esCatalogo);

    public RepuestoModelo getRepuestoModeloByIdDetalleByIdModeloByCodigo(Integer idDetalle, Integer idModelo, String codigos);
}
