package testcases;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gui.constructor.ConstructorScreen;
import com.mygdx.game.PlatformBuilder;
import com.mygdx.game.desktop.DesktopLauncher;

public class TestSetUp
{

    private ConstructorScreen screen;
    private LwjglApplication app;

    PlatformBuilder builder = new PlatformBuilder();

    TestSetUp(){}

    TestSetUp(int choice){
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.useGL30 = true;
        cfg.height = 600;
        cfg.width = 900;
        if(choice == 0) {
            app = new LwjglApplication(new PlatformBuilder(), cfg);
        }
    }

    protected ConstructorScreen getConstructorScreen() {
        return screen;
    }

    protected void runTests(Runnable run){
        app.postRunnable(run);
    }
}
