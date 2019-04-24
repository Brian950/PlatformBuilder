package com.gui.constructor;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.gui.menu.MainMenuScreen;
import com.logic.constructor.CoinFactory;
import com.logic.constructor.FactoryProducer;
import com.logic.constructor.ObjectFactory;
import com.logic.logging.LoggingDispatcher;
import com.logic.logging.LogContext;
import com.logic.logging.LoggingService;
import com.mygdx.game.PlatformBuilder;
import com.world.levels.SaveLevel;
import com.world.objects.WorldObject;

public class ConstructorScreen implements Screen {


    private static final String BUTTON = "BUTTON";
    private static final String BUTTON_PRESSED = "BUTTON-pressed";
    private static final String QUANTUM_SKIN_FONT_EXPORT_FNT = "quantum/skin/font-export.fnt";

    private PlatformBuilder game;
    private SpriteBatch batch;
    private Label framesLabel;
    private Stage stage;
    private float frameRate;
    private float sinceChange;
    private long lastTimeCounted;
    private Array<WorldObject> obstacleArray;
    private ObjectFactory obj;
    private LoggingDispatcher dispatcher;
    private LoggingService logService;
    private MainMenuScreen mainMenu;

    // Enable/disable logging
    private boolean LOG_STATUS = true;


    public ConstructorScreen(PlatformBuilder game, MainMenuScreen mainMenu){
        this.game = game;
        this.mainMenu = mainMenu;
        create();
    }

    private void create(){
        batch = new SpriteBatch();
        TextureAtlas buttonAtlas = new TextureAtlas(Gdx.files.internal("quantum/skin/quantum-horizon-ui.atlas"));
        Skin skin = new Skin();
        skin.addRegions(buttonAtlas);

        // Dispatcher
        dispatcher = LoggingDispatcher.getInstance();

        // Services
        logService = new LoggingService();
        logService.log(new LogContext("Application started.", LOG_STATUS), dispatcher);

        // FPS counter
        BitmapFont frames = new BitmapFont();
        lastTimeCounted = TimeUtils.millis();
        frameRate = Gdx.graphics.getFramesPerSecond();
        framesLabel = new Label("FPS: "+frameRate, new Label.LabelStyle(frames, Color.BLACK));
        framesLabel.setPosition((float) Gdx.graphics.getWidth()/50, (float)Gdx.graphics.getHeight()-20);

        // Buttons
        TextButton.TextButtonStyle squareButtonStyle = new TextButton.TextButtonStyle();
        squareButtonStyle.up = skin.getDrawable(BUTTON);
        squareButtonStyle.down = skin.getDrawable(BUTTON_PRESSED);
        squareButtonStyle.font = new BitmapFont(Gdx.files.internal(QUANTUM_SKIN_FONT_EXPORT_FNT));
        TextButton squareButton = new TextButton("Square", squareButtonStyle);
        squareButton.setTransform(true);
        squareButton.setColor(new Color(4555));

        TextButton.TextButtonStyle rectButtonStyle = new TextButton.TextButtonStyle();
        rectButtonStyle.up = skin.getDrawable(BUTTON);
        rectButtonStyle.down = skin.getDrawable(BUTTON_PRESSED);
        rectButtonStyle.font = new BitmapFont(Gdx.files.internal(QUANTUM_SKIN_FONT_EXPORT_FNT));
        TextButton rectButton = new TextButton("Rectangle", rectButtonStyle);
        rectButton.setTransform(true);
        rectButton.setColor(new Color(4555));

        TextButton.TextButtonStyle coinButtonStyle = new TextButton.TextButtonStyle();
        coinButtonStyle.up = skin.getDrawable(BUTTON);
        coinButtonStyle.down = skin.getDrawable(BUTTON_PRESSED);
        coinButtonStyle.font = new BitmapFont(Gdx.files.internal(QUANTUM_SKIN_FONT_EXPORT_FNT));
        TextButton coinButton = new TextButton("Score Coin", coinButtonStyle);
        coinButton.setTransform(true);
        coinButton.setColor(new Color(4555));

        TextButton.TextButtonStyle coinBoxButtonStyle = new TextButton.TextButtonStyle();
        coinBoxButtonStyle.up = skin.getDrawable(BUTTON);
        coinBoxButtonStyle.down = skin.getDrawable(BUTTON_PRESSED);
        coinBoxButtonStyle.font = new BitmapFont(Gdx.files.internal(QUANTUM_SKIN_FONT_EXPORT_FNT));
        TextButton coinBoxButton = new TextButton("Coin Box", coinBoxButtonStyle);
        coinBoxButton.setTransform(true);
        coinBoxButton.setColor(new Color(4555));

        TextButton.TextButtonStyle saveButtonStyle = new TextButton.TextButtonStyle();
        saveButtonStyle.up = skin.getDrawable(BUTTON);
        saveButtonStyle.down = skin.getDrawable(BUTTON_PRESSED);
        saveButtonStyle.font = new BitmapFont(Gdx.files.internal(QUANTUM_SKIN_FONT_EXPORT_FNT));
        TextButton saveButton = new TextButton("Save Level", squareButtonStyle);
        saveButton.setTransform(true);
        saveButton.setColor(new Color(97777));

        // Table
        Table table = new Table();
        table.add(rectButton).right();
        table.add(saveButton).right();
        table.row();
        table.add(squareButton).right();
        table.row();
        table.add(coinButton).right();
        table.row();
        table.add(coinBoxButton).right();
        table.setPosition(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()-120);

        // WorldObject array
        obstacleArray = new Array<WorldObject>();

        // Stage
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.addActor(table);
        stage.addActor(framesLabel);

        // Listeners
        rectButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }


            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                obj = FactoryProducer.getFactory(false);
                final WorldObject rect = obj.getObject("Rectangle");
                /*final RectangleObstacle rect = new RectangleObstacle("badlogic.jpg",
                        new Vector2(50, 50), new Vector2(75, 75));*/

