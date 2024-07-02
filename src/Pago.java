public class Pago {
    private String metodo; // Puede ser "Tarjeta" o "Transferencia"
    private double monto;

    public Pago(String metodo, double monto) {
        this.metodo = metodo;
        this.monto = monto;
    }

    // Metodo de getters y setters
    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
