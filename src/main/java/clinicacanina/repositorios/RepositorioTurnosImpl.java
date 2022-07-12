package clinicacanina.repositorios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Usuario;
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
		turnoNuevo.setEstado(true);
		turnoNuevo.setUsuario(null);
		turnoNuevo.setMascota(null);
		sessionFactory.getCurrentSession().update(turnoNuevo);
		return turnoNuevo.getEstado();

	}
	@Override
	public List<Turno> mostarTurnosDisponiblesFechaHoy() {
		//  sessionFactory.getCurrentSession().createQuery("from turno t where t.fechaTurno >= :fecha and t.estado= true" )
		//	.setDate("fecha",new java.util.Date()).list();
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-mm-dd");
		String fechaInicio = dateFormat.format(date);
		String fechaInicio2 = dateFormat2.format(date);
		/*Calendar fechaFin= Calendar.getInstance();
		fechaFin.set(Calendar.MINUTE,59);
		fechaFin.set(Calendar.SECOND,59);
		fechaFin.set(Calendar.HOUR,59);*/
		String fechaFin= fechaInicio2+" 23:59:59";
		return  sessionFactory.getCurrentSession().createQuery("from turno t where t.estado=true  and t.fechaTurno between :fechaInicio and :fechaFin")
				.setParameter("fechaInicio",fechaInicio).setParameter("fechaFin",fechaFin).list();

	}

	@Override
	public boolean tomarTurno(Mascota idMascota, Usuario idUsuario, long idTurno) {
		Turno tomarTurno= buscarTurnoPorId(idTurno);
		tomarTurno.setEstado(false);
		tomarTurno.setUsuario(idUsuario);
		tomarTurno.setMascota(idMascota);
		sessionFactory.getCurrentSession().update(tomarTurno);
		return tomarTurno.getEstado();
	}

	@Override
	public Turno buscarProximoTurnoLibre() {
	/*
		Calendar fechaActual= Calendar.getInstance();
		String hql = "from turno t where t.estado=true  and t.fechaTurno > :fechaA";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("fechaA",fechaActual);
		query.setFirstResult(0);
		query.setMaxResults(1);
		Turno results = (Turno) query.uniqueResult();
		return results;
	 */
		Calendar fechaActual= Calendar.getInstance();
		return (Turno) sessionFactory.getCurrentSession().createQuery("from turno t where t.estado=true  and t.fechaTurno > :fechaActual")
				.setParameter("fechaActual",fechaActual).setMaxResults(1).uniqueResult();

	}
	@Override
	public List<Turno> buscarTurnosPorFecha(Calendar calendario) {
		Calendar fechaMaxima=calendario;
		fechaMaxima.set(Calendar.HOUR,23);
		fechaMaxima.set(Calendar.MINUTE,59);
		fechaMaxima.set(Calendar.SECOND,59);
		return sessionFactory.getCurrentSession().createQuery("from turno t where t.estado=true and t.fechaTurno between :fechaActual and :fechaMaxima")
				.setParameter("fechaActual",calendario).setParameter("fechaMaxima",fechaMaxima).list();
	}


}
