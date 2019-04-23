package modelos;

public class Visita {

	private String nsala;
	private String id_emple;
	private String nif_cliente;
	private String fecha;
	private String tiempo;
	private int nPersonas;
	private int importe;
	
	public Visita(String nsala, String id_emple, String nif_cliente, String fecha, String tiempo, int nPersonas,
			int importe) {
		super();
		this.nsala = nsala;
		this.id_emple = id_emple;
		this.nif_cliente = nif_cliente;
		this.fecha = fecha;
		this.tiempo = tiempo;
		this.nPersonas = nPersonas;
		this.importe = importe;
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

	public String getFecha() {
		return fecha;
	}

	public String getTiempo() {
		return tiempo;
	}

	public int getnPersonas() {
		return nPersonas;
	}

	public int getImporte() {
		return importe;
	}
	
	
	
	
	
	
}
