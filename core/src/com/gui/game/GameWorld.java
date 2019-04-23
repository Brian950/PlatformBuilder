package com.gui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.logic.strategy.Context;
import com.logic.strategy.JumpHigher;
import com.mygdx.game.PlatformBuilder;
import com.world.objects.Coin;
import com.world.objects.RectangleObstacle;
import com.world.objects.ScoreObject;
import com.world.objects.WorldObject;
import com.world.player.BluePersonCharacter;
import com.world.player.BluePersonExtension;
import com.world.player.Character;

public class GameWorld implements Screen {

    private SpriteBatch batch;
    private Stage stage;
    private Character character;
    private Array<WorldObject> obstacleArray;
    private Array<ScoreObject> coinArray;


    public GameWorld() {
        create();
    }

    private void create()    {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        character = new BluePersonCharacter("BluePerson" , new Vector2(0,0), new Vector2(75, 100));
        BluePersonExtension bluePersonExtension = (BluePersonExtension) character.getCharacterExtension("BluePerson");

        if(bluePersonExtension != null) {
            bluePersonExtension.bluePersonReady();
            character.changeTexture("sprites/BluePerson.png");
        }

        obstacleArray = new Array<WorldObject>();
        coinArray = new Array<ScoreObject>();
        Coin coin = new Coin(new Vector2(600, 0));
        stage.addActor(coin);
        coinArray.add(coin);


        stage.addActor(character);

        RectangleObstacle rect3 = new RectangleObstacle("wooden_crate.png",
                new Vector2(400, 0), new Vector2(100, 75));

        stage.addActor(rect3);

        obstacleArray.add(rect3);
    }

    @Override
    public void show() {
        //Not used method.
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.W) && character.getIsJumping() == Character.CharacterJumpingState.NOT_JUMPING){
                character.jump();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D) && !character.isCanMoveRight()) {
                character.moveBy(5, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) && !character.isCanMoveLeft()) {
                character.moveBy(-5, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))    {
            Context context = new Context(new JumpHigher());
            character.setJumpHeight(context.executeStrategy(character.getJumpHeight()));
        }

        for(int i = 0; i < obstacleArray.size; i++){
            character.collidesWith(obstacleArray.get(i));
        }

        for(int i = 0; i < coinArray.size; i++) {
            assert coinArray.get(i) != null;
            if(character.collidesWith(coinArray.get(i))){
                coinArray.removeIndex(i);
            }
        }

        if(character.getX() > 900) {
            System.out.println("Game Over ");
            System.out.println("Score: " + character.getCharacterScore());
        }

        character.updateCharacter();

        stage.act();
        stage.draw();
        batch.begin();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        //No need to resize screen
    }

    @Override
    public void pause() {
        //No need to pause
    }

    @Override
    public void resume() {
        //No need to resume
    }

    @Override
    public void hide() {
        //No need to hide
    }

    @Override
    public void dispose() {
        //Not used
    }
}
