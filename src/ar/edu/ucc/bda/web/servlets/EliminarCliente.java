package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ucc.bda.web.utiles.Constantes;

@WebServlet("/EliminarCliente")
public class EliminarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public EliminarCliente() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String nombre=(String) request.getSession().getAttribute("eleccion");
		String eleccion=request.getParameter("labelElec");
		//System.out.println("eleccion: "+eleccion);
		int eleccionInt=Integer.parseInt(eleccion); //porque es el ID
		//System.out.println("hollllll");
		//response.setContentType("text/html");
		//response.getWriter().println(nombre);
		try{
			
		      Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
		         
				
				String sql="DELETE FROM clientes WHERE idcliente="+eleccionInt+"";
				Statement st=cn.createStatement();
				st.executeUpdate(sql);
		  }	
		catch (Exception e){
			System.out.println("ERROR al borrar");
			//request.setAttribute("msj", "Problemas con la BD");
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/agregarCliente.jsp");
			
			//rd.forward(request, response);	
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/menu.jsp");
		String mensaje=" <div class=\"alert alert-error\"> <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>"
	    		+ "<strong>El cliente fue eliminado</strong> </div>";
		
		
		request.setAttribute("eliminado", mensaje);
		rd.forward(request, response);
		//response.sendRedirect("/dbaf/menu.jsp");
	}
}
