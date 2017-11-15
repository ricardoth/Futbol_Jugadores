package model;

/**
 *
 * @author ricar
 */
public class Jugador {

    private int id;
    private String nombre;
    private String pais;
    //private int pais;
    private String posicion;
    //private int posicion;
    private int dorsal;

    public Jugador() {
    }

    public Jugador(int id, String nombre, String pais, String posicion, int dorsal) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.posicion = posicion;
        this.dorsal = dorsal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

}
