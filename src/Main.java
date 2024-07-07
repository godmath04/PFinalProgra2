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
        // Partidos que van a existir

        //Ejemplo del Primer partido
        Estadio estadio1 = new Estadio("Rodrigo Paz");
        partidos.add(new Partido("1", LocalDateTime.of(2024, 8, 15, 19, 0), "Liga", "Barcelona", "Primera", 20.0, 100,estadio1));
        Estadio estadio2 = new Estadio("Monumental");
        partidos.add(new Partido("2", LocalDateTime.of(2024, 10, 2, 20, 0), "Imbabura", "Orense", "Segunda", 10.0, 2, estadio2));

        //Ingresando el admin
        Administrador administrador = new Administrador();
        //Ingresando la contraseña
        String claveAdministrador = "admin123";
        boolean salir = false;

        while (!salir) {
            // Menú principal para ponerle con botones
            /* Este JOPtion pane se ingresa el mensaje principal con las opciones
            , el titulo se muestra como la barro, el default opcion da la primera opcion
            de forma predeterminada, el plain message es que es un mensaje simple y el partidos futuros es
            prefeterminadao, */
            int opcion = JOptionPane.showOptionDialog(null,
                    "Seleccione una opción",
                    "Menú Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    // Botones que van a existir
                    new String[]{"Partidos futuros", "Comprar entradas", "Administrador", "Salir"},
                    "Partidos futuros");

            //Lo importante es que el Joption anterior devuelve un valor entero de acuerdo
            //A lo que se seleccione y se anida al string,
            switch (opcion) {
                case 0: // Ver para ver los partidos futuros
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < partidos.size(); i++) {
                        Partido partido = partidos.get(i);
                        sb.append("ID: ").append(partido.getId())
                                .append(", Fecha y Hora: ").append(partido.getFechaHora())
                                .append(", Anfitrión: ").append(partido.getEquipoAnfitrion())
                                .append(", Visitante: ").append(partido.getEquipoVisitante())
                                .append(", Categoría: ").append(partido.getCategoria())
                                .append(", Precio: $").append(partido.getPrecio())
                                .append(", Estadio: ").append(partido.getEstadio().getNombre())
                                .append(", Asientos Disponibles: ").append(partido.getAsientosDisponibles())
                                .append("\n");
                    }

                    JOptionPane.showMessageDialog(null, sb.toString(), "Partidos Futuros", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 1: // Comprar entradas
                    String idPartido = JOptionPane.showInputDialog("Ingrese el ID del partido:");
                    if (idPartido == null) {
                        break;
                    }

                    Partido partidoSeleccionado = null;
                    for (int i = 0; i < partidos.size(); i++) {
                        Partido partido = partidos.get(i);
                        if (partido.getId().equals(idPartido)) {
                            partidoSeleccionado = partido;
                            break;
                        }
                    }


                    if (partidoSeleccionado != null) {
                        //AHora vamos a verificar la disponibilidad de los asientos
                        String cantidadStr = JOptionPane.showInputDialog("¿Cuántas entradas desea comprar? (Máximo 3):");
                        if (cantidadStr == null) break;
                        int cantidad;
                        try {
                            cantidad = Integer.parseInt(cantidadStr);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Cantidad inválida.", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }

                        // Verificar límite de entradas por usuario
                        if (cantidad < 1 || cantidad > 3) {
                            JOptionPane.showMessageDialog(null, "Solo puede comprar entre 1 y 3 entradas.", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }

                        // Verificar disponibilidad de asientos
                        if (!partidoSeleccionado.hayAsientosDisponibles(cantidad)) {
                            JOptionPane.showMessageDialog(null, "No hay suficientes asientos disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
                            break;
                        }

                        //Ahora si vamos a pedir datos



                        String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
                        if (nombre == null) break;

                        String apellido = JOptionPane.showInputDialog("Ingrese su apellido:");
                        if (apellido == null) break;

                        String cedula = JOptionPane.showInputDialog("Ingrese su cédula:");
                        if (cedula == null) break;

                        String equipoFavorito = JOptionPane.showInputDialog("Ingrese su equipo favorito:\nRecibiras notificaciiones personalizadas a tu correo.");
                        if (equipoFavorito == null) break;

                        // Importante y sumo cuidado con la fecha de nacimiento
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

                        // Reducción de asientos disponibles
                        try {
                            partidoSeleccionado.reducirAsientosDisponibles(cantidad); // Reducir asientos en la cantidad solicitada
                            JOptionPane.showMessageDialog(null, "Compra realizada con éxito!\n" +
                                    "Usuario: " + usuario.getNombre() + " " + usuario.getApellido() + "\n" +
                                    "Partido: " + partidoSeleccionado.getEquipoAnfitrion() + " vs " + partidoSeleccionado.getEquipoVisitante() + "\n" +
                                    "Entradas: " + cantidad + "\n" +
                                    "Método de pago: " + pago.getMetodo(), "Confirmacoin", JOptionPane.INFORMATION_MESSAGE);
                        } catch (IllegalArgumentException e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Partido no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case 2: // Administrador
                    // NOTA: LA CONTRASEÑA ES admin123
                    JPasswordField passwordField = new JPasswordField();
                    int option = JOptionPane.showConfirmDialog(null, passwordField, "Ingrese la clave de administrador", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (option == JOptionPane.OK_OPTION) {
                        String claveIngresada = new String(passwordField.getPassword());
                        if (claveIngresada.equals(claveAdministrador)) {
                            administrador.agregarPartido(partidos);
                        } else {
                            JOptionPane.showMessageDialog(null, "Clave incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;

                case 3: // Salir
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
        //Calcullamos la edad donde la persona cumple y si es mayor o no
        return fechaNacimiento.plusYears(18).isBefore(hoy) || fechaNacimiento.plusYears(18).isEqual(hoy);
    }
}
