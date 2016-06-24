/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.ReporteMantenimientoDAO;
import com.innovaciones.reporte.model.DetalleCatalogoReporte;
import com.innovaciones.reporte.model.Modelo;
import com.innovaciones.reporte.model.ProductoClienteReporte;
import com.innovaciones.reporte.model.ReporteMantenimiento;
import com.innovaciones.reporte.model.RepuestoModelo;
import com.innovaciones.reporte.model.Usuarios;
import com.innovaciones.reporte.util.Enums;
import com.innovaciones.reporte.util.EstadosEnum;
import com.innovaciones.reporte.util.Utilities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pisama
 */
@Service
@ManagedBean(name = "reporteMantenimientoService")
@ViewScoped
public class ReporteMantenimientoServiceImpl extends Utilities implements ReporteMantenimientoService, Serializable {

    @Setter
    private ReporteMantenimientoDAO reporteMantenimientoDAO;

    @Getter
    @Setter
    @ManagedProperty("#{detalleCatalogoReporteService}")
    private DetalleCatalogoReporteService detalleCatalogoReporteService;

    @Getter
    @Setter
    @ManagedProperty("#{repuestoModeloService}")
    private RepuestoModeloService repuestoModeloService;

    @Override
    @Transactional
    public ReporteMantenimiento saveOrUpdateReporteMantenimiento(ReporteMantenimiento reporteMantenimiento) {
        reporteMantenimientoDAO.saveOrUpdateReporteMantenimiento(reporteMantenimiento);
        return reporteMantenimiento;
    }

    @Override
    @Transactional
    public void removeReporteMantenimiento(ReporteMantenimiento reporteMantenimiento) {
        reporteMantenimientoDAO.removeReporteMantenimiento(reporteMantenimiento);
    }

    public void agregarMantenimientoListDetalle(ProductoClienteReporte productoClienteReporte, List<DetalleCatalogoReporte> lista) {

        ReporteMantenimiento mantenimiento;
        for (DetalleCatalogoReporte catalogoReporte : lista) {
            if (catalogoReporte.getSeleccion() != null && catalogoReporte.getSeleccion()) {
                mantenimiento = new ReporteMantenimiento();
                mantenimiento.setIdRepuestoModelo(new RepuestoModelo(catalogoReporte.getIdRepuestoModelo()));
                mantenimiento.setIdProductoClienteReporte(productoClienteReporte);
                mantenimiento.setTipoRepuesto(catalogoReporte.getTipoRepuesto());
                mantenimiento.setEstado(EstadosEnum.ACTIVO.getValue());
                reporteMantenimientoDAO.saveOrUpdateReporteMantenimiento(mantenimiento);
            }
        }
    }

    @Override
    @Transactional
    public void addReporteMantenimientoPreventivo(ProductoClienteReporte productoClienteReporte,
            List<DetalleCatalogoReporte> procesamiento,
            List<DetalleCatalogoReporte> imagen,
            List<DetalleCatalogoReporte> fijacion,
            List<DetalleCatalogoReporte> limpieza) {

        this.agregarMantenimientoListDetalle(productoClienteReporte, procesamiento);
        this.agregarMantenimientoListDetalle(productoClienteReporte, imagen);
        this.agregarMantenimientoListDetalle(productoClienteReporte, fijacion);
        this.agregarMantenimientoListDetalle(productoClienteReporte, limpieza);

    }

