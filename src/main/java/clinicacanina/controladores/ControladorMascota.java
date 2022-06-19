package clinicacanina.controladores;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Medico;
import clinicacanina.servicios.ListaVaciaExcepcion;
import clinicacanina.servicios.ServicioMascota;
import clinicacanina.servicios.ServicioMedico;
import clinicacanina.servicios.ServicioMedicoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ModelAndView listarMascotas(  HttpServletRequest session) {

        Long idUsuario = (Long) session.getSession().getAttribute("usuarioId");

        ModelMap model= new ModelMap();

        Medico medico = servicioMedico.getMedico(idUsuario);

        if(session.getSession().getAttribute("usuarioId") != null){


            List<Mascota> listaDeMascotas = servicioMascota.listarMascotas();

            if(listaDeMascotas.isEmpty()){
                model.put("sinMascotas", "no hay pacientes mascotas");
            }


            model.put("listarmascotas", listaDeMascotas);

            return new ModelAndView("listaMascotas", model);



        }else{
            model.put("error", "Usted debe estar registrado para acceder");

            return new ModelAndView("error", model);

        }



    }


    @RequestMapping(path = "/historia-clinica", method = RequestMethod.GET)
    public ModelAndView irAHistoriaClinica(@RequestParam("idMascota") Long idMascota, HttpSession session) {

        ModelMap model= new ModelMap();

        if(session.getAttribute("usuarioId") != null){

            Mascota mascota = servicioMascota.buscarMascotaPorId(idMascota);


            model.put("historiaclinica", mascota);

            return new ModelAndView("historiaClinica", model);

        }else{
            model.put("error", "Usted debe estar registrado para acceder");

            return new ModelAndView("error", model);

        }

    }




}
