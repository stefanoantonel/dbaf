package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		if(f.mayorFechaActual(us.getFecha_creacion()))
		{
			//Mandar MAIL
			System.out.println("mail vencido");
		}
		else
		{
			
			request.getSession().setAttribute("usuario", us);
			request.getSession().setAttribute("usuarioNombre", us.getNombre());
			System.out.println("us.getNombre():   "+us.getNombre());
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/activarUsuario.jsp");
			rd.forward(request, response);
		}
			
		
	}
    
    
	
}
