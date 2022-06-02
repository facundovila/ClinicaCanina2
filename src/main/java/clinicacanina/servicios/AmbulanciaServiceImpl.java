package clinicacanina.servicios;

import clinicacanina.modelo.Ambulancia;
import clinicacanina.repositorios.AmbulanciaRepository;

public class AmbulanciaServiceImpl implements AmbulanciaService{

	private AmbulanciaRepository ambulanciaRepository;
	
	public AmbulanciaServiceImpl(AmbulanciaRepository ambulanciaRepository) {
		this.ambulanciaRepository = ambulanciaRepository;
	}

	@Override
	public Ambulancia buscarAmbulanciaDisponible() {
		Ambulancia encontrada = ambulanciaRepository.traerAmbulanciaDisponible();
		if(encontrada != null) {
			ambulanciaRepository.reservarAmbulancia(encontrada);
		}
		return encontrada;
	}
  
}
