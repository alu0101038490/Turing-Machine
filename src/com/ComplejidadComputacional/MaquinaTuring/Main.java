package com.ComplejidadComputacional.MaquinaTuring;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            MaquinaTuring maquinaTuring = null;
            while (maquinaTuring == null) {
                System.out.println("\nIntroduce el nombre del archivo (o exit para terminar):");
                String nombreArchivo = scanner.next();
                if (nombreArchivo.equals("exit")) return;
                try {
                    // El archivo debe encontrarse en la carpeta Ejemplos
                    maquinaTuring = new MaquinaTuring("Ejemplos/" + nombreArchivo);
                } catch (IOException e) {
                    System.out.println("\u001B[31mProblemas abriendo el archivo.\033[0;0m");
                } catch (IllegalArgumentException e) {
                    System.out.println("\u001B[31m" + e.getMessage() + "\033[0;0m");
                }
            }

            while (true) {
                System.out.println("\nIntroduce la cadena a comprobar (o exit para cambiar de archivo):");
                String cadena = scanner.next();
                if (cadena.equals("exit")) break;
                System.out.println("Resultado: " + maquinaTuring.comprobarCadena(cadena));
            }
        }
    }
}
