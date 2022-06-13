package clinicacanina.repositorios;

import clinicacanina.modelo.Ambulancia;

public interface AmbulanciaRepository {

	Ambulancia traerAmbulanciaDisponible();

	void reservarAmbulancia(String patente);

	Ambulancia buscarPorPatente(String patente);

	void actualizarEstado(String patente);

}
