package Implementacion;

import Datos.Pantalla;
import Menu.Menu_p;
import java.io.IOException;
import javax.swing.*;
import Juego.Juego_p;
import Juego.Sonido;
import Menu.Botones;
import Menu.Cargar_Imagen_menu;
import Menu.Menu_p;
import Menu.Cargar_Imagen_menu;

/**
 * Clase ventana, que se encarga de montar todo en la pantalla
 */
public class Ventana extends JFrame {

    Menu_p menu;
    Juego_p juego;
    Pantalla pixel;
    Botones boton;
    Cargar_Imagen_menu fondo;
    Sonido sonido;
    Usuario usuario;
    Top_10 top;

    /**
     * Constructor de ventana, encargado de inicializar los atributos, y mandar
     * por parametro objetos de las clases ya instanciadas a otra clases
     */
    public Ventana() throws IOException {
        super();
        super.setFocusable(true);
        
        
        usuario = new Usuario();
        top=new Top_10(usuario);
        fondo = new Cargar_Imagen_menu();
        menu = new Menu_p(fondo);
        pixel = new Pantalla();
        juego = new Juego_p(pixel.getAncho(), pixel.getLargo(), menu, usuario, this,top);
        boton = new Botones(pixel.getAncho(), pixel.getLargo(), menu, juego, this, fondo, usuario,top);
        juego.leer();
        sonido = new Sonido();
        super.addKeyListener(juego.getTecla());
        /*le paso por parametros al objeto: 
        1.el panel a  remover 2.el panel que se va a colocar y 
        3. la ventana a la cual se le va a hacer todo(ir a la clase botones para mas informacion...) */

        setLayout(null);
        menu.add(boton);
        add(menu);
        setUndecorated(true);
        setVisible(true);
        setBounds(0, 0, pixel.getAncho(), pixel.getLargo());
        setLocationRelativeTo(null);

    }

}
