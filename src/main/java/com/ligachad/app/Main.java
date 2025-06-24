package com.ligachad.app;

import com.ligachad.domain.*;
import com.ligachad.service.*;
import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static List<Equipo> equipos = new ArrayList<>();
    static List<Partido> partidos = new ArrayList<>();

    static JugadorService jugadorService = new JugadorService();
    static EquipoService equipoService = new EquipoService();
    static PartidoService partidoService = new PartidoService();

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer


            switch (opcion) {
                case 1 -> registrarJugador();
                case 2 -> crearEquipo();
                case 3 -> registrarPartido();
                case 4 -> mostrarGoleador();
                case 5 -> listarTodosLosJugadores();
                case 6 -> exportarCsvEquipo();


                case 0 -> System.out.println("¡Saliendo del sistema!");
                System.out.println("6. Exportar jugadores de un equipo a CSV");

                default -> System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }

    public static void mostrarMenu() {
        System.out.println("\n--- LIGA DE FÚTBOL CHAD ---");
        System.out.println("1. Registrar jugador");
        System.out.println("2. Crear equipo");
        System.out.println("3. Registrar partido y asignar goles");
        System.out.println("4. Mostrar goleador de la liga");
        System.out.println("5. Listar todos los jugadores");
        System.out.println("0. Salir");
        System.out.print("Elegí una opción: ");
    }

    public static void registrarJugador() {
        System.out.print("Nombre del jugador: ");
        String nombre = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.print("¿Es titular o suplente? (t/s): ");
        String tipo = scanner.nextLine();

        Jugador jugador = null;
        if (tipo.equalsIgnoreCase("t")) {
            System.out.print("Minutos jugados: ");
            int minutos = scanner.nextInt();
            jugador = new Titular(nombre, edad, minutos);
        } else {
            System.out.print("Partidos ingresados desde el banco: ");
            int partidos = scanner.nextInt();
            jugador = new Suplente(nombre, edad, partidos);
        }

        if (!equipos.isEmpty()) {
            System.out.println("Seleccioná equipo (por número):");
            for (int i = 0; i < equipos.size(); i++) {
                System.out.println(i + 1 + ". " + equipos.get(i).getNombre());
            }
            int idx = scanner.nextInt() - 1;
            equipos.get(idx).agregarJugador(jugador);
            System.out.println("Jugador agregado al equipo " + equipos.get(idx).getNombre());
        } else {
            System.out.println("No hay equipos creados aún.");
        }
    }

    public static void crearEquipo() {
        System.out.print("Nombre del equipo: ");
        String nombre = scanner.nextLine();
        equipos.add(new Equipo(nombre));
        System.out.println("Equipo creado correctamente.");
    }

    public static void registrarPartido() {
        if (equipos.size() < 2) {
            System.out.println("Necesitás al menos 2 equipos.");
            return;
        }

        System.out.println("Seleccioná equipo local:");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println(i + 1 + ". " + equipos.get(i).getNombre());
        }
        int localIdx = scanner.nextInt() - 1;

        System.out.println("Seleccioná equipo visitante:");
        int visitanteIdx = scanner.nextInt() - 1;

        Partido partido = new Partido(equipos.get(localIdx), equipos.get(visitanteIdx));
        scanner.nextLine();

        System.out.print("¿Cuántos goles se registraron en total?: ");
        int goles = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < goles; i++) {
            System.out.print("Nombre del jugador que hizo el gol #" + (i + 1) + ": ");
            String nombre = scanner.nextLine();

            // Buscar jugador por nombre
            for (Equipo eq : List.of(equipos.get(localIdx), equipos.get(visitanteIdx))) {
                for (Jugador j : eq.getJugadores()) {
                    if (j.getNombre().equalsIgnoreCase(nombre)) {
                        partido.asignarGol(j);
                    }
                }
            }
        }

        partidos.add(partido);
        System.out.println("Partido registrado con éxito.");
    }

    public static void mostrarGoleador() {
        List<Jugador> todos = new ArrayList<>();
        for (Equipo e : equipos) {
            todos.addAll(e.getJugadores());
        }

        jugadorService.obtenerGoleador(todos).ifPresentOrElse(
                j -> System.out.println("Goleador: " + j.getNombre() + " (" + j.getGoles() + " goles)"),
                () -> System.out.println("No hay goles registrados aún.")
        );
    }

    public static void listarTodosLosJugadores() {
        for (Equipo equipo : equipos) {
            System.out.println("\nEquipo: " + equipo.getNombre());
            jugadorService.mostrarJugadores(equipo.getJugadores());
        }
    }
}
