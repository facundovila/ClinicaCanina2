package clinicacanina.controladores;

public class DatosReservaAmbulancia {
    private String direccionCalle;
    private Integer direccionNumero;
    private String patente;
    private String telefono;
    private String motivo;
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccionCalle() {
		return direccionCalle;
	}
	public void setDireccionCalle(String direccionCalle) {
		this.direccionCalle = direccionCalle;
	}
	public Integer getDireccionNumero() {
		return direccionNumero;
	}
	public void setDireccionNumero(Integer direccionNumero) {
		this.direccionNumero = direccionNumero;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
}
