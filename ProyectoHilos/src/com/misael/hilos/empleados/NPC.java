package com.misael.hilos.empleados;

import com.misael.hilos.SetupFile;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
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

    SetupFile setup = new SetupFile();
    boolean        isWorking = false;

    public NPC(GamePanel gp, String nombreEmpleado, KeyHandler keyHandler) {
        this.gp             = gp;
        this.keyHandler     = keyHandler;
        this.nombreEmpleado = nombreEmpleado;
        this.empleadoThread = new EmpleadoThread(this, nombreEmpleado, gp.sound);
        this.resources      = gp.resources;
        hitbox              = new Rectangle(x, y, width, height);
        image               = idle;

        interactButton = setup.image("/resources/misc/btn_interactuar.png");
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

    public void draw(Graphics2D g2) {
        hitbox.x      = x;
        hitbox.y      = y;
        hitbox.width  = width;
        hitbox.height = height;

        int center = (x + (width / 2) - 45);

        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 26));

        //NPC image
        g2.drawImage(image, x, y, width, height, null);


        //Interact button
        if (collisionOn) {
            g2.drawImage(interactButton, center, y - 100, 90, 90, null);

//            g2.fillRoundRect(x + 50, y - 120, 240, 150, 15, 15);
//            g2.setColor(Color.BLACK);
//            g2.fillRoundRect(x + 55, y - 115, 230, 140, 15, 15);
        }

        g2.setColor(Color.WHITE);
        drawMaterialBar(g2);

    }

    private void drawMaterialBar(Graphics2D g2) {
        g2.drawImage(resources.stone, 50, 50, 50, 50, null);
        g2.drawImage(resources.geode, 200, 50, 50, 50, null);
        g2.drawImage(resources.iron, 350, 50, 50, 50, null);

        g2.drawString(String.valueOf(resources.totalStone), 110, 75);
        g2.drawString(String.valueOf(resources.totalGeode), 260, 75);
        g2.drawString(String.valueOf(resources.totalIron), 410, 75);
    }

}
