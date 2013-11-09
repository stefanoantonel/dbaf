package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ucc.bda.web.modelo.Cliente;
import ar.edu.ucc.bda.web.modelo.dao.ClienteDAO;
import ar.edu.ucc.bda.web.utiles.Constantes;


@WebServlet("/AgregarCliente")
public class AgregarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AgregarCliente() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}
//permitir solo el metodo post, para los formularios, para evitar que escriba lo que quiera
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cliente= request.getParameter("parametroNombre");
		//String idZona1= request.getParameter("parametroIdZona");
		String cuentaHabilitada= request.getParameter("parametroHabilitado");
		
		String idZona2= request.getParameter("oculto");
		System.out.println(cliente);
		System.out.println(cuentaHabilitada);
		System.out.println(idZona2);
		
		//controlar los parametros ahora son responsabilidad de la clase.
		/*
		int idZona=-1; //para que me mande el error si es null
		if(idZona1!=null && idZona1.length()>0)
		{
			idZona=Integer.parseInt(idZona1);
		}
		*/
		Cliente c = new Cliente();
		c.setCliente(cliente);
		c.setIdZona(Integer.parseInt(idZona2));
		c.setCuentaHabilitada(cuentaHabilitada==null || cuentaHabilitada.equals("0") ? false :true);
		
		String mensaje = c.datosValidos();
		if(mensaje.length()>0) 
		{
			
			//guardar cosas en el ambito request: Guarda atributos: nombre y objeto
			request.setAttribute("msj", mensaje2HTML(mensaje));
			//response.sendRedirect("errorParametros.html");// ida vuelta, ida vuelta
			// en la jsp puedo poner java.
			//no se pasa como parametro, se guarda en un lugar accesible por la jsp
			//en el ambito del reques (el mas pequeño: el que menos recursos gasta)
			//para que el server me mande directamente a la pag que tengo q ir se usa:
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/agregarCliente.jsp");
			rd.forward(request, response);
			return;
		}
//		  try{
//		
//		      Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
//		         
//				
//				String sql="INSERT INTO clientes (cliente,idZona,cuentaHabilitada) VALUES (?,?,?)";
//				PreparedStatement pst=cn.prepareStatement(sql);
//				
//				pst.setString(1,c.getCliente());
//				pst.setInt(2,c.getIdZona());
//				pst.setInt(3, c.isCuentaHabilitada()? 1:0);
//				
//				pst.executeUpdate();
//		  }	
//		catch (Exception e){
//			despachar(mensaje2HTML(mensaje),request,response);
//			//request.setAttribute("msj", "Problemas con la BD");
//			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/agregarCliente.jsp");
//			
//			//rd.forward(request, response);
//			return;
//		}
		
		
		Connection con =(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
		
		ClienteDAO dao=new ClienteDAO(con);
		
//		mensaje="Cliente ok";
//		despachar(mensaje2HTML(mensaje),request,response);
		//request.setAttribute("msj", "cliente OK");
		//RequestDispatcher rd = getServletContext().getRequestDispatcher("/agregarCliente.jsp");
		//rd.forward(request, response);
		
		
		
		if(dao.agregar(c)){
			despachar("Todo OK", request, response);
		}

		
		
	}
		//le paso la conexion
		
		


	private void despachar (String mensaje, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("msj", mensaje);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/agregarCliente.jsp");
		rd.forward(request, response);
		
	}
	
	
	private String mensaje2HTML(String m)


	{
		StringBuilder sb = new StringBuilder();
		//split separa, join me arma un string
		String[] mensajes=m.split(","); 
		if(mensajes.length>0)
		{
			sb.append("<ul>");
			for (String mensaje: mensajes)
			{
				sb.append("<li>"+mensaje+"</li>");
			}
			sb.append("</ul>");
		}
		return sb.toString();
	}
	public String getT(){
		String tipo="button";
		return tipo;
	}
}
