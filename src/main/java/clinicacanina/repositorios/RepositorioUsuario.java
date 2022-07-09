package clinicacanina.repositorios;

import clinicacanina.modelo.Usuario;

public interface RepositorioUsuario {
    Usuario buscarUsuario(String email, String password);

    void guardar(Usuario usuario);

    Usuario buscar(String email);

    void modificar(Usuario usuario);
}
