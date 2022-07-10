package clinicacanina.servicios;



import java.time.LocalDateTime;

import clinicacanina.modelo.Navegador;
import clinicacanina.modelo.ReservaDeAmbulancia;
import clinicacanina.repositorios.RepositorioAmbulancia;
import clinicacanina.repositorios.RepositorioNavegacion;
import clinicacanina.repositorios.Trayecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service @Transactional
public class ServicioNavegacionImpl implements ServicioNavegacion{
	
private RepositorioNavegacion repositorioNavegacion;
	
	@Autowired
	public ServicioNavegacionImpl(RepositorioNavegacion repositorioNavegacion) {
		this.repositorioNavegacion = repositorioNavegacion;
	}

	
	@Override
	public LocalDateTime obtenerHoraActual() {
        return LocalDateTime.now();
		
	}

	@Override
	public LocalDateTime calcularHorarioDeLlegada(String patente) {
		LocalDateTime horarioActual = obtenerHoraActual();
		String horarioActualString = horarioActual.getYear() + "-"+horarioActual.getMonthValue()+"-"+horarioActual.getDayOfMonth()+" "+horarioActual.getHour()+":"+horarioActual.getMinute();
		Navegador navegador = repositorioNavegacion.buscarNavegacion(patente);
		String tiempoEstimado = navegador.getTiempoEstimado();
		Integer tiempoEstimadoNumber = parseTiempoEstimadoDeLlegada(tiempoEstimado);
		LocalDateTime horarioLlegada = sumarTiempoEstimadoAHoraActual(tiempoEstimadoNumber,horarioActual);
		//Formato de guardado : "2022-7-10 16:11"
		String horarioLLegadaString = horarioLlegada.getYear() + "-"+horarioLlegada.getMonthValue()+"-"+horarioLlegada.getDayOfMonth()+" "+horarioLlegada.getHour()+":"+horarioLlegada.getMinute();
		
		navegador.setHorarioDeLlegada(horarioLLegadaString);
		navegador.setHorarioDeSolicitud(horarioActualString);
		//---------Se actualizan los datos del Navegador en la BD -------------------
		actualizarNavegacion(navegador);
		return horarioLlegada;
		
		
	}


	private LocalDateTime sumarTiempoEstimadoAHoraActual(Integer tiempoEstimadoNumber, LocalDateTime horarioActual) {
		
		LocalDateTime horarioLlegada = horarioActual.plusMinutes(tiempoEstimadoNumber);
		return horarioLlegada;
	}


	private Integer parseTiempoEstimadoDeLlegada(String tiempoEstimado) {
		char[] arrayCaracteres = tiempoEstimado.toCharArray();
		String tiempoEstimadoString = String.valueOf(arrayCaracteres[0]) + String.valueOf(arrayCaracteres[1]);
		Integer tiempoEstimadoNumber = Integer.parseInt(tiempoEstimadoString);
		return tiempoEstimadoNumber;
	}

	/*@Override
	public String calcularDistanciaRecorridoRestante(String ) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	// Nuevo metodo para guardar datos de seguimiento
		@Override
		public String guardarNavegacion(ReservaDeAmbulancia reserva, Trayecto trayecto) {
			Navegador navegador = new Navegador();
			navegador.setReserva(reserva);
			navegador.setLocalidadOrigen(trayecto.getLocalidadOrigen());
			navegador.setLocalidadDestino(trayecto.getLocalidadDestino());
			navegador.setDistancia(trayecto.getDistancia());
			navegador.setTiempoEstimado(trayecto.getTiempo());
			//Long idNavegacion = repositorioNavegacion.guardarNavegacion(navegador);
			
			return navegador.getReserva().getAmbulancia().getPatente();
			
		}

		// Nuevo metodo para buscar datos de seguimiento
		@Override
		public Navegador buscarNavegacion(String patente) {
			Navegador navegadorEcontrado = repositorioNavegacion.buscarNavegacion(patente);
			return navegadorEcontrado;
		}


		@Override
		public void actualizarNavegacion(Navegador navegador) {
			repositorioNavegacion.actualizarNavegacion(navegador);
			
		}

}
