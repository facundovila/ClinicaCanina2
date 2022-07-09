package clinicacanina.servicios;

import clinicacanina.modelo.Usuario;
import clinicacanina.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioRegistro")
@Transactional
public class ServicioRegistroImpl implements ServicioRegistro {

    private RepositorioUsuario servicioRegistroDAO;

    @Autowired
    public ServicioRegistroImpl(RepositorioUsuario servicioRegistroDAO) {
        super();
        this.servicioRegistroDAO = servicioRegistroDAO;
    }

    @Override
    public void registrar(String email, String contrasenia) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPassword(contrasenia);

        servicioRegistroDAO.guardar(nuevoUsuario);
    }

    @Override
    public Usuario buscarUsuarioPorEmail(String email) {
        // TODO Auto-generated method stub
        return servicioRegistroDAO.buscar(email);
    }

}
