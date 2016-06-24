/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.model.Rol;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface RolService {

    public List<Rol> getRoles();

    public List<Rol> getRolesByestado(Integer estado);
}
