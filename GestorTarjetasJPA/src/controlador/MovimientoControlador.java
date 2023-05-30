/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import conexion.Conexion;
import gestortarjetas.Movimiento;
import javax.persistence.EntityManager;

/**
 * Esta clase hace de intermediario entre la base de datos y nuestro programa
 * permitiendonos establecer una conexion y la capacidad de obtener y guardar
 * datos de objetos Movimiento en nuestra base de datos.
 *
 * @author Heriberto Amezcua
 * @version 3.0
 * @since JDK 11.0.17
 */
public class MovimientoControlador {

    /**
     * Permite insertar el estado de un objeto Movimiento en una base de datos.
     *
     * @param m el movimiento que queremos almacenar en la base de datos
     */
    public void create(Movimiento m) {
        EntityManager en = entityManager();
        try {
            en.getTransaction().begin(); //INICIAMOS LA TRANSACCION
            en.persist(m);
            en.getTransaction().commit(); //INSERTA LA TRANSACION

        } catch (Exception e) {
            en.getTransaction().rollback();
        }
    }

    /**
     * Nos permite obtener una instancia de EntityManager conectada a nuestra
     * base de datos.
     *
     * @return una instancia de EntityManager conectada a nuestra base de datos.
     */
    private EntityManager entityManager() {
        return Conexion.getInstancia().getFabrica().createEntityManager();
    }
}
