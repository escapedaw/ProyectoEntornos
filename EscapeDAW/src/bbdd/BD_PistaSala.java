package bbdd;

import java.sql.*;
import java.util.Vector;

import modelos.Empleado;
import modelos.Jefe;
import modelos.Pista;
import modelos.Sala;
/**
 * 
 * @author Sandra
 *
 */
public class BD_PistaSala extends BD_Conector{
	private static Statement s;	
	private static ResultSet reg;
	private int nsala;
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
		String cadenaSQL="INSERT INTO salas VALUES('" + sa.getNsala() + "'," + sa.getIdEmple() + ",'" +
		sa.getIdJefe() + "','" + sa.getTipo() + "','" + sa.getDificultad() + "'," +
				sa.getNPersonas()+ "," + sa.getPrecio() + ")";
		
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
		String cadenaSQL="UPDATE salas SET PRECIO = " + sa.getPrecio() + " WHERE NSALA = '" + sa.getNsala() + "'";
		
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
	 * Metodo que modifica el id del empleado
	 * @param sa
	 * @return numero de filas, -1 si hay una excepcion
	 */
	
	public  int modificarIdEmple (String idEmple, String nSala){	
		String cadenaSQL="UPDATE salas SET ID_EMPLE = '" + idEmple + "' WHERE NSALA = '" + nSala + "'";
		
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
	
	public int borrarSala (String nSala){
		String cadena="DELETE FROM salas WHERE NSALA='" +  nSala + "'";	
		
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
	
	/**
	 * Metodo que modifica la descripcion de una pista de la bbdd
	 * @param pi
	 * @returnnumero de filas si se modifica, -1 si no se puede
	 */
	
	public  int modificarDescripcion (String descripcion){	
		String cadenaSQL="UPDATE pistas SET DESCRIPCION='" + descripcion +"'";
		
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
		String cadena="DELETE FROM pistas WHERE codPista='" + pi.getCod_pista() + "'";	
		
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
	 * @throws TecnicaExcepcion 
	 */
	
	public  Vector<Pista> listarPistas () { 
		String cadenaSQL="SELECT * from pistas";
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
		catch ( SQLException e){			
			return null;				
		}

	}
	
	/**
	 * Metodo que busca la sala en la que se encuentra un cliente
	 * @param nif
	 * @return nsala si la encuentra, 0 si no tiene sala
	 */
	public int buscarSala (String nif) {
		String cadenaSQL="SELECT NSALA FROM reservas WHERE NIF_CLIENTE = '" + nif +  "'";
		
		try{
		this.abrir();
		s=c.createStatement();
		reg=s.executeQuery(cadenaSQL);
		if (reg.next()){
			nsala=reg.getInt("NSALA");
		}
		s.close();
		this.cerrar();
		return nsala;
		}
		catch (SQLException e){			
			return 0;
		}
	}
	
	/**
	 * Metodo que cambia a true la variable solicitado (Pista) cuando el cliente pide una pista
	 * @return true si se ha ejecutado bien, false si ha dado fallo
	 */

	public boolean pedirPista (String nif) {
		nsala = buscarSala(nif);
		
		String cadenaSQL = "SELECT * FROM pistas WHERE NSALA = " + nsala + " AND SOLICITADO = 0";
		
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			if (reg.next()){
				cadenaSQL="UPDATE pistas SET SOLICITADO = 1 where NSALA = " + nsala ;
				
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
		}
		catch (SQLException e){		
			return false;			
		}
		return false;
	} 
	
	/**
	 * Metodo que busca las pistas solicitadas
	 * @return listaSolicitadas
	 */
	
	public Vector <Pista> pistasSolicitadas () {
		String cadenaSQL = "SELECT COD_PISTA, NSALA, DESCRIPCION FROM pistas WHERE SOLICITADO = 1";
		
		Vector<Pista> listaSolicitadas=new Vector<Pista>();
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			if (reg.next()){
				listaSolicitadas.add(new Pista(reg.getString("COD_PISTA"),reg.getString("NSALA"),reg.getString("DESCRIPCION")));		
			}
			s.close();
			this.cerrar();
			return listaSolicitadas;
		}
		catch (SQLException e){		
			return null;			
		}
	}
	
	/**
	 * Metodo que confirma la pista seleccionada
	 * @param cod_pista
	 * @return true si se ha ejecutado, false si no
	 */
	
	public boolean confirmarPista (String cod_pista) {
		String cadenaSQL;
		cadenaSQL="SELECT * from pistas WHERE SOLICITADO = 1 AND COD_PISTA = '" + cod_pista + "'";
		
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while (reg.next()){
				cadenaSQL="UPDATE pistas SET CONFIRMADO = 1";
				
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
	 * @throws TecnicaExcepcion 
	 */
	// Modificar para que de la pista correspondiente, ahora mismo da todas las confirmadas
	public Vector <Pista> pistasConfirmadas () {
		String cadenaSQL = "SELECT NSALA, COD_PISTA, DESCRIPCION FROM pistas WHERE CONFIRMADO = 1";
		
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
		catch ( SQLException e){			
			return null;				
		}
		/*catch (ClassNotFoundException e){
			throw new TecnicaExcepcion("En este momento no podemos acceder a la base de datos");
		}*/
	}
	
	public void darPista (String cod_pista) {
		confirmarPista(cod_pista);
		Vector <Pista> confirmadas = pistasConfirmadas();
		for (Pista pista : confirmadas){
			if (pista.getCod_pista().equalsIgnoreCase(cod_pista))
				System.out.println("Pista: " + pista.getDescripcion());
		}
	}
	
	public boolean reiniciarJuego () {
		String cadenaSQL;
		cadenaSQL="SELECT * from pistas WHERE (SOLICITADO = 1 AND CONFIRMADO = 1) OR (SOLICITADO = 1 AND CONFIRMADO = 0)";
		
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while (reg.next()){
				cadenaSQL="UPDATE pistas SET SOLICITADO = 0, CONFIRMADO = 0";
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
	
}