package com.world.levels;

import com.badlogic.gdx.math.Vector2;
import com.world.objects.WorldObject;

public class ObjectAttributes {

    private String object;
    private float x;
    private float y;
    private Vector2 size;

    public ObjectAttributes(String object, float x, float y, Vector2 size){
        this.object = object;
        this.x = x;
        this.y = y;
        this.size = size;
    }
}
