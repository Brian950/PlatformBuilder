package com.logic.command;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class JumpOnCommand implements Command {

    private final Vector2 velocity = new Vector2();
    private Vector2 position;
    private Rectangle bounds;
    private float jumpHeight = 750;

    public JumpOnCommand(Vector2 playerPos, Rectangle playerBounds, float jumpHeight) {
        position = playerPos;
        bounds = playerBounds;
        this.jumpHeight = jumpHeight;
    }
    public void executeMovement(float pos, boolean orientation)
    {
        velocity.y = jumpHeight;
    }

    public float getPosition(){
        return position.y;
    }

    public float getPlayerBounds(){return bounds.y; }

    public Vector2 getVelocity(){
        return velocity;
    }

    public void setJumpHeight(float jumpHeight) {
        this.jumpHeight = jumpHeight;
    }
}
