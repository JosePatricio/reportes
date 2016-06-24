/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.beans;

import com.innovaciones.reporte.model.CabeceraCatalogoReporte;
import com.innovaciones.reporte.model.Cliente;
import com.innovaciones.reporte.model.DetalleCatalogoReporte;
import com.innovaciones.reporte.model.Marca;
import com.innovaciones.reporte.model.Modelo;
import com.innovaciones.reporte.model.Producto;
import com.innovaciones.reporte.model.ProductoCliente;
import com.innovaciones.reporte.model.ProductoClienteReporte;
import com.innovaciones.reporte.model.ProductoDetalleReporte;
import com.innovaciones.reporte.model.Reporte;
import com.innovaciones.reporte.model.DTO.ReportesDTO;
import com.innovaciones.reporte.model.DetalleReporteInstalacionNueva;
import com.innovaciones.reporte.model.DetalleReporteTemporal;
import com.innovaciones.reporte.model.RepuestoModelo;
import com.innovaciones.reporte.model.TipoVisita;
import com.innovaciones.reporte.model.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pisama
 */
@ManagedBean(name = "reporteBean")
@ViewScoped
public class ReporteBean implements Serializable {

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listDetalleCatalogoReporte;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listProcesamiento;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listPreventivoImagen;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listPreventivoFijacion;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listPreventivoLimpieza;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listCorrectivoLimpieza;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listCorrectivoSuministros;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listCorrectivoImagen;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listCorrectivoFijacion;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listCorrectivoRevelado;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listCorrectivoAlimentacion;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listCorrectivoAlimentacionSelected;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listCorrectivoOtros;

    @Getter
    @Setter
    private List<CabeceraCatalogoReporte> listcabeceraCatalogoReportesPreventivo;

    @Getter
    @Setter
    private List<TipoVisita> listTipoVisitas;

    @Getter
    @Setter
    private List<Cliente> listCliente;

    @Getter
    @Setter
    private List<Cliente> listClienteFiltered;
    @Getter
    @Setter
    private Cliente clienteSelected;

    @Getter
    @Setter
    private List<Producto> listProducto;
    @Getter
    @Setter
    private List<Producto> listProductoFiltered;

    @Getter
    @Setter
    private List<Marca> listMarca;

    @Getter
    @Setter
    private List<Modelo> listModelo;

    @Getter
    @Setter
    private List<ReportesDTO> reportesDTOs;

    @Getter
    @Setter
    private List<ReportesDTO> reportesDTOsFiltered;

    @Getter
    @Setter
    private ReportesDTO reportesDTOSelected;

    @Getter
    @Setter
    private Reporte reporte;

    @Getter
    @Setter
    private Reporte reporteSelected;

    @Getter
    @Setter
    private Cliente cliente;

    @Getter
    @Setter
    private Producto producto;

    @Getter
    @Setter
    private Producto productoTemporal;

    @Getter
    @Setter
    private Producto productoSelected;

    @Getter
    @Setter
    private Marca marca;

    @Getter
    @Setter
    private Modelo modelo;

    @Getter
    @Setter
    private Usuarios usuarios;

    @Getter
    @Setter
    private Marca marcaSelected;

    @Getter
    @Setter
    private Modelo modeloSelected;

    @Getter
    @Setter
    private DetalleCatalogoReporte detalleCatalogoReporte;

    @Getter
    @Setter
    private ProductoCliente productoCliente;

    @Getter
    @Setter
    private ProductoCliente productoClienteTemporal;

    @Getter
    @Setter
    private ProductoCliente productoClienteTemporalSelected;

    @Getter
    @Setter
    private ProductoCliente productoClienteSelected;

    @Getter
    @Setter
    private ProductoClienteReporte productoClienteReporte;

    @Getter
    @Setter
    private ProductoClienteReporte productoClienteReporteSelected;

    @Getter
    @Setter
    private ProductoClienteReporte productoClienteReporteTemporalSelected;

    @Getter
    @Setter
    private ProductoDetalleReporte productoDetalleReporte;

    @Getter
    @Setter
    private Boolean disable;

    @Getter
    @Setter
    private Boolean estadoFirma;

    @Getter
    @Setter
    private String reporteActual;

    @Getter
    @Setter
    private String EstadoActualMantenimiento;

    @Getter
    @Setter
    private Integer idTipoVisita;

    @Getter
    @Setter
    private String filtroSerial;

    @Getter
    @Setter
    private String filtroCedula;

    @Getter
    @Setter
    private String filtroProducto;

    @Getter
    @Setter
    private Integer numeroFacturaTecnico;

    @Getter
    @Setter
    private String campoHabilitado;

    @Getter
    @Setter
    private Boolean habilitarGuardarConSerial;

    @Getter
    @Setter
    private List<RepuestoModelo> listRepuestosHistorial;

    @Getter
    @Setter
    private DetalleCatalogoReporte repuestoHistorial;

    @Getter
    @Setter
    private RepuestoModelo repuestoHistorialSelected;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listMedicionesElectricas;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listConsideraciones;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listPreguntas;

    @Getter
    @Setter
    private DetalleReporteTemporal detalleReporteTemporal;

}
