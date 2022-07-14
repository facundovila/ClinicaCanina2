package clinicacanina.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.time.LocalDate;


@Entity
public class VisitaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sintomas;
    private String tratamiento;


    private Integer edad;

    private Float peso;



    private String fechaActual;

    @ManyToOne
    @JoinColumn(name ="mascota_id")
    private Mascota mascotaAsignada;


    public Mascota getMascotaAsignada() {
        return mascotaAsignada;
    }

    public void setMascotaAsignada(Mascota mascotaAsignada) {
        this.mascotaAsignada = mascotaAsignada;
    }


    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
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
