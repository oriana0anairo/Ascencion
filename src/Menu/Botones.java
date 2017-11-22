package Menu;

import Implementacion.Top_10;
import Implementacion.Usuario;
import Juego.Juego_p;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Clase encargada de los botenes del menu
 */
public final class Botones extends JLabel {

    JButton exit, play, help, credits, top, volver, game, aceptar;
    int ancho, largo, hori, verti, panel, piloto = 0;
    Cargar_Imagen_menu foto;
    Point punto;
    TextField nombre;
    boolean mostrar = true;
    Juego_p jue;
    Menu_p menu;
    Usuario usuario;
    JTextArea creditos, ayuda,top_10;
    Top_10 tops;
    /**
     * Constructor de la clase botones, encargado de inicializar el valor de los atributos
     */
    public Botones(int ancho, int largo, final JPanel panel1, final JPanel panel2, final JFrame vent, Cargar_Imagen_menu icon, Usuario usu,Top_10 tops) throws IOException {
        super();
        usuario = usu;
        this.tops=tops;
        exit = new JButton("salir");
        play = new JButton("jugar");
        help = new JButton("ayuda");
        credits = new JButton("Creditos");
        top = new JButton("top10");
        volver = new JButton("volver");
        game = new JButton("jugar");
        aceptar = new JButton("Aceptar");
        creditos = new JTextArea();
        creditos.setOpaque(false);
        top_10=new JTextArea();
        top_10.setOpaque(false);
        ayuda = new JTextArea();
        ayuda.setOpaque(false);
        nombre = new TextField(8);
        jue = (Juego_p) panel2;
        menu = (Menu_p) panel1;
        foto = icon;
        this.ancho = ancho;
        this.largo = largo;
        Inicializar(vent, panel1, panel2);

    }

