package Juego;

import Habilidades.Disparo;
import Habilidades.Gasolina;
import Implementacion.Top_10;
import Implementacion.Usuario;
import Menu.Botones;
import Menu.Menu_p;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import personajes.Avion;
import personajes.Enemigo1;
import personajes.Enemigo2;

/**
 * Clase Juego_p, donde se aplica la logica del juego, y la implementacionde los
 * objetos para que todo funcione
 */
public class Juego_p extends JPanel {

    private Image fondo, piso, agujeronegro;
    private Avion jei_JEi;
    private final int ancho;
    private final int largo;
    private int Ctamano = 0, tamano = 0, isla = 0, contisla = 0, retornar = 0, puntaje = 0;
    boolean acelelar = false, disparo = false, mantener = false, mostrar = false;
    private double nancho;
    private Random ram;
    private Enemigo1 enemigo1[];
    private Enemigo2 enemigo2;
    Timer timer, timer_tiempo;
    Borde[] borde;
    Disparo[] shootav;
    Tiempo reloj;
    JButton boton_aceptar;
    Menu_p menu;
    JFrame vent;
    JLabel puntos, nombre, tanque;
    Usuario usuario;
    Gasolina gas;
    Top_10 top;
    String nombreusu;
    private int bulet = 0;

    /**
     * Constructor de Juego_p donde se incializan los valor de los atributos de
     * la clase
     */
    public Juego_p(int ancho, int largo, Menu_p menu, Usuario usu, JFrame venta, Top_10 top) throws IOException {
        super();
        this.top = top;
        usuario = usu;
        this.menu = menu;
        puntos = new JLabel("Puntos: ");
        puntos.setBounds(ancho - 400, largo - 100, 400, 100);
        puntos.setForeground(Color.white);
        puntos.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        puntos.setVisible(true);
        nombre = new JLabel();
        nombre.setBounds(40, 5, 400, 100);
        nombre.setForeground(Color.white);
        nombre.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        nombre.setVisible(true);
        tanque = new JLabel();
        tanque.setOpaque(true);
        tanque.setBackground(new Color(79, 255, 240));
        tanque.setVisible(true);
        add(tanque);
        add(puntos);
        add(nombre);
        vent = venta;
        borde = new Borde[11];
        shootav = new Disparo[10];
        enemigo1 = new Enemigo1[4];
        enemigo2 = new Enemigo2(ancho, largo);
        boton_aceptar = new JButton("Aceptar");
        super.add(boton_aceptar);
        ram = new Random();

        for (int i = 0; i < borde.length; i++) {
            borde[i] = new Borde(largo, ancho);

        }
        for (int i = 0; i < 4; i++) {
            enemigo1[i] = new Enemigo1(jei_JEi, borde, ancho, largo);
        }
        gas = new Gasolina(ancho, largo, jei_JEi, borde);
        jei_JEi = new Avion(largo, ancho, enemigo1, enemigo2, this);
        for (int i = 0; i < shootav.length; i++) {
            shootav[i] = new Disparo(jei_JEi, enemigo1, enemigo2, this, usuario, gas);

        }
        jei_JEi.setAnchura(80);
        jei_JEi.setAlto(60);
        this.ancho = ancho;
        this.largo = largo;
        for (int i = 0; i < enemigo1.length; i++) {
            enemigo1[i].posicion();
        }

        timer = new Timer(1, evento);
        timer_tiempo = new Timer(1000, evento_tiempo);
        reloj = new Tiempo(ancho);

        super.add(reloj);
        setLayout(null);
        setBounds(0, 0, ancho, largo);
        setearMapa();

    }

