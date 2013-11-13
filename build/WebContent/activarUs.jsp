<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd"> 
<%@ page import = "java.text.*" %> 
<%@ page import="java.util.*, javax.mail.*, javax.mail.internet.*, javax.activation.*, javax.mail.PasswordAuthentication.*, javax.mail.Authenticator.*" %> 

<% 
            String Enviar = request.getParameter("enviar") == null ? "0" : request.getParameter("enviar"); 
%> 

<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>JSP Page</title> 
    </head> 

    <% 
                System.out.println("Enviar : " + Enviar); 
                if (Enviar.equals("1")) { 

                    String servidor = request.getParameter("srv"); 
                    String de = request.getParameter("de"); 
                    String para = request.getParameter("para"); 
                    String asunto = request.getParameter("asunto"); 
                    String contenido = request.getParameter("contenido"); 
                    String auth = request.getParameter("auth"); 
                    String usuario = request.getParameter("usuario"); 
                    String contrasena = request.getParameter("contrasena"); 
                    String puerto = request.getParameter("puerto"); 

                    MimeMultipart multipart = new MimeMultipart(); 

                    System.out.println("Para : " + para + " \nDe: " + de + " \nAsunto: " + asunto + " \nContenido: " + contenido + " \nServidor:" + servidor); 

                    try { 
                        Properties properties = new Properties(); 
                        properties.put("mail.smtp.host", servidor); 
                        properties.put("mail.smtp.starttls.enable", "false");// 
                        properties.put("mail.smtp.port", puerto); 
                        properties.put("mail.smtp.user", usuario); 
                        properties.put("mail.smtp.mail.sender", para); 
                        if (auth.equals("si")) { 
                            properties.put("mail.smtp.auth", "true"); 
                        } 

                        Session sess = Session.getDefaultInstance(properties, null); 
                        /* 1 */ 
                        //Session sess = Session.getDefaultInstance(properties, new PasswordAuthentication(usuario, contrasena)); 
                        //sess.setDebug(true); 

                        MimeMessage msg = new MimeMessage(sess); 
                        msg.setFrom(new InternetAddress(de, "E-mail por JSP")); 
                        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(para)); 
                        //msg.setRecipients(Message.RecipientType.CC, ccAddress); 
                        //msg.setRecipients(Message.RecipientType.BCC, bccAddress); 
                        msg.setSubject(asunto); 
                        msg.setSentDate(new Date()); 
                        // BODY 
                        MimeBodyPart mbp = new MimeBodyPart(); 
                        mbp.setText(contenido.toString()); 

                        multipart.addBodyPart(mbp); 

                        msg.setContent(multipart); 

                        //Transport.send(msg); 

                        try { 
                            Transport mta = sess.getTransport("smtp"); 
                            /* 2 */ 
                            mta.connect("smtp.dominio.com", "unCorreo@dominio.com", "unPassword"); //  
                            //mta.connect(); 
                            try { 
                                mta.send(msg); 
                            } catch (SendFailedException ex) { 
                                System.out.println("Error al enviar 3 :  " + ex.toString()); 
                            } 
                        } catch (Exception ex) { 
                            System.out.println("Error al enviar 2 :" + ex.toString()); 
                        } 
                    } catch (Exception e) { 
                        System.out.println("Error al enviar 1 : " + e); 
                    } 
                } 
    %> 



    <body> 
        <h1>Envio de correo!</h1> 
        <br> 
        <form id="mail" name='mail' method="post" action=""> 
            <table> 
                <tr> 
                    <td>Servidor Mail:</td><td><input type="text" value="" name="srv"/></td> 
                </tr> 
                <tr> 
                    <td>Para:</td><td><input type="text" value="" name="para"/></td> 
                </tr> 
                <tr> 
                    <td>De:</td><td><input type="text" value="" name="de"/></td> 
                </tr> 
                <tr> 
                    <td>Asunto:</td><td><input type="text" value="Prueba de sistema" name="asunto"/></td> 
                </tr> 
                <tr> 
                    <td>Contenido:</td><td><input type="text" value="Contenido de prueba!" name="contenido"/></td> 
                </tr> 
                <tr> 
                    <td colspan="2">Otras configuraciones</td> 
                </tr> 
                <tr> 
                    <td>Autentificación:</td> 
                    <td> 
                        <select name="auth"> 
                            <option value="si">Si</option> 
                            <option value="no">No</option> 
                        </select> 
                    </td> 
                </tr> 
                <tr> 
                    <td>Usuario:</td><td><input type="text" value="" name="usuario"/></td> 
                </tr> 
                <tr> 
                    <td>Contraseña:</td><td><input type="text" value="" name="contrasena"/></td> 
                </tr> 
                <tr> 
                    <td>Puerto:</td><td><input type="text" value="25" name="puerto"/></td> 
                </tr> 
                <tr align="center"> 
                    <td colspan="2"> 
                        <input type='hidden' id="enviar" name='enviar' value="<%=Enviar%>"> 
                        <input type="button" value="Enviar" onclick="document.getElementById('enviar').value=1;document.mail.submit();"> 
                    </td> 
                </tr> 
            </table> 
        </form> 
    </body> 
</html>  