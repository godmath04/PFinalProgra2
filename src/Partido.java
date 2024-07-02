import java.time.LocalDateTime;

public class Partido {
    private String id;
    private LocalDateTime fechaHora;
    private String equipoAnfitrion;
    private String equipoVisitante;
    private String categoria;
    private double precio;
    private int asientosDisponibles;

    public Partido(String id, LocalDateTime fechaHora, String equipoAnfitrion, String equipoVisitante, String categoria, double precio, int asientosDisponibles) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.equipoAnfitrion = equipoAnfitrion;
        this.equipoVisitante = equipoVisitante;
        this.categoria = categoria;
        this.precio = precio;
        this.asientosDisponibles = asientosDisponibles;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEquipoAnfitrion() {
        return equipoAnfitrion;
    }

    public void setEquipoAnfitrion(String equipoAnfitrion) {
        this.equipoAnfitrion = equipoAnfitrion;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }

    public void setAsientosDisponibles(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }
}