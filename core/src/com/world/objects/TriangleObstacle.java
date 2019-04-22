package com.world.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class TriangleObstacle extends WorldObject {
    public void onCollision(){
        System.out.println("Using Triangle objects draw method");
    }

    @Override
    public boolean collidesWith(WorldObject obj) {
        return false;
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }

    @Override
    public Vector2 getPosition() {
        return null;
    }

    @Override
    public Vector2 getSize() {
        return null;
    }
}
