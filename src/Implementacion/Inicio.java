package Implementacion;

import Datos.Imagenes;
import java.io.IOException;

/**
 * Clase encargado de inciar todo
 */
public class Inicio extends Ventana implements Imagenes {

    /**
     * Costructor encargado de incializar
     */
    public Inicio() throws IOException {
        super();
    }

    /**
     * Metodo Main donde se corre el programa
     */
    public static void main(String[] args) throws IOException {
        Inicio run = new Inicio();
        run.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

}
