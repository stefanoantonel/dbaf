package ar.edu.ucc.bda.web.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ar.edu.ucc.bda.web.modelo.Cliente;
import ar.edu.ucc.bda.web.utiles.Constantes;

public class ClienteDAO {

	Connection con;
	
	public ClienteDAO(Connection c){
		this.con=c;
	}
	public boolean agregar(Cliente cli){
		 try{
				
//		      Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
		         
				
				String sql="INSERT INTO clientes (cliente,idZona,cuentaHabilitada) VALUES (?,?,?)";
				PreparedStatement pst=con.prepareStatement(sql);
				
				pst.setString(1,cli.getCliente());
				pst.setInt(2,cli.getIdZona());
				pst.setInt(3, cli.isCuentaHabilitada()? 1:0);
				
				pst.executeUpdate();
				return true;
		  }	
		catch (Exception e){
//			despachar(mensaje2HTML(mensaje),request,response);
			//request.setAttribute("msj", "Problemas con la BD");
			//RequestDispatcher rd = getServletContext().getRequestDispatcher("/agregarCliente.jsp");
			
			//rd.forward(request, response);
			return false;
		}
	}
}

