/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.util;

import com.innovaciones.reporte.beans.ReporteBean;
import com.innovaciones.reporte.model.DetalleCatalogoReporte;
import com.innovaciones.reporte.model.TipoVisita;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Fernando
 */
public class ReporteTecnico extends Utilities implements Serializable {

    /**
     * Creates a new instance of ReporteTecnicoBean
     */
    private Map<String, Object> parameters;
    private final static String IMAGEN_EMBEBIDA_FORMAT = "data:image/png;base64,";
    private final static String REPORTE_TECNICO_PATH = "/reports/reporte_tecnico.jasper";
    private final static String REPORTE_INSTALACION_NUEVA_PATH = "/reports/instalacion_nueva.jasper";
    private final static String REPORTE_INSTALACION_TEMPORAL_PATH = "/reports/instalacion_temporal.jasper";
    private final static String SELECCIONAR = "X";

    private final static String CAMBIADO = "C";
    private final static String SOLICITAR = "S";

    private final static String DG_VISITA_POR_GARANTIA = "Vista por Garantía";
    private final static String DG_EQUIPOS_BAJO_CONTARTO = "Equipo bajo Contrato";
    private final static String DG_VISITA_DE_CORTESIA = "Visita de Cortesía";
    private final static String DG_CAPACITACION_MANEJO = "Capacitación / Manejo";
    private final static String DG_ARRENDAMIENTO = "Arrendamiento";
    private final static String DG_POR_FACTURAR = "Por facturar";

    private final static String MP_UNIDAD_TRANSFERENCIA = "Unidad de Transferencia";
    private final static String MP_REGISTRO = "Registro";
    private final static String MP_SENSORES_PROCESAMIENTO = "Sensores";
    private final static String MP_CORONA_TRANSFERENCIA = "Corona de Transferencia";
    private final static String MP_SECCION_PROCESAMIENTO = "Sección de Procesamiento";

    private final static String MP_PRESS_ROLLER = "Press Roller";
    private final static String MP_HEAT_ROLLER = "Heat Roller";
    private final static String MP_GUIAS = "Guías";
    private final static String MP_RODILLOS_ARRASTRE = "Rodillos de Arrastre";
    private final static String MP_SENSORES_FIJACION = "Sensores";
    private final static String MP_PINON_ACOPLE = "Piñón de Acople";

    private final static String MP_VIDRIOS = "Vidrios";
    private final static String MP_CUBIERTAS = "Cubiertas";
    private final static String MP_ALIMENTADOR_ORIGINALES = "Alimentador de Originales";
    private final static String MP_BANDEJA_PAPEL = "Bandejas de Papel";
    private final static String MP_BY_PASS = "By Pass (Manual)";
    private final static String MP_LUBRICACION_GENERAL = "Lubricación General";

    private final static String MC_TONER_K = "Toner K";
    private final static String MC_TONER_CMY = "Toner C,M,Y";
    private final static String MC_TONER_C = "Toner C";
    private final static String MC_TONER_M = "Toner M";
    private final static String MC_TONER_Y = "Toner Y";
    private final static String TANQUE_DESECHO = "Tanque de Desechos";

    private final static String MC_UNIDAD_IMAGEN_K = "Unidad de Imagen K";
    private final static String MC_CILINDRO = "Cilindro";
    private final static String MC_BANDA_TRANSFERENCIA = "Banda Transferencia";

    private final static String MC_UNIDAD_IMAGEN_C = "Unidad Imagen C";
    private final static String MC_UNIDAD_IMAGEN_M = "Unidad Imagen M";
    private final static String MC_UNIDAD_IMAGEN_Y = "Unidad Imagen Y";

    private final static String MC_UNIDAD_FUSION = "Unidad de Fusión";
    private final static String MC_PRESS_ROLLER = "Press Roller";
    private final static String MC_HEAT_ROLLER = "Heat Roller";
    private final static String MC_UNIDAD_REVELADO = "Unidad Revelado";
    private final static String MC_REVELADOR = "Revelador";

    private final static String MC_PICKUP_ROLLER = "Pick up Roller";
    private final static String MC_FEED_ROLLER = "Feed Roller";
    private final static String MC_SEPARATION_ROLLER = "Separation Roller";
    private final static String MC_UNIDAD_LASER = "Unidad Láser";
    private final static String MC_REGULADOR_VOLTAJE = "Regulador de Voltaje";

    public ReporteTecnico() {
    }

    public void enviarMailReporte(Map<String, Object> parametros, String recipient, String body, String subject) {

        try {
            if (!recipient.isEmpty()) {

                enviarMailPdf(REPORTE_TECNICO_PATH, parametros, recipient, subject);
            }
        } catch (Exception ex) {

            System.out.println("ERROR " + ex.getMessage());
        }

    }

    public boolean enviarMailReporte(ReporteBean reporteBean, String recipient, String subject) {

        try {
            if (!recipient.isEmpty()) {

                Map<String, Object> parametros = loadParametersReporte(reporteBean);
                return enviarMailPdf(REPORTE_TECNICO_PATH, parametros, recipient, subject);
            }
        } catch (Exception ex) {
            System.out.println("ERROR " + ex.getMessage());
            return false;
        }
        return false;

    }

