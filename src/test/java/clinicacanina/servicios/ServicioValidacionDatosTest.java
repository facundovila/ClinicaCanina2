package clinicacanina.servicios;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clinicacanina.repositorios.RepositorioAmbulancia;

import static org.assertj.core.api.Assertions.*;


public class ServicioValidacionDatosTest {
	private ServicioValidacionDatos servicioValidacionDatos;
	
	private final String TELEFONO_VALIDO = "1133804973";
	private final String TELEFONO_INVALIDO = "1133S804973";
	private final String DIRECCION_VALIDA_1 = "Arana 283";
	private final String DIRECCION_VALIDA_2 = "Av Siempre Viva 10455";
	private final String DIRECCION_VALIDA_3 = "Calle Falsa 123";
	private final String DIRECCION_INVALIDA_1 = "Calle Incorrecta No Valida 123";
	private final String DIRECCION_INVALIDA_2 = "777 calle";
	private final String DIRECCION_INVALIDA_3 = "C C";
	private final String MOTIVO_VALIDO = "Fiebre y malestar general";
	private final String MOTIVO_INVALIDO = "Malestar con dolor de cabeza y vomitos";
	private final Integer EDAD_VALIDA = 15;
	private final Integer EDAD_INVALIDA = 156;
	
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
	
	@Test
	public void queSePuedaValidarUnaDireccionIncorrecta() {
		boolean resultado = cuandoValidoDirecciones(DIRECCION_INVALIDA_1,DIRECCION_INVALIDA_2,DIRECCION_INVALIDA_3);
		entoncesObtengoUnaValidacionIncorrecta(resultado);
	}
	
	@Test
	public void queSePuedaValidarUnMotivoValido() {
		boolean resultado = cuandoValidoUnMotivo(MOTIVO_VALIDO);
		entoncesObtengoUnaValidacionCorrecta(resultado);
	}
	
	@Test
	public void queSePuedaValidarUnMotivoInvalido() {
		boolean resultado = cuandoValidoUnMotivo(MOTIVO_INVALIDO);
		entoncesObtengoUnaValidacionIncorrecta(resultado);
	}
	
	
	@Test 
	public void queSePuedaConvertirElHorarioDeSolicitud(){
		String fechaHoraA = "2022-7-10 16:1";
		String fechaHoraB = "2022-7-10 1:17";
		String fechaHoraC = "2022-7-10 0:0";
		String fechaHoraD = "2022-7-10 11:11";
		
		
		List<String> fechaHoraConvertida = cuandoValidoUnaFechaHora(fechaHoraA,fechaHoraB,fechaHoraC,fechaHoraD);
		obtengoLaFechaHoraConvertida(fechaHoraConvertida);
	}
	
	@Test
	public void queSePuedaConvertirElHorarioDeLlegada() {
		String HoraA = "16:1";
		String HoraB = "1:17";
		String HoraC = "0:0";
		String HoraD = "11:11";
		
		
		List<String> HoraConvertida = cuandoValidoUnHorario(HoraA,HoraB,HoraC,HoraD);
		obtengoLaHoraConvertida(HoraConvertida);
	}
	

	private void obtengoLaHoraConvertida(List<String> horaConvertida) {
		String formatoEsperadoA = "16:01";
		String formatoEsperadoB = "01:17";
		String formatoEsperadoC = "00:00";
		String formatoEsperadoD = "11:11";
		System.out.println("\n*************** Hora Conversion*************\n");
		System.out.println(horaConvertida.get(0));
		System.out.println(horaConvertida.get(1));
		System.out.println(horaConvertida.get(2));
		System.out.println(horaConvertida.get(3));
		assertThat(horaConvertida.get(0)).isEqualTo(formatoEsperadoA);
		assertThat(horaConvertida.get(1)).isEqualTo(formatoEsperadoB);
		assertThat(horaConvertida.get(2)).isEqualTo(formatoEsperadoC);
		assertThat(horaConvertida.get(3)).isEqualTo(formatoEsperadoD);
		
	}

	private List<String> cuandoValidoUnHorario(String horaA, String horaB, String horaC, String horaD) {
		List<String> listaHora = new LinkedList<>();
		listaHora.add(servicioValidacionDatos.validarHorario(horaA));
		listaHora.add(servicioValidacionDatos.validarHorario(horaB));
		listaHora.add(servicioValidacionDatos.validarHorario(horaC));
		listaHora.add(servicioValidacionDatos.validarHorario(horaD));
		return listaHora;
		
	}

	private void obtengoLaFechaHoraConvertida(List<String> fechaHoraConvertida) {
		String formatoEsperadoA = "2022-7-10 16:01";
		String formatoEsperadoB = "2022-7-10 01:17";
		String formatoEsperadoC = "2022-7-10 00:00";
		String formatoEsperadoD = "2022-7-10 11:11";
		System.out.println(fechaHoraConvertida.get(0));
		System.out.println(fechaHoraConvertida.get(1));
		System.out.println(fechaHoraConvertida.get(2));
		System.out.println(fechaHoraConvertida.get(3));
		assertThat(fechaHoraConvertida.get(0)).isEqualTo(formatoEsperadoA);
		assertThat(fechaHoraConvertida.get(1)).isEqualTo(formatoEsperadoB);
		assertThat(fechaHoraConvertida.get(2)).isEqualTo(formatoEsperadoC);
		assertThat(fechaHoraConvertida.get(3)).isEqualTo(formatoEsperadoD);
		
		
	}

	private List<String> cuandoValidoUnaFechaHora(String fechaHoraA, String fechaHoraB, String fechaHoraC, String fechaHoraD) {
		List<String> listaFechaHora = new LinkedList<>();
		listaFechaHora.add(servicioValidacionDatos.validarHorario(fechaHoraA));
		listaFechaHora.add(servicioValidacionDatos.validarHorario(fechaHoraB));
		listaFechaHora.add(servicioValidacionDatos.validarHorario(fechaHoraC));
		listaFechaHora.add(servicioValidacionDatos.validarHorario(fechaHoraD));
		return listaFechaHora;
		
	}

	@Test
	public void queSePuedaValidarLaEdadCorrecta(){
		boolean resultado = cuandoValidoLaEdad(EDAD_VALIDA);
		entoncesObtengoUnaValidacionCorrecta(resultado);

	}

	@Test
	public void queSePuedaValidarLaEdadIncorrecta(){
		boolean resultado = cuandoValidoLaEdad(EDAD_INVALIDA);
		entoncesObtengoUnaValidacionIncorrecta(resultado);

	}



	private boolean cuandoValidoLaEdad(Integer edad) {
		return servicioValidacionDatos.validadEdad(edad);
	}

	private boolean cuandoValidoUnMotivo(String motivo) {
		return servicioValidacionDatos.validarMotivo(motivo);
		
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
