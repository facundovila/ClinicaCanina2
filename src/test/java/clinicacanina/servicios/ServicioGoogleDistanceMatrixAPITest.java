package clinicacanina.servicios;



import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

import clinicacanina.controladores.ControladorAmbulancia;

public class ServicioGoogleDistanceMatrixAPITest {

	private ServicioGoogleDistanceMatrixAPI servicioGoogleDistanceMatrixAPI;
	
	@Before
	public void init() {
		servicioGoogleDistanceMatrixAPI = new ServicioGoogleDistanceMatrixAPI();
		
	}
	
	@Test
	public void queSePuedaConsultarLaDistanciaYTiempoDeUnPuntoEspecifico() {
		String[] respuesta = cuandoConsultoPorUnDestino();
		
		obtengoLosDatosCorrespondientes(respuesta);
	}

	private void obtengoLosDatosCorrespondientes(String[] respuesta) {
		assertThat(respuesta[2]).isEqualTo("25.5 km");
		
	}

	private String[] cuandoConsultoPorUnDestino() {
		String[] respuesta = null;
		try{ respuesta = servicioGoogleDistanceMatrixAPI.getData("alem171montegrande");
		}catch(Exception e) {
			System.out.println(e);
		}
		return respuesta;
	}
	
	
	
}
