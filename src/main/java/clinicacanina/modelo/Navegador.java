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
	private String tiempoEstimado;
	private String tiempoRestante;
	private String horarioDeLlegada;
	private String horarioDeSolicitud;
	public String getHorarioDeSolicitud() {
		return horarioDeSolicitud;
	}
	public void setHorarioDeSolicitud(String horarioDeSolicitud) {
		this.horarioDeSolicitud = horarioDeSolicitud;
	}
	public String getHorarioDeLlegada() {
		return horarioDeLlegada;
	}
	public void setHorarioDeLlegada(String horarioDeLlegada) {
		this.horarioDeLlegada = horarioDeLlegada;
	}
	public String getTiempoEstimado() {
		return tiempoEstimado;
	}
	public void setTiempoEstimado(String tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}
	public String getTiempoRestante() {
		return tiempoRestante;
	}
	public void setTiempoRestante(String tiempoRestante) {
		this.tiempoRestante = tiempoRestante;
	}
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
