package clinicacanina.servicios;



import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.Estado;
import clinicacanina.modelo.ReservaDeAmbulancia;
import clinicacanina.repositorios.RepositorioAmbulancia;


public class ServicioAmbulanciaTest {
  
	private Ambulancia ambulancia1 = new Ambulancia();
	private Ambulancia ambulancia2 = new Ambulancia();
	private Ambulancia ambulancia3 = new Ambulancia();
	private Ambulancia ambulancia4 = new Ambulancia();
	private final String PATENTE_1 = "abc123";
	private final String PATENTE_2 = "def456";
	private final String PATENTE_3 = "ghi789";
	private final String PATENTE_4 = "jkl012";
	private final String DIRECCION_1 = "Arana 283";
	private final String DIRECCION_2 = "Calle Falsa 123";
	private final String DIRECCION_3 = "Av Siempre Viva 333";
	private final String TELEFONO_1 = "42811190";
	private final String MOTIVO_1 = "Fiebre y decaimiento";
	private final String TELEFONO_2 = "42819084";
	private final String MOTIVO_2 = "Diarrea";
	private final String TELEFONO_3 = "42811795";
	private final String MOTIVO_3 = "Vomitos";
	private final boolean DISPONIBLE = true;
	private final boolean NO_DISPONIBLE = false;
	private final int AMBULANCIAS_DISPONIBLES = 2;
	private RepositorioAmbulancia repositorioAmbulancia;
	private ServicioAmbulancia servicioAmbulancia;
	
	@Before
	public void init() {
		repositorioAmbulancia = mock(RepositorioAmbulancia.class);
		servicioAmbulancia = new ServicioAmbulanciaImpl(repositorioAmbulancia);
	}
	
	
	@Test
	public void queSePuedaBuscarAmbulanciasYSoloTraigaLasDisponibles() {
		dadoQueExistenAmbulancias();
		List<Ambulancia> ambulanciasDisponibles = cuandoBuscoLasAmbulanciasDisponibles();
		entoncesMeTraeUnaListaSoloDeLasDisponibles(ambulanciasDisponibles);
	}
	
	@Test
	public void queSePuedaReservarUnaAmbulancia() {
		Ambulancia ambulancia = new Ambulancia();
		ambulancia.setDisponibilidad(true);
		ambulancia.setPatente("ccc111");
		
		cuandoReservoUnaAmbulancia(ambulancia);
		laMismaDejaDeEstarDisponible(ambulancia);
	}
	
	@Test
	public void queSePuedaConsultarUnaReserva() {
		dadoQueHayReservasRealizadas();
		ReservaDeAmbulancia reserva = cuandoConsultoUnaReserva(setearAmbulanciasDispYNoDisp().get(1));
		entoncesMeTraeLaReservaDeLaAmbulanciaIndicada(reserva);
	}


	private void entoncesMeTraeLaReservaDeLaAmbulanciaIndicada(ReservaDeAmbulancia reserva) {
		
		assertThat(reserva.getDireccion()).isEqualTo(reservasDeAmbulancias().get(0).getDireccion());
		assertThat(reserva.getId()).isEqualTo(reservasDeAmbulancias().get(0).getId());
		assertThat(reserva.getAmbulancia()).isEqualTo(reservasDeAmbulancias().get(0).getAmbulancia());
		//assertThat(reserva).isEqualTo(reservasDeAmbulancias().get(0)); //preguntar porque no son el mismo objeto
		
	}


	private ReservaDeAmbulancia cuandoConsultoUnaReserva(Ambulancia ambulancia) {
		return servicioAmbulancia.buscarReserva(ambulancia);
		
	}


	private void dadoQueHayReservasRealizadas() {
		
		when(repositorioAmbulancia.buscarReservas()).thenReturn(reservasDeAmbulancias());
	}


	private void laMismaDejaDeEstarDisponible(Ambulancia ambulancia) {
		assertThat(NO_DISPONIBLE).isEqualTo(ambulancia.getDisponibilidad());
		
	}


