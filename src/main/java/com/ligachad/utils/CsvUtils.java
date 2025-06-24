package com.ligachad.utils;

import com.ligachad.domain.Equipo;
import com.ligachad.domain.Jugador;
import com.ligachad.domain.Titular;

import java.io.FileWriter;
import java.io.IOException;

public class CsvUtils {

    public static void exportarJugadores(Equipo equipo) {
        String nombreArchivo = equipo.getNombre().replaceAll("\\s+", "_") + ".csv";

        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            // Cabecera
            writer.write("EsTitular,Nombre,Edad,Goles\n");

            for (Jugador j : equipo.getJugadores()) {
                String esTitular = (j instanceof Titular) ? "SI" : "NO";
                writer.write(String.format("%s,%s,%d,%d\n",
                        esTitular,
                        j.getNombre(),
                        j.getEdad(),
                        j.getGoles()));
            }

            System.out.println("Archivo CSV exportado como: " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("Error al exportar el archivo CSV: " + e.getMessage());
        }
    }
}
