package clinicacanina.repositorios;

import org.springframework.beans.factory.annotation.Autowired;

import clinicacanina.SpringTest;
import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.Estado;
import clinicacanina.modelo.ReservaDeAmbulancia;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Test;

public class RepositorioAmbulanciaTest extends SpringTest{
	private Ambulancia ambulancia1 = new Ambulancia();
	private Ambulancia ambulancia2 = new Ambulancia();
	private Ambulancia ambulancia3 = new Ambulancia();
	private final String PATENTE_1 = "abc123";
	private final String PATENTE_2 = "def456";
	private final String PATENTE_3 = "ghi789";
	private final String DIRECCION = "Arana 283";
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

	private Ambulancia cuandoBuscoUnaAmbulanciaPorPatente(String patente) {
		return repositorioAmbulancia.buscarAmbulanciaPorPatente(patente);
		
	}
	
	@Test @Transactional @Rollback
	public void alReservarUnaAmbulanciaQueSeActualiceEnLaBDSuDisponibilidad() {
		dadoQueExistenAmbulancias();
		cuandoReservoLaAmbulancia();
		entoncesLaBuscoEnLaBDYNoEstaDisponible(PATENTE_1);
	}

	private void cuandoReservoLaAmbulancia() {
		ReservaDeAmbulancia reservaDeAmbulancia = new ReservaDeAmbulancia();
		Ambulancia ambulanciaAReservar = repositorioAmbulancia.buscarAmbulanciaPorPatente(PATENTE_1);
		ambulanciaAReservar.setDisponibilidad(NO_DISPONIBLE);
		reservaDeAmbulancia.setDireccion(DIRECCION);
		reservaDeAmbulancia.setAmbulancia(ambulanciaAReservar);
		repositorioAmbulancia.reservarAmbulancia(reservaDeAmbulancia);
		
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

}
