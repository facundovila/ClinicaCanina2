package clinicacanina.controladores;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import clinicacanina.modelo.Ambulancia;
import clinicacanina.servicios.ServicioAmbulancia;
import static org.assertj.core.api.Assertions.*;

public class ControladorAmbulanciaTest {
	
	private final String VISTA_AMBULANCIA_DISPONIBLE = "ambulancia-en-camino";
	private final String VISTA_AMBULANCIA_NO_DISPONIBLE = "no-hay-ambulancias";
	private ControladorAmbulancia controladorAmbulancia;
	private ServicioAmbulancia servicioAmbulancia;
	
	@Before
	public void init() {
		servicioAmbulancia = mock(ServicioAmbulancia.class);
		controladorAmbulancia = new ControladorAmbulancia(servicioAmbulancia);
	}
	
	@Test
	public void poderSolicitarUnaAmbulancia() {
		dadoQueHayAmbulanciasDisponibles();
		ModelAndView mav = cuandoPidoUnaAmbulancia();
		entoncesMeLlevaALaVistaDe(VISTA_AMBULANCIA_DISPONIBLE, mav.getViewName());
	}
	
	@Test
	public void noPoderSolicitarUnaAbulancia() {
		dadoQueNoHayAmbulanciasDisponibles();
		ModelAndView mav = cuandoPidoUnaAmbulancia();
		entoncesMeLlevaALaVistaDe(VISTA_AMBULANCIA_NO_DISPONIBLE, mav.getViewName());
		}

	private void dadoQueNoHayAmbulanciasDisponibles() {
		when(servicioAmbulancia.buscarAmbulanciaDisponible()).thenReturn(null);
		
	}

	private void dadoQueHayAmbulanciasDisponibles() {
		when(servicioAmbulancia.buscarAmbulanciaDisponible()).thenReturn(new Ambulancia());
		
	}

	private ModelAndView cuandoPidoUnaAmbulancia() {
		return controladorAmbulancia.pedirAmbulancia();
	}
	
	private void entoncesMeLlevaALaVistaDe(String VISTA_ESPERADA, String vistaRecibida) {
		assertThat(vistaRecibida).isEqualTo(VISTA_ESPERADA);
		
	}

}
