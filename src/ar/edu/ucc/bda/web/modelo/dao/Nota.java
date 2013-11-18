package ar.edu.ucc.bda.web.modelo.dao;

import org.json.JSONArray;

public class Nota {

	JSONArray arrayNota;
	NotasDAO dao;
	public Nota(){
		dao=new NotasDAO();
		arrayNota =new JSONArray();
	}
	public void cambiarFecha(String fecha,String id){
		dao.cambiarFecha(fecha, id);
	}
	
	public int esVencida(String fecha)
	{
		return dao.esVencida(fecha);
	}
	
	
	
	
}
