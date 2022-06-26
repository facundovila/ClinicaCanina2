package clinicacanina.servicios;

import static org.mockito.Mockito.*;


import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clinicacanina.repositorios.RepositorioAmbulancia;

import static org.assertj.core.api.Assertions.*;


public class ServicioValidacionDatosTest {
	private ServicioValidacionDatos servicioValidacionDatos;
	private String regex = "";
	private final String TELEFONO_VALIDO = "1133804973";
	private final String TELEFONO_INVALIDO = "1133S804973";
	private final String DIRECCION_VALIDA_1 = "Arana 283";
	private final String DIRECCION_VALIDA_2 = "Av Siempre Viva 10455";
	private final String DIRECCION_VALIDA_3 = "Calle Falsa 123";
	private final String DIRECCION_INVALIDA_1 = "Calle Incorrecta No Valida 123";
	private final String DIRECCION_INVALIDA_2 = "777 calle";
	private final String DIRECCION_INVALIDA_3 = "C C";
	
	@Before
	public void init() {
		
		servicioValidacionDatos = new ServicioValidacionDatosImpl();
	}
	
	@Test
	public void queSePuedaValidarUnNumeroDeTelefonoCorrecto() {
		
		boolean resultado = cuandoValidoUnNumeroDeTelefono(TELEFONO_VALIDO);
		entoncesObtengoUnaValidacionCorrecta(resultado);
	}
	
	@Test
	public void queSePuedaValidarUnNumeroDeTelefonoInvalido() {
		boolean resultado = cuandoValidoUnNumeroDeTelefono(TELEFONO_INVALIDO);
		entoncesObtengoUnaValidacionIncorrecta(resultado);
	}
	
	@Test
	public void queSePuedaValidarUnaDireccionCorrecta() {
		boolean resultado = cuandoValidoDirecciones(DIRECCION_VALIDA_1,DIRECCION_VALIDA_2,DIRECCION_VALIDA_3);
		entoncesObtengoUnaValidacionCorrecta(resultado);
	}

	private boolean cuandoValidoDirecciones(String direccion1, String direccion2,
			String direccion3) {
		boolean primerValidacion = servicioValidacionDatos.validarDireccion(direccion1);
		boolean segundaValidacion = servicioValidacionDatos.validarDireccion(direccion2);
		boolean tercerValidacion = servicioValidacionDatos.validarDireccion(direccion3);
		
		if(primerValidacion && segundaValidacion && tercerValidacion) return true;
		
		return false;
		
	}

	private void entoncesObtengoUnaValidacionIncorrecta(boolean resultado) {
		assertThat(resultado).isEqualTo(false);
		
	}

	private void entoncesObtengoUnaValidacionCorrecta(boolean resultado) {
		assertThat(resultado).isEqualTo(true);
		
	}

	private boolean cuandoValidoUnNumeroDeTelefono(String telefono) {
		return servicioValidacionDatos.validarTelefono(telefono);
		
	}
}
