package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contenidoRandom")
public class ContenidoRandom extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ContenidoRandom() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.sendRedirect("/dbaf/nota.jsp");
		
		
		response.setContentType("text/html");
		response.getWriter().print("<input type=\"text\" value=\""+Math.random()+"\"<br/>");
	}

}


