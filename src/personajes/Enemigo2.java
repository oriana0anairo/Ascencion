package personajes;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * Clase encargada de todo lo relacionado con el enemigo2
 */
public class Enemigo2 {

    private int posX, ancho;
    private int posY, alto, anchop, altop, velocidad;
    private Image imagen;
    private Random ram;

    /**
     * Constructor de la clase Enemigo2, encargado de inicializar los atributos
     * de la clase
     */
    public Enemigo2(int ancho, int largo) {
        anchop = ancho;
        altop = largo;
        posX = 0 - 90;
        posY = 200;
        this.ancho = 90;
        this.alto = 90;
        ram = new Random();
        try {
            imagen = ImageIO.read(new File("src/imagenes_juego/enemigo1.png"));
        } catch (IOException ex) {
            System.out.println("No existe imagen");
        }
    }

    /**
     * Metodo encargo de posicionar en enemigo
     */
    public void posicion() {
        int dentro, fuera;

        posY = 1 + ram.nextInt((altop - 500));
    }

    /**
     * Metodos encargado de hacer que el enemigo rebote
     */
    public void desplazar() {
        if (posX != (anchop * 2)) {
            posX = posX + 8;
        }
        if (posX > anchop * 2) {
            posX = 0 - 90;
        }
    }

    /**
     * Metodo encargado de hacer que el enemigo baje
     */
    public void bajar() {

        posY = posY + velocidad;
        if (posY > altop + 90) {
            posicion();
            posX = 0 - 90;
        }

    }

    /**
     * Metodo encargado de retornar el valor del atributo posx
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo posX
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Metodo encargado de retornar el valor del atributo ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo ancho
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    /**
     * Metodo encargado de retornar el valor del atributo posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo posY
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Metodo encargado de retornar el valor del atributo alto
     */
    public int getAlto() {
        return alto;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo alto
     */
    public void setAlto(int alto) {
        this.alto = alto;
    }

    /**
     * Metodo encargado de retornar el valor del atributo Imagen
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo imagen
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    /**
     * Metodo encargado de retornar el valor del atributo valocidad
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo valocidad
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * Metodo encargado de crear el rectangulo del enemigo 2 y darle una
     * posicion y un tamaño
     */
    public Rectangle2D area() {
        return new Rectangle2D.Double(posX, posY, ancho, alto);
    }

}
