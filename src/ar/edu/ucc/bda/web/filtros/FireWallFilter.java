package ar.edu.ucc.bda.web.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ar.edu.ucc.bda.web.modelo.Usuario;
import ar.edu.ucc.bda.web.utiles.Constantes;

/**
 * Servlet Filter implementation class FireWallFilter
 */
@WebFilter("/*")
public class FireWallFilter implements Filter {

	private String[] noProtegidos;
	
    public FireWallFilter() {
        
    }

	
	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//todas las peticiones van a pasar por aca porque le puse /* VA A ACTUAR SIEMPRE
		/*puedo tener N cantidad de filtro, se ejecutan en el orden en que son creados
		si creo mas filtros, tengo que "pasar la bola" con el chain o a los siguientes filtros. 
		
		vamos a imprimir todas las URL 
		
		*/
		//lo transofrmo en HTTP no se bien porque... 
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		
		
		//definimos un arreglo de los recursos que NO queremos proteger
		
		String uri=req.getRequestURI().toLowerCase();
		boolean noProtegido=false;
		
		for(String np:noProtegidos){
			if(uri.endsWith(np)){
				//el recuros no esta protegido
				noProtegido=true;
				break;
			}
		}
		//System.out.println("uri="+req.getRequestURI()+" protegido="+!noProtegido);
		//si esta protejido tiene que verificar si tiene una sesion abierta y si esta en el atrributo.
		boolean necesitaLogin=false;
		if(!noProtegido){
			//verifico que esta  logueado
			//si no exixte la secion que no la crree
			HttpSession s=req.getSession(false);
			if(s!=null){
				//obtengo el atributo del usuario en la session
				Object o=s.getAttribute(Constantes.usuario);
				if(o==null || !(o instanceof Usuario)){
					necesitaLogin=true;
				}
			}
			
		}
		
		else { //sigo como si nada el curso normal porque no esta protegido 
			chain.doFilter(request, response);
			return;
		}
		if(necesitaLogin){
			//el recurso esta protegido pero el usuario no esta logueado.
			res.sendRedirect("login.jsp");
			return;
		}
		else { 
			//el recurso esta protegido pero esta logeado
			chain.doFilter(request, response);
			return;
		}
		//
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		//Es como el constructor...
		//agregamos lo que no queremos proteger. por ejemplo el log porque si lo protejo no podria logearme si no estoy logueado
		//la la URI termina con algo de lo de abajo lo dejo pasar, no lo restrinjo
		noProtegidos=("/login.jsp,/Login,/agregarUsuario.jsp,/agregarUsuario,.html,.css,.gif,.ico," +
				".js,.jpg,ListaZona,/modificarUsuario.jsp,modificarUsuario.jsp,/updateUsuario,/activar,activarUs.jsp").toLowerCase().split(","); //separo en array antes y dsp del ,
	}

}
