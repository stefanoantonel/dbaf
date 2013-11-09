package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.bcel.classfile.ArrayElementValue;

/**
 * Servlet implementation class VerParametros
 */
@WebServlet("/VerParametros")
public class VerParametros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Enumeration<String> nombres= request.getParameterNames();
	
		StringBuilder sb= new StringBuilder("<ol>");
		while (nombres.hasMoreElements())
		{
			String nombre=nombres.nextElement();
			sb.append("<li>");
			sb.append(nombre);
			String[] valor_nombre= request.getParameterValues(nombre);
			sb.append("<ul>");
			for(String valor:valor_nombre)
			             //for(int i=0;i<valor_nombre.length;i++ )
			{
			    sb.append("<li>");
			    sb.append(valor);
				//sb.append(valor_nombre);
				sb.append("</li>");
			}
			sb.append("</ul>");
			sb.append("</li>");
			
		}
		 sb.append("</ol>");
		 
		 response.setContentType("text/html");
		response.getWriter().println(sb);
		
	}

}

//http://localhost:8080/dba/VerParametros?fhdf=aksnd&hola=aniiiiiiiiiiiii
// con getparameters se pierde el primer valor de cada parametro
//si quiero tener todos los valores uso: getParametersvalues

