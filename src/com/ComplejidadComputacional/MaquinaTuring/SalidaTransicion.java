package com.ComplejidadComputacional.MaquinaTuring;

import java.util.Arrays;

public class SalidaTransicion {

    private Estado siguiente;
    private String[] simbolosSustitucion;
    private Direccion[] direcciones;

    public SalidaTransicion(Estado siguiente, String[] simbolosSustitucion, Direccion[] direcciones) {
        if (simbolosSustitucion.length != direcciones.length)
            throw new IllegalArgumentException(
                    "El número de símbolos de sustitución y direcciones de una transición debe ser el mismo.");

        this.siguiente = siguiente;
        this.simbolosSustitucion = simbolosSustitucion;
        this.direcciones = direcciones;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SalidaTransicion) {
            return siguiente == ((SalidaTransicion) obj).siguiente &&
                    Arrays.equals(simbolosSustitucion, ((SalidaTransicion) obj).simbolosSustitucion) &&
                    Arrays.equals(direcciones, ((SalidaTransicion) obj).direcciones);
        } else {
            return false;
        }
    }

    public Estado getSiguiente() {
        return siguiente;
    }

    public String[] getSimbolosSustitucion() {
        return simbolosSustitucion;
    }

    public Direccion[] getDirecciones() {
        return direcciones;
    }
}
