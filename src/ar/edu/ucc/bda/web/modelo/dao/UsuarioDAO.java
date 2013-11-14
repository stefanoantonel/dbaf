package ar.edu.ucc.bda.web.modelo.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.edu.ucc.bda.web.modelo.PersistenciaException;
import ar.edu.ucc.bda.web.modelo.Usuario;
import ar.edu.ucc.bda.web.utiles.Fecha;

public class UsuarioDAO implements IUsuarioDAO {

//esta clase es la que se va a conectar con la BD
	private Connection cn;
	
	
	public static void main(String [ ] args)
	{
	     
	}
	
	public UsuarioDAO(){
		this.cn=Coneccion.getConnection();
			
	}
	//esto es cargar en memoria al usuario NO agregar
	public Usuario cargar(String nombre,String clave) throws PersistenciaException{
//		INSERT INTO usuarios VALUES('usuario',AES_ENCRYPT('contraseña','llave'),3);
//		SELECT * FROM usuarios where clave=AES_ENCRYPT('contraseña','llave');
		StringBuilder sql=new StringBuilder();
		sql.append("SELECT * FROM usuarios WHERE usuario=? and clave=");
		sql.append(desencriptar(clave));
		
		int estado;
		Usuario resultado=null;
		try {
			PreparedStatement stm=cn.prepareStatement(sql.toString());
			stm.setString(1, nombre);
			//stm.setString(2, clave);
			ResultSet rs=stm.executeQuery();
			if(rs.next()){				
				String nombrep=rs.getString("usuario");
				String clavep=rs.getString("clave");
				String mail=rs.getString("mail");
				String id=rs.getString("id");
				String activada=rs.getString("cuentaActivada");
				String expiracion=rs.getString("fecha_expiracion");
				if(activada.equals("1")){
					estado=1; //todo ok 
					
					//me fijo si expiro la fecha 
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					Fecha f=new Fecha();
					int comparacion=f.compararFecha(dateFormat.format(date), expiracion); //primera anterioir a segunda = -1
					if(comparacion>=0){
						estado=3; //expiro
					}
				}
				else{
					estado=2; //falta activar 
				}

				
				resultado=new Usuario(nombrep,clavep,mail,id);
				resultado.setEstado(estado);
					
				System.out.println("clave en string: "+resultado.getClave());
			}
			else{
				System.out.println("usuario DAO cargar dio null");
				estado=0; //no esta
				resultado=new Usuario("","","","");
				resultado.setEstado(estado);
			}
		} catch (SQLException e) {
			System.out.println("error usuarioDAO");
			e.printStackTrace();
			resultado=new Usuario("","","","");
			estado=-1;
			resultado.setEstado(estado);
			 //error
			//throw new PersistenciaException(); //relanzo la persistencia con otro nombre
			
		}
		
		//System.out.println("UsuarioDAO: resultado ="+resultado+"");
		return resultado;
	}

	
	public boolean eliminar(Usuario usuario) throws PersistenciaException{
	
		return false;
	}

	
	public boolean agregar(Usuario usuario) throws PersistenciaException{
		
		String claveEnc=encriptar(usuario.getClave());
		StringBuilder sql=new StringBuilder();
				sql.append(" INSERT INTO practico.usuarios(usuario,clave,mail,cuentaActivada,fecha_creacion)");
				sql.append(" VALUES (?,");
				sql.append(" "+claveEnc+" ");
				sql.append(",?,'0',CURDATE())");
		Usuario resultado=null;
		try {
			PreparedStatement stm=cn.prepareStatement(sql.toString());
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
		String claveEnc=encriptar(usuario.getClave()); //encripto la clave nueva
		StringBuilder sql=new StringBuilder();
		sql.append(" UPDATE usuarios SET clave= ");
		sql.append(" "+claveEnc+" ");
		sql.append(" ,fecha_expiracion= CURDATE()+ INTERVAL '30' day");
		sql.append(" WHERE usuario=?");
		Usuario resultado=null;
		try {
			PreparedStatement stm=cn.prepareStatement(sql.toString());
			stm.setString(1,usuario.getNombre());
//			String c=encriptar(usuario.getClave());
//			stm.set(2,c);
			//stm.setString(2,usuario.getEmail());
			
			
			System.out.println(stm.toString());
			stm.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("error agregar usuario en usuarioDAO");
			e.printStackTrace();
			return false;
			
		}
		
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
		return "AES_ENCRYPT('pass',"+clave+")";
	}
	
	public Usuario getUsuarioByEmail(String email)
	{
		StringBuilder sql=new StringBuilder();
		sql.append("Select * from usuarios where mail=? ");
		Usuario us=null;
		String usuario="";
		String fecha="";
		String id="";
		String clave="";
		try {
			PreparedStatement stm=cn.prepareStatement(sql.toString());
			stm.setString(1,email);
			System.out.println(stm.toString());
			ResultSet rs=stm.executeQuery();
			while(rs.next()){
				usuario=rs.getString("usuario");
				clave=rs.getString("clave");
				id=rs.getString("id");
				//mail=rs.getString("mail");
				fecha=rs.getString("fecha_creacion");
				
			}
		} catch (SQLException e) {
			System.out.println("error getUsuarioByEmail en usuarioDAO");
			e.printStackTrace();
		}

		us= new Usuario(id,usuario,fecha);
		us.setEmail(email);
		return us;
		
	}

	public boolean activar(Usuario us)
	{
		StringBuilder sql=new StringBuilder();
		sql.append(" update usuarios set cuentaActivada=1, fecha_expiracion=CURDATE()+ INTERVAL '30' day where id=?");
		try {
			PreparedStatement stm=cn.prepareStatement(sql.toString());
			stm.setString(1,us.getId());
			System.out.println(stm.toString());
			stm.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("error activar en usuarioDAO");
			e.printStackTrace();
			return false;
			
		}
	}
	
	public boolean updateCreacion(Usuario us)
	{
		StringBuilder sql=new StringBuilder();
		sql.append(" update usuarios set fecha_creacion=CURDATE()+ INTERVAL '1' day where id=?");
		try {
			PreparedStatement stm=cn.prepareStatement(sql.toString());
			stm.setString(1,us.getId());
			System.out.println(stm.toString());
			stm.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("error activar en usuarioDAO");
			e.printStackTrace();
			return false;
			
		}
	}

	
}
