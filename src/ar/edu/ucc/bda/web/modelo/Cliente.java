package ar.edu.ucc.bda.web.modelo;

import java.io.Serializable;
//clase java q tengo intencion de mandar por la red: serializar. -->implementar serializable (cuando la creo)
// la guarda de otra forma en memoria.
//new class ADD serializable.java...
// para guardar en la secion... request... para que tomcat las pueda guardar si se queda sin memoria
public class Cliente implements Serializable {
		private int idCliente;
	   private String cliente;
	   private int idZona;
	   private boolean cuentaHabilitada;
	
   public String getIdCliente() {
	   String a="cliente";
	   return a;
		//return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public int getIdZona() {
		return idZona;
	}
	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}
	public boolean isCuentaHabilitada() {
		return cuentaHabilitada;
	}
	public void setCuentaHabilitada(boolean cuentaHabilitada) {
		this.cuentaHabilitada = cuentaHabilitada;
	}
	
	public String datosValidos()
	{
		StringBuilder sb= new StringBuilder();
		if(getCliente()==null || getCliente().trim().length()<2)
		{
			sb.append("Nombre erroneo,");
		}
		if(getIdZona()<0)
		{
			sb.append("Id zona erroneo,");
		}
		String mensaje=sb.toString();
		 if(mensaje.length()>0)
		 {
			 mensaje=mensaje.substring(0,mensaje.length()-1); //desde y cuantos caracteres
		 }
		return mensaje;
		 
	}

}
