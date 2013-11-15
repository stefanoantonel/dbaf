package ar.edu.ucc.bda.web.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ar.edu.ucc.bda.web.modelo.PersistenciaException;
import ar.edu.ucc.bda.web.modelo.Usuario;
import ar.edu.ucc.bda.web.utiles.Constantes;

public class NotasDAO implements INotasDAO {

	private Connection cn;

	public NotasDAO() {
		cn=Coneccion.getConnection();
	}


	
	public JSONArray load(String usuario) throws JSONException {
		List<String> descripciones=new ArrayList<>();
//		String sql="SELECT * FROM notas WHERE usuario=?";
		String sql="SELECT * FROM notas WHERE usuarios_id=? ";
		Usuario resultado=null;
		JSONArray notas=new JSONArray();
		try {
			PreparedStatement stm=cn.prepareStatement(sql);
			stm.setString(1,usuario);
			ResultSet rs=stm.executeQuery();
			
				//descripciones.add(rs.getString("nota"));
			while(rs.next()){
				JSONObject nota=agregarJson(rs.getString("id"), rs.getString("titulo"),rs.getString("lista"),rs.getString("fecha_agregada"),rs.getString("fecha_modificada"), rs.getString("cuerpo") );
				notas.put(nota);
			}
			System.out.println();
			
			
			
		} catch (SQLException e) {
			System.out.println("error load solo");
			e.printStackTrace();
//			throw new PersistenciaException(); //relanzo la persistencia con otro nombre
			
		}
		
		//System.out.println("UsuarioDAO: resultado ="+resultado+"");
		return notas;
	}
	
	


	
	@Override
	public JSONArray loadHacer(String usuario) throws JSONException {
		List<String> descripciones=new ArrayList<>();
//		String sql="SELECT * FROM notas WHERE usuario=?";
		String sql="SELECT * FROM notas WHERE (lista is null OR lista=0) AND usuarios_id=? ";
		Usuario resultado=null;
		JSONArray notas=new JSONArray();
		try {
			PreparedStatement stm=cn.prepareStatement(sql);
			stm.setString(1,usuario);
			ResultSet rs=stm.executeQuery();
			
				//descripciones.add(rs.getString("nota"));
			while(rs.next()){
				JSONObject nota=agregarJson(rs.getString("id"), rs.getString("titulo"),rs.getString("lista"),rs.getString("fecha_agregada"),rs.getString("fecha_modificada"), rs.getString("cuerpo") );
				notas.put(nota);
			}
			System.out.println();
			
			
			
		} catch (SQLException e) {
			System.out.println("error load solo");
			e.printStackTrace();
//			throw new PersistenciaException(); //relanzo la persistencia con otro nombre
			
		}
		
		//System.out.println("UsuarioDAO: resultado ="+resultado+"");
		return notas;
	}
	
	public JSONArray loadLista(String usuario) throws JSONException {
		List<String> descripciones=new ArrayList<>();
//		String sql="SELECT * FROM notas WHERE usuario=?";
		String sql="SELECT * FROM notas WHERE usuarios_id=? AND lista=1 ";
		Usuario resultado=null;
		JSONArray notas=new JSONArray();
		try {
			PreparedStatement stm=cn.prepareStatement(sql);
			stm.setString(1,usuario);
			ResultSet rs=stm.executeQuery();
			
				//descripciones.add(rs.getString("nota"));
			while(rs.next()){
				JSONObject nota=agregarJson(rs.getString("id"), rs.getString("titulo"),rs.getString("lista"),rs.getString("fecha_agregada"),rs.getString("fecha_modificada"), rs.getString("cuerpo") );
				notas.put(nota);
			}
			System.out.println();
			
			
			
		} catch (SQLException e) {
			System.out.println("error load solo");
			e.printStackTrace();
//			throw new PersistenciaException(); //relanzo la persistencia con otro nombre
			
		}
		
		//System.out.println("UsuarioDAO: resultado ="+resultado+"");
		return notas;
	}
	
	public JSONArray loadUltima(String usuario) throws JSONException {
		List<String> descripciones=new ArrayList<>();
//		String sql="SELECT * FROM notas WHERE usuario=?";
		String sql="SELECT * FROM notas WHERE id=(	SELECT MAX(id) FROM notas WHERE usuarios_id=?	) ";
		Usuario resultado=null;
		JSONArray notas=new JSONArray();
		try {
			PreparedStatement stm=cn.prepareStatement(sql);
			stm.setString(1,usuario);
			ResultSet rs=stm.executeQuery();
			
				//descripciones.add(rs.getString("nota"));
			while(rs.next()){
				JSONObject nota=agregarJson(rs.getString("id"), rs.getString("titulo"),rs.getString("lista"),rs.getString("fecha_agregada"),rs.getString("fecha_modificada"), rs.getString("cuerpo") );
				notas.put(nota);
			}
			System.out.println();
			
			
			
		} catch (SQLException e) {
			System.out.println("error load solo");
			e.printStackTrace();
//			throw new PersistenciaException(); //relanzo la persistencia con otro nombre
			
		}
		
		//System.out.println("UsuarioDAO: resultado ="+resultado+"");
		return notas;
	}
	
