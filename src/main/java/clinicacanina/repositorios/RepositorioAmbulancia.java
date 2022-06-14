package clinicacanina.repositorios;


import java.util.List;

import clinicacanina.modelo.Ambulancia;

public interface RepositorioAmbulancia {

	List <Ambulancia> buscarAmbulancias();
  /*
	void reservarAmbulancia(String patente);

	Ambulancia buscarPorPatente(String patente);

	void actualizarEstado(String patente);
*/
}
