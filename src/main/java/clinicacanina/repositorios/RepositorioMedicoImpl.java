package clinicacanina.repositorios;

import java.util.List;

import clinicacanina.modelo.Mascota;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

		 sessionFactory.getCurrentSession().save(medico);
		
	}

	@Override
	public Medico buscarPorMailYPassword(Integer dni, String password) {

		final Session session = sessionFactory.getCurrentSession();
		return (Medico) session.createCriteria(Medico.class)
				.add(Restrictions.eq("dni", dni))
				.add(Restrictions.eq("contrasenia", password))
				.uniqueResult();


	}

}
