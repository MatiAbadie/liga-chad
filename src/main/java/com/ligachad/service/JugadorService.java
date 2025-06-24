package com.ligachad.service;

import com.ligachad.domain.Jugador;
import com.ligachad.domain.Suplente;
import com.ligachad.domain.Titular;

import java.util.List;
import java.util.Optional;

public class JugadorService {

    // Mostrar todos los jugadores y su tipo
    public void mostrarJugadores(List<Jugador> jugadores) {
        for (Jugador j : jugadores) {
            System.out.println(j.getNombre() + " - " + j.getTipo() + " - Goles: " + j.getGoles());
        }
    }

    // Buscar goleador de la liga
    public Optional<Jugador> obtenerGoleador(List<Jugador> jugadores) {
        return jugadores.stream().max((j1, j2) -> Integer.compare(j1.getGoles(), j2.getGoles()));
    }

    // Mostrar suplentes que nunca ingresaron
    public void mostrarSuplentesSinIngresar(List<Jugador> jugadores) {
        for (Jugador j : jugadores) {
            if (j instanceof Suplente s && s.getPartidosIngresadosDesdeElBanco() == 0) {
                System.out.println("Suplente sin ingresar: " + j.getNombre());
            }
        }
    }

    // Mostrar titular con más minutos
    public Optional<Titular> titularMasMinutos(List<Jugador> jugadores) {
        return jugadores.stream()
                .filter(j -> j instanceof Titular)
                .map(j -> (Titular) j)
                .max((t1, t2) -> Integer.compare(t1.getMinutosJugados(), t2.getMinutosJugados()));
    }
}
