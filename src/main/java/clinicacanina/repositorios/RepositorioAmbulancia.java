package clinicacanina.repositorios;

import clinicacanina.modelo.Ambulancia;

public interface RepositorioAmbulancia {

	Ambulancia traerAmbulanciaDisponible();

	void reservarAmbulancia(String patente);

	Ambulancia buscarPorPatente(String patente);

	void actualizarEstado(String patente);

}
