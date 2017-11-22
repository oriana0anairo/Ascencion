package Menu;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Clase encargada de cargar las imagenes de fondo del menu
 */
public class Cargar_Imagen_menu implements Datos.Imagenes {

    private Cargar_Imagen_menu instance = null;

    private static final int imagen_menu = img_menu.length;
    private static final int imagen_boton = img_boton.length;

    private Image images[], poses[];
    private ImageIcon boton[];

    /**
     * Constructor de cargar_imagenes, donde se lee la iameges y se guardan en
     * los atributos
     */
    public Cargar_Imagen_menu() throws IOException {
        images = new Image[imagen_menu];
        for (int i = 0; i < imagen_menu; i++) {
            String url;
            if (i < 253) {
                url = ("src/Imagenes_menu/" + img_menu[i] + ".JPG");
            } else {
                url = ("src/Imagenes_menu/" + img_menu[i] + ".PNG");
            }
            images[i] = ImageIO.read(new File(url));
            // System.out.println(img_menu[i]+" "+i);
        }
        boton = new ImageIcon[imagen_boton];
        for (int i = 0; i < imagen_boton; i++) {
            String url = ("src/Imagenes_menu/" + img_boton[i] + ".PNG");

            //boton[i] = ImageIO.read(new File(url));
            boton[i] = new ImageIcon(url);

            //  System.out.println(url);
        }
    }

    /**
     * Metodo encargado de devolver el valor del atributo instance
     */
    public Cargar_Imagen_menu getInstance() throws IOException {
        if (instance == null) {
            instance = new Cargar_Imagen_menu();
        }
        return instance;
    }

    /**
     * Metodo encargado de devolver la imegen del vector en la posicion que le
     * pasen por parametro
     */
    public Image getImages(int imagenId) {
        if (imagenId < 0 || imagenId > imagen_menu) {
            return null;
        }

        return images[imagenId];
    }

    /**
     * Metodo encargado devolver el boton del vector en la posicion que se le
     * pase por parametros
     */
    public ImageIcon getBoton(int imagenId) {
        if (imagenId < 0 || imagenId > imagen_boton) {
            return null;
        }

        return boton[imagenId];
    }
}
