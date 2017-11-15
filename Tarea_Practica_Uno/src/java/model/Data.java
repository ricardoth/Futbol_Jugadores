/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ricar
 */
public class Data {

    private Conexion c;
    private String query;
    private ResultSet rs;

    public Data() {
        try {
            c = new Conexion("localhost", "bd_tareaUno", "root", "");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //CRUD
    //Crear
    public void crearJugador(Jugador j) {
        query = "insert into jugador values(null,'" + j.getNombre() + "', "
                + "'" + j.getPais() + "', '" + j.getPosicion() + "', '" + j.getDorsal() + "')";
        try {
            c.ejecutar(query);
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Eliminar
    public void eliminarJugador(int id) {
        query = "delete from jugador where id = " + id;
        try {
            c.ejecutar(query);
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Actualizar
    public void actualizarJugador(Jugador j) {
        query = "update jugador set nombre = '" + j.getNombre() + "', "
                + "posicion = '" + j.getPosicion() + "', "
                + "pais = '" + j.getPais() + "', dorsal = '" + j.getDorsal() + "' where id = '" + j.getId() + "'";
        try {
            c.ejecutar(query);
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Listar todos los jugadores
    public List<Jugador> getTodosLosJugadores() throws SQLException {
        List<Jugador> lista = new ArrayList<>();
        Jugador j;
        query = "SELECT jugador.id, jugador.nombre, pais.nombre AS 'País', posicion.nombre AS 'Posición', jugador.dorsal "
                + "FROM jugador "
                + "INNER JOIN pais ON jugador.pais = pais.id "
                + "INNER JOIN posicion ON jugador.posicion = posicion.id;";
        try {
            rs = c.ejecutarSelect(query);

            while (rs.next()) {
                int id, dorsal;
                String nombre, pais, posicion;

                id = rs.getInt(1);
                nombre = rs.getString(2);
                pais = rs.getString(3);
                posicion = rs.getString(4);
                dorsal = rs.getInt(5);

                j = new Jugador(id, nombre, pais, posicion, dorsal);
                lista.add(j);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.desconectar();
        return lista;
    }

    //buscar por filtro
    public List<Jugador> getJugadores(String filtro) throws SQLException {
        List<Jugador> lista = new ArrayList<>();

        query = "SELECT jugador.id, jugador.nombre, pais.nombre AS 'País', "
                + "posicion.nombre AS 'Posición', jugador.dorsal "
                + "FROM jugador "
                + "INNER JOIN pais ON jugador.pais = pais.id "
                + "INNER JOIN posicion ON jugador.posicion = posicion.id "
                + "WHERE "
                + "jugador.nombre LIKE '%" + filtro + "%' OR "
                + "pais.nombre LIKE '%" + filtro + "%' OR "
                + "posicion.nombre LIKE '%" + filtro + "%' OR "
                + "jugador.dorsal LIKE '%" + filtro + "%';";

        rs = c.ejecutarSelect(query);
        Jugador j;

        while (rs.next()) {
            j = new Jugador();

            j.setId(rs.getInt(1));
            j.setNombre(rs.getString(2));
            j.setPais(rs.getString(3));
            j.setPosicion(rs.getString(4));
            j.setDorsal(rs.getInt(5));

            lista.add(j);

        }

        c.desconectar();

        return lista;
    }

    //Listar todos los paises
    public List<Pais> getPaises() {
        List<Pais> lista = new ArrayList<>();
        Pais p;

        query = "select * from pais";

        try {
            rs = c.ejecutarSelect(query);

            while (rs.next()) {
                p = new Pais();

                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));

                lista.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    // Listar todas las posiciones
    public List<Posicion> getPosiciones() {
        List<Posicion> lista = new ArrayList<>();
        Posicion p;

        query = "select * from posicion";

        try {
            rs = c.ejecutarSelect(query);

            while (rs.next()) {
                p = new Posicion();

                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));

                lista.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
