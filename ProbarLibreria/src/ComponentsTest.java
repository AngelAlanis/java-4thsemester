import javax.swing.*;

import com.misael.componentes.MiBoton;

public class ComponentsTest extends JFrame {

    MiBoton miBoton  = new MiBoton();
    MiBoton miBoton2 = new MiBoton();
    MiBoton miBoton3 = new MiBoton();

    public ComponentsTest() {
        this.setSize(300, 300);
        miBoton.setText("TEST");
        this.add(miBoton);
        this.add(miBoton2);
        this.add(miBoton3);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new ComponentsTest();
    }

}