    ActionListener evento_tiempo = new ActionListener() {

        @Override
        /**
         * Metodo donde se realiza todo lo asociado con el tiempo, se valida
         * cuando se gana, que corra y que pare
         */
        public void actionPerformed(ActionEvent e) {

            if (reloj.contar_tiempo()) {
                usuario.setNombre(nombreusu);
                usuario.setPuntuacion(puntaje + (jei_JEi.getVida() * 500));
                try {
                    top.comparar();
                } catch (IOException ex) {
                    Logger.getLogger(Juego_p.class.getName()).log(Level.SEVERE, null, ex);
                }
                top.setEntra(true);
                puntos.setText("Puntos: " + usuario.getPuntuacion());
                puntaje = 0;
                nombreusu="";
                for (int i = 0; i < shootav.length; i++) {
                    shootav[i].setPosX(2000);
                }

                timer.stop();
                timer_tiempo.stop();
                reloj.setBounds(ancho / 2 - 350, largo / 2 - 35, 700, 70);
                reloj.setText("  Felicitaciones, llegaste al final");
                jei_JEi.setVida(3);
                reloj.setMin(0);
                reloj.setSeg(0);
                boton_aceptar.setBounds(ancho / 2 - 50, largo - 250, 100, 50);
                boton_aceptar.setVisible(true);

            }
            if (jei_JEi.getVida() < 0) {
                top.setEntra(false);
                puntos.setText("Puntos: " + usuario.getPuntuacion());
                puntaje = 0;
                nombreusu="";
                for (int i = 0; i < shootav.length; i++) {
                    shootav[i].setPosX(2000);
                }

                timer.stop();
                timer_tiempo.stop();
                reloj.setBounds(ancho / 2 - 350, largo / 2 - 35, 700, 70);
                reloj.setText("                    Perdiste...");
                jei_JEi.setVida(3);
                reloj.setMin(0);
                reloj.setSeg(0);
                boton_aceptar.setBounds(ancho / 2 - 50, largo - 250, 100, 50);
                boton_aceptar.setVisible(true);

            }

        }
    };

    /**
     * Metodo encargado de mandar valores a la clse usuario
     */
    public void llenarUsuario() {
        usuario.setPuntuacion(usuario.getPuntuacion() + puntaje);
    }

    /**
     * Metodo encargado de retornar el valor del atrbuto retornar
     */
    public int getRetornar() {
        return retornar;
    }

    /**
     * Metodo encargado de leer las imagenes usadas para el piso, el fondo y el
     * agujero
     */
    public void leer() {

        try {
            fondo = ImageIO.read(new File("src/imagenes_juego/Fondo.jpg"));
            piso = ImageIO.read(new File("src/imagenes_juego/piso.jpg"));
            agujeronegro = ImageIO.read(new File("src/imagenes_juego/agujero.png"));
        } catch (IOException ex) {
            System.out.println("No existe");
        }
    }

