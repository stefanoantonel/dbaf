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

@WebServlet("/GuardaNota")
public class GuardaNota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GuardaNota() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
		String nota=request.getParameter("nota");
		String id=request.getParameter("id");
		System.out.println("NotaCambiada: "+nota+" Id: "+id);
		NotasDAO dao=new NotasDAO();
		boolean a=dao.update(nota, id);
		if(a==true){
			System.out.println("Todo ok guardados");
		}
		response.setContentType("text/html");	
		response.getWriter().print("<input type=\"text\" value=\""+Math.random()+"\"<br/>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("entro post");
		doGet(request, response);
	}

}
