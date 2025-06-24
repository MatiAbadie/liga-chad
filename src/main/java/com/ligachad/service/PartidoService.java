package com.ligachad.service;

import com.ligachad.domain.Equipo;
import com.ligachad.domain.Jugador;
import com.ligachad.domain.Partido;

import java.util.List;

public class PartidoService {

    public void registrarGoles(Partido partido, List<Jugador> autores) {
        for (Jugador j : autores) {
            partido.asignarGol(j);
        }
    }

    public int golesDelEquipo(Partido partido, Equipo equipo) {
        return (int) partido.getGolesPorJugador().entrySet().stream()
                .filter(e -> equipo.getJugadores().contains(e.getKey()))
                .mapToInt(e -> e.getValue())
                .sum();
    }
}
