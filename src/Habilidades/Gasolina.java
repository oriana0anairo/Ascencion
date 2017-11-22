package Habilidades;

import Juego.Borde;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import personajes.Avion;

/**
 * Clase que se encarga de el factor de la gasolina en el juego
 */
public class Gasolina {

    /**
     * Atributo encargado de la posicion en X de la gasolina
     */
    private int posx;
    /**
     * Atributo encargado de la posicion en Y de la gasolina
     */
    private int posy;
    /**
     * Atributo encargado de el ancho de la imagen de la gasolina
     */
    private int ancho;
    /**
     * Atributos encargados de los limitantes para que salgan dentro del mapa
     */
    private int alto, anchop, altop, izqisla, derisla, bsuperior, isla, velocidad;
    /**
     * Atributo encargado de la imagen de la gasolina
     */
    private Image imagen;
    private Avion avi;
    private Borde[] bordes;
    Random ram;

    /**
     * Constructor encargado de inicializar los valores de los atributos de la
     * clase Gasolina
     */
    public Gasolina(int ancho, int largo, Avion avi, Borde[] bor) {
        posx = 2500;
        posy = 5000;
        this.ancho = 90;
        this.alto = 90;
        this.avi = avi;
        bordes = new Borde[bor.length];
        anchop = ancho;
        altop = largo;
        for (int i = 0; i < bor.length; i++) {
            bordes[i] = bor[i];
        }

        ram = new Random();
        izqisla = (int) ((anchop / 2) - (bordes[0].getAncho_isla() / 2));
        derisla = (int) ((anchop / 2) + (bordes[0].getAncho_isla() / 2));
        try {
            imagen = ImageIO.read(new File("src/imagenes_juego/gasolina.png"));
        } catch (IOException ex) {
            System.out.println("No existe imagen");
        }
    }

    /**
     * Da la pocision donde aparecera la gasolina en el el mapa del juego
     */
    public void posicion() {

        int dentro, fuera;
        dentro = (int) ((anchop - bordes[bsuperior].getAncho()) + 1);
        fuera = (int) bordes[bsuperior].getAncho();
        if (isla == 0) {
            do {
                posx = fuera + ram.nextInt(dentro);

            } while ((posx > dentro) || posx < fuera);
        }

    }

    /**
     * Metodo encargado de hacer que le imagen de la gasolina baje
     */
    public void bajar() {

        posy = posy + velocidad;
        if (posy > altop + 90) {
            posicion();
            posy = 0 - 90;
        }

    }

    /**
     * Metodo Encargado de crear el rectangulo y darle la posicion
     */
    public Rectangle2D area() {
        return new Rectangle2D.Double(posx, posy, ancho, alto);

    }

    /**
     * Metodo getter encargado de retornar la variable ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Metodo setter encargado de cambiar el atributo ancho
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * Metodo encargado de devolver el atrbuto alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Metodo encargado de cambiar el atrbuto alto
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     * Metodo encargado de retornar el atributo posx
     */
    public int getPosx() {
        return posx;
    }

    /**
     * Metodo encargado de cambiar el atributo posx
     */
    public void setPosx(int posx) {
        this.posx = posx;
    }

    /**
     * Metodo encargo de devulver el atributo posy
     */
    public int getPosy() {
        return posy;
    }

    /**
     * Metodo encargado de cambiar el atributo posy
     */
    public void setPosy(int posy) {
        this.posy = posy;
    }

    /**
     * Metodo encargo de devulver el atributo Bsuperior
     */
    public int getBsuperior() {
        return bsuperior;
    }

    /**
     * Metodo encargado de cambiar el atributo bsuperior
     */
    public void setBsuperior(int bsuperior) {
        this.bsuperior = bsuperior;
    }

    /**
     * Metodo encargo de devulver el atributo isla
     */
    public int getIsla() {
        return isla;
    }

    /**
     * Metodo encargado de cambiar el atributo isla
     */
    public void setIsla(int isla) {
        this.isla = isla;
    }

    /**
     * Metodo encargo de devulver el atributo valocidad
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * Metodo encargado de cambiar el atributo velocidad
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * Metodo encargo de devulver el atributo imagen
     */
    public Image getImagen() {
        return imagen;
    }

}
