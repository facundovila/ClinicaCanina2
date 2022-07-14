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

       return repositorioMascota.guardarVisitaMedica(idMascota,visitaClinica);

    }

    @Override
    public List<VisitaClinica> obtenerVisitasClinicasDeLaMascota(Mascota mascota){

        return repositorioMascota.obtenerVisitaMedicaDeLaMascota(mascota);

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