    KeyListener tecla = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent ke) {

        }

        @Override
        /**
         * Metodo encargado de tomar el evento de las teclas, para el disparo y
         * los movimientos
         */
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                shootav[bulet].setEvento(1);
                bulet++;
                if (bulet == 9) {
                    bulet = 0;
                }
            }

            if (ke.getKeyCode() == KeyEvent.VK_UP) {
                acelelar = true;
            }
            if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                jei_JEi.movimiento("Izquierda");
                repaint();
            }
            if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                jei_JEi.movimiento("Derecha");
                repaint();
            }
        }

        @Override
        /**
         * Metodo encagado de tomar el evento de la clases para la aceleracion y
         * el disparo
         */
        public void keyReleased(KeyEvent ke) {
            acelelar = false;
            disparo = false;
        }

    };

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(fondo, 0, 0, super.getWidth(), super.getHeight(), this);
       
        g2.drawImage(gas.getImagen(), gas.getPosx(), gas.getPosy(), gas.getAncho(), gas.getAlto(), this);
        g2.drawImage(jei_JEi.getImagen(), jei_JEi.getPosX(), jei_JEi.getPosY(), jei_JEi.getAnchura(), jei_JEi.getAlto(), this);
        
        

        for (int i = 0; i < borde.length; i++) {
          
            g2.drawImage(piso, borde[i].getPosX(), borde[i].getPosY(), (int) borde[i].getAncho(), borde[i].getLargo() + 20, this);
            g2.drawImage(piso, (int) (ancho - borde[i].getAncho()), borde[i].getPosY(), (int) borde[i].getAncho(), borde[i].getLargo() + 20, this);
        }
       
        g2.drawImage(agujeronegro, borde[isla].getDx(), borde[isla].getDy(), borde[isla].getDan(), borde[isla].getDlar(), this);

        for (int i = 0; i < shootav.length; i++) {
            if (shootav[i].getEvento() == 1) {
             
                g2.drawImage(shootav[i].getImagen(), shootav[i].getPosX(), shootav[i].getPosY(), 10, 20, this);
            }
        }
        for (int i = 0; i < enemigo1.length; i++) {

            g2.drawImage(enemigo1[i].getImagen(), enemigo1[i].getPosX(), enemigo1[i].getPosY(), enemigo1[i].getAncho(), enemigo1[i].getAlto(), this);
           
        }
       
        g2.drawImage(enemigo2.getImagen(), enemigo2.getPosX(), enemigo2.getPosY(), enemigo2.getAncho(), enemigo2.getAlto(), this);
        try {
            g2.drawImage(jei_JEi.getVidas(), ancho - 100, 10, 100, 100, this);
        } catch (IOException ex) {
            Logger.getLogger(Juego_p.class.getName()).log(Level.SEVERE, null, ex);
        }

        repaint();
    }

    /**
     * Metodo encargado de retornar el valor de el atributo teacle
     */
    public KeyListener getTecla() {
        return tecla;
    }

    ActionListener evento = new ActionListener() {

        @Override
        /**
         * Metodo encargado de llamar a todos los metodos necesarios para que
         * funcione el juego
         */
        public void actionPerformed(ActionEvent e) {
            nombre.setText(nombreusu);
            puntos.setText("Puntos: " + puntaje);
            tanque.setBounds(10, largo - 50, (int) jei_JEi.getTanque(), 60);

            for (int i = 0; i < borde.length; i++) {

                reposicion();

            }

            borde[isla].imgisla();
            colisiones();
            tamanoMapa();
            disparar();
            jei_JEi.colicion_con_enemigos();
            posicionEnemiga();
            setearEnemigo();
            llenar_tanque();
            bajarMapa();
            repaint();

        }
    };

    /**
     * Metodo encargado de llenar el tanque de la gasolina
     */
    public void llenar_tanque() {
        if (jei_JEi.getRectangulo().intersects(gas.area())) {
            jei_JEi.setTanque(jei_JEi.getTanque() + 1.5);
        }
    }

    /**
     * Metodo encargado de colocar a los enemigos en la poscion donde deben
     * estar
     */
    public void setearEnemigo() {
        for (int i = 0; i < enemigo1.length; i++) {
            enemigo1[i].bajar();
            enemigo1[i].desplazar();
        }
        enemigo2.bajar();
        enemigo2.desplazar();
        gas.bajar();
    }

    /**
     * Metodo encargado para indicar la posicion del enemigo
     */
    public void posicionEnemiga() {
        for (int i = 0; i < borde.length; i++) {
            if (borde[i].getPosY() < 0) {
                for (int j = 0; j < enemigo1.length; j++) {
                    enemigo1[0].setBsuperior(i);
                }

            }
        }
    }

    /**
     * Metodo encargo de trabajar con los metodos de disparar
     */
    public void disparar() {
        for (int i = 0; i < shootav.length; i++) {
            shootav[i].mover_disparo();
            shootav[i].disparar();
        }
    }

    /**
     * Metodo encargado de colocar el mapa en poscion incial
     */
    public void setearMapa() {
        tamano = numero();
        for (int i = 0; i < borde.length; i++) {
            if (i < borde.length - 1) {
                borde[i + 1].setPosY(borde[i].getPosY() - (largo / 10));

            }
            borde[i].setAncho(borde[i].getAnchos(tamano));

        }
    }

    /**
     * Metodo encargado de mover el mapa
     */
    public void bajarMapa() {
        for (int i = 0; i < enemigo1.length; i++) {
            if (acelelar == false) {
                enemigo1[i].setVelocidad(1);
            }
            if (acelelar == true) {
                enemigo1[i].setVelocidad(5);
            }
        }
        for (int i = 0; i < borde.length; i++) {
            if (acelelar == false) {
                borde[i].setPosY(borde[i].getPosY() + 1);

            }
            if (acelelar == true) {
                borde[i].setPosY(borde[i].getPosY() + 5);

            }
        }
        if (acelelar == false) {
            gas.setVelocidad(1);
            enemigo2.setVelocidad(1);
            jei_JEi.setTanque(jei_JEi.getTanque() - 0.2);
            if (contisla > 1) {
                borde[isla].setPosy_isla(borde[isla].getPosy_isla() + 1);
                mostrar = true;
                for (int i = 0; i < enemigo1.length; i++) {
                    enemigo1[i].setIsla(1);
                    gas.setIsla(1);
                }
            }
        }
        if (acelelar == true) {
            gas.setVelocidad(5);
            enemigo2.setVelocidad(5);
            jei_JEi.setTanque(jei_JEi.getTanque() - 0.4);
            if (contisla > 1) {
                borde[isla].setPosy_isla(borde[isla].getPosy_isla() + 5);
                mostrar = true;
                for (int i = 0; i < enemigo1.length; i++) {
                    enemigo1[i].setIsla(1);
                    gas.setIsla(1);
                }
            }

        }

    }

    /**
     * Metodo encargado de retornar el alor de la variable puntaje
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * Metodo encargado de cambiar el valor de la variable puntaje
     */
    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    /**
     * Metodo encargado de darle al mapa el acnho con el que va salir
     */
    public void tamanoMapa() {
        if (Ctamano == 8) {
            tamano = numero();
        }

    }

    /**
     * Metodo encargado de retornar el ancho del mapa
     */
    public int numero() {
        return (int) (Math.random() * 3) + 0;
    }

    /**
     * Metodo encargo de colocar todo en posicion incail
     */
    public void reinicios() {
        setearMapa();
        contisla = 0;
        borde[isla].setPosy_isla(0 - ((largo / 10) * 9));
        jei_JEi.setPosX(((ancho) / 2) - (jei_JEi.getAnchura() / 2));
        enemigo2.posicion();
        enemigo2.setPosX(1500);
        gas.posicion();
        gas.setPosy(0-180);
        for (int j = 0; j < enemigo1.length; j++) {
            enemigo1[j].setPosY(0 - 180);
            enemigo1[j].posicion();
        }

    }

    /**
     * Metodo encargado de la coliciones en el mapa
     */
    public void colisiones() {
        for (int i = 0; i < borde.length; i++) {
            if ((jei_JEi.getRectangulo().intersects(borde[i].getRectangulo()) || jei_JEi.getRectangulo().intersects(borde[i].getRectanguloopuesto()) || jei_JEi.getRectangulo().intersects(borde[i].getRectanguloisla()))) {
                reinicios();
                jei_JEi.setVida(jei_JEi.getVida() - 1);
            }
        }
    }

    /**
     * Metodo encargado de reposicionar todo en el mapa
     */
    public void reposicion() {
        for (int j = 0; j < borde.length; j++) {
            if (borde[j].getRectangulo().getY() >= ((borde[j].getLargo() * 10) + (borde[j].getLargo() / 10))) {
                borde[j].setPosY(0 - borde[j].getLargo());
                Ctamano++;

                if (Ctamano >= 12) {

                    if (borde[j].getRectangulo().getWidth() < borde[j].getAnchos(tamano)) {

                        if (j == 0) {
                            borde[j].setAncho(borde[j].getAncho() + borde[j].getTamanopantalla());

                        } else {
                            borde[j].setAncho(borde[j - 1].getAncho() + borde[j].getTamanopantalla());

                        }
                        //System.out.println("ejecuta el a " + borde[j].getAncho() + " " + borde[j].getAnchos(tamano));
                    } else if (borde[j].getRectangulo().getWidth() > borde[j].getAnchos(tamano)) {

                        if (j == 0) {
                            borde[j].setAncho(borde[j].getAncho() - borde[j].getTamanopantalla());

                        } else {
                            borde[j].setAncho(borde[j - 1].getAncho() - borde[j].getTamanopantalla());

                        }
                        //System.out.println("ejecuta el b " + borde[j].getAncho() + " " + borde[j].getAnchos(tamano));

                    }
                    if (borde[j].getRectangulo().getWidth() == borde[j].getAnchos(tamano)) {
                        Ctamano = 0;
                        nancho = borde[j].getAncho();
                        mantener = true;

                        //  System.out.println("ejecuta");
                    }
                }
                if (Ctamano < 12 && mantener == true) {
                    borde[j].setAncho(nancho);

                }
                if (borde[j].getAncho() == borde[j].getAnchos(0)) {
                    if (contisla == 0) {
                        isla = j;

                    }
                    contisla++;

                }
                if (contisla > 22) {

                }
            }
            if (borde[isla].getRectanguloisla().getY() >= ((borde[isla].getLargo() * 10) + (borde[isla].getLargo() / 10))) {
                contisla = 0;
                for (int i = 0; i < enemigo1.length; i++) {
                    enemigo1[i].setIsla(0);
                }

                borde[isla].setPosy_isla(0 - ((largo / 10) * 9));
            }

        }

    }

    public String getNombreusu() {
        return nombreusu;
    }

    public void setNombreusu(String nombreusu) {
        this.nombreusu = nombreusu;
    }

    /**
     * Mrtodo encargado de retornar el valor de atributo tiempo
     */
    public Tiempo getReloj() {
        return reloj;
    }

    /**
     * Metodo encargo de retornar el valor de atributo boton aceptar
     */
    public JButton getBoton_aceptar() {
        return boton_aceptar;
    }

    /**
     * Metodo encargado de reatornar valor de atributo timer_tiempo
     */
    public Timer getTimer_tiempo() {
        return timer_tiempo;
    }

    /**
     * Metoo encargado de retornar el valor del atributo timer
     */
    public Timer getTimer() {
        return timer;
    }

}
