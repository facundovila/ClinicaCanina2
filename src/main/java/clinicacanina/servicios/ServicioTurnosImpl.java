package clinicacanina.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import clinicacanina.modelo.Turno;
import clinicacanina.repositorios.RepositorioMascota;
import clinicacanina.repositorios.RepositorioTurnos;

public class ServicioTurnosImpl implements ServicioTurnos {
	
	private RepositorioTurnos repositorioTurnos;
	


    @Autowired
    public ServicioTurnosImpl(RepositorioTurnos repositorioTurnos){

        this.setRepositorioTurnos(repositorioTurnos);

    }


	@Override
	public void crearTurno(String fecha, Boolean estado) {
		
		Turno turnoCreado= new Turno(fecha, estado);
		repositorioTurnos.guardarTurno(turnoCreado);
		
	}

	@Override
	public Turno buscarTurno(Turno turno) {
		
		Turno turnoAReservar = null;
		
		if(turno.getEstado()==false) {
		
			turnoAReservar=repositorioTurnos.buscarTurnos(turno);
		}
		return turnoAReservar;
		
			
	}


	public RepositorioTurnos getRepositorioTurnos() {
		return repositorioTurnos;
	}


	public void setRepositorioTurnos(RepositorioTurnos repositorioTurnos) {
		this.repositorioTurnos = repositorioTurnos;
	}

}
