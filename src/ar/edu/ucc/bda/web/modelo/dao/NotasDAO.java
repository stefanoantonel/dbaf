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

public class NotasDAO {

	private Connection cn;

	public NotasDAO(Connection cn) {
		this.cn = cn;
		
	}

//	public List<List<String>> load(String usuario) throws JSONException {
//		List<String> descripciones=new ArrayList<>();
////		String sql="SELECT * FROM notas WHERE usuario=?";
//		String sql="SELECT * FROM notas WHERE usuario_id=?";
//		Usuario resultado=null;
//		List<List<String>> notas=new ArrayList<>();
//		try {
//			PreparedStatement stm=cn.prepareStatement(sql);
//			stm.setString(1,usuario);
//			ResultSet rs=stm.executeQuery();
//			
//			if(rs.next()){
//				descripciones.add(rs.getString("id"));
//				descripciones.add(rs.getString("nota"));
//				notas.add(descripciones);
//				while(rs.next()){
//					descripciones=new ArrayList<>();
//					descripciones.add(rs.getString("id"));
//					descripciones.add(rs.getString("nota"));
//					notas.add(descripciones);
//				}
//				System.out.println();
//			}
//			else{
//				System.out.println("usuario DAO dio null");
//				
//			}
//			
//		} catch (SQLException e) {
//			System.out.println("error usuarioDAO");
//			e.printStackTrace();
////			throw new PersistenciaException(); //relanzo la persistencia con otro nombre
//			
//		}
//		
//		//System.out.println("UsuarioDAO: resultado ="+resultado+"");
//		return notas;
//	}
	
	public JSONArray load(String usuario) throws JSONException {
		List<String> descripciones=new ArrayList<>();
//		String sql="SELECT * FROM notas WHERE usuario=?";
		String sql="SELECT * FROM notas WHERE usuario_id=?";
		Usuario resultado=null;
		JSONArray notas=new JSONArray();
		try {
			PreparedStatement stm=cn.prepareStatement(sql);
			stm.setString(1,usuario);
			ResultSet rs=stm.executeQuery();
			if(rs.next()){
				JSONObject nota=new JSONObject();
				nota.put("id",rs.getString("id") );
				nota.put("value", rs.getString("nota"));
				notas.put(nota);
				//descripciones.add(rs.getString("nota"));
				while(rs.next()){
					nota=new JSONObject();
//					descripciones.add(rs.getString("nota"));
					nota.put("id",rs.getString("id") );
					nota.put("value", rs.getString("nota"));
					notas.put(nota);
				}
				System.out.println();
			}
			else{
				System.out.println("usuario DAO dio null");
				
			}
			
		} catch (SQLException e) {
			System.out.println("error usuarioDAO");
			e.printStackTrace();
//			throw new PersistenciaException(); //relanzo la persistencia con otro nombre
			
		}
		
		//System.out.println("UsuarioDAO: resultado ="+resultado+"");
		return notas;
	}

}
