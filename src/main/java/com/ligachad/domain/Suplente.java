package com.ligachad.domain;

public class Suplente extends Jugador{
    private int partidosIngresadosDesdeElBanco;

    public Suplente(String nombre, int edad, int partidosIngresados) {
        super(nombre, edad);
        this.partidosIngresadosDesdeElBanco = partidosIngresados;
    }

    @Override
    public String getTipo() {
        return "Suplente";
    }

    public int getPartidosIngresadosDesdeElBanco() {
        return partidosIngresadosDesdeElBanco;
    }

    public void setPartidosIngresadosDesdeElBanco(int partidos) {
        this.partidosIngresadosDesdeElBanco = partidos;
    }
}
