package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import modelos.Reserva;
import modelos.Visita;

public class BD_ReservaVisita extends BD_Conector {
	private static Statement s;
	private static ResultSet reg;

	public BD_ReservaVisita() {
		super();
	}

	//DANI
	public int añadirReserva(Reserva re) {

		String cadena2 = "SELECT PRECIO FROM salas WHERE NSALA = '" + re.getnSala() + "';";
		String cadena;
		int precio = 0;
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena2);
			if (reg.next())
				precio = reg.getInt("precio");
			cadena = "INSERT INTO reservas VALUES('" + re.getCod_reserva() + "','" + re.getFecha() + "','"
					+ re.getnSala() + "','" + re.getId_emple() + "','" + re.getNif_cliente() + "','" + re.getNpersonas()
					+ "','" + precio * re.getNpersonas() + "')";
			s.executeUpdate(cadena);
			s.close();
			this.cerrar();
			return 1;
		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}

	}

	//DANI
	public Vector<Reserva> mostrarReservas() {
		Vector<Reserva> v = new Vector<Reserva>();
		String cadena = "SELECT * FROM reservas";
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				java.sql.Date f = reg.getDate("fecha");
				java.sql.Time h = reg.getTime("fecha");
				LocalDate fBuena = f.toLocalDate();
				LocalTime hBuena = h.toLocalTime();
				LocalDateTime fechaHora = LocalDateTime.of(fBuena, hBuena);
				v.add(new Reserva(reg.getString("cod_reserva"), fechaHora, reg.getString("nsala"),
						reg.getString("id_emple"), reg.getString("nif_cliente"), reg.getInt("npersonas"),
						reg.getInt("importe")));
			}
			s.close();
			this.cerrar();
			return v;
		} catch (SQLException e) {
			return null;
		}
	}

	//DANI
	public double modificarPersonasReserva(String codR, int numP) {
		String cadena;
		String cadena2 = "SELECT PRECIO FROM salas WHERE NSALA = (SELECT NSALA FROM reservas WHERE COD_RESERVA = '" + codR + "');";
		int precio=0;
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena2);
			if (reg.next())
				precio = reg.getInt("precio");
			cadena="UPDATE reservas SET NPERSONAS="+numP+", IMPORTE =" + precio*numP + "  WHERE COD_RESERVA ='" + codR + "'";
			int filas = s.executeUpdate(cadena);
			s.close();
			this.cerrar();
			return filas;
		} catch (SQLException e) {
			return -1;
		}
	}
	
	//DANI
	public double modificarFechaReserva(LocalDateTime fecha, String codR) {
		String cadena="UPDATE reservas SET FECHA='"+ fecha+"' WHERE COD_RESERVA ='" + codR + "';";
		try {
			this.abrir();
			s = c.createStatement();
			int filas = s.executeUpdate(cadena);
			s.close();
			this.cerrar();
			return filas;
		} catch (SQLException e) {
			return -1;
		}
	}
	
	//DANI
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
	
	//DANI
	public Vector<Visita> mostrarVisitas() {
		Vector<Visita> v = new Vector<Visita>();
		String cadena = "SELECT * FROM visitas";
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				java.sql.Date f = reg.getDate("fecha");
				java.sql.Time h = reg.getTime("fecha");
				LocalDate fBuena = f.toLocalDate();
				LocalTime hBuena = h.toLocalTime();
				LocalDateTime fechaHora = LocalDateTime.of(fBuena, hBuena);
				v.add(new Visita(reg.getString("nsala"),reg.getString("id_emple"),reg.getString("nif_cliente"), fechaHora, reg.getDouble("tiempo"),
						reg.getInt("npersonas"), reg.getInt("importe")));
			}
			s.close();
			this.cerrar();
			return v;
		} catch (SQLException e) {
			return null;
		}
	}
	
	//DANI
	public Vector<Visita> mostrarVisitasID(String usuario) {
		Vector<Visita> v = new Vector<Visita>();
		String cadena = "SELECT * FROM visitas WHERE  nif_cliente = (SELECT NIF FROM clientes WHERE ID='"+usuario+"');";
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				java.sql.Date f = reg.getDate("fecha");
				java.sql.Time h = reg.getTime("fecha");
				LocalDate fBuena = f.toLocalDate();
				LocalTime hBuena = h.toLocalTime();
				LocalDateTime fechaHora = LocalDateTime.of(fBuena, hBuena);
				v.add(new Visita(reg.getString("nsala"),reg.getString("id_emple"),reg.getString("nif_cliente"), fechaHora, reg.getDouble("tiempo"),
						reg.getInt("npersonas"), reg.getInt("importe")));
			}
			s.close();
			this.cerrar();
			return v;
		} catch (SQLException e) {
			return null;
		}
	}
	
	//DANI
	public Vector<Reserva> mostrarReservasID(String usuario) {
		Vector<Reserva> v = new Vector<Reserva>();
		String cadena = "SELECT * FROM reservas WHERE  nif_cliente = (SELECT NIF FROM clientes WHERE ID='"+usuario+"');";

		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				java.sql.Date f = reg.getDate("fecha");
				java.sql.Time h = reg.getTime("fecha");
				LocalDate fBuena = f.toLocalDate();
				LocalTime hBuena = h.toLocalTime();
				LocalDateTime fechaHora = LocalDateTime.of(fBuena, hBuena);
				v.add(new Reserva(reg.getString("cod_reserva"), fechaHora, reg.getString("nsala"),
						reg.getString("id_emple"), reg.getString("nif_cliente"), reg.getInt("npersonas"),
						reg.getInt("importe")));
			}
			s.close();
			this.cerrar();
			return v;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public boolean poderJugar(String usuario) {
		String cadena = "SELECT * FROM reservas WHERE  nif_cliente = (SELECT NIF FROM clientes WHERE ID='"+usuario+"');";
		LocalDate fechaActual=LocalDate.now();
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadena);
			while (reg.next()) {
				java.sql.Date f = reg.getDate("fecha");
				LocalDate fBuena = f.toLocalDate();
				if(fechaActual.equals(fBuena))
					return true;
			}
			s.close();
			this.cerrar();
			return false;
		} catch (SQLException e) {
			return false;
		}
	}

	
	public boolean añadirVisita(Visita vi) {
		String cadena = "INSERT INTO visitas VALUES('" + vi.getNsala() + "','" + vi.getId_emple() + "','"
				+ vi.getNif_cliente() + "','" + vi.getFecha() + "','" + vi.getTiempo() + "')";

		try {
			this.abrir();
			s = c.createStatement();
			s.executeUpdate(cadena);
			s.close();
			this.cerrar();
			return true;
		} catch (SQLException e) {
			this.cerrar();
			return false;
		}

	}
}
