package clinicacanina.repositorios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Medico;
import clinicacanina.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import clinicacanina.SpringTest;
import clinicacanina.modelo.Turno;

@Repository
public class RepositorioTurnosTest extends SpringTest {


	@Autowired
	private RepositorioTurnos repositorioTurnos;

	@Test
	@Transactional
	@Rollback
	public void guardarUnTurnoDeberiaPersistirlo() {
		//preparacion
		Turno turno = dadoQueExisteTurno("fecha");
		//ejecucion
		String fecha = cuandoGuardoTurno(turno);
		//validacion
		entoncesEncuentroTurno(fecha);
	}

	@Test
	@Transactional
	@Rollback()
	public void creaUnTurnoYLoBuscaPorFecha() {

		Turno turno1 = dadoQueExisteTurno("fecha1");
		Turno turno2 = dadoQueExisteTurno("fecha1");
		Turno turno3 = dadoQueExisteTurno("fecha3");
		cuandoGuardoTurno(turno1);
		cuandoGuardoTurno(turno2);
		cuandoGuardoTurno(turno3);
		List<Turno> turnoBuscado = cuandoBuscoTurnoPorFecha("fecha1");
		Integer cantidadEsperada = 2;
		entoncesEncuentroTurno(turnoBuscado, cantidadEsperada);
	}

	private void entoncesEncuentroTurno(List<Turno> turnoBuscado, Integer cantidadEsperada) {
		// TODO Auto-generated method stub
		assertThat(turnoBuscado).hasSize(cantidadEsperada);
	}

	private List<Turno> cuandoBuscoTurnoPorFecha(String fecha) {
		return repositorioTurnos.buscarPorFecha(fecha);
	}

	private void entoncesEncuentroTurno(String fecha) {
		List<Turno> turno = repositorioTurnos.buscarPorFecha(fecha);
		assertThat(turno).isNotNull();
	}

	private String cuandoGuardoTurno(Turno turno) {
		turno.setEstado(true);
		repositorioTurnos.guardarTurno(turno);
		return turno.getFecha();
	}

	private Turno dadoQueExisteTurno(String fecha) {
		Turno turno = new Turno();
		turno.setFecha(fecha);
		turno.setEstado(false);
		return turno;
	}


	@Test
	@Transactional
	@Rollback
	public void puedoBuscarLosTurnosPorelIdDelUsuario() {
		List <Turno> lista= new ArrayList<>();
		lista= repositorioTurnos.mostrarTurnoUsuarioDesdeHoy(1l);
		assertThat(lista.isEmpty()).isTrue();
	}
	@Test
	@Transactional
	@Rollback
	public void GuardoTurnosYBuscoPorIdDeUnUsuarioRegresaListaDETurnos(){
		long idUsuario=1L;
		CuandoCArgoDosTurnosAUnUsuario(idUsuario);
		List<Turno> lista = cuandoBuscoAlUsuarioConDosTurnos(idUsuario);
		testeoQueREgresenDosTurnos(lista);
	}

	private void testeoQueREgresenDosTurnos(List<Turno> lista) {
		assertThat(lista.size()).isEqualTo(2);
	}

	private void CuandoCArgoDosTurnosAUnUsuario(long idUsuario) {
		Usuario usuario1= new Usuario();
		usuario1.setId(idUsuario);
		Usuario usuario2= new Usuario();
		usuario1.setId(2L);
		session().save(usuario1);
		session().save(usuario2);

		Turno turno1 = new Turno();
		turno1.setUsuario(usuario1);
		turno1.setId(1L);
		Turno turno2 = new Turno();
		turno2.setId(2L);
		turno2.setUsuario(usuario2);
		Turno turno3 = new Turno();
		turno3.setId(3L);
		turno3.setUsuario(usuario1);

		session().save(turno1);
		session().save(turno2);
		session().save(turno3);
	}
	private List<Turno>cuandoBuscoAlUsuarioConDosTurnos(long idUsuario){
		return repositorioTurnos.mostarTodosTurnosDelUsuario(idUsuario);
	}
	@Test
	@Transactional
	@Rollback
	public void puedoCancelarTurnoSinModificarFechaNiMedico(){
		// preparacion
		long idTurno=1l;
		long idMedico=2L;
		Calendar calendario = new GregorianCalendar(2022,06,06);

		Turno turno = CuandoCargoUnTurnoAUnUsuario(idTurno, idMedico,calendario);
		// ejecucion
		alCancelarElturnoDelUsuario(turno.getId());
		//comparacion
		entoncesBuscoElTurnoYMeMuestraSoloIdFechaYmeDico(turno.getId(), idMedico,calendario);
	}
	private void entoncesBuscoElTurnoYMeMuestraSoloIdFechaYmeDico(long idTurno, long idMedico, Calendar calendario) {
		Turno turnobuscado= repositorioTurnos.buscarTurnoPorId(idTurno);
		System.out.println("\n******************TURNO BUSCADO : " + turnobuscado+"**************************************\n");
		assertThat(turnobuscado.getFechaTurno()).isEqualTo(calendario);
		assertThat(turnobuscado.getMedico().getId()).isEqualTo(idMedico);
		assertThat(turnobuscado.getEstado()).isFalse();
		assertThat(turnobuscado.getMascota()).isNull();
		assertThat(turnobuscado.getUsuario()).isNull();
	}

	private void alCancelarElturnoDelUsuario(long idTurno) {
		repositorioTurnos.cancelarTurnoPorId(idTurno);
	}

	private Turno CuandoCargoUnTurnoAUnUsuario(long idTurno, long idMedico, Calendar calendario) {
		Usuario usuario1 = new Usuario();
		usuario1.setId(1L);
		session().save(usuario1);

		Mascota mascota1 = new Mascota();
		mascota1.setId(1L);
		session().save(mascota1);

		Medico medico= new Medico();
		medico.setId(idMedico);
		session().save(medico);

		Turno turno1 = new Turno();
		turno1.setId(idTurno);
		turno1.setUsuario(usuario1);
		turno1.setMedico(medico);
		turno1.setMascota(mascota1);
		turno1.setEstado(false);
		turno1.setFechaTurno(calendario);
		session().save(turno1);
		return turno1;
	}


}