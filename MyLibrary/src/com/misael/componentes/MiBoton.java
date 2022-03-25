package com.misael.componentes;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class MiBoton extends JButton {

    public MiBoton() {
        super();
        this.setForeground(Color.CYAN);
        this.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
    }

}
