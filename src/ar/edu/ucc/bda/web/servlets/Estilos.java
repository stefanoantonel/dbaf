package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Estilos")
public class Estilos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Estilos() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String estiloSeleccionado=request.getParameter("estiloSeleccionado");
		
		if(!estiloSeleccionado.equals("-1"))
			 request.getServletContext().setAttribute("estilo",estiloSeleccionado);
		
		String estilo=(String) request.getServletContext().getAttribute("estilo");
		
		if(estilo == null)
		{
			estilo="Negro";
		}
		System.out.println("est: "+estilo);
		response.setContentType("text/html");
		response.getWriter().println(estilo);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
