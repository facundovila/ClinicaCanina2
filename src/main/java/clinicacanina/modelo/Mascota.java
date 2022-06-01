package clinicacanina.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "mascota")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    @Size(min = 3, message = "nombre invalido")
    private String nombre;

    @Column(nullable = false)
    @NotNull
    private Integer peso;
<<<<<<< HEAD
    
=======

    @Column(nullable = false)
    @NotNull
    private Integer edad;



>>>>>>> main
    // para que no rompa test controlador
    public Mascota(String nombre, Integer peso, Integer edad){
        this.nombre = nombre;
        this.peso = peso;
        this.edad = edad;
    }

    // para que no rompa test controlador
    public Mascota() {

    }

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


