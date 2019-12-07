package com.ComplejidadComputacional.MaquinaTuring;

import java.util.ArrayList;
import java.util.Arrays;

public class Multicinta implements Cloneable {

    private Cinta[] cintas;

    public Multicinta(String cadenaInicio, String simboloBlanco, int numeroCintas) {
        this((cadenaInicio.length() == 0) ? new String[0] : cadenaInicio.split(""), simboloBlanco, numeroCintas);
    }

    public Multicinta(String[] cadenaInicio, String simboloBlanco, int numeroCintas) {
        this(new ArrayList<>(Arrays.asList(cadenaInicio)), simboloBlanco, numeroCintas);
    }

    public Multicinta(ArrayList<String> cadenaInicio, String simboloBlanco, int numeroCintas) {
        // No se guarda el símbolo vacío porque se encargan las cintas individuales
        // No se guarda el número de cintas porque es el tamaño del atributo "cintas"

        this.cintas = new Cinta[numeroCintas];
        this.cintas[0] = new Cinta(cadenaInicio, simboloBlanco);
        for (int i = 1; i < numeroCintas; i++)
            this.cintas[i] = new Cinta("", simboloBlanco);
    }

    public String[] leer() {
        String[] simbolos = new String[cintas.length];
        for (int i = 0; i < cintas.length; i++)
            simbolos[i] = cintas[i].leer();
        return simbolos;
    }

    public void mover(SalidaTransicion movimiento) {
        mover(movimiento.getSimbolosSustitucion(), movimiento.getDirecciones());
    }

    private void mover(String[] simbolosSustitucion, Direccion[] direcciones) {
        for (int i = 0; i < direcciones.length; i++) {
            cintas[i].mover(simbolosSustitucion[i], direcciones[i]);
        }
    }

    public Object clone() {
        Multicinta copia = null;
        try {
            copia = (Multicinta) super.clone();
            copia.cintas = copia.cintas.clone();
        } catch (CloneNotSupportedException ignored) {
        }
        return copia;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < cintas.length; i++)
            string.append("\nCinta ").append(i + 1).append(": ").append(cintas[i].toString());
        return string.toString();
    }

}
