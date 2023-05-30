/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gestortarjetas;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase con diferentes métodos que nos permiten interactuar con una base de
 * datos PostgreSQL.
 *
 * @author Heriberto Amezcua
 * @version 3.0
 * @since JDK 19
 */
public class GestorTarjetasDao {

    //URL de conexión
    static final String URL = "jdbc:postgresql://localhost:5432/gestor_tarjetas?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
    //Usuario para conectar a la base de datos.
    static final String USUARIO = "postgres";
    //Contraseña para conectar a la base de datos.
    static final String PASSWORD = "1234";

    /**
     * Permite guardar el estado de un objeto TarjetaCredito en una base de
     * datos .
     *
     * @param t el objeto TarjetaCredito que queremos insertar en la base de
     * datos
     */
    public void insert(TarjetaCredito t) {
        try {
            //ABRIMOS LA CONEXION CON LA BASE DE DATOS
            Connection con = DriverManager.getConnection(URL, USUARIO, PASSWORD);

            //Realizamos la consulta deseada
            String query = """
                           INSERT INTO tarjetas (numero, nif, titular, pin, limite, cvv, year_caducidad, month_caducidad) VALUES (?,?,?,?,?,?,?,?)""";

            //Como es una consulta compleja hacemos uso de PreparedStatement
            PreparedStatement consulta = con.prepareStatement(query);

            //Establecemos los valores de cada uno de los ?
            consulta.setString(1, t.getNumeroTarjeta());
            consulta.setString(2, t.getNif());
            consulta.setString(3, t.getTitular());
            consulta.setString(4, t.getPin());
            consulta.setInt(5, t.getLimite());
            consulta.setString(6, t.getCVV());
            consulta.setString(7, t.getAñoCaducidad());
            consulta.setString(8, t.getMesCaducidad());

            consulta.executeUpdate();

            //Finalmente cerramos las conexiones con la consulta y la base de datos
            if (consulta != null) {
                consulta.close();
            }
            if (con != null) {
                con.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(GestorTarjetasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Permite actualizar el limite y pin de un objeto TarjetaCredito en una
     * base de datos.
     *
     * @param t el objeto TarjetaCredito cuyo pin y limite queremos actualizar
     * en la base de datos
     */
    public void update(TarjetaCredito t) {
        try {
            Connection con = DriverManager.getConnection(URL, USUARIO, PASSWORD);

            String query = "UPDATE tarjetas SET pin = ?, limite = ? WHERE numero LIKE ?";
            PreparedStatement consulta = con.prepareStatement(query);

            consulta.setString(1, t.getPin());
            consulta.setInt(2, t.getLimite());
            consulta.setString(3, t.getNumeroTarjeta());

            consulta.executeUpdate();

            if (consulta != null) {
                consulta.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.err.printf("Se ha producido un error al ejecutar la consulta SQL.");
        }
    }

    /**
     * Permite eliminar un registro de una TarjetaCredito en una base de datos.
     *
     * @param numero el numero de tarjeta del objeto TarjetaCredito que queremos
     * eliminar de nuestra base de datos
     */
    public void delete(String numero) {
        try {
            Connection con = DriverManager.getConnection(URL, USUARIO, PASSWORD);

            String query = "DELETE FROM tarjetas WHERE numero LIKE ?";
            PreparedStatement consulta = con.prepareStatement(query);

            consulta.setString(1, numero);

            consulta.executeUpdate();

            if (consulta != null) {
                consulta.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.err.printf("Se ha producido un error al ejecutar la consulta SQL.");
        }
    }

    /**
     * Permite obtener todas las tarjetas de credito almacenadas en la base de
     * datos hasta el momento.
     *
     * @return un arraylist con todas las tarjetas de credito de la base de
     * datos.
     */
    public ArrayList<TarjetaCredito> obtenerTarjetas() {
        ArrayList<TarjetaCredito> tarjetasBD = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(URL, USUARIO, PASSWORD);

            //como es una consulta simple no hace falta un preparedStatement
            Statement consulta = con.createStatement();
            ResultSet resultados = consulta.executeQuery("SELECT * FROM tarjetas");

            while (resultados.next()) {
                tarjetasBD.add(new TarjetaCredito(resultados.getString("titular"), resultados.getString("nif"), resultados.getString("pin"),
                        resultados.getInt("limite"), resultados.getString("numero"), resultados.getString("month_caducidad"), resultados.getString("year_caducidad"),
                        resultados.getString("cvv")));
            }

            if (consulta != null) {
                consulta.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorTarjetasDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tarjetasBD;
    }

    /**
     * Permite almacenar el estado de un objeto movimiento en nuestra base de
     * datos.
     *
     * @param t la tarjeta de credito asociada al movimiento
     * @param m el movimiento que queremos almacenar
     */
    public void insertMovimiento(TarjetaCredito t, Movimiento m) {
        try {
            //ABRIMOS LA CONEXION AL INSERTAR
            Connection con = DriverManager.getConnection(URL, USUARIO, PASSWORD);

            String query = """
                                   INSERT INTO movimientos(
                                       cantidad_euros,
                                       concepto,
                                       fecha,
                                       tarjeta_asociada
                                   ) VALUES (
                                       ?,
                                       ?,
                                       ?,
                                       ?
                                   );""";

            PreparedStatement consulta = con.prepareStatement(query);

            Date fecha = new Date();
            String format = "YYYY-MM-DD HH:MM:SS";
            SimpleDateFormat sdf = new SimpleDateFormat(format, new Locale("en_En"));
            String formattedDate = sdf.format(fecha);

            consulta.setDouble(1, m.getCantidad());
            consulta.setString(2, m.getConcepto());
            consulta.setString(3, formattedDate);
            consulta.setString(4, t.getNumeroTarjeta());

            consulta.executeUpdate();

            if (consulta != null) {
                consulta.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorTarjetasDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Permite obtener todos los movimientos de una tarjeta de credito
     * almacenadas en la base de datos.
     *
     * @param numTarjeta el numero de la tarjeta de credito cuyos movimientos
     * queremos obtener.
     * @return un arraylist con todos los movimientos asociados a ese numero de
     * tarjeta introducido almacenados en la base de datos
     */
    public ArrayList<Movimiento> obtenerMovimientosTarjeta(String numTarjeta) {
        ArrayList<Movimiento> movimientosBD = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(URL, USUARIO, PASSWORD);

            String query = "SELECT * FROM movimientos WHERE tarjeta_asociada = ? ORDER BY fecha";

            PreparedStatement consulta = con.prepareStatement(query);
            consulta.setString(1, numTarjeta);

            ResultSet resultados = consulta.executeQuery();

            String format = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(format, new Locale("en_En"));

            while (resultados.next()) {
                String dateString = resultados.getString("fecha");
                Date date = sdf.parse(dateString);
                movimientosBD.add(new Movimiento(resultados.getDouble("cantidad_euros"), resultados.getString("concepto"), date));
            }

            if (consulta != null) {
                consulta.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(GestorTarjetasDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return movimientosBD;
    }

}