    public void agregarRepuesteMantenimientoCorrectivo(ProductoClienteReporte productoClienteReporte, List<DetalleCatalogoReporte> repuestos, String esReparacion, Usuarios usuario) {

        ReporteMantenimiento mantenimiento;
        RepuestoModelo repuestoModeloStock;
        for (DetalleCatalogoReporte catalogoReporte : repuestos) {

            if (catalogoReporte.getTipoRepuesto() != null) {
                mantenimiento = new ReporteMantenimiento();

                mantenimiento.setIdRepuestoModelo(new RepuestoModelo());
                mantenimiento.getIdRepuestoModelo().setId(catalogoReporte.getIdRepuestoModelo());

                mantenimiento.setIdProductoClienteReporte(productoClienteReporte);
                mantenimiento.setTipoRepuesto(catalogoReporte.getTipoRepuesto());
                mantenimiento.setCodigoRepuesto(codigoTipoRepuesto(catalogoReporte.getCodigoRepuesto()));
                mantenimiento.setEstado(EstadosEnum.ACTIVO.getValue());
                mantenimiento.setPorcentaje(catalogoReporte.getPorcentaje());

                mantenimiento = reporteMantenimientoDAO.saveOrUpdateReporteMantenimiento(mantenimiento);
                if (esReparacion.equals(Enums.TIPO_REPORTE_REPARACION.getValue())) {
                    /*
                    repuestoModeloStock = new RepuestoModelo();
                    repuestoModeloStock = repuestoModeloService.getRepuestoModeloById(catalogoReporte.getIdRepuestoModelo());
                    repuestoModeloStock.setStock(repuestoModeloStock.getStock() - 1);
                    repuestoModeloStock.setFechaModificacion(new Date());
                    repuestoModeloStock.setUsuarioModificacion(usuario.getNombreCompleto());
                    repuestoModeloService.updateRepuestoModelo(repuestoModeloStock);
                     */
                }
            }

        }

    }

    @Override
    @Transactional
    public void addReporteMantenimientoCorrectivo(ProductoClienteReporte productoClienteReporte,
            List<DetalleCatalogoReporte> suministros,
            List<DetalleCatalogoReporte> imagen,
            List<DetalleCatalogoReporte> fijacion,
            List<DetalleCatalogoReporte> revelado,
            List<DetalleCatalogoReporte> alimentacion,
            List<DetalleCatalogoReporte> otros, String tipoReparacion,
            Usuarios usuario) {

        this.agregarRepuesteMantenimientoCorrectivo(productoClienteReporte, suministros, tipoReparacion, usuario);
        System.out.println("**************-----------******************* imagen");
        this.agregarRepuesteMantenimientoCorrectivo(productoClienteReporte, imagen, tipoReparacion, usuario);
        System.out.println("**************-----------*******************  fijacion");
        this.agregarRepuesteMantenimientoCorrectivo(productoClienteReporte, fijacion, tipoReparacion, usuario);
        System.out.println("**************-----------******************* revelado");
        this.agregarRepuesteMantenimientoCorrectivo(productoClienteReporte, revelado, tipoReparacion, usuario);
        System.out.println("**************-----------******************* alimentacion");
        this.agregarRepuesteMantenimientoCorrectivo(productoClienteReporte, alimentacion, tipoReparacion, usuario);
        ReporteMantenimiento mantenimiento;
        DetalleCatalogoReporte detalleCatalogoReporte, catalogoReporteBusqueda;
        RepuestoModelo repuestoModelo;
        for (DetalleCatalogoReporte catalogoReporte : otros) {

            if (catalogoReporte.getTipoRepuesto() != null && !catalogoReporte.getDescripcion().equals("")) {

                detalleCatalogoReporte = new DetalleCatalogoReporte();
                mantenimiento = new ReporteMantenimiento();
                catalogoReporteBusqueda = new DetalleCatalogoReporte();

                catalogoReporteBusqueda = detalleCatalogoReporteService.getDetalleCatalogoReporteByDescripcionByIdCabecera(catalogoReporte.getDescripcion(), catalogoReporte.getIdCabecera().getId());
                if (catalogoReporteBusqueda != null) {
                    detalleCatalogoReporte = catalogoReporteBusqueda;
                } else {
                    detalleCatalogoReporte.setId(null);
                    detalleCatalogoReporte.setCodigoRepuesto(catalogoReporte.getCodigoRepuesto());
                    detalleCatalogoReporte.setEstado(true);
                    detalleCatalogoReporte.setIdCabecera(catalogoReporte.getIdCabecera());
                    detalleCatalogoReporte.setTipoRepuesto(catalogoReporte.getTipoRepuesto());
                    detalleCatalogoReporte.setSeleccion(Boolean.FALSE);
                    detalleCatalogoReporte.setDescripcion(catalogoReporte.getDescripcion());
                    detalleCatalogoReporte.setCatalogo(Boolean.FALSE);
                    detalleCatalogoReporte.setFechaCreacion(new Date());
                    detalleCatalogoReporte.setUsuarioCreacion(usuario.getUsuario());
                }

                detalleCatalogoReporte = detalleCatalogoReporteService.saveOrUpdateDetalleCatalogoReporte(detalleCatalogoReporte);
                repuestoModelo = new RepuestoModelo();
                repuestoModelo.setIdDetalleCatalogoReporte(detalleCatalogoReporte);
                repuestoModelo.setIdModelo(new Modelo(catalogoReporte.getIdModelo()));
                repuestoModelo.setFechaCreacion(new Date());
                repuestoModelo.setUsuarioCreacion(usuario.getUsuario());
                repuestoModelo.setEsCatalogo(Boolean.FALSE);
                repuestoModelo.setStock(0);
                repuestoModelo.setEstado(1);
                repuestoModelo = repuestoModeloService.addRepuestoModelo(repuestoModelo);
                detalleCatalogoReporte.setIdModelo(repuestoModelo.getId());

                mantenimiento.setIdRepuestoModelo(new RepuestoModelo(detalleCatalogoReporte.getIdModelo()));
                mantenimiento.setEstado(EstadosEnum.ACTIVO.getValue());
                mantenimiento.setIdProductoClienteReporte(productoClienteReporte);
                mantenimiento.setTipoRepuesto(catalogoReporte.getTipoRepuesto());
                mantenimiento.setCodigoRepuesto(catalogoReporte.getCodigoRepuesto());
                reporteMantenimientoDAO.saveOrUpdateReporteMantenimiento(mantenimiento);

            }

        }

    }

