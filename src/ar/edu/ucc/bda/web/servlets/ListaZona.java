package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ucc.bda.web.utiles.Constantes;

@WebServlet("/ListaZona")
public class ListaZona extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListaZona() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder sb=new StringBuilder();
		try{
			
		      Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
		      Statement select = cn.createStatement();
		      ResultSet resultado = select.executeQuery("SELECT * FROM zonas");
		      //sb.append("<select id=\"listaZona\" onChange=\"document.getElementById(\"+oculto+\").value =this.id.selectedIndex.value;\">");"); //es para que " no funcionen
		      sb.append("<select id=\"listaZona\">");
		      //StringBuilder sb = new StringBuilder("<table border='1'>");
		      int i=0;
				while (resultado.next())
				{
					i++;
					String zona= resultado.getString("Zona");
					sb.append("<option value=\"");
					sb.append(resultado.getInt("idZona"));
					sb.append("\">");
					sb.append(zona);
					sb.append("</option>");
					
				
			 }	sb.append("</select>");
				
		}catch (Exception e){
			   e.printStackTrace();
			 }
		
		//request.setAttribute("zonas", sb.toString());
		//response.sendRedirect("/agregarCliente.jsp");
		
		request.getSession().setAttribute("zonas", sb.toString()); //para que me lo guarde por mas tiempo.
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/agregarCliente.jsp");
		rd.forward(request, response);
		
		
		/*
		System.out.println("lissta");
		response.setContentType("text/html");
		response.getWriter().println(sb.toString()); 	
		*/
		//despachar(sb.toString(), request, response);
		return;
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}


}
