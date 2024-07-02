
import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Lista de partidos
        List<Partido> partidos = new ArrayList<>();
        partidos.add(new Partido("1", LocalDateTime.of(2024, 7, 1, 18, 0), "Equipo A", "Equipo B", "Primera", 50.0, 100));
        partidos.add(new Partido("2", LocalDateTime.of(2024, 7, 2, 20, 0), "Equipo C", "Equipo D", "Segunda", 30.0, 200));

        boolean salir = false;

        while (!salir) {
            // Menú principal en un JOptionPane con botones
            int opcionSeleccionada = JOptionPane.showOptionDialog(null,
                    "Seleccione una opción",
                    "Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    new String[]{"Ver partidos futuros", "Comprar entradas", "Salir"},
                    "Ver partidos futuros");

            switch (opcionSeleccionada) {
                case 0: // Ver partidos futuros
                    StringBuilder partidosInfo = new StringBuilder();
                    for (Partido partido : partidos) {
                        partidosInfo.append("ID: ").append(partido.getId())
                                .append(", Fecha y Hora: ").append(partido.getFechaHora())
                                .append(", Anfitrión: ").append(partido.getEquipoAnfitrion())
                                .append(", Visitante: ").append(partido.getEquipoVisitante())
                                .append(", Categoría: ").append(partido.getCategoria())
                                .append(", Precio: $").append(partido.getPrecio())
                                .append(", Asientos Disponibles: ").append(partido.getAsientosDisponibles())
                                .append("\n");
                    }
                    JOptionPane.showMessageDialog(null, partidosInfo.toString(), "Partidos Futuros", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 1: // Comprar entradas
                    String idPartido = JOptionPane.showInputDialog("Ingrese el ID del partido:");
                    if (idPartido == null) {
                        break;
                    }

                    Partido partidoSeleccionado = null;
                    for (Partido partido : partidos) {
                        if (partido.getId().equals(idPartido)) {
                            partidoSeleccionado = partido;
                            break;
                        }
                    }

                    if (partidoSeleccionado != null) {
                        String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
                        if (nombre == null) break;

                        String apellido = JOptionPane.showInputDialog("Ingrese su apellido:");
                        if (apellido == null) break;

                        String cedula = JOptionPane.showInputDialog("Ingrese su cédula:");
                        if (cedula == null) break;

                        String equipoFavorito = JOptionPane.showInputDialog("Ingrese su equipo favorito:");
                        if (equipoFavorito == null) break;

                        String fechaNacimientoStr = JOptionPane.showInputDialog("Ingrese su fecha de nacimiento (AAAA-MM-DD):");
                        if (fechaNacimientoStr == null) break;

                        LocalDate fechaNacimiento;
                        try {
                            fechaNacimiento = LocalDate.parse(fechaNacimientoStr);
                        } catch (DateTimeParseException e) {
                            JOptionPane.showMessageDialog(null, "Fecha de nacimiento inválida.", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }

                        if (!esMayorDeEdad(fechaNacimiento)) {
                            JOptionPane.showMessageDialog(null, "Debe ser mayor de edad para comprar entradas.", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }

                        String email = JOptionPane.showInputDialog("Ingrese su email:");
                        if (email == null) break;

                        Usuario usuario = new Usuario(nombre, apellido, cedula, equipoFavorito, fechaNacimiento, email);

                        String[] metodosPago = {"Tarjeta", "Transferencia"};
                        String metodoPago = (String) JOptionPane.showInputDialog(null,
                                "Seleccione el método de pago",
                                "Método de Pago",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                metodosPago,
                                metodosPago[0]);
                        if (metodoPago == null) break;

                        Pago pago = new Pago(metodoPago, partidoSeleccionado.getPrecio());

                        JOptionPane.showMessageDialog(null, "Compra realizada con éxito!\n" +
                                "Usuario: " + usuario.getNombre() + " " + usuario.getApellido() + "\n" +
                                "Partido: " + partidoSeleccionado.getEquipoAnfitrion() + " vs " + partidoSeleccionado.getEquipoVisitante() + "\n" +
                                "Método de pago: " + pago.getMetodo(), "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Partido no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 2: // Salir
                    salir = true;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }

    private static boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        LocalDate hoy = LocalDate.now();
        return fechaNacimiento.plusYears(18).isBefore(hoy) || fechaNacimiento.plusYears(18).isEqual(hoy);
    }
}
