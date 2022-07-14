package clinicacanina.servicios;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Usuario;
import clinicacanina.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioLogin")
@Transactional
public class ServicioLoginImpl implements ServicioLogin {

    private RepositorioUsuario repositorioUsuario;

    @Autowired
    public ServicioLoginImpl(RepositorioUsuario repositorioUsuario){
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public Usuario consultarUsuario (String email, String password) {
        return repositorioUsuario.buscarUsuario(email, password);
    }

    @Override
    public Usuario consultarUsuarioPorID(Long id) {
        return repositorioUsuario.consultarUsuarioPorID(id);
    }
    @Override
    public List<Mascota> listarMascotas(long id) {
        return repositorioUsuario.listarMascotasPropias(id);
    }

}