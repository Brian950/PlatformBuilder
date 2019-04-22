package testcases;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gui.constructor.ConstructorScreen;
import com.gui.menu.MainMenuScreen;
import com.mygdx.game.CharacterTest;
import com.mygdx.game.PlatformBuilder;

public class TestSetUp
{

    private ConstructorScreen screen;

    PlatformBuilder builder = new PlatformBuilder();

    TestSetUp(){}

    TestSetUp(int choice){
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.useGL30 = true;
        cfg.height = 600;
        cfg.width = 900;
        if(choice == 0) {
            new LwjglApplication(new PlatformBuilder(), cfg);

        }else if(choice == 1){
            new LwjglApplication(new CharacterTest(), cfg);
        }
    }

    protected ConstructorScreen getConstructorScreen(){
        return screen;
    }
}
