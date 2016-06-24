/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.beans;

import com.innovaciones.reporte.model.CabeceraCatalogoReporte;
import com.innovaciones.reporte.model.DetalleCatalogoReporte;
import com.innovaciones.reporte.model.RepuestoModelo;
import com.innovaciones.reporte.util.Enums;
import com.innovaciones.reporte.util.EstadosEnum;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author pisama
 */
@ManagedBean(name = "detalleCatalogoReporteBean")
@ViewScoped
public class DetalleCatalogoReporteBean implements Serializable {

    @Getter
    @Setter
    private DetalleCatalogoReporte detalleCatalogoReporte;

    @Getter
    @Setter
    private DetalleCatalogoReporte detalleCatalogoReporteSelected;

    @Getter
    @Setter
    private DetalleCatalogoReporte detalleCatalogoReporteNuevoVersion;

    @Getter
    @Setter
    private String codigoNuevoRepuesto;

    @Getter
    @Setter
    private String codigoEdicionRepuesto;

    @Getter
    @Setter
    private Integer stockNuevoRepuesto;

    @Getter
    @Setter
    private Integer stockEdicionRepuesto;

    @Getter
    @Setter
    private BigDecimal porcentajeNuevoRepuesto;

    @Getter
    @Setter
    private BigDecimal porcentajeEdicionRepuesto;

    @Getter
    @Setter
    private List<CabeceraCatalogoReporte> listCabeceraCatalogoReportes;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listDetalleCatalogoReporte;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listFiltrados;

    @Getter
    @Setter
    private List<DetalleCatalogoReporte> listDetalleCatalogoReporteVersiones;

    @Getter
    @Setter
    private List<EstadosEnum> listEstados;

    @Getter
    @Setter
    private Integer estado;

    @Getter
    @Setter
    private List<RepuestoModelo> listRepuestoModelosByIdRepuesto;

    @Getter
    private final String[] tipoMantenimiento = {Enums.PREVENTIVO.getValue(), Enums.CORRECTIVO.getValue(), Enums.INSTALACION_NUEVA.getValue()};

    @Setter
    @Getter
    private List<SelectItem> groupSelectCatalogosMantenimiento;

    @Setter
    @Getter
    private List<SelectItem> groupSelectCatalogosMantenimientoFilter;

    @Setter
    @Getter
    private Boolean cambioDeVersionReparacion;

    @Setter
    @Getter
    private Boolean isNew;

    @Setter
    @Getter
    private String modalLabel;

    public DetalleCatalogoReporteBean() {

    }

    public static List<SelectItem> groupSelectMantenimientos(List<CabeceraCatalogoReporte> list, Boolean esIdValue) {
        List<CabeceraCatalogoReporte> listMantenimientoPreventivo = new ArrayList<CabeceraCatalogoReporte>();
        List<CabeceraCatalogoReporte> listMantenimientoCorrectivo = new ArrayList<CabeceraCatalogoReporte>();
        List<CabeceraCatalogoReporte> listNuevaInstalacion = new ArrayList<CabeceraCatalogoReporte>();

        for (CabeceraCatalogoReporte object : list) {
            if (object.getTipo().equals(Enums.PREVENTIVO.getValue())) {
                listMantenimientoPreventivo.add(object);
            } else if (object.getTipo().equals(Enums.CORRECTIVO.getValue())) {
                listMantenimientoCorrectivo.add(object);
            } else if (object.getTipo().equals(Enums.INSTALACION_NUEVA.getValue())) {
                listNuevaInstalacion.add(object);
            }
        }

        SelectItem arrayCorrectivo[] = new SelectItem[listMantenimientoPreventivo.size()];
        SelectItem arrayPreventivo[] = new SelectItem[listMantenimientoCorrectivo.size()];
        SelectItem arrayNuevaInstalacion[] = new SelectItem[listNuevaInstalacion.size()];

        for (int i = 0; i < listMantenimientoPreventivo.size(); i++) {

            arrayCorrectivo[i] = new SelectItem(esIdValue ? listMantenimientoPreventivo.get(i).getId() : listMantenimientoPreventivo.get(i).getDescripcion(), listMantenimientoPreventivo.get(i).getDescripcion());
        }

        for (int i = 0; i < listMantenimientoCorrectivo.size(); i++) {
            arrayPreventivo[i] = new SelectItem(esIdValue ? listMantenimientoCorrectivo.get(i).getId() : listMantenimientoCorrectivo.get(i).getDescripcion(), listMantenimientoCorrectivo.get(i).getDescripcion());
        }

        for (int i = 0; i < listNuevaInstalacion.size(); i++) {
            arrayNuevaInstalacion[i] = new SelectItem(esIdValue ? listNuevaInstalacion.get(i).getId() : listNuevaInstalacion.get(i).getDescripcion(), listNuevaInstalacion.get(i).getDescripcion());
        }

        SelectItemGroup g1 = new SelectItemGroup(Enums.PREVENTIVO.getValue());
        g1.setSelectItems(arrayCorrectivo);

        SelectItemGroup g2 = new SelectItemGroup(Enums.CORRECTIVO.getValue());
        g2.setSelectItems(arrayPreventivo);

        SelectItemGroup g3 = new SelectItemGroup(Enums.INSTALACION_NUEVA.getValue());
        g3.setSelectItems(arrayNuevaInstalacion);

        List<SelectItem> res = new ArrayList<SelectItem>();
        res.add(g1);
        res.add(g2);
        res.add(g3);
        return res;
    }

}
