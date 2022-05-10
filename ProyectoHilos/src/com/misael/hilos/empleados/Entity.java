package com.misael.hilos.empleados;

import java.awt.Image;
import java.awt.Rectangle;

public class Entity {

    int x, y, width, height, speed;
    Image     idle;
    Rectangle hitbox;
    int       direction;
    final int DIRECTION_UP    = 0;
    final int DIRECTION_LEFT  = 1;
    final int DIRECTION_DOWN  = 2;
    final int DIRECTION_RIGHT = 3;

    boolean collisionOn = false;


}

