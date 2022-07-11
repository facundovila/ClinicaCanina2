package clinicacanina.modelo;

import clinicacanina.controladores.HistoriaClinica;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "mascota")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



   //@Size(min = 3, message = "nombre invalido")
    private String nombre;

    private Integer peso;

    private Integer edad;





    public Mascota(HistoriaClinica historiaClinica){
        this.nombre = historiaClinica.getNombre();
        this.peso = historiaClinica.getPeso();
        this.edad = historiaClinica.getEdad();
    }


    public Mascota() {}

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }



}


