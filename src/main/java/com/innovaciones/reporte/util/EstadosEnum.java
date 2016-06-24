/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.util;

/**
 *
 * @author pisama
 */
public enum EstadosEnum {

    ACTIVO("ACTIVO", 1),
    INACTIVO("INACTIVO", 0);

    private final String propertyName; //Nombre del estado (propiedad)
    private final Integer value; // Valor del estado

    EstadosEnum(String propertyName, Integer value) {
        this.propertyName = propertyName;
        this.value = value;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Integer getValue() {
        return value;
    }
}
