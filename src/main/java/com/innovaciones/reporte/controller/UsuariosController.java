/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.controller;

import com.innovaciones.reporte.beans.UsuarioBean;
import com.innovaciones.reporte.model.Rol;
import com.innovaciones.reporte.model.UsuarioRoles;
import com.innovaciones.reporte.model.Usuarios;
import com.innovaciones.reporte.service.RolService;
import com.innovaciones.reporte.service.UsuarioRolesService;
import com.innovaciones.reporte.service.UsuariosService;
import com.innovaciones.reporte.util.EstadosEnum;
import com.innovaciones.reporte.util.Utilities;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.exception.ConstraintViolationException;
import org.primefaces.event.FileUploadEvent;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author pisama
 */
@ManagedBean(name = "usuariosController")
@ViewScoped

public class UsuariosController extends Utilities implements Serializable {

    @Getter
    @Setter
    @ManagedProperty("#{usuariosService}")
    private UsuariosService usuarioService;

    @Getter
    @Setter
    @ManagedProperty("#{rolService}")
    private RolService rolService;

    @Getter
    @Setter
    @ManagedProperty("#{usuarioRolesService}")
    private UsuarioRolesService usuarioRolesService;

    @Getter
    @Setter
    @ManagedProperty("#{usuarioBean}")
    private UsuarioBean usuarioBean;

    /**
     * Creates a new instance of UsuariosController
     */
    public UsuariosController() {

    }

    @PostConstruct
    void init() {

        usuarioBean.setListUsuarios(new ArrayList<Usuarios>());
        usuarioBean.setListUsuarios(usuarioService.getUsuarios());
        usuarioBean.setListRol(rolService.getRolesByestado(EstadosEnum.ACTIVO.getValue()));
        usuarioBean.setListEstados(cargarEstadosBoolean());
    }

    public void asignarUsername() {
        usuarioBean.getUsuario().setUsuario(usuarioBean.getUsuario().getDni());
    }

    private void cargarIdRolesByUsuario(List<UsuarioRoles> listUsuarioRoles) {
        usuarioBean.setListIdRolesByUsuario(new ArrayList<Integer>());

        for (UsuarioRoles usuarioRoles : listUsuarioRoles) {
            usuarioBean.getListIdRolesByUsuario().add(usuarioRoles.getIdRol().getId());
        }
    }

    public void abrirDialogUsuario(Usuarios usuarios) throws CloneNotSupportedException {
        usuarioBean.setUsuario(new Usuarios());
        usuarioBean.setUsuarioRoles(new UsuarioRoles());
        usuarioBean.getUsuarioRoles().setIdRol(new Rol());

        usuarioBean.setNuevaClave("");
        usuarioBean.setConfirmarNuevaClave("");

        if (usuarios != null) {
            usuarioBean.setUsuario(usuarios);
            usuarioBean.setUsuarioSelected((Usuarios) usuarios.clone());
            cargarIdRolesByUsuario(usuarios.getUsuarioRolesList());
            usuarioBean.setEstado(usuarios.getEstado());
            usuarioBean.setIsEdit(Boolean.TRUE);
        } else {
            usuarioBean.setListIdRolesByUsuario(new ArrayList<Integer>());
            usuarioBean.setEstado(EstadosEnum.ACTIVO.getValue());
            usuarioBean.getUsuario().setEstado(EstadosEnum.ACTIVO.getValue());
            usuarioBean.setIsEdit(Boolean.FALSE);
        }
        update("formEditUsuarios");
        openModalBS("dlgUsuarios");
    }

    private List<UsuarioRoles> generarListaRolesToGuardar(Usuarios usuario, List<Integer> listRoles) {
        List<UsuarioRoles> result = new ArrayList<UsuarioRoles>();

        for (int i = 0; i < listRoles.size(); i++) {
            Object id = (Object) listRoles.get(i);

            Integer idRol = Integer.parseInt(id.toString());

            UsuarioRoles usuarioRoles = new UsuarioRoles();

            usuarioRoles.setIdRol(new Rol(idRol));
            usuarioRoles.setIdUsuario(new Usuarios(usuario.getId()));
            result.add(usuarioRoles);
        }
        return result;
    }

    public boolean verificarNuevaClave() {

        boolean flag = Boolean.TRUE;

        usuarioBean.setNuevaClave(usuarioBean.getNuevaClave().trim());
        usuarioBean.setConfirmarNuevaClave(usuarioBean.getConfirmarNuevaClave().trim());

        if (!usuarioBean.getNuevaClave().isEmpty() && usuarioBean.getConfirmarNuevaClave().isEmpty()) {
            warn("Confirme la nueva clave");
            flag = Boolean.FALSE;
        }

        if (usuarioBean.getNuevaClave().isEmpty() && !usuarioBean.getConfirmarNuevaClave().isEmpty()) {
            warn("Ingrese la nueva clave");
            flag = Boolean.FALSE;
        }

        if (!usuarioBean.getNuevaClave().isEmpty() && !usuarioBean.getConfirmarNuevaClave().isEmpty()) {
            if (usuarioBean.getNuevaClave().equalsIgnoreCase(usuarioBean.getConfirmarNuevaClave())) {
                usuarioBean.getUsuario().setClave(encrypt(usuarioBean.getNuevaClave()));
            } else {
                warn("Las claves ingresadas no coinciden");
                flag = Boolean.FALSE;
            }
        }

        return flag;
    }

    public void cerrarDialog() {
        closeModalBS("dlgUsuarios");
    }

    private void guardarUsuario() {
        usuarioBean.getUsuario().setEstado(usuarioBean.getEstado());
        usuarioBean.setUsuario((Usuarios) toUpperCaseStrings(usuarioBean.getUsuario()));

        usuarioBean.setUsuario(usuarioService.addUsuarios(usuarioBean.getUsuario()));
        usuarioBean.setListUsuarioRoles(generarListaRolesToGuardar(usuarioBean.getUsuario(), usuarioBean.getListIdRolesByUsuario()));

        usuarioRolesService.addUsuarioRoles(usuarioBean.getListUsuarioRoles());

        usuarioBean.setListUsuarios(usuarioService.getUsuarios());
        info("Guardado exitósamente");
        update("tblUsuarios");
        cerrarDialog();
    }

    public void guardar() {
        try {

            if (!usuarioBean.getIsEdit()) {
                usuarioBean.getUsuario().setClave(encrypt(usuarioBean.getUsuario().getDni()));
            }

            if (verificarNuevaClave()) {

                if (usuarioBean.getUsuario().getId() != null) {
                    guardarUsuario();
//                    if (!comparePropertiesObject(usuarioBean.getUsuario(), usuarioBean.getUsuarioSelected())) {
//                        guardarUsuario();
//                    } else {
//                        info("No se modificó el registro");
//                        cerrarDialog();
//                    }
                } else {
                    guardarUsuario();
                }

            }

        } catch (ConstraintViolationException e) {
            if (e.getSQLException().getMessage().startsWith("Duplicate")) {
                warn("Ya existe el usuario " + usuarioBean.getUsuario().getUsuario());
            } else {
                e.printStackTrace();
            }
        } catch (Exception e) {
            error("" + e.getMessage());
            e.printStackTrace();
        }

    }

    public void handleFileUpload(FileUploadEvent event) {

        try {
            if (event.getFile() != null) {
                usuarioBean.getUsuario().setImagen(IOUtils.toByteArray(event.getFile().getInputstream()));
                info("Imagen cargada exitósamente");
            }

        } catch (IOException ex) {
            error("No se pudo cargar la imagen");
            ex.printStackTrace();
        }

    }

}