	private void cuandoReservoUnaAmbulancia(Ambulancia ambulancia) {
		servicioAmbulancia.reservarAmbulancia(DIRECCION_1, TELEFONO_1, MOTIVO_1, ambulancia);
		
	}



	private void entoncesMeTraeUnaListaSoloDeLasDisponibles(List<Ambulancia> ambulanciasDisponibles) {
		assertThat(AMBULANCIAS_DISPONIBLES).isEqualTo(ambulanciasDisponibles.size());
		assertThat(DISPONIBLE).isEqualTo(ambulanciasDisponibles.get(0).getDisponibilidad());
		assertThat(DISPONIBLE).isEqualTo(ambulanciasDisponibles.get(1).getDisponibilidad());
		assertThat(PATENTE_1).isEqualTo(ambulanciasDisponibles.get(0).getPatente());
		assertThat(PATENTE_3).isEqualTo(ambulanciasDisponibles.get(1).getPatente());
		
	}


	private List<Ambulancia> cuandoBuscoLasAmbulanciasDisponibles() {
		return servicioAmbulancia.buscarAmbulanciasDisponibles();
	}


	private void dadoQueExistenAmbulancias() {
		when(repositorioAmbulancia.buscarAmbulancias()).thenReturn(setearAmbulanciasDispYNoDisp());
		
	}
	
	
	private List<Ambulancia> setearAmbulanciasDispYNoDisp(){
		List<Ambulancia> ambulancias = new LinkedList<>();
		//Impares disponibles, Pares no disponibles.
		ambulancia1.setPatente(PATENTE_1);
		ambulancia1.setDisponibilidad(true);
		ambulancia2.setPatente(PATENTE_2);
		ambulancia2.setDisponibilidad(false);
		ambulancia3.setPatente(PATENTE_3);
		ambulancia3.setDisponibilidad(true);
		ambulancia4.setPatente(PATENTE_4);
		ambulancia4.setDisponibilidad(false);
		
		ambulancias.add(ambulancia1);
		ambulancias.add(ambulancia2);
		ambulancias.add(ambulancia3);
		ambulancias.add(ambulancia4);
		
		return ambulancias;
	}
	
	private List<ReservaDeAmbulancia> reservasDeAmbulancias(){
		List<ReservaDeAmbulancia> reservas = new LinkedList<>();
		ReservaDeAmbulancia reserva1 = new ReservaDeAmbulancia();
		ReservaDeAmbulancia reserva2 = new ReservaDeAmbulancia();
		ReservaDeAmbulancia reserva3 = new ReservaDeAmbulancia();
		//------------Primer Reserva Arana 283, 42811190, Fiebre y decaimiento ---------------------
		reserva1.setDireccion(DIRECCION_1);
		reserva1.setTelefono(TELEFONO_1);
		reserva1.setMotivo(MOTIVO_1);
		reserva1.setAmbulancia(setearAmbulanciasDispYNoDisp().get(1));
		//-----------Segunda Reserva Calle Falsa 123, 42819084, Diarrea -------------------------------
		reserva2.setDireccion(DIRECCION_2);
		reserva2.setTelefono(TELEFONO_2);
		reserva2.setMotivo(MOTIVO_2);
		reserva2.setAmbulancia(setearAmbulanciasDispYNoDisp().get(3));
		//-----------Tercera Reserva Av Siempre Viva 333, 42811795, Vomitos ----------------------------
		reserva3.setDireccion(DIRECCION_3);
		reserva3.setTelefono(TELEFONO_3);
		reserva3.setMotivo(MOTIVO_3);
		Ambulancia ambulancia = new Ambulancia();
		ambulancia.setPatente("FFF232");
		ambulancia.setDisponibilidad(false);
		reserva3.setAmbulancia(ambulancia);
		//----------Se agregan las reservas a la Lista ---------------------------------
		reservas.add(reserva1);
		reservas.add(reserva2);
		reservas.add(reserva3);
		//---------Se retorna la lista -------------------------------
		return reservas;
		
		
		
		
	}
	
}
