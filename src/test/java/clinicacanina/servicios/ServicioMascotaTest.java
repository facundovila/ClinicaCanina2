package clinicacanina.servicios;

import clinicacanina.controladores.HistoriaClinica;
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

		Mascota mascota = dadoQueExisteMascota( nombre,  peso,  edad,  medicamentos,  detalle);


		Mascota mascotaBuscada = cuandoBuscoMascota(mascota.getId());

		entoncesObtengoLaMascota(mascotaBuscada.getNombre(), "lupe" );

	}

	@Test
	public void sePuedeModificarLaHistoriaClinicaDeUnamascota(){


		String nombre = "lupe";
		Integer peso = 10;
		Integer edad=10;
		String medicamentos = "tratamiento";
		String detalle="detalle";

		Mascota mascota = dadoQueExisteMascotaParaModificar( nombre,  peso,  edad,  medicamentos,  detalle);

		String detalleTratamientoCambiado = "pipeta anti pulgas";


		Mascota mascotaModificada = dadoQueExisteMascotaParaModificar(nombre,  peso,  edad,  medicamentos,  detalleTratamientoCambiado);


		when(repositorioMascota.modificarMascota(mascota.getId(), mascota.getSintomas(), detalleTratamientoCambiado, mascota.getPeso(), mascota.getEdad(), mascota.getNombre())).thenReturn(mascotaModificada);


		servicioMascota.modificarMascota(mascota.getId(),  detalleTratamientoCambiado, medicamentos,edad, peso, nombre );


		entoncesSeModificoLaHistoriaClinicaDeLaMascota(mascotaModificada.getDetalleTratamientos(),"pipeta anti pulgas" );






	}

	private void entoncesSeModificoLaHistoriaClinicaDeLaMascota(String detalleTratamientos, String detalleTratamientoCambiado) {

		assertThat(detalleTratamientos).isEqualTo(detalleTratamientoCambiado);

	}




	private void entoncesObtengoLaMascota(String mascotaBuscada, String mascotaEsperada) {

		assertThat(mascotaBuscada).isEqualTo(mascotaEsperada);

	}

	private Mascota cuandoBuscoMascota(Long id) {
		return servicioMascota.buscarMascotaPorId(id);
	}


	private Mascota dadoQueExisteMascota(String nombre, Integer peso, Integer edad, String medicamentos, String detalle){
		HistoriaClinica historiaClinica= new HistoriaClinica();

		historiaClinica.setDetalleTratamientos(detalle);
		historiaClinica.setEdad(edad);
		historiaClinica.setNombre(nombre);
		historiaClinica.setPeso(peso);
		historiaClinica.setSintomas(medicamentos);

		Mascota mascota = new Mascota(historiaClinica);

		mascota.setId(1L);

		when(repositorioMascota.buscarPorId(mascota.getId())).thenReturn(mascota);

		return mascota;


	}
	private Mascota dadoQueExisteMascotaParaModificar(String nombre, Integer peso, Integer edad, String medicamentos, String detalle){
		HistoriaClinica historiaClinica= new HistoriaClinica();

		historiaClinica.setDetalleTratamientos(detalle);
		historiaClinica.setEdad(edad);
		historiaClinica.setNombre(nombre);
		historiaClinica.setPeso(peso);
		historiaClinica.setSintomas(medicamentos);

		Mascota mascota = new Mascota(historiaClinica);

		mascota.setId(1L);

		String detalleTratamientoCambiado = "pipeta anti pulgas";
		when(repositorioMascota.buscarPorId(mascota.getId())).thenReturn(mascota);

		return mascota;


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
