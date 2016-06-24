/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.Cliente;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface ClienteDAO {

    public Cliente addCliente(Cliente cliente);

    public Cliente getClienteByRuc(String ruc);
    
    public Cliente getClienteByNombre(String nombre);

    public List<Cliente> getClientes();

    public List<Cliente> getClientesByEstado(Integer estado);

}
