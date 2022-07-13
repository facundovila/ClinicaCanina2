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

import javax.persistence.Query;
import javax.persistence.TemporalType;

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
		/*Turno turnoNuevo= buscarTurnoPorId(id);
		turnoNuevo.setEstado(true);
		turnoNuevo.setUsuario(null);
		turnoNuevo.setMascota(null);
		sessionFactory.getCurrentSession().update(turnoNuevo);
		return turnoNuevo.getEstado();
*/
		String hql = "update turno t set t.estado=true,t.mascota=null, t.usuario=null  where t.id=:id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id",id);
		query.executeUpdate();
		return true;
	}
	@Override
	public List<Turno> mostarTurnosDisponiblesFechaHoy() {
		Calendar fechaActual= Calendar.getInstance();
		Calendar fechaFin= Calendar.getInstance();
		fechaFin.set(Calendar.HOUR_OF_DAY,23);
		fechaFin.set(Calendar.MINUTE,59);
		String hql = "from turno t where t.estado=true and t.fechaTurno between :fechaA and :fechaFin";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("fechaA",fechaActual);
		query.setParameter("fechaFin",fechaFin);
		List<Turno> results = query.getResultList();
		return results;

	}

	@Override
	public boolean tomarTurno(Mascota idMascota, Usuario idUsuario, long idTurno) {
		/*Turno tomarTurno= buscarTurnoPorId(idTurno);
		tomarTurno.setEstado(false);
		tomarTurno.setUsuario(idUsuario);
		tomarTurno.setMascota(idMascota);
		sessionFactory.getCurrentSession().update(tomarTurno);
		return tomarTurno.getEstado();*/
		String hql = "update turno t set t.estado=false,t.mascota=:idMascota, t.usuario=:idUsuario  where t.id=:id";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("id",idTurno);
		query.setParameter("idUsuario",idUsuario);
		query.setParameter("idMascota",idMascota);
		query.executeUpdate();
		return true;
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
		return (Turno) sessionFactory.getCurrentSession().createQuery("from turno t where t.estado=true  and t.fechaTurno >= :fechaActual")
				.setParameter("fechaActual",fechaActual).setMaxResults(1).uniqueResult();

	}
	@Override
	public List<Turno> buscarTurnosPorFecha(Calendar calendario) {
/*
		//select * from turno where t.estado=true and t.fechaTurno between fechaHoy and finDelDia
		Calendar fechaActual= new GregorianCalendar(calendario.get(Calendar.YEAR),(Calendar.MONTH),(Calendar.DAY_OF_MONTH),00,00,1);
		Calendar fechaMaxima= new GregorianCalendar(calendario.get(Calendar.YEAR),(Calendar.MONTH),(Calendar.DAY_OF_MONTH),23,59,59);

		String hql = "select t from turno t where t.estado =true and t.fechaTurno BETWEEN :fechaActual AND :fechaMaxima ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("fechaActual", fechaActual, TemporalType.TIMESTAMP);
		query.setParameter("fechaMaxima",fechaMaxima,TemporalType.TIMESTAMP);

		return query.getResultList();
*/

		/*
		Calendar fechaActual=calendario;
		fechaActual.set(Calendar.HOUR_OF_DAY,00);
		fechaActual.set(Calendar.MINUTE,00);
		fechaActual.set(Calendar.SECOND,01);
		Calendar fechaMaxima=calendario;
		fechaMaxima.set(Calendar.HOUR_OF_DAY,23);
		fechaMaxima.set(Calendar.MINUTE,59);
		fechaMaxima.set(Calendar.SECOND,59);*/

/*
		String hql = "from turno t where t.estado=true and t.fechaTurno >=:fechaA and t.fechaTurno <=:fechaFin";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("fechaA",fechaActual,TemporalType.TIMESTAMP);
		query.setParameter("fechaFin",fechaMaxima,TemporalType.TIMESTAMP);
		List<Turno> results = query.getResultList();
		return results;
		*/

		/* esto funciona
		String hql = "from turno t where t.estado=true and t.fechaTurno >=:fechaA";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("fechaA",fechaActual,TemporalType.TIMESTAMP);
		List<Turno> results = query.getResultList();
		return results;
		*/

		//return sessionFactory.getCurrentSession().createQuery("select t from turno t where t.estado=true and t.fechaTurno >=:fechaActual")
		//		.setParameter("fechaActual",fechaActual,TemporalType.TIMESTAMP).list();
		return sessionFactory.getCurrentSession().createQuery("select t from turno t where t.estado=true and t.fechaTurno =:fechaActual")
				.setParameter("fechaActual",calendario,TemporalType.TIMESTAMP).list();

		//return sessionFactory.getCurrentSession().createQuery("from turno t where t.estado=true  and t.fechaTurno >=:fechaActual and t.fechaTurno <=:fechaFin")
		//		.setParameter("fechaActual",fechaActual,TemporalType.TIMESTAMP).setParameter("fechaFin",fechaMaxima,TemporalType.TIMESTAMP).list();




	}


}
