package clinicacanina.controladores;


public class HistoriaClinica {

    private String nombre;
    private Float peso;
    private Integer edad;
    private String sintomas;
    private String detalleTratamientos;



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

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public String getDetalleTratamientos() {
        return detalleTratamientos;
    }

    public void setDetalleTratamientos(String detalleTratamientos) {
        this.detalleTratamientos = detalleTratamientos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
