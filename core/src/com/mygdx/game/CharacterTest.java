package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;


import com.logic.command.Movements;
import com.logic.controller.InputController;
import com.world.objects.RectangleObstacle;
import com.world.player.Player;


public class CharacterTest extends ApplicationAdapter {

    private SpriteBatch batch;
    private Texture background;
    private Movements moveTest;
    private Stage stage;
    private Player player1;
    private Player playerRect;
    private RectangleObstacle rectangleObstacle;

    public CharacterTest() {
    }

    @Override
    public void create () {
        batch = new SpriteBatch();
        background = new Texture("glassy/raw/white.png");

        player1 = new Player("sprites/CharSprite.png",new Vector2(0,0), new Vector2(100,75));

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(player1);

        playerRect = new Player("sprites/CharSprite.png",
                new Vector2(700, 0), new Vector2(100, 75));

        rectangleObstacle = new RectangleObstacle("badlogic.jpg",
                new Vector2(300, 0), new Vector2(100, 75));

        stage.addActor(playerRect);
        stage.addActor(rectangleObstacle);

        Gdx.input.setInputProcessor(new InputController(){
            @Override
            public boolean keyDown(int keycode){
                if(keycode == Input.Keys.Q) {
                    playerRect.onCollision();
                }
                return true;
            }
            @Override
            public boolean keyUp(int keycode){
                if(keycode == Input.Keys.Q){
                    System.out.println("Q up");
                }
                return true;
            }
        });

    }

    @Override
    public void render () {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            if(player1.getIsJumping() == Player.jumpingState.CAN_JUMP) {
                player1.jump(500);
            }
            player1.moveBy(0,10);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && !player1.isBlockedRight()) {
                player1.moveBy(5, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) && !player1.isBlockedLeft()) {
                player1.moveBy(-5, 0);
        }

        if(player1.collidesWith(playerRect) || player1.collidesWith(rectangleObstacle)){
            player1.onCollision();
        }

        player1.update();
        stage.act();
        stage.draw();
        batch.begin();
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        background.dispose();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

}


