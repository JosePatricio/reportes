/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.service;

import com.innovaciones.reporte.dao.ClienteDAO;
import com.innovaciones.reporte.model.Cliente;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
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
@ManagedBean(name = "clienteService")
@ViewScoped

public class ClienteServiceImpl implements ClienteService, Serializable {

    @Getter
    @Setter
    private ClienteDAO clienteDAO;

    @Override
    @Transactional
    public Cliente addCliente(Cliente cliente) {
        return clienteDAO.addCliente(cliente);
    }

    @Override
    @Transactional
    public Cliente getClienteByRuc(String ruc) {

        return clienteDAO.getClienteByRuc(ruc);
    }

    @Override
    @Transactional
    public List<Cliente> getClientes() {
        return clienteDAO.getClientes();
    }

    @Override
    @Transactional
    public List<Cliente> getClientesByEstado(Integer estado) {
        return clienteDAO.getClientesByEstado(estado);
    }

    @Override
    @Transactional
    public Cliente getClienteByNombre(String nombre) {
        return clienteDAO.getClienteByNombre(nombre);
    }

}
