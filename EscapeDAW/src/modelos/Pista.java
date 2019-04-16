package modelos;

public class Pista {

	private String cod_pista;
	private String nsala;
	private String descripcion;
	private boolean solicitado;
	private boolean confirmado;
	private static int numPistas;
	
	/**
	 * Constructor para crear objeto Pista
	 * @param nsala
	 * @param descripcion
	 */
	
	public Pista(String nsala, String descripcion) {
		super();
		numPistas++;
		this.cod_pista = "PI" + numPistas;
		this.nsala = nsala;
		this.descripcion = descripcion;
	}
	
	public Pista(String cod_pista, String nsala, String descripcion, boolean solicitado, boolean confirmado) {
		super();
		this.cod_pista = cod_pista;
		this.nsala = nsala;
		this.descripcion = descripcion;
		this.solicitado = solicitado;
		this.confirmado = confirmado;
	}
	
	

	public Pista(String cod_pista, String nsala, String descripcion) {
		super();
		this.cod_pista = cod_pista;
		this.nsala = nsala;
		this.descripcion = descripcion;
	}

	public Pista(String descripcion) {
		super();
		this.descripcion = descripcion;
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
	public boolean getSolicitado() {
		return solicitado;
	}
	public void setSolicitado(boolean solicitado) {
		this.solicitado = solicitado;
	}
	public boolean getConfirmado() {
		return confirmado;
	}
	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}

	
	
}
