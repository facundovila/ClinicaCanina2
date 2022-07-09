package clinicacanina.servicios;

import java.time.LocalDateTime;

public interface Navegacion {
    public LocalDateTime obtenerHoraActual();
    public String calcularDistanciaRecorridoRestante();
	LocalDateTime calcularHorarioDeLlegada(Integer tiempo);
    
}
