package ar.edu.ucc.bda.web.modelo.dao;

import ar.edu.ucc.bda.web.modelo.PersistenciaException;
import ar.edu.ucc.bda.web.modelo.Usuario;

public interface IUsuarioDAO {
	
	//pongo todo lo que quiero que se pueda hacer con las BD
	
	
	public Usuario cargar(String nombre) throws PersistenciaException;
	//public Usuario cargar(String nombre,String clave) throws PersistenciaException;
	public Usuario cargar(String nombre,String clave) throws PersistenciaException;
	
	public boolean eliminar (Usuario usuario)throws PersistenciaException;
	public boolean agregar(Usuario usuario)throws PersistenciaException;
	public boolean modificar(Usuario usuario)throws PersistenciaException;
	public String encriptar(String clave);
	public String desencriptar(String clave);
	public Usuario getUsuarioByEmail (String email);
	public boolean activar(Usuario us)throws PersistenciaException;
	public boolean updateCreacion(Usuario us);
}
