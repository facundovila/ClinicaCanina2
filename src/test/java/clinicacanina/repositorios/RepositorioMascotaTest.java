package clinicacanina.repositorios;

import clinicacanina.SpringTest;
import clinicacanina.controladores.HistoriaClinica;
import clinicacanina.modelo.Mascota;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class RepositorioMascotaTest extends SpringTest {

    @Autowired
    private RepositorioMascota repositorioMascota;


    @Test
    @Transactional
    @Rollback
    public void guardarUnaMascotaDeberiaPersistirla() {
        //preparacion
        Mascota mascota = dadoQueExisteMascota("goten", 15);
        //ejecucion
        Long idMascota = cuandoGuardoMascota(mascota);
        //validacion
        entoncesEncuentroLaMascota(idMascota);

    }

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarMascotaPorNombre() {
        //preparacion
        Mascota mascota1 = dadoQueExisteMascota("goten", 15);
        Mascota mascota2 = dadoQueExisteMascota("firu", 20);
        Mascota mascota3 = dadoQueExisteMascota("firu", 17);

        dadoQueGuardoMascota(mascota1);
        dadoQueGuardoMascota(mascota2);
        dadoQueGuardoMascota(mascota3);

        //ejecucion
        List<Mascota> mascotasBuscadas = CuandoBuscoMascotaPorNombre("firu");

        Integer cantidadEsperada = 2;

        //validacion
        entoncesEncuentroLaMascotaConNombre(mascotasBuscadas, cantidadEsperada);
    }
    @Test
    @Transactional
    @Rollback
    public void sePuedenBuscarTodasLasMascotas() {
        //preparacion
        Mascota mascota1 = dadoQueExisteMascota("goten", 15);
        Mascota mascota2 = dadoQueExisteMascota("firu", 20);
        Mascota mascota3 = dadoQueExisteMascota("firu", 17);

        dadoQueGuardoMascota(mascota1);
        dadoQueGuardoMascota(mascota2);
        dadoQueGuardoMascota(mascota3);

        //ejecucion
        List<Mascota> todasLasMascotas = CuandoBuscoTodasLasMascotas();

        Integer cantidadEsperada = 3;

        //validacion
        entoncesEncuentroTodasLasMascota(todasLasMascotas, cantidadEsperada);

    }

    @Test
    @Transactional
    @Rollback
    public void sePuedeModificarUnaMascota(){

        String nombre = "lupe";
        Integer peso = 10;
        Integer edad=10;
        String sintomas = "tratamiento";
        String detalle="detalle";

        HistoriaClinica historiaClinica = new HistoriaClinica();

        historiaClinica.setEdad(edad);
        historiaClinica.setPeso(peso);
        historiaClinica.setNombre(nombre);
        historiaClinica.setSintomas(sintomas);
        historiaClinica.setDetalleTratamientos(detalle);



        Mascota mascota = dadoQueExisteMascotaConHistoriaClinica(historiaClinica);

        dadoQueGuardoMascota(mascota);

        cuandoModificoMascota(mascota);

        entoncesLaHistoriaClinicaCambio(mascota);








    }

    private void entoncesLaHistoriaClinicaCambio(Mascota mascota) {

        assertThat(mascota.getSintomas()).isEqualTo("los sintomas fueron cambiados");
        assertThat(mascota.getNombre()).isEqualTo("goten");
        assertThat(mascota.getDetalleTratamientos()).isEqualTo("sada");

    }

    private void cuandoModificoMascota(Mascota mascota) {

        mascota.setNombre("goten");
        mascota.setSintomas("los sintomas fueron cambiados");
        String detalleTratamientoCambiado = "sada";
        repositorioMascota.modificarMascota(mascota.getId(),detalleTratamientoCambiado, mascota.getSintomas(),  mascota.getPeso(), mascota.getEdad(), mascota.getNombre());

    }


    private void entoncesEncuentroTodasLasMascota(List<Mascota> todasLasMascotas, Integer cantidadEsperada) {

        assertThat(todasLasMascotas).hasSize(cantidadEsperada);

    }

    private List<Mascota> CuandoBuscoTodasLasMascotas() {

        return repositorioMascota.buscarTodasLasMascotas();
    }


    private void entoncesEncuentroLaMascotaConNombre(List<Mascota> mascotasBuscadas, Integer cantidadEsperada) {

        assertThat(mascotasBuscadas).hasSize(cantidadEsperada);
    }

    private List<Mascota> CuandoBuscoMascotaPorNombre(String nombre) {

        return repositorioMascota.buscarPor(nombre);

    }

    private void dadoQueGuardoMascota(Mascota mascota) {
        repositorioMascota.guardar(mascota);
    }


    private void entoncesEncuentroLaMascota(Long idMascota) {

        Mascota mascota = repositorioMascota.buscarPorId(idMascota);
        assertThat(mascota).isNotNull();


    }

    private Long cuandoGuardoMascota(Mascota mascota) {
       // repositorioMascota.guardar(mascota);
        // no tendria que llamar al repo para preguntarle cual es el id?
        // return mascota.getId();
        return repositorioMascota.guardarYRegresarID(mascota);
    }

    private Mascota dadoQueExisteMascota(String nombre, int peso) {
        Mascota mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setPeso(peso);
        return mascota;

    }

    private Mascota dadoQueExisteMascotaConHistoriaClinica(HistoriaClinica historiaClinica){
        return new Mascota(historiaClinica);
    }

}
