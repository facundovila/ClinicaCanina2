package clinicacanina.servicios;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import clinicacanina.modelo.Medico;
import clinicacanina.repositorios.RepositorioMedico;
import clinicacanina.servicios.ServicioMedico;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServicioMedicoTest {
	
	private RepositorioMedico repositorioMedico;
	private ServicioMedico servicioMedico;
	
	@Before
	public void init() {
		repositorioMedico = mock(RepositorioMedico.class);
		servicioMedico = new ServicioMedicoImpl(repositorioMedico);
	}
	
	@Test
	public void mostrarLaListaDeMedicos() {
		//preparacion
		String nombre = "Marcelo";
		Integer horarioEntrada = 8;
		Integer horarioSalida = 20;
		Integer cantidadDeMedicosEsperada = 2;
		
		dadoQueExistenMedicos(nombre,horarioEntrada,horarioSalida);//creo el medico
		//ejecucion
		List<Medico> listaDeMedicos= cuandoListoTodosLosMedicos(); 
		//validacion
		entoncesEncuentroLaListaDeMedicos(listaDeMedicos,cantidadDeMedicosEsperada); // 
		
		
	}
	
	private void dadoQueExistenMedicos(String nombre, Integer horarioEntrada, Integer horarioSalida) {
		List <Medico> listaDeMedicos = new ArrayList<>();
		for(int i = 0; i < 2 ; i++) {
			listaDeMedicos.add(new Medico(nombre,horarioEntrada,horarioSalida));
		}
		when(repositorioMedico.traerTodosLosMedicos()).thenReturn(listaDeMedicos);
	}
	
	private List<Medico> cuandoListoTodosLosMedicos() {
		return servicioMedico.listarMedico();
	}
	
	private void entoncesEncuentroLaListaDeMedicos(List<Medico> listaDeMedicos, Integer cantidadDeMedicosEsperada) {
		assertThat(listaDeMedicos).hasSize(cantidadDeMedicosEsperada);
	}


}
