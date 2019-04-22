package com.world.objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Square extends WorldObject {
    public void onCollision(){
        System.out.println("Using Square obstacles draw method");
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
        return null;     //CHANGE IF CLASS IS UPDATED
    }

    @Override
    public Vector2 getSize() {
        return null;    //CHANGE IF CLASS IS UPDATED
    }
}
