package personajes;

import Juego.Borde;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * Clase encargada de trabajar con todo que tengas que ver con el enemigo1
 */
public class Enemigo1 {

    private int posX;
    private int posY;
    private int ancho;
    private int alto, anchop, altop, isla = 0, velocidad;
    private int izqisla, derisla, bsuperior, rect;
    private Image imagen;
    private Random ram;
    private Avion avi;
    private Borde[] bordes;
    private boolean rebote = false;

    /**
     * Constructor de Enemigo1 encargado de inicializar los valores
     */
    public Enemigo1(Avion avi, Borde[] bor, int ancho, int alto) {
        this.avi = avi;
        bordes = new Borde[bor.length];
        anchop = ancho;
        altop = alto;
        for (int i = 0; i < bor.length; i++) {
            bordes[i] = bor[i];
        }
        posX = 0;//670;
        posY = 0 - 90;//710; 
        alto = 90;
        this.ancho = 90;
        this.alto = 90;
        ram = new Random();
        izqisla = (int) ((anchop / 2) - (bordes[0].getAncho_isla() / 2));
        derisla = (int) ((anchop / 2) + (bordes[0].getAncho_isla() / 2));
        try {
            imagen = ImageIO.read(new File("src/imagenes_juego/enemigo2.png"));
        } catch (IOException ex) {
            System.out.println("No existe imagen");
        }
    }

    /**
     * MEtodo encargado de asignar las posicion del enemigo1
     */
    public void posicion() {

        int dentro, fuera;
        dentro = (int) ((anchop - bordes[bsuperior].getAncho()) + 1);
        fuera = (int) bordes[bsuperior].getAncho();
        if (isla == 0) {
            do {
                posX = fuera + ram.nextInt(dentro);

            } while ((posX > dentro) || posX < fuera);
        }

    }

    /**
     * Metodo encargado de mover hacia la derehca el enemigo1
     */
    public void desplazar() {
        if ((posX != ((anchop - bordes[rect].getAncho()) - ancho)) && rebote == false) {
            posX = posX + 1;
            if (posX >= (anchop - bordes[rect].getAncho() - 90)) {
                rebote = true;
            }
        }

        if ((posX != bordes[rect].getAncho()) && rebote == true) {
            posX = posX - 1;
            if ((posX <= (bordes[rect].getAncho()))) {
                rebote = false;
            }
        }
    }

    /**
     * Metodo encargado de hacer que el enmigo baje con el mapa
     */
    public void bajar() {

        posY = posY + velocidad;
        if (posY > altop + 90) {
            posicion();
            rect = bsuperior;
            posY = 0 - 90;
        }

    }

    /**
     * Metodo encargado de retornar el valor del atributo Bsuperiro
     */
    public int getBsuperior() {
        return bsuperior;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo Bsuperior
     */
    public void setBsuperior(int bsuperior) {
        this.bsuperior = bsuperior;
    }

    /**
     * Metodo encargado de retornar el valor del atributo posX
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
     * Metodo encargado de retornar el valor del atributo ancho
     */
    public int getAncho() {
        return ancho;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo sncho
     */
    public void setAncho(int ancho) {
        this.ancho = ancho;
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
     * Metodo encargado de retornar el valor del atributo imagen
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo Imagen
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    /**
     * Metodo encargado de retornar el valor del atributo isla
     */
    public int getIsla() {
        return isla;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo Isla
     */
    public void setIsla(int isla) {
        this.isla = isla;
    }

    /**
     * Metodo encargado de retornar el valor del atributo valocidad
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo veloidad
     */
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    /**
     * Metodo encargado de crear el rectangulo y darle una posicion y tamaño
     */
    public Rectangle2D area() {
        return new Rectangle2D.Double(posX, posY, ancho, alto);

    }
}
