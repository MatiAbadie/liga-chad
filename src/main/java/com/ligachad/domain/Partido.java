package com.ligachad.domain;

import java.util.HashMap;
import java.util.Map;

public class Partido {
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private String resultado;
    private Map<Jugador, Integer> golesPorJugador;

    public Partido(Equipo local, Equipo visitante) {
        this.equipoLocal = local;
        this.equipoVisitante = visitante;
        this.golesPorJugador = new HashMap<>();
    }

    public void asignarGol(Jugador jugador) {
        golesPorJugador.put(jugador, golesPorJugador.getOrDefault(jugador, 0) + 1);
        jugador.anotarGol();
    }

    public Map<Jugador, Integer> getGolesPorJugador() {
        return golesPorJugador;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
