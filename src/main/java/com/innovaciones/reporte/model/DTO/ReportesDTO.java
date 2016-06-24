/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.model.DTO;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pisama
 */
public class ReportesDTO implements Serializable {

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String factura;

    @Getter
    @Setter
    private String numeroFactura;

    @Getter
    @Setter
    private String descripcion;

    @Getter
    @Setter
    private String ruc;

    @Getter
    @Setter
    private String cliente;

    @Getter
    @Setter
    private String ciudad;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String nombre_soporte;

    @Getter
    @Setter
    private Date fecha;

    @Getter
    @Setter
    private String tipoReporte;

    @Getter
    @Setter
    private String equipo;

    @Getter
    @Setter
    private String soporte;

    @Getter
    @Setter
    private String serial;

    @Getter
    @Setter
    private String estado;

    @Getter
    @Setter
    private String tipo;

    @Getter
    @Setter
    private String subtipo;

    @Getter
    @Setter
    private String mantenimiento;

    @Getter
    @Setter
    private String nota;

    public ReportesDTO() {
    }

}
