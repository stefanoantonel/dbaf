package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/guardarFecha")
public class GuardarFecha extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public GuardarFecha() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
		String fecha=request.getParameter("fecha");
		int vencida=0;
		//Nota nota=new Nota();
		//boolean a=nota.updateFecha(fecha);
	//	if(a==true){
			System.out.println("Todo ok guardarNota");
	//	}
		
		//vencida=nota.esVencida(fecha);
		vencida=1;
		JSONObject json=new JSONObject();
		
		try {
			json.put("vencida", vencida);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("vencido: "+json);
		response.setContentType("application/json");
		response.getWriter().println(json);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
