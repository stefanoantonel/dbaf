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
@WebServlet("/GetNotas")
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
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
////		ArrayList<Usuario> ses=(ArrayList<Usuario>)request.getServletContext().getAttribute("sesiones");
//		//OBTENGO EL USUARIO PARA SACAR LAS NOTAS DE ESE ESPECIFICO
//		String usuarioActual=request.getServletContext().getAttribute("usuarioActual").toString();
//		NotasDAO notas=new NotasDAO(cn);
//		System.out.println("Le mando el usuario: "+usuarioActual);
//		JSONArray distintasNotas = null;
//		try {
//			distintasNotas = notas.load(usuarioActual);
//		} catch (JSONException e1) {
//			
//			e1.printStackTrace();
//		}
////		List<String> distintasNotas =notas.load(usuarioActual);
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
//		
//		request.getSession().setAttribute("notas", distintasNotas);
//		despachar("despache desde GetNotas", request, response);
//	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Connection cn=(Connection)getServletContext().getAttribute(Constantes.NOMBRE_CONEXION);
//		ArrayList<Usuario> ses=(ArrayList<Usuario>)request.getServletContext().getAttribute("sesiones");
		//OBTENGO EL USUARIO PARA SACAR LAS NOTAS DE ESE ESPECIFICO
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
			distintasNotas = notas.load(usuarioActual);
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
			request.getSession().setAttribute("notas", distintasNotas);
			
			despachar("despache desde GetNotas", request, response);
			//response.sendRedirect("/dbaf/nota.jsp");
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
