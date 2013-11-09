package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Meta programacion, cuando el compilador java se encuentra con esto
//@WebServlet --> inyecta codigo
// puedo cambiar la URL "/"

@WebServlet("/HolaMundo")
public class HolaMundo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public HolaMundo() {
        super();
       
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	 Date date=new Date();
	 String mensaje="Hola Mundo" + date; //esto le respondemos desde el servidor al cliente
	 //para eso uso response
	 StringBuilder sb= new StringBuilder("<ol>");//para cadenas acumulativas
	 for (int t=1; t<=100; t++)
	 {
		 sb.append("<li>"); // append para que no cree mas estring.
		 sb.append("a");
		 sb.append(t);
		 sb.append("</li>");
	 }
	 
	 sb.append("</ol>");
	 response.setContentType("text/html");
	PrintWriter print= response.getWriter(); //string orientado a caracteres
	print.println(mensaje+sb);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


}
