package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import modelos.Reserva;
import modelos.Visita;

public class BD_ReservaVisita extends BD_Conector{
	private static Statement s;		
	private static ResultSet reg;
	
public BD_ReservaVisita(String bbdd){
	super ();
}
	

/*no me deja por las foreign keys ?¿ 		*/
public  boolean añadirReserva(Reserva re){
	String cadena="INSERT INTO reservas VALUES('" + re.getCod_reserva() + "','" + re.getFecha()+"','" + re.getId_emple()+"','" + re.getNif_cliente()+"','" + re.getNpersonas()+"')"; 	
	
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


/*hacer un get date y pasarlo a .tolocaldate */
/*
public Vector<Reserva> selectImporte(){
	Vector<Reserva> v=new Vector<Reserva>();
	String cadena="SELECT PRECIO * R.NPERSONAS FROM RESERVAS R, SALAS S WHERE R.NSALA = S.NSALA AND PRECIO LIKE (  SELECT PRECIO FROM SALAS)";
	try{
		this.abrir();
		s=c.createStatement();
		reg=s.executeQuery(cadena);
		while ( reg.next()){			
			 v.add(new Reserva(reg.getInt("importe")));
		}		
		s.close();
		this.cerrar();
		return v;
	}
	catch ( SQLException e){
		return null;
	}
}

*/




public Vector<Reserva>  mostrarReservas(){
	Vector<Reserva> v=new Vector<Reserva>();
	String cadena="SELECT * FROM reservas ";
	try{
		this.abrir();
		s=c.createStatement();
		reg=s.executeQuery(cadena);
		while ( reg.next()){					
			//java.sql.Date f=reg.getDate("fecha");
			//LocalDate fBuena=f.toLocalDate();
			 v.add(new Reserva(reg.getString("cod_reserva"),reg.getString("fecha"),reg.getString("nsala"),reg.getString("id_emple"),reg.getString("nif_cliente"),reg.getInt("npersonas"),reg.getInt("importe")));			
		}		
		s.close();
		this.cerrar();
		return v;
	}
	catch ( SQLException e){
		return null;
	}
}

/*este esta bien (nos ha jodio)*/
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
