package clinicacanina.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clinicacanina.modelo.Ambulancia;
import clinicacanina.repositorios.AmbulanciaRepository;

@Service @Transactional
public class AmbulanciaServiceImpl implements AmbulanciaService{

	private AmbulanciaRepository ambulanciaRepository;
	
	@Autowired
	public AmbulanciaServiceImpl(AmbulanciaRepository ambulanciaRepository) {
		this.ambulanciaRepository = ambulanciaRepository;
	}

	//busca ambulancia disponible, si la hay la reserva automaticamente.
	@Override
	public Ambulancia buscarAmbulanciaDisponible() {
		Ambulancia encontrada = ambulanciaRepository.traerAmbulanciaDisponible();
		if(encontrada != null) {
			ambulanciaRepository.reservarAmbulancia(encontrada.getPatente());
		}
		return encontrada;
	}
  
}
