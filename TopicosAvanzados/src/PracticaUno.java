import javax.swing.*;

public class PracticaUno extends JFrame {

    public PracticaUno() {
        setVisible(true);
    }

    public static void main(String[] args) {
        PracticaUno frameUno = new PracticaUno();
        frameUno.setTitle("JFrame 1");
        frameUno.setSize(450, 450);
        frameUno.setLocation(0,0);
        frameUno.setDefaultCloseOperation(EXIT_ON_CLOSE);

        PracticaUno frameDos = new PracticaUno();
        frameDos.setTitle("JFrame 2");
        frameDos.setSize(400, 400);
        frameDos.setLocation(frameUno.getX() + frameUno.getWidth(), frameUno.getY());
        frameDos.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        PracticaUno frameTres = new PracticaUno();
        frameTres.setTitle("JFrame 3");
        frameTres.setSize(350, 350);
        frameTres.setLocation(frameDos.getX() + frameDos.getWidth(), frameDos.getY());
        frameTres.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        PracticaUno frameCuatro = new PracticaUno();
        frameCuatro.setTitle("JFrame 4");
        frameCuatro.setSize(300, 300);
        frameCuatro.setLocation(frameTres.getX() + frameTres.getWidth(), frameTres.getY());
        frameCuatro.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
