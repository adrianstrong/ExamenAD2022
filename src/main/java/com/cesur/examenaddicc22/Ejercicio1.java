package com.cesur.examenaddicc22;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

class Ejercicio1 {

    /**
     * Enunciado:
     * 
     * Completar el método estadísticasDeArchivo de manera que lea el archivo
     * de texto que se le pasa como parámetro, lo analice y muestre por consola 
     * el número de caracteres, palabras y líneas total.
     * 
     * Modificar solo el código del método.
     * 
     */
    
    static void solucion() {

        estadísticasDeArchivo("pom.xml");

    }

    private static void estadísticasDeArchivo(String archivo) {
            File input = new File(archivo);
            try {
                BufferedReader br = new BufferedReader(new FileReader(input, StandardCharsets.UTF_8));

                String line = "";
                Integer lines = 0;
                Integer amountWords = 0;
                Integer amountChars = 0;
                while ((line = br.readLine()) != null) {
                    lines++;
                    //System.out.println(line);
                    String[] words = line.split(" ");
                    for (String word : words) {
                        amountChars += word.length();
                    }
                    amountWords+= words.length;
                }
                System.out.println("Lineas: "+lines+", Palabras: " +amountWords+ ", Caracteres: " +amountChars);
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
