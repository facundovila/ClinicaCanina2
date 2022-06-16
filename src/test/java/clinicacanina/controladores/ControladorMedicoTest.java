package clinicacanina.controladores;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import clinicacanina.modelo.Medico;
import clinicacanina.servicios.ServicioMedico;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorMedicoTest { 

	
	private ControladorMedico controladorMedico;
	private ServicioMedico servicioMedico;
	
	@Before
    public void init(){
		servicioMedico = mock(ServicioMedico.class);
		controladorMedico = new ControladorMedico(servicioMedico);	
	}
	
    public static final String VISTA_ESPERADA = "medicos";
   
    @Test
	public void mostrarMedicosExistentes() { 
		//preparacion
		String nombre = "Marcelo";
		Integer horarioEntrada = 8;
		Integer horarioSalida = 20;
		
		dadoQueExistaMedico(nombre,horarioEntrada,horarioSalida);
		
		//ejecucion
		ModelAndView mav =cuandoMuestroTodosLosMedicos();//

		//validacion
		entoncesMeLlevaALaVista(VISTA_ESPERADA,mav);
	}
    


	private void dadoQueExistaMedico(String nombre, Integer horarioEntrada, Integer horarioSalida) {
		List <Medico> listaDeMedicos = new ArrayList<>();
		for(int i = 0; i <= 1 ; i++) {
			listaDeMedicos.add(new Medico(nombre,horarioEntrada,horarioSalida));
		}
		when(servicioMedico.listarMedico()).thenReturn(listaDeMedicos);
	}
	
	private ModelAndView cuandoMuestroTodosLosMedicos() {
		return controladorMedico.mostrarTodosLosMedicos();
	
	}

	private void entoncesMeLlevaALaVista(String vista, ModelAndView mav) {
		assertThat(mav.getViewName()).isEqualTo(vista);		
	}
	

}
