package clinicacanina.controladores;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import clinicacanina.modelo.Medico;
import clinicacanina.servicios.ServicioMedico;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorMedicoTest { //no necesito que extienda de Spring Test porque no interctua con la BD
	//voy a mockear el servicio

	
	private ControladorMedico controladorMedico;
	private ServicioMedico servicioMedico;
	
	@Before
    public void init(){
		servicioMedico = mock(ServicioMedico.class);
		controladorMedico = new ControladorMedico(servicioMedico);	
	}
	
	private String nombre = "Marcelo";
	private Integer horarioEntrada = 8;
	private Integer horarioSalida = 20;
	
    public static final String VISTA_ESPERADA = "medicos";
    
    
	@Test
	public void mostrarMedicosExistentes() { //crea el medico y lo muestra en la pantalla
		//preparacion
		dadoQueExistaMedico(nombre,horarioEntrada,horarioSalida); //creo el medico
		
		//ejecucion
		ModelAndView mav =cuandoCreoElMedico(nombre,horarioEntrada,horarioSalida);

		//validacion
		entoncesMeLlevaALaVista(VISTA_ESPERADA,mav);
	}
	
	@Test
	public void queElMedicoEsteDisponibleEntreLas8YLas20() {
		
	}
	
	@Test
	public void siSeSolicitaAlMedicoFueraDeSuHorarioQueMuestreUnMensajeDeError() {
		
	}

	private void dadoQueExistaMedico(String nombre, Integer horarioEntrada, Integer horarioSalida) {
		when(servicioMedico.crearMedico(nombre,horarioEntrada,horarioSalida)).thenReturn(new Medico(nombre, horarioEntrada, horarioSalida));
	}
	
	private ModelAndView cuandoCreoElMedico(String nombre, Integer horarioEntrada, Integer horarioSalida) {
		return controladorMedico.mostrarTodosLosMedicos(nombre,horarioEntrada,horarioSalida);
	
	}

	private void entoncesMeLlevaALaVista(String string, ModelAndView mav) {
		assertThat(mav.getViewName()).isEqualTo(VISTA_ESPERADA);		
	}
	

}
