package com.gui.constructor;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.PlatformBuilder;
import com.world.obstacle.RectangleObstacle;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;

public class ConstructorScreen implements Screen {

    private PlatformBuilder game;
    private SpriteBatch batch;
    private BitmapFont frames;
    private Label framesLabel;
    private Stage stage;
    private Table table;
    private TextButton rectButton;
    private TextButton triButton;
    private TextButton circButton;
    private TextureAtlas buttonAtlas;
    private Skin skin;
    private float frameRate;
    private float sinceChange;
    private long lastTimeCounted;
    private Array<RectangleObstacle> obstacleArray;

    public ConstructorScreen(PlatformBuilder game){
        this.game = game;
        create();
    }

    public void create(){
        batch = new SpriteBatch();
        buttonAtlas = new TextureAtlas(Gdx.files.internal("quantum/skin/quantum-horizon-ui.atlas"));
        skin = new Skin();
        skin.addRegions(buttonAtlas);

        // FPS counter
        frames = new BitmapFont();
        lastTimeCounted = TimeUtils.millis();
        frameRate = Gdx.graphics.getFramesPerSecond();
        framesLabel = new Label("FPS: "+frameRate, new Label.LabelStyle(frames, Color.BLACK));
        framesLabel.setPosition(Gdx.graphics.getWidth()/50, Gdx.graphics.getHeight()-20);

        // Buttons
        TextButton.TextButtonStyle rectButtonStyle = new TextButton.TextButtonStyle();
        rectButtonStyle.up = skin.getDrawable("button");
        rectButtonStyle.down = skin.getDrawable("button-pressed");
        rectButtonStyle.font = new BitmapFont(Gdx.files.internal("quantum/skin/font-export.fnt"));
        rectButton = new TextButton("Rectangle", rectButtonStyle);
        rectButton.setTransform(true);
        rectButton.setColor(new Color(4555));

        TextButton.TextButtonStyle triButtonStyle = new TextButton.TextButtonStyle();
        triButtonStyle.up = skin.getDrawable("button");
        triButtonStyle.down = skin.getDrawable("button-pressed");
        triButtonStyle.font = new BitmapFont(Gdx.files.internal("quantum/skin/font-export.fnt"));
        triButton = new TextButton("Triangle", triButtonStyle);
        triButton.setTransform(true);
        triButton.setColor(new Color(4555));

        TextButton.TextButtonStyle circButtonStyle = new TextButton.TextButtonStyle();
        circButtonStyle.up = skin.getDrawable("button");
        circButtonStyle.down = skin.getDrawable("button-pressed");
        circButtonStyle.font = new BitmapFont(Gdx.files.internal("quantum/skin/font-export.fnt"));
        circButton = new TextButton("Circle", circButtonStyle);
        circButton.setTransform(true);
        circButton.setColor(new Color(4555));

        // Table
        table = new Table();
        table.add(rectButton).left();
        table.row();
        table.add(triButton).left();
        table.row();
        table.add(circButton).left();
        table.setPosition(Gdx.graphics.getWidth()/8, Gdx.graphics.getHeight()-120);

        // WorldObject array
        obstacleArray = new Array<RectangleObstacle>();

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
                final RectangleObstacle rect = new RectangleObstacle("/home/brian/libgdx/PlatformBuilder/core/assets/badlogic.jpg",
                        new Vector2(50, 50), new Vector2(75, 75));

                stage.addActor(rect);
                obstacleArray.add(rect);
                System.out.println("Touched up");
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
        for(int i = 0; i < obstacleArray.size; i++){
            for(int j = 0; j < obstacleArray.size; j++){
                if(i != j){
                    if(obstacleArray.get(i).collidesWith(obstacleArray.get(j))){
                        obstacleArray.get(i).onCollision(); obstacleArray.get(j).onCollision();
                    }
                }
            }
        }

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