    public static Map<String, Object> loadParametersReporte(ReporteBean reporteBean) {
        Map<String, Object> parametros = new HashMap();

        try {

            parametros.put("codigo", reporteBean.getUsuarios().getCodigo());
            parametros.put("fecha", fomatearFecha(reporteBean.getReporte().getFecha()));

            //DATOS GENERALES
            parametros.put("cliente", reporteBean.getCliente().getCliente());
            parametros.put("ruc", reporteBean.getCliente().getRuc());

            parametros.put("atencion", reporteBean.getProductoClienteReporte().getAtencion());
            parametros.put("telefono", reporteBean.getCliente().getTelefono());
            parametros.put("telefono2", reporteBean.getCliente().getTelefono2());

            parametros.put("direccion", reporteBean.getCliente().getDireccion());
            parametros.put("factura", reporteBean.getReporte().getFactura());
            parametros.put("referencia", reporteBean.getReporte().getReferencia());

            parametros.put("departamento", reporteBean.getProductoClienteReporte().getDepartamento());
            parametros.put("ciudad", reporteBean.getProductoClienteReporte().getCiudad());
            parametros.put("email", reporteBean.getCliente().getEmail());

            //  parametros.put("codigo_tecnico", reporteBean.getUsuarios().getCodigo());
            parametros.put("numero_reporte", "N° " + numeroFactura(reporteBean.getProductoClienteReporte().getIdReporte().getId()));

            parametros.put("direccion_equipo", reporteBean.getProductoClienteReporte().getDireccionEquipo());
            parametros.put("telefono_equipo", reporteBean.getProductoClienteReporte().getTelefonoEquipo());

            parametros.put("mail_equipo", reporteBean.getProductoClienteReporte().getCorreoEquipo());

            //FIRMA
            String image64Cliente = reporteBean.getReporte().getFirmaClienteBase64();

            String image64Tecnico = reporteBean.getUsuarios().getFirmaBase64();

            if (image64Cliente != null && image64Tecnico != null && !image64Cliente.isEmpty() && !image64Tecnico.isEmpty()) {
                image64Tecnico = image64Tecnico.replace(IMAGEN_EMBEBIDA_FORMAT, "").trim();
                image64Cliente = image64Cliente.replace(IMAGEN_EMBEBIDA_FORMAT, "").trim();

                parametros.put("firma_tecnico", image64Tecnico);
                parametros.put("firma_cliente", image64Cliente);

                System.out.println("LAS FIRMAS SON:::" + image64Tecnico.length() + " y " + image64Cliente.length());

            }

            //Asignacion de visitas en Datos Generales
            for (TipoVisita tipoVisita : reporteBean.getListTipoVisitas()) {
                tipoVisita.setDescripcion(tipoVisita.getDescripcion().trim());

                if (tipoVisita.getId().equals(reporteBean.getIdTipoVisita())) {

                    if (tipoVisita.getDescripcion().equalsIgnoreCase(DG_VISITA_POR_GARANTIA)) {
                        parametros.put("visita_garantia", SELECCIONAR);
                    }
                    if (tipoVisita.getDescripcion().equalsIgnoreCase(DG_EQUIPOS_BAJO_CONTARTO)) {
                        parametros.put("equipos_bajo_contrato", SELECCIONAR);
                    }
                    if (tipoVisita.getDescripcion().equalsIgnoreCase(DG_VISITA_DE_CORTESIA)) {
                        parametros.put("visita_cortesia", SELECCIONAR);
                    }
                    if (tipoVisita.getDescripcion().equalsIgnoreCase(DG_CAPACITACION_MANEJO)) {
                        parametros.put("capacitacion_manejo", SELECCIONAR);
                    }
                    if (tipoVisita.getDescripcion().equalsIgnoreCase(DG_ARRENDAMIENTO)) {
                        parametros.put("arrendamiento", SELECCIONAR);
                    }
                    if (tipoVisita.getDescripcion().equalsIgnoreCase(DG_POR_FACTURAR)) {
                        parametros.put("por_facturar", SELECCIONAR);
                    }

                    break;
                }
            }

            //DATOS DEL EQUIPO
            parametros.put("equipo", reporteBean.getProducto().getEquipo());
            parametros.put("marca", reporteBean.getProducto().getIdModelo().getIdMarca().getMarca());
            parametros.put("modelo", reporteBean.getProducto().getIdModelo().getModelo());
            parametros.put("serie", reporteBean.getProductoCliente().getSerie());

            parametros.put("ip", reporteBean.getProductoClienteReporte().getIpEquipo());
            parametros.put("firmware", reporteBean.getProducto().getVersionFirmware());
            parametros.put("empty1", reporteBean.getProducto().getCampo1());
            parametros.put("empty2", reporteBean.getProducto().getCampo2());

            //DATOS DE CONTADORES
            if (reporteBean.getProductoDetalleReporte().getContadorTotalAnterior() != null) {
                parametros.put("contador_total_anterior", reporteBean.getProductoDetalleReporte().getContadorTotalAnterior());
            }

            if (reporteBean.getProductoDetalleReporte().getContadorColorAnterior() != null) {
                parametros.put("contador_color_anterior", reporteBean.getProductoDetalleReporte().getContadorColorAnterior());
            }

            if (reporteBean.getProductoDetalleReporte().getContadorBnAnterior() != null) {
                parametros.put("contador_bn_anterior", reporteBean.getProductoDetalleReporte().getContadorBnAnterior());
            }

            if (reporteBean.getProductoDetalleReporte().getContadorTotalActual() != null) {
                parametros.put("contador_total_actual", reporteBean.getProductoDetalleReporte().getContadorTotalActual());
            }

            if (reporteBean.getProductoDetalleReporte().getContadorColorActual() != null) {
                parametros.put("contador_color_actual", reporteBean.getProductoDetalleReporte().getContadorColorActual());
            }

            if (reporteBean.getProductoDetalleReporte().getContadorBnActual() != null) {
                parametros.put("contador_bn_actual", reporteBean.getProductoDetalleReporte().getContadorBnActual());
            }

            if (reporteBean.getProductoDetalleReporte().getContadorTotalImpReal() != null) {
                parametros.put("contador_total_real", reporteBean.getProductoDetalleReporte().getContadorTotalImpReal());

            }

            if (reporteBean.getProductoDetalleReporte().getContadorColorImpReal() != null) {
                parametros.put("contador_color_real", reporteBean.getProductoDetalleReporte().getContadorColorImpReal());

            }

            if (reporteBean.getProductoDetalleReporte().getContadorBnImpReal() != null) {
                parametros.put("contador_bn_real", reporteBean.getProductoDetalleReporte().getContadorBnImpReal());

            }

            if (reporteBean.getProductoDetalleReporte().getMantenimiento() != null) {
                parametros.put("mantenimiento", reporteBean.getProductoDetalleReporte().getMantenimiento());

            }

            if (reporteBean.getProductoDetalleReporte().getOtros() != null) {
                parametros.put("otros", reporteBean.getProductoDetalleReporte().getOtros());

            }

            if (reporteBean.getProductoDetalleReporte().getServicioFacturar() != null) {
                parametros.put("servicio_facturar", reporteBean.getProductoDetalleReporte().getServicioFacturar().doubleValue());
            }

            if (reporteBean.getProductoDetalleReporte().getServicioFacturarEstado()) {
                parametros.put("servicio_facturar_estado", SELECCIONAR);
            }

            parametros.put("sintomas", reporteBean.getReporte().getSintomasEquipo());

            parametros.put("observacion", reporteBean.getReporte().getObservacionMantenimiento());
            parametros.put("observaciones_recomendaciones", reporteBean.getReporte().getObservacionesRecomendaciones());

            parametros.put("hora_inicio", fomatearHora(reporteBean.getReporte().getHoraInicio()));
            parametros.put("hora_finalizacion", fomatearHora(reporteBean.getReporte().getHoraFin()));
            parametros.put("nombre_tecnico", reporteBean.getUsuarios().getNombreCompleto());
            parametros.put("nombre_cliente", reporteBean.getReporte().getNombreCliente());

            if (reporteBean.getEstadoActualMantenimiento() != null && reporteBean.getEstadoActualMantenimiento().equals(Enums.CORRECTIVO.getPropertyName())) {
                reporteBean.getReporte().setMantenimiento(Enums.CORRECTIVO.getValue());
            }
            if (reporteBean.getEstadoActualMantenimiento() != null && reporteBean.getEstadoActualMantenimiento().equals(Enums.PREVENTIVO.getPropertyName())) {
                reporteBean.getReporte().setMantenimiento(Enums.PREVENTIVO.getValue());
            }

            if (reporteBean.getEstadoActualMantenimiento() != null && reporteBean.getReporte().getMantenimiento().equals(Enums.CORRECTIVO.getPropertyName())) {
                reporteBean.getReporte().setMantenimiento(Enums.CORRECTIVO.getValue());
            }
            if (reporteBean.getEstadoActualMantenimiento() != null && reporteBean.getReporte().getMantenimiento().equals(Enums.PREVENTIVO.getPropertyName())) {
                reporteBean.getReporte().setMantenimiento(Enums.PREVENTIVO.getValue());
            }

            System.out.println("INICIO ASIGNACION PREVENTIVO procesamineto");
            //MANTENIMIENTO PREVENTIVO
            for (DetalleCatalogoReporte detalleCatalogoReporte : reporteBean.getListProcesamiento()) {
                detalleCatalogoReporte.setDescripcion(detalleCatalogoReporte.getDescripcion().trim());
                System.out.println("DETALLE PROCESAMINETO " + detalleCatalogoReporte);
                if (detalleCatalogoReporte.getSeleccion() != null && detalleCatalogoReporte.getSeleccion()) {

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_UNIDAD_TRANSFERENCIA)) {
                        parametros.put("unidad_transferencia", SELECCIONAR);
                    }
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_REGISTRO)) {
                        parametros.put("registro", SELECCIONAR);
                    }
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_SENSORES_PROCESAMIENTO)) {
                        parametros.put("sensores_procesamiento", SELECCIONAR);
                    }

                }
            }

            System.out.println("INICIO ASIGNACION IMAGEN");

            for (DetalleCatalogoReporte detalleCatalogoReporte : reporteBean.getListPreventivoImagen()) {
                detalleCatalogoReporte.setDescripcion(detalleCatalogoReporte.getDescripcion().trim());

                if (detalleCatalogoReporte.getSeleccion() != null && detalleCatalogoReporte.getSeleccion()) {
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_CORONA_TRANSFERENCIA)) {
                        parametros.put("corona_transferencia", SELECCIONAR);
                    }
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_SECCION_PROCESAMIENTO)) {
                        parametros.put("seccion_procesamiento", SELECCIONAR);
                    }
                }
            }

            for (DetalleCatalogoReporte detalleCatalogoReporte : reporteBean.getListPreventivoFijacion()) {
                detalleCatalogoReporte.setDescripcion(detalleCatalogoReporte.getDescripcion().trim());
                if (detalleCatalogoReporte.getSeleccion() != null && detalleCatalogoReporte.getSeleccion()) {
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_PRESS_ROLLER)) {
                        parametros.put("press_roller", SELECCIONAR);
                    }
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_HEAT_ROLLER)) {
                        parametros.put("heat_roller", SELECCIONAR);
                    }
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_GUIAS)) {
                        parametros.put("guias", SELECCIONAR);
                    }
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_RODILLOS_ARRASTRE)) {
                        parametros.put("rodillo_arrastre", SELECCIONAR);
                    }
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_SENSORES_FIJACION)) {
                        parametros.put("sensores_fijacion", SELECCIONAR);
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_PINON_ACOPLE)) {
                        parametros.put("pinon_acople", SELECCIONAR);
                    }

                }
            }

            for (DetalleCatalogoReporte detalleCatalogoReporte : reporteBean.getListPreventivoLimpieza()) {
                detalleCatalogoReporte.setDescripcion(detalleCatalogoReporte.getDescripcion().trim());
                if (detalleCatalogoReporte.getSeleccion() != null && detalleCatalogoReporte.getSeleccion()) {
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_VIDRIOS)) {
                        parametros.put("vidrios", SELECCIONAR);
                    }
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_CUBIERTAS)) {
                        parametros.put("cubiertas", SELECCIONAR);
                    }
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_ALIMENTADOR_ORIGINALES)) {
                        parametros.put("alimentador_originales", SELECCIONAR);
                    }
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_BANDEJA_PAPEL)) {
                        parametros.put("bandeja_papel", SELECCIONAR);
                    }
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_BY_PASS)) {

                        parametros.put("by_pass", SELECCIONAR);
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MP_LUBRICACION_GENERAL)) {
                        parametros.put("lubricacion_general", SELECCIONAR);
                    }
                }
            }

            //MANTENIMIENTO CORRECTIVO
            for (DetalleCatalogoReporte detalleCatalogoReporte : reporteBean.getListCorrectivoSuministros()) {
                detalleCatalogoReporte.setDescripcion(detalleCatalogoReporte.getDescripcion().trim());
                if (detalleCatalogoReporte.getTipoRepuesto() != null && !detalleCatalogoReporte.getTipoRepuesto().equals("")) {

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_TONER_K)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("toner_k_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("toner_k_s", SELECCIONAR);
                        }
                        parametros.put("tonerk_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("toner_k_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_TONER_CMY)) {

                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("toner_cmy_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("toner_cmy_s", SELECCIONAR);
                        }
                        parametros.put("tonercmy_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("toner_cmy_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_TONER_C)) {

                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("toner_c_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("toner_c_s", SELECCIONAR);
                        }
                        parametros.put("tonerc_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("toner_c_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_TONER_M)) {

                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("toner_m_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("toner_m_s", SELECCIONAR);
                        }
                        parametros.put("tonerm_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("toner_m_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_TONER_Y)) {

                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("toner_y_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("toner_y_s", SELECCIONAR);
                        }
                        parametros.put("tonery_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("toner_y_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(TANQUE_DESECHO)) {

                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("tanque_desechos_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("tanque_desechos_s", SELECCIONAR);
                        }
                        parametros.put("tanque_desechos_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("tanque_desechos_code", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                }
            }

            for (DetalleCatalogoReporte detalleCatalogoReporte : reporteBean.getListCorrectivoImagen()) {
                detalleCatalogoReporte.setDescripcion(detalleCatalogoReporte.getDescripcion().trim());
                if (detalleCatalogoReporte.getTipoRepuesto() != null && !detalleCatalogoReporte.getTipoRepuesto().equals("")) {

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_UNIDAD_IMAGEN_K)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("u_imagen_k_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("u_imagen_k_s", SELECCIONAR);
                        }
                        parametros.put("u_imagen_k_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("u_imagen_k_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));

                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_CILINDRO)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("cilindro_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("cilindro_s", SELECCIONAR);
                        }
                        parametros.put("cilindro_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("cilindro_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_BANDA_TRANSFERENCIA)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("banda_transferencia_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("banda_transferencia_s", SELECCIONAR);
                        }
                        parametros.put("banda_trans_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("banda_transferencia_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_UNIDAD_IMAGEN_C)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("u_imagen_c_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("u_imagen_c_s", SELECCIONAR);
                        }
                        parametros.put("u_imagen_c_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("u_imagen_c_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_UNIDAD_IMAGEN_M)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("u_imagen_m_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("u_imagen_m_s", SELECCIONAR);
                        }
                        parametros.put("u_imagen_m_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("u_imagen_m_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_UNIDAD_IMAGEN_Y)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("u_imagen_y_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("u_imagen_y_s", SELECCIONAR);
                        }
                        parametros.put("u_imagen_y_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("u_imagen_y_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                }
            }

            for (DetalleCatalogoReporte detalleCatalogoReporte : reporteBean.getListCorrectivoFijacion()) {
                detalleCatalogoReporte.setDescripcion(detalleCatalogoReporte.getDescripcion().trim());
                if (detalleCatalogoReporte.getTipoRepuesto() != null && !detalleCatalogoReporte.getTipoRepuesto().equals("")) {
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_UNIDAD_FUSION)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("unidad_fusion_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("unidad_fusion_s", SELECCIONAR);
                        }
                        parametros.put("u_fusion_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("unidad_fusion_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_PRESS_ROLLER)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("press_roller_correctivo_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("press_roller_correctivo_s", SELECCIONAR);
                        }
                        parametros.put("press_roller_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("press_roller_correctivo_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_HEAT_ROLLER)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("heat_roller_correctivo_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("heat_roller_correctivo_s", SELECCIONAR);
                        }
                        parametros.put("heat_roller_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("heat_roller_correctivo_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }
                }
            }

            for (DetalleCatalogoReporte detalleCatalogoReporte : reporteBean.getListCorrectivoRevelado()) {
                detalleCatalogoReporte.setDescripcion(detalleCatalogoReporte.getDescripcion().trim());
                if (detalleCatalogoReporte.getTipoRepuesto() != null && !detalleCatalogoReporte.getTipoRepuesto().equals("")) {
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_UNIDAD_REVELADO)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("unidad_revelado_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("unidad_revelado_s", SELECCIONAR);
                        }
                        parametros.put("u_revelado_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("unidad_revelado_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_REVELADOR)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("revelador_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("revelador_s", SELECCIONAR);
                        }
                        parametros.put("revelador_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("revelador_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }
                }
            }

            for (DetalleCatalogoReporte detalleCatalogoReporte : reporteBean.getListCorrectivoAlimentacion()) {
                detalleCatalogoReporte.setDescripcion(detalleCatalogoReporte.getDescripcion().trim());
                if (detalleCatalogoReporte.getTipoRepuesto() != null && !detalleCatalogoReporte.getTipoRepuesto().equals("")) {
                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_PICKUP_ROLLER)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("pickup_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("pickup_s", SELECCIONAR);
                        }
                        parametros.put("pick_uproller_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("pickup_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_FEED_ROLLER)) {

                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("feed_roller_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("feed_roller_s", SELECCIONAR);
                        }
                        parametros.put("feed_roller_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("feed_roller_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_SEPARATION_ROLLER)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("separation_roller_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("separation_roller_s", SELECCIONAR);
                        }
                        parametros.put("sep_roller_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("separation_roller_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_UNIDAD_LASER)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("unidad_laser_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("unidad_laser_s", SELECCIONAR);
                        }
                        parametros.put("u_laser_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("unidad_laser_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }

                    if (detalleCatalogoReporte.getDescripcion().equalsIgnoreCase(MC_REGULADOR_VOLTAJE)) {
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                            parametros.put("regulador_voltaje_c", SELECCIONAR);
                        }
                        if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                            parametros.put("regulador_voltaje_s", SELECCIONAR);
                        }
                        parametros.put("r_voltaje_porc", detalleCatalogoReporte.getPorcentaje() != null ? detalleCatalogoReporte.getPorcentaje().toString().concat(" %") : null);
                        parametros.put("regulador_voltaje_code_rep", codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                    }
                }
            }

            int c = 1;

            for (DetalleCatalogoReporte detalleCatalogoReporte : reporteBean.getListCorrectivoOtros()) {
                detalleCatalogoReporte.setDescripcion(detalleCatalogoReporte.getDescripcion().trim());
                if (detalleCatalogoReporte.getTipoRepuesto() != null
                        && !detalleCatalogoReporte.getTipoRepuesto().equals("")
                        && !detalleCatalogoReporte.getDescripcion().equals("")) {

                    parametros.put("otros_" + c, detalleCatalogoReporte.getDescripcion());
                    if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("C")) {
                        parametros.put("otros_c_" + c, "( " + SELECCIONAR + " )");
                    }
                    if (detalleCatalogoReporte.getTipoRepuesto().equalsIgnoreCase("S")) {
                        parametros.put("otros_s_" + c, "( " + SELECCIONAR + " )");
                    }
                    parametros.put("otros_cod_rep_" + c, codigoTipoRepuesto(detalleCatalogoReporte.getCodigoRepuesto()));
                }
                c++;
            }

        } catch (Exception e) {
            System.out.println("Error cargar Parametros " + e.getMessage());
            e.getStackTrace();
        }

        return parametros;

    }

    public static Map<String, Object> loadParametersInstalacionNueva(ReporteBean reporteBean) {
        Map<String, Object> parametros = new HashMap();

        try {

            parametros.put("codigo", reporteBean.getUsuarios().getCodigo());
            parametros.put("fecha", fomatearFecha(reporteBean.getReporte().getFecha()));

            //DATOS GENERALES
            parametros.put("cliente", reporteBean.getProductoClienteReporte().getIdProductoCliente().getIdCliente().getCliente());
            parametros.put("ruc", reporteBean.getProductoClienteReporte().getIdProductoCliente().getIdCliente().getRuc());

            parametros.put("atencion", reporteBean.getProductoClienteReporte().getAtencion());
            parametros.put("telefono", reporteBean.getProductoClienteReporte().getIdProductoCliente().getIdCliente().getTelefono());
            parametros.put("telefono2", reporteBean.getProductoClienteReporte().getIdProductoCliente().getIdCliente().getTelefono2());

            parametros.put("direccion", reporteBean.getProductoClienteReporte().getIdProductoCliente().getIdCliente().getDireccion());
            parametros.put("factura", reporteBean.getReporte().getFactura());
            parametros.put("referencia", reporteBean.getReporte().getReferencia());

            parametros.put("departamento", reporteBean.getProductoClienteReporte().getDepartamento());
            parametros.put("ciudad", reporteBean.getProductoClienteReporte().getCiudad());
            parametros.put("email", reporteBean.getProductoClienteReporte().getIdProductoCliente().getIdCliente().getEmail());

            parametros.put("numero_reporte", "N° " + numeroFactura(reporteBean.getProductoClienteReporte().getIdReporte().getId()));

            parametros.put("direccion_equipo", reporteBean.getProductoClienteReporte().getDireccionEquipo());
            parametros.put("telefono_equipo", reporteBean.getProductoClienteReporte().getTelefonoEquipo());

            parametros.put("mail_equipo", reporteBean.getProductoClienteReporte().getCorreoEquipo());

            parametros.put("nota", reporteBean.getProductoClienteReporte().getIdDetalleReporteInstalacionNueva().getNota());

            System.out.println(reporteBean.getProductoClienteReporte().getIdProductoCliente().getIdCliente().getEmail() + " , mail clinet   " + reporteBean.getCliente().getEmail());
            System.out.println("NOTA   " + reporteBean.getProductoClienteReporte().getIdDetalleReporteInstalacionNueva().getNota());

            //FIRMA
            String image64Cliente = reporteBean.getReporte().getFirmaClienteBase64();

            String image64Tecnico = reporteBean.getUsuarios().getFirmaBase64();

            if (image64Cliente != null && image64Tecnico != null && !image64Cliente.isEmpty() && !image64Tecnico.isEmpty()) {
                image64Tecnico = image64Tecnico.replace(IMAGEN_EMBEBIDA_FORMAT, "").trim();
                image64Cliente = image64Cliente.replace(IMAGEN_EMBEBIDA_FORMAT, "").trim();

                parametros.put("firma_tecnico", image64Tecnico);
                parametros.put("firma_cliente", image64Cliente);

            }

            //DATOS DEL EQUIPO
            parametros.put("equipo", reporteBean.getProducto().getEquipo());
            parametros.put("marca", reporteBean.getProducto().getIdModelo().getIdMarca().getMarca());
            parametros.put("modelo", reporteBean.getProducto().getIdModelo().getModelo());
            parametros.put("serie", reporteBean.getProductoCliente().getSerie());

            parametros.put("ip", reporteBean.getProductoClienteReporte().getIpEquipo());
            parametros.put("firmware", reporteBean.getProducto().getVersionFirmware());
            parametros.put("empty1", reporteBean.getProducto().getCampo1());
            parametros.put("empty2", reporteBean.getProducto().getCampo2());

            parametros.put("detalle_instalacion", reporteBean.getReporte().getSintomasEquipo());

            parametros.put("fase_neutro", reporteBean.getProductoClienteReporte().getIdDetalleReporteInstalacionNueva().getFaseNeutro());
            parametros.put("fase_tierra", reporteBean.getProductoClienteReporte().getIdDetalleReporteInstalacionNueva().getFaseTierra());
            parametros.put("neutro", reporteBean.getProductoClienteReporte().getIdDetalleReporteInstalacionNueva().getNeutro());

            DetalleCatalogoReporte[] arrayConsideraciones = reporteBean.getListConsideraciones().toArray(new DetalleCatalogoReporte[reporteBean.getListConsideraciones().size()]);
            DetalleCatalogoReporte[] arrayPreguntas = reporteBean.getListPreguntas().toArray(new DetalleCatalogoReporte[reporteBean.getListPreguntas().size()]);

            for (int i = 0; i < arrayConsideraciones.length; i++) {

                parametros.put("consideracion_" + (i + 1), arrayConsideraciones[i].getDescripcion().contains("Watts") ? arrayConsideraciones[i].getDescripcion().replace("Watts", reporteBean.getProductoClienteReporte().getIdDetalleReporteInstalacionNueva().getVoltajePrevencion() + " Watts") : arrayConsideraciones[i].getDescripcion());
            }

            parametros.put("consideracion_observacion", reporteBean.getProductoClienteReporte().getIdDetalleReporteInstalacionNueva().getDescripcionConsideraciones());

            for (int i = 0; i < arrayPreguntas.length; i++) {
                parametros.put("pregunta_" + (i + 1), arrayPreguntas[i].getDescripcion().contains("Operario?") ? arrayPreguntas[i].getDescripcion().replace("Operario?", " Operario?  " + reporteBean.getProductoClienteReporte().getIdDetalleReporteInstalacionNueva().getNombreOperario()) : arrayPreguntas[i].getDescripcion());
            }

            for (int i = 0; i < arrayPreguntas.length; i++) {
                if (arrayPreguntas[i].getSeleccion() != null && arrayPreguntas[i].getSeleccion()) {
                    parametros.put("pregunta_" + (i + 1) + "_check", "X");
                }
            }

            parametros.put("observacion", reporteBean.getReporte().getSintomasEquipo());
            parametros.put("hora_inicio", fomatearHora(reporteBean.getReporte().getHoraInicio()));
            parametros.put("hora_finalizacion", fomatearHora(reporteBean.getReporte().getHoraFin()));
            parametros.put("nombre_tecnico", reporteBean.getUsuarios().getNombreCompleto());
            parametros.put("nombre_cliente", reporteBean.getReporte().getNombreCliente());

        } catch (Exception e) {
            System.out.println("Error cargar Parametros " + e.getMessage());
            e.getStackTrace();
        }

        return parametros;

    }

    public static Map<String, Object> loadParametersInstalacionTemporal(ReporteBean reporteBean) {
        Map<String, Object> parametros = new HashMap();

        try {

            parametros.put("codigo", reporteBean.getUsuarios().getCodigo());
            parametros.put("fecha", fomatearFecha(reporteBean.getReporte().getFecha()));

            //DATOS GENERALES
            parametros.put("cliente", reporteBean.getProductoCliente().getIdCliente().getCliente());
            parametros.put("ruc", reporteBean.getProductoCliente().getIdCliente().getRuc());

            parametros.put("atencion", reporteBean.getProductoClienteReporte().getAtencion());
            parametros.put("telefono", reporteBean.getProductoCliente().getIdCliente().getTelefono());
            parametros.put("telefono2", reporteBean.getProductoCliente().getIdCliente().getTelefono2());

            parametros.put("direccion", reporteBean.getProductoCliente().getIdCliente().getDireccion());
            parametros.put("factura", reporteBean.getReporte().getFactura());
            parametros.put("referencia", reporteBean.getReporte().getReferencia());

            parametros.put("departamento", reporteBean.getProductoClienteReporte().getDepartamento());
            parametros.put("ciudad", reporteBean.getProductoClienteReporte().getCiudad());
            parametros.put("email", reporteBean.getProductoCliente().getIdCliente().getEmail());

            parametros.put("numero_reporte", "N° " + numeroFactura(reporteBean.getProductoClienteReporte().getIdReporte().getId()));

            parametros.put("direccion_equipo", reporteBean.getProductoClienteReporte().getDireccionEquipo());
            parametros.put("telefono_equipo", reporteBean.getProductoClienteReporte().getTelefonoEquipo());

            parametros.put("mail_equipo", reporteBean.getProductoClienteReporte().getCorreoEquipo());

            //FIRMA
            String image64Cliente = reporteBean.getReporte().getFirmaClienteBase64();

            String image64Tecnico = reporteBean.getUsuarios().getFirmaBase64();

            if (image64Cliente != null && image64Tecnico != null && !image64Cliente.isEmpty() && !image64Tecnico.isEmpty()) {
                image64Tecnico = image64Tecnico.replace(IMAGEN_EMBEBIDA_FORMAT, "").trim();
                image64Cliente = image64Cliente.replace(IMAGEN_EMBEBIDA_FORMAT, "").trim();

                parametros.put("firma_tecnico", image64Tecnico);
                parametros.put("firma_cliente", image64Cliente);

                System.out.println("LAS FIRMAS SON:::" + image64Tecnico.length() + " y " + image64Cliente.length());

            }

            //DATOS DEL EQUIPO
            parametros.put("equipo", reporteBean.getProducto().getEquipo());
            parametros.put("marca", reporteBean.getProducto().getIdModelo().getIdMarca().getMarca());
            parametros.put("modelo", reporteBean.getProducto().getIdModelo().getModelo());
            parametros.put("serie", reporteBean.getProductoCliente().getSerie());

            parametros.put("ip", reporteBean.getProductoClienteReporte().getIpEquipo());
            parametros.put("firmware", reporteBean.getProducto().getVersionFirmware());
            parametros.put("empty1", reporteBean.getProducto().getCampo1());
            parametros.put("empty2", reporteBean.getProducto().getCampo2());

            //DATOS DE CONTADORES
            if (reporteBean.getProductoDetalleReporte().getContadorTotalAnterior() != null) {
                parametros.put("contador_total_anterior", reporteBean.getProductoDetalleReporte().getContadorTotalAnterior());
            }
            if (reporteBean.getProductoDetalleReporte().getContadorColorAnterior() != null) {
                parametros.put("contador_color_anterior", reporteBean.getProductoDetalleReporte().getContadorColorAnterior());
            }

            if (reporteBean.getProductoDetalleReporte().getContadorBnAnterior() != null) {
                parametros.put("contador_bn_anterior", reporteBean.getProductoDetalleReporte().getContadorBnAnterior());
            }

            if (reporteBean.getProductoDetalleReporte().getContadorTotalActual() != null) {
                parametros.put("contador_total_actual", reporteBean.getProductoDetalleReporte().getContadorTotalActual());
            }

            if (reporteBean.getProductoDetalleReporte().getContadorColorActual() != null) {
                parametros.put("contador_color_actual", reporteBean.getProductoDetalleReporte().getContadorColorActual());
            }

            if (reporteBean.getProductoDetalleReporte().getContadorBnActual() != null) {
                parametros.put("contador_bn_actual", reporteBean.getProductoDetalleReporte().getContadorBnActual());
            }

            if (reporteBean.getProductoDetalleReporte().getContadorTotalImpReal() != null) {
                parametros.put("contador_total_real", reporteBean.getProductoDetalleReporte().getContadorTotalImpReal());

            }

            if (reporteBean.getProductoDetalleReporte().getContadorColorImpReal() != null) {
                parametros.put("contador_color_real", reporteBean.getProductoDetalleReporte().getContadorColorImpReal());

            }

            if (reporteBean.getProductoDetalleReporte().getContadorBnImpReal() != null) {
                parametros.put("contador_bn_real", reporteBean.getProductoDetalleReporte().getContadorBnImpReal());

            }

            if (reporteBean.getProductoDetalleReporte().getMantenimiento() != null) {
                parametros.put("mantenimiento", reporteBean.getProductoDetalleReporte().getMantenimiento());

            }

            if (reporteBean.getProductoDetalleReporte().getOtros() != null) {
                parametros.put("otros", reporteBean.getProductoDetalleReporte().getOtros());
            }

            parametros.put("observacion", reporteBean.getReporte().getSintomasEquipo());

            System.out.println("sintaomaosssss=  " + reporteBean.getReporte().getSintomasEquipo());

            //DATOS DEL EQUIPO TEMPORAL
            parametros.put("equipo_tmp", reporteBean.getProductoClienteReporte().getIdProductoCliente().getIdProducto().getEquipo());
            parametros.put("marca_tmp", reporteBean.getProductoClienteReporte().getIdProductoCliente().getIdProducto().getIdModelo().getIdMarca().getMarca());
            parametros.put("modelo_tmp", reporteBean.getProductoClienteReporte().getIdProductoCliente().getIdProducto().getIdModelo().getModelo());
            parametros.put("serie_tmp", reporteBean.getProductoClienteReporte().getIdProductoCliente().getSerie());

            parametros.put("ip_tmp", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getIpEquipo());
            parametros.put("firmware_tmp", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getIdProductoCliente().getIdProducto().getVersionFirmware());
            parametros.put("empty1_tmp", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getIdProductoCliente().getIdProducto().getCampo1());
            parametros.put("empty2_tmp", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getIdProductoCliente().getIdProducto().getCampo2());

            parametros.put("contador_total_anterior_tmp", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getContadorTotalActual());
            parametros.put("contador_color_anterior_tmp", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getContadorColorAnterior());
            parametros.put("contador_bn_anterior_tmp", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getContadorBnAnterior());
            parametros.put("contador_total_actual_tmp", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getContadorTotalActual());
            parametros.put("contador_color_actual_tmp", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getContadorColorActual());
            parametros.put("contador_bn_actual_tmp", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getContadorBnActual());
            parametros.put("contador_total_real_tmp", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getContadorTotalImpReal());
            parametros.put("contador_color_real_tmp", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getContadorColorImpReal());
            parametros.put("contador_bn_real_tmp", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getContadorBnImpReal());
            parametros.put("observacion_tmp", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getObservaciones());

            parametros.put("fase_neutro", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getFaseNeutro());
            parametros.put("fase_tierra", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getFaseTierra());
            parametros.put("neutro", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getNeutro());
            parametros.put("observacion_mediciones", reporteBean.getProductoClienteReporte().getIdDetalleReporteTemporal().getObservacionMedicion());

            parametros.put("observaciones_recomendaciones", reporteBean.getReporte().getObservacionesRecomendaciones());
            parametros.put("hora_inicio", fomatearHora(reporteBean.getReporte().getHoraInicio()));
            parametros.put("hora_finalizacion", fomatearHora(reporteBean.getReporte().getHoraFin()));
            parametros.put("nombre_tecnico", reporteBean.getUsuarios().getNombreCompleto());
            parametros.put("nombre_cliente", reporteBean.getReporte().getNombreCliente());

        } catch (Exception e) {
            System.out.println("Error cargar Parametros " + e.getMessage());
            e.getStackTrace();
        }

        return parametros;

    }

    public void descargarReporte(ReporteBean reporteBean, String tipo) {

        try {

            System.out.println("TIPO   " + tipo);
            parameters = new HashMap();

            if (tipo.equals(Enums.REPORTE.getValue())) {
                parameters = loadParametersReporte(reporteBean);
                downloadAsPDF(REPORTE_TECNICO_PATH, parameters);
            }

            if (tipo.equals(Enums.INSTALACION_NUEVA.getValue())) {
                parameters = loadParametersInstalacionNueva(reporteBean);
                downloadAsPDF(REPORTE_INSTALACION_NUEVA_PATH, parameters);
            }

            if (tipo.equals(Enums.INSTALACION_TEMPORAL.getValue())) {
                parameters = loadParametersInstalacionTemporal(reporteBean);
                downloadAsPDF(REPORTE_INSTALACION_TEMPORAL_PATH, parameters);
            }

        } catch (Exception e) {
            System.out.println("ERROR descargarReporte " + e.getMessage());
            e.getStackTrace();
        }
    }

}
