package modelos;

public class Visita {

	private String nsala;
	private String id_emple;
	private String nif_cliente;
	private String fecha;
	private String tiempo;
	
	public Visita(String nsala, String id_emple, String nif_cliente, String fecha, String tiempo) {
		super();
		this.nsala = nsala;
		this.id_emple = id_emple;
		this.nif_cliente = nif_cliente;
		this.fecha = fecha;
		this.tiempo = tiempo;
	}
	public String getNsala() {
		return nsala;
	}
	public void setNsala(String nsala) {
		this.nsala = nsala;
	}
	public String getId_emple() {
		return id_emple;
	}
	public void setId_emple(String id_emple) {
		this.id_emple = id_emple;
	}
	public String getNif_cliente() {
		return nif_cliente;
	}
	public void setNif_cliente(String nif_cliente) {
		this.nif_cliente = nif_cliente;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	
	
	
	
	
	
}
