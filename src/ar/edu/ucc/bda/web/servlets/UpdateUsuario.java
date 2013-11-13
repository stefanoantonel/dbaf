package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String vieja=request.getParameter("claveV");
		String nueva1=request.getParameter("claveN1");
		String nueva2=request.getParameter("claveN2");
		Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
		UsuarioDAO dao=new UsuarioDAO(cn);
		Usuario
		dao.
	}

}
