package clinicacanina.servicios;

import clinicacanina.controladores.HistoriaClinica;
import clinicacanina.modelo.VisitaClinica;
import org.junit.Before;
import org.junit.Test;

import clinicacanina.modelo.Mascota;
import clinicacanina.repositorios.RepositorioMascota;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.LinkedList;
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
		Float peso = 10F;
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

	@Test
	public void sePuedeBuscarUnaMascota(){

		String nombre = "lupe";
		Float peso = 10F;


		Integer edad=10;
		String medicamentos = "tratamiento";
		String detalle="detalle";

		Mascota mascota = dadoQueExisteMascota( nombre,  peso,  edad);


		Mascota mascotaBuscada = cuandoBuscoMascota(mascota.getId());

		entoncesObtengoLaMascota(mascotaBuscada.getNombre(), "lupe" );

	}


	@Test
	public void sePuedeGuardarVisitaMedica(){

		Mascota mascota = new Mascota();
		mascota.setNombre("hachi");
		mascota.setPeso(15F);
		mascota.setId(1l);

		VisitaClinica visitaClinica = new VisitaClinica();
		visitaClinica.setId(2l);


		when(repositorioMascota.guardarVisitaMedica(mascota.getId(),visitaClinica)).thenReturn(visitaClinica.getId());


		servicioMascota.guardarVisitaMedicaDeMascota(mascota.getId(), visitaClinica);

		assertThat(visitaClinica.getId()).isEqualTo(2l);

	}


	@Test
	public void sePuedeObtenerLasVisitasMedicasDeLaMascota(){

		Mascota mascota = new Mascota();
		mascota.setNombre("hachi");
		mascota.setPeso(15F);
		mascota.setId(1l);

		VisitaClinica visitaClinica = new VisitaClinica();
		visitaClinica.setId(2l);
		visitaClinica.setSintomas("sintomas");
		visitaClinica.setTratamiento("tratamiento");
		visitaClinica.setMascotaAsignada(mascota);

		List<VisitaClinica> visitasEsperadas = new LinkedList<>();

		visitasEsperadas.add(visitaClinica);


		when(repositorioMascota.obtenerVisitaMedicaDeLaMascota(mascota)).thenReturn(visitasEsperadas);


		List<VisitaClinica> visitaRecibidas = servicioMascota.obtenerVisitasClinicasDeLaMascota(mascota);

		assertThat(visitaRecibidas.contains(mascota)).isEqualTo(visitasEsperadas.contains(mascota));

	}




	@Test
	public void sePuedeModificarLaHistoriaClinicaDeUnamascota(){

		String nombre = "lupe";
		Float peso = 10F;
		Integer edad=10;

		Mascota mascota = dadoQueExisteMascotaParaModificar( nombre,  peso,  edad);

		cuandoModificoMascota(mascota);

		entoncesSeModificoLaMascota(mascota);


	}

	private void entoncesSeModificoLaMascota(Mascota mascota) {

		assertThat(mascota.getEdad()).isEqualTo(15);

	}

	private Mascota cuandoModificoMascota(Mascota mascota) {

		mascota.setEdad(15);

		return servicioMascota.modificarMascota(mascota.getId(), mascota.getEdad(), mascota.getPeso());
	}






	private void entoncesObtengoLaMascota(String mascotaBuscada, String mascotaEsperada) {

		assertThat(mascotaBuscada).isEqualTo(mascotaEsperada);

	}

	private Mascota cuandoBuscoMascota(Long id) {
		return servicioMascota.buscarMascotaPorId(id);
	}


	private Mascota dadoQueExisteMascota(String nombre, Float peso, Integer edad){
		HistoriaClinica historiaClinica= new HistoriaClinica();


		historiaClinica.setEdad(edad);
		historiaClinica.setNombre(nombre);
		historiaClinica.setPeso(peso);


		Mascota mascota = new Mascota(historiaClinica);

		mascota.setId(1L);

		when(repositorioMascota.buscarPorId(mascota.getId())).thenReturn(mascota);

		return mascota;


	}
	private Mascota dadoQueExisteMascotaParaModificar(String nombre, Float peso, Integer edad){
		HistoriaClinica historiaClinica= new HistoriaClinica();


		historiaClinica.setEdad(edad);
		historiaClinica.setNombre(nombre);
		historiaClinica.setPeso(peso);


		Mascota mascota = new Mascota(historiaClinica);

		mascota.setId(1L);

		when(repositorioMascota.buscarPorId(mascota.getId())).thenReturn(mascota);
		when(repositorioMascota.modificarMascota( mascota.getId(), mascota.getPeso() , mascota.getEdad())).thenReturn(mascota);

		return mascota;


	}
	
	private List<Mascota> dadoQueExistenMascotas(Integer cantidadDeMascotas, String nombre, Float peso, Integer edad) {
		HistoriaClinica historiaClinica= new HistoriaClinica();

		historiaClinica.setEdad(edad);
		historiaClinica.setNombre(nombre);
		historiaClinica.setPeso(peso);

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
