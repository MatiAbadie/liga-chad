package com.ligachad.domain;

public class Titular extends Jugador{
    private int minutosJugados;

    public Titular(String nombre, int edad, int minutosJugados) {
        super(nombre, edad);
        this.minutosJugados = minutosJugados;
    }

    @Override
    public String getTipo() {
        return "Titular";
    }

    public int getMinutosJugados() {
        return minutosJugados;
    }

    public void setMinutosJugados(int minutosJugados) {
        this.minutosJugados = minutosJugados;
    }
}
