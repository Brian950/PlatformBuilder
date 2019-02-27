package com.logic.constructor.common;

import com.badlogic.gdx.math.Vector2;
import com.logic.constructor.common.Command;

public class MoveLeftOnCommand implements Command {
    private final Vector2 velocity = new Vector2();
    private final Vector2 acceleration = new Vector2();
    private final Vector2 position = new Vector2();

    public MoveLeftOnCommand(){

    }

    public void executeMovement()
    {

    }
}
