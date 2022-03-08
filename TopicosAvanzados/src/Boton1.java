import javax.swing.*;

public class Boton1 extends JFrame {

    JButton botonAceptar = new JButton("Aceptar");
    JButton botonCancelar = new JButton("Cancelar");
    JButton botonOmitir = new JButton("Omitir");
    JButton botonGuardar = new JButton("Guardar");
    JButton botonEliminar = new JButton("Eliminar");

    public Boton1(){
        super("Interfaz con botones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelBotones = new JPanel();
        panelBotones.add(botonAceptar);
        panelBotones.add(botonCancelar);
        panelBotones.add(botonOmitir);
        panelBotones.add(botonGuardar);
        panelBotones.add(botonEliminar);

        setContentPane(panelBotones);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Boton1 ventana = new Boton1();
        ventana.setSize(500,150);
        ventana.setVisible(true);
    }

}
