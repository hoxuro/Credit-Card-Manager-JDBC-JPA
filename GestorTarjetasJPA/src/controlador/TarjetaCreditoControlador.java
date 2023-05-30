/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import conexion.Conexion;
import gestortarjetas.TarjetaCredito;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Esta clase hace de intermediario entre la base de datos y nuestro programa
 * permitiendonos establecer una conexion y la capacidad de obtener y guardar
 * datos de objetos TarjetaCredito en nuestra base de datos.
 *
 * @author Heriberto Amezcua
 * @version 3.0
 * @since JDK 11.0.17
 */
public class TarjetaCreditoControlador {

    /**
     * Permite insertar el estado de un objeto TarjetaCredito en una base de
     * datos.
     *
     * @param t la TarjetaCredito que queremos almacenar en la base de datos
     */
    public void create(TarjetaCredito t) {
        EntityManager en = entityManager();
        try {
            en.getTransaction().begin(); //INICIAMOS LA TRANSACCION
            en.persist(t);
            en.getTransaction().commit(); //INSERTA LA TRANSACION

        } catch (Exception e) {
            en.getTransaction().rollback();
        }
    }

    /**
     * Permite actualizar el estado de un objeto TarjetaCredito previamente
     * almacenado en una base de datos.
     *
     * @param t la TarjetaCredito cuyo estado queremos modificar en la base de
     * datos
     */
    public void update(TarjetaCredito t) {
        EntityManager en = entityManager();
        try {
            en.getTransaction().begin(); //INICIAMOS LA TRANSACCION
            en.merge(t);
            en.getTransaction().commit(); //INSERTA LA TRANSACION

        } catch (Exception e) {
            en.getTransaction().rollback();
        }
    }

    /**
     * Permite eliminar el registro de un objeto TarjetaCredito de nuestra base
     * de datos.
     *
     * @param t la TarjetaCredito que queremos eliminar de nuestra base de datos
     */
    public void delete(TarjetaCredito t) {
        EntityManager en = entityManager();
        try {
            en.getTransaction().begin(); //INICIAMOS LA TRANSACCION
            en.remove(en.merge(t));
            en.getTransaction().commit(); //INSERTA LA TRANSACION

        } catch (Exception e) {
            en.getTransaction().rollback();

        }
    }

    /**
     * Nos permite obtener en forma de ArrayList todas las tarjetas almacenadas
     * en nuestra base de datos.
     *
     * @return un ArrayList con todas las tarjetas almacenadas en la base de
     * datos
     */
    public ArrayList<TarjetaCredito> read() {
        Query q = entityManager().createQuery("SELECT t FROM TarjetaCredito t");
        return new ArrayList(q.getResultList());
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
