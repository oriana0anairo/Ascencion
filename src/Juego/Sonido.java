package Juego;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Clase encargada del sonido
 */
public class Sonido {

    /**
     * Metodo donde se inicializa el sonido
     */
    public Sonido() throws IOException {
        String audio = "src/sonido/audio.wav";
        InputStream in = null;
        try {
            in = new FileInputStream(audio);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sonido.class.getName()).log(Level.SEVERE, null, ex);
        }
        AudioStream au = new AudioStream(in);
        AudioPlayer.player.start(au);
    }

}
