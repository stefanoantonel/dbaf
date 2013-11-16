package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ucc.bda.web.modelo.Usuario;
import ar.edu.ucc.bda.web.modelo.dao.IUsuarioDAO;
import ar.edu.ucc.bda.web.modelo.dao.UsuarioDAO;
import ar.edu.ucc.bda.web.utiles.Constantes;
import ar.edu.ucc.bda.web.utiles.Email;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public Login() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	private void despachar (String mensaje, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("msj", mensaje); //el "msj" es el nombre con el que guardo la variable en la tabla
		//RequestDispatcher rd = getServletContext().getRequestDispatcher("/agregarCliente.jsp");
		System.out.println("despachar-> mensaje="+mensaje+"");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
		
		
		
		rd.forward(request, response); //aca lo mando de nuevo a la pagina pero con los parametros cambiados del request
		//y luego la jsp lee estos parametros 

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String usuariop=request.getParameter("usuario");
		String clavep=request.getParameter("clave");
		
		request.getServletContext().setAttribute("usuarioActual", usuariop);
		
		System.out.println("guardo el usuario en el contexto: " +usuariop);

		IUsuarioDAO usuarioDAO=new UsuarioDAO(); //uso la interfaz y uso la implementacion especifica de usuarioDAO. Esto usa polimorfismp
		try {
			
			int estado=-1; //me va a traer el estado del usuario (Activado, caduco, etc)
			Usuario usuario=usuarioDAO.cargar(usuariop,clavep);
			if(usuario!=null){
				estado=usuario.getEstado();
			}
			
			request.getSession().setAttribute("uActual",usuariop);
			request.getSession().setAttribute("cActual",clavep);
			
			
//			if(usuario!=null && usuario.getClave().equals(clavep)){
			switch (estado){
			case -1:{
				String mensaje=" <div class=\"alert alert-error\"> <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>"
			    		+ "<strong>La contraseña deben ser solo numeros</strong> </div>";
				String volver="<a href=\"menu.jsp\">Volver al menu</a>";
				despachar(mensaje, request, response);
				break;
				}
			
			case 0:{
				String mensaje=" <div class=\"alert alert-error\"> <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>"
			    		+ "<strong>Usuario o contraseña incorrectos</strong> </div>";
				String volver="<a href=\"menu.jsp\">Volver al menu</a>";
				despachar(mensaje, request, response);
				break;
				}
				case 1:{ //todo ok usuario registrado activado.
					if(usuario!=null){
						request.getServletContext().setAttribute("usuarioActualId", usuario.getId());
						//todo OK. tengo que crear una sesion
						System.out.println("antes de crear la sesion");
						ArrayList<Usuario> ses=(ArrayList<Usuario>)request.getServletContext().getAttribute("sesiones");
						int t,yaEsta=0;
						
						for(t=0;t<ses.size();t++){
								if(usuario.getNombre().equals(ses.get(t).getNombre())){
									yaEsta=-1;
									
								}
						}
						if(yaEsta==0){
							ses.add(usuario);
							HttpSession s = request.getSession(true); //false que si la sesion ya existe que no me la cree. Me la crea y graba ese usuario en el contexto de la sesion
							//tiene un sesion creada y tien un atributo con el nnombre de usuario (esto es loq ue checkeamos en el filtro)
							s.setAttribute(Constantes.usuario, usuario);
							response.sendRedirect("menu.jsp");
						}
						else{
							String mensaje=" <div class=\"alert alert-error\"> <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>"
						    		+ "<strong>EL USUARIO INGRESADO ESTA LOGUEADO</strong> </div>";
							String volver="<a href=\"menu.jsp\">Volver al menu</a>";
							request.setAttribute("volver", volver); //el "msj" es el nombre con el que guardo la variable en la tabla	
							despachar(mensaje, request, response);
						}		
					}
					else {
						String mensaje=" <div class=\"alert alert-error\"> <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>"
					    		+ "<strong>USUARIO O CONTRASEñA INCORRECTOS</strong> </div>";
						despachar(mensaje, request, response);
					}
					break;
				}
				case 2:{	//falta activar
					String mensaje=" <div class=\"alert alert-error\"> <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>"
				    		+ "<strong>Debe activar su cuenta. Por favor revise su email.</strong> </div>";
					despachar(mensaje, request, response);
					break;
				}
				case 3:{	//exipiro
					response.sendRedirect("modificarUsuario.jsp");
					break;
				}
				default: {
					response.sendRedirect("login.jsp");
					break;
				}
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
        
	}

	
}
