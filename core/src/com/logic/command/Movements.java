package com.logic.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.world.player.Player;

public class Movements {

    private boolean isJumping = false;
    private boolean orientation = true;

    private boolean powerUpUsed = true;

    private float gravity = -1000;

    private Vector2 position;

    private Vector2 velocity;

    private Vector2 acceleration;
    private Command moveRight;
    private Command moveLeft;
    private Command jump;
    private Rectangle bounds;

    public Movements(Vector2 playerPos, Rectangle playerBounds, float jumpHeight){
        position = playerPos;
        bounds = playerBounds;
        velocity = new Vector2();
        float gravity = -1000;
        moveRight = new MoveRightOnCommand(playerPos, playerBounds);
        moveLeft = new MoveLeftOnCommand(playerPos, playerBounds);
        jump = new JumpOnCommand(playerPos, playerBounds, jumpHeight);
        acceleration = new Vector2(0, gravity);
    }


    public void update()
    {
        float dt = Gdx.graphics.getDeltaTime();
        velocity.add(acceleration.x * dt, acceleration.y * dt);
        position.add(velocity.x * dt, velocity.y * dt);

        if (position.y <= 0){ // hit ground, so bounce
            position.y = 0;
            isJumping = false;
            /*if(powerUpUsed){
                jump.setJumpHeight(500);
            }*/
        }
        if ((position.x <= 0)){
            position.x = 0;
        }
/*        if (position.x >= Gdx.graphics.getWidth() - player.getRegionWidth()/2){
            position.x = Gdx.graphics.getWidth() - player.getRegionWidth()/2;
        }*/

    }

    public void jump() {
        jump.executeMovement(position.x, orientation);
        isJumping = true;
        powerUpUsed = true;
        position.y = jump.getPosition();
        bounds.y = jump.getPlayerBounds();
        velocity = jump.getVelocity();
    }

    public void moveRight()
    {
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

    public Rectangle getBounds() { return bounds; }

    public Vector2 getVelocity(){
        return  velocity;
    }
    /*public boolean isJumping() {
        return isJumping;
    }*/

    /*public float getJumpHeight(){
        return jump.getJumpHeight();
    }*/

   /* public void setPowerUpUsed(boolean powerUpUsed) {
        this.powerUpUsed = powerUpUsed;
    }*/
}
