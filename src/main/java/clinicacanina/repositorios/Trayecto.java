package clinicacanina.repositorios;

public class Trayecto {
	private String localidadOrigen;
	private String localidadDestino;
	private String distancia;
	private String tiempo;
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
	
}
