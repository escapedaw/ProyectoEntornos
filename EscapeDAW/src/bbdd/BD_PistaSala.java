package bbdd;

import java.sql.*;
import java.util.Vector;

import modelos.Empleado;
import modelos.Pista;
import modelos.Sala;

/**
 * Clase que contiene todos los métodos que acceden a la tablas "Pistas" y
 * "Salas" de la base de datos. Se realizan las tareas de crear, modificar y
 * eliminar en ambas tablas.
 * 
 * @author Sandra Lobón
 *
 */
public class BD_PistaSala extends BD_Conector {
	private static Statement s;
	private static ResultSet reg;
	private int nsala;

	public BD_PistaSala() {
		super();
	}

	// SALAS
	/**
	 * Metodo que introduce los datos de un objeto sala en la bbdd
	 * 
	 * @param sa objeto de tipo sala
	 * @return numero de filas que se insertan. 1 si se inserta una fila, 0 si no se
	 *         inserta ninguna fila y -1 si hay algún error
	 */

	public int crearSala(Sala sa) {
		String cadenaSQL = "INSERT INTO salas VALUES('" + sa.getNsala() + "'," + sa.getIdEmple() + ",'" + sa.getIdJefe()
				+ "','" + sa.getTipo() + "','" + sa.getDificultad() + "'," + sa.getNPersonas() + "," + sa.getPrecio()
				+ ")";

		try {
			this.abrir();
			s = c.createStatement();
			int filas = s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return filas;
		} catch (SQLException e) {
			return -1;
		}
	}

	/**
	 * Metodo que modifica el precio de una sala en la bbdd
	 * 
	 * @param sa
	 *            Objetido de tipo sala que se quiere modificar
	 * @return numero de filas si se modifica, -1 si no se puede
	 */

	public int modificarPrecio(Sala sa) {
		String cadenaSQL = "UPDATE salas SET PRECIO = " + sa.getPrecio() + " WHERE NSALA = '" + sa.getNsala() + "'";

		try {
			this.abrir();
			s = c.createStatement();
			int filas = s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return filas;
		} catch (SQLException e) {
			return -1;
		}
	}

	/**
	 * Método que modifica el ID_emple de la sala
	 * 
	 * @param idEmple
	 *            Id del empleado que queremos añadir o modificar
	 * @param nSala
	 *            Número de la sala
	 * @return numero de filas si se modifica, -1 si no se puede
	 */

	public int modificarIdEmple(String idEmple, String nSala) {
		String cadenaSQL = "UPDATE salas SET ID_EMPLE = '" + idEmple + "' WHERE NSALA = '" + nSala + "'";

		try {
			this.abrir();
			s = c.createStatement();
			int filas = s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return filas;
		} catch (SQLException e) {
			return -1;
		}
	}

	/**
	 * Metodo que elimina una sala en concreto de la bbdd
	 * 
	 * @param nSala
	 *            Número de sala que se desea eliminar
	 * @return numero de filas si se elimina, -1 si no se puede
	 */
	public int borrarSala(String nSala) {
		String cadena = "DELETE FROM salas WHERE NSALA='" + nSala + "'";

		try {
			this.abrir();
			s = c.createStatement();
			int filas = s.executeUpdate(cadena);
			s.close();
			this.cerrar();
			return filas;

		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}
	}

	/**
	 * Metodo que busca todos los datos de la tabla salas y los introduce en un
	 * vector
	 * 
	 * @return vector de salas o null si salta una excepción
	 */

