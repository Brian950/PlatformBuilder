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
import com.logic.constructor.CoinFactory;
import com.logic.constructor.FactoryProducer;
import com.logic.constructor.ObjectFactory;
import com.logic.logging.Dispatcher;
import com.logic.logging.LogContext;
import com.logic.logging.LoggingService;
import com.mygdx.game.PlatformBuilder;
import com.world.objects.RectangleObstacle;
import com.world.objects.WorldObject;

public class ConstructorScreen implements Screen {

    private PlatformBuilder game;
    private SpriteBatch batch;
    private BitmapFont frames;
    private Label framesLabel;
    private Stage stage;
    private Table table;
    private TextButton rectButton;
    private TextButton squareButton;
    private TextButton coinButton;
    private TextButton coinBoxButton;
    private TextButton saveButton;
    private TextureAtlas buttonAtlas;
    private Skin skin;
    private float frameRate;
    private float sinceChange;
    private long lastTimeCounted;
    private Array<WorldObject> obstacleArray;
    private ObjectFactory obj;
    private Dispatcher dispatcher;
    private LoggingService logService;

    // Enable/disable logging
    private boolean LOG_STATUS = true;


    public ConstructorScreen(PlatformBuilder game){
        this.game = game;
        create();
    }

    public void create(){
        batch = new SpriteBatch();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("quantum/skin/quantum-horizon-ui.atlas"));
        skin = new Skin();
        skin.addRegions(buttonAtlas);

        // Dispatcher
        dispatcher = Dispatcher.getInstance();

        // Services
        logService = new LoggingService();
        logService.log(new LogContext("Application started.", LOG_STATUS), dispatcher);

        // FPS counter
        frames = new BitmapFont();
        lastTimeCounted = TimeUtils.millis();
        frameRate = Gdx.graphics.getFramesPerSecond();
        framesLabel = new Label("FPS: "+frameRate, new Label.LabelStyle(frames, Color.BLACK));
        framesLabel.setPosition(Gdx.graphics.getWidth()/50, Gdx.graphics.getHeight()-20);

        // Buttons
        TextButton.TextButtonStyle squareButtonStyle = new TextButton.TextButtonStyle();
        squareButtonStyle.up = skin.getDrawable("button");
        squareButtonStyle.down = skin.getDrawable("button-pressed");
        squareButtonStyle.font = new BitmapFont(Gdx.files.internal("quantum/skin/font-export.fnt"));
        squareButton = new TextButton("Square", squareButtonStyle);
        squareButton.setTransform(true);
        squareButton.setColor(new Color(4555));

        TextButton.TextButtonStyle rectButtonStyle = new TextButton.TextButtonStyle();
        rectButtonStyle.up = skin.getDrawable("button");
        rectButtonStyle.down = skin.getDrawable("button-pressed");
        rectButtonStyle.font = new BitmapFont(Gdx.files.internal("quantum/skin/font-export.fnt"));
        rectButton = new TextButton("Rectangle", rectButtonStyle);
        rectButton.setTransform(true);
        rectButton.setColor(new Color(4555));

        TextButton.TextButtonStyle coinButtonStyle = new TextButton.TextButtonStyle();
        coinButtonStyle.up = skin.getDrawable("button");
        coinButtonStyle.down = skin.getDrawable("button-pressed");
        coinButtonStyle.font = new BitmapFont(Gdx.files.internal("quantum/skin/font-export.fnt"));
        coinButton = new TextButton("Score Coin", coinButtonStyle);
        coinButton.setTransform(true);
        coinButton.setColor(new Color(4555));

        TextButton.TextButtonStyle coinBoxButtonStyle = new TextButton.TextButtonStyle();
        coinBoxButtonStyle.up = skin.getDrawable("button");
        coinBoxButtonStyle.down = skin.getDrawable("button-pressed");
        coinBoxButtonStyle.font = new BitmapFont(Gdx.files.internal("quantum/skin/font-export.fnt"));
        coinBoxButton = new TextButton("Coin Box", coinBoxButtonStyle);
        coinBoxButton.setTransform(true);
        coinBoxButton.setColor(new Color(4555));

        TextButton.TextButtonStyle saveButtonStyle = new TextButton.TextButtonStyle();
        saveButtonStyle.up = skin.getDrawable("button");
        saveButtonStyle.down = skin.getDrawable("button-pressed");
        saveButtonStyle.font = new BitmapFont(Gdx.files.internal("quantum/skin/font-export.fnt"));
        saveButton = new TextButton("Save Level", squareButtonStyle);
        saveButton.setTransform(true);
        saveButton.setColor(new Color(97777));

        // Table
        table = new Table();
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

                logService.log(new LogContext("Level saved.", LOG_STATUS), dispatcher);
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

    public void update(){
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