    @Override
    @Transactional
    public void addReporteMantenimientoInstalacionNueva(ProductoClienteReporte productoClienteReporte, List<DetalleCatalogoReporte> preguntas, Usuarios usuario) {
        if (!preguntas.isEmpty()) {
            this.agregarMantenimientoListDetalle(productoClienteReporte, preguntas);
        }

    }

    @Override
    @Transactional
    public void updateReporteMantenimientoInstalacionNueva(ProductoClienteReporte productoClienteReporte, List<DetalleCatalogoReporte> preguntas, Usuarios usuario) {

        if (preguntas.isEmpty()) {
            return;
        }

        for (DetalleCatalogoReporte detalleCatalogoReporte : preguntas) {
            //   System.out.println("DETALLE "+ detalleCatalogoReporte);
        }

        System.out.println("*******************************************");
        System.out.println("*******************************************");

        ReporteMantenimiento reporteMantenimiento;
        for (DetalleCatalogoReporte detalleCatalogoReporte : preguntas) {
            reporteMantenimiento = new ReporteMantenimiento();
            reporteMantenimiento.setIdRepuestoModelo(new RepuestoModelo());
            reporteMantenimiento.setIdProductoClienteReporte(new ProductoClienteReporte());
            if (detalleCatalogoReporte.getSeleccion() != null && detalleCatalogoReporte.getSeleccion()) {

                reporteMantenimiento.setIdRepuestoModelo(new RepuestoModelo());
                reporteMantenimiento.setIdProductoClienteReporte(new ProductoClienteReporte());
                reporteMantenimiento.setIdRepuestoModelo(new RepuestoModelo(detalleCatalogoReporte.getIdRepuestoModelo()));
                reporteMantenimiento.setIdProductoClienteReporte(productoClienteReporte);
                reporteMantenimiento.setEstado(EstadosEnum.ACTIVO.getValue());

                for (ReporteMantenimiento mantenimiento : productoClienteReporte.getReporteMantenimientoList()) {
                    if (detalleCatalogoReporte.getIdRepuestoModelo() != mantenimiento.getIdRepuestoModelo().getId()) {
                        reporteMantenimiento = new ReporteMantenimiento();
                        reporteMantenimiento.setIdRepuestoModelo(new RepuestoModelo());
                        reporteMantenimiento.setIdProductoClienteReporte(new ProductoClienteReporte());
                        reporteMantenimiento.setIdRepuestoModelo(new RepuestoModelo(detalleCatalogoReporte.getIdRepuestoModelo()));
                        reporteMantenimiento.setIdProductoClienteReporte(productoClienteReporte);
                        reporteMantenimiento.setEstado(EstadosEnum.ACTIVO.getValue());

                    } else {
                        reporteMantenimiento = mantenimiento;
                        break;
                    }

                }

                if (reporteMantenimiento.getId() == null) {

                    this.saveOrUpdateReporteMantenimiento(reporteMantenimiento);
                }

            } else {

                for (ReporteMantenimiento mantenimiento : productoClienteReporte.getReporteMantenimientoList()) {
                    if (mantenimiento.getIdRepuestoModelo().getIdDetalleCatalogoReporte().getIdCabecera().getCodigo().equals(detalleCatalogoReporte.getIdCabecera().getCodigo())) {

                        if (mantenimiento.getIdRepuestoModelo().getId() == detalleCatalogoReporte.getIdRepuestoModelo()) {
                            reporteMantenimiento = new ReporteMantenimiento();
                            reporteMantenimiento.setIdRepuestoModelo(new RepuestoModelo());
                            reporteMantenimiento.setIdProductoClienteReporte(new ProductoClienteReporte());
                            reporteMantenimiento = mantenimiento;
                            reporteMantenimiento.setEstado(detalleCatalogoReporte.getSeleccion() ? EstadosEnum.ACTIVO.getValue() : EstadosEnum.INACTIVO.getValue());
                            if (reporteMantenimiento.getEstado() == EstadosEnum.INACTIVO.getValue()) {
                                this.removeReporteMantenimiento(mantenimiento);
                            }
                            break;
                        }
                    }
                }
            }

        }

    }

