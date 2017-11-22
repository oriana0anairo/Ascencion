package Juego;

import java.awt.Image;
import java.awt.geom.Rectangle2D;

/**
 * Clase Borde, encargada de los bordes del juego y del agujero negro
 */
public final class Borde {

    private double[] anchos;
    private double ancho, ancho_isla;
    private int largo;
    private Image imagen;
    private int posX;
    private int posY, posy_isla;
    private double tamanopantalla;
    int dx, dy, dan, dlar;

    /**
     * Constructor de Borde, encargado de inicializar los atributos
     */
    public Borde(int largo, int ancho) {
        this.largo = largo / 10;
        this.anchos = new double[3];
        this.anchos[0] = ancho * 0.1;
        this.anchos[1] = ancho * 0.2;
        this.anchos[2] = ancho * 0.3;
        ancho_isla = ancho * 0.4;
        tamanopantalla = ancho * 0.02;
        posY = 0 - (this.largo);
        posX = 0;
        posy_isla = 0 - (this.largo * 9);

    }

    /**
     * Metodo encargado de crear el rectangulo del primer borde de los bordes y
     * del agujero negro, y darle tamaño
     */
    public Rectangle2D getRectangulo() {

        return new Rectangle2D.Double(0, posY, ancho, largo);

    }

    /**
     * Metodo encargado de crear el rectangolo del lado 2 y darle tamaño y posicion
     */
    public Rectangle2D getRectanguloopuesto() {

        return new Rectangle2D.Double((tamanopantalla / 0.02) - ancho, posY, ancho, largo);

    }

    /**
     * Metodo encargado de crear el rectangulo de la isla y darle tamaño y posicion
     */
    public Rectangle2D getRectanguloisla() {

        return new Rectangle2D.Double(((tamanopantalla / 0.02) / 2) - (ancho_isla / 2), posy_isla, ancho_isla, largo * 8);

    }

    /**
     * Metodo encargado de devolver el valor del atributo ancho
     */
    public double getAncho() {
        return ancho;
    }

    /**
     * Metodo encargado de devolver el valor del atributo ancho_isla
     */
    public double getAncho_isla() {
        return ancho_isla;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo ancho_isla
     */
    public void setAncho_isla(double ancho_isla) {
        this.ancho_isla = ancho_isla;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo ancho
     */
    public void setAncho(double ancho) {
        this.ancho = Math.round(ancho * 100) / 100d;
    }

    /**
     * Metodo encargado de devolver el valor del atributo largo
     */
    public int getLargo() {
        return largo;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo largo
     */
    public void setLargo(int largo) {
        this.largo = largo;
    }

    /**
     * Metodo encargado de devolver el valor del atributo aposX
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
     * Metodo encargado de devolver el valor del atributo posY
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
     * Metodo encargado de devolver el valor del atributo anchos
     */
    public double getAnchos(int pos) {
        return anchos[pos];
    }

    /**
     * Metodo encargado de devolver el valor del atributo posy_isla
     */
    public int getPosy_isla() {
        return posy_isla;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo Posy_isla
     */
    public void setPosy_isla(int posy_isla) {
        this.posy_isla = posy_isla;
    }

    /**
     * Metodo encargado de obetern el ancho del borde
     */
    public void tamano(int pos) {
        if (getRectangulo().getWidth() > anchos[pos]) {
            ancho = ancho - tamanopantalla;

        } else if (getRectangulo().getWidth() < anchos[pos]) {
            ancho = ancho + tamanopantalla;

        }
    }

    /**
     * Le tamaño y posicion a la imagen de la isla
     */
    public void imgisla() {
        dx = (int) getRectanguloisla().getX();
        dy = (int) getRectanguloisla().getY();
        dan = (int) getRectanguloisla().getWidth();
        dlar = (int) getRectanguloisla().getHeight();
    }

    /**
     * Metodo encargado de devolver el valor del atributo tamanopantalla
     */
    public double getTamanopantalla() {
        return tamanopantalla;
    }

    /**
     * Metodo encargado de devolver el valor del atributo Dx
     */
    public int getDx() {
        return dx;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo Dx
     */
    public void setDx(int dx) {
        this.dx = dx;
    }

    /**
     * Metodo encargado de devolver el valor del atributo Dy
     */
    public int getDy() {
        return dy;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo Dy
     */
    public void setDy(int dy) {
        this.dy = dy;
    }

    /**
     * Metodo encargado de devolver el valor del atributo Dan
     */
    public int getDan() {
        return dan;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo Dan
     */
    public void setDan(int dan) {
        this.dan = dan;
    }

    /**
     * Metodo encargado de devolver el valor del atributo Dlar
     */
    public int getDlar() {
        return dlar;
    }

    /**
     * Metodo encargado de cambiar el valor del atributo Dlar
     */
    public void setDlar(int dlar) {
        this.dlar = dlar;
    }

}
