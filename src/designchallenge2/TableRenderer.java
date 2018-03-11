/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package designchallenge2;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Arturo III
 */
public class TableRenderer extends DefaultTableCellRenderer
{
    private int x;
    private int y;
    public TableRenderer(int row, int col){
        this.x = row;
        this.y = col;
    }
    public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column)
    {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0 || column == 6)
                    setBackground(new Color(220,220,255));
            else
                    setBackground(Color.WHITE);
            if(row==x&&column==y)
                setBackground(Color.RED);
            setBorder(null);
            setForeground(Color.black);
            return this;  
    }
    
} 
