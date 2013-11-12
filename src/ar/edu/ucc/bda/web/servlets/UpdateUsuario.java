package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ucc.bda.web.modelo.PersistenciaException;
import ar.edu.ucc.bda.web.modelo.Usuario;
import ar.edu.ucc.bda.web.modelo.dao.UsuarioDAO;
import ar.edu.ucc.bda.web.utiles.Constantes;

/**
 * Servlet implementation class UpdateUsuario
 */
@WebServlet("/updateUsuario")
public class UpdateUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateUsuario() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre=(String) request.getSession().getAttribute("uActual");
		String vieja=request.getParameter("claveV");
		String vieja2=(String) request.getSession().getAttribute("cActual");
		
		if(vieja.equals(vieja2)){ //si ingreso la antigua bien
			String nueva1=request.getParameter("claveN1");
			String nueva2=request.getParameter("claveN2");
			if(nueva1.equals(nueva2)){ //si las nuevas coinciden
				if(!nueva1.equals(vieja)){
					Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
					UsuarioDAO dao=new UsuarioDAO();
					Usuario nuevo=new Usuario(nombre, nueva1, "", "");
					try {
						dao.modificar(nuevo);
						String mensaje=" <div class=\"alert alert-error\"> <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>"
					    		+ "<strong>Clave cambiada con exito</strong> </div>";
						despacharLogin(mensaje, request, response);
					} catch (PersistenciaException e) {
						e.printStackTrace();
					}
				}
				else{ //si coincide la nueva con la antigua
					String mensaje=" <div class=\"alert alert-error\"> <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>"
				    		+ "<strong>Cambie la clave</strong> </div>";
					despachar(mensaje, request, response);
				}
				
			}
			else{
				String mensaje=" <div class=\"alert alert-error\"> <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>"
			    		+ "<strong>Claves nuevas no coinciden</strong> </div>";
				despachar(mensaje, request, response);
			}
			
		}
		else{
			String mensaje=" <div class=\"alert alert-error\"> <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>"
		    		+ "<strong>Clave antigua no coincide</strong> </div>";
			despachar(mensaje, request, response);
		}
		
		
		
		
		
	}
	
	
	private void despachar (String mensaje, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("msj", mensaje); //el "msj" es el nombre con el que guardo la variable en la tabla
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/modificarUsuario.jsp");
		rd.forward(request, response); //aca lo mando de nuevo a la pagina pero con los parametros cambiados del request

	}
	private void despacharLogin (String mensaje, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("msj", mensaje); //el "msj" es el nombre con el que guardo la variable en la tabla
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
		rd.forward(request, response); //aca lo mando de nuevo a la pagina pero con los parametros cambiados del request

	}
}
