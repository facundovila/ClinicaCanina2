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
import clinicacanina.modelo.ReservaDeAmbulancia;

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
	public void reservarAmbulancia(ReservaDeAmbulancia reservaDeAmbulancia, Ambulancia ambulancia) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(reservaDeAmbulancia);
		session.update(ambulancia);
		
	}

	//nuevo metodo review 3
	@Override
	public List<ReservaDeAmbulancia> buscarReservas() {
		 final Session session = sessionFactory.getCurrentSession();
	      List<ReservaDeAmbulancia> reservas = new LinkedList<ReservaDeAmbulancia>();
	      reservas = (List<ReservaDeAmbulancia>) session.createCriteria(ReservaDeAmbulancia.class)
					                               .list();
		return reservas;
	}

}
