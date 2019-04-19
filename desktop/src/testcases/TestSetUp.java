package testcases;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.CharacterTest;

public class TestSetUp
{
    TestSetUp(){
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.useGL30 = true;
        cfg.height = 600;
        cfg.width = 900;
        new LwjglApplication(new CharacterTest(), cfg);
    }
}
