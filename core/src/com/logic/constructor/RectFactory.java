package com.logic.constructor;

import com.badlogic.gdx.math.Vector2;
import com.world.objects.RectangleObstacle;
import com.world.objects.WorldObject;

public class RectFactory extends ObjectFactory {
    public WorldObject getObject(String obj) {
        if(obj.equals("Rectangle"))
            return new RectangleObstacle("badlogic.jpg",new Vector2(50,50),new Vector2(200,75));
        else
            return new RectangleObstacle("badlogic.jpg",new Vector2(50,50),new Vector2(100,100));
    }
}

