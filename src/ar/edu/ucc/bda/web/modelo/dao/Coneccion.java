package ar.edu.ucc.bda.web.modelo.dao;

import java.sql.Connection;

public class Coneccion {
	
	private static Connection cn;
	
	public Coneccion (Connection c)
	{
		cn= c; 
	}
	
	
	public static Connection getConnection()
	{
		return cn;
	}

}
