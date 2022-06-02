package clinicacanina.controladores;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import clinicacanina.modelo.Ambulancia;
import clinicacanina.servicios.AmbulanciaService;
import static org.assertj.core.api.Assertions.*;

public class ControladorAmbulanciaDeberia {
	
	private final String VISTA_AMBULANCIA_DISPONIBLE = "ambulancia-en-camino";
	private final String VISTA_AMBULANCIA_NO_DISPONIBLE = "no-hay-ambulancias";
	private AmbulanciaController ambulanciaController;
	private AmbulanciaService ambulanciaService;
	
	@Before
	public void init() {
		ambulanciaService = mock(AmbulanciaService.class);
		ambulanciaController = new AmbulanciaController(ambulanciaService);
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
		when(ambulanciaService.buscarAmbulanciaDisponible()).thenReturn(null);
		
	}

	private void dadoQueHayAmbulanciasDisponibles() {
		when(ambulanciaService.buscarAmbulanciaDisponible()).thenReturn(new Ambulancia());
		
	}

	private ModelAndView cuandoPidoUnaAmbulancia() {
		return ambulanciaController.pedirAmbulancia();
	}
	
	private void entoncesMeLlevaALaVistaDe(String VISTA_ESPERADA, String vistaRecibida) {
		assertThat(vistaRecibida).isEqualTo(VISTA_ESPERADA);
		
	}

}
