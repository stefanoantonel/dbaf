package ar.edu.ucc.bda.web.servlets;

import java.io.IOException;
import ar.edu.ucc.bda.web.utiles.Email;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.ucc.bda.web.modelo.PersistenciaException;
import ar.edu.ucc.bda.web.modelo.Usuario;
import ar.edu.ucc.bda.web.modelo.dao.IUsuarioDAO;
import ar.edu.ucc.bda.web.modelo.dao.UsuarioDAO;
import javax.mail.Address;


@WebServlet("/agregarUsuario")
public class AgregarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AgregarUsuario() {
        super();
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String usuariop=request.getParameter("nombre");
		String clave1=request.getParameter("clave1");
		String clave2=request.getParameter("clave2");
		
		String email=request.getParameter("mail");
		
		//Email em = new Email();
		//Email.main(null);
	
		//Email em = new Email("florenciabonansea@gmail.com", "extlpphhgovsmnqh", email, "Activar cuenta");
		//String error =em.send();
		//System.out.println("error: "+error);
		
		if(!clave1.equals(clave2)){
			despachar("Error: Claves no coinciden", request, response);
			
		}
		
		else{
			try{
				int clave=Integer.parseInt(clave1);
				Usuario nuevo=new Usuario(usuariop, clave1,email,"");
				
				IUsuarioDAO usuarioDAO=new UsuarioDAO(); //uso la interfaz y uso la implementacion especifica de usuarioDAO. Esto usa polimorfismp
				try {
					if(usuarioDAO.agregar(nuevo)==true){
						String mensaje=" <div class=\"alert alert-error\"> <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>"
						    		+ "<strong>Revise su email para confirmar</strong> </div>";
						//String volver="<a href=\"menu.jsp\">Volver al menu</a>";
						//request.setAttribute("volver", volver); //el "msj" es el nombre con el que guardo la variable en la tabla
						System.out.println("email: "+email);
						nuevo.sendEmial();
						
						
						despachar(mensaje, request, response);
					}
					else{
						String mensaje=" <div class=\"alert alert-error\"> <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>"
					    		+ "<strong>Error: contraseña incorrecta o usuario ya registrado</strong> </div>";
						despachar(mensaje, request, response);
					}
				} 
				catch (PersistenciaException e) {
					
					e.printStackTrace();
				}
			}
			catch (NumberFormatException e){
				String mensaje=" <div class=\"alert alert-error\"> <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>"
			    		+ "<strong>Datos incorrectos</strong> </div>";
				despachar(mensaje, request, response);
				
			}
			
		}
		
	}
	
	private void despachar (String mensaje, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("msj", mensaje); //el "msj" es el nombre con el que guardo la variable en la tabla
		//RequestDispatcher rd = getServletContext().getRequestDispatcher("/agregarCliente.jsp");
		System.out.println("despachar-> mensaje="+mensaje+"");
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/agregarUsuario.jsp");
		
		rd.forward(request, response);

	}

}
