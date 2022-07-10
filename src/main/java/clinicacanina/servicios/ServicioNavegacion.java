package clinicacanina.servicios;

import java.time.LocalDateTime;

import clinicacanina.modelo.Navegador;
import clinicacanina.modelo.ReservaDeAmbulancia;
import clinicacanina.repositorios.Trayecto;

public interface ServicioNavegacion {
    public LocalDateTime obtenerHoraActual();
    public String calcularDistanciaRecorridoRestante();
	LocalDateTime calcularHorarioDeLlegada(Integer tiempo);
    String guardarNavegacion(ReservaDeAmbulancia reserva, Trayecto trayecto);
	Navegador buscarNavegacion(String patente);

    
}
