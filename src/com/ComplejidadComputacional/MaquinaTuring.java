package com.ComplejidadComputacional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MaquinaTuring {

    // A pesar de que los atributos "alfabeto" y "alfabetoPila" pueden ser locales actualmente
    // se ha decidido que sean un argumento pensando en posibles ampliaciones de la práctica
    private Vector<String> alfabetoEntrada;
    private Vector<String> alfabetoCinta;
    private Estado estadoInicial;
    private String simboloBlanco;

    public MaquinaTuring(String nombreFichero) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(nombreFichero)));
        Vector<String[]> tokens = new Vector<>(); // Tokens de cada fila del archivo no vacía
        while (br.ready()) {
            String[] tokensLinea = br.readLine().replaceFirst("#.*", " ").split("\\s+");
            if (tokensLinea.length > 0) tokens.add(tokensLinea);
        }

        if (tokens.size() > 6) {
            ArrayList<String> nombresEstados = new ArrayList<>(Arrays.asList(tokens.get(0)));

            if (!Arrays.stream(tokens.get(5)).allMatch(nombresEstados::contains))
                throw new IllegalArgumentException("En la sexta línea deben estar los estados de aceptación.");

            if (tokens.get(3).length != 1)
                throw new IllegalArgumentException("Sólo puede haber un estado inicial.");
            else if (!nombresEstados.contains(tokens.get(3)[0]))
                throw new IllegalArgumentException("El estado inicial debe ser un estado existente.");
            String nombreEstadoInicial = tokens.get(3)[0];

            HashMap<String, Estado> estados = new HashMap<>();
            for (String nombre : nombresEstados) {
                estados.put(nombre, new Estado(nombre, Arrays.asList(tokens.get(5)).contains(nombre)));
            }
            estadoInicial = estados.get(nombreEstadoInicial);

            alfabetoEntrada = Arrays.stream(tokens.get(1)).distinct().collect(Collectors.toCollection(Vector::new));
            alfabetoCinta = Arrays.stream(tokens.get(2)).distinct().collect(Collectors.toCollection(Vector::new));

            if (!alfabetoCinta.containsAll(alfabetoEntrada))
                throw new IllegalArgumentException("El alfabeto de entrada debe ser un subconjunto del alfabeto de la cinta.");

            if (tokens.get(4).length == 1 && !alfabetoEntrada.contains(tokens.get(4)[0]) && alfabetoCinta.contains(tokens.get(4)[0]))
                simboloBlanco = tokens.get(4)[0];
            else
                throw new IllegalArgumentException("El simbolo vacío debe pertenecer al alfabeto de la cinta pero no al de entrada.");

            for (String[] transicion : tokens.subList(6, tokens.size())) {
                if (transicion.length == 5 &&
                        nombresEstados.contains(transicion[0]) &&
                        alfabetoCinta.contains(transicion[1]) &&
                        nombresEstados.contains(transicion[2]) &&
                        alfabetoCinta.contains(transicion[3])) {

                    try {
                        estados.get(transicion[0]).addTransicion(
                                transicion[1],
                                estados.get(transicion[2]),
                                transicion[3],
                                Direccion.valueOf(transicion[4]));
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Dirección no apta.");
                    }
                } else {
                    throw new IllegalArgumentException("Datos mal colocados en las transiciones.");
                }
            }
        } else {
            throw new IllegalArgumentException("Formato de fichero incorrecto.");
        }
    }

    public boolean comprobarCadena(String cadena) throws CloneNotSupportedException {
        return estadoInicial.comprobarCadena(new Cinta(cadena, simboloBlanco));
    }
}
