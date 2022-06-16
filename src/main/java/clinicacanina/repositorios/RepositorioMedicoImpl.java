package clinicacanina.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.cj.Session;

import clinicacanina.controladores.DatosMedicos;
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
	public void modificarDisponibilidadMedico(Medico medico) {
		
		sessionFactory.getCurrentSession().update(medico);
		
	}


	public Medico buscarMedicoPorDni(Integer dni) {
		
		 return (Medico) sessionFactory.getCurrentSession().createCriteria(Medico.class)
				 .add(Restrictions.eq("dni", dni)).uniqueResult();


	}

}
