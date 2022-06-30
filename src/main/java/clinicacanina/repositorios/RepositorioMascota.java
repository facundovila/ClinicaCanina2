package clinicacanina.repositorios;

import clinicacanina.modelo.Mascota;

import java.util.List;

public interface RepositorioMascota {

    Mascota buscarPorId(Long id);

    Long guardar(Mascota mascota);

    List<Mascota> buscarPor(String nombre);

    List<Mascota> buscarTodasLasMascotas();

    Long guardarYRegresarID(Mascota mascota);

    Mascota modificarMascota(Long id, String detalleTratamientos, String sintomas, Integer peso, Integer edad, String nombre);
}
