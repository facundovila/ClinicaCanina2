package clinicacanina.servicios;


import clinicacanina.controladores.HistoriaClinica;
import clinicacanina.modelo.DatosCrearMascota;
import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Usuario;
import clinicacanina.modelo.VisitaClinica;
import clinicacanina.repositorios.RepositorioMascota;
import clinicacanina.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ServicioMascotaImpl implements ServicioMascota {

    private RepositorioMascota repositorioMascota;
    private ServicioLogin ServicioLogin;




    @Autowired
    public ServicioMascotaImpl(RepositorioMascota repositorioMascota,ServicioLogin ServicioLogin){
     this.ServicioLogin=ServicioLogin;
     this.repositorioMascota = repositorioMascota;

    }


    @Override
    public void mostrarDatosMascota(){


    }


    @Override
    public Mascota crearMascota(HistoriaClinica historiaClinica){


        Mascota mascotaGuardada = new Mascota(historiaClinica);

       repositorioMascota.guardar(mascotaGuardada);

        return mascotaGuardada;

    }




    @Override
    public Mascota buscarMascotaPorId(Long id){

        Mascota mascotaBuscada = repositorioMascota.buscarPorId(id);

        return mascotaBuscada;

    }


    @Override
    public List<Mascota> listarMascotas(){

        return repositorioMascota.buscarTodasLasMascotas();

    }
    @Override
    public List<Mascota> listarMascotasPorUsuario(Long idUsuario){
        return repositorioMascota.listarMascotasPorUsuario(idUsuario);
    }


    @Override
    public Mascota modificarMascota(Long id, Integer edad, Float peso) {

        Mascota mascotaAModificar = buscarMascotaPorId(id);

        if(mascotaAModificar.getId() != null){

           Mascota mascotaModificada = repositorioMascota.modificarMascota( id, peso, edad);

            return mascotaModificada;

        }else{

           throw new MascotaInexistenteException();

        }
    }

    @Override
    public Long guardarVisitaMedicaDeMascota(Long idMascota, VisitaClinica visitaClinica){

        LocalDateTime horarioActual = LocalDateTime.now();

        String HorarioActualParseado = parseHoraActual(horarioActual);

        visitaClinica.setFechaActual(HorarioActualParseado);

        Mascota mascotaBuscada = buscarMascotaPorId(idMascota);

        if(mascotaBuscada.getId() != null){
            return repositorioMascota.guardarVisitaMedica(mascotaBuscada.getId() ,visitaClinica);
        }else{
            throw new MascotaInexistenteException();
        }


    }

    @Override
    public List<VisitaClinica> obtenerVisitasClinicasDeLaMascota(Mascota mascota){

        if(mascota !=null){
            return repositorioMascota.obtenerVisitaMedicaDeLaMascota(mascota);

        }else{
            throw new MascotaInexistenteException();
        }

    }

    @Override
    public void guardarImagen(Long id, String originalFilename) {
        Mascota mascotaAModificar = buscarMascotaPorId(id);

        repositorioMascota.guardarImagen(id, originalFilename);

    }


    private String parseHoraActual(LocalDateTime horarioActual){

        String horaActual = horarioActual.getYear() + "-" + horarioActual.getMonthValue() + "-"
                + horarioActual.getDayOfMonth();


        return horaActual;

    }

    @Override
    public void crearNuevaMascota(String datosCrearMascota, Long idUsuario) {
        Mascota mascota= new Mascota();
        Usuario usuario= ServicioLogin.consultarUsuarioPorID(idUsuario);
        mascota.setNombre(datosCrearMascota);
        mascota.setUsuario(usuario);
        repositorioMascota.guardar(mascota);
    }


}



