package clinicacanina.repositorios;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.VisitaClinica;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public class RepositorioMascotaImpl implements RepositorioMascota {
    private SessionFactory sessionFactory;
    @Autowired
    public RepositorioMascotaImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    @Override
    public Mascota buscarPorId(Long id) {
        return (Mascota) sessionFactory.getCurrentSession()
                .createCriteria(Mascota.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();


    }

    @Override
    public Mascota getById(Long idMascota) {
        return (Mascota) sessionFactory.getCurrentSession()
                .createCriteria(Mascota.class)
                .add(Restrictions.eq("id", idMascota))
                .uniqueResult();
    }



    @Override
    public Long guardar(Mascota mascota){
        sessionFactory.getCurrentSession().save(mascota);
        return mascota.getId();

    }

    @Override
    public List<Mascota> buscarPor(String nombreBuscado){

        return sessionFactory.getCurrentSession()
                .createCriteria(Mascota.class)
                .add(Restrictions.eq("nombre", nombreBuscado))
                .list();

    }

    @Override
    public List<Mascota> buscarTodasLasMascotas(){

      return sessionFactory.getCurrentSession()
              .createCriteria(Mascota.class)
              .list();


    }

    @Override
    public Long guardarYRegresarID(Mascota mascota) {
        return null;
    }

    @Override
    public Mascota modificarMascota(Long id,  Float peso, Integer edad) {

        Mascota mascota = buscarPorId(id);
        mascota.setEdad(edad);
        mascota.setPeso(peso);
        sessionFactory.getCurrentSession().update(mascota);

        return mascota;
    }

    @Override
    public List<VisitaClinica> obtenerVisitaMedicaDeLaMascota(Mascota mascota) {

        final Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select distinct v from VisitaClinica v where mascotaAsignada =: mascota ")
                .setParameter("mascota", mascota)
                .list();


    }

    @Override
    public Long guardarVisitaMedica(Long idMascota, VisitaClinica visita){

        Mascota mascota = getById(idMascota);

        visita.setMascotaAsignada(mascota);

        sessionFactory.getCurrentSession().save(visita);

        return visita.getId();


    }




}
