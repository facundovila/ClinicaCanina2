package clinicacanina.servicios;

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
	public Ambulancia buscarAmbulanciaDisponible() {
		Ambulancia encontrada = repositorioAmbulancia.traerAmbulanciaDisponible();
		if(encontrada != null) {
			repositorioAmbulancia.reservarAmbulancia(encontrada.getPatente());
		}
		return encontrada;
	}
  
}
