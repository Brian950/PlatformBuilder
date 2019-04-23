package com.logic.command;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Movements {

    private boolean orientation = true;
    private Vector2 position;
    private Vector2 velocity;
    private Command moveRight;
    private Command moveLeft;
    private Command jump;
    private Rectangle bounds;

    public Movements(Vector2 playerPos, Rectangle playerBounds, float jumpHeight){
        position = playerPos;
        bounds = playerBounds;
        velocity = new Vector2();
        moveRight = new MoveRightOnCommand(playerPos, playerBounds);
        moveLeft = new MoveLeftOnCommand(playerPos, playerBounds);
        jump = new JumpOnCommand(playerPos, playerBounds, jumpHeight);
    }

    public void jump() {
        jump.executeMovement(position.x, orientation);
        position.y = jump.getPosition();
        bounds.y = jump.getPlayerBounds();
        velocity = jump.getVelocity();
    }

    public void moveRight() {
        moveRight.executeMovement(position.x, orientation);
        orientation = true;
        position.x = moveRight.getPosition();
        bounds.x = moveRight.getPlayerBounds();
    }

    public void moveLeft() {
        moveLeft.executeMovement(position.x, orientation);
        orientation = false;
        position.x = moveLeft.getPosition();
        bounds.x = moveLeft.getPlayerBounds();
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Vector2 getVelocity(){
        return  velocity;
    }
}
