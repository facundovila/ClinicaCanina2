package clinicacanina.repositorios;

import clinicacanina.modelo.Mascota;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Mascota modificarMascota(Long id, String detalleTratamientos, String sintomas, Integer peso, Integer edad, String nombre) {

        Mascota mascota = buscarPorId(id);
        mascota.setSintomas(sintomas);
        mascota.setDetalleTratamientos(detalleTratamientos);
        mascota.setEdad(edad);
        mascota.setPeso(peso);
        mascota.setNombre(nombre);

        sessionFactory.getCurrentSession().update(mascota);

        return mascota;
    }


}