	public Vector<Sala> listarSalas() {
		String cadenaSQL = "SELECT * from salas";
		Vector<Sala> listaSalas = new Vector<Sala>();
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while (reg.next()) {
				listaSalas.add(new Sala(reg.getString("NSALA"), reg.getString("ID_EMPLE"), reg.getString("ID_JEFE"),
						reg.getString("TIPO"), reg.getString("DIFICULTAD"), reg.getInt("NPERSONAS"),
						reg.getInt("PRECIO")));
			}
			s.close();
			this.cerrar();
			return listaSalas;
		} catch (SQLException e) {
			return null;
		}
	}

	// PISTAS

	/**
	 * Metodo que introduce los datos de un objeto pista en la bbdd
	 * 
	 * @param pi
	 *            Objeto de tipo pista
	 * @return numero de filas si se crea, -1 si no se puede
	 */

	public int crearPista(Pista pi) {
		String cadenaSQL = "INSERT INTO pistas VALUES('" + pi.getCod_pista() + "','" + pi.getNsala() + "','"
				+ pi.getDescripcion() + "'," + pi.getSolicitado() + "," + pi.getConfirmado() + ")";

		try {
			this.abrir();
			s = c.createStatement();
			int filas = s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return filas;
		} catch (SQLException e) {
			return -1;
		}
	}

	/**
	 * Metodo que modifica la descripcion de una pista de la bbdd
	 * 
	 * @param descripcion
	 *            Nueva descripción de la pista
	 * @param codP
	 *            Código de la pista que se desea modificar
	 * @return numero de filas si se modifica, -1 si no se puede
	 */
	public int modificarDescripcion(String descripcion, String codP) {
		String cadenaSQL = "UPDATE pistas SET DESCRIPCION='" + descripcion + "' WHERE COD_PISTA='" + codP + "';";

		try {
			this.abrir();
			s = c.createStatement();
			int filas = s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return filas;
		} catch (SQLException e) {
			return -1;
		}
	}

	/**
	 * Metodo que elimina una pista de la bbdd
	 * 
	 * @param codP
	 *            Codigo de la pista que se desea borrar
	 * @return numero de filas si se elimina, -1 si no se puede
	 */
	public int borrarPista(String codP) {
		String cadena = "DELETE FROM pistas WHERE COD_PISTA='" + codP + "';";

		try {
			this.abrir();
			s = c.createStatement();
			int filas = s.executeUpdate(cadena);
			s.close();
			this.cerrar();
			return filas;

		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}
	}

	/**
	 * Metodo que busca todos los datos de la tabla pistas y los introduce en un
	 * vector
	 * 
	 * @return vector de pistas o null si salta una excepcion
	 */

	public Vector<Pista> listarPistas() {
		String cadenaSQL = "SELECT * from pistas";
		Vector<Pista> listaPistas = new Vector<Pista>();
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while (reg.next()) {
				listaPistas.add(new Pista(reg.getString("COD_PISTA"), reg.getString("NSALA"),
						reg.getString("DESCRIPCION"), reg.getBoolean("SOLICITADO"), reg.getBoolean("CONFIRMADO")));

			}
			s.close();
			this.cerrar();
			return listaPistas;
		} catch (SQLException e) {
			return null;
		}

	}

	/**
	 * Metodo que busca todos los datos de la tabla pistas de una sala determinada y
	 * los introduce en un vector
	 * 
	 * @param nsala
	 *            numero de la sala de la que se desean buscar las pistas
	 * @return vector de pistas o null si salta una excepcion
	 */
	public Vector<Pista> listarPistasSala(String nsala) {
		String cadenaSQL = "SELECT * from pistas WHERE NSALA='" + nsala + "' AND SOLICITADO=0;";
		Vector<Pista> listaPistas = new Vector<Pista>();
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while (reg.next()) {
				listaPistas.add(new Pista(reg.getString("COD_PISTA"), reg.getString("NSALA"),
						reg.getString("DESCRIPCION"), reg.getBoolean("SOLICITADO"), reg.getBoolean("CONFIRMADO")));

			}
			s.close();
			this.cerrar();
			return listaPistas;
		} catch (SQLException e) {
			return null;
		}

	}

	/**
	 * Metodo que cambia a true la variable solicitado (Pista) cuando el cliente
	 * pide una pista
	 * 
	 * @param codP
	 *            codigo de la pista a modificar
	 * @return true si se ha ejecutado bien, false si ha dado fallo
	 */
	public boolean pedirPista(String codP) {

		String cadenaSQL = "UPDATE pistas SET SOLICITADO = 1 where COD_PISTA = '" + codP + "';";

		try {
			this.abrir();
			s = c.createStatement();
			s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Metodo que busca todos los datos de la tabla pistas de una sala determinada y
	 * los introduce en un vector. Es un método para los clientes pues solo añade
	 * las que han sido solicitadas pero no confirmadas.
	 * 
	 * @param nsala
	 *            numero de la sala de la que se desean buscar las pistas
	 * @return vector de pistas o null si salta una excepcion
	 */
	public Vector<Pista> listarPistasSalaCliente(String nsala) {
		String cadenaSQL = "SELECT * from pistas WHERE NSALA='" + nsala + "' AND SOLICITADO=1 AND CONFIRMADO=0;";
		Vector<Pista> listaPistas = new Vector<Pista>();
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while (reg.next()) {
				listaPistas.add(new Pista(reg.getString("COD_PISTA"), reg.getString("NSALA"),
						reg.getString("DESCRIPCION"), reg.getBoolean("SOLICITADO"), reg.getBoolean("CONFIRMADO")));

			}
			s.close();
			this.cerrar();
			return listaPistas;
		} catch (SQLException e) {
			return null;
		}

	}

	/**
	 * Metodo que busca las pistas solicitadas pero que no han sido aún confirmadas
	 * 
	 * @return vector de pistas solicitadas o null si salta una excepcion
	 */

	public Vector<Pista> pistasSolicitadas() {
		String cadenaSQL = "SELECT COD_PISTA, NSALA, DESCRIPCION FROM pistas WHERE SOLICITADO = 1 AND CONFIRMADO = 0";

		Vector<Pista> listaSolicitadas = new Vector<Pista>();
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while (reg.next()) {
				listaSolicitadas.add(
						new Pista(reg.getString("COD_PISTA"), reg.getString("NSALA"), reg.getString("DESCRIPCION")));
			}
			s.close();
			this.cerrar();
			return listaSolicitadas;
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Metodo que confirma la pista seleccionada
	 * 
	 * @param cod_pista
	 *            codigo de pista
	 * @return true si se ha ejecutado, false si no
	 */

	public boolean confirmarPista(String cod_pista) {
		String cadenaSQL;
		cadenaSQL = "SELECT * from pistas WHERE COD_PISTA = '" + cod_pista + "'";

		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while (reg.next()) {
				cadenaSQL = "UPDATE pistas SET CONFIRMADO = 1 WHERE COD_PISTA = '" + cod_pista + "'";

				try {
					this.abrir();
					s = c.createStatement();
					s.executeUpdate(cadenaSQL);
					s.close();
					this.cerrar();
					return true;
				} catch (SQLException e) {
					return false;
				}

			}
			s.close();
			this.cerrar();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * Metodo que comprueba la variable confirmado y devuelve la descripcion de una
	 * pista si esta a true
	 * 
	 * @param nsala
	 *            número de la sala
	 * @return vector de pistas confirmadas o null si salta una excepcion
	 */
	public Vector<Pista> pistasConfirmadas(String nsala) {
		String cadenaSQL = "SELECT NSALA, COD_PISTA, DESCRIPCION FROM pistas WHERE NSALA='" + nsala
				+ "' AND CONFIRMADO = 1";

		Vector<Pista> listaConfirmadas = new Vector<Pista>();
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while (reg.next()) {
				listaConfirmadas.add(
						new Pista(reg.getString("COD_PISTA"), reg.getString("NSALA"), reg.getString("DESCRIPCION")));

			}
			s.close();
			this.cerrar();
			return listaConfirmadas;
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Método que resetea las pistas de una sala determinada poniendo a false el
	 * campo de solicitado y confirmado
	 * 
	 * @param nsala
	 *            numero de sala que se desea resetear sus pistas
	 * @return devuelve el número de filas modificadas. -1 en caso de que salte
	 *         excepción
	 */
	public int reiniciarJuego(String nsala) {
		String cadenaSQL;
		int filas = 0;
		cadenaSQL = "SELECT * from pistas WHERE ((SOLICITADO = 1 AND CONFIRMADO = 1) OR (SOLICITADO = 1 AND CONFIRMADO = 0)) AND NSALA='"
				+ nsala + "';";

		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			while (reg.next()) {
				cadenaSQL = "UPDATE pistas SET SOLICITADO = 0, CONFIRMADO = 0 WHERE NSALA='" + nsala + "';";
				try {
					this.abrir();
					s = c.createStatement();
					filas += s.executeUpdate(cadenaSQL);
					s.close();
					this.cerrar();
				} catch (SQLException e) {
					return -1;
				}
				return filas;
			}
			s.close();
			this.cerrar();
		} catch (SQLException e) {
			return -1;
		}
		return 0;
	}

}