package modelos;

import java.time.LocalDateTime;

public class Visita {

	private String cod_visita;
	private String nsala;
	private String id_emple;
	private String nif_cliente;
	private LocalDateTime fecha;
	private double tiempo;
	private int nPersonas;
	private int importe;
	
	public Visita(String cod_visita, String nsala, String id_emple, String nif_cliente, LocalDateTime fecha,
			double tiempo, int nPersonas, int importe) {
		super();
		this.cod_visita = cod_visita;
		this.nsala = nsala;
		this.id_emple = id_emple;
		this.nif_cliente = nif_cliente;
		this.fecha = fecha;
		this.tiempo = tiempo;
		this.nPersonas = nPersonas;
		this.importe = importe;
	}

	public String getCod_visita() {
		return cod_visita;
	}

	public String getNsala() {
		return nsala;
	}

	public String getId_emple() {
		return id_emple;
	}

	public String getNif_cliente() {
		return nif_cliente;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public double getTiempo() {
		return tiempo;
	}

	public int getnPersonas() {
		return nPersonas;
	}

	public int getImporte() {
		return importe;
	}

	@Override
	public String toString() {
		return "Visita [cod_visita=" + cod_visita + ", nsala=" + nsala + ", id_emple=" + id_emple + ", nif_cliente="
				+ nif_cliente + ", fecha=" + fecha + ", tiempo=" + tiempo + ", nPersonas=" + nPersonas + ", importe="
				+ importe + "]";
	}
	
	


	

}