    /**
     * Metodo donde se le da la forma a todos los botones y atributos extra
     */
    public void Inicializar(final JFrame vent, final JPanel panel1, final JPanel panel2) {
        panel = 0;
        setLayout(null);
        setBounds(0, 0, ancho, largo);
        exit.setBounds(/*627*/(ancho - 200) / 2, /*696*/ (largo - 200), 200, 200);
        play.setBounds(/*1168*/(ancho - 250), 130, 200, 200);
        help.setBounds(81, 130, 200, 200);
        credits.setBounds((ancho - 250), 526, 200, 200);
        top.setBounds(81, 526, 200, 200);

        configurarBoton(play, foto.getBoton(0), foto.getBoton(1), foto.getBoton(2));
        configurarBoton(exit, foto.getBoton(3), foto.getBoton(4), foto.getBoton(5));
        configurarBoton(help, foto.getBoton(6), foto.getBoton(7), foto.getBoton(8));
        configurarBoton(credits, foto.getBoton(9), foto.getBoton(10), foto.getBoton(11));
        configurarBoton(top, foto.getBoton(12), foto.getBoton(13), foto.getBoton(14));

        add(exit);
        add(play);
        add(help);
        add(credits);
        add(top);

        exit.setFocusable(false);
        play.setFocusable(false);
        help.setFocusable(false);
        credits.setFocusable(false);
        top.setFocusable(false);
        volver.setFocusable(false);
        game.setFocusable(false);

        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        play.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //cuando se unde el boton se llama a la funcion cambiar paneles con sus parametros
                //jugar X: 382 Y: 527
                //volver X: 885 Y: 522
                piloto = 1;
                volver.setBounds((int) (ancho * 0.6), 522, 200, 200);
                game.setBounds((int) (ancho * 0.2), 522, 200, 200);
                configurarBoton(volver, foto.getBoton(15), foto.getBoton(16), foto.getBoton(17));
                configurarBoton(game, foto.getBoton(0), foto.getBoton(1), foto.getBoton(2));
                nombre.setBounds((ancho - (ancho / 2)) / 2, 400, ancho / 2, 60);
                nombre.setBackground(new Color(137, 71, 237));
                nombre.setForeground(Color.WHITE);
                nombre.setFont(new Font("Dialog", Font.BOLD, 36));
                nombre.setEditable(true);

                // nombre.setFocusable(false);
                add(nombre);
                remove(play);
                remove(exit);
                remove(help);
                remove(credits);
                remove(top);
                mostrar = false;
                add(volver);
                add(game);

            }
        });

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                resetear();
                piloto = 0;
            }
        });

        game.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                jue.setNombreusu(nombre.getText());
                cambiar(vent, menu, jue);

            }
        });
        credits.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                piloto = 2;
                creditos.setBounds((ancho / 2) - 450, 250, (900), (400));
                creditos.setForeground(Color.white);
                creditos.setFont(new Font("Dialog", Font.BOLD, 28));
                creditos.setText("Edicion: Miguel Muñoz y Oriana Mendez" + "\n\n" + "Programacion: Miguel Muñoz y Oriana Mendez \n\nCalificacion: Yeniffer Peña \n\nAgradecimientos especiales a Miguel Muñoz y\nOriana Mendez sin ellos este trabajo no habria sido posible\n\n MIRIANASOFT C.A (TODOS LOS DERECHOS RESERVADOS).");
                add(creditos);
                remove(play);
                remove(exit);
                remove(help);
                remove(credits);
                remove(top);
                mostrar = false;
                volver.setBounds((ancho / 2) - 100, largo - 210, 200, 200);
                configurarBoton(volver, foto.getBoton(15), foto.getBoton(16), foto.getBoton(17));
                add(volver);

            }
        });
        top.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                piloto = 2;
                top_10.setBounds((ancho / 2) - 450, 250, (900), (400));
                top_10.setForeground(Color.white);
                top_10.setFont(new Font("Dialog", Font.BOLD, 35));
                top_10.setText(tops.getPosiciones(0)+"\n"+tops.getPosiciones(1)+"\n"+tops.getPosiciones(2)+"\n"+tops.getPosiciones(3)+"\n"+tops.getPosiciones(4)+"\n"+tops.getPosiciones(5)+"\n"+tops.getPosiciones(6)+"\n"+tops.getPosiciones(7)+"\n"+tops.getPosiciones(8)+"\n"+tops.getPosiciones(9));
                add(top_10);
                remove(play);
                remove(exit);
                remove(help);
                remove(credits);
                remove(top);
                volver.setBounds((ancho / 2) - 100, largo - 210, 200, 200);
                configurarBoton(volver, foto.getBoton(15), foto.getBoton(16), foto.getBoton(17));
                add(volver);
            }
        });
        jue.getBoton_aceptar().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                cambiar(vent, jue, menu);
            }
        });
        nombre.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                int max_caracter = 8;
                if (nombre.getText().length() >= max_caracter) {
                    ke.consume();

                }

            }

            @Override
            public void keyPressed(KeyEvent ke) {

            }

            @Override
            public void keyReleased(KeyEvent ke) {

            }
        });
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                piloto = 2;
                ayuda.setBounds(50, 80, ancho - 100, largo - 270);
                ayuda.setForeground(Color.white);
                ayuda.setFont(new Font("Dialog", Font.BOLD, 28));
                ayuda.setText("El juego inicia con una nave que debe esquivar los enemiegos y los agujeros negros que van\napareciendo en pantalla y evitar que se choquen con los muros que estan a los lados. Todo esto\nmientras intentas no quedarte sin gasolina, que se recargan pasando sobre los puntos azules\nque bajan junto con el mapa, los niveles de esta se pueden ver en la esquina inferior izquierda\nde la pantalla.\n\nSi chocas con los enemigos, los muros o los agujeros negros, o te quedas sin gasolina, pierdes\nuna de las tres vidas que te dan al incio del juego.\n\nSi duras 1:30 sin perder todas tus vidas, ganas el juego.\n\nLa nave se mueve con las teclas direccionales del taclado (←Izquierda-Derecha→) y se puede\nacelerar oprimiendo la flecha que señada hacia arriba (Arriba ↑).\n\nPuedes dispararle a tus enemigos y hacerlos desaparecer con la barra de espacio.");
                add(ayuda);
                remove(play);
                remove(exit);
                remove(help);
                remove(credits);
                remove(top);
                mostrar = false;
                volver.setBounds((ancho / 2) - 100, largo - 210, 200, 200);
                configurarBoton(volver, foto.getBoton(15), foto.getBoton(16), foto.getBoton(17));
                add(volver);
            }
        });
        //mover botones
        /*  moverBoton(exit);
         moverBoton(play);
         moverBoton(help);
         moverBoton(credits);
         moverBoton(top);
         System.out.println(250-ancho+" "+ancho);
         moverBoton(game);
         moverBoton(volver);*/

    }

    /**
     * Metodo encargado de resetear los atrbutos
     */
    public void resetear() {
        remove(top_10);
        remove(creditos);
        remove(nombre);
        remove(volver);
        remove(game);
        remove(ayuda);
        nombre.setText("");
        mostrar = true;
        add(exit);
        add(play);
        add(help);
        add(credits);
        add(top);
    }

    /**
     * Metodo que sirve para configurar los botones
     */
    public void configurarBoton(JButton boton, ImageIcon img1, ImageIcon img2, ImageIcon img3) {
        //colco la imagen inicial de boton
        boton.setIcon(new ImageIcon(img1.getImage().getScaledInstance(boton.getHeight(), boton.getHeight(), java.awt.Image.SCALE_DEFAULT)));

        // Para que el boton no tenga borde …
        boton.setBorderPainted(false);

        //Para que no se pinte el boton
        boton.setContentAreaFilled(false);

        boton.setFocusable(false);

        boton.setRolloverEnabled(true);

        //Definimos el icono que se mostrara cuando el mouse este sobre el boton
        boton.setRolloverIcon(new ImageIcon(img2.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_DEFAULT)));

        //Configuramos el icono que se mostrara cuando se de click en el boton
        boton.setPressedIcon(new ImageIcon(img3.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_DEFAULT)));

    }

    /**
     * Metodo que sirve para mover y cambiar la posicion de los botones
     */
    public void moverBoton(final JButton boton) {
        boton.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                hori = e.getX();
                verti = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                System.out.println(boton.getText() + " X: " + boton.getX() + " Y: " + boton.getY());
            }

            @Override
            public void mouseEntered(MouseEvent me) {

            }

            @Override
            public void mouseExited(MouseEvent me) {
            }
        });

        boton.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {

                punto = MouseInfo.getPointerInfo().getLocation();
                boton.setLocation(punto.x - hori, punto.y - verti);

            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }

    /**
     * Metodo que cambia los paneles se le pasa por´parametros, 1.la ventana a
     * utilizar 2.el panel a remover ,3.el panel que se le va a colocar a la ventana
     */
    public void cambiar(JFrame vent, JPanel panel1, JPanel panel2) {
        if (panel1 instanceof Menu_p) {
            jue.reinicios();
            jue.getBoton_aceptar().setVisible(false);
            menu.getTimer().stop();
            jue.getTimer().start();
            jue.getTimer_tiempo().start();
            vent.setVisible(false);
            vent.remove(panel1);
            vent.add(panel2);
            vent.setVisible(true);
        }

        //panel = 1;
        if (panel1 instanceof Juego_p) {
            resetear();
            jue.getReloj().setBounds((ancho / 2) - (150 / 2), 5, 150, 100);
            jue.getReloj().setText("00:00");
            menu.getTimer().start();
            jue.getTimer().stop();
            jue.getTimer_tiempo().stop();
            vent.setVisible(false);
            vent.remove(panel1);
            vent.add(panel2);
            vent.setVisible(true);
            panel = 0;
        }

    }

    /**
     * Metodo usado para pintar en pantalla
     */
    protected void paintComponent(Graphics pintar) {
        super.paintComponents(pintar);
        Graphics2D pin = (Graphics2D) (pintar);
        if (piloto == 0) {
            pin.drawImage(foto.getImages(253), (ancho - (ancho / 2)) / 2, 200, ancho / 2, 130, this);
        }
        if (piloto == 1) {
            pin.drawImage(foto.getImages(254), (ancho - (ancho / 2)) / 2, 150, ancho / 2, 100, this);
        }

        repaint();

    }

}
