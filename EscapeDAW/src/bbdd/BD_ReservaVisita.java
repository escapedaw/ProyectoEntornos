package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;

import modelos.Reserva;
import modelos.Visita;

public class BD_ReservaVisita extends BD_Conector{
	private static Statement s;		
	private static ResultSet reg;
	
public BD_ReservaVisita(){
	super ();
}
	

/*no me deja por las foreign keys ?¿ 		*/
public  int añadirReserva(Reserva re){
	String cadena="INSERT INTO reservas VALUES('" + re.getCod_reserva() + "','" + re.getFecha()+"','" + re.getnSala()+"','"+ re.getId_emple()+"','" + re.getNif_cliente()+"','"+ re.getNpersonas()+"','" + re.getImporte()+"')"; 	
	
	try{
	this.abrir();
	s=c.createStatement();
	s.executeUpdate(cadena);
	s.close();
	this.cerrar();
	return 1;
	}
	catch ( SQLException e){
		this.cerrar();
		return -1;
	}
	
}
/* esto es para que te coja el empleado asociado al cliente   */
public String selectEmplePorDNI(String dni){
	String emple=" ";
	String cadena="SELECT id_emple FROM CLIENTES WHERE nif='" + dni + "'";
	try{														
		this.abrir();
		s=c.createStatement();
		reg=s.executeQuery(cadena);
		if ( reg.next()){			
			emple=reg.getString("Id_emple");
		}		
		s.close();
		this.cerrar();
		return emple;
	}
	catch ( SQLException e){
		return null;
	}
}


public int selectPrecio(String numsala){
	int precio=0;
	String cadena="SELECT Precio FROM SALAS WHERE nsala='" + numsala + "'";
	try{														
		this.abrir();
		s=c.createStatement();
		reg=s.executeQuery(cadena);
		if ( reg.next()){			
			 precio=reg.getInt("Precio");
		}		
		s.close();
		this.cerrar();
		return precio;
	}
	catch ( SQLException e){
		return 0;
	}
}

public double selectNpersonas(String nif_cliente){
	int npersonas=0;
	String cadena="SELECT npersonas FROM reservas WHERE nif_cliente='" + nif_cliente + "'";
	try{														
		this.abrir();
		s=c.createStatement();
		reg=s.executeQuery(cadena);
		if ( reg.next()){			
			npersonas=reg.getInt("npersonas");
		}		
		s.close();
		this.cerrar();
		return npersonas;
	}
	catch ( SQLException e){
		return 0;
	}
}



public double updateImporte(double importe, int numsala){
	String cadena="UPDATE reservas SET importe =" + importe + "  WHERE nsala ='" + numsala + "'";
	try{
		this.abrir();
		s=c.createStatement();
		int filas=s.executeUpdate(cadena);
		s.close();
		this.cerrar();
		return filas;
		}
		catch ( SQLException e){			
			return -1;
		}
}




public Vector<Reserva>  mostrarReservas(){
	Vector<Reserva> v=new Vector<Reserva>();
	String cadena="SELECT * FROM reservas ";
	try{
		this.abrir();
		s=c.createStatement();
		reg=s.executeQuery(cadena);
		while ( reg.next()){					
			java.sql.Date f=reg.getDate("fecha");
			LocalDate fBuena=f.toLocalDate();
			 v.add(new Reserva(reg.getString("cod_reserva"),fBuena,reg.getString("nsala"),reg.getString("id_emple"),reg.getString("nif_cliente"),reg.getInt("npersonas"),reg.getInt("importe")));			
		}		
		s.close();
		this.cerrar();
		return v;
	}
	catch ( SQLException e){
		return null;
	}
}

public int eliminarReserva(String cod_reserva) {
	String cadena = "DELETE FROM reservas WHERE cod_reserva='" + cod_reserva + "'";

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




public  boolean añadirVisita(Visita vi){
	String cadena="INSERT INTO visitas VALUES('" + vi.getNsala() + "','" + vi.getId_emple()+"','" + vi.getNif_cliente()+"','" + vi.getFecha()+"','" + vi.getTiempo()+"')"; 	
	
	try{
	this.abrir();
	s=c.createStatement();
	s.executeUpdate(cadena);
	s.close();
	this.cerrar();
	return true;
	}
	catch ( SQLException e){
		this.cerrar();
		return false;
	}
	
	
}
}
