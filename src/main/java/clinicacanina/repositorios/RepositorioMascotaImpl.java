package clinicacanina.repositorios;

import clinicacanina.modelo.Mascota;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

        Mascota mascota = sessionFactory.getCurrentSession()
                .get(Mascota.class, id);


        return mascota;


    }

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




}
