package ar.edu.ucc.bda.web.utiles;

 import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

 public class Email {
 
// partes del mail declaradas como privadas
 private String user, pass, destino, subject, mensaje;
 //objeto Propierties donde pondremos los parametros del servidor
 private Properties props;

 public Email(String vuser, String vpass, String vdestino, String vsubject) {
 user = vuser;
 pass = vpass;
 destino = vdestino;
 subject = vsubject;

    StringBuilder link = new StringBuilder();
	link.append("<form action=\"http://localhost:8080/dbaf/activar?mail=florenciabonansea@gmail.com\" method=\"post\"> <div name=\"mail\"  id=\"mail\" style=\"display:none\"  value=\"florenciabonansea@gmail.com\">");
	link.append("florenciabonansea@gmail.com");
	link.append("</div>  <input type=\"submit\" value=\"Activar\" /></form>");
	
	mensaje =link.toString();
	
	 props = new Properties();
 }
 //metodo para poner las parametros necesarios
 private void setProps() {
 // el host de correo, en nuestro caso gmail
 props.put("mail.smtp.host","smtp.gmail.com");
// props.setProperty("mail.smtp.host", "smtp.gmail.com");
 props.setProperty("mail.smtp.starttls.enable", "true");
 //el puerto que vamos a usar
 props.setProperty("mail.smtp.port", "587");
 //el usuario
 props.setProperty("mail.smtp.user", user);
 //le indicamos que es necesario autentificarse
 props.setProperty("mail.smtp.auth", "true");
 }
 
public String send() {
	setProps();
 String error;
 try {
 //objeto session - setearemos los parametros.
 Session session = Session.getDefaultInstance(props);
//mensaje dentro del mail
 MimeMessage message = new MimeMessage(session);
 //remitente
 message.setFrom(new InternetAddress(user));
 //destinatario
 message.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
 //el asunto
 message.setSubject(subject);
 // y por ultimo le seteamos el cuerpo del mensaje, hay varios modos, en nuestro caso utilizamos
 //html pero podria ser en texto plano
 message.setContent(mensaje, "text/html");
 
// con estas 4 linias creamos la conexion y enviamos el mensaje
 Transport t = session.getTransport("smtp");
 t.connect(user, pass);
 t.sendMessage(message, message.getAllRecipients());
 t.close();

 
error = "";
 } catch (Exception e) {
 error = e.toString();
 }
 return error;
 }
 
public String getDestino() {
 return destino;
 }
 
public void setDestino(String destino) {
 this.destino = destino;
 }
 
public String getMensaje() {
 return mensaje;
 }
 
public void setMensaje(String mensaje) {
 this.mensaje = mensaje;
 }
 
public String getPass() {
 return pass;
 }
 
public void setPass(String pass) {
 this.pass = pass;
 }
 
public Properties getProps() {
 return props;
 }
 
public void setProps(Properties props) {
 this.props = props;
 }
 
public String getSubject() {
 return subject;
 }
 
public void setSubject(String subject) {
 this.subject = subject;
 }
 
public String getUser() {
 return user;
 }
 
public void setUser(String user) {
 this.user = user;
 }
 


public static void main(String[] args) {

	//"extlpphhgovsmnqh"
	
	StringBuilder link = new StringBuilder();
	link.append("<form action=\"http://localhost:8080/dbaf/activar?mail=florenciabonansea@gmail.com\" method=\"post\"> <div name=\"mail\"  id=\"mail\" style=\"display:none\"  value=\"florenciabonansea@gmail.com\">");
	link.append("florenciabonansea@gmail.com");
	link.append("</div>  <input type=\"submit\" value=\"Activar\" /></form>");
	

	Email e = new Email("florenciabonansea@gmail.com", "extlpphhgovsmnqh", "florenciabonansea@gmail.com", "prueba");
	//e.setProps();
	String error =e.send();
	if(error!="")
		System.out.println("error "+error);
	 
}



}
 
 
 