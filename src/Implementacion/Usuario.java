package Implementacion;

/**
 * Clase usuarioencarga de guardar la informacion del usuario
 */
public class Usuario {

    private String nombre;
    private int puntuacion;

    /**
     * Constuructor de usuario, encargado de inicailiazar loa atributos
     */
    public Usuario() {
    }

    /**
     * Metodo encargado de devuelver el valor de nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo encargado de cambiar el valor del atrbuto nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo encargado de devolver el valor del atrbuto puntuacion
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Metodo encargado de cambiar el valor del atrbuto puntuacion
     */
    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

}
