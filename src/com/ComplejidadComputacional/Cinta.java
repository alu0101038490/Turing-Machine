package com.ComplejidadComputacional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Cinta implements Cloneable {

    private ArrayList<ArrayList<String>> cintas;
    private int[] posiciones;
    private String simboloBlanco;

    public Cinta(String cadenaInicio, String simboloBlanco, int numeroCintas) {
        this((cadenaInicio.length() == 0) ? new ArrayList<>() : new ArrayList<>(Arrays.asList(cadenaInicio.split(""))), simboloBlanco, numeroCintas);
    }

    public Cinta(String[] cadenaInicio, String simboloBlanco, int numeroCintas) {
        this((ArrayList<String>) Arrays.asList(cadenaInicio), simboloBlanco, numeroCintas);
    }

    public Cinta(ArrayList<String> cadenaInicio, String simboloBlanco, int numeroCintas) {
        this.cintas = new ArrayList<>();
        this.posiciones = new int[numeroCintas];
        this.simboloBlanco = simboloBlanco;

        ArrayList<String> cadenaVacia = new ArrayList<>(Collections.singleton(this.simboloBlanco));
        this.cintas.add(cadenaInicio.isEmpty() ? cadenaVacia : cadenaInicio);
        for (int i = 1; i < numeroCintas; i++)
            this.cintas.add((ArrayList<String>) cadenaVacia.clone());
    }

    public String[] leer() {
        String[] simbolos = new String[cintas.size()];
        Arrays.setAll(simbolos, i -> cintas.get(i).get(posiciones[i]));
        return simbolos;
    }

    public void mover(SalidaTransicion movimiento) {
        mover(movimiento.getSimbolosSustitucion(), movimiento.getDirecciones());
    }

    private void mover(String[] simbolosSustitucion, Direccion[] direcciones) {
        for (int i = 0; i < direcciones.length; i++) {
            cintas.get(i).set(posiciones[i], simbolosSustitucion[i]);
            if (direcciones[i].equals(Direccion.R)) {
                posiciones[i]++;
                if (posiciones[i] >= cintas.get(i).size())
                    cintas.get(i).add(simboloBlanco);
            } else if (direcciones[i].equals(Direccion.L)) {
                if (posiciones[i] == 0)
                    cintas.get(i).add(0, simboloBlanco);
                else
                    posiciones[i]--;
            }
        }
    }

    public Object clone() throws CloneNotSupportedException {
        Cinta copia = (Cinta) super.clone();
        copia.cintas = (ArrayList<ArrayList<String>>) copia.cintas.clone();
        copia.posiciones = copia.posiciones.clone();
        return copia;
    }

    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < cintas.size(); i++) {
            StringBuilder situacionCinta = new StringBuilder(String.join("", cintas.get(i)));
            situacionCinta.replace(posiciones[i], posiciones[i] + 1, "\u001B[31m" + cintas.get(i).get(posiciones[i]) + "\033[0;0m");
            resultado.append("\nCinta ").append(i+1).append(": ").append(situacionCinta.toString());
        }
        return resultado.toString();
    }
}
