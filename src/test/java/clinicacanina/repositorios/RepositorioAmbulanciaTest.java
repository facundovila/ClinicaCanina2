package clinicacanina.repositorios;

import org.springframework.beans.factory.annotation.Autowired;

import clinicacanina.SpringTest;
import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.Estado;
import clinicacanina.modelo.ReservaDeAmbulancia;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class RepositorioAmbulanciaTest extends SpringTest{
	private Ambulancia ambulancia1 = new Ambulancia();
	private Ambulancia ambulancia2 = new Ambulancia();
	private Ambulancia ambulancia3 = new Ambulancia();
	private final String PATENTE_1 = "abc123";
	private final String PATENTE_2 = "def456";
	private final String PATENTE_3 = "ghi789";
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
	private final int AMBULANCIAS_EXISTENTES = 3;
	
	@Autowired
	private RepositorioAmbulancia repositorioAmbulancia;
	
	@Test @Transactional @Rollback
	public void queSePuedaBuscarTodasLasAmbulanciasExistentes() {
		dadoQueExistenAmbulancias();
		List<Ambulancia> ambulanciasBuscadas = cuandoBuscoTodasLasAmbulancias();
		entoncesObtengoUnaListaDeTodasLasAmbulanciasExistentes(ambulanciasBuscadas);
	}
	
	@Test @Transactional @Rollback
	public void queSePuedaBuscarUnaAmbulanciaPorPatente() {
		dadoQueExistenAmbulancias();
		Ambulancia ambulancia = cuandoBuscoUnaAmbulanciaPorPatente(PATENTE_1);
		entoncesObtengoLaAmbulanciaCorrespondiente(ambulancia1,ambulancia);
	}

	
	
	@Test @Transactional @Rollback
	public void alReservarUnaAmbulanciaQueSeActualiceEnLaBDSuDisponibilidad() {
		dadoQueExistenAmbulancias();
		cuandoReservoLaAmbulancia();
		entoncesLaBuscoEnLaBDYNoEstaDisponible(PATENTE_1);
	}
	// nuevo metodo-test review 3
	@Test @Transactional @Rollback
	public void queSePuedaConsultarTodasLasReservas() {
		dadoQueHayAmbulanciasReservadas();
		List <ReservaDeAmbulancia> reservas = cuandoConsultoLasReservas();
		entoncesObtengoUnaListaLLena(reservas);
	}


	private void entoncesObtengoUnaListaLLena(List<ReservaDeAmbulancia> reservas) {
		assertThat(reservas).isNotEmpty();
		//assertThat(reservas.get(0).getId()).isEqualTo(reservasDeAmbulancias().get(0).getId());
		
	}

	private List<ReservaDeAmbulancia> cuandoConsultoLasReservas() {
		return repositorioAmbulancia.buscarReservas();
		
	}

	private void dadoQueHayAmbulanciasReservadas() {
		ambulancia1.setPatente(PATENTE_1);
		ambulancia1.setDisponibilidad(NO_DISPONIBLE);
		ambulancia2.setPatente(PATENTE_2);
		ambulancia2.setDisponibilidad(NO_DISPONIBLE);
		ambulancia3.setPatente(PATENTE_3);
		ambulancia3.setDisponibilidad(NO_DISPONIBLE);
		ReservaDeAmbulancia reserva1 = new ReservaDeAmbulancia();
		ReservaDeAmbulancia reserva2 = new ReservaDeAmbulancia();
		ReservaDeAmbulancia reserva3 = new ReservaDeAmbulancia();
		//------------Primer Reserva Arana 283, 42811190, Fiebre y decaimiento ---------------------
		reserva1.setDireccion(DIRECCION_1);
		reserva1.setTelefono(TELEFONO_1);
		reserva1.setMotivo(MOTIVO_1);
		reserva1.setAmbulancia(ambulancia1);
		//-----------Segunda Reserva Calle Falsa 123, 42819084, Diarrea -------------------------------
		reserva2.setDireccion(DIRECCION_2);
		reserva2.setTelefono(TELEFONO_2);
		reserva2.setMotivo(MOTIVO_2);
		reserva2.setAmbulancia(ambulancia2);
		//-----------Tercera Reserva Av Siempre Viva 333, 42811795, Vomitos ----------------------------
		reserva3.setDireccion(DIRECCION_3);
		reserva3.setTelefono(TELEFONO_3);
		reserva3.setMotivo(MOTIVO_3);
		reserva3.setAmbulancia(ambulancia3);
		
		session().save(ambulancia1);
		session().save(ambulancia2);
		session().save(ambulancia3);
		
		session().save(reserva1);
		session().save(reserva2);
		session().save(reserva3);
		
	}

	private Ambulancia cuandoBuscoUnaAmbulanciaPorPatente(String patente) {
		return repositorioAmbulancia.buscarAmbulanciaPorPatente(patente);
		
	}
	private void cuandoReservoLaAmbulancia() {
		ReservaDeAmbulancia reservaDeAmbulancia = new ReservaDeAmbulancia();
		Ambulancia ambulanciaAReservar = repositorioAmbulancia.buscarAmbulanciaPorPatente(PATENTE_1);
		ambulanciaAReservar.setDisponibilidad(NO_DISPONIBLE);
		reservaDeAmbulancia.setDireccion(DIRECCION_1);
		reservaDeAmbulancia.setAmbulancia(ambulanciaAReservar);
		repositorioAmbulancia.reservarAmbulancia(reservaDeAmbulancia, ambulanciaAReservar);
		
	}

	private void entoncesLaBuscoEnLaBDYNoEstaDisponible(String patente) {
		Ambulancia ambulanciaReservada = repositorioAmbulancia.buscarAmbulanciaPorPatente(patente);
		assertThat(ambulanciaReservada.getDisponibilidad()).isEqualTo(NO_DISPONIBLE);
		
	}

	private void entoncesObtengoLaAmbulanciaCorrespondiente(Ambulancia ambulanciaEsperada, Ambulancia ambulanciaRecibida) {
		assertThat(ambulanciaEsperada).isEqualTo(ambulanciaRecibida);
		assertThat(ambulanciaEsperada.getPatente()).isEqualTo(ambulanciaRecibida.getPatente());
		//System.out.println(ambulanciaEsperada.getPatente()+ " === " + ambulanciaRecibida.getPatente());
		
	}

	private void entoncesObtengoUnaListaDeTodasLasAmbulanciasExistentes(List<Ambulancia> ambulanciasBuscadas) {
		assertThat(AMBULANCIAS_EXISTENTES).isEqualTo(ambulanciasBuscadas.size());
		assertThat(PATENTE_1).isEqualTo(ambulanciasBuscadas.get(0).getPatente());
		assertThat(PATENTE_2).isEqualTo(ambulanciasBuscadas.get(1).getPatente());
		assertThat(PATENTE_3).isEqualTo(ambulanciasBuscadas.get(2).getPatente());
		
	}

	private List<Ambulancia> cuandoBuscoTodasLasAmbulancias() {
		
		return repositorioAmbulancia.buscarAmbulancias();
	}

	private void dadoQueExistenAmbulancias() {
		ambulancia1.setDisponibilidad(DISPONIBLE);
		ambulancia1.setPatente(PATENTE_1);
		ambulancia2.setDisponibilidad(NO_DISPONIBLE);
		ambulancia2.setPatente(PATENTE_2);
		ambulancia3.setDisponibilidad(NO_DISPONIBLE);
		ambulancia3.setPatente(PATENTE_3);
		
		session().save(ambulancia1);
		session().save(ambulancia2);
		session().save(ambulancia3);
	}
	
	private List<Ambulancia> crearAmbulanciasReservadas(){
		List<Ambulancia> ambulancias = new LinkedList<>();
		//Impares disponibles, Pares no disponibles.
		ambulancia1.setPatente(PATENTE_1);
		ambulancia1.setDisponibilidad(NO_DISPONIBLE);
		ambulancia2.setPatente(PATENTE_2);
		ambulancia2.setDisponibilidad(NO_DISPONIBLE);
		ambulancia3.setPatente(PATENTE_3);
		ambulancia3.setDisponibilidad(NO_DISPONIBLE);
		
		ambulancias.add(ambulancia1);
		ambulancias.add(ambulancia2);
		ambulancias.add(ambulancia3);
		
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
		reserva1.setAmbulancia(crearAmbulanciasReservadas().get(0));
		//-----------Segunda Reserva Calle Falsa 123, 42819084, Diarrea -------------------------------
		reserva2.setDireccion(DIRECCION_2);
		reserva2.setTelefono(TELEFONO_2);
		reserva2.setMotivo(MOTIVO_2);
		reserva2.setAmbulancia(crearAmbulanciasReservadas().get(1));
		//-----------Tercera Reserva Av Siempre Viva 333, 42811795, Vomitos ----------------------------
		reserva3.setDireccion(DIRECCION_3);
		reserva3.setTelefono(TELEFONO_3);
		reserva3.setMotivo(MOTIVO_3);
		reserva3.setAmbulancia(crearAmbulanciasReservadas().get(2));
		//----------Se agregan las reservas a la Lista ---------------------------------
		reservas.add(reserva1);
		reservas.add(reserva2);
		reservas.add(reserva3);
		//---------Se retorna la lista -------------------------------
		return reservas;
	}
}
