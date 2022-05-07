package com.misael.hilos.empleados;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class NPC extends Entity {

    Resources resources;
    int       material1, material2;
    Image work1, work2, interactButton;
    String         nombreEmpleado;
    Image          image;
    GamePanel      gp;
    KeyHandler     keyHandler;
    EmpleadoThread empleadoThread;
    boolean        isWorking = false;

    public NPC(GamePanel gp, String nombreEmpleado, KeyHandler keyHandler) {
        this.gp             = gp;
        this.keyHandler     = keyHandler;
        this.nombreEmpleado = nombreEmpleado;
        this.empleadoThread = new EmpleadoThread(this, nombreEmpleado);
        this.resources      = gp.resources;
        hitbox              = new Rectangle(x, y, width, height);
        image               = idle;

        try {
            interactButton = ImageIO.read(new File("ProyectoHilos/src/resources/misc/btn_interactuar.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSize(int width, int height) {
        this.width  = width;
        this.height = height;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void work() {

        if (image == idle) {
            Random random = new Random();
            int    action = random.nextInt(2);

            if (action == 0) {
                image = work1;
            } else if (action == 1 && work2 != null) {
                image = work2;
            }

        }
    }

    public void rest() {
        image = idle;
    }

    public void update() {
        if (isWorking) {
            work();
        } else {
            rest();
        }
    }

    public void recieveMaterialUpdate() {
        drawNewMaterial();
    }

    public void drawNewMaterial() {

    }

    public void draw(Graphics2D g2) {
        hitbox.x      = x;
        hitbox.y      = y;
        hitbox.width  = width;
        hitbox.height = height;

        int center = (x + (width / 2) - 35);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 26));

        //Interact button
        if (collisionOn) {
            g2.drawImage(interactButton, center, y - 70, 70, 70, null);
        }

        //NPC image
        g2.drawImage(image, x, y, width, height, null);

        drawMaterialBar(g2);

    }

    private void drawMaterialBar(Graphics2D g2) {
        g2.drawImage(resources.stone, 50, 45, 50, 50, null);
        g2.drawImage(resources.geode, 200, 45, 50, 50, null);
        g2.drawImage(resources.iron, 350, 45, 50, 50, null);

        g2.drawString(String.valueOf(resources.totalStone), 110, 75);
        g2.drawString(String.valueOf(resources.totalGeode), 260, 75);
        g2.drawString(String.valueOf(resources.totalIron), 410, 75);
    }

}
