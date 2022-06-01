package clinicacanina.repositorios;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import clinicacanina.modelo.Turno;

public class RepositorioTurnosImpl implements RepositorioTurnos {

	private SessionFactory sessionFactory;
	
	@Autowired 
	public RepositorioTurnosImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void guardarTurno(Turno turnoCreado) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Turno> mostrarTurnoDisponible(String fecha) {
		
		return sessionFactory.getCurrentSession().createCriteria(Turno.class)
				.add(Restrictions.eq("fecha", fecha))
				.list();
	}

}
