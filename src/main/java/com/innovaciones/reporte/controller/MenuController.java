/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author pisama
 */
@ManagedBean
@SessionScoped
public class MenuController implements Serializable{

    /**
     * Creates a new instance of MenuController
     */
    public MenuController() {
    }
    
    @PostConstruct
    void init (){
    
    }
    
}
