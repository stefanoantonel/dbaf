package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ar.edu.ucc.bda.web.modelo.Usuario;
import ar.edu.ucc.bda.web.modelo.dao.IUsuarioDAO;
import ar.edu.ucc.bda.web.modelo.dao.NotasDAO;
import ar.edu.ucc.bda.web.modelo.dao.UsuarioDAO;
import ar.edu.ucc.bda.web.utiles.Constantes;

/**
 * Servlet implementation class GetNotas
 */
@WebServlet("/getNotas")
public class GetNotas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetNotas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
//		ArrayList<Usuario> ses=(ArrayList<Usuario>)request.getServletContext().getAttribute("sesiones");
		//OBTENGO EL UerSUARIO PARA SACAR LAS NOTAS DE ESE ESPECIFICO
		String notaBuscar=request.getParameter("texto");
		
		
		System.out.println("parametro de ajax: "+notaBuscar);
		String usuarioActual=request.getServletContext().getAttribute("usuarioActualId").toString();
		NotasDAO notas=new NotasDAO();
		System.out.println("Le mando el usuario: "+usuarioActual);
		
//		List<List<String>> distintasNotas=null;
//		try {
//			distintasNotas = notas.load(usuarioActual);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		JSONArray distintasNotas=null;
		try {
			
			if( notaBuscar==null ||notaBuscar.equals("undefined")||notaBuscar.equals("-1")){
				System.out.println("entro a buscar todas");
				distintasNotas = notas.load(usuarioActual);
				System.out.println("notas todas"+distintasNotas);
				request.getSession().setAttribute("notas", distintasNotas);
				despachar("despache desde GetNotas", request, response);
			}
			else{
				System.out.println("entro con parametro");
				distintasNotas = notas.load(usuarioActual,notaBuscar);
				System.out.println("notas filtradas"+distintasNotas);
				response.setContentType("application/json");
				response.getWriter().println(distintasNotas);
				
			}
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		if(distintasNotas!=null){
//			for (int i = 0; i < distintasNotas.length(); ++i) {
//			    JSONObject rec;
//				try {
//					rec = distintasNotas.getJSONObject(i);
//					
//					System.out.println(rec.get("id"));
//				    System.out.println(rec.get("value"));
//				} catch (JSONException e) {
//					
//					e.printStackTrace();
//				}
//			    
//			    // ...
//			}
//		}
		if(distintasNotas!=null){
			
			
			//response.sendRedirect("/dbaf/nota.jsp");
//			response.
			
			
		}
		else
			System.out.println("error en GetNotas");
		
	}
	
	
	private void despachar (String mensaje, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("msj", mensaje);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/mostrarNotas.jsp");
		rd.forward(request, response);
		
	}
}
