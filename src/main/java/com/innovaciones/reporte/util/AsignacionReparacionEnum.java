/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.util;

/**
 *
 * @author Fernando
 */
public enum AsignacionReparacionEnum {

    PRIORIDAD_DEFAULT("Default", 0),
    PRIORIDAD_BAJA("Baja", 1),
    PRIORIDAD_NORMAL("Normal", 2),
    PRIORIDAD_ALTA("Alta", 3);

    private final String propertyName; //Nombre del estado (propiedad)
    private final Integer value; // Valor del estado

    AsignacionReparacionEnum(String propertyName, Integer value) {
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
