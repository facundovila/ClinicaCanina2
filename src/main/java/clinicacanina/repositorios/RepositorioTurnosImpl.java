package clinicacanina.repositorios;

import java.util.Calendar;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import clinicacanina.modelo.Turno;

@Repository
public class RepositorioTurnosImpl implements RepositorioTurnos {

	private SessionFactory sessionFactory;
	
	@Autowired 
	public RepositorioTurnosImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	@Override
	public List<Turno> mostrarTurnoDisponible(String fecha) {
		
		return sessionFactory.getCurrentSession().createCriteria(Turno.class)
				.add(Restrictions.eq("fecha", fecha))
				.list();
	}


	@Override
	public void guardarTurno(Turno turnoCreado) {
		sessionFactory.getCurrentSession().save(turnoCreado);
	}


	@Override
	public List<Turno> buscarPorFecha(String fecha) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createCriteria(Turno.class)
				.add(Restrictions.eqOrIsNull("fecha", fecha))
				.list();
	}

	@Override
	public List<Turno> mostrarTurnoUsuarioDesdeHoy(long usuarioId) {
		Calendar fechaActual= Calendar.getInstance();
		return  sessionFactory.getCurrentSession().createQuery("from turno t where t.fechaTurno >= :fecha and t.usuario.id= :usu" )
				.setDate("fecha",new java.util.Date()).setLong("usu",usuarioId).list();
	}
	@Override
	public List<Turno> mostarTodosTurnosDelUsuario(long usuarioId) {

		return sessionFactory.getCurrentSession().createCriteria(Turno.class).createAlias("usuario","u")
				.add(Restrictions.eq("u.id",usuarioId)).list();
	}



	@Override
	public Turno buscarTurnoPorId(Long id) {
		return (Turno) sessionFactory.getCurrentSession().createCriteria(Turno.class)
				.add(Restrictions.eq("id",id)).uniqueResult();
	}


	@Override
	public Boolean cancelarTurnoPorId(Long id) {
		Turno turnoNuevo= buscarTurnoPorId(id);
		turnoNuevo.setEstado(false);
		turnoNuevo.setUsuario(null);
		turnoNuevo.setMascota(null);

		sessionFactory.getCurrentSession().update(turnoNuevo);

		Turno turnoADevolver = (Turno) sessionFactory.getCurrentSession()
									  .createCriteria(Turno.class)
				                      .add(Restrictions.eq("id",id)).uniqueResult();
		return turnoADevolver.getEstado();

	}



	@Override
	public List<Turno> mostarTurnosDisponiblesFechaHoy() {
		Calendar fechaActual= Calendar.getInstance();
		return  sessionFactory.getCurrentSession().createQuery("from turno t where t.fechaTurno = :fecha" )
				.setDate("fecha",new java.util.Date()).list();

	}

}
