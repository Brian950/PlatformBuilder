package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import com.logic.controller.InputController;
import com.logic.command.Movements;
import com.logic.strategy.Context;
import com.logic.strategy.JumpHigher;
import com.world.objects.Coin;
import com.world.objects.RectangleObstacle;
import com.world.objects.ScoreObject;
import com.world.objects.WorldObject;
import com.world.player.BluePersonCharacter;
import com.world.player.BluePersonExtension;
import com.world.player.Character;
import com.world.player.Player;

public class GameWorld implements Screen {

    private PlatformBuilder game;
    private SpriteBatch batch;
    private Stage stage;
    private RectangleObstacle rect3;
    private Coin coin;
    private Character character;
    private Array<WorldObject> obstacleArray;
    private Array<ScoreObject> coinArray;


    public GameWorld(PlatformBuilder game) {
        this.game = game;
        create();
    }

    public GameWorld(){
        create();
    }

    public void create()    {
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
        coin = new Coin(new Vector2(600,0));
        stage.addActor(coin);
        coinArray.add(coin);


        stage.addActor(character);

        rect3 = new RectangleObstacle("wooden_crate.png",
                new Vector2(400, 0), new Vector2(100, 75));

        stage.addActor(rect3);

        obstacleArray.add(rect3);
        System.out.println(rect3.getHeight());
        System.out.println(rect3.getBounds());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            if(character.getIsJumping() == Character.CharacterJumpingState.NOT_JUMPING) {
                character.jump();
            }
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

//        character.collidesWith(rect3);

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

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
