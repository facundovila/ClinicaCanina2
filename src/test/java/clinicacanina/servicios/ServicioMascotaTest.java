package clinicacanina.servicios;

import org.junit.Before;
import org.junit.Test;

import clinicacanina.modelo.Mascota;
import clinicacanina.repositorios.RepositorioMascota;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class ServicioMascotaTest {
	
	private RepositorioMascota repositorioMascota;
	private ServicioMascota servicioMascota;
	
	@Before
	public void init() {
		repositorioMascota = mock(RepositorioMascota.class);
		servicioMascota = new ServicioMascotaImpl(repositorioMascota);
	}
	
	@Test
	public void sePuedenListarTodasLasMascotas() {
		
		//preparacion
		String nombre = "Lupe";
		Integer peso = 10;
		Integer cantidadDeMascotas = 10;
		Integer cantidadDeMascotasEsperadas = 10;
		Integer edad=10;
		
		List<Mascota> mascota = dadoQueExistenMascotas(cantidadDeMascotas,nombre,peso,edad);//crea Mascotas
		when(repositorioMascota.buscarTodasLasMascotas()).thenReturn(mascota);


		//ejecucion buscar toda la lista de Mascotas
		List <Mascota> mascotaBuscada = cuandoBuscoTodasLasMascotas();
		//devuelve una lista nueva
		
		//validacion
		obtenerTodasLasMascotas(mascotaBuscada,cantidadDeMascotasEsperadas);

	}
	
	private List<Mascota> dadoQueExistenMascotas(Integer cantidadDeMascotas, String nombre, Integer peso, Integer edad) {
		List<Mascota> mascota1 = new ArrayList<>();
		for (int i = 0; i < cantidadDeMascotas; i++) {
			mascota1.add(new Mascota(nombre,peso, edad));
		}
		return mascota1;
	}

	private List<Mascota> cuandoBuscoTodasLasMascotas() {
		return servicioMascota.listarMascotas();

	}
	
	private void obtenerTodasLasMascotas(List<Mascota> mascotaBuscada, Integer cantidadDeMascotasEsperadas) {
			assertThat(mascotaBuscada).hasSize(cantidadDeMascotasEsperadas);
//		verify(repositorioMascota, times(1)).buscarTodasLasMascotas(anyList(Mascota.class
//		)));
	}







}
