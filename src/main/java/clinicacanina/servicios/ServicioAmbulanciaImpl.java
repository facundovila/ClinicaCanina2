package clinicacanina.servicios;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clinicacanina.modelo.Ambulancia;
import clinicacanina.repositorios.RepositorioAmbulancia;

@Service @Transactional
public class ServicioAmbulanciaImpl implements ServicioAmbulancia{

	private RepositorioAmbulancia repositorioAmbulancia;
	
	@Autowired
	public ServicioAmbulanciaImpl(RepositorioAmbulancia ambulanciaRepository) {
		this.repositorioAmbulancia = ambulanciaRepository;
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
  
}
