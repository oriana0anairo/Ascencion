package Datos;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Clase encargada de conseguir el tamaño de el monitor donde se esta carriendo
 * el juego
 */
public class Pantalla {

    private Dimension tamaño = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * Atributo que guarda elñ ancho de la pantalla
     */
    private int ancho;
    /**
     * Atributo que guarda el largo de la pantalla
     */
    private int largo;

    /**
     * Constructor de la clase pantalla, asigna el valor de ancho y largo de la pantalla
     */
    public Pantalla() {
        ancho = tamaño.width;
        largo = tamaño.height;
    }

    /**
     * Devuelve el anocho de la pantalla
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Devuelve el largo de la pantalla
     */
    public int getLargo() {
        return largo;
    }

}
