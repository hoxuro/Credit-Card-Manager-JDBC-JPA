/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Esta clase nos permite crear una conexion con la base de datos de forma
 * generica.
 *
 * @author Heriberto Amezcua
 * @version 3.0
 * @since JDK 11.0.17
 */
public class Conexion {

    private static Conexion con = new Conexion();

    private EntityManagerFactory entManFactory;

    /**
     * Constructor privado que nos permite establecer conexion con la base de
     * datos en que se encuentran nuestras tarjetas de credito y sus movimientos
     * de forma generica a traves del nombre de la unidad de persistencia.
     */
    private Conexion() {
        entManFactory = Persistence.createEntityManagerFactory("GestorTarjetasJPAPU");
    }

    /**
     * Permite obtener una instancia del conector con la base de datos.
     *
     * @return una instancia del conector con la base de datos
     */
    public static Conexion getInstancia() {
        return con;
    }

    /**
     *
     * @return un objeto de la clase EntityManagerFactory asociado con la unidad
     * de persistencia con nuestra base de datos
     */
    public EntityManagerFactory getFabrica() {
        return entManFactory;
    }
}
