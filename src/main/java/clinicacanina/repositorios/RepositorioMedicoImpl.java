package clinicacanina.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import clinicacanina.modelo.Medico;

@Repository
public class RepositorioMedicoImpl implements RepositorioMedico{

	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioMedicoImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}
	

	@Override
	public List<Medico> traerTodosLosMedicos() {
		return sessionFactory.getCurrentSession().createCriteria(Medico.class).list();
	}


	@Override
	public void guardarMedico(Medico medico) {
		
	}

}
