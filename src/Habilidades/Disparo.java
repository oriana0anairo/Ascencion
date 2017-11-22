/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Habilidades;

import Implementacion.Usuario;
import Juego.Juego_p;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import personajes.Avion;
import personajes.Enemigo1;
import personajes.Enemigo2;

/**
 * Clase donde se plantea el disparo del persoje
 */
public class Disparo {

    /**
     * Poscision en X del disparo
     */
    private int posX;
    /**
     * Poscision en Y del disparo
     */
    private int posY, xx, yy;
    /**
     * Atribujo que almacena el ancho del disparo
     */
    private int grosor;
    /**
     * atributo que almacena la imagen des disparo
     */
    private Image imagen;
    /**
     * Atributo que funciona para que el disparo se mueva
     */
    int evento = 9;
    Avion avion;
    Enemigo1[] peje;
    Enemigo2 lagarto;
    int n1, n2, puntos;
    Juego_p juego;
    Usuario usuario;
    Gasolina gas;

    /**
     * Constructor del Disparo, donde se le asignan los valores a los atributos
     */
    public Disparo(Avion avi, Enemigo1[] ene, Enemigo2 migo, Juego_p jue, Usuario usu, Gasolina gas) throws IOException {
        this.puntos = 0;
        this.gas = gas;
        usuario = usu;
        juego = jue;
        peje = new Enemigo1[ene.length];
        for (int i = 0; i < ene.length; i++) {
            peje[i] = ene[i];
        }
        imagen = ImageIO.read(new File("src/imagenes_juego/disparo.png"));
        lagarto = migo;

        avion = avi;

        posX = 2000;
        posY = 0 - 50;

    }

    /**
     * Metodo que funciona para retornar el rectangulo de la imagen
     */
    public Rectangle2D getRectangulo() {

        return new Rectangle2D.Double(posX, posY, 10, 20);

    }

    /**
     * Metodo donde se crea el movmiento del disparo
     */
    public void mover_disparo() {
        if (evento == 1) {
            posX = avion.getPosX() + (avion.getAnchura() / 2);
            posY = posY - 10;
        }
        if (posY < 0) {
            evento = 0;

        }
        if (evento == 0) {
            posX = 2000;
            posY = avion.getPosY() - 10;
        }

    }

    /**
     * Metodo donde se instersecta el disparo con el enemigo y hace que
     * desaparezca y se sume los puntos
     */
    public void disparar() {
        for (int i = 0; i < peje.length; i++) {
            if (getRectangulo().intersects(peje[i].area())) {
                peje[i].setPosY(0 - 180);
                peje[i].posicion();
                n1 = i;
                evento = 0;
                juego.setPuntaje(juego.getPuntaje()+200);
                

            }
            if (getRectangulo().intersects(lagarto.area())) {
                n2 = i;
                lagarto.posicion();
                lagarto.setPosX(1500);
                evento = 0;
                juego.setPuntaje(juego.getPuntaje()+350);
               

            }
            if (getRectangulo().intersects(gas.area())) {

                gas.posicion();
                gas.setPosy(0 - 180);
                evento = 0;
                if (usuario.getPuntuacion() > 150) {
                    juego.setPuntaje(juego.getPuntaje()-150);
                    
                }

            }
        }

    }

    /**
     * Metodo que que desvuelve la imagen del disparo
     */
    public Image getImagen() {
        return imagen;
    }

    /**
     * Devuelve el atributo que permite que el disparo se mueva
     */
    public int getEvento() {
        return evento;
    }

    /**
     * MEtodo que permite cambiar el valor del atributo evento
     */
    public void setEvento(int evento) {
        this.evento = evento;
    }

    /**
     * Metodo que permite obtener el valor de la posicion X del disparo
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Metodo que permite cambiar la posicion X del disparo
     */
    public void setPosX(int posX) {
        this.posX = posX + (avion.getAnchura() / 2);
    }

    /**
     * Metodo que permite obtener la posicion en Y del disparo
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Metodo que permite cambiar la posicion en Y del disparo
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Metodo que permite obtener los putnos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * Metodo que permite cambiar los puntos
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

}