	public JSONArray load(String usuario,String notaParaBuscar) throws JSONException {
		List<String> descripciones=new ArrayList<>();
//		String sql="SELECT * FROM notas WHERE usuario=?";
		String sql="SELECT * FROM notas WHERE usuarios_id=? AND (titulo like '%"+notaParaBuscar+"%' OR cuerpo like '%"+notaParaBuscar+"%')";
		Usuario resultado=null;
		JSONArray notas=new JSONArray();
		try {
			PreparedStatement stm=cn.prepareStatement(sql);
			stm.setString(1,usuario);
			
			System.out.println(sql);
			ResultSet rs=stm.executeQuery();
			//if(rs.next()){
				
			//	JSONObject nota=agregarJson(rs.getString("id"), rs.getString("titulo"),rs.getString("lista"),rs.getString("fecha_agregada"),rs.getString("fecha_modificada"), rs.getString("cuerpo") );
			//	notas.put(nota);
				//descripciones.add(rs.getString("nota"));
			while(rs.next()){
				JSONObject nota=agregarJson(rs.getString("id"), rs.getString("titulo"),rs.getString("lista"),rs.getString("fecha_agregada"),rs.getString("fecha_modificada"), rs.getString("cuerpo") );
				notas.put(nota);
			}
			System.out.println();
			//}
			//else{
				//System.out.println("notas load DAO dio null");
				
			//}
			
		} catch (SQLException e) {
			System.out.println("error load con parametros");
			e.printStackTrace();
//			throw new PersistenciaException(); //relanzo la persistencia con otro nombre
			
		}
		
		//System.out.println("UsuarioDAO: resultado ="+resultado+"");
		return notas;
	}
	public boolean updateTitulo(String titulo,String id){	
		try {
			String sql="UPDATE `practico`.`notas` SET `titulo`=? WHERE `id`=? ";
			PreparedStatement stm=cn.prepareStatement(sql);
			stm.setString(1, titulo);
			stm.setString(2, id);
			stm.executeUpdate();
			return true;
		} 
		catch (SQLException e) {
			System.out.println("error en update titulo notas");
			return false;
		}
	}
	
	public boolean updateCuerpo(String cuerpo,String id){	
		try {
			String sql="UPDATE `practico`.`notas` SET `cuerpo`=? WHERE `id`=? ";
			PreparedStatement stm=cn.prepareStatement(sql);
			stm.setString(1, cuerpo);
			stm.setString(2, id);
			stm.executeUpdate();
			return true;
		} 
		catch (SQLException e) {
			System.out.println("error en update cuerpo notas");
			return false;
		}
	}
	
	public boolean updateLista(String lista, String id){	
		try {
			String sql="UPDATE `practico`.`notas` SET `lista`=? WHERE `id`=? ";
			PreparedStatement stm=cn.prepareStatement(sql);
			stm.setString(1, lista);
			stm.setString(2, id);
			
			System.out.println(sql);
			stm.executeUpdate();
			return true;
		} 
		catch (SQLException e) {
			System.out.println("error en update lista notas");
			return false;
		}
	}

	public boolean insert(String usuario){
		try {
			String sql="INSERT INTO `practico`.`notas`(`titulo`,`cuerpo`,`usuarios_id`,`fecha_agregada`) VALUES('','',?,CURDATE() ) ";
			
			PreparedStatement stm=cn.prepareStatement(sql);
			stm.setString(1, usuario);
			System.out.println(sql);
			stm.executeUpdate();
			return true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("error en insertar notas sola");
			return false;
		}
	}
	public boolean delete(String id){
		try{
			String sql="Delete from `practico`.`notas` where id=? ";
			PreparedStatement stm=cn.prepareStatement(sql);
			stm.setString(1, id);
			System.out.println(sql);
			stm.executeUpdate();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	private JSONObject agregarJson(String id, String titulo,String lista,String fecha_agregada,String fecha_modificada, String cuerpo ) throws JSONException{
		JSONObject nota=new JSONObject();
		nota.put("id",id );
		nota.put("titulo",titulo);
		nota.put("lista", lista);
		nota.put("agregada", fecha_agregada);
		nota.put("modificada", fecha_modificada);
		nota.put("cuerpo", cuerpo);
		return nota;
	}




}
