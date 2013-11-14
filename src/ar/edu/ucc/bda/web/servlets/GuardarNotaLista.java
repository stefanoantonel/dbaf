package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ucc.bda.web.modelo.dao.NotasDAO;
import ar.edu.ucc.bda.web.utiles.Constantes;

@WebServlet("/GuardarNotaLista")
public class GuardarNotaLista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public GuardarNotaLista() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
		String estado=request.getParameter("lista");
		String id=request.getParameter("id");
		System.out.println("me guardarNotaLista estado: "+estado+" id "+id);
		NotasDAO dao=new NotasDAO();
		boolean a=dao.updateLista(estado, id);
		if(a==true){
			System.out.println("Todo ok notaLista");
		}
		response.setContentType("text/html");	
		response.getWriter().print("<input type=\"text\" value=\""+Math.random()+"\"<br/>");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
