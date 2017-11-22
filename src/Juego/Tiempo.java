package Juego;

import com.sun.org.apache.regexp.internal.REUtil;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 * Clase encargada de el tiempo del jeugo
 */
public class Tiempo extends JLabel {

    private int min = 0, seg = 0;

    /**
     * Constructor del tiempo dondes se inicializan los valores
     */
    public Tiempo(int ancho) {
        super("00:00");
        super.setBounds((ancho / 2) - (150 / 2), 5, 150, 100);
        super.setForeground(Color.white);
        super.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        super.setVisible(true);
    }

    /**
     * Metodo de retornar cuando el tiempo se haya acabado, de contarlo y de
     * mostrarlo en pantalla
     */
    public boolean contar_tiempo() {
        seg++;
        if (seg == 60) {
            min++;
            seg = 0;
        }
        if (seg < 10) {
            super.setText(min + ":0" + seg);
        }
        if (seg >= 10) {
            super.setText(min + ":" + seg);
        }

        if (min == 1 && seg == 30) {
            return true;
        }
        return false;

    }

    /**
     * Metodo encargado de devolver el valor de el atrbuto min
     */
    public int getMin() {
        return min;
    }

    /**
     * Metodo encargado de cambiar el valor del atrbuto min
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Metodo encargado de devolver el valor de atrbuto seg
     */
    public int getSeg() {
        return seg;
    }

    /**
     * Metodo encargado de Cambiar el valor del atributo seg
     */
    public void setSeg(int seg) {
        this.seg = seg;
    }

}
