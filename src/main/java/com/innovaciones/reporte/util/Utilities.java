/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.innovaciones.reporte.util;

import com.innovaciones.reporte.beans.ReporteBean;
import com.innovaciones.reporte.model.CabeceraCatalogoReporte;
import com.innovaciones.reporte.model.DetalleCatalogoReporte;
import com.innovaciones.reporte.model.Modelo;
import com.innovaciones.reporte.model.ReporteMantenimiento;
import com.innovaciones.reporte.model.RepuestoModelo;
import com.innovaciones.reporte.model.UsuarioRoles;
import com.innovaciones.reporte.model.Usuarios;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.context.RequestContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import javax.mail.MessagingException;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MalformedObjectNameException;
import javax.management.ReflectionException;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author pisama
 */
public class Utilities implements Serializable {

    private static JasperPrint jasperPrint;
    private final static String SMTP_HOST = "smtp.live.com";
    private final static int SMTP_PORT = 587;

    private final static String MAIL_USERNAME = "correoinnovaciones@outlook.com";
    private final static String MAIL_PASSWORD = "contrase;a";

    private static String getFullPath(String reportPath) {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath(reportPath);
    }

    private static void fillReport(String reportPath, Map<String, Object> parameters, List data) throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(data);
        jasperPrint = JasperFillManager.fillReport(reportPath, parameters, beanCollectionDataSource);

    }

    public static void downloadAsPDF(String reportPath, Map<String, Object> parameters) throws JRException, IOException {

        String fullReportPath = getFullPath(reportPath);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

        fillReport(fullReportPath, parameters);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

        FacesContext.getCurrentInstance().responseComplete();

    }

    private static void fillReport(String reportPath, Map<String, Object> parameters) throws JRException {
        jasperPrint = JasperFillManager.fillReport(reportPath, parameters, new JREmptyDataSource());

    }

    public static void downloadAsPDF(String reportPath, Map<String, Object> parameters, List data) throws JRException, IOException {

        String fullReportPath = getFullPath(reportPath);
        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

        fillReport(fullReportPath, parameters, data);

        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        FacesContext.getCurrentInstance().responseComplete();
    }

    public static List<DetalleCatalogoReporte> listCorrectivoOtros() {

        List<DetalleCatalogoReporte> list = new ArrayList<DetalleCatalogoReporte>();
        DetalleCatalogoReporte catalogoReporte;
        for (int i = 0; i < 6; i++) {
            catalogoReporte = new DetalleCatalogoReporte();
            catalogoReporte.setId(i);
            catalogoReporte.setDescripcion("");
            catalogoReporte.setEstado(true);
            catalogoReporte.setIdCabecera(new CabeceraCatalogoReporte(10, Enums.MANTENIMIENTO_OTROS.getValue()));
            list.add(catalogoReporte);
        }
        return list;
    }

    public static String codigoTipoRepuesto(String code) {

        return code.replace("|", "");
    }

    public static void warn(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", msg));

    }

    public static void info(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", msg));

    }

    public static void error(String msg) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg));

    }

    public static String fomatearFecha(Date fecha) {

        Locale id = new Locale("in", "ID");
        String pattern = "EEEE, dd 'de' MMM 'del' yyyy";
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, id);
        DateFormatSymbols dfs = new DateFormatSymbols(id);
        String[] days = dfs.getWeekdays();
        String newDays[] = {"", "Domingo", "Lunes", "Mártes", "Miércoles", "Jueves", "Viernes", "Sábado"};
        dfs.setWeekdays(newDays);
        String months[] = {"", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        dfs.setShortMonths(months);
        sdf = new SimpleDateFormat(pattern, dfs);
        return sdf.format(today);
    }

    public static String fomatearHora(Date fecha) {
        String result;
        DateFormat df = new SimpleDateFormat("HH:mm");
        result = df.format(fecha);
        return result;
    }

    public static String fomatearFechaCorto(Date fecha) {
        String result;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        result = df.format(fecha);
        return result;
    }

    public static String UUID_CODE() {

        return String.valueOf(UUID.randomUUID()).substring(0, 6);
    }

    public static String getParameter(String parameter) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String reportId = paramMap.get(parameter);
        return reportId;

    }

    public static void redirectToPage(String url) throws IOException {

        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + url);

    }

    public static void redireccionar(String pagina) {
        try {

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(ec.getRequestContextPath() + "/" + pagina);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void setSessionText(String parameter, String mensaje) {
        setValueInSession(parameter, mensaje);
    }

    public static String getSessionText(String parameter) {
        FacesContext fc = FacesContext.getCurrentInstance();
        String mensaje = "";
        if (fc.getExternalContext().getSessionMap().get(parameter) != null) {
            return fc.getExternalContext().getSessionMap().get(parameter).toString();
        }
        return mensaje;
    }

    public static void killSession(String parameter) {
        setValueInSession(parameter, null);
    }

    public static void setIdReporteSession(Integer idReporte) {
        setValueInSession(Enums.PARAMETRO_ID_REPORTE.getValue(), idReporte);
    }

    public static Integer getIdReporteSession() {
        Integer mensaje = 0;

        if (getValueInSession(Enums.PARAMETRO_ID_REPORTE.getValue()) != null) {
            return Integer.parseInt(getValueInSession(Enums.PARAMETRO_ID_REPORTE.getValue()).toString());
        }

        return mensaje;
    }

    public static void killIdReporteSession() {
        setValueInSession(Enums.PARAMETRO_ID_REPORTE.getValue(), null);
    }

    public static void setMensajeSession(String mensaje) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("mensaje", mensaje);
    }

    public static String getMensajeSession() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String mensaje = "";
        if (fc.getExternalContext().getSessionMap().get("mensaje") != null) {
            return fc.getExternalContext().getSessionMap().get("mensaje").toString();
        }

        return mensaje;
    }

    public static void showSessionMensaje() {

        if (!getMensajeSession().isEmpty()) {
            info(getMensajeSession());
            killSession("mensaje");
        }
    }

    public static void openDialog(String dialog) {
        RequestContext.getCurrentInstance().execute("PF('" + dialog + "').show();");
    }

    public static void closeDialog(String dialog) {
        RequestContext.getCurrentInstance().execute("PF('" + dialog + "').hide();");
    }

    public static void update(String componente) {
        RequestContext.getCurrentInstance().update(componente);
    }

    public static void updateMany(String componentes) {
        String[] vec = componentes.split(",");
        for (int i = 0; i < vec.length; i++) {
            RequestContext.getCurrentInstance().update(vec[i].trim());
        }
    }

    public static Boolean isInteger(String integer) {
        try {
            Integer.parseInt(integer);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void clearTableFilters(String wgId) {
        RequestContext.getCurrentInstance().execute("PF('" + wgId + "').clearFilters()");
    }

    public static String numeroFactura(Integer integer) {
        return String.format("%07d", integer);
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }

    private static String hashString(String message, String algorithm) throws Exception {

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

            return convertByteArrayToHexString(hashedBytes);
        } catch (Exception ex) {
            throw new Exception("Could not generate hash from String" + ex);
        }
    }

    public static String encrypt(String value) {
        try {
            return hashString(value, "MD5");
        } catch (Exception e) {
            return value;
        }

    }

    public static List<EstadosEnum> cargarEstadosBoolean() {
        List<EstadosEnum> estados = new ArrayList<EstadosEnum>();
        estados.add(EstadosEnum.ACTIVO);
        estados.add(EstadosEnum.INACTIVO);
        return estados;
    }

    public static List<AsignacionReparacionEnum> cargarPrioridadAsignaciones() {
        List<AsignacionReparacionEnum> prioridades = new ArrayList<AsignacionReparacionEnum>();
        prioridades.add(AsignacionReparacionEnum.PRIORIDAD_DEFAULT);
        prioridades.add(AsignacionReparacionEnum.PRIORIDAD_BAJA);
        prioridades.add(AsignacionReparacionEnum.PRIORIDAD_NORMAL);
        prioridades.add(AsignacionReparacionEnum.PRIORIDAD_ALTA);
        return prioridades;
    }

    public static List<Enums> cargarTiposReporte() {
        List<Enums> tiposReporte = new ArrayList<Enums>();

        tiposReporte.add(Enums.TIPO_REPORTE_DIAGNOSTICO);
        tiposReporte.add(Enums.TIPO_REPORTE_REPARACION);
        tiposReporte.add(Enums.TIPO_REPORTE_CONTADORES);
        tiposReporte.add(Enums.INSTALACION_NUEVA);
        tiposReporte.add(Enums.INSTALACION_TEMPORAL);

        return tiposReporte;
    }

    public static Date getDateServer() {
        Date date = new Date();
        return date;
    }

    public static void sendPost(String parameter) throws ServletException, IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher(parameter);
        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
        FacesContext.getCurrentInstance().responseComplete();
    }

    public static String contrasenia(String nombre, String apellido) {

        if (!nombre.isEmpty()) {
            if (nombre.trim().split("\\s+").length > 1) {
                nombre = nombre.split("\\s+")[0].toLowerCase().trim();
            } else {
                nombre = nombre.toLowerCase().trim();
            }
        }

        if (!apellido.isEmpty()) {
            if (apellido.trim().split("\\s+").length > 1) {
                apellido = apellido.split("\\s+")[0].toLowerCase().trim();
            } else {
                apellido = apellido.toLowerCase().trim();
            }
        }

        return encrypt(nombre + "." + apellido);

    }

    public static Boolean habilitarGestionReporte(Usuarios usuarios) {
        Boolean Estado = Boolean.FALSE;
        for (UsuarioRoles usuarioRoles : usuarios.getUsuarioRolesList()) {
            if (usuarioRoles.getIdRol().getRol().equals(Enums.ROLE_POSTVENTAS.getValue())
                    || usuarioRoles.getIdRol().getRol().equals(Enums.ROLE_ADMIN.getValue())) {
                Estado = Boolean.TRUE;
                break;
            }
        }
        return Estado;
    }

    public static Boolean habilitarMenuCorto(Usuarios usuario) {
        Boolean Estado = Boolean.FALSE;
        for (UsuarioRoles usuarioRoles : usuario.getUsuarioRolesList()) {
            if (usuarioRoles.getIdRol().getRol().equals(Enums.ROLE_POSTVENTAS.getValue())
                    || usuarioRoles.getIdRol().getRol().equals(Enums.ROLE_ADMIN.getValue())
                    || usuarioRoles.getIdRol().getRol().equals(Enums.ROLE_TECNICO.getValue())) {
                Estado = Boolean.TRUE;
                break;
            }
        }
        return Estado;
    }

    public static String getAppUrl() throws MBeanException, AttributeNotFoundException, InstanceNotFoundException, ReflectionException, MalformedObjectNameException, UnknownHostException {

//        String port = ManagementFactory.getPlatformMBeanServer().getAttribute(new ObjectName("jboss.as:socket-binding-group=standard-sockets,socket-binding=http"), "port").toString();
//        String ip = ManagementFactory.getPlatformMBeanServer().getAttribute(new ObjectName("jboss.as:interface=public"), "inet-address").toString();
//        String appName = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
//
//        return "http://" + ip + ":" + port + "" + appName;
        String ipAddress = "";

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getScheme(); //http - https

            if (request.getLocalAddr().equals("127.0.0.1")) {
                ipAddress += "://" + InetAddress.getLocalHost().getHostAddress();
            } else {
                ipAddress += "://" + request.getLocalAddr();
            }

            ipAddress += ":" + request.getServerPort();
            ipAddress += "/" + request.getContextPath();

            //  ipAddress += "/" + request.
//            System.out.println("com.innovaciones.reporte.util.Utilities.getAppUrl(): " + request.getRemoteAddr());
//            System.out.println("com.innovaciones.reporte.util.Utilities.getAppUrl(): " + request.getRemoteUser());
//            System.out.println("com.innovaciones.reporte.util.Utilities.getAppUrl(): " + request.getRemoteAddr());
//            System.out.println("com.innovaciones.reporte.util.Utilities.getAppUrl(): " + InetAddress.getLocalHost().getHostAddress());
        }
        return ipAddress;

    }

    private static String headerEmail(String cid) {
        StringBuilder header = new StringBuilder();

        header.append("	<table border='0' style='width:100%; margin-top: 0px; font-family:Century Gothic,arial,sans-serif;'>");
        header.append("<tr >");
        header.append("<td style='width:150px;'>");
        header.append("<a href='https://www.innovaciones.ec' target='_blank'>");
        header.append("<img style='width:150px; height:45px' alt='Embedded Image' src='cid:");

        header.append(cid);
        header.append("'/>");
        header.append("</a>");
        header.append("</td>");
        header.append("<td/>");
        header.append("<td/>");
        header.append("<td style='vertical-align: bottom;'>");
        header.append("<a style='color:#2B6CA3; text-decoration: none;' href='https://www.innovaciones.ec' target='_blank'>");
        header.append("<span style='font-size:25; font-weight:bold;'>Innovaciones Tecnol&oacute;gicas Imaginarium S.A.</span><br/>");
        header.append("</a>");
        header.append("</td>");
        header.append("</tr>");
        header.append("</table>");
        header.append("<hr style='color: #2B6CA3'/>");

        return header.toString();
    }

    private static MimeBodyPart putLogoInsideEmail(String cid) throws IOException, MessagingException {
        MimeBodyPart imagePart = new MimeBodyPart();
        String pathLogo = FacesContext.getCurrentInstance()
                .getExternalContext().getRealPath("resources/images/InnovacionesLogo.png");

        System.out.println("com.innovaciones.reporte.util.Utilities.enviarMail(): " + pathLogo);

        imagePart.attachFile(pathLogo);
        imagePart.setContentID("<" + cid + ">");
        imagePart.setDisposition(MimeBodyPart.INLINE);
        return imagePart;
    }

    private static String getTitleEmail(String title) {
        StringBuilder tittle = new StringBuilder();

        tittle.append("<p style='font-weight: bold; font-size: 16px; font-family:Century Gothic,arial,sans-serif;'>");
        tittle.append(title);
        tittle.append("</p> <br/>");

        return tittle.toString();
    }

    private static String getHeaderEmail(String cid, String title) {
        StringBuilder result = new StringBuilder();
        result.append(headerEmail(cid));
        result.append(getTitleEmail(title));
        return result.toString();
    }

    private static String getContentAsignacionEmail(List<Object> listContent, String tipoAsignacion) throws ParseException {
        StringBuilder content = new StringBuilder();

        // String[] listDatos = datos.split(";");
        content.append("<p style='font-size: 14px; font-family:Century Gothic,arial,sans-serif;'>El requerimiento de ");
        content.append(tipoAsignacion);
        content.append(" para su equipo fu&eacute; registrado con la siguiente informaci&oacute;n:</p>");
        content.append("<br/>");

        content.append("<table border='0'  style='font-size:13px ;font-family:Century Gothic,arial,sans-serif;'>");

        content.append("<tr>");
        content.append("<td>");
        content.append("<span style='font-weight: bold;'>C&oacute;digo asignaci&oacute;n:&nbsp;</span>");
        content.append("</td>");
        content.append("<td>");
        content.append("<span style='font-weight: bold;'>");
        content.append(listContent.get(0));
        content.append("</span>");
        content.append("</td>");
        content.append("</tr>");

        content.append("<tr>");
        content.append("<td>");
        content.append("<span style='font-weight: bold;'>Cliente:&nbsp;</span>");
        content.append("</td>");
        content.append("<td>");
        content.append("<span>");
        content.append(listContent.get(1));
        content.append("</span>");
        content.append("</td>");
        content.append("</tr>");

        content.append("<tr>");
        content.append("<td>");
        content.append("<span style='font-weight: bold;'>Equipo:&nbsp;</span>");
        content.append("</td>");
        content.append("<td>");
        content.append("<span>");
        content.append(listContent.get(2));
        content.append("</span>");
        content.append("</td>");
        content.append("</tr>");

        content.append("<tr>");
        content.append("<td>");
        content.append("<span style='font-weight: bold;'>Serie del equipo:&nbsp;</span>");
        content.append("</td>");
        content.append("<td>");
        content.append("<span>");
        content.append(listContent.get(3));
        content.append("</span>");
        content.append("</td>");
        content.append("</tr>");

        content.append("<tr>");
        content.append("<td>");
        content.append("<span style='font-weight: bold;'>T&eacute;cnico asignado:&nbsp;</span>");
        content.append("</td>");
        content.append("<td>");
        content.append("<span>");
        content.append(listContent.get(4));
        content.append("</span>");
        content.append("</td>");
        content.append("</tr>");

        content.append("<tr>");
        content.append("<td>");
        content.append("<span style='font-weight: bold;'>Fecha ");
        content.append(tipoAsignacion);
        content.append("&nbsp;:</span>");
        content.append("</td>");
        content.append("<td>");
        content.append("<span>");
        content.append(fomatearFechaCorto((Date) listContent.get(5)));
        content.append("</span>");
        content.append("</td>");
        content.append("</tr>");

        content.append("<tr>");
        content.append("<td>");
        content.append("<span style='font-weight: bold;'>Hora ");
        content.append(tipoAsignacion);
        content.append("&nbsp;:</span>");
        content.append("</td>");
        content.append("</td>");
        content.append("<td>");

        content.append("<span>");
        content.append(fomatearHora((Date) listContent.get(6)));
        content.append("</span>");
        content.append("<br/>");
        content.append("</td>");
        content.append("</tr>");

        content.append("<tr>");
        content.append("<td>");
        content.append("<span style='font-weight: bold;'>Estado requerimiento:&nbsp;</span>");
        content.append("</td>");
        content.append("<td>");
        content.append(listContent.get(7));
        content.append("<br/>");
        content.append("</td>");
        content.append("</tr>");

        content.append("</table>");
        content.append("<br/>");

        return content.toString();
    }

    private static String footerMail() {
        StringBuilder footer = new StringBuilder();

        footer.append("<p style='color: gray;font-size: 13px; font-family:Century Gothic,arial,sans-serif;'>");
        footer.append("Cada vez que se procese su requerimiento se le notificar&aacute; mediante este mismo medio.</p>");
        footer.append("<br/><hr style='color: gray'/>");
        footer.append("<table border='0' style='width:100%;font-family:Century Gothic,arial,sans-serif;'>");
        footer.append("<tr >");
        footer.append("<td style='vertical-align: bottom;'>");
        footer.append("<div style='color: gray; font-size: 12px'>");
        footer.append("<span>Innovaciones Tecnol&oacute;gicas Imaginarium S.A</span><br/>");
        footer.append("<span>Av. Atahualpaa Oe3-109 y Pje. Orbigny<span><br/>");
        footer.append("<span>PBX: (02) 5 101-101 &#8226; (02) 2 541-600<span><br/>");
        footer.append("<a href='mailto:web@innovaciones.ec?subject=Proformar%20Equipo'>web@innovaciones.ec</a></span><br/>");
        footer.append("<span>Quito - Ecuador<span><br/>");
        footer.append("</div>");
        footer.append("</td>");
        footer.append("</tr>");
        footer.append("</table>");

        return footer.toString();
    }

    private static InternetAddress[] convertRecipientsAddress(String recipients) {
        String[] recipientList = recipients.split(";");
        InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
        try {
            int counter = 0;
            for (String recipient : recipientList) {

                if (!recipient.trim().isEmpty()) {
                    System.out.println("com.innovaciones.reporte.util.Utilities.convertRecipientsAddress(): " + recipient);
                    recipientAddress[counter] = new InternetAddress(recipient.trim());
                }
                counter++;
            }
        } catch (Exception e) {
            System.out.println("No se pudo convertir los destinatarios de emails: " + e.getMessage());
        }
        return recipientAddress;
    }

    public static boolean enviarMailPdf(String reportPath, Map<String, Object> parameters, String recipient, String subject) {

        try {
            String fullReportPath = getFullPath(reportPath);
            fillReport(fullReportPath, parameters);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            return enviarMail(baos, recipient, subject);

        } catch (Exception e) {
            System.out.println("Error enviarMailPdf: " + e.getMessage());
            return false;
        }

        /*JRException, IOException */
    }

    public static Boolean enviarMail(String recipient, String body, String subject) {
        try {
            Message message = new MimeMessage(getSessionEmail());
            message.setFrom(new InternetAddress(MAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, convertRecipientsAddress(recipient));
            message.setSubject(subject);

            //body = headerEmail("") + body;
            // ContentID is used by both parts            
            String cid = String.valueOf((new Date()).getTime());
            StringBuilder textEmail = new StringBuilder();

            textEmail.append(headerEmail(cid));
            textEmail.append(body);
            textEmail.append(footerMail());

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(textEmail.toString(), "UTF-8", "html");

            MimeMultipart contentMultipart = new MimeMultipart("related");
            contentMultipart.addBodyPart(textPart);
            contentMultipart.addBodyPart(putLogoInsideEmail(cid));
            message.setContent(contentMultipart);
            Transport.send(message);
            return true;
        } catch (Exception e) {
            System.out.println("Error enviarMail: " + e.getMessage());
            return false;

        }

    }

    private static Session getSessionEmail() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", SMTP_HOST);
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL_USERNAME, MAIL_PASSWORD);
            }
        });
        return session;
    }

    public static Boolean enviarMail(String recipient, String title, List<Object> dataMail, String body, String subject, String tipoNotififacion) {
        try {

            String cid = String.valueOf((new Date()).getTime());

            MimeMessage mimeMessage = new MimeMessage(getSessionEmail());
            mimeMessage.setFrom(new InternetAddress(MAIL_USERNAME));
            mimeMessage.setRecipients(Message.RecipientType.TO, convertRecipientsAddress(recipient));
            mimeMessage.setSubject(subject);

            // ContentID is used by both parts            
            StringBuilder textEmail = new StringBuilder();
            textEmail.append(getHeaderEmail(cid, title));

            if (tipoNotififacion.equals(Enums.REPORTE.getValue())) {
                textEmail.append(getContentAsignacionEmail(dataMail, "reparaci&oacute;n"));
            } else {
                if (tipoNotififacion.equals(Enums.INSTALACION.getValue())) {
                    textEmail.append(getContentAsignacionEmail(dataMail, "instalaci&oacute;n"));
                }
            }

            textEmail.append(body);

            textEmail.append(footerMail());

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(textEmail.toString(), "UTF-8", "html");

            MimeMultipart contentMultipart = new MimeMultipart("related");
            contentMultipart.addBodyPart(textPart);
            contentMultipart.addBodyPart(putLogoInsideEmail(cid));
            mimeMessage.setContent(contentMultipart);

            Transport.send(mimeMessage);
            return true;
        } catch (Exception e) {
            System.out.println("Error enviarMail: ");
            e.printStackTrace();
            return false;

        }

    }

    public static boolean enviarMail(ByteArrayOutputStream archivo, String recipient, String subject) {

        try {

            byte[] bytes = archivo.toByteArray();

            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("reporte.pdf");

            InternetAddress iaSender = new InternetAddress(MAIL_USERNAME);

            MimeMessage mimeMessage = new MimeMessage(getSessionEmail());
            mimeMessage.setSender(iaSender);
            mimeMessage.setSubject(subject);
            mimeMessage.setRecipients(Message.RecipientType.TO, convertRecipientsAddress(recipient));
            //InternetAddress iaRecipient = new InternetAddress(recipient);

            String cid = String.valueOf((new Date()).getTime());

            StringBuilder textEmail = new StringBuilder();
            textEmail.append(getHeaderEmail(cid, "Nuevo Reporte Creado"));
            textEmail.append(footerMail());

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(textEmail.toString(), "UTF-8", "html");

            MimeMultipart contentMultipart = new MimeMultipart("related");

            contentMultipart.addBodyPart(textPart);
            contentMultipart.addBodyPart(putLogoInsideEmail(cid));
            contentMultipart.addBodyPart(pdfBodyPart);
            mimeMessage.setContent(contentMultipart);

            Transport.send(mimeMessage);

            return true;
        } catch (Exception ex) {
            System.out.println("Error enviarMailPdf.archivo.close.Exception(): " + ex.getMessage());
            return false;
        } finally {
            //clean off
            if (null != archivo) {
                try {
                    archivo.close();
                    archivo = null;
                } catch (Exception ex) {
                    System.out.println("Error enviarMailPdf.archivo.close.finally(): " + ex.getMessage());
                    return false;
                }
            }
        }

    }

    public static void setValueInSession(String key, Object value) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().put(key, value);
    }

    public static Object getValueInSession(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getExternalContext().getSessionMap().get(key);
    }

    public static void openModalBS(String modal) {
        try {

            RequestContext.getCurrentInstance()
                    .execute("$('#" + modal + "').modal('show');");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeModalBS(String modal) {
        try {
            RequestContext.getCurrentInstance()
                    .execute("$('#" + modal + "').modal('hide');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T> Object toUpperCaseStrings(Object object) {

        try {

            Class<?> objectClass = object.getClass();
            java.lang.reflect.Field[] declaredFields = objectClass.getDeclaredFields();

            for (java.lang.reflect.Field field : declaredFields) {

                if (field.getType().getSimpleName().equalsIgnoreCase("String")) {

                    field.setAccessible(true);
                    Object objectValue = field.get(object);

                    if (!field.getName().toLowerCase().contains("firma") && !field.getName().equalsIgnoreCase("usuario")
                            && !field.getName().equalsIgnoreCase("clave") && !field.getName().toLowerCase().contains("mail")
                            && !field.getName().toLowerCase().contains("url")) {

                        if (objectValue != null) {
                            String value = String.valueOf(field.get(object));
                            value = value.trim().toUpperCase();
                            field.set(object, value);
                        }
                    } else {
                        if (field.getName().toLowerCase().contains("mail")) {
                            if (objectValue != null) {
                                String value = String.valueOf(field.get(object));
                                value = value.trim().toLowerCase();
                                field.set(object, value);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    public static Boolean comparePropertiesObject(Object object1, Object object2) {
        Boolean result = Boolean.TRUE;
        try {

            Class<?> objectClass = object1.getClass();
            java.lang.reflect.Field[] declaredFields = objectClass.getDeclaredFields();

            for (java.lang.reflect.Field field : declaredFields) {

                if (!field.getType().getSimpleName().equalsIgnoreCase("List")) {

                    if (!result) {
                        break;
                    }

                    field.setAccessible(true);

                    Object objectValue1 = field.get(object1);
                    Object objectValue2 = field.get(object2);

                    if (objectValue1 != null && objectValue2 != null) {
                        result = objectValue1.equals(objectValue2);
                        //System.out.println("Property().!null: " + field.getName() + " -> " + objectValue1 + " == " + objectValue2 + " " + (objectValue1.equals(objectValue2)));
                        continue;
                    }

                    if (objectValue1 != null && objectValue2 == null) {
                        result = objectValue1.equals(objectValue2);
                        //System.out.println("Property()1!null: " + field.getName() + " -> " + objectValue1 + " == " + objectValue2 + " " + (objectValue1.equals(objectValue2)));
                        continue;
                    }

                    if (objectValue1 == null && objectValue2 != null) {
                        result = objectValue2.equals(objectValue1);
                        // System.out.println("Property()2!null: " + field.getName() + " -> " + objectValue1 + " == " + objectValue2 + " " + (objectValue2.equals(objectValue1)));
                        continue;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void resetDataTable(String idDataTable) {
        DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(idDataTable);
        dataTable.reset();
    }

    public static String obtenerClassByPrioridad(Integer idPrioridad) {
        String result = "";

        if (AsignacionReparacionEnum.PRIORIDAD_DEFAULT.getValue().equals(idPrioridad)) {
            result = "label label-default";
        }
        if (AsignacionReparacionEnum.PRIORIDAD_BAJA.getValue().equals(idPrioridad)) {
            result = "label label-success";
        }
        if (AsignacionReparacionEnum.PRIORIDAD_NORMAL.getValue().equals(idPrioridad)) {
            result = "label label-warning";
        }
        if (AsignacionReparacionEnum.PRIORIDAD_ALTA.getValue().equals(idPrioridad)) {
            result = "label label-danger";
        }
        return result;
    }

    public static String obtenerColorByPrioridad(Integer idPrioridad) {
        String result = "";

        if (AsignacionReparacionEnum.PRIORIDAD_DEFAULT.getValue().equals(idPrioridad)) {
            result = "#777";
        }
        if (AsignacionReparacionEnum.PRIORIDAD_BAJA.getValue().equals(idPrioridad)) {
            result = "#5cb85c";
        }
        if (AsignacionReparacionEnum.PRIORIDAD_NORMAL.getValue().equals(idPrioridad)) {
            result = "#f0ad4e";
        }
        if (AsignacionReparacionEnum.PRIORIDAD_ALTA.getValue().equals(idPrioridad)) {
            result = "#d9534f";
        }
        return result;
    }

    public static String obtenerNameByPrioridad(Integer idPrioridad) {
        String result = "";

        if (AsignacionReparacionEnum.PRIORIDAD_DEFAULT.getValue().equals(idPrioridad)) {
            result = AsignacionReparacionEnum.PRIORIDAD_DEFAULT.getPropertyName();
        }
        if (AsignacionReparacionEnum.PRIORIDAD_BAJA.getValue().equals(idPrioridad)) {
            result = AsignacionReparacionEnum.PRIORIDAD_BAJA.getPropertyName();
        }
        if (AsignacionReparacionEnum.PRIORIDAD_NORMAL.getValue().equals(idPrioridad)) {
            result = AsignacionReparacionEnum.PRIORIDAD_NORMAL.getPropertyName();
        }
        if (AsignacionReparacionEnum.PRIORIDAD_ALTA.getValue().equals(idPrioridad)) {
            result = AsignacionReparacionEnum.PRIORIDAD_ALTA.getPropertyName();
        }
        return result;
    }

    public static List<DetalleCatalogoReporte> listaDeRadioButton(DetalleCatalogoReporte catalogoReporte, List<DetalleCatalogoReporte> detalleCatalogoReportes) {
        List<DetalleCatalogoReporte> list = new ArrayList<DetalleCatalogoReporte>();
        DetalleCatalogoReporte detalleCatalogoReporte;

        for (DetalleCatalogoReporte object : detalleCatalogoReportes) {
            detalleCatalogoReporte = new DetalleCatalogoReporte();
            detalleCatalogoReporte = object;
            if (catalogoReporte.getDescripcion().equals(object.getDescripcion())) {
                detalleCatalogoReporte.setTipoRepuesto(object.getTipoRepuesto());
                detalleCatalogoReporte.setEstado(Boolean.TRUE);
            }
            list.add(detalleCatalogoReporte);
        }
        return list;
    }

    public static List<DetalleCatalogoReporte> listaDeTextField(DetalleCatalogoReporte catalogoReporte, List<DetalleCatalogoReporte> detalleCatalogoReportes, Modelo modelo) {
        List<DetalleCatalogoReporte> list = new ArrayList<DetalleCatalogoReporte>();
        DetalleCatalogoReporte detalleCatalogoReporte;
        for (DetalleCatalogoReporte object : detalleCatalogoReportes) {
            detalleCatalogoReporte = new DetalleCatalogoReporte();
            detalleCatalogoReporte = object;
            if (catalogoReporte.getId().equals(object.getId())) {
                detalleCatalogoReporte.setCodigoRepuesto(object.getCodigoRepuesto());
                detalleCatalogoReporte.setEstado(Boolean.TRUE);
            }
            if (modelo.getId() != null) {
                detalleCatalogoReporte.setIdModelo(modelo.getId());
            }
            list.add(detalleCatalogoReporte);
        }
        return list;
    }

    public static List<DetalleCatalogoReporte> listaDeReset(DetalleCatalogoReporte catalogoReporte, List<DetalleCatalogoReporte> detalleCatalogoReportes) {
        List<DetalleCatalogoReporte> list = new ArrayList<DetalleCatalogoReporte>();
        DetalleCatalogoReporte detalleCatalogoReporte;
        for (DetalleCatalogoReporte object : detalleCatalogoReportes) {
            detalleCatalogoReporte = new DetalleCatalogoReporte();
            detalleCatalogoReporte = object;
            if (catalogoReporte.getId().equals(object.getId())) {
                detalleCatalogoReporte.setCodigoRepuesto(null);
                detalleCatalogoReporte.setEstado(Boolean.FALSE);
                detalleCatalogoReporte.setSeleccion(Boolean.FALSE);// ADDED
            }
            list.add(detalleCatalogoReporte);
        }
        return list;
    }

    public static List<DetalleCatalogoReporte> repuestosCodigoActualizado(List<DetalleCatalogoReporte> list,
            RepuestoModelo seleccion) {
        List<DetalleCatalogoReporte> catalogoReportes = new ArrayList<DetalleCatalogoReporte>();

        DetalleCatalogoReporte eliminar = new DetalleCatalogoReporte(), agregar = new DetalleCatalogoReporte();

        /*  System.out.println("SELECCION  " + seleccion);
        System.out.println("*****************************");
        for (DetalleCatalogoReporte catalogoReporte : list) {
            System.out.println("1 =" + catalogoReporte);

        }
        System.out.println("----------------------------");
         */
        for (DetalleCatalogoReporte repuesto : list) {
            if (repuesto.getDescripcion().equals(seleccion.getIdDetalleCatalogoReporte().getDescripcion())) {
                eliminar = repuesto;
            }
            catalogoReportes.add(repuesto);
        }

        agregar.setTipoRepuesto(eliminar.getTipoRepuesto());
        catalogoReportes.remove(eliminar);
        /* System.out.println("DELETE " + eliminar);

        System.out.println("****************************");
        for (DetalleCatalogoReporte repuesto : catalogoReportes) {
            System.out.println("2 " + repuesto);
        }
        System.out.println("****************************");*/
        agregar.setId(seleccion.getIdDetalleCatalogoReporte().getId());
        agregar.setDescripcion(seleccion.getIdDetalleCatalogoReporte().getDescripcion());
        agregar.setIdCabecera(new CabeceraCatalogoReporte());
        agregar.setIdCabecera(seleccion.getIdDetalleCatalogoReporte().getIdCabecera());
        agregar.setCodigoRepuesto(seleccion.getCodigoRepuesto());
        agregar.setStock(seleccion.getStock());
        agregar.setOrden(eliminar.getOrden());
        agregar.setEstado(seleccion.getEstado() == EstadosEnum.ACTIVO.getValue());
        agregar.setIdRepuestoModelo(seleccion.getId());

        catalogoReportes.add(agregar);
        //  System.out.println("ADDED_ " + agregar);

        ordenar(catalogoReportes, DetalleCatalogoReporte.sortByOrden);

        /*  System.out.println("******************");
        for (DetalleCatalogoReporte catalogoReporte : catalogoReportes) {
            System.out.println("3 " + catalogoReporte);
        }
         */
        return catalogoReportes;

    }

    public static List<DetalleCatalogoReporte> noId(List<DetalleCatalogoReporte> listIn) {
        List<DetalleCatalogoReporte> listOut = new ArrayList<DetalleCatalogoReporte>();
        DetalleCatalogoReporte dcr;
        for (DetalleCatalogoReporte detalleCatalogoReporte : listIn) {
            dcr = new DetalleCatalogoReporte();
            dcr = detalleCatalogoReporte;
            dcr.setId(null);
            listOut.add(dcr);
        }
        return listOut;
    }

    public static List<DetalleCatalogoReporte> llenarRepuestosCorrectivos(List<DetalleCatalogoReporte> repuestos, ReporteBean reporteBean) {
        DetalleCatalogoReporte detalleCatalogo;
        List<DetalleCatalogoReporte> list = new ArrayList<DetalleCatalogoReporte>();
        for (DetalleCatalogoReporte catalogoReporte : repuestos) {

            detalleCatalogo = new DetalleCatalogoReporte();
            detalleCatalogo = catalogoReporte;
            for (ReporteMantenimiento mantenimiento : reporteBean.getProductoClienteReporte().getReporteMantenimientoList()) {

                if (mantenimiento.getIdRepuestoModelo().getIdDetalleCatalogoReporte().getDescripcion().equals(catalogoReporte.getDescripcion())
                        && mantenimiento.getEstado() == EstadosEnum.ACTIVO.getValue()) {

                    detalleCatalogo.setId(mantenimiento.getIdRepuestoModelo().getIdDetalleCatalogoReporte().getId());
                    detalleCatalogo.setTipoRepuesto(mantenimiento.getTipoRepuesto());
                    detalleCatalogo.setCodigoRepuesto(mantenimiento.getCodigoRepuesto());
                    detalleCatalogo.setStock(mantenimiento.getIdRepuestoModelo().getStock());
                    detalleCatalogo.setIdRepuestoModelo(mantenimiento.getIdRepuestoModelo().getId());
                    detalleCatalogo.setSeleccion(true);
                    detalleCatalogo.setPorcentaje(mantenimiento.getPorcentaje());

                } else {
                    detalleCatalogo.setSeleccion(false);
                }

                detalleCatalogo.setEstado(mantenimiento.getEstado() == EstadosEnum.ACTIVO.getValue());

            }
            list.add(detalleCatalogo);
        }
        return list;
    }

    public static List<DetalleCatalogoReporte> llenarRepuestosPreventivos(List<DetalleCatalogoReporte> repuestos, ReporteBean reporteBean) {
      
        List<DetalleCatalogoReporte> list = new ArrayList<DetalleCatalogoReporte>();
        DetalleCatalogoReporte procesamiento;
        for (DetalleCatalogoReporte catalogoReporte : repuestos) {
            procesamiento = new DetalleCatalogoReporte();
            procesamiento = catalogoReporte;
            for (ReporteMantenimiento mantenimiento : reporteBean.getProductoClienteReporte().getReporteMantenimientoList()) {
    
                if (mantenimiento.getIdRepuestoModelo().getIdDetalleCatalogoReporte().getId() == catalogoReporte.getId()) {
                    procesamiento.setSeleccion(true);
                    break;
                }
                procesamiento.setIdModelo(mantenimiento.getIdRepuestoModelo().getIdModelo().getId());
            }
            list.add(procesamiento);
        }
        return list;
    }

    public static List<DetalleCatalogoReporte> llenarRepuestosPreguntas(List<DetalleCatalogoReporte> repuestos, ReporteBean reporteBean) {
        List<DetalleCatalogoReporte> list = new ArrayList<DetalleCatalogoReporte>();
        DetalleCatalogoReporte procesamiento;
        for (DetalleCatalogoReporte catalogoReporte : repuestos) {
            procesamiento = new DetalleCatalogoReporte();
            procesamiento = catalogoReporte;
            for (ReporteMantenimiento mantenimiento : reporteBean.getProductoClienteReporte().getReporteMantenimientoList()) {
                if (mantenimiento.getIdRepuestoModelo().getId() == catalogoReporte.getIdRepuestoModelo()) {
                    procesamiento.setSeleccion(true);
                }
                procesamiento.setIdModelo(mantenimiento.getIdRepuestoModelo().getIdModelo().getId());
               
            }
            list.add(procesamiento);
        }
        return list;
    }

    public static List<DetalleCatalogoReporte> repuestoModelo(List<RepuestoModelo> repuestoModelos) {
        List<DetalleCatalogoReporte> list = new ArrayList<DetalleCatalogoReporte>();
        DetalleCatalogoReporte detalle;
        for (RepuestoModelo repuestoModelo : repuestoModelos) {
            detalle = new DetalleCatalogoReporte();
            detalle.setId(repuestoModelo.getIdDetalleCatalogoReporte().getId());
            detalle.setIdRepuestoModelo(repuestoModelo.getId());
            detalle.setEstado(repuestoModelo.getEstado() == EstadosEnum.ACTIVO.getValue());
            detalle.setIdCabecera(new CabeceraCatalogoReporte());
            detalle.setIdCabecera(repuestoModelo.getIdDetalleCatalogoReporte().getIdCabecera());
            detalle.setDescripcion(repuestoModelo.getIdDetalleCatalogoReporte().getDescripcion());
            detalle.setIdModelo(repuestoModelo.getIdModelo().getId());
            list.add(detalle);
        }

        return list;
    }

    public static List<DetalleCatalogoReporte> llenarRepuestosOtros(List<DetalleCatalogoReporte> repuestos, ReporteBean reporteBean) {

        List<DetalleCatalogoReporte> listOtro = new ArrayList<DetalleCatalogoReporte>();
        DetalleCatalogoReporte otro;
        for (ReporteMantenimiento mantenimiento : reporteBean.getProductoClienteReporte().getReporteMantenimientoList()) {

            if (mantenimiento.getIdRepuestoModelo().getIdDetalleCatalogoReporte().getIdCabecera().getCodigo().equals(Enums.MANTENIMIENTO_OTROS.getValue())
                    && mantenimiento.getEstado() == EstadosEnum.ACTIVO.getValue()) {
                otro = new DetalleCatalogoReporte();
                otro.setId(mantenimiento.getId());
                otro.setCodigoRepuesto(mantenimiento.getCodigoRepuesto());
                otro.setTipoRepuesto(mantenimiento.getTipoRepuesto());
                otro.setDescripcion(mantenimiento.getIdRepuestoModelo().getIdDetalleCatalogoReporte().getDescripcion());
                otro.setEstado(mantenimiento.getEstado().equals(EstadosEnum.ACTIVO.getValue()));
                otro.setIdCabecera(new CabeceraCatalogoReporte(10, Enums.MANTENIMIENTO_OTROS.getValue()));
                otro.setIdRepuestoModelo(mantenimiento.getIdRepuestoModelo().getId());
                otro.setIdModelo(mantenimiento.getIdRepuestoModelo().getIdModelo().getId());
                listOtro.add(otro);

            }

        }

        int tamanio = listOtro.size();
        List<DetalleCatalogoReporte> otros = listCorrectivoOtros();
        reporteBean.setListCorrectivoOtros(new ArrayList<DetalleCatalogoReporte>());

        List<DetalleCatalogoReporte> listaEliminar = new ArrayList<DetalleCatalogoReporte>();

        int c = 0;
        for (DetalleCatalogoReporte catalogoReporte : otros) {

            if (c < tamanio) {
                listaEliminar.add(catalogoReporte);
            }
            c++;
            reporteBean.getListCorrectivoOtros().add(catalogoReporte);
        }

        otros.removeAll(listaEliminar);
        listOtro.addAll(otros);

        return listOtro;
    }

    public static <T> void ordenar(List<T> list, Comparator<? super T> c) {
        Collections.sort(list, c);
    }

    public static String getCurrentPage() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest servletRequest = (HttpServletRequest) ctx.getExternalContext().getRequest();
        return servletRequest.getRequestURI();
    }

}
