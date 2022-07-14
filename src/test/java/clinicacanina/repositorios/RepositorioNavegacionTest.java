package clinicacanina.repositorios;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.*;

import clinicacanina.SpringTest;
import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.Navegador;
import clinicacanina.modelo.ReservaDeAmbulancia;

public class RepositorioNavegacionTest extends SpringTest{
	@Autowired
    private RepositorioNavegacion repositorioNavegacion;
	
	@Test @Transactional @Rollback
	public void queSePuedaGuardarUnaNavegacionYLuegoBuscarla() {
		Navegador navegadorCreado = crearNavegador();
		Navegador navegador = cuandoGuardoUnaNavegacion(navegadorCreado);
		entoncesPuedoBuscarlaYEncontrarElObjetoNavegador(navegador);
		
	}
	
	private void entoncesPuedoBuscarlaYEncontrarElObjetoNavegador(Navegador navegador) {
		assertThat(navegador).isNotNull();
		
	}

	private Navegador cuandoGuardoUnaNavegacion(Navegador navegador) {
		repositorioNavegacion.guardarNavegacion(navegador);
		//session().save(navegador);
		
		//-------------Busco lo guardado -------------------------
		return repositorioNavegacion.buscarNavegacion(navegador.getPatente());
	}

	private Navegador crearNavegador() {
		ReservaDeAmbulancia reserva = new ReservaDeAmbulancia();
		Trayecto trayecto = new Trayecto();
		Ambulancia ambulancia = new Ambulancia();
		Navegador navegador = new Navegador();
	    
		//-------------- Datos de Ambulancia ---------------
        ambulancia.setPatente("ABC123");
        ambulancia.setDisponibilidad(false);
        //-------------- Datos Reserva ---------------------
        reserva.setAmbulancia(ambulancia);
        reserva.setDireccion("alem171montegrande");
        reserva.setMotivo("Fiebre y vomitos");
        reserva.setTelefono("1122334455");
        //-------------- Datos Trayecto --------------------
        trayecto.setLocalidadOrigen("FlorencioVarela1903SanJusto");
        trayecto.setLocalidadDestino(reserva.getDireccion());
        trayecto.setDistancia("30km");
        trayecto.setTiempo("45min");
        //-------------- Datos Navegador -------------------
        navegador.setReserva(reserva);
        navegador.setLocalidadOrigen(trayecto.getLocalidadOrigen());
        navegador.setLocalidadDestino(trayecto.getLocalidadDestino());
        navegador.setDistancia(trayecto.getDistancia());
        navegador.setTiempoEstimado(trayecto.getTiempo());
        navegador.setPatente(ambulancia.getPatente());
        
        //------------- Guardo Entidades -----------------
        session().save(reserva);
        session().save(ambulancia);
        return navegador;
	}
	
}
