package clinicacanina.servicios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clinicacanina.modelo.Turno;
import clinicacanina.repositorios.RepositorioMascota;
import clinicacanina.repositorios.RepositorioTurnos;

@Service @Transactional
public class ServicioTurnosImpl implements ServicioTurnos {
	
	private RepositorioTurnos repositorioTurnos;
	


    @Autowired
    public ServicioTurnosImpl(RepositorioTurnos repositorioTurnos){

        this.setRepositorioTurnos(repositorioTurnos);

    }


	@Override
	public List<Turno> buscarTurno(String fecha) {
		
		return repositorioTurnos.mostrarTurnoDisponible(fecha);
	}

	public RepositorioTurnos getRepositorioTurnos() {
		return repositorioTurnos;
	}


	public void setRepositorioTurnos(RepositorioTurnos repositorioTurnos) {
		this.repositorioTurnos = repositorioTurnos;
	}


	




}
