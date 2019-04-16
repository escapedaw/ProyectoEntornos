package bbdd;

import java.sql.*;
import java.util.Vector;

import modelos.Pista;
import modelos.Sala;

public class BD_PistaSala extends BD_Conector{
	private static Statement s;	
	private static ResultSet reg;
	
	public BD_PistaSala() {
		super();
	}
	
	// SALAS
	/**
	 * Metodo que introduce los datos de un objeto sala en la bbdd
	 * @param sa
	 * @return numero de filas si se crea, -1 si no se crea
	 */
	
	public  int crearSala(Sala sa){	
		String cadenaSQL="INSERT INTO salas VALUES('" + sa.getNsala() + "','" + sa.getIdEmple() + "','" +
		sa.getIdJefe() + "','" + sa.getTipo() + "'," + sa.getDificultad() + ",'" +
				sa.getNPersonas()+ "','" + sa.getPrecio() + "')";
		
		try{
		this.abrir();
		s=c.createStatement();
		int filas=s.executeUpdate(cadenaSQL);
		s.close();
		this.cerrar();
		return filas;
		}
		catch (SQLException e){			
			return -1;
		}
	}
	
	/**
	 * Metodo que modifica el precio de una sala en la bbdd
	 * @param sa
	 * @return numero de filas si se modifica, -1 si no se puede
	 */
	
	public  int modificarPrecio (Sala sa){	
		String cadenaSQL="UPDATE salas SET PRECIO=" + sa.getPrecio() +"')";
		
		try{
		this.abrir();
		s=c.createStatement();
		int filas=s.executeUpdate(cadenaSQL);
		s.close();
		this.cerrar();
		return filas;
		}
		catch (SQLException e){			
			return -1;
		}
	}
	
	/**
	 * Metodo que elimina una sala en concreto de la bbdd
	 * @param sa
	 * @return numero de filas si se elimina, -1 si no se puede
	 */
	
	public int borrarSala (Sala sa){
		String cadena="DELETE FROM salas WHERE NSALA='" +  sa.getNsala() + "'";	
		
		try{
		this.abrir();
		s=c.createStatement();
		int filas=s.executeUpdate(cadena);	
		s.close();
		this.cerrar();
		return filas;
		
		}
		catch (SQLException e){
			this.cerrar();
			return -1;
		}
	}
	
	/**
	 * Metodo que busca todos los datos de la tabla salas y los introduce en un vector
	 * @return vector de salas o null si salta una excepción
	 */
	
