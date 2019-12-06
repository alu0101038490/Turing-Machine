package com.ComplejidadComputacional;

import java.util.ArrayList;
import java.util.Arrays;

public class Cinta implements Cloneable {

    private ArrayList<String> cadena;
    private int posicion;
    private String simboloBlanco;

    public Cinta(String cadenaInicio, String simboloBlanco) {
        this((cadenaInicio.length() == 0) ? new ArrayList<>() : new ArrayList<>(Arrays.asList(cadenaInicio.split(""))), simboloBlanco);
    }

    public Cinta(String[] cadenaInicio, String simboloBlanco) {
        this((ArrayList<String>) Arrays.asList(cadenaInicio), simboloBlanco);
    }

    public Cinta(ArrayList<String> cadenaInicio, String simboloBlanco) {
        this.cadena = cadenaInicio;
        this.posicion = 0;
        this.simboloBlanco = simboloBlanco;

        if (this.cadena.isEmpty()) this.cadena.add(this.simboloBlanco);
    }

    public String leer() {
        return cadena.get(posicion);
    }

    public void mover(String simbolo, Direccion direccion) {
        cadena.set(posicion, simbolo);
        if (direccion.equals(Direccion.R)) {
            posicion++;
            if (posicion >= cadena.size())
                cadena.add(simboloBlanco);
        } else if (direccion.equals(Direccion.L)) {
            if (posicion == 0)
                cadena.add(0, simboloBlanco);
            else
                posicion--;
        }
    }

    public Object clone() throws CloneNotSupportedException {
        Cinta copia = (Cinta) super.clone();
        copia.cadena = (ArrayList<String>) copia.cadena.clone();
        return copia;
    }
}
