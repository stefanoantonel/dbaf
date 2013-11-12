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


@WebServlet("/EliminarNota")
public class EliminarNota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EliminarNota() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
		
		String id=request.getParameter("id");
		id=id.trim();
		NotasDAO dao=new NotasDAO();
		boolean a=dao.delete(id);
		if(a==true){
			System.out.println("Todo ok eliminado");
		}
		
	}

}
