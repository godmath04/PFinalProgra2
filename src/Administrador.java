import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Administrador {
    public void agregarPartido(List<Partido> partidos) {
        //Ingresar el id del partido
        String id = JOptionPane.showInputDialog("Ingrese el ID del nuevo partido:");
        if (id == null) return;
        //Ingresar la fecha y hora del partido
        String fechaHoraStr = JOptionPane.showInputDialog("Ingrese la fecha y hora (AAAA-MM-DDTHH:MM) del partido:");
        if (fechaHoraStr == null) return;

        LocalDateTime fechaHora;
        try {
            fechaHora = LocalDateTime.parse(fechaHoraStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Fecha y hora inválidas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Ingresar el equipo anfitrion
        String equipoAnfitrion = JOptionPane.showInputDialog("Ingrese el equipo anfitrión:");
        if (equipoAnfitrion == null) return;
        //Ingresar el equipo visitante
        String equipoVisitante = JOptionPane.showInputDialog("Ingrese el equipo visitante:");
        if (equipoVisitante == null) return;
        //Ingresar la categoria
        String categoria = JOptionPane.showInputDialog("Ingrese la categoría:");
        if (categoria == null) return;
        //Ingresar el precio
        String precioStr = JOptionPane.showInputDialog("Ingrese el precio:");
        if (precioStr == null) return;

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Precio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Ingresar el asientos disponibles
        String asientosDisponiblesStr = JOptionPane.showInputDialog("Ingrese la cantidad de asientos disponibles:");
        if (asientosDisponiblesStr == null) return;

        int asientosDisponibles;
        try {
            asientosDisponibles = Integer.parseInt(asientosDisponiblesStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad de asientos inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Ingresar el nombre del estadio
        String nombreEstadio = JOptionPane.showInputDialog("Ingrese el nombre del estadio:");
        if (nombreEstadio == null) return;

        //Creacion del nuevo partido
        Estadio estadio = new Estadio(nombreEstadio);
        Partido nuevoPartido = new Partido(id, fechaHora, equipoAnfitrion, equipoVisitante, categoria, precio, asientosDisponibles, estadio);
        partidos.add(nuevoPartido);
        JOptionPane.showMessageDialog(null, "Partido agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
