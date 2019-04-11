package modelos;

public class Reserva {

	private String cod_reserva;
	private String fecha;
	private String id_emple;
	private String nif_cliente;
	private int npersonas;
	
	public Reserva(String cod_reserva, String fecha, String id_emple, String nif_cliente, int npersonas) {
		super();
		this.cod_reserva = cod_reserva;
		this.fecha = fecha;
		this.id_emple = id_emple;
		this.nif_cliente = nif_cliente;
		this.npersonas = npersonas;
	}
	
	public String getCod_reserva() {
		return cod_reserva;
	}
	public void setCod_reserva(String cod_reserva) {
		this.cod_reserva = cod_reserva;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
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
	public int getNpersonas() {
		return npersonas;
	}
	public void setNpersonas(int npersonas) {
		this.npersonas = npersonas;
	}
	
	
	
	
	
	
	
	
	
}
