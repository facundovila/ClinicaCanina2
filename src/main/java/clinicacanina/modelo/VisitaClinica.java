package clinicacanina.modelo;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class VisitaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sintomas;
    private String tratamiento;

    private Date fecha;

    @ManyToOne
    @JoinColumn(name ="mascota_id")
    private Mascota mascotaAsignada;


    public Mascota getMascotaAsignada() {
        return mascotaAsignada;
    }

    public void setMascotaAsignada(Mascota mascotaAsignada) {
        this.mascotaAsignada = mascotaAsignada;
    }




    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this. sintomas = sintomas;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
