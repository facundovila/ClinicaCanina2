package clinicacanina.repositorios;


import java.util.List;

import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.ReservaDeAmbulancia;

public interface RepositorioAmbulancia {

	List <Ambulancia> buscarAmbulancias();
  /*
	void reservarAmbulancia(String patente);

	Ambulancia buscarPorPatente(String patente);

	void actualizarEstado(String patente);
*/

	Ambulancia buscarAmbulanciaPorPatente(String patente);

	void reservarAmbulancia(ReservaDeAmbulancia reservaDeAmbulancia, Ambulancia ambulancia);

	List<ReservaDeAmbulancia> buscarReservas();
}
