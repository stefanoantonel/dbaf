package ar.edu.ucc.bda.web.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.ucc.bda.web.modelo.PersistenciaException;
import ar.edu.ucc.bda.web.modelo.Usuario;

public class UsuarioDAO implements IUsuarioDAO {

//esta clase es la que se va a conectar con la BD
	private Connection cn;
	
	public UsuarioDAO(Connection cn){
		this.cn=cn;
			
	}
	//esto es cargar en memoria al usuario NO agregar
	public Usuario cargar(String nombre) throws PersistenciaException{
		
		String sql="SELECT * FROM usuarios WHERE usuario=?";
		Usuario resultado=null;
		try {
			PreparedStatement stm=cn.prepareStatement(sql);
			stm.setString(1,nombre);
			ResultSet rs=stm.executeQuery();
			
			if(rs.next()){
				resultado=new Usuario(rs.getString("usuario"),rs.getString("clave"));
				//System.out.println("todo ok usuarioDAO");
			}
			else{
				System.out.println("usuario DAO dio null");
				
			}
		} catch (SQLException e) {
			System.out.println("error usuarioDAO");
			e.printStackTrace();
			throw new PersistenciaException(); //relanzo la persistencia con otro nombre
			
		}
		
		//System.out.println("UsuarioDAO: resultado ="+resultado+"");
		return resultado;
	}

	
	public boolean eliminar(Usuario usuario) throws PersistenciaException{
	
		return false;
	}

	
	public Usuario agregar(Usuario usuario) throws PersistenciaException{
		
		String sql="INSERT INTO practico.usuarios(usuario,clave) VALUES (?,?)";
		Usuario resultado=null;
		try {
			PreparedStatement stm=cn.prepareStatement(sql);
			stm.setString(1,usuario.getNombre());
			stm.setString(2,usuario.getClave());
			
			ResultSet rs=stm.executeQuery();
			
			if(rs.next()){
				resultado=new Usuario(rs.getString("usuario"),rs.getString("clave"));
				System.out.println("todo ok usuarioDAO");
			}
			else{
				System.out.println("ddsp de ejecutar usuario DAO dio null");
				
			}
		} catch (SQLException e) {
			System.out.println("error usuarioDAO");
			e.printStackTrace();
			throw new PersistenciaException(); //relanzo la persistencia con otro nombre
			
		}
		
		System.out.println("UsuarioDAO: resultado ="+resultado+"");
		return resultado;
	
	}

	
	public boolean modificar(Usuario usuario) throws PersistenciaException{
		// TODO Auto-generated method stub
		return false;
	}

}
