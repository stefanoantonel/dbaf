package ar.edu.ucc.bda.web.modelo.dao;

import java.sql.Connection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface INotasDAO {
	
	public JSONArray load(String usuario) throws JSONException;
	
	public JSONArray loadUltima(String usuario) throws JSONException;
	
	public JSONArray loadLista(String usuario) throws JSONException;
	public JSONArray loadHacer(String usuario) throws JSONException;
	
	public JSONArray load(String usuario,String notaParaBuscar) throws JSONException;
	
	public boolean updateTitulo(String titulo,String id);
	
	public boolean updateCuerpo(String cuerpo,String id);
	
	public boolean updateLista(String lista, String id);

	public boolean insert(String usuario);
	
	public boolean delete(String id);

	
	
	
}
