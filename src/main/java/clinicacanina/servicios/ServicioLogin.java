package clinicacanina.servicios;

import clinicacanina.modelo.Usuario;

public interface ServicioLogin {
    Usuario consultarUsuario(String email, String password);
}
