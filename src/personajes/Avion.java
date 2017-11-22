package personajes;

//import com.sun.prism.Graphics;
import Juego.Juego_p;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 * Clase encargada de todo lo realiconado con la nave dle juego
 */
public class Avion {

    private double tanque = 300;
    private int posX;
    private int posY, vida;
    private Image imagen, vidas;
    private int anchura, alto;
    private Enemigo1[] ene;
    private Enemigo2 ened;
    private Juego_p panel;

    /**
     * Contructor encargado de inicializar los atributos del juegos
     */
    public Avion(int largo, int ancho, Enemigo1[] ene1, Enemigo2 ene2, Juego_p panel) {
        this.vida = 3;
        this.panel = panel;
        ene = new Enemigo1[ene1.length];
        for (int i = 0; i < ene1.length; i++) {
            ene[i] = ene1[i];
        }
        ened = ene2;
        posX = 600;//670;

        try {
            imagen = ImageIO.read(new File("src/imagenes_juego/avion.png"));
        } catch (IOException ex) {
            System.out.println("No existe imagen");
        }
        ImageObserver observer = null;
        anchura = 80;
        alto = 60;
        posY = largo - alto - 20;//710; 

    }

    /**
     * Metodo encargado de las intersecciones con el ambiente
     */
    public void colicion_con_enemigos() {
        if (getRectangulo().intersects(ened.area())) {

            panel.reinicios();
            vida = vida - 1;
            tanque = 300;
        }
        for (int i = 0; i < ene.length; i++) {
            if (getRectangulo().intersects(ene[i].area())) {
                panel.reinicios();
                vida = vida - 1;
                tanque = 300;
            }
        }
        if (tanque <= 0) {
            panel.reinicios();
            vida = vida - 1;
            tanque = 300;
        }
    }

    /**
     * Metodo encargado del movimiento de la nave
     */
    public void movimiento(String lado) {
        if (lado.compareTo("Izquierda") == 0) {

            if (getPosX() > 0) {
                setPosX(getPosX() - 10);

            }
        }
        if (lado.compareTo("Derecha") == 0) {
            if (getPosX() < 1150) {
                setPosX(getPosX() + 10);
            }
        }

    }

    /**
     * Metodo encargado de crear el rectangulo de la nave y darle posicion
     */
    public Rectangle2D getRectangulo() {

        return new Rectangle2D.Double(posX, posY + 20, (anchura), (alto - 20));

    }

    /**
     * Metodo encargado de retornar el valor del atributo tanque
     */
    public double getTanque() {
        return tanque;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo tanque
     */
    public void setTanque(double tanque) {
        this.tanque = tanque;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo vida
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Metodo encargado de retornar el valor del atributo vida
     */
    public int getVida() {
        return vida;
    }

    /**
     * Metodo encargado de retornar el valor del atributo anchura
     */
    public int getAnchura() {
        return anchura;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo anchura
     */
    public void setAnchura(int anchura) {
        this.anchura = anchura;
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
     * Metodo encargado de retornar el valor del atributo imagen
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * Metodo encargado de retornar el valor del atributo vidas
     */
    public Image getVidas() throws IOException {
        if (vida == 3) {
            return vidas = ImageIO.read(new File("src/imagenes_juego/vida.png"));
        }
        if (vida == 2) {
            return vidas = ImageIO.read(new File("src/imagenes_juego/vida1.png"));
        }
        if (vida == 1) {
            return vidas = ImageIO.read(new File("src/imagenes_juego/vida2.png"));
        }
        return vidas = ImageIO.read(new File("src/imagenes_juego/vida3.png"));

    }

    /**
     * Metodo encargado de cambiar el valor del atributo imagen
     */
    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

}
