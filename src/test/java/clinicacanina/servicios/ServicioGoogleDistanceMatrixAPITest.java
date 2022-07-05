package clinicacanina.servicios;



import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

import clinicacanina.controladores.ControladorAmbulancia;
import clinicacanina.repositorios.Trayecto;

public class ServicioGoogleDistanceMatrixAPITest {

	private ServicioGoogleDistanceMatrixAPI servicioGoogleDistanceMatrixAPI;
	
	@Before
	public void init() {
		servicioGoogleDistanceMatrixAPI = new ServicioGoogleDistanceMatrixAPI();
		
	}
	
	@Test
	public void queSePuedaConsultarLaDistanciaYTiempoDeUnPuntoEspecifico() {
		Trayecto trayecto = cuandoConsultoPorUnDestino();
		
		obtengoLosDatosCorrespondientes(trayecto);
	}

	private void obtengoLosDatosCorrespondientes(Trayecto trayecto) {
		assertThat(trayecto.getDistancia()).isEqualTo("25.5 km");
		
	}

	private Trayecto cuandoConsultoPorUnDestino() {
		Trayecto trayecto = null;
		try{ trayecto = servicioGoogleDistanceMatrixAPI.getDistance("alem171montegrande");
		}catch(Exception e) {
			System.out.println(e);
		}
		return trayecto;
	}
	
	
	
}
