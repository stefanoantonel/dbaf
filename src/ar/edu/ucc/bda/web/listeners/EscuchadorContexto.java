package ar.edu.ucc.bda.web.listeners;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import ar.edu.ucc.bda.web.modelo.Usuario;
import ar.edu.ucc.bda.web.modelo.dao.Coneccion;
import ar.edu.ucc.bda.web.utiles.Constantes;

// a nivel de aplicacion , cuando le doy de baja a tomcat
@WebListener
public class EscuchadorContexto implements ServletContextListener {
	
	//public static ArrayList<Usuario> lista=new ArrayList();
	public static ArrayList<Usuario> lista=new ArrayList(); 
	
	public EscuchadorContexto() {
 
    }

    public void contextInitialized(ServletContextEvent sce) {
   
    	DataSource fuenteDatos = null;
    	try {
			
			ServletContext sc= sce.getServletContext();
			Context ctx =new InitialContext();
				fuenteDatos = (DataSource) ctx
						// si o si hay q poner  java:comp/env/ --> aca estan las variables "GLOBALES" (una forma de decir)
						.lookup("java:comp/env/jdbc/practico");
				Connection cn = fuenteDatos.getConnection();
				
				Coneccion c = new Coneccion(cn);
				sc.setAttribute(Constantes.NOMBRE_CONEXION,cn);
				
				//System.out.println("conecxion exitosa");
				
    	} catch ( NamingException | SQLException e){
    		e.printStackTrace();
    		//excepcion : cuando un metodo emite una exp
    		throw new RuntimeException();
			
			//despachar(mensaje2HTML(mensaje),request,response);
			//request.setAttribute("msj", "Problemas con la BD");
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/agregarCliente.jsp");
			
			//rd.forward(request, response);
		}
    	
    	
    	
    	
    	sce.getServletContext().setAttribute("sesiones", lista);
    	
    }

    public void contextDestroyed(ServletContextEvent arg0) {

    }
	
}
