package ar.edu.ucc.bda.web.modelo;

import java.util.Date;

import ar.edu.ucc.bda.web.modelo.dao.IUsuarioDAO;
import ar.edu.ucc.bda.web.modelo.dao.UsuarioDAO;
import ar.edu.ucc.bda.web.utiles.Email;

public class Usuario {
	
	String nombre;
	String email;
	String clave;
	String id;
	int estado;
	IUsuarioDAO usDAO;
	String fecha_creacion;
	String fecha_expiracion;

	public Usuario(String nom, String cla,String mail,String ident) {
		super();
		this.nombre = nom;
		this.clave = cla;
		this.email=mail;
		this.id=ident;
		usDAO = new UsuarioDAO();
	}

	public Usuario(String id,String nom,String fecha_c)
	{
		this.id=id;
		nombre= nom;
		fecha_creacion=fecha_c;
		usDAO = new UsuarioDAO();
	}
	
	public String getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public String getFecha_expiracion() {
		return fecha_expiracion;
	}

	public void setFecha_expiracion(String fecha_expiracion) {
		this.fecha_expiracion = fecha_expiracion;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public Usuario getUsuarioByEmail (String email)
	{
		return usDAO.getUsuarioByEmail(email);
	}
	
	public boolean activar()throws PersistenciaException
	{
		try {
			return usDAO.activar(this);
		} catch (PersistenciaException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void sendEmial(){
		
		Email e=new Email(getEmail());
		e.send();
	}
}
