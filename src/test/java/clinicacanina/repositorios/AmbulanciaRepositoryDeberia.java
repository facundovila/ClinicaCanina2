package clinicacanina.repositorios;

import org.springframework.beans.factory.annotation.Autowired;

import clinicacanina.SpringTest;
import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.Estado;

import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class AmbulanciaRepositoryDeberia extends SpringTest{
	private final Estado ESTADO_ESPERADO = Estado.EN_CAMINO;
	private final Estado PRIMER_ESTADO_RESERVADA = Estado.EN_PREPARACION;
	private final String PATENTE_ESPERADA = "abc123";
	
	
	@Autowired
	private AmbulanciaRepository ambulanciaRepository;
	
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
		ambulanciaRepository.reservarAmbulancia(patente);
		return ambulanciaRepository.buscarPorPatente(patente);
		
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
	   ambulanciaRepository.actualizarEstado("abc123");
	   
	   assertThat(ambulanciaAActualizar.getEstado()).isEqualTo(ESTADO_ESPERADO);
	   
		
	}

	private Ambulancia cuandoBuscoLaAmbulanciaReservada() {
		Ambulancia ambulancia = new Ambulancia();
		ambulancia.setDisponible(false);
		ambulancia.setEstado(Estado.EN_COCHERA);
		ambulancia.setPatente("abc123");
		
		session().save(ambulancia);
		
		return ambulanciaRepository.buscarPorPatente("abc123");
		
		
	}
	
	

}
