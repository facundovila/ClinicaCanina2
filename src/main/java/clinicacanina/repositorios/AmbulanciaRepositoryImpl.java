package clinicacanina.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import clinicacanina.modelo.Ambulancia;

@Repository
public class AmbulanciaRepositoryImpl implements AmbulanciaRepository{
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public AmbulanciaRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Ambulancia traerAmbulanciaDisponible() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reservarAmbulancia(Ambulancia ambulancia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ambulancia buscarPorPatente(String patente) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (Ambulancia) session.createCriteria(Ambulancia.class)
				.add(Restrictions.eq("patente", patente))
				.uniqueResult();
	}

	@Override
	public void actualizarEstado() {
		// TODO Auto-generated method stub
		
	}

}
