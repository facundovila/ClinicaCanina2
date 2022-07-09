package clinicacanina.servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import clinicacanina.controladores.ControladorAmbulancia;

public class NavegacionTest {
	
	private Navegacion navegacion;
	private final LocalDateTime AHORA = LocalDateTime.now();
	private final String MINUTOS_ESTIMADOS = "41";

	@Before
	public void init() {
		navegacion = new NavegacionImpl();
	}
	
	@Test
	public void queSePuedaObtenerLaHoraActual() {
		
		LocalDateTime horarioActual = navegacion.obtenerHoraActual();
		
		System.out.println(horarioActual.getHour() + ":" + horarioActual.getMinute()+ ":" + horarioActual.getSecond());
		assertThat(horarioActual.getHour()).isEqualTo(AHORA.getHour());
		assertThat(horarioActual.getMinute()).isEqualTo(AHORA.getMinute());
		assertThat(horarioActual.getSecond()).isEqualTo(AHORA.getSecond());
		
	}
	
	@Test
	public void queSePuedaCalcularElHorarioEstimadoDeLlegadaDeLaAmabulancia() {
		Integer tiempoEstimadoDeLlegada = dadoUnTiempoEstimadoDeLlegadaDeLaAmbulancia();
		LocalDateTime horarioEstimadoDeLlegada= cuandoConsultoElHorarioEstimadoDeLlegada(tiempoEstimadoDeLlegada);
		entoncesObtengoLoQueFaltaParaQueLlegue(horarioEstimadoDeLlegada);
	}

	private void entoncesObtengoLoQueFaltaParaQueLlegue(LocalDateTime horarioEstimadoDeLlegada) {
		System.out.println("Horario estimado de Llegada :" + horarioEstimadoDeLlegada);
		String minutosEstimados = "Restan :" + horarioEstimadoDeLlegada.getMinute() + " Minutos";
		String minutosEstimadosEsperados = "Restan :" + MINUTOS_ESTIMADOS + " Minutos";
		
		assertThat(minutosEstimados).isEqualTo(minutosEstimadosEsperados);
		
		
	}

	private LocalDateTime cuandoConsultoElHorarioEstimadoDeLlegada(Integer tiempo) {
		return navegacion.calcularHorarioDeLlegada(tiempo);
		
	}

	private Integer dadoUnTiempoEstimadoDeLlegadaDeLaAmbulancia() {
		return 10;
	}

	
	
	
}
