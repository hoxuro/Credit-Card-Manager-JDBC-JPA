/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestortarjetas;

import funciones.ValidarDatos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.*;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Esta clase permite crear una tarjeta de credito con la cual podremos realizar
 * disitas operaciones.
 *
 * @author Heriberto Amezcua
 * @version 3.0
 * @since JDK 11.0.17
 */
@Entity
@Table(name = "tarjetas", catalog = "gestor_tarjetas", schema = "public")
public class TarjetaCredito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numero", nullable = false, length = 2147483647)
    private String numeroTarjeta;
    @Basic(optional = false)
    @Column(name = "nif", nullable = false, length = 12)
    private String nif;
    @Basic(optional = false)
    @Column(name = "titular", nullable = false, length = 80)
    private String titular;
    @Basic(optional = false)
    @Column(name = "pin", nullable = false, length = 32)
    private String pin;
    @Basic(optional = false)
    @Column(name = "limite", nullable = false)
    private int limite;
    @Basic(optional = false)
    @Column(name = "cvv", nullable = false, length = 3)
    private String cvv;
    @Basic(optional = false)
    @Column(name = "year_caducidad", nullable = false, length = 12)
    private String añoCaducidad;
    @Basic(optional = false)
    @Column(name = "month_caducidad", nullable = false, length = 12)
    private String mesCaducidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tarjetaAsociada", fetch = FetchType.LAZY)
    private ArrayList<Movimiento> movimientos;

    /**
     * Metodo constructor que nos permite crear una "tarjeta de credito" dandole
     * un estado a sus valores si son validos.
     *
     * @param nombre el nombre del titular de la tarjeta de credito
     * @param nif el nif del titular de la tarjeta de credito
     * @param pin el pin de acceso de la tarjeta de credito
     * @param limite el limite que el titular puede gastar ese mes
     * @param numeroTarjeta el numero de esa tarjeta de credito
     * @throws IllegalArgumentException si El nombre del titular es menor de 5 o
     * mayor de 80 caracteres o no esta compuesto de caracteres alfanumericos
     * @throws IllegalArgumentException si el NIF/CIF/NIE no es un documento que
     * sigue un algoritmo que lo valida
     * @throws IllegalArgumentException si el pin es menor de 4 caracteres o
     * tiene algun caracter que no es un digito
     * @throws IllegalArgumentException si el limite de gasto es menor a 500 o
     * mayor a 5000
     * @throws IllegalArgumentException si el numero de la tarjeta de credito
     * introducido no sigue el algoritmo de Luhn.
     */
    public TarjetaCredito(String nombre, String nif, String pin, int limite, String numeroTarjeta) {
        //valido el nombre del titular
        if (!Pattern.matches("[ A-Za-záéíóúÁÉÍÓÚñÑ]{5,80}", nombre)) {
            throw new IllegalArgumentException("El nombre del titular no cumple con los requisitos.");
        }

        if (!ValidarDatos.validarDocumento(nif)) {
            throw new IllegalArgumentException("El NIF/CIF/NIE introducido no es válido.");
        }

        //compruebo si el pin introducido es valido
        if (!Pattern.matches("^\\d{4,}", pin)) {
            throw new IllegalArgumentException("El pin introducido no es válido.");
        }

        //compruebo si el limite se encuentra dentro del rango permitido
        if (limite < 500 || limite > 5000) {
            throw new IllegalArgumentException("El límite de gasto establecido no es válido.");
        }

        //compruebo que el numero de tarjeta sea correcto
        if (!ValidarDatos.comprobarNumeroTarjeta(numeroTarjeta)) {
            throw new IllegalArgumentException("El número de tarjeta introducido no es válido.");
        }

        //una vez validados los datos introducidos, los establezco en la tarjeta
        this.titular = nombre;
        this.nif = nif.toUpperCase();
        this.pin = pin;
        this.limite = limite;
        this.numeroTarjeta = numeroTarjeta;

        Calendar fechaActual = Calendar.getInstance();
        if ((fechaActual.get(Calendar.MONTH) + 1) < 10) {
            this.mesCaducidad = "0" + (fechaActual.get(Calendar.MONTH) + 1);
        } else {
            this.mesCaducidad = "" + (fechaActual.get(Calendar.MONTH) + 1);
        }

        this.añoCaducidad = "" + ((fechaActual.get(Calendar.YEAR) + 5));
        this.cvv = "" + calcRandomInt(100, 999);

        this.movimientos = new ArrayList<>();
    }

    /**
     * Metodo constructor copia que copia el estado de los atributos de una
     * tarjeta de credito en otra
     *
     * @param t la tarjeta de credito a copiar
     * @throws IllegalArgumentException si El nombre del titular es menor de 5 o
     * mayor de 80 caracteres o no esta compuesto de caracteres alfanumericos
     * @throws IllegalArgumentException si el NIF/CIF/NIE no es un documento que
     * sigue un algoritmo que lo valida
     * @throws IllegalArgumentException si el pin es menor de 4 caracteres o
     * tiene algun caracter que no es un digito
     * @throws IllegalArgumentException si el limite de gasto es menor a 500 o
     * mayor a 5000
     * @throws IllegalArgumentException si el numero de la tarjeta de credito
     * introducido no sigue el algoritmo de Luhn.
     */
    public TarjetaCredito(TarjetaCredito t) {
        this(t.titular, t.nif, t.pin, t.limite, t.numeroTarjeta);
        this.cvv = t.getCVV();
        this.mesCaducidad = t.getMesCaducidad();
        this.añoCaducidad = t.getAñoCaducidad();
        this.movimientos = t.movimientos;
    }

    /**
     * Constructor por defecto de una TarjetaCredito creado unicamente porque la
     * unidad de persistencia lo requeria.
     */
    public TarjetaCredito() {
        this("BOB DEFAULT", "z1234567r", "1234", 1000, "1234567890123452");
    }

    //defino metodos getter
    /**
     * Metodo getter que devuelve el nombre del titular de la tarjeta
     *
     * @return el nombre del titular de la tarjeta
     */
    public String getTitular() {
        return this.titular;
    }

    /**
     * Metodo getter que devuelve el nif del titular de la tarjeta
     *
     * @return el nif del titular de la tarjeta
     */
    public String getNif() {
        return this.nif;
    }

    /**
     * Metodo getter que devuelve el pin de la tarjeta
     *
     * @return el pin de la tarjeta
     */
    public String getPin() {
        return this.pin;
    }

    /**
     * Metodo getter que devuelve el limite de gasto de la tarjeta
     *
     * @return el limite de gasto de la tarjeta
     */
    public int getLimite() {
        return this.limite;
    }

    /**
     * Metodo getter que devuelve el mes de caducidad de la tarjeta
     *
     * @return el mes de caducidad de la tarjeta
     */
    public String getMesCaducidad() {
        return this.mesCaducidad;
    }

    /**
     * Metodo getter que devuelve el anio de caducidad de la tarjeta
     *
     * @return el anio de caducidad de la tarjeta
     */
    public String getAñoCaducidad() {
        return this.añoCaducidad;
    }

    /**
     * Metodo getter que devuelve el numero de la tarjeta
     *
     * @return el numero de la tarjeta
     */
    public String getNumeroTarjeta() {
        return this.numeroTarjeta;
    }

    /**
     * Metodo getter que devuelve el cvv de la tarjeta
     *
     * @return el cvv de la tarjeta
     */
    public String getCVV() {
        return this.cvv;
    }

    //defino metodos setter
    /**
     * Metodo setter para establecer el pin de una tarjeta de credito. Antes de
     * hacerlo se valida.
     *
     * @param pin el pin a establecer
     */
    public void setPin(String pin) {
        if (pin.length() >= 4 && Pattern.matches("^\\d+$", pin)) {
            this.pin = pin;
        }
    }

    /**
     * Metodo setter para establecer el limite de una tarjeta de credito. Antes
     * de hacerlo se valida.
     *
     * @param limite el limite a establecer
     */
    public void setLimite(int limite) {
        if (limite >= 500 && limite <= 5000) {
            this.limite = limite;
        }
    }

    /**
     * Metodo que permite realizar pagos con la tarjeta siempre y cuando sea una
     * cantidad valida.
     *
     * @param cantidad la cantidad a pagar
     * @param concepto el concepto a mostrar
     * @param tarjetaAsociada la tarjeta asociada al movimiento
     * @return true si la cantidad a gastar es valida
     */
    public boolean pagar(double cantidad, String concepto, TarjetaCredito tarjetaAsociada) {
        boolean pagoValido = (cantidad > 0 && Pattern.matches(".{0,50}", concepto) && (cantidad + this.gastado()) <= this.limite);

        if (pagoValido) {
            introducirMovimiento(cantidad, concepto, tarjetaAsociada);
        }

        return pagoValido;
    }

    /**
     * Metodo que permite ver la cantidad total gastada por la tarjeta de
     * credito.
     *
     * @return la cantidad total gastada de los ultimos 50 movimientos
     */
    public double gastado() {
        double gastado = 0;

        for (Movimiento movimiento : this.movimientos) {
            if (movimiento != null) {
                gastado += movimiento.getCantidad();
            }
        }

        return gastado;
    }

    /**
     * Metodo que muestra los ultimos movimientos realizados que el usuario pida
     * ver. Si hay menos movimientos de los que pide el usuario solo se
     * mostraran estos.
     *
     * @param numero el numero de ultimos movimientos a mostrar
     * @return string con la informacion de los ultimos, numero, movimientos
     * @throws IllegalArgumentException si el numero de movimientos es menor o
     * igual que 0 o mayor a los movimientos totales de la tarjeta.
     */
    public ArrayList<Movimiento> movimientos(int numero) {
        boolean rangoValido = numero > 0;

        ArrayList<Movimiento> listaMovimientos = new ArrayList<>();
        for (int i = 0; i < this.movimientos.size() && i < numero && rangoValido; i++) {
            listaMovimientos.add(this.movimientos.get(i));
        }

        return listaMovimientos;
    }

    /**
     * Metodo que muestra el numero de movimientos realizados por esa tarjeta de
     * credito.
     *
     * @return el numero de movimientos realizados
     */
    public int numeroMovimientos() {
        int cantMovimientos = 0;

        for (Movimiento movimiento : this.movimientos) {
            if (movimiento != null) {
                cantMovimientos++;
            }
        }

        return cantMovimientos;
    }

    /**
     * Permite ver en formato String un objeto de tipo TarjetaCredito.
     *
     * @return el estado de un objeto TarjetaCredito en formato String
     */
    @Override
    public String toString() {
        return "Titular= " + titular + ", NIF= " + nif + ", Caducidad= " + mesCaducidad + "/" + añoCaducidad + ", Número de Tarjeta= " + numeroTarjeta + ", Límite= " + limite + ", Total gastado= " + this.gastado();
    }

    /**
     * Metodo que compara el numero de dos tarjetas y devuelve true si tienen el
     * mismo numero de tarjeta.
     *
     * @param obj la tarjeta con la que queremos comparar la primera
     * @return true si ambas tarjetas tienen el mismo numero de tarjeta
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
        final TarjetaCredito other = (TarjetaCredito) obj;
        return Objects.equals(this.numeroTarjeta, other.numeroTarjeta);
    }

    /**
     * Metodo meditante el cual podemos introducir un nuevo movimiento en el
     * array de movimientos de nuestra tarjeta de credito. Funciona de forma que
     * los movimientos son desplazados un lugar a la derecha y el ultimo de
     * ellos es desechado, dejando el primer elemento vacio y situando en el al
     * nuevo movimiento.
     *
     * @param cantidad la cantidad gastada por el nuevo movimiento.
     * @param concepto el concepto del nuevo movimiento.
     */
    private void introducirMovimiento(double cantidad, String concepto, TarjetaCredito tarjetaAsociada) {
        //creo el nuevo movimiento a introducir
        this.movimientos.add(new Movimiento(cantidad, concepto, tarjetaAsociada));

    }

    /**
     * Metodo estatico que genera un numero entero aleatorio de 3 cifras en un
     * rango determinado.
     *
     * @param tuNumIni numero a partir del cual se empezara a generar el
     * aleatorio. (incluido)
     * @param tuNumFin numero donde terminara la generacion del aleatorio
     * (incluido)
     * @return un numero entero aleatorio de tres cifras.
     */
    private static int calcRandomInt(int tuNumIni, int tuNumFin) {
        int numIni = tuNumIni;
        int numFin = tuNumFin + 1;
        double random = Math.random() * (numFin - numIni) + numIni;
        return (int) random;
    }

}
