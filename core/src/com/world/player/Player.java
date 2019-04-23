package com.world.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.logic.command.Movements;
import com.world.objects.ScoreObject;
import com.world.objects.WorldObject;
import com.world.objects.RectangleObstacle;
import org.lwjgl.Sys;



public class Player extends Image {

    public enum jumpingState {
        CAN_JUMP, CANNOT_JUMP
    }

    private String playerTexturePath;
    private int playerScore;
    private Rectangle playerBounds;
    private Texture playerTexture;
    private Texture jumpTexture;
    private Drawable drawableRegion;
    private Vector2 playerPosition;
    private Vector2 playerSize;
    private Vector2 velocity;
    private Vector2 acceleration;
    private float floor = 0;
    private jumpingState isJumping;
    private boolean isColliding;
    private boolean isBlockedRight;
    private boolean isBlockedLeft;
    private Movements playerMovements;


    public Player(String playerTexture, Vector2 position, Vector2 size){
        super(new Texture(Gdx.files.internal(playerTexture)));
        this.playerTexture = new Texture(Gdx.files.internal(playerTexture));
        this.jumpTexture = new Texture("sprites/Jumping.png");

        this.playerTexturePath = playerTexture;
        isColliding = false;
        isBlockedRight = false;
        playerPosition = position;
        playerSize = size;
        isJumping = jumpingState.CAN_JUMP;
        velocity = new Vector2();
        acceleration = new Vector2(0, -1000);
        playerBounds = new Rectangle(position.x, position.y, size.x, size.y);
        setBounds(position.x, position.y, size.x, size.y);
        playerScore = 0;
        playerMovements = new Movements(playerPosition,playerBounds, 500);
    }

    @Override
    public void moveBy(float x, float y){
        if(getX()+x >= 0 & getX()+x <= 900) {
            if(x == -5){
                playerMovements.moveLeft();
            }else if(x == 5){
                playerMovements.moveRight();
            }
            setX(playerMovements.getPosition().x + x);
            playerBounds.x = playerMovements.getBounds().x + x;
        }

        if(getY()+y >= floor & getY()+y <= 600) {
            setY(getY() + y);
            playerBounds.y += y;
        }
    }

    public Rectangle getPlayerBounds() {
        return playerBounds;
    }

    public boolean collidesWith(Player rect)
    {
        Rectangle otherBounds = rect.getBounds();
        if(playerBounds.overlaps(otherBounds)) {
            return true;
        }
        if(isColliding) {
            drawableRegion = new TextureRegionDrawable(new TextureRegion(playerTexture));
            super.setDrawable(drawableRegion);
        }
        isColliding = false;
        return false;
    }

    public boolean collidesWith(WorldObject rect){
        Rectangle otherBounds = rect.getBounds();
        if(playerBounds.x + playerBounds.getWidth() == rect.getX() && playerBounds.y <= rect.getHeight()){
            isBlockedRight = true;
            return true;
        }
        if(playerBounds.x == otherBounds.x + rect.getWidth() && playerBounds.y <= rect.getHeight()){
            isBlockedLeft = true;
            return true;
        }
        if(playerBounds.x + playerBounds.getWidth() >= otherBounds.x  && playerBounds.x <= otherBounds.x + rect.getWidth())   {
            floor = rect.getY()+rect.getHeight();
            return true;
        }

        floor = 0;
        isBlockedRight = false;
        isBlockedLeft = false;
        isColliding = false;
        return false;
    }

    public boolean collidesWith(ScoreObject coin) {
        Rectangle coinBound  = coin.getCoinBounds();
        if(playerBounds.overlaps(coinBound)){
            playerScore += coin.getScore();
            return true;
        }
        return false;
    }

    public Rectangle getBounds() {
        return playerBounds;
    }

    public void onCollision(){
        isColliding = true;
        drawableRegion = new TextureRegionDrawable(new TextureRegion(jumpTexture));
        super.setDrawable(drawableRegion);
    }

    public void update() {
        float dt = Gdx.graphics.getDeltaTime();
        velocity.add(acceleration.x * dt, acceleration.y * dt);
        moveBy(velocity.x * dt, velocity.y * dt);
        if(playerBounds.y <= floor + 10) {
            drawableRegion = new TextureRegionDrawable(new Texture(playerTexturePath));
            canNowJump();
            super.setDrawable(drawableRegion);
        } else {
            drawableRegion = new TextureRegionDrawable(jumpTexture);
            super.setDrawable(drawableRegion);
        }
    }

    public void jump(float jumpHeight){
            isJumping = jumpingState.CANNOT_JUMP;
            playerMovements.jump();
            velocity.y = playerMovements.getVelocity().y;
            drawableRegion = new TextureRegionDrawable(jumpTexture);
            super.setDrawable(drawableRegion);
    }

    public jumpingState getIsJumping() {
        return isJumping;
    }

    public void canNowJump(){
        isJumping = jumpingState.CAN_JUMP;
    }

    public boolean isBlockedRight() {
        return isBlockedRight;
    }

    public boolean isBlockedLeft() {
        return isBlockedLeft;
    }
}
