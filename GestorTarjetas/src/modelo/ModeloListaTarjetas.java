/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import gestortarjetas.Movimiento;
import gestortarjetas.TarjetaCredito;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Heriberto Amezcua
 */
public class ModeloListaTarjetas extends AbstractTableModel implements Serializable {

    private ArrayList<TarjetaCredito> listaTarjetas;
    private String[] columnas = {"Titular", "NIF", "Nº Tarjeta", "Total Gastado (€)"};

    public ModeloListaTarjetas() {
        this.listaTarjetas = new ArrayList<>();
    }

    public void eliminarTarjeta(int indice) {
        this.listaTarjetas.remove(indice);
    }

    public void añadirTarjeta(TarjetaCredito t) {
        this.listaTarjetas.add(t);
    }

    public void actualizarTarjeta(int index, TarjetaCredito t) {
        this.listaTarjetas.set(index, t);
    }

    @Override
    public int getRowCount() {
        return this.listaTarjetas.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return listaTarjetas.get(rowIndex).getTitular();
            }
            case 1 -> {
                return listaTarjetas.get(rowIndex).getNif();
            }
            case 2 -> {
                return listaTarjetas.get(rowIndex).getNumeroTarjeta();
            }
            case 3 -> {
                double totalGastado = 0;

                for (Movimiento m : this.listaTarjetas.get(rowIndex).movimientos(this.listaTarjetas.get(rowIndex).numeroMovimientos())) {
                    totalGastado += m.getCantidad();
                }

                return totalGastado;
            }
        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

}