    public void updateListaReporteMantenimientoOtros(ProductoClienteReporte productoClienteReporte, List<DetalleCatalogoReporte> lista, Modelo modelo,
            Usuarios usuario) {

        ReporteMantenimiento mantenimiento;
        DetalleCatalogoReporte detalleCatalogo, detalleCatalogoBusqueda;
        RepuestoModelo repuestoModelo, repuestoModeloBusqueda;
        for (DetalleCatalogoReporte detalleCatalogoReporte : lista) {

            if (!detalleCatalogoReporte.getCodigoRepuesto().isEmpty() && detalleCatalogoReporte.getTipoRepuesto() != null) {
                detalleCatalogo = new DetalleCatalogoReporte();
                mantenimiento = new ReporteMantenimiento();
                repuestoModelo = new RepuestoModelo();
                for (ReporteMantenimiento reporteMantenimiento : productoClienteReporte.getReporteMantenimientoList()) {
                    if (reporteMantenimiento.getIdRepuestoModelo().getIdDetalleCatalogoReporte().getIdCabecera().getCodigo().equals(detalleCatalogoReporte.getIdCabecera().getCodigo())) {

                        if (detalleCatalogoReporte.getIdRepuestoModelo() == reporteMantenimiento.getIdRepuestoModelo().getId()) {
                            repuestoModelo = reporteMantenimiento.getIdRepuestoModelo();
                            mantenimiento = reporteMantenimiento;

                        } else {

                            /* repuestoModelo.setIdModelo(new Modelo(detalleCatalogoReporte.getIdModelo()));
                            repuestoModelo.setStock(0);
                            repuestoModelo.setEsCatalogo(Boolean.FALSE);
                            repuestoModelo.setEstado(EstadosEnum.ACTIVO.getValue());
                            repuestoModelo.setUsuarioCreacion(usuario.getNombreCompleto());
                            repuestoModelo.setFechaCreacion(new Date());
                            repuestoModelo.setCodigoRepuesto(detalleCatalogoReporte.getCodigoRepuesto());
                            repuestoModelo.setTipoRepuesto(detalleCatalogoReporte.getTipoRepuesto());*/
                        }
                    }
                }

                detalleCatalogo.setId(null);
                detalleCatalogo.setDescripcion(detalleCatalogoReporte.getDescripcion());
                detalleCatalogo.setUsuarioCreacion(usuario.getNombreCompleto());
                detalleCatalogo.setFechaCreacion(new Date());
                detalleCatalogo.setEstado(true);
                detalleCatalogo.setCatalogo(Boolean.FALSE);
                detalleCatalogo.setIdCabecera(detalleCatalogoReporte.getIdCabecera());

                detalleCatalogoBusqueda = detalleCatalogoReporteService.getDetalleCatalogoReporteByDescripcionByIdCabecera(detalleCatalogoReporte.getDescripcion(), detalleCatalogoReporte.getIdCabecera().getId());
                if (detalleCatalogoBusqueda != null) {
                    detalleCatalogo = detalleCatalogoBusqueda;
                }

                detalleCatalogo = detalleCatalogoReporteService.saveOrUpdateDetalleCatalogoReporte(detalleCatalogo);
                repuestoModeloBusqueda = repuestoModeloService.getRepuestoModeloByIdDetalleByIdModeloByCodigo(detalleCatalogo.getId(), modelo.getId(), detalleCatalogoReporte.getCodigoRepuesto());

                if (repuestoModeloBusqueda != null) {
                    repuestoModelo = repuestoModeloBusqueda;
                } else {
                    repuestoModelo.setIdDetalleCatalogoReporte(detalleCatalogo);
                    repuestoModelo.setIdModelo(modelo);
                    repuestoModelo.setStock(0);
                    repuestoModelo.setEsCatalogo(Boolean.FALSE);
                    repuestoModelo.setEstado(EstadosEnum.ACTIVO.getValue());
                    repuestoModelo.setUsuarioCreacion(usuario.getNombreCompleto());
                    repuestoModelo.setFechaCreacion(new Date());
                    repuestoModelo.setCodigoRepuesto(detalleCatalogoReporte.getCodigoRepuesto());
                    repuestoModelo.setTipoRepuesto(detalleCatalogoReporte.getTipoRepuesto());
                }

                repuestoModelo = repuestoModeloService.addRepuestoModelo(repuestoModelo);

                mantenimiento.setCodigoRepuesto(detalleCatalogoReporte.getCodigoRepuesto());
                mantenimiento.setTipoRepuesto(detalleCatalogoReporte.getTipoRepuesto());
                mantenimiento.setEstado(EstadosEnum.ACTIVO.getValue());
                mantenimiento.setIdProductoClienteReporte(productoClienteReporte);
                mantenimiento.setIdRepuestoModelo(repuestoModelo);

                mantenimiento = this.saveOrUpdateReporteMantenimiento(mantenimiento);
            } else {

                detalleCatalogo = new DetalleCatalogoReporte();
                mantenimiento = new ReporteMantenimiento();
                repuestoModelo = new RepuestoModelo();
                for (ReporteMantenimiento reporteMantenimiento : productoClienteReporte.getReporteMantenimientoList()) {
                    if (reporteMantenimiento.getIdRepuestoModelo().getIdDetalleCatalogoReporte().getIdCabecera().getCodigo().equals(detalleCatalogoReporte.getIdCabecera().getCodigo())) {
                        if (detalleCatalogoReporte.getIdRepuestoModelo() == reporteMantenimiento.getIdRepuestoModelo().getId()) {

                            this.removeReporteMantenimiento(reporteMantenimiento);
                        }
                    }
                }
            }

        }
    }

