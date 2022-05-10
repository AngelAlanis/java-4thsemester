package com.misael.hilos.empleados;

public class CollisionChecker {

    GamePanel  gp;
    KeyHandler keyHandler;

    public CollisionChecker(GamePanel gp, KeyHandler keyHandler) {
        this.gp         = gp;
        this.keyHandler = keyHandler;
    }

    public void checkCollision() {

        //Checks if player's hitbox intersects with npc's hitbox


        for (int i = 0; i < gp.npcs.length; i++) {
            if (playerIntersectsNPC(i)) {
                gp.npcs[i].collisionOn = true;
                gp.jugador.collisionOn = true;
            } else {
                gp.npcs[i].collisionOn = false;
            }
        }

    }

    private boolean playerIntersectsNPC(int i) {
        return gp.jugador.hitbox.intersects(gp.npcs[i].hitbox);
    }


}
