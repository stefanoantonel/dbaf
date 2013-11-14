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
import ar.edu.ucc.bda.web.modelo.dao.IUsuarioDAO;
import ar.edu.ucc.bda.web.modelo.dao.UsuarioDAO;
import ar.edu.ucc.bda.web.utiles.Fecha;

@WebServlet("/activar")
public class Activar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Activar() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}
//permitir solo el metodo post, para los formularios, para evitar que escriba lo que quiera
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail=request.getParameter("mail");
		System.out.println("mail: "+mail);
		IUsuarioDAO usDAO = new UsuarioDAO();
		
		Usuario us = usDAO.getUsuarioByEmail(mail); 
		
		Fecha f = new Fecha();
		
		String mnj= "";
   		RequestDispatcher rd=null;
   		
		if(f.mayorFechaActual(us.getFecha_creacion()))
		{
			//Mandar MAIL
			us.sendEmial();
			System.out.println("mail vencido");
			usDAO.updateCreacion(us);
			mnj="<div class=\"alert alert-error\"> Mail a vencido. Revise su email</div>";
			request.setAttribute("msj", mnj);
			rd = getServletContext().getRequestDispatcher("/login.jsp");
		}
		else
		{
			
//			request.getSession().setAttribute("usuario", us);
//			request.getSession().setAttribute("usuarioNombre", us.getNombre());
//			System.out.println("us.getNombre():   "+us.getNombre());
//			RequestDispatcher rd = getServletContext().getRequestDispatcher("/activarUsuario.jsp");
//			rd.forward(request, response);
			
			//Usuario us =(Usuario)request.getSession().getAttribute("usuario");
			
	   		
	   		try {
				if(us.activar())
				 	{	//TODO OK
						mnj="<div class=\"alert alert-success\"> Usuario activado correctamente</div>";
						request.setAttribute("msj", mnj);
						rd = getServletContext().getRequestDispatcher("/login.jsp");
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
	   		
	   		
		}
		rd.forward(request, response);
		
	}
    
    
	
}
