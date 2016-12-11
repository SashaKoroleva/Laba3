package com.company;
import javax.swing.table.AbstractTableModel;
import java.text.DecimalFormat;
import java.text.NumberFormat;

@SuppressWarnings("serial")

public class GornerTableModel extends AbstractTableModel {

    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;


    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        // Вычислить количество точек между началом и концом отрезка исходя из шага табулирования
        return new Double(Math.ceil((to-from)/step)).intValue()+1;
    }

    public Object getValueAt(int row, int col) {

        double x = from + step*row;

        Double result;
        result = coefficients[coefficients.length-1];
        for (int i = 1; i < coefficients.length; i++) {
            result += (Math.pow(x, i) * coefficients[(coefficients.length - 1) - i]);
        }

        if (col==0) {
            return x;
        }
        if (col == 1) {
            return result;
        }
        else {
            int res = (int)(Math.floor(result));
            String str = Integer.toString(res);
            StringBuilder strBuilder = new StringBuilder(str);
            strBuilder.reverse();
            String invertedText = strBuilder.toString();
            return str.equalsIgnoreCase(invertedText) ;
        }

    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            default:
                return "Целая часть палиндром?";
        }
    }

    public Class<?> getColumnClass(int col) {
        if(col == 0 || col == 1) {
            return Double.class;
        }
        else {
            return Boolean.class;
        }

    }
}