    public void updateListaReporteMantenimientoCorrectivo(ProductoClienteReporte productoClienteReporte, List<DetalleCatalogoReporte> lista, Usuarios usuario) {

        RepuestoModelo repuestoModeloStock;
        ReporteMantenimiento mantenimiento;
        for (DetalleCatalogoReporte catalogoDetalle : lista) {
            mantenimiento = new ReporteMantenimiento();
            if (catalogoDetalle.getTipoRepuesto() != null && !catalogoDetalle.getCodigoRepuesto().isEmpty()) {
                for (ReporteMantenimiento reporteMantenimiento : productoClienteReporte.getReporteMantenimientoList()) {
                    if (reporteMantenimiento.getIdRepuestoModelo().getIdDetalleCatalogoReporte().getIdCabecera().getTipo().equals(Enums.CORRECTIVO.getValue())) {

                        if (catalogoDetalle.getId() == reporteMantenimiento.getIdRepuestoModelo().getIdDetalleCatalogoReporte().getId()) {
                            mantenimiento.setId(reporteMantenimiento.getId());
                            mantenimiento.setIdRepuestoModelo(reporteMantenimiento.getIdRepuestoModelo());
                            mantenimiento.setIdProductoClienteReporte(productoClienteReporte);
                        } else {
                            mantenimiento.setIdRepuestoModelo(new RepuestoModelo(catalogoDetalle.getIdRepuestoModelo()));
                            mantenimiento.setIdProductoClienteReporte(productoClienteReporte);
                        }
                        mantenimiento.setPorcentaje(catalogoDetalle.getPorcentaje());
                        mantenimiento.setCodigoRepuesto(codigoTipoRepuesto(catalogoDetalle.getCodigoRepuesto()));
                        mantenimiento.setTipoRepuesto(catalogoDetalle.getTipoRepuesto());
                        mantenimiento.setEstado(EstadosEnum.ACTIVO.getValue());
                    }
                }

                if (catalogoDetalle.getSeleccion() != null && catalogoDetalle.getSeleccion()) {

                    /*  repuestoModeloStock = repuestoModeloService.getRepuestoModeloById(catalogoDetalle.getIdRepuestoModelo());
                    repuestoModeloStock.setStock(repuestoModeloStock.getStock() - 1);
                    repuestoModeloStock.setFechaModificacion(new Date());
                    repuestoModeloStock.setUsuarioModificacion(usuario.getNombreCompleto());
                    repuestoModeloService.updateRepuestoModelo(repuestoModeloStock);*/
                }

                mantenimiento = this.saveOrUpdateReporteMantenimiento(mantenimiento);

            } else {

                for (ReporteMantenimiento reporteMantenimiento : productoClienteReporte.getReporteMantenimientoList()) {
                    if (reporteMantenimiento.getIdRepuestoModelo().getIdDetalleCatalogoReporte().getIdCabecera().getTipo().equals(Enums.CORRECTIVO.getValue())) {
                        mantenimiento = new ReporteMantenimiento();
                        if (catalogoDetalle.getId() == reporteMantenimiento.getIdRepuestoModelo().getIdDetalleCatalogoReporte().getId()) {
                            mantenimiento = reporteMantenimiento;
                            mantenimiento.setEstado(EstadosEnum.INACTIVO.getValue());
                            mantenimiento = this.saveOrUpdateReporteMantenimiento(mantenimiento);

                            if (catalogoDetalle.getTipoRepuesto() == null && catalogoDetalle.getCodigoRepuesto().isEmpty()) {

                                /*  repuestoModeloStock = new RepuestoModelo();
                                repuestoModeloStock = repuestoModeloService.getRepuestoModeloById(reporteMantenimiento.getIdRepuestoModelo().getId());
                                repuestoModeloStock.setStock(repuestoModeloStock.getStock() + 1);
                                repuestoModeloStock.setFechaModificacion(new Date());
                                repuestoModeloStock.setUsuarioModificacion(usuario.getNombreCompleto());
                                repuestoModeloService.updateRepuestoModelo(repuestoModeloStock);*/
                            }
                        }
                    }
                }

            }
        }

    }

