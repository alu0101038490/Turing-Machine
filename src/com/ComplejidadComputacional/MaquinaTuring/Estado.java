package com.ComplejidadComputacional.MaquinaTuring;

import java.util.*;

public class Estado {

    private String nombre;
    private boolean aceptacion;
    // Las transiciones son HashMap para localizarlas con eficiencia
    private HashMap<ArrayList<String>, HashSet<SalidaTransicion>> transiciones;

    public Estado(String nombre, boolean aceptacion) {
        this.nombre = nombre;
        this.aceptacion = aceptacion;
        this.transiciones = new HashMap<>();
    }

    public void addTransicion(String[] entrada, Estado siguiente, String[] salida, Direccion[] direcciones) {
        HashSet<SalidaTransicion> salidas = transiciones.getOrDefault(entrada, new HashSet<>());
        salidas.add(new SalidaTransicion(siguiente, salida, direcciones));
        transiciones.put(new ArrayList<>(Arrays.asList(entrada)), salidas);
    }

    public HashSet<SalidaTransicion> getTransicion(String[] simbolos) {
        return getTransicion(new ArrayList<>(Arrays.asList(simbolos)));
    }

    public HashSet<SalidaTransicion> getTransicion(ArrayList<String> simbolos) {
        return transiciones.getOrDefault(simbolos, new HashSet<>());
    }

    public boolean comprobarCadena(Multicinta cintas) {
        HashSet<SalidaTransicion> transiciones = getTransicion(cintas.leer());
        if (transiciones.isEmpty()) {
            System.out.println(cintas.toString());
            return aceptacion;
        }
        for (SalidaTransicion transicion : transiciones) {
            // Si hay sólo un camino nos podemos ahorrar la carga de copiar la cinta.
            Multicinta copiaCinta = (transiciones.size() > 1) ? (Multicinta) cintas.clone() : cintas;
            copiaCinta.mover(transicion);
            if (transicion.getSiguiente().comprobarCadena(copiaCinta)) return true;
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
