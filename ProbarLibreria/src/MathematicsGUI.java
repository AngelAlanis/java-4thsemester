import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MathematicsGUI extends JFrame {
    private JPanel panel1;
    private JTable table1;

    public void configurarComponentes() {
        var defaultTableModel = new DefaultTableModel();
        table1.setModel(defaultTableModel);
        defaultTableModel.addColumn("i");
        defaultTableModel.addColumn("a");
        defaultTableModel.addColumn("f(a)");
        defaultTableModel.addColumn("b");
        defaultTableModel.addColumn("f(b)");
        defaultTableModel.addColumn("xi");
        defaultTableModel.addColumn("error");
        defaultTableModel.addColumn("f(xi)");


    }
}
