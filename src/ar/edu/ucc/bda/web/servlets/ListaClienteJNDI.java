package ar.edu.ucc.bda.web.servlets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import ar.edu.ucc.bda.web.utiles.Constantes;

@WebServlet("/ListaClienteJNDI")
public class ListaClienteJNDI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		//String filtro = request.getParameter("filtroTexto");

		
	
			
			
			//Class.forName("com.mysql.jdbc.Driver");
			//Connection cn = DriverManager.getConnection(
					//"jdbc:mysql://localhost:3306/practico", "root", "root");
			
			
		 try{
				
		      Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
		
		      Statement select = cn.createStatement();
			ResultSet resultado = select
					.executeQuery("SELECT * FROM clientes");// WHERE cliente like '%"
						//	+ filtro + "%'");

			//StringBuilder sb;=new StringBuilder("<ol>");
			StringBuilder sb = new StringBuilder();
			sb.append("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title></title>"
					+ "<link href=\"css/bootstrap.min.css\" rel=\"stylesheet\" media=\"screen\">		"
					+ "<link href=\"css/ui-lightness/jquery-ui-1.10.3.custom.min.css\" rel=\"stylesheet\" media=\"screen\">"
					+ "</head><body><div align=\"center\"><table border='5'>");
			while (resultado.next()) {
				String nombre = resultado.getString("cliente");
				int id = resultado.getInt("idCliente");

				sb.append("<tr>");

				sb.append("<td>");
				sb.append(id);
				sb.append("</td>");

				sb.append("<td>");
				sb.append(nombre);
				sb.append("</td>");

				sb.append("</tr>");
			}
			sb.append("</table>");
			sb.append("</div>");
			sb.append("</body>");
			response.setContentType("text/html");
			response.getWriter().println(sb);
		 }catch (Exception e){
		   e.printStackTrace();
		 }
	}

}
