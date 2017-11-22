/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lourdes
 */
public class Top_10 {

    boolean entra = false;
    int[] puntajes;
    String[] nombres;
    String[] posiciones;
    Usuario usu;

    public Top_10(Usuario usu) throws IOException {
        this.usu = usu;
        puntajes = new int[10];
        nombres = new String[10];
        posiciones=new String[10];
        leertop();
        
    }

    public void leertop() {
        String[] linea;
        File archivo = new File("src/Archivos/top10.txt");
        FileReader leer = null;
        try {
            leer = new FileReader(archivo);
        } catch (Exception e) {
            System.out.println("Archivo no existe");
        }
        BufferedReader entrada = new BufferedReader(leer);

        linea = new String[10];
        for (int i = 0; i < 10; i++) {
            try {
                linea[i] = entrada.readLine();
                //entrada.close();

            } catch (IOException ex) {
                Logger.getLogger(Top_10.class.getName()).log(Level.SEVERE, null, ex);
            }//System.out.println("que paso :c ?");
        }
        String[] separar;
        for (int i = 0; i < 10; i++) {
            separar = linea[i].split(",");
            nombres[i] = separar[0];
            puntajes[i] = Integer.parseInt(separar[1]);
        }
        burbuja(puntajes);
        for (int i = 0; i < 10; i++) {
            posiciones[i]= nombres[i]+" "+puntajes[i];
        }

    }

    public void comparar() throws IOException {
        if (usu.getPuntuacion() > 0) {
            for (int i = 0; i < 10; i++) {
                if (usu.getPuntuacion() >= puntajes[i]) {
                    puntajes[9] = usu.getPuntuacion();
                    nombres[9] = usu.getNombre();
                }
            }
        }
        burbuja(puntajes);
        String[] relleno = new String[10];
        for (int i = 0; i < 10; i++) {
            relleno[i] = nombres[i] + "," + puntajes[i];
            System.out.println(relleno[i]);
        }
        escribirtop(relleno);

    }

    public void escribirtop(String[] datos) throws IOException {
        borrararchivo();
        File archivo = new File("src/Archivos/top10.txt");
        FileWriter escribir = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(escribir);
        for (int i = 0; i < 10; i++) {
           
                bw.write(datos[i]+"\n");
        }
        bw.close();
        leertop();
    }

    public void borrararchivo() throws IOException {
        File archivo = new File("src/Archivos/top10.txt");
        FileWriter escribir = new FileWriter(archivo);
        BufferedWriter bw = new BufferedWriter(escribir);
        bw.write("");
        bw.close();       
    }

    void burbuja(int arreglo[]) {

        for (int i = 0; i < arreglo.length - 1; i++) {

            for (int j = 0; j < arreglo.length - 1; j++) {

                if (arreglo[j] < arreglo[j + 1]) {
                    String aux = nombres[j + 1];
                    int tmp = arreglo[j + 1];
                    nombres[j + 1] = nombres[j];
                    arreglo[j + 1] = arreglo[j];
                    nombres[j] = aux;
                    arreglo[j] = tmp;

                }

            }

        }
    }

    public String getPosiciones(int pos) {
        return posiciones[pos];
    }
    
    public boolean isEntra() {
        return entra;
    }

    public void setEntra(boolean entra) {
        this.entra = entra;
    }

    public int[] getPuntajes() {
        return puntajes;
    }

    public void setPuntajes(int[] puntajes) {
        this.puntajes = puntajes;
    }

    public String[] getNombres() {
        return nombres;
    }

    public void setNombres(String[] nombres) {
        this.nombres = nombres;
    }

}
