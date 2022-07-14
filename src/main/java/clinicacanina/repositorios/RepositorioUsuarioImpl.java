package clinicacanina.repositorios;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Turno;
import clinicacanina.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class RepositorioUsuarioImpl implements RepositorioUsuario{

    // Maneja acciones de persistencia, normalmente estara inyectado el session factory de hibernate
    // el mismo esta difinido en el archivo hibernateContext.xml
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioUsuarioImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Usuario buscarUsuario(String email, String password) {

        // Se obtiene la sesion asociada a la transaccion iniciada en el servicio que invoca a este metodo y se crea un criterio
        // de busqueda de Usuario donde el email y password sean iguales a los del objeto recibido como parametro
        // uniqueResult da error si se encuentran mas de un resultado en la busqueda.
        final Session session = sessionFactory.getCurrentSession();
        return (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
    }

    @Override
    public void guardar(Usuario usuario) {
        sessionFactory.getCurrentSession().save(usuario);
    }

    @Override
    public Usuario buscar(String email) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public void modificar(Usuario usuario) {
        sessionFactory.getCurrentSession().update(usuario);
    }

    @Override
    public Usuario consultarUsuarioPorID(long id) {
        //return (Usuario) sessionFactory.getCurrentSession().createQuery("from usuario u where u.id >= :id" ).setLong("id",id).uniqueResult();
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class).add(Restrictions.eq("id",id)).uniqueResult();
    }

    @Override
    public List<Mascota> listarMascotasPropias(long idUsuario) {
        return sessionFactory.getCurrentSession().createQuery("from Mascota m where m.usuario.id >= :id" ).setLong("id",idUsuario).list();

    }

}
