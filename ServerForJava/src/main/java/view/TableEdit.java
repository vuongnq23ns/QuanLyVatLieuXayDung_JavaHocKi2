package view;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import java.awt.Color;
import javax.swing.SwingConstants;

public class TableEdit extends DefaultTableModel {
    private int editableRowIndex = -1;

    // Custom cell renderer as an inner class
    public class CustomRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBackground(new Color(50,60,80,255));  // 
            setForeground(new Color(74,187,239,255));  // 
            setHorizontalAlignment(SwingConstants.CENTER);  // Canh giữa dữ liệu trong ô
            setFont(new Font("Arial", Font.BOLD, 25));  // Use a bold Arial font at size 16
            
            return this;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return row == editableRowIndex;
    }

    public void setEditableRow(int rowIndex) {
        this.editableRowIndex = rowIndex;
    }

    public void clearEditableRow() {
        this.editableRowIndex = -1;
    }
}
