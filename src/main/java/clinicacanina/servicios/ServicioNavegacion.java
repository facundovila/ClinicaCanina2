package clinicacanina.servicios;

import java.time.LocalDateTime;

import clinicacanina.modelo.Navegador;
import clinicacanina.modelo.ReservaDeAmbulancia;
import clinicacanina.repositorios.Trayecto;

public interface ServicioNavegacion {
    public LocalDateTime obtenerHoraActual();
    //public String calcularDistanciaRecorridoRestante(String distancia, String recorrido);
	LocalDateTime calcularHorarioDeLlegada(String patente);
    String guardarNavegacion(ReservaDeAmbulancia reserva, Trayecto trayecto);
    void actualizarNavegacion(Navegador navegador);
	Navegador buscarNavegacion(String patente);
	String calcularTiempoRestanteDeLlegada(String patente);

    
}
