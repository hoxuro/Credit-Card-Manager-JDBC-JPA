/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestortarjetas;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Clase que permite crear una transaccion bancaria.
 *
 * @author Heriberto Amezcua
 * @version 3.0
 * @since JDK 11.0.17
 */
public class Movimiento implements Serializable {

    private double cantidad;
    private String concepto;
    private Date fecha;

    /**
     * Metodo constructor donde El usuario introduce la cantidad a transferir y
     * el concepto a mostrar y si ambos parametros son validos se instanciará un
     * objeto con esos valores para sus atributos.
     *
     * @param cantidad la cantidad de dinero a transferir.
     * @param concepto el mensaje a mostrar para el receptor.
     * @throws IllegalArgumentException si la cantidad a transferir es negativa
     * o nula.
     * @throws IllegalArgumentException si el concepto es demasiado largo.
     */
    public Movimiento(double cantidad, String concepto) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad de la transferencia no puede ser negativa o nula.");
        }
        if (!Pattern.matches(".{0,50}", concepto)) {
            throw new IllegalArgumentException("El concepto es demasiado largo.");
        }
        Date fecha = new Date();
        String format = "E MMM dd HH:mm:ss z yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(format, new Locale("en_En"));
        String formattedDate = sdf.format(fecha);

        this.cantidad = cantidad;
        this.concepto = concepto;
        this.fecha = fecha;
    }

    /**
     * Contructor pensado para crear movimientos de datos obtenidos de una base
     * de datos.
     *
     * @param cantidad la cantidad de dinero a transferir.
     * @param concepto el mensaje a mostrar para el receptor.
     * @param fecha la fecha en que se realizó la transaccion.
     * @throws IllegalArgumentException si la cantidad a transferir es negativa
     * o nula.
     * @throws IllegalArgumentException si el concepto es demasiado largo.
     */
    public Movimiento(double cantidad, String concepto, Date fecha) {
        this(cantidad, concepto);
        this.fecha = fecha;
    }

    /**
     * Metodo constructor copia que instancia una clase movimiento con los
     * mismos atributos que la introducida por parametro.
     *
     * @param m objeto de la clase Movimiento.
     */
    public Movimiento(Movimiento m) {
        this(m.cantidad, m.concepto);
        this.fecha = new Date(m.fecha.getTime());
    }

    /**
     * Metodo getter que te devuelve la cantidad a transferir de ese movimiento.
     *
     * @return la cantidad de dinero a transferir de ese movimiento
     */
    public double getCantidad() {
        return this.cantidad;
    }

    /**
     * Metodo getter que te devuelve el concepto de ese movimiento.
     *
     * @return el concepto de ese movimiento
     */
    public String getConcepto() {
        return this.concepto;
    }

    /**
     * Metodo getter que te devuelve la fecha de transferencia de ese
     * movimiento.
     *
     * @return la fecha de transferencia del movimiento.
     */
    public Date getFecha() {
        return new Date(this.fecha.getTime());
    }

    /**
     * Metodo setter que valida si el concepto es valido antes de establecerselo
     * al ese movimiento.
     *
     * @param concepto el concepto a mostrar de ese movimiento
     * @throws IllegalArgumentException si el concepto es mayor a 50 caracteres
     */
    public void setConcepto(String concepto) {
        if (!Pattern.matches(".{0,50}", concepto)) {
            throw new IllegalArgumentException("El concepto no puede tener mas de 50 caractres.");
        }
        this.concepto = concepto;
    }

    /**
     * Permite visualizar en formato string el estado de un objeto movimiento.
     *
     * @return el estado de un objeto movimiento en formato string.
     */
    @Override
    public String toString() {
        return "Cantidad tranferida= " + cantidad + " €, Concepto= " + concepto + ", Fecha de transferencia= " + fecha;
    }

    /**
     * Permite comprobar si dos movimientos son iguales.
     *
     * @param obj el objeto movimiento con el que comparamos nuestro primer
     * objeto
     * @return true si ambos movimientos coinciden en cantidad, concepto y fecha
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movimiento other = (Movimiento) obj;
        if (Double.doubleToLongBits(this.cantidad) != Double.doubleToLongBits(other.cantidad)) {
            return false;
        }
        if (!Objects.equals(this.concepto, other.concepto)) {
            return false;
        }
        return Objects.equals(this.fecha, other.fecha);
    }

}
