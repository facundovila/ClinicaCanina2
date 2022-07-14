package clinicacanina.repositorios;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.VisitaClinica;

import java.util.List;

public interface RepositorioMascota {

    Mascota buscarPorId(Long id);

    Long guardar(Mascota mascota);

    List<Mascota> buscarPor(String nombre);

    List<Mascota> buscarTodasLasMascotas();

    Long guardarYRegresarID(Mascota mascota);

    Mascota modificarMascota(Long id,  Float peso, Integer edad);

    List<VisitaClinica> obtenerVisitaMedicaDeLaMascota(Mascota mascota);

    Long guardarVisitaMedica(Long idMascota, VisitaClinica visita);

    Mascota getById(Long idMascota);



    void guardarImagen(Long id, String originalFilename);
}
