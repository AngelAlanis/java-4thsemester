package com.misael.hilos;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkCollision() {
        if (gp.jugador.hitbox.intersects(gp.clint.hitbox) || gp.jugador.hitbox.intersects(gp.robin.hitbox)) {
            System.out.println("collision");
            gp.jugador.collisionOn = true;
        }

    }

}
