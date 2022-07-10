package clinicacanina.servicios;


import java.util.List;

import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.Navegador;
import clinicacanina.modelo.ReservaDeAmbulancia;
import clinicacanina.repositorios.Trayecto;

public interface ServicioAmbulancia {

	List <Ambulancia> buscarAmbulanciasDisponibles();

	Ambulancia buscarAmbulanciaPorPatente(String patente);

	void reservarAmbulancia(String direccion, String telefono, String motivo, Ambulancia ambulancia);

	ReservaDeAmbulancia buscarReserva(Ambulancia ambulancia);
	
	
}
