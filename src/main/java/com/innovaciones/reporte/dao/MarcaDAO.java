/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.dao;

import com.innovaciones.reporte.model.Marca;
import java.util.List;

/**
 *
 * @author pisama
 */
public interface MarcaDAO {

    public Marca addMarca(Marca marca);
    
       public Marca saveMarca(Marca marca);

    public List<Marca> getMarcas();

    public Marca getMarcaById(Integer id);

    public Marca getMarcaByMarca(String id);
    
    public List<Marca> getMarcasByEstado(Integer estado);
}
