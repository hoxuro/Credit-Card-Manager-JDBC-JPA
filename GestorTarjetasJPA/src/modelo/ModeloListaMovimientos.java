/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import gestortarjetas.Movimiento;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Heriberto Amezcua
 */
public class ModeloListaMovimientos extends AbstractTableModel {

    private ArrayList<Movimiento> listaMovimientos;
    private String[] columnas = {"Cantidad (€)", "Concepto", "Fecha"};

    public ModeloListaMovimientos(ArrayList<Movimiento> listaMovimientos) {
        this.listaMovimientos = listaMovimientos;
    }

    @Override
    public int getRowCount() {
        return this.listaMovimientos.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return listaMovimientos.get(rowIndex).getCantidad();
            }
            case 1 -> {
                return listaMovimientos.get(rowIndex).getConcepto();
            }
            case 2 -> {
                return listaMovimientos.get(rowIndex).getFecha();
            }
        }

        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }
}
