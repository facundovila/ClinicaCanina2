package clinicacanina.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import clinicacanina.repositorios.Trayecto;

@Entity
public class Navegador {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String patente;
	private String localidadOrigen;
	private String localidadDestino;
	private String distancia;
	private String tiempo;
	@OneToOne
	 @JoinColumn(name = "reserva_id")
	private ReservaDeAmbulancia reserva;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLocalidadOrigen() {
		return localidadOrigen;
	}
	public void setLocalidadOrigen(String localidadOrigen) {
		this.localidadOrigen = localidadOrigen;
	}
	public String getLocalidadDestino() {
		return localidadDestino;
	}
	public void setLocalidadDestino(String localidadDestino) {
		this.localidadDestino = localidadDestino;
	}
	public String getDistancia() {
		return distancia;
	}
	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	public ReservaDeAmbulancia getReserva() {
		return reserva;
	}
	public void setReserva(ReservaDeAmbulancia reserva) {
		this.reserva = reserva;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	
}
