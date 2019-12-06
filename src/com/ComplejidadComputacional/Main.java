package com.ComplejidadComputacional;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner scanner = new Scanner(System.in);
        MaquinaTuring maquinaTuring = null;
        while (maquinaTuring == null) {
            System.out.println("Introduce el nombre del archivo del autómata en el directorio \"Ejemplos\":");
            try {
                maquinaTuring = new MaquinaTuring("Ejemplos/" + scanner.next());
            } catch (IOException e) {
                System.out.println("\u001B[31mProblemas abriendo el archivo.\033[0;0m");
            } catch (IllegalArgumentException e) {
                System.out.println("\u001B[31m" + e.getMessage() + "\033[0;0m");
            }
        }

        while (true) {
            System.out.println("\nIntroduce la cadena a comprobar (o exit para terminar):");
            String cadena = scanner.next();
            if (cadena.equals("exit")) break;
            System.out.println("Resultado: " + maquinaTuring.comprobarCadena(cadena));
        }
    }
}