    @Override
    @Transactional
    public void updateReporteMantenimientoCorrectivo(ProductoClienteReporte productoClienteReporte,
            List<DetalleCatalogoReporte> suministros, List<DetalleCatalogoReporte> imagen,
            List<DetalleCatalogoReporte> fijacion, List<DetalleCatalogoReporte> revelado,
            List<DetalleCatalogoReporte> listaAlimentacion, List<DetalleCatalogoReporte> otros, Modelo modelo, Usuarios usuario) {

        System.out.println(" ACTUALIZAR  suministros ... ");
        this.updateListaReporteMantenimientoCorrectivo(productoClienteReporte, suministros, usuario);

        System.out.println(" ACTUALIZAR  imagen ... ");
        this.updateListaReporteMantenimientoCorrectivo(productoClienteReporte, imagen, usuario);

        System.out.println(" ACTUALIZAR  fijacion ... ");
        this.updateListaReporteMantenimientoCorrectivo(productoClienteReporte, fijacion, usuario);

        System.out.println(" ACTUALIZAR  revelado ... ");
        this.updateListaReporteMantenimientoCorrectivo(productoClienteReporte, revelado, usuario);

        System.out.println(" ACTUALIZAR  listaAlimentacion ... ");
        this.updateListaReporteMantenimientoCorrectivo(productoClienteReporte, listaAlimentacion, usuario);
        System.out.println(" ACTUALIZAR  otros ... ");
        this.updateListaReporteMantenimientoOtros(productoClienteReporte, otros, modelo, usuario);
    }

