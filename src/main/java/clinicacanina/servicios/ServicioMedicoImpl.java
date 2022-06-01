package clinicacanina.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinicacanina.modelo.Medico;
import clinicacanina.repositorios.RepositorioMedico;

@Service
public class ServicioMedicoImpl implements ServicioMedico{
	
	private RepositorioMedico repositorioMedico;
	
	@Autowired
	public ServicioMedicoImpl(RepositorioMedico repositorioMedico) {
		this.repositorioMedico = repositorioMedico;
	}

	@Override
	public List<Medico> traerMedicos() {
		List<Medico> listaMedico = repositorioMedico.traerTodosLosMedicos();
		return listaMedico;
	}

	@Override
	public Medico crearMedico(String nombre, Integer horarioEntrada, Integer horarioSalida) {
		return new Medico(nombre,horarioEntrada, horarioSalida);
	}



}
