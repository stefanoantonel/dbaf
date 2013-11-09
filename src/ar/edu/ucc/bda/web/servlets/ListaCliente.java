package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ucc.bda.web.utiles.Constantes;

@WebServlet("/ListaCliente")
public class ListaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListaCliente() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//String filtro=request.getParameter("filtro"); //obtengo el paramtero que ingreso el cleinte po el nombre
		String filtro="";
		
		try {
			Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
			Statement select=cn.createStatement(); //creo el statement. 
			ResultSet rs= select.executeQuery("select* from clientes where Cliente like '%"+filtro+"%'"); //ejecuto y lo guardo si o si en un 
			//result set que es especial para gusardar el resultado 
			
			StringBuilder sb=new StringBuilder();		//es para cuando queremos cambiar el valor

			sb.append("<form action=\"EliminarCliente\" method=\"post\">");
			sb.append("<input type=\"text\" id=\"labelElec\" name=\"labelElec\" value=\"\" style=\"display: none\"/>");
			sb.append("<ol id="+"oll"+" name="+"lista"+" >");
			while(rs.next()){
				String nombre=rs.getString("Cliente");
				String idCliente=rs.getString("idCliente");
				sb.append("<li>");
				//value="+"+nombre+"+"
				
				sb.append(nombre);
				// nclick="+"document.getElementById("+"oll"+").value="+idCliente+";+"	 href="+"EliminarCliente"+" 
				sb.append(" 	<input type=\"submit\" id=\"pepe\"  value=\"Eliminar\" name=\"link\" onclick=\"javascript:elimina("+idCliente+");\">");
						//+" onclick=\"javascript:alert('hello');\">");
						//+\"
						
				
				sb.append("</li>");
				
				
				
			}
			sb.append("</ol>");
			sb.append("</form>");
			
			
			//response.setContentType("text/html");
			//response.getWriter().println(sb);
			request.getSession().setAttribute("lista", sb.toString()); //para que me lo guarde por mas tiempo.
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/eliminarCliente.jsp");
			rd.forward(request, response);
			

		} catch ( SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response); //se lo paso al otro 
	}
	
	
	

}
