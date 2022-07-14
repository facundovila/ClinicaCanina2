package clinicacanina.controladores;


public class HistoriaClinica {

    private String nombre;
    private Float peso;
    private Integer edad;




    public Float getPeso() { return peso; }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
