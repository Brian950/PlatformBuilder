package com.world.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public abstract class WorldObject extends Image{

    public WorldObject(){}

    public WorldObject(Texture t){
        super(t);
    }

    public abstract void onCollision();
    public abstract boolean collidesWith(WorldObject obj);
    public abstract Rectangle getBounds();
    public abstract Vector2 getPosition();
    public abstract Vector2 getSize();
}
