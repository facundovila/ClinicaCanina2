package clinicacanina.repositorios;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Usuario;

import java.util.List;

public interface RepositorioUsuario {
    Usuario buscarUsuario(String email, String password);

    void guardar(Usuario usuario);

    Usuario buscar(String email);

    void modificar(Usuario usuario);

    Usuario consultarUsuarioPorID(long l);

   List<Mascota> listarMascotasPropias(long l);
}
