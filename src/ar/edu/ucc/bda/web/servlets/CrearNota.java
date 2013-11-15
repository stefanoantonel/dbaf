package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

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
		JSONArray ultimaNota;
		try {
			NotasDAO dao=new NotasDAO();
			boolean a=dao.insert(usuarioActualId);
			if(a==true){
				System.out.println("Todo ok guardados");
			}
			ultimaNota = dao.loadUltima(usuarioActualId);
			System.out.println("Antes devolver: "+ultimaNota);
			response.setContentType("application/json");
			response.getWriter().println(ultimaNota.toString());
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void despachar (String mensaje, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/getNotas");
		rd.forward(request, response);
		
	}

}
