package clinicacanina.servicios;



import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.Estado;
import clinicacanina.repositorios.AmbulanciaRepository;

public class AmbulanciaServiceDeberia {
  
	private AmbulanciaRepository ambulanciaRepository;
	private AmbulanciaService ambulanciaService;
	
	@Before
	public void init() {
		ambulanciaRepository = mock(AmbulanciaRepository.class);
		ambulanciaService = new AmbulanciaServiceImpl(ambulanciaRepository);
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
		when(ambulanciaRepository.traerAmbulanciaDisponible()).thenReturn(new Ambulancia());
		
	}

	private void dadoQueNoHayAmbulanciasDisponibles() {
		when(ambulanciaRepository.traerAmbulanciaDisponible()).thenReturn(null);
		
	}

	

	private Ambulancia cuandoBuscoUnaAmbulancia() {
		return ambulanciaService.buscarAmbulanciaDisponible();
		
	}

	
	
	private void entoncesPuedoReservarla(Ambulancia ambulancia) {
		verify(ambulanciaRepository, times(1)).reservarAmbulancia(any(Ambulancia.class));
		
	}
	
	private void entoncesNoPuedoReservarla(Ambulancia ambulancia) {
		verify(ambulanciaRepository, never()).reservarAmbulancia(any(Ambulancia.class));
		
	}
}
