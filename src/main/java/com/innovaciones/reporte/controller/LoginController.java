/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.UsuarioBean;
import com.innovaciones.reporte.model.Usuarios;
import com.innovaciones.reporte.util.Utilities;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.innovaciones.reporte.service.UsuariosService;
import com.innovaciones.reporte.util.Enums;
import com.innovaciones.reporte.util.EstadosEnum;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedProperty;
import javax.servlet.ServletException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author pisama
 */
@ManagedBean(name = "loginController")
@ViewScoped
public class LoginController extends Utilities implements Serializable {

    @Getter
    @Setter
    @ManagedProperty("#{usuarioBean}")
    private UsuarioBean usuarioBean;

    @Getter
    @Setter
    @ManagedProperty("#{usuariosService}")
    private UsuariosService loginService;

    @Getter
    @Setter
    @ManagedProperty("#{sesionController}")
    private SesionController sesionController;

    @Getter
    @Setter
    private Boolean msj;

    @PostConstruct
    void init() {
        usuarioBean.setUsuario(new Usuarios());
        usuarioBean.getUsuario().setUsuario("pato1");

        setMsj(!getSessionText(Enums.PARAMETRO_MENSAJE_LOGIN.getValue()).isEmpty());

    }

    public void controlSessionSpringSecurity() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            redireccionar("paginas/menu/menu-principal.jsf");
        }

    }

    public String doLogin() throws IOException, ServletException, Exception {
        usuarioBean.setUsuarioSelected(loginService.login(usuarioBean.getUsuario().getUsuario(), encrypt(usuarioBean.getUsuario().getClave()), EstadosEnum.ACTIVO.getValue()));
        if (usuarioBean.getUsuarioSelected() != null) {
            sesionController.getSesionBean().setUsuarios(usuarioBean.getUsuarioSelected());
            sesionController.getSesionBean().setIsAdminPostVentaRole(habilitarGestionReporte(usuarioBean.getUsuarioSelected()));
            sesionController.getSesionBean().setIsHabilitadoMenuCorto(habilitarMenuCorto(usuarioBean.getUsuarioSelected()));
            killSession(Enums.PARAMETRO_MENSAJE_LOGIN.getValue());
        } else {
            setSessionText(Enums.PARAMETRO_MENSAJE_LOGIN.getValue(), "TRUE");
        }

        sendPost("/j_spring_security_check");

        return null;
    }

    public String doLogout() throws ServletException, IOException {
        sendPost("/j_spring_security_logout");
        return null;
    }

}
