package com.ligachad.service;

import com.ligachad.domain.Equipo;
import com.ligachad.domain.Jugador;

import java.util.List;

public class EquipoService {

    //transferir jugador entre equipos
    public void transferirJugador(Jugador jugador, Equipo origen, Equipo destino) {
        origen.eliminarJugador(jugador);
        destino.agregarJugador(jugador);
        System.out.println("Jugador transferido: " + jugador.getNombre());
    }

    //calcular promedio de goles del equipo
    public double promedioGoles(Equipo equipo) {
        List<Jugador> jugadores = equipo.getJugadores();
        if (jugadores.isEmpty()) return 0;

        int total = jugadores.stream().mapToInt(Jugador::getGoles).sum();
        return (double) total / jugadores.size();
    }

    //mostrar jugadores sin goles
    public void mostrarJugadoresSinGoles(Equipo equipo) {
        for (Jugador j : equipo.getJugadores()) {
            if (j.getGoles() == 0) {
                System.out.println(j.getNombre() + " - sin goles");
            }
        }
    }
}
