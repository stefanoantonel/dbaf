package ar.edu.ucc.bda.web.modelo;

public class Usuario {
	
	String nombre;
	String email;
	String clave;
	String id;


	public Usuario(String nom, String cla,String mail,String ident) {
		super();
		this.nombre = nom;
		this.clave = cla;
		this.email=mail;
		this.id=ident;
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
	

}