    public void updateListaReporteMantenimientoPreventivo(ProductoClienteReporte productoClienteReporte, List<DetalleCatalogoReporte> lista) {

        ReporteMantenimiento reporteMantenimiento;
        for (DetalleCatalogoReporte detalleCatalogoReporte : lista) {
            reporteMantenimiento = new ReporteMantenimiento();
            reporteMantenimiento.setIdRepuestoModelo(new RepuestoModelo());
            reporteMantenimiento.setIdProductoClienteReporte(new ProductoClienteReporte());
            if (detalleCatalogoReporte.getSeleccion() != null && detalleCatalogoReporte.getSeleccion()) {
                for (ReporteMantenimiento mantenimiento : productoClienteReporte.getReporteMantenimientoList()) {
                    if (detalleCatalogoReporte.getIdRepuestoModelo() != mantenimiento.getIdRepuestoModelo().getId()) {
                        reporteMantenimiento = new ReporteMantenimiento();
                        reporteMantenimiento.setIdRepuestoModelo(new RepuestoModelo());
                        reporteMantenimiento.setIdProductoClienteReporte(new ProductoClienteReporte());
                        reporteMantenimiento.setIdRepuestoModelo(new RepuestoModelo(detalleCatalogoReporte.getIdRepuestoModelo()));
                        reporteMantenimiento.setIdProductoClienteReporte(productoClienteReporte);
                        reporteMantenimiento.setEstado(EstadosEnum.ACTIVO.getValue());

                    } else {
                        reporteMantenimiento = mantenimiento;
                        break;
                    }

                }

                System.out.println("dwetalle  " + detalleCatalogoReporte);
                System.out.println("  insertar   " + reporteMantenimiento);
                System.out.println("-******************");
                if (reporteMantenimiento.getId() == null) {

                    this.saveOrUpdateReporteMantenimiento(reporteMantenimiento);
                }

            } else {

                for (ReporteMantenimiento mantenimiento : productoClienteReporte.getReporteMantenimientoList()) {
                    if (mantenimiento.getIdRepuestoModelo().getIdDetalleCatalogoReporte().getIdCabecera().getCodigo().equals(detalleCatalogoReporte.getIdCabecera().getCodigo())) {
                        if (mantenimiento.getIdRepuestoModelo().getIdDetalleCatalogoReporte().getId() == detalleCatalogoReporte.getId()) {
                            reporteMantenimiento = new ReporteMantenimiento();
                            reporteMantenimiento.setIdRepuestoModelo(new RepuestoModelo());
                            reporteMantenimiento.setIdProductoClienteReporte(new ProductoClienteReporte());

                            reporteMantenimiento = mantenimiento;
                            reporteMantenimiento.setEstado(detalleCatalogoReporte.getSeleccion() ? EstadosEnum.ACTIVO.getValue() : EstadosEnum.INACTIVO.getValue());
                            if (reporteMantenimiento.getEstado() == EstadosEnum.INACTIVO.getValue()) {
                                this.removeReporteMantenimiento(reporteMantenimiento);
                            }
                            break;
                        }
                    }
                }
            }

        }
    }

    @Override
    @Transactional
    public void updateReporteMantenimientoPreventivo(ProductoClienteReporte productoClienteReporte,
            List<DetalleCatalogoReporte> procesamiento, List<DetalleCatalogoReporte> imagen,
            List<DetalleCatalogoReporte> fijacion, List<DetalleCatalogoReporte> limpieza) {

        this.updateListaReporteMantenimientoPreventivo(productoClienteReporte, procesamiento);
        this.updateListaReporteMantenimientoPreventivo(productoClienteReporte, imagen);
        this.updateListaReporteMantenimientoPreventivo(productoClienteReporte, fijacion);
        this.updateListaReporteMantenimientoPreventivo(productoClienteReporte, limpieza);

    }

    @Override
    @Transactional
    public List<ReporteMantenimiento> getReporteMantenimientoByReporteId(Integer id) {
        return reporteMantenimientoDAO.getReporteMantenimientoByReporteId(id);

    }

    @Override
    @Transactional
    public List<ReporteMantenimiento> getReporteMantenimientoByDetalleCatalogoId(Integer id) {
        return reporteMantenimientoDAO.getReporteMantenimientoByDetalleCatalogoId(id);
    }

}
