package clinicacanina.servicios;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Usuario;

import java.util.List;

public interface ServicioLogin {
    Usuario consultarUsuario(String email, String password);
    Usuario consultarUsuarioPorID(Long id);

    List<Mascota> listarMascotas(long id);
}
