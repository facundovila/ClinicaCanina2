package clinicacanina.repositorios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import clinicacanina.modelo.Navegador;

@Repository
public class RepositorioNavegacionImpl implements RepositorioNavegacion{
	
    private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioNavegacionImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void guardarNavegacion(Navegador navegador) {
		final Session session = sessionFactory.getCurrentSession();
		session.save(navegador);
		
		
	}

	@Override
	public Navegador buscarNavegacion(String patente) {
		final Session session = sessionFactory.getCurrentSession();
	 Navegador navegadorEncontrado = (Navegador)session.createCriteria(Navegador.class)
				                                   .add(Restrictions.eq("patente", patente))
				                                   .uniqueResult();
	 return navegadorEncontrado;
		
	}

	@Override
	public void actualizarNavegacion(Navegador navegador) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(navegador);
	}

}
