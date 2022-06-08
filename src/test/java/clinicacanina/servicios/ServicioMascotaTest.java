package clinicacanina.servicios;

import org.junit.Before;
import org.junit.Test;

import clinicacanina.modelo.HistoriaClinica;
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
		String medicamentos = "tratamiento";
		String detalle="detalle";
		
		List<Mascota> mascota = dadoQueExistenMascotas(cantidadDeMascotas,nombre,peso,edad, medicamentos, detalle);//crea Mascotas
		when(repositorioMascota.buscarTodasLasMascotas()).thenReturn(mascota);


		//ejecucion buscar toda la lista de Mascotas
		List <Mascota> mascotaBuscada = cuandoBuscoTodasLasMascotas();
		//devuelve una lista nueva
		
		//validacion
		obtenerTodasLasMascotas(mascotaBuscada,cantidadDeMascotasEsperadas);

	}

	@Test
	public void sePuedeBuscarUnaMascota(){

		String nombre = "lupe";
		Integer peso = 10;


		Integer edad=10;
		String medicamentos = "tratamiento";
		String detalle="detalle";
		Long id= Long.valueOf(1);
		Mascota mascota = dadoQueExistenMascota( nombre,  peso,  edad,  medicamentos,  detalle);


		when(repositorioMascota.buscarPorId(id)).thenReturn(mascota);

		Mascota mascotaBuscada = cuandoBuscoMascota(id);

		entoncesObtengoLaMascota(mascotaBuscada.getNombre(), "lupe" );

	}

	private void entoncesObtengoLaMascota(String mascotaBuscada, String mascotaEsperada) {

		assertThat(mascotaBuscada).isEqualTo(mascotaEsperada);

	}

	private Mascota cuandoBuscoMascota(Long id) {
		return servicioMascota.buscarMascotaPorId(id);
	}


	private Mascota dadoQueExistenMascota(String nombre, Integer peso, Integer edad, String medicamentos, String detalle){
		HistoriaClinica historiaClinica= new HistoriaClinica();

		historiaClinica.setDetalleTratamientos(detalle);
		historiaClinica.setEdad(edad);
		historiaClinica.setNombre(nombre);
		historiaClinica.setPeso(peso);
		historiaClinica.setSintomas(medicamentos);

		return new Mascota(historiaClinica);


	}
	
	private List<Mascota> dadoQueExistenMascotas(Integer cantidadDeMascotas, String nombre, Integer peso, Integer edad, String medicamentos, String detalle) {
		HistoriaClinica historiaClinica= new HistoriaClinica();

		historiaClinica.setDetalleTratamientos(detalle);
		historiaClinica.setEdad(edad);
		historiaClinica.setNombre(nombre);
		historiaClinica.setPeso(peso);
		historiaClinica.setSintomas(medicamentos);

		List<Mascota> mascota1 = new ArrayList<>();
		for (int i = 0; i < cantidadDeMascotas; i++) {
			mascota1.add(new Mascota(historiaClinica));
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
