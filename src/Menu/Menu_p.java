package Menu;

import Datos.Pantalla;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

/**
 * Clase encargada del Menu del juego
 */
public final class Menu_p extends JPanel {

    Pantalla pixel;
    Timer timer;
    ImageIcon imagen;
    Cargar_Imagen_menu fondo;
    int nfondo = 0, accion;

    /**
     * Constructor del menu_p encargado de inicializar la variables
     */
    public Menu_p(Cargar_Imagen_menu fondo) throws IOException {
        super();
        pixel = new Pantalla();
        timer = new Timer(20, evento);
        this.fondo = fondo;
        pixel = new Pantalla();
        imagen = new ImageIcon();
        Inicializar();

    }

    /**
     * Encargado de darle posicion y inciar el timer
     */
    public void Inicializar() {
        setLayout(null);
        setBounds(0, 0, pixel.getAncho(), pixel.getLargo());
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics pintar) {
        super.paintComponents(pintar);
        Graphics2D pin = (Graphics2D) (pintar);
        pin.drawImage(fondo.getImages(nfondo), 0, 0, this.getWidth(), this.getHeight(), this);

        repaint();

    }

    ActionListener evento = new ActionListener() {

        @Override
        /**
         * Para correr el fondo del menu
         */
        public void actionPerformed(ActionEvent e) {
            nfondo++;
            if (nfondo == 252) {
                nfondo = 0;
            }

        }
    };

    /**
     * Metodo encargado de devolver el valor del atributo timer
     */
    public Timer getTimer() {
        return timer;
    }

}
