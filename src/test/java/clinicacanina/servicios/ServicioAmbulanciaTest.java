package clinicacanina.servicios;



import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.Estado;
import clinicacanina.repositorios.RepositorioAmbulancia;


public class ServicioAmbulanciaTest {
  
	private RepositorioAmbulancia repositorioAmbulancia;
	private ServicioAmbulancia servicioAmbulancia;
	
	@Before
	public void init() {
		repositorioAmbulancia = mock(RepositorioAmbulancia.class);
		servicioAmbulancia = new ServicioAmbulanciaImpl(repositorioAmbulancia);
	}
	
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
		
	}
}
