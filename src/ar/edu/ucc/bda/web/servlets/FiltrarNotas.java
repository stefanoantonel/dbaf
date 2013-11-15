package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import ar.edu.ucc.bda.web.modelo.dao.INotasDAO;
import ar.edu.ucc.bda.web.modelo.dao.NotasDAO;

@WebServlet("/FiltrarNotas")
public class FiltrarNotas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FiltrarNotas() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String usuarioActualId=(String) request.getServletContext().getAttribute("usuarioActualId");
		String filtro = request.getParameter("filtro");
		INotasDAO notaDao=new NotasDAO();
		System.out.println("entro al filtrar: "+filtro);
		JSONArray distintasNotas=new JSONArray();
		try {
			if (filtro.equals("hacer")) {
				distintasNotas=notaDao.loadHacer(usuarioActualId);
			}
			if (filtro.equals("listas")) {
				distintasNotas=notaDao.loadLista(usuarioActualId);
			}
			if(filtro.equals("todas")){
				distintasNotas=notaDao.load(usuarioActualId);
			}
		} 
		catch (JSONException e) {
			System.out.println("error en filtro notas");
			e.printStackTrace();
		}
		System.out.println("notas filtro: "+distintasNotas);
		response.setContentType("application/json");
		response.getWriter().println(distintasNotas);
	}

}
