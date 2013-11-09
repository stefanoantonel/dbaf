package ar.edu.ucc.bda.web.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public final class ListaDeUsuarios {
	
	private List<HttpSession> lista;
	private static ListaDeUsuarios instancia;
	/*
	private static ListaDeUsuarios(){
		lista=new ArrayList<HttpSession>();
	}
	*/
	public static ListaDeUsuarios getInstance(){
		if(instancia==null){
			instancia =new ListaDeUsuarios();
		}
		return instancia;
	}

	/* El obtener la sesion le sirve a todas las app, en ver si es intancia 
	 * */
}
