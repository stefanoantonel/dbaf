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
	
	
	public static void main(String [ ] args)
	{
	     
	}
	
	public UsuarioDAO(Connection cn){
		this.cn=cn;
			
	}
	//esto es cargar en memoria al usuario NO agregar
	public Usuario cargar(String nombre,String clave) throws PersistenciaException{
//		INSERT INTO usuarios VALUES('usuario',AES_ENCRYPT('contraseņa','llave'),3);
//		SELECT * FROM usuarios where clave=AES_ENCRYPT('contraseņa','llave');
		String sql="SELECT * FROM usuarios WHERE usuario=? and clave=AES_ENCRYPT('pass',?)";
		
		Usuario resultado=null;
		try {
			PreparedStatement stm=cn.prepareStatement(sql);
			stm.setString(1,nombre);
			stm.setString(2, clave);
			
			ResultSet rs=stm.executeQuery();
			
			if(rs.next()){
//				String nom, String cla,String mail)
				resultado=new Usuario(rs.getString("usuario"),rs.getString("clave"),rs.getString("mail"),rs.getString("id"));
				
				System.out.println("clave en string: "+resultado.getClave());
			}
			else{
				System.out.println("usuario DAO cargar dio null");
				
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

	
	public boolean agregar(Usuario usuario) throws PersistenciaException{
		
		String sql="INSERT INTO practico.usuarios(usuario,clave,mail,cuentaActivada,fecha_creacion) VALUES (?,AES_ENCRYPT('pass','"+usuario.getClave()+"'),?,'0',CURDATE())";
		Usuario resultado=null;
		try {
			PreparedStatement stm=cn.prepareStatement(sql);
			stm.setString(1,usuario.getNombre());
//			String c=encriptar(usuario.getClave());
//			stm.set(2,c);
			stm.setString(2,usuario.getEmail());
			
			
			System.out.println(stm.toString());
			stm.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("error agregar usuario en usuarioDAO");
			e.printStackTrace();
			return false;
			
		}
	}

	
	public boolean modificar(Usuario usuario) throws PersistenciaException{
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Usuario cargar(String nombre) throws PersistenciaException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String encriptar(String clave) {
		return "AES_ENCRYPT('pass','"+clave+"')";
	}
	@Override
	public String desencriptar(String clave) {
		// TODO Auto-generated method stub
		return null;
	}

}
