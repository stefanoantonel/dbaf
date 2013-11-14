package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ucc.bda.web.modelo.PersistenciaException;
import ar.edu.ucc.bda.web.modelo.Usuario;
import javax.mail.internet.*;

@WebServlet("/activarUsuario")
public class ActivarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ActivarUsuario() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


   	}
   //permitir solo el metodo post, para los formularios, para evitar que escriba lo que quiera
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		Usuario us =(Usuario)request.getSession().getAttribute("usuario");
   		String mnj= "";
   		RequestDispatcher rd=null;
   		try {
			if(us.activar())
			 	{	//TODO OK
					mnj="<div class=\"alert alert-success\"> Usuario activado correctamente</div>";
					request.setAttribute("msj", mnj);
					rd = getServletContext().getRequestDispatcher("/menu.jsp");
				}

			else
				{
				mnj="Error ocurrido en la activacion";
				request.setAttribute("msj", mnj);
				rd = getServletContext().getRequestDispatcher("/login.jsp");
				}
		} catch (PersistenciaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   		rd.forward(request, response);
   		
   	}

}

