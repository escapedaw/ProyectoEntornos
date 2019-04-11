package modelos;

public class Pista {

	private String cod_pista;
	private String nsala;
	private String descripcion;
	private String solicitado;
	private String confirmado;
	public Pista(String cod_pista, String nsala, String descripcion, String solicitado, String confirmado) {
		super();
		this.cod_pista = cod_pista;
		this.nsala = nsala;
		this.descripcion = descripcion;
		this.solicitado = solicitado;
		this.confirmado = confirmado;
	}
	public String getCod_pista() {
		return cod_pista;
	}
	public void setCod_pista(String cod_pista) {
		this.cod_pista = cod_pista;
	}
	public String getNsala() {
		return nsala;
	}
	public void setNsala(String nsala) {
		this.nsala = nsala;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getSolicitado() {
		return solicitado;
	}
	public void setSolicitado(String solicitado) {
		this.solicitado = solicitado;
	}
	public String getConfirmado() {
		return confirmado;
	}
	public void setConfirmado(String confirmado) {
		this.confirmado = confirmado;
	}

	
	
}
