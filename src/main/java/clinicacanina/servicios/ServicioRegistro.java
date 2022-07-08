package clinicacanina.servicios;

import clinicacanina.modelo.Usuario;

public interface ServicioRegistro {
    void registrar(String email, String contrasenia);

    Usuario buscarUsuarioPorEmail(String email);
}
