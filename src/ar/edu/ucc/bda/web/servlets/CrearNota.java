package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ucc.bda.web.modelo.dao.NotasDAO;
import ar.edu.ucc.bda.web.utiles.Constantes;


@WebServlet("/CrearNota")
public class CrearNota extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CrearNota() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
		String usuarioActualId=(String) request.getServletContext().getAttribute("usuarioActualId");
		
		NotasDAO dao=new NotasDAO();
		boolean a=dao.insert(usuarioActualId);
		if(a==true){
			System.out.println("Todo ok guardados");
		}
		despachar("",request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void despachar (String mensaje, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/getNotas");
		rd.forward(request, response);
		
	}

}
