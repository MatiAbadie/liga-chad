package com.ligachad.domain;

public class Jugador {

    protected String nombre;
    protected int edad;
    protected int goles;

    public Jugador(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.goles = 0;
    }

    public void anotarGol() {
        this.goles++;
    }

    public abstract String getTipo(); // "Titular" o "Suplente"

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public int getGoles() {
        return goles;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
