package com.gui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.gui.menu.MainMenuScreen;
import com.logic.constructor.builder.interceptor.BuilderDispatcher;
import com.logic.constructor.builder.interceptor.BuilderFileContext;
import com.logic.constructor.builder.interceptor.BuilderFileService;
import com.logic.strategy.Context;
import com.logic.strategy.JumpHigher;
import com.mygdx.game.PlatformBuilder;
import com.world.levels.Level;
import com.world.objects.*;
import com.world.player.Character;

import java.util.ArrayList;

public class GameWorld implements Screen {

    private SpriteBatch batch;
    private Stage stage;
    private Character character;
    private BuilderDispatcher dispatcher;
    private BuilderFileContext context;
    private BuilderFileService service;
    private String levelPath;
    private Level level;
    private ArrayList<WorldObject> worldObjects;
    private ArrayList<Coin> coinObjects;
    private ArrayList<CoinBox> coinBoxObjects;
    private Array<Actor> actorArrayList;
    private PlatformBuilder game;
    private MainMenuScreen mainScreen;

    private boolean REVERSE_LEVEL = false;
    private static final String BUTTON = "button";
    private static final String BUTTON_PRESSED = "button-pressed";
    private static final String QUANTUM_SKIN_FONT_EXPORT_FNT = "quantum/skin/font-export.fnt";

    public GameWorld(PlatformBuilder game, MainMenuScreen screen) {
        this.game = game;
        this.mainScreen = screen;
        create();
    }

    private void create()    {
        batch = new SpriteBatch();
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        actorArrayList = new Array<>();
//        character = new BluePersonCharacter("BluePerson" , new Vector2(0,0), new Vector2(75, 100));
//        BluePersonExtension bluePersonExtension = (BluePersonExtension) character.getCharacterExtension("BluePerson");
//
//        if(bluePersonExtension != null) {
//            bluePersonExtension.bluePersonReady();
//            character.changeTexture("sprites/BluePerson.png");
//        }

        character = new Character("", new Vector2(0,0), new Vector2(75, 100));


        if(System.getProperty("os.name").equals("Linux"))
            levelPath = "levels/Level.json";
        else if(System.getProperty("os.name").contains("Windows"))
            levelPath = "levels\\Level.json";

        // Level loading
        dispatcher = new BuilderDispatcher();
        if(REVERSE_LEVEL) {
            context = new BuilderFileContext(levelPath, "json", "reverse");
        }
        else {
            context = new BuilderFileContext(levelPath, "json", "normal");
        }

        service = new BuilderFileService();
        service.runService(context, dispatcher);
        this.level = context.getLevel();
        worldObjects = level.getWorldObjects();
        coinObjects = level.getCoinObjects();
        coinBoxObjects = level.getCoinBoxObjects();

        // Populate obstacle arrays
        for(int x = 0; x < worldObjects.size(); x++){
            stage.addActor(worldObjects.get(x));
        }
        for(int x = 0; x < coinObjects.size(); x++){
            stage.addActor(coinObjects.get(x));
        }
        for(int x = 0; x < coinBoxObjects.size(); x++){
            stage.addActor(coinBoxObjects.get(x));
        }

        // Add character
        stage.addActor(character);

        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("quantum/skin/quantum-horizon-ui.atlas"));
        Skin skin = new Skin();
        skin.addRegions(buttonAtlas);

        TextButton.TextButtonStyle exitButtonStyle = new TextButton.TextButtonStyle();
        exitButtonStyle.up = skin.getDrawable(BUTTON);
        exitButtonStyle.down = skin.getDrawable(BUTTON_PRESSED);
        exitButtonStyle.font = new BitmapFont(Gdx.files.internal(QUANTUM_SKIN_FONT_EXPORT_FNT));
        TextButton exitButton = new TextButton("Exit", exitButtonStyle);
        exitButton.setTransform(true);
        exitButton.setColor(new Color(4555));
        exitButton.setPosition(50, 550);

        exitButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }


            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(mainScreen);
                mainScreen.create();
            }
        });

        stage.addActor(exitButton);

        actorArrayList = stage.getActors();

    }

    @Override
    public void show() {
        //Not used method.
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if(Gdx.input.isKeyPressed(Input.Keys.W) && character.getIsJumping() == Character.CharacterJumpingState.NOT_JUMPING){
                character.jump();
        }
//        if(Gdx.input.isKeyPressed(Input.Keys.D) && !character.isCanMoveRight()) {
        if(Gdx.input.isKeyPressed(Input.Keys.D) && character.getX() + character.getWidth() < character.getxLimit()) {
                character.moveBy(1, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A) && character.getX() > character.getBackwardLimit()) {
//        if(Gdx.input.isKeyPressed(Input.Keys.A) && !character.isCanMoveLeft()) {
                character.moveBy(-1, 0);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S))    {
            Context context = new Context(new JumpHigher());
            character.setJumpHeight(context.executeStrategy(character.getJumpHeight()));
        }

        for (WorldObject worldObject : worldObjects) {
            character.collidesWith(worldObject);
        }

//        character.collidesWith(worldObjects);



        for(int i = 0; i < coinObjects.size(); i++) {
            assert coinObjects.get(i) != null;
            if(character.collidesWith((ScoreObject) coinObjects.get(i))){
                coinObjects.remove(i);
                break;
            }
        }

        for(int i = 0; i < coinBoxObjects.size(); i++) {
            assert coinBoxObjects.get(i) != null;
            if(character.collidesWith((ScoreObject) coinBoxObjects.get(i))){
                coinBoxObjects.remove(i);
                break;
            }
        }

        for(int i = 0; i < actorArrayList.size; i++) {

        }


        if(character.getX() > 900) {
            System.out.println("Game Over ");
            System.out.println("Score: " + character.getCharacterScore());
            game.setScreen(new MainMenuScreen(game));
            dispose();
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
