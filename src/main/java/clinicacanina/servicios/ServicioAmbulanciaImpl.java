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
	public void reservarAmbulancia(String direccion, Ambulancia ambulancia) {
		if((ambulancia !=null && ambulancia.getDisponibilidad() == true) && direccion != "") {
			ambulancia.setDisponibilidad(false);
			ReservaDeAmbulancia reservaDeAmbulancia = new ReservaDeAmbulancia();
			reservaDeAmbulancia.setDireccion(direccion);
			reservaDeAmbulancia.setAmbulancia(ambulancia);
			repositorioAmbulancia.reservarAmbulancia(reservaDeAmbulancia, ambulancia);
		}else {
			throw new ErrorDeReserva();
		}
		
		
	}
  
}
