package com.ComplejidadComputacional;

import java.util.ArrayList;

public class SalidaTransicion {

    private Estado siguiente;
    private String simboloSustitucion;
    private Direccion direccion;

    public SalidaTransicion(Estado siguiente, String simboloSustitucion, Direccion direccion) {
        this.siguiente = siguiente;
        this.simboloSustitucion = simboloSustitucion;
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SalidaTransicion) {
            return siguiente == ((SalidaTransicion) obj).siguiente &&
                    simboloSustitucion.equals(((SalidaTransicion) obj).simboloSustitucion) &&
                    direccion.equals(((SalidaTransicion) obj).direccion);
        } else {
            return false;
        }
    }

    public Estado getSiguiente() {
        return siguiente;
    }

    public String getSimboloSustitucion() {
        return simboloSustitucion;
    }

    public Direccion getDireccion() {
        return direccion;
    }
}
