package com.misael.hilos;

public class CollisionChecker {

    GamePanel gp;
    KeyHandler keyHandler;

    public CollisionChecker(GamePanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
    }

    public void checkCollision() {

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