                stage.addActor(rect);
                obstacleArray.add(rect);
                logService.log(new LogContext("Rectangle added to scene.", LOG_STATUS), dispatcher);
            }
        });

        // Listeners
        squareButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }


            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                obj = FactoryProducer.getFactory(false);
                final WorldObject rect = obj.getObject("Square");
                /*final RectangleObstacle rect = new RectangleObstacle("badlogic.jpg",
                        new Vector2(50, 50), new Vector2(75, 75));*/

                stage.addActor(rect);
                obstacleArray.add(rect);
                logService.log(new LogContext("Square added to screen.", LOG_STATUS), dispatcher);
            }
        });

        coinButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }


            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                obj = FactoryProducer.getFactory(true);
                final WorldObject coin = obj.getObject("Coin");

                stage.addActor(coin);
                obstacleArray.add(coin);
                logService.log(new LogContext("Coin added to screen.", LOG_STATUS), dispatcher);
            }
        });

        coinBoxButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }


            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                obj = FactoryProducer.getFactory(true);
                final WorldObject box = obj.getObject("CB");

                stage.addActor(box);
                obstacleArray.add(box);
                logService.log(new LogContext("CoinBox added to screen.", LOG_STATUS), dispatcher);
            }
        });

        saveButton.addListener(new ClickListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }


            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                SaveLevel save = new SaveLevel("Level", obstacleArray);
                save.saveToFile();
                logService.log(new LogContext("Level saved.", LOG_STATUS), dispatcher);
                game.setScreen(mainMenu);
            }
        });

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,1,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        framesLabel.setText("FPS: "+frameRate);

        stage.act();
        stage.draw();
        batch.end();

        // Check for collisions
//        for(int i = 0; i < obstacleArray.size; i++){
//            for(int j = 0; j < obstacleArray.size; j++){
//                if(i != j){
//                    if(obstacleArray.get(i).collidesWith(obstacleArray.get(j))){
//                        obstacleArray.get(i).onCollision(); obstacleArray.get(j).onCollision();
//                    }
//                }
//            }
//        }

        update();
    }

    private void update(){
        long delta = TimeUtils.timeSinceMillis(lastTimeCounted);
        lastTimeCounted = TimeUtils.millis();

        sinceChange += delta;
        if(sinceChange >= 1000) {
            sinceChange = 0;
            frameRate = Gdx.graphics.getFramesPerSecond();
        }
    }

    @Override
    public void resize(int width, int height) {
        //Not used
    }

    @Override
    public void pause() {
        //Not used
    }

    @Override
    public void resume() {
        //Not used
    }

    @Override
    public void hide() {
        //Not used
    }

    @Override
    public void dispose() {
        //Not used
    }

}
