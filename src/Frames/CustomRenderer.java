/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.*;
import javax.swing.*;
/**
 *
 * @author jarrydbaillie
 */
class CustomRenderer extends DefaultTableCellRenderer 
{
    private java.util.List<Color> desiredColors = new ArrayList<Color>();

    public void setColors(Color incomingColor)
    {
        desiredColors.add(incomingColor);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        for (int i = 0; i < desiredColors.size(); i++) {
            if(row == i){
                cellComponent.setBackground(desiredColors.get(i));
            }
        }
        return cellComponent;
    }
}