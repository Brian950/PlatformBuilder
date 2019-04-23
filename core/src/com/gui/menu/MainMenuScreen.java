package com.gui.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.TimeUtils;
import com.gui.constructor.ConstructorScreen;
import com.gui.game.GameWorld;
import com.mygdx.game.PlatformBuilder;

public class MainMenuScreen implements Screen {

	private PlatformBuilder game;
	private SpriteBatch batch;
	private BitmapFont frames;
	private Label title;
	private BitmapFont titleFont;
	private Label framesLabel;
	private Stage stage;
	private Table table;
	private TextButton createButton;
	private TextButton.TextButtonStyle createButtonStyle;
	private TextButton playButton;
	private TextButton.TextButtonStyle playButtonStyle;
	private TextureAtlas buttonAtlas;
	private Skin skin;
	private float frameRate;
	private float sinceChange;
	private long lastTimeCounted;

	public MainMenuScreen(PlatformBuilder game){
		this.game = game;
		create();
	}

	public void create () {
		batch = new SpriteBatch();

		// FPS counter
		frames = new BitmapFont();
		lastTimeCounted = TimeUtils.millis();
		frameRate = Gdx.graphics.getFramesPerSecond();
		framesLabel = new Label("FPS: "+frameRate, new Label.LabelStyle(frames, Color.BLACK));
		framesLabel.setPosition((float) Gdx.graphics.getWidth()/50, (float) Gdx.graphics.getHeight()-20);

		// Menu widgets
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		table = new Table();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("glassy/skin/glassy-ui.atlas"));
		skin = new Skin();
		skin.addRegions(buttonAtlas);

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/BigSpace.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 56;
		titleFont = generator.generateFont(parameter);
		title = new Label("Platformer Builder", new Label.LabelStyle(titleFont, Color.BLACK));

		createButtonStyle = new TextButton.TextButtonStyle();
		createButtonStyle.up = skin.getDrawable("button");
		createButtonStyle.down = skin.getDrawable("button-down");
		createButtonStyle.font = new BitmapFont(Gdx.files.internal("glassy/skin/font-export.fnt"));
		createButton = new TextButton("Create Level", createButtonStyle);

		playButtonStyle = new TextButton.TextButtonStyle();
		playButtonStyle.up = skin.getDrawable("button");
		playButtonStyle.down = skin.getDrawable("button-down");
		playButtonStyle.font = new BitmapFont(Gdx.files.internal("glassy/skin/font-export.fnt"));
		playButton = new TextButton("Play Level", playButtonStyle);

		table.add(title).padBottom(40);
		table.row();
		table.add(createButton).padBottom(20);
		table.row();
		table.add(playButton);
		table.setPosition((float)Gdx.graphics.getWidth()/2, (float) Gdx.graphics.getHeight()/2);

		stage.addActor(framesLabel);
		stage.addActor(table);
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		framesLabel.setText("FPS: "+frameRate);
		stage.draw();
		batch.end();

		if(createButton.isPressed()){
			game.setScreen(new ConstructorScreen(game));
			dispose();
		}
		else if(playButton.isPressed()){
//			game.setScreen(new GameScreen(game));
			game.setScreen(new GameWorld(game));
			dispose();
		}

		update();
	}

	public void update() {
		long delta = TimeUtils.timeSinceMillis(lastTimeCounted);
		lastTimeCounted = TimeUtils.millis();

		sinceChange += delta;
		if(sinceChange >= 1000) {
			sinceChange = 0;
			frameRate = Gdx.graphics.getFramesPerSecond();
		}
	}

	@Override
	public void dispose () {
		batch.dispose();
		frames.dispose();
		stage.dispose();
	}

	@Override
	public void show() {

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
}
