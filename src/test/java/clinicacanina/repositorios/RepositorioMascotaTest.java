package clinicacanina.repositorios;

import clinicacanina.SpringTest;
import clinicacanina.controladores.HistoriaClinica;
import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.VisitaClinica;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class RepositorioMascotaTest extends SpringTest {

    @Autowired
    private RepositorioMascota repositorioMascota;



    @Test
    @Transactional
    @Rollback
    public void puedoBuscarMascotaPorId() {

        //preparacion
        Mascota mascota1 = dadoQueExisteMascota("goten", 15F);
        Mascota mascota2 = dadoQueExisteMascota("firu", 20F);
        Mascota mascota3 = dadoQueExisteMascota("firu", 17.5F);

        dadoQueGuardoMascota(mascota1);
        dadoQueGuardoMascota(mascota2);
        dadoQueGuardoMascota(mascota3);




        //ejecucion
        Mascota  mascotaBuscada = CuandoBuscoMascotaPorid(mascota1.getId());


        Long id = 1l;

        //validacion
        entoncesEncuentroLaMascotaporId(mascotaBuscada.getId(), mascota1.getId());
    }

    @Test
    @Transactional
    @Rollback
    public void guardarUnaMascotaDeberiaPersistirla() {
        //preparacion
        Mascota mascota = dadoQueExisteMascota("goten", 15F);
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
        Mascota mascota1 = dadoQueExisteMascota("goten", 15F);
        Mascota mascota2 = dadoQueExisteMascota("firu", 20F);
        Mascota mascota3 = dadoQueExisteMascota("firu", 17F);

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
        Mascota mascota1 = dadoQueExisteMascota("goten", 15F);
        Mascota mascota2 = dadoQueExisteMascota("firu", 20F);
        Mascota mascota3 = dadoQueExisteMascota("firu", 17F);

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
        Float peso = 10F;
        Integer edad=10;

        HistoriaClinica historiaClinica = new HistoriaClinica();

        historiaClinica.setEdad(edad);
        historiaClinica.setPeso(peso);
        historiaClinica.setNombre(nombre);


        Mascota mascota = dadoQueExisteMascotaConHistoriaClinica(historiaClinica);

        dadoQueGuardoMascota(mascota);

        cuandoModificoMascota(mascota);

        entoncesCambioLa(mascota);

    }



    @Test
    @Transactional
    @Rollback
    public void sePuedeAgregarVisitasAUnaMascota(){

        Mascota mascota = dadoQueExisteMascota("goten", 15F);
        Long idMascota = repositorioMascota.guardar(mascota);

        VisitaClinica visitaClinica= new VisitaClinica();

        visitaClinica.setTratamiento("tratamientos");
        visitaClinica.setSintomas("sintomas");

        repositorioMascota.guardarVisitaMedica(idMascota, visitaClinica );

        assertThat(visitaClinica.getId()).isNotNull();


    }



    @Test
    @Transactional
    @Rollback
    public void sePuedeObtenerLasVisitasMedicasDeLaMascota(){
        Mascota mascota = dadoQueExisteMascota("goten", 15F);
        Long idMascota = repositorioMascota.guardar(mascota);

        VisitaClinica visitaClinica= new VisitaClinica();
        visitaClinica.setTratamiento("tratamientos");
        visitaClinica.setSintomas("sintomas");

        VisitaClinica visitaClinica2= new VisitaClinica();
        visitaClinica2.setSintomas("fiebre");
        visitaClinica2.setTratamiento("pastillas");

        repositorioMascota.guardarVisitaMedica(idMascota, visitaClinica );
        repositorioMascota.guardarVisitaMedica(idMascota, visitaClinica2 );

        List<VisitaClinica> visitas = repositorioMascota.obtenerVisitaMedicaDeLaMascota(mascota);


        assertThat(visitas).hasSize(2);

    }


    private void entoncesCambioLa(Mascota mascota) {

        assertThat(mascota.getEdad()).isEqualTo(15);

    }

    private void cuandoModificoMascota(Mascota mascota) {

        mascota.setEdad(15);

        repositorioMascota.modificarMascota(mascota.getId(), mascota.getPeso(), mascota.getEdad());

    }

    private void entoncesEncuentroLaMascotaporId(Long idMascotaBuscada, Long id) {

        assertThat(idMascotaBuscada).isEqualTo(id);

    }

    private Mascota CuandoBuscoMascotaPorid(Long id) {

        return repositorioMascota.buscarPorId(id );
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
        repositorioMascota.buscarPorId(idMascota);

    }

    private Long cuandoGuardoMascota(Mascota mascota) {

            return repositorioMascota.guardar(mascota);

    }

    private Mascota dadoQueExisteMascota(String nombre, Float peso) {
        Mascota mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setPeso(peso);
        return mascota;

    }

    private Mascota dadoQueExisteMascotaConHistoriaClinica(HistoriaClinica historiaClinica){
        return new Mascota(historiaClinica);
    }

}
