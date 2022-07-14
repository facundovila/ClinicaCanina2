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


    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    public Usuario getUsuario() {
        return usuario;
    }

    private Float peso;

    private Integer edad;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

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

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }



}


