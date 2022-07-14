package clinicacanina.controladores;

import clinicacanina.modelo.DatosCrearMascota;
import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.VisitaClinica;
import clinicacanina.servicios.ServicioMascota;
import clinicacanina.servicios.ServicioMedico;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ControladorMascota {


    private ServicioMascota servicioMascota;
    private ServicioMedico servicioMedico;

    @Autowired
    public ControladorMascota(ServicioMascota servicioMascota, ServicioMedico servicioMedico) {
        this.servicioMascota = servicioMascota;
        this.servicioMedico = servicioMedico;

    }
    @RequestMapping(path = "/listar-mascotas" , method = RequestMethod.GET)
    public ModelAndView listarMascotas(HttpSession session) {

        ModelMap model= new ModelMap();

        model.addAttribute("datosCrearMascota",new DatosCrearMascota());

        if(session.getAttribute("userId") != null){

            List<Mascota> listaDeMascotas = servicioMascota.listarMascotas();



            if(listaDeMascotas.isEmpty()){
                model.put("sinMascotas", "no hay pacientes mascotas");

                return new ModelAndView("listaMascotas", model);
            }
            else if(!listaDeMascotas.isEmpty()){
                model.put("listarmascotas", listaDeMascotas);
            }

                return new ModelAndView("listaMascotas", model);
                }
            else{
                    model.put("error", "Usted debe estar registrado para acceder");

                    return new ModelAndView("error", model);

        }




    }


    @RequestMapping(path = "/historia-clinica", method = RequestMethod.GET)
    public ModelAndView irAHistoriaClinica(@RequestParam("idMascota") Long idMascota, HttpSession session) {

        ModelMap model= new ModelMap();

        if(session.getAttribute("userId") != null){

            Mascota mascotaBuscada = servicioMascota.buscarMascotaPorId(idMascota);

            List<VisitaClinica> listaVisitas = servicioMascota.obtenerVisitasClinicasDeLaMascota(mascotaBuscada);

            model.put("mascota", mascotaBuscada);
            model.put("visita", listaVisitas);

            return new ModelAndView("historiaClinica", model);
        }else{
            model.put("error", "Usted debe estar registrado para accederr");

            return new ModelAndView("error", model);

        }

    }

    @RequestMapping(path = "/agregar-visita", method = RequestMethod.GET)
    public ModelAndView agregarVisitas(@RequestParam("idMascota") Long idMascota, HttpSession session) {

        ModelMap model= new ModelMap();

        Mascota mascotaAModificar = servicioMascota.buscarMascotaPorId(idMascota);

        VisitaClinica visitaClinica = new VisitaClinica();

        if(session.getAttribute("userId") != null && mascotaAModificar!=null ){
            model.put("mascota", mascotaAModificar );
            model.put("visita", visitaClinica);

            return new ModelAndView("modificarMascota", model);

        }else{
            model.put("error", "Usted debe estar registrado para accedeer");

            return new ModelAndView("error", model);
        }

    }



    @RequestMapping(path = "/modificar-historia-clinica",  method = RequestMethod.POST)
    public ModelAndView agregarVisitaMedica( @ModelAttribute VisitaClinica visitaClinica, HttpSession session){

        ModelMap model= new ModelMap();

        Mascota mascotaBuscada = servicioMascota.buscarMascotaPorId(visitaClinica.getMascotaAsignada().getId());

        servicioMascota.modificarMascota(mascotaBuscada.getId(), visitaClinica.getEdad() , visitaClinica.getPeso());

        if( session.getAttribute("userId") != null && mascotaBuscada!= null){

                servicioMascota.guardarVisitaMedicaDeMascota(mascotaBuscada.getId(), visitaClinica);

                return new ModelAndView("redirect:/historia-clinica?idMascota="+mascotaBuscada.getId(),model);

        }else{
            model.put("error", "no se encuentra mascota");

            return new ModelAndView("error", model);

        }

            }


    @RequestMapping(path = "/agregarMascota",  method = RequestMethod.POST)
    public ModelAndView agregarMascota(HttpServletRequest request, @ModelAttribute @NotNull DatosCrearMascota datosCrearMascota){
        ModelMap mapa= new ModelMap();
        Long idUsuario = (Long) request.getSession().getAttribute("userId");
       servicioMascota.crearNuevaMascota(datosCrearMascota.getNombre(),idUsuario);
    return new ModelAndView("redirect:/listar-mascotas",mapa);
      }


}
