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


	private void laMismaDejaDeEstarDisponible(Ambulancia ambulancia) {
		assertThat(NO_DISPONIBLE).isEqualTo(ambulancia.getDisponibilidad());
		
	}


	private void cuandoReservoUnaAmbulancia(Ambulancia ambulancia) {
		servicioAmbulancia.reservarAmbulancia(ambulancia);
		
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
	
	
	
	
	
	/*
	
	@Test
	public void poderBuscarAmbulanciasDisponiblesYReservarla() {
		dadoQueHayAmbulanciasDisponibles();
		Ambulancia ambulancia = cuandoBuscoUnaAmbulancia();
		entoncesPuedoReservarla(ambulancia);
		
	}
	
	
	@Test
	public void noPoderReservarUnaAmbulanciaSiNoEstaDisponible() {
		dadoQueNoHayAmbulanciasDisponibles();
		Ambulancia ambulancia = cuandoBuscoUnaAmbulancia();
		entoncesNoPuedoReservarla(ambulancia);
		
	}

	private void dadoQueHayAmbulanciasDisponibles() {
		when(repositorioAmbulancia.traerAmbulanciaDisponible()).thenReturn(new Ambulancia());
		
	}

	private void dadoQueNoHayAmbulanciasDisponibles() {
		when(repositorioAmbulancia.traerAmbulanciaDisponible()).thenReturn(null);
		
	}

	

	private Ambulancia cuandoBuscoUnaAmbulancia() {
		return servicioAmbulancia.buscarAmbulanciaDisponible();
		
	}

	
	
	private void entoncesPuedoReservarla(Ambulancia ambulancia) {
		verify(repositorioAmbulancia, times(1)).reservarAmbulancia(anyString());
		
	}
	
	private void entoncesNoPuedoReservarla(Ambulancia ambulancia) {
		verify(repositorioAmbulancia, never()).reservarAmbulancia(anyString());
		
	}*/
}
