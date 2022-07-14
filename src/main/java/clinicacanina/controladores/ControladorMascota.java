package clinicacanina.controladores;

import clinicacanina.modelo.DatosCrearMascota;
import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.VisitaClinica;
import clinicacanina.servicios.ServicioMascota;
import clinicacanina.servicios.ServicioMedico;

import clinicacanina.servicios.ServicioValidacionDatos;
import clinicacanina.servicios.ServicioValidacionDatosImpl;

import org.jetbrains.annotations.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ControladorMascota {


    private ServicioMascota servicioMascota;
    private ServicioMedico servicioMedico;
    private ServicioValidacionDatosImpl servicioValidacionDatos = new ServicioValidacionDatosImpl();
    private Mascota mascota;
    private MultipartFile imagen;
    private RedirectAttributes attributes;

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

            VisitaClinica visitaClinica = new VisitaClinica();

            List<VisitaClinica> listaVisitas = servicioMascota.obtenerVisitasClinicasDeLaMascota(mascotaBuscada);

            model.put("mascota", mascotaBuscada);
            model.put("visitas", listaVisitas);
            model.put("visita", visitaClinica);

            return new ModelAndView("historiaClinica", model);
        } else {
            model.put("error", "Usted debe estar registrado para accederr");

            return new ModelAndView("error", model);

        }

    }


    @RequestMapping(path = "/agregar-visita", method = RequestMethod.GET)
    public ModelAndView agregarVisitas(@RequestParam("idMascota") Long idMascota, HttpSession session) {

        ModelMap model = new ModelMap();

        Mascota mascotaAModificar = servicioMascota.buscarMascotaPorId(idMascota);

        VisitaClinica visitaClinica = new VisitaClinica();

        if (session.getAttribute("userId") != null && mascotaAModificar != null) {
            model.put("mascota", mascotaAModificar);
            model.put("visita", visitaClinica);


            return new ModelAndView("modificarMascota", model);

        } else {
            model.put("error", "Usted debe estar registrado para accedeer");

            return new ModelAndView("error", model);
        }

    }


    @RequestMapping(path = "/modificar-historia-clinica", method = RequestMethod.POST)
    public ModelAndView agregarVisitaMedica(@ModelAttribute VisitaClinica visitaClinica, HttpSession session) {



        ModelMap model= new ModelMap();

        Mascota mascotaBuscada = servicioMascota.buscarMascotaPorId(visitaClinica.getMascotaAsignada().getId());


        if (session.getAttribute("userId") != null && mascotaBuscada != null) {
            Boolean esValidaLaEdad = servicioValidacionDatos.validadEdad(visitaClinica.getEdad());
            if (esValidaLaEdad == false) {
                model.put("error", "Edad invalida.");
                return new ModelAndView("error", model);
            }
            try {
                servicioMascota.modificarMascota(mascotaBuscada.getId(), visitaClinica.getEdad(),
                        visitaClinica.getPeso());

                servicioMascota.guardarVisitaMedicaDeMascota(mascotaBuscada.getId(),
                        visitaClinica);
            } catch (Exception e) {
                model.put("Error", "Ocurrio un error inesperado al intentar agregar la visita medica.");
            }
            return new ModelAndView("redirect:/historia-clinica?idMascota=" + mascotaBuscada.getId(), model);
        } else {


            model.put("error", "no se encuentra mascota");

            return new ModelAndView("error", model);

        }
    }



    @RequestMapping(path = "/guardar-imagen", method = RequestMethod.POST)
    public ModelAndView guardarImagen(@ModelAttribute Mascota mascota, @RequestParam("file") MultipartFile imagen) {


        ModelMap model = new ModelMap();
        Mascota mascotaBuscada = servicioMascota.buscarMascotaPorId(mascota.getId());

        if (!imagen.isEmpty()) {
            Path directorioImagenes = Paths.get("src//main//resources//static//images");
            String rutaAbsoluta = directorioImagenes.toFile().getAbsolutePath();

            try {
                byte[] bytesImg = imagen.getBytes();
               Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + imagen.getOriginalFilename());

                Files.write(rutaCompleta, bytesImg);

            } catch (IOException e) {
                    e.printStackTrace();
            }

        }

        servicioMascota.guardarImagen(mascota.getId(), imagen.getOriginalFilename());



        return new ModelAndView("redirect:/historia-clinica?idMascota=" + mascotaBuscada.getId(), model);

    }


    @RequestMapping(path = "/agregarMascota",  method = RequestMethod.POST)
    public ModelAndView agregarMascota(HttpServletRequest request, @ModelAttribute @NotNull DatosCrearMascota datosCrearMascota){
        ModelMap mapa= new ModelMap();
        Long idUsuario = (Long) request.getSession().getAttribute("userId");
       servicioMascota.crearNuevaMascota(datosCrearMascota.getNombre(),idUsuario);
    return new ModelAndView("redirect:/listar-mascotas",mapa);
      }




}
