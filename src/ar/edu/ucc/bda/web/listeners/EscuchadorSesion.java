package ar.edu.ucc.bda.web.listeners;

import java.awt.List;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.catalina.Session;
import org.apache.catalina.connector.Request;

import ar.edu.ucc.bda.web.modelo.Usuario;
import ar.edu.ucc.bda.web.utiles.ListaSesion;


@WebListener
public class EscuchadorSesion implements HttpSessionListener {
	 //Lista estática
	
	  
	
	
    public EscuchadorSesion() {
        
    }
    
   

    public void sessionCreated(HttpSessionEvent arg0) {
      System.out.println("Se creo una sesion!!!");
      arg0.getSession().setAttribute("estilo", "blanco");
      System.out.println( "estilo: "+arg0.getSession().getAttribute("estilo"));
      
     // System.out.println(ls);
    }

    public void sessionDestroyed(HttpSessionEvent arg0) {
       
    }
	
}
