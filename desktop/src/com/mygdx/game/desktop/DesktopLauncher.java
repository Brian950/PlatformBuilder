package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.PlatformBuilder;


public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.useGL30 = true;
		cfg.height = 600;
		cfg.width = 900;
		new LwjglApplication(new PlatformBuilder(), cfg);
//		new LwjglApplication(new CharacterTest(), cfg);
//		new LwjglApplication(new Physics3(), cfg);
	}
}
