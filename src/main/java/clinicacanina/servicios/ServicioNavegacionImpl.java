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
	public LocalDateTime calcularHorarioDeLlegada(Integer tiempo) {
		LocalDateTime horarioActual = obtenerHoraActual();
		return horarioActual.plusMinutes(tiempo);
		
	}

	@Override
	public String calcularDistanciaRecorridoRestante() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Nuevo metodo para guardar datos de seguimiento
		@Override
		public String guardarNavegacion(ReservaDeAmbulancia reserva, Trayecto trayecto) {
			Navegador navegador = new Navegador();
			navegador.setReserva(reserva);
			navegador.setLocalidadOrigen(trayecto.getLocalidadOrigen());
			navegador.setLocalidadDestino(trayecto.getLocalidadDestino());
			navegador.setDistancia(trayecto.getDistancia());
			navegador.setTiempo(trayecto.getTiempo());
			//Long idNavegacion = repositorioNavegacion.guardarNavegacion(navegador);
			
			return navegador.getReserva().getAmbulancia().getPatente();
			
		}

		// Nuevo metodo para buscar datos de seguimiento
		@Override
		public Navegador buscarNavegacion(String patente) {
			Navegador navegadorEcontrado = repositorioNavegacion.buscarNavegacion(patente);
			return navegadorEcontrado;
		}

}
