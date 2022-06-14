package clinicacanina.servicios;


import java.util.List;

import clinicacanina.modelo.Ambulancia;

public interface ServicioAmbulancia {

	List <Ambulancia> buscarAmbulanciasDisponibles();

	Ambulancia buscarAmbulanciaPorPatente(String patente);

	void reservarAmbulancia(Ambulancia ambulancia);

}
