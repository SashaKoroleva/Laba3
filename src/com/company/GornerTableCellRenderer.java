package com.company;

/**
 * Created by sasha_koroleva on 22.11.2016.
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRenderer implements TableCellRenderer {

    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private String needle = null;
    private DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance();

    public GornerTableCellRenderer() {

        formatter.setMaximumFractionDigits(5);
        // Не использовать группировку
        formatter.setGroupingUsed(false);
        // Установить в качестве разделителя дробной части точку
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
        // Разместить надпись внутри панели

        panel.add(label);
        // Установить выравнивание надписи по левому краю панели
        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        // Преобразовать double в строку с помощью форматировщика
        String formattedDouble = formatter.format(value);

        // Установить текст надписи равным строковому представлению числа
        label.setText(formattedDouble);
        Double val = Double.valueOf(formattedDouble);
        int dotPos;
        String whole, fractional;

        if (col==1 && needle!=null && needle.equals(formattedDouble)) {
            panel.setBackground(Color.RED);
        }  else {
            panel.setBackground(Color.WHITE);
        }
        if(val%1!=0) {
            dotPos = formattedDouble.indexOf(".");
            whole = formattedDouble.substring(0, dotPos);
            fractional = formattedDouble.substring(dotPos + 1);
            if ((col == 1 || col == 0) && whole.equals(fractional)) {
                panel.setBackground(Color.GREEN);
            }else {
                panel.setBackground(Color.WHITE);
            }
        }
        return panel;
    }

    public void setNeedle(String needle) {
        this.needle = needle;
    }
}
