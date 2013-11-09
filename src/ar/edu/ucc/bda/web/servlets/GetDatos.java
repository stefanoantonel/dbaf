package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ar.edu.ucc.bda.web.utiles.Constantes;

@WebServlet("/getDatos")
public class GetDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GetDatos() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Object texto=request.getParameter("texto");
		//no lo pongo como name texto en el submint porque para eso necesito si o si el sbmit porque usa el get o post y el form.
		JSONArray clientes=new JSONArray(); //cremoa un objeto json array
		
		
		try {
			Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
			Statement select=cn.createStatement(); //creo el statement. 
			ResultSet rs= select.executeQuery("select* from clientes where Cliente like '%"+texto+"%'"); //ejecuto y lo guardo si o si en un 
			//result set que es especial para gusardar el resultado 
			
			
			while(rs.next()){
				JSONObject cliente=new JSONObject();
				cliente.put("descripcion", rs.getString("cliente"));
				
				JSONObject otro=new JSONObject();
				otro.put("idCliente", rs.getString("idCliente"));
				otro.put("idZona", rs.getString("idZOna"));
				otro.put("cuentaHabilitada", rs.getString("cuentaHabilitada"));
				otro.put("fecha", new Date());
				cliente.put("otro", otro);
				
				
				clientes.put(cliente); //lo pongo en el array
				
				
			}
		} catch ( SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.setContentType("application/json");
		response.getWriter().println(clientes.toString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
