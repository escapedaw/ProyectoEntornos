package modelos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reserva {

	private String cod_reserva;
	private LocalDateTime fecha;
	private String nSala;
	private String id_emple;
	private String nif_cliente;
	private int npersonas;
	private int importe;
	
	public Reserva(String cod_reserva, LocalDateTime fecha, String nSala, String id_emple, String nif_cliente, int npersonas) {
		super();
		this.cod_reserva = cod_reserva;
		this.fecha = fecha;
		this.nSala = nSala;
		this.id_emple = id_emple;
		this.nif_cliente = nif_cliente;
		this.npersonas = npersonas;
	}

	
	
	public Reserva(String cod_reserva, LocalDateTime fecha, String nSala, String id_emple, String nif_cliente,
			int npersonas, int importe) {
		super();
		this.cod_reserva = cod_reserva;
		this.fecha = fecha;
		this.nSala = nSala;
		this.id_emple = id_emple;
		this.nif_cliente = nif_cliente;
		this.npersonas = npersonas;
		this.importe = importe;
	}





	public Reserva(int importe) {
		super();
		this.importe = importe;
	}



	public String getCod_reserva() {
		return cod_reserva;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public String getnSala() {
		return nSala;
	}

	public String getId_emple() {
		return id_emple;
	}

	public String getNif_cliente() {
		return nif_cliente;
	}

	public int getNpersonas() {
		return npersonas;
	}

	public int getImporte() {
		return importe;
	}



	@Override
	public String toString() {
		return "Reserva [cod_reserva=" + cod_reserva + ", fecha=" + fecha + ", nSala=" + nSala + ", id_emple="
				+ id_emple + ", nif_cliente=" + nif_cliente + ", npersonas=" + npersonas + ", importe=" + importe + "]";
	}
	
	
	
	
	
}
