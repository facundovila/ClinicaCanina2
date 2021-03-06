package clinicacanina.controladores;

import clinicacanina.modelo.DatosCrearMascota;
import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Turno;
import clinicacanina.servicios.ServicioMascota;
import clinicacanina.servicios.ServicioTurnos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class ControladorUsuarioHome {
    private ServicioTurnos servicioTurnos;
    private ServicioMascota servicioMascota;
    @Autowired
    public ControladorUsuarioHome(ServicioTurnos servicioTurnos,ServicioMascota servicioMascota){
        this.servicioTurnos=servicioTurnos;
        this.servicioMascota=servicioMascota;
    }

    @RequestMapping("/usuarioHome")
    public ModelAndView usuarioHome(HttpServletRequest request) {
        ModelMap mapa = new ModelMap();
        if (request.getSession().getAttribute("userId") == null) {
            return new ModelAndView("redirect:/login");
        }
        mapa.put("userID", request.getSession().getAttribute("userId"));
        mapa.addAttribute("datosCrearMascota",new DatosCrearMascota());
        List<Mascota> listaMascotas= servicioMascota.listarMascotasPorUsuario((Long) request.getSession().getAttribute("userId"));
        List<Turno> listaTurnos= servicioTurnos.turnosDelUsuario((Long) request.getSession().getAttribute("userId"));

        mapa.put("listaTurnosUsuario",listaTurnos);
        mapa.put("listaMascotas",listaMascotas);
        return new ModelAndView("usuarioHome",mapa);
    }
}
