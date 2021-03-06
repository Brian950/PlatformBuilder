package com.world.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.logic.command.Movements;
import com.world.objects.ScoreObject;
import com.world.objects.WorldObject;

public class Character extends Image {

    public enum CharacterJumpingState {
        IS_JUMPING, NOT_JUMPING
    }


    private static final float INITIAL_JUMP_HEIGHT = 500;
    private String name;
    private int characterScore;
    private float jumpHeight;
    private Rectangle characterHitBox;
    private Vector2 characterPosition;
    private Vector2 characterVelocity;
    private Vector2 characterAcceleration;
    private float floor = 0;

    private float xLimit = 1000;

    public float getBackwardLimit() {
        return backwardLimit;
    }

    private float backwardLimit = 0;

    private CharacterJumpingState isJumping;
    private boolean canMoveRight;
    private boolean canMoveLeft;
    private Movements characterMovements;
    CharacterExtension characterExtension = null;
    public Character(String characterName, Vector2 characterPosition, Vector2 characterSize) {
        super((new Texture("sprites/CharSprite.png")));

        name = characterName;
        this.characterPosition = characterPosition;

        canMoveLeft = false;
        canMoveRight = false;
        isJumping = CharacterJumpingState.NOT_JUMPING;

        characterVelocity = new Vector2();
        characterAcceleration = new Vector2(0,-1000);
        characterHitBox = new Rectangle(characterPosition.x , characterPosition.y,
                characterSize.x, characterSize.y);
        setBounds(characterPosition.x , characterPosition.y,
                characterSize.x, characterSize.y);
        characterScore = 0;
        jumpHeight = 500;
        characterMovements = new Movements(this.characterPosition, this.characterHitBox, INITIAL_JUMP_HEIGHT);

    }

    public boolean collidesWith(WorldObject worldObject) {
        Rectangle worldObjectBounds = worldObject.getBounds();
        if(characterHitBox.x + characterHitBox.getWidth() == worldObject.getX() && characterHitBox.y <= worldObject.getHeight()) {
            canMoveRight = true;
            xLimit = worldObject.getX();
            System.out.println(xLimit);
            return true;
        }
        if (characterHitBox.x == worldObjectBounds.x + worldObject.getWidth() &&  characterHitBox.y <= worldObject.getHeight())  {
            canMoveLeft = true;
            backwardLimit = worldObject.getX() + worldObject.getWidth();
            return true;
        }
        if(characterHitBox.x + characterHitBox.getWidth() >= worldObjectBounds.x && characterHitBox.x <= worldObjectBounds.x + worldObject.getWidth()) {
            floor = worldObject.getY() + worldObject.getHeight();
            return true;
        }
        if(characterHitBox.y > worldObject.getHeight() + worldObject.getY()){
            xLimit = 1000;
            backwardLimit = 0;
        }

        canMoveRight = false;
        canMoveLeft = false;
        return false;
    }

    public boolean collidesWith(ScoreObject collidedCoin) {
        Rectangle collidedCoinBounds = collidedCoin.getCoinBounds();
        if(characterHitBox.overlaps(collidedCoinBounds)) {
            characterScore += collidedCoin.getScore();
            System.out.println("Coin collected for: " + collidedCoin.getScore() + " points.");
            return true;
        }
        return false;
    }

    public void updateCharacter() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        characterVelocity.add(characterAcceleration.x * deltaTime, characterAcceleration.y * deltaTime);
        moveBy(characterVelocity.x * deltaTime, characterVelocity.y * deltaTime);

        if(characterHitBox.y <= floor + 10) {
            characterCanNowJump();
            setJumpHeight(INITIAL_JUMP_HEIGHT);
        }
    }

    public void jump() {
        floor = 0;
        isJumping = CharacterJumpingState.IS_JUMPING;
        characterMovements.jump();
        characterVelocity.y = characterMovements.getVelocity().y;
     }



    @Override
    public void moveBy(float x, float y){
        if(getX()+x >= 0 && getX()+x <= 900) {
            if(x < 0){
                characterMovements.moveLeft();
            }else if(x > 0){
                characterMovements.moveRight();
            }
            setX(characterMovements.getPosition().x + x);
            characterHitBox.x = characterMovements.getBounds().x + x;
        }

        if(getY()+y >= floor && getY()+y <= 600) {
            setY(getY() + y);
            characterHitBox.y += y;
        }
    }

    public CharacterJumpingState getIsJumping() {
        return isJumping;
    }

    public boolean isCanMoveRight() {
        return canMoveRight;
    }

    public boolean isCanMoveLeft() {
        return canMoveLeft;
    }

    public void characterCanNowJump(){
        isJumping = CharacterJumpingState.NOT_JUMPING;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public CharacterExtension getCharacterExtension(String extensionName) {
        return  null;
    }

    public void changeTexture(String newTexture) {
        Drawable drawableRegion = new TextureRegionDrawable(new Texture(newTexture));
        super.setDrawable(drawableRegion);

    }

    public float getJumpHeight() {
        return jumpHeight;
    }

    public int getCharacterScore() {
        return characterScore;
    }

    public void setJumpHeight(float jumpHeight) {
        this.jumpHeight = jumpHeight;
        characterMovements = new Movements(this.characterPosition, this.characterHitBox, jumpHeight);
    }

    public float getxLimit() {
        return xLimit;
    }

}
