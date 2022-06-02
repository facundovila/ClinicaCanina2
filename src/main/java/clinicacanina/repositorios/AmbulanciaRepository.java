package clinicacanina.repositorios;

import clinicacanina.modelo.Ambulancia;

public interface AmbulanciaRepository {

	Ambulancia traerAmbulanciaDisponible();

	void reservarAmbulancia(Ambulancia ambulancia);

	Ambulancia buscarPorPatente(String patente);

	void actualizarEstado();

}
