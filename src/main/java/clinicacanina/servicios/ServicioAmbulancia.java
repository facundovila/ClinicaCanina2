package clinicacanina.servicios;


import java.util.List;

import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.ReservaDeAmbulancia;

public interface ServicioAmbulancia {

	List <Ambulancia> buscarAmbulanciasDisponibles();

	Ambulancia buscarAmbulanciaPorPatente(String patente);

	void reservarAmbulancia(String direccion, String telefono, String motivo, Ambulancia ambulancia);

	ReservaDeAmbulancia buscarReserva(Ambulancia ambulancia);
	
	ReservaDeAmbulancia buscarReservaPorTelefono(String telefono);

}
