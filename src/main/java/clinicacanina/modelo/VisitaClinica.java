package clinicacanina.modelo;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;


@Entity
public class VisitaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sintomas;
    private String tratamiento;



    private Integer edad;
    private Float peso;


    private final LocalDateTime fechaActual = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name ="mascota_id")
    private Mascota mascotaAsignada;


    public Mascota getMascotaAsignada() {
        return mascotaAsignada;
    }

    public void setMascotaAsignada(Mascota mascotaAsignada) {
        this.mascotaAsignada = mascotaAsignada;
    }

    public LocalDateTime getFechaActual() {
        return fechaActual;
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


    public Integer getEdad() {return edad;}

    public void setEdad(Integer edad) {this.edad = edad;}

    public Float getPeso() {return peso;}

    public void setPeso(Float peso) {this.peso = peso;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
