package com.misael.hilos.empleados;

public class CollisionChecker {

    GamePanel gp;
    KeyHandler keyHandler;

    public CollisionChecker(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
    }

    public void checkCollision() {

        //Checks if player's hitbox intersects with npc's hitbox

        if (gp.jugador.hitbox.intersects(gp.clint.hitbox)) {
            gp.clint.collisionOn   = true;
            gp.jugador.collisionOn = true;

        } else if (gp.jugador.hitbox.intersects(gp.robin.hitbox)) {
            gp.robin.collisionOn   = true;
            gp.jugador.collisionOn = true;

        } else {
            gp.clint.collisionOn  = false;
            gp.robin.collisionOn  = false;
        }
    }

}
