package ar.edu.ucc.bda.web.modelo;

public class Usuario {
	
	String nombre;
	String email;
	String clave;


	public Usuario(String nom, String cla,String mail) {
		super();
		this.nombre = nom;
		this.clave = cla;
		this.email=mail;
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
	

}
