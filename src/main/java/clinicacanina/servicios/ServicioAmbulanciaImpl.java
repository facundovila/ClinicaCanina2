package clinicacanina.servicios;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.ErrorDeReserva;
import clinicacanina.modelo.ReservaDeAmbulancia;
import clinicacanina.repositorios.RepositorioAmbulancia;

@Service @Transactional
public class ServicioAmbulanciaImpl implements ServicioAmbulancia{

	private RepositorioAmbulancia repositorioAmbulancia;
	
	@Autowired
	public ServicioAmbulanciaImpl(RepositorioAmbulancia repositorioAmbulancia) {
		this.repositorioAmbulancia = repositorioAmbulancia;
	}

	//busca ambulancia disponible, si la hay la reserva automaticamente.
	@Override
	public List <Ambulancia> buscarAmbulanciasDisponibles() {
		List <Ambulancia> ambulancias = repositorioAmbulancia.buscarAmbulancias();
		List <Ambulancia> ambulanciasDisponibles = new LinkedList<Ambulancia>();
		
		for(int i = 0; i < ambulancias.size(); i++) {
			if(ambulancias.get(i) != null) {
				if(ambulancias.get(i).getDisponibilidad() == true) {
				ambulanciasDisponibles.add(ambulancias.get(i));
				}
			}
		}
		
		return ambulanciasDisponibles;
	}

	@Override
	public Ambulancia buscarAmbulanciaPorPatente(String patente) {
		return repositorioAmbulancia.buscarAmbulanciaPorPatente(patente);
	}

	@Override
	public void reservarAmbulancia(String direccion, String telefono, String motivo, Ambulancia ambulancia) {
		if((ambulancia !=null && ambulancia.getDisponibilidad() == true) && direccion != "") {
			ambulancia.setDisponibilidad(false);
			ReservaDeAmbulancia reservaDeAmbulancia = new ReservaDeAmbulancia();
			reservaDeAmbulancia.setDireccion(direccion);
			reservaDeAmbulancia.setTelefono(telefono);
			reservaDeAmbulancia.setMotivo(motivo);
			reservaDeAmbulancia.setAmbulancia(ambulancia);
			repositorioAmbulancia.reservarAmbulancia(reservaDeAmbulancia, ambulancia);
		}else {
			throw new ErrorDeReserva();
		}
		
		
	}

	// nuevo metodo review 3
	@Override
	public ReservaDeAmbulancia buscarReserva(Ambulancia ambulancia) {
		List <ReservaDeAmbulancia> reservasAmbulancias = repositorioAmbulancia.buscarReservas();
		if(reservasAmbulancias.isEmpty()) {
			throw new ErrorDeReserva();
		}
		for(ReservaDeAmbulancia reserva : reservasAmbulancias ) {
			if(reserva.getAmbulancia().getPatente().equals(ambulancia.getPatente())) {
				return reserva;
			}
		}
		return null;
	}

  
}
