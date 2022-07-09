package clinicacanina.servicios;



import java.time.LocalDateTime;



public class NavegacionImpl implements Navegacion{

	
	@Override
	public LocalDateTime obtenerHoraActual() {
        return LocalDateTime.now();
		
	}

	@Override
	public LocalDateTime calcularHorarioDeLlegada(Integer tiempo) {
		LocalDateTime horarioActual = obtenerHoraActual();
		return horarioActual.plusMinutes(tiempo);
		
	}

	@Override
	public String calcularDistanciaRecorridoRestante() {
		// TODO Auto-generated method stub
		return null;
	}

}
