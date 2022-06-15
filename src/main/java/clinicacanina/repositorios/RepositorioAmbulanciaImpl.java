package clinicacanina.repositorios;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.Estado;

@Repository
public class RepositorioAmbulanciaImpl implements RepositorioAmbulancia{
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioAmbulanciaImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List <Ambulancia> buscarAmbulancias() {
      final Session session = sessionFactory.getCurrentSession();
      List<Ambulancia> ambulancias = new LinkedList<Ambulancia>();
		
		ambulancias = (List<Ambulancia>) session.createCriteria(Ambulancia.class)
				                               .list();
		
		return ambulancias;
	
	}

	@Override
	public Ambulancia buscarAmbulanciaPorPatente(String patente) {
		final Session session = sessionFactory.getCurrentSession();
		Ambulancia ambulancia = (Ambulancia)session.createCriteria(Ambulancia.class)
				                                   .add(Restrictions.eq("patente", patente))
				                                   .uniqueResult();
		return ambulancia;
	}

	@Override
	public void reservarAmbulancia(Ambulancia ambulancia) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(ambulancia);
		
	}

	
	/*
	@Override
	public void reservarAmbulancia(String patente) {
		final Session session = sessionFactory.getCurrentSession();
		Ambulancia ambulanciaAReservar = buscarPorPatente(patente);
		
		if(ambulanciaAReservar.getDisponible() == true && ambulanciaAReservar.getEstado().equals(Estado.EN_COCHERA)) {
			ambulanciaAReservar.setDisponible(false);
			ambulanciaAReservar.setEstado(Estado.EN_PREPARACION);
			session.update(ambulanciaAReservar);
		}
		
	}

	@Override
	public Ambulancia buscarPorPatente(String patente) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (Ambulancia) session.createCriteria(Ambulancia.class)
				.add(Restrictions.eq("patente", patente))
				.uniqueResult();
	}

	@Override
	public void actualizarEstado(String patente) {
		Ambulancia encontrada = buscarPorPatente(patente);
		
		//Esta validacion estaria de mas, ya que al reservar la ambulancia automaticamente el estado migraria a "EN_PREPARACION".
		if(encontrada.getDisponible() == false && encontrada.getEstado().equals(Estado.EN_COCHERA)) {
			encontrada.setEstado(Estado.EN_PREPARACION);
		}
		
	}
*/
}