	public  Vector<Sala> listarSalas (){ 
		String cadenaSQL="SELECT * from salas";
		Vector<Sala> listaSalas=new Vector<Sala>();
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while ( reg.next()){
				listaSalas.add(new Sala(reg.getString("NSALA"),reg.getString("ID_EMPLE"),reg.getString("ID_JEFE"),reg.getString("TIPO"),reg.getString("DIFICULTAD"),reg.getInt("NPERSONAS"),reg.getInt("PRECIO")));
				
			}
			s.close();
			this.cerrar();
			return listaSalas;
		}
		catch (SQLException e){		
			return null;			
		}
	}
	
	// PISTAS
	
	/**
	 * Metodo que introduce los datos de un objeto pista en la bbdd
	 * @param pi
	 * @return numero de filas si se crea, -1 si no se puede
	 */
	
	public  int crearPista(Pista pi){	
		String cadenaSQL="INSERT INTO pistas VALUES('" + pi.getCod_pista() + "','" + pi.getNsala() + "','" +
		pi.getDescripcion() + "','" + pi.getSolicitado() + "'," + pi.getConfirmado() + "')";
		
		try{
		this.abrir();
		s=c.createStatement();
		int filas=s.executeUpdate(cadenaSQL);
		s.close();
		this.cerrar();
		return filas;
		}
		catch (SQLException e){			
			return -1;
		}
	}
	/*
	 Se supone que en el objeto que le paso ya esta cambiada la descripcion o hay que pasarle la desc nueva??
	 */
	
	/**
	 * Metodo que modifica la descripcion de una pista de la bbdd
	 * @param pi
	 * @returnnumero de filas si se modifica, -1 si no se puede
	 */
	
	public  int modificarDescripciom(String descripcion){	
		String cadenaSQL="UPDATE pistas SET DESCRIPCION='" + descripcion +"')";
		
		try{
		this.abrir();
		s=c.createStatement();
		int filas=s.executeUpdate(cadenaSQL);
		s.close();
		this.cerrar();
		return filas;
		}
		catch (SQLException e){			
			return -1;
		}
	}
	
	/**
	 * Metodo que elimina una pista de la bbdd
	 * @param pi
	 * @return numero de filas si se elimina, -1 si no se puede
	 */
	
	public int borrarPista (Pista pi){
		String cadena="DELETE FROM pista WHERE codPista='" + pi.getCod_pista() + "'";	
		
		try{
		this.abrir();
		s=c.createStatement();
		int filas=s.executeUpdate(cadena);	
		s.close();
		this.cerrar();
		return filas;
		
		}
		catch (SQLException e){
			this.cerrar();
			return -1;
		}
	}
	
	/**
	 * Metodo que busca todos los datos de la tabla salas y los introduce en un vector
	 * @return vector de pistas o null si salta una excepcion
	 */
	
	public  Vector<Pista> listarPistas (){ 
		String cadenaSQL="SELECT * from pista";
		Vector<Pista> listaPistas=new Vector<Pista>();
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while (reg.next()){
				listaPistas.add(new Pista(reg.getString("COD_PISTA"),reg.getString("NSALA"),reg.getString("DESCRIPCION"),reg.getBoolean("SOLICITADO"),reg.getBoolean("CONFIRMADO")));
				
			}
			s.close();
			this.cerrar();
			return listaPistas;
		}
		catch (SQLException e){		
			return null;			
		}
	}
	
	/**
	 * Metodo que cambia a true la variable solicitado (Pista) cuando el cliente pide una pista
	 * @return true si se ha ejecutado bien, false si ha dado fallo
	 */

	public boolean pedirPista (String cod_pista) {
		String cadenaSQL="UPDATE pistas SET SOLICITADO = TRUE where COD_PISTA = '" + cod_pista +  "')";
		
		try{
		this.abrir();
		s=c.createStatement();
		s.executeUpdate(cadenaSQL);
		s.close();
		this.cerrar();
		return true;
		}
		catch (SQLException e){			
			return false;
		}	
	} 
	
	/*
	 De momento se confirman todas, ha que confirmar solo una en concreto?
	*/
	/**
	 * Metodo que compreba si alguna variable solicitado esta en true y cambia la variable confirmado a true 
	 * @return true si se ha ejecutado bien, false si ha dado fallo
	 */
	
	public boolean confirmarPista () {
		String cadenaSQL;
		cadenaSQL="SELECT SOLICITADO from pista WHERE SOLICITADO = TRUE";
		
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while (reg.next()){
				cadenaSQL="UPDATE pistas SET CONFIRMADO = TRUE)";
				
				try{
				this.abrir();
				s=c.createStatement();
				s.executeUpdate(cadenaSQL);
				s.close();
				this.cerrar();
				return true;
				}
				catch (SQLException e){			
					return false;
				}	
				
			}
			s.close();
			this.cerrar();
			return true;
		}
		catch (SQLException e){		
			return false;			
		}
	}
	
	/**
	 * Metodo que comprueba la variable confirmado y devuelve la descripcion de una pista si esta a true
	 * @param pi
	 * @return String descripcion
	 */
	// Modificar para que de la pista correspondiente, ahora mismo da todas las confirmadas
	public Vector <Pista> pistasConfirmadas () {
		String cadenaSQL = "SELECT NSALA, COD_PISTA, DESCRIPCION FROM pistas WHERE CONFIRMADO = TRUE";
		
		Vector<Pista> listaConfirmadas=new Vector<Pista>();
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while (reg.next()){
				listaConfirmadas.add(new Pista(reg.getString("COD_PISTA"),reg.getString("NSALA"),reg.getString("DESCRIPCION")));
				
			}
			s.close();
			this.cerrar();
			return listaConfirmadas;
		}
		catch (SQLException e){		
			return null;			
		}
	}
	
	public String darPista (String numSala) {
		Vector <Pista> confirmadas = pistasConfirmadas();
		for (Pista pista : confirmadas){
			if (pista.getNsala().equalsIgnoreCase(numSala))
				System.out.println("Pista: " + pista.getDescripcion());
		}
		return null;
	}
}























