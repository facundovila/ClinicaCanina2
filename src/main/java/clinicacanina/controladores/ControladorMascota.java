package clinicacanina.controladores;

import clinicacanina.modelo.Mascota;
import clinicacanina.servicios.ServicioMascota;
import clinicacanina.servicios.ServicioMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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


    @RequestMapping(path = "/listar-mascotas", method = RequestMethod.GET)
    public ModelAndView listarMascotas(HttpSession session) {

        ModelMap model = new ModelMap();


        if (session.getAttribute("userId") != null) {


            List<Mascota> listaDeMascotas = servicioMascota.listarMascotas();


            if (listaDeMascotas.isEmpty()) {
                model.put("sinMascotas", "no hay pacientes mascotas");

                return new ModelAndView("listaMascotas", model);
            } else if (!listaDeMascotas.isEmpty()) {
                model.put("listarmascotas", listaDeMascotas);
            }

            return new ModelAndView("listaMascotas", model);
        } else {
            model.put("error", "Usted debe estar registrado para acceder");

            return new ModelAndView("error", model);

        }


    }


    @RequestMapping(path = "/historia-clinica", method = RequestMethod.GET)
    public ModelAndView irAHistoriaClinica(@RequestParam("idMascota") Long idMascota, HttpSession session) {

        ModelMap model = new ModelMap();

        if (session.getAttribute("userId") != null) {

            Mascota mascotaBuscada = servicioMascota.buscarMascotaPorId(idMascota);


            model.put("mascota", mascotaBuscada);

            return new ModelAndView("historiaClinica", model);
        } else {
            model.put("error", "Usted debe estar registrado para acceder");

            return new ModelAndView("error", model);

        }

    }


    @RequestMapping(path = "/modificar-mascota", method = RequestMethod.GET)
    public ModelAndView irAModificarMascota(@RequestParam("idMascota") Long idMascota, HttpSession session) {

        ModelMap model = new ModelMap();


        Mascota mascotaAModificar = servicioMascota.buscarMascotaPorId(idMascota);


        if (session.getAttribute("userId") != null && mascotaAModificar != null) {
            model.put("mascota", mascotaAModificar);

            return new ModelAndView("modificarMascota", model);

        } else {
            model.put("error", "Usted debe estar registrado para acceder");

            return new ModelAndView("error", model);
        }

    }


    @RequestMapping(path = "/modificar-historia-clinica", method = RequestMethod.POST)
    public ModelAndView modificarHistoriaClinica(@ModelAttribute("mascota") Mascota mascota, HttpSession session) {


        ModelMap model = new ModelMap();
        Mascota mascotaBuscada = servicioMascota.buscarMascotaPorId(mascota.getId());

        if (session.getAttribute("userId") != null && mascotaBuscada != null) {

            servicioMascota.modificarMascota(mascotaBuscada.getId(), mascota.getDetalleTratamientos(), mascota.getSintomas(), mascota.getEdad(), mascota.getPeso(), mascota.getNombre());


            return new ModelAndView("historiaClinica", model);


        } else {
            model.put("error", "Usted debe estar registrado para acceder");

            return new ModelAndView("error", model);

        }


    }


}
