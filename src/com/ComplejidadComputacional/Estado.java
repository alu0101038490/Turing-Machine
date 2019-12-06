package com.ComplejidadComputacional;

import java.util.*;

public class Estado {

    private String nombre;
    private boolean aceptacion;
    // Las transiciones son HashMap para localizarlas con eficiencia
    private HashMap<String, HashSet<SalidaTransicion>> transiciones;

    public Estado(String nombre, boolean aceptacion) {
        this.nombre = nombre;
        this.aceptacion = aceptacion;
        this.transiciones = new HashMap<>();
    }

    public void addTransicion(String simbolo, Estado siguiente, String simboloSustitucion, Direccion direccion) {
        HashSet<SalidaTransicion> salidas = transiciones.getOrDefault(simbolo, new HashSet<>());
        salidas.add(new SalidaTransicion(siguiente, simboloSustitucion, direccion));
        transiciones.put(simbolo, salidas);
    }

    public HashSet<SalidaTransicion> getTransicion(String simbolo) {
        return transiciones.getOrDefault(simbolo, new HashSet<>());
    }

    public boolean comprobarCadena(Cinta entrada) throws CloneNotSupportedException {
        HashSet<SalidaTransicion> salidas = getTransicion(entrada.leer());
        if (salidas.isEmpty()) {
            System.out.println(entrada.toString());
            return aceptacion;
        }
        for (SalidaTransicion transicion : salidas) {
            Cinta copiaEntrada = (Cinta) entrada.clone();
            copiaEntrada.mover(transicion.getSimboloSustitucion(), transicion.getDireccion());
            if (transicion.getSiguiente().comprobarCadena(copiaEntrada)) return true;
        }
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isAceptacion() {
        return aceptacion;
    }

    @Override
    public boolean equals(Object obj) {
        // Nos interesa que se identifiquen por los nombres
        return obj instanceof Estado && this.nombre.equals(((Estado) obj).nombre);
    }
}
