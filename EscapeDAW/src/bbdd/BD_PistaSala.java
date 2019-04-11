package bbdd;

import java.sql.*;
import java.util.Vector;
import modelos.Sala;

public class BD_PistaSala extends BD_Conector{
	private static Statement s;	
	private static ResultSet reg;
	
	public BD_PistaSala() {
		super();
	}
	
	// SALAS
	
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
		catch ( SQLException e){
			this.cerrar();
			return -1;
		}
	}
	
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
		catch ( SQLException e){		
			return null;			
		}
	}
	
	// PISTAS
	
	public  int crearPista(Sala sa){	
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
		catch ( SQLException e){
			this.cerrar();
			return -1;
		}
	}
	
	public  Vector<Sala> listarSalas (){ 
		String cadenaSQL="SELECT * from salas";
		Vector<Sala> listaSalas=new Vector<Sala>();
		try{
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadenaSQL);
			while ( reg.next()){
				listaSalas.add(new Sala(reg.getString("ID_EMPLE"),reg.getString("ID_JEFE"),reg.getString("TIPO"),reg.getString("DIFICULTAD"),reg.getInt("NPERSONAS"),reg.getInt("PRECIO")));
				
			}
			s.close();
			this.cerrar();
			return listaSalas;
		}
		catch ( SQLException e){		
			return null;			
		}
	}
	
	
}
