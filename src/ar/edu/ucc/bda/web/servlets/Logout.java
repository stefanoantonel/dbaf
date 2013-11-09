package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ucc.bda.web.modelo.Usuario;
import ar.edu.ucc.bda.web.utiles.Constantes;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession s=request.getSession(false);//si no existe que no la cree
		if(s!=null){
			s.removeAttribute(Constantes.usuario);
			s.invalidate(); //destruyo la session 
			/*
			ArrayList<Usuario> ses=(ArrayList<Usuario>)request.getServletContext().getAttribute("sesiones");
			int t,yaEsta=0;
			//Usuario u=Constantes.usuario;
			for(t=0;t<ses.size();t++){
					if(Constantes.usuario.equals(ses.get(t).getNombre())){
						ses.set(t, null);
						
					}
			}
			*/
			
		}
		response.sendRedirect("login.jsp");
		
	}

}
