package clinicacanina.repositorios;

import org.springframework.beans.factory.annotation.Autowired;

import clinicacanina.SpringTest;
import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.Estado;

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
	
	
	/*
	@Test @Transactional @Rollback
	public void poderBuscarUnaAmbulanciaPorPatente() {
		Ambulancia ambulancia = cuandoBuscoLaAmbulanciaReservada();
		entoncesObtengoLaAmbulanciaCorrespondiente(PATENTE_ESPERADA, ambulancia.getPatente());
	}
	
	@Test @Transactional @Rollback
	public void poderReservarUnaAmbulancia() {
		dadoQueExisteAmbulanciaSinReservar();
		Ambulancia reservada = cuandoReservoLaAmbulancia("def123");
		entoncesLaMismaPoseeDisponibilidadYEstado(PRIMER_ESTADO_RESERVADA, reservada);
		
	}
	
	private void entoncesLaMismaPoseeDisponibilidadYEstado(Estado PRIMER_ESTADO_RESERVADA, Ambulancia reservada) {
		assertThat(reservada.getDisponible()).isFalse();
		assertThat(reservada.getEstado()).isEqualTo(PRIMER_ESTADO_RESERVADA);
		
	}

	private Ambulancia cuandoReservoLaAmbulancia(String patente) {
		repositorioAmbulancia.reservarAmbulancia(patente);
		return repositorioAmbulancia.buscarPorPatente(patente);
		
	}

	private void dadoQueExisteAmbulanciaSinReservar() {
		Ambulancia ambulanciaCreada = new Ambulancia();
		ambulanciaCreada.setDisponible(true);
		ambulanciaCreada.setEstado(Estado.EN_COCHERA);
		ambulanciaCreada.setPatente("def123");
		
		session().save(ambulanciaCreada);
		
	}

	//@Test @Transactional @Rollback
	public void poderActualizarElEstadoDeUnaAmbulanciaReservada() {
		Ambulancia ambulanciaAActualizar = cuandoBuscoLaAmbulanciaReservada();
		entoncesPuedoActualizarSuEstado(ambulanciaAActualizar);
	}
	
	private void entoncesObtengoLaAmbulanciaCorrespondiente(String PATENTE_ESPERADA, String patenteRecibida) {
		assertThat(patenteRecibida).isEqualTo(PATENTE_ESPERADA);
		
	}

	

	private void entoncesPuedoActualizarSuEstado(Ambulancia ambulanciaAActualizar) {
	   repositorioAmbulancia.actualizarEstado("abc123");
	   
	   assertThat(ambulanciaAActualizar.getEstado()).isEqualTo(ESTADO_ESPERADO);
	   
		
	}

	private Ambulancia cuandoBuscoLaAmbulanciaReservada() {
		Ambulancia ambulancia = new Ambulancia();
		ambulancia.setDisponible(false);
		ambulancia.setEstado(Estado.EN_COCHERA);
		ambulancia.setPatente("abc123");
		
		session().save(ambulancia);
		
		return repositorioAmbulancia.buscarPorPatente("abc123");
		
		
	}
	
	*/

}
