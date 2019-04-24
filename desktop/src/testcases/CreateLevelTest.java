package testcases;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Array;
import com.gui.constructor.ConstructorScreen;
import com.logic.constructor.FactoryProducer;
import com.logic.constructor.ObjectFactory;
import com.world.objects.WorldObject;
import org.junit.Assert;
import org.junit.Test;

public class CreateLevelTest extends TestSetUp
{
 ConstructorScreen screen;
    TextButton button;


    private Array<WorldObject> obstacleArray;
    private ObjectFactory obj;
    private Runnable run;

    public CreateLevelTest()
    {
        super(0);
        obstacleArray = new Array<WorldObject>();
    }

    @Test
    public void TestCoinButton() {
        run = new Runnable() {
            @Override
            public void run() {
                obj = FactoryProducer.getFactory(true);
                final WorldObject coin = obj.getObject("Coin");
                Assert.assertEquals("Coin", coin.getName());
                obstacleArray.add(coin);
                Assert.assertEquals("Coin", obstacleArray.get(0).getName());
                obstacleArray.clear();
            }
        };
        super.runTests(run);
    }

    @Test
    public void TestCoinBoxButton(){
        run = new Runnable() {
            @Override
            public void run() {
                obj = FactoryProducer.getFactory(true);
                final WorldObject box = obj.getObject("CB");
                Assert.assertEquals("CB", box.getName());
                obstacleArray.add(box);
                Assert.assertEquals("CB", obstacleArray.get(0).getName());
                obstacleArray.clear();
            }
        };
        super.runTests(run);
    }

    @Test
    public void TestSquareButton(){
        run = new Runnable() {
            @Override
            public void run() {
                obj = FactoryProducer.getFactory(false);
                final WorldObject rect = obj.getObject("Square");
                Assert.assertEquals("Square", rect.getName());
                obstacleArray.add(rect);
                Assert.assertEquals("Square", obstacleArray.get(0).getName());
                obstacleArray.clear();
            }
        };
        super.runTests(run);
    }

    @Test
    public void TestRectangleButton(){
        run = new Runnable() {
            @Override
            public void run() {
                obj = FactoryProducer.getFactory(false);
                final WorldObject rect = obj.getObject("Rectangle");
                Assert.assertEquals("Rectangle", rect.getName());
                obstacleArray.add(rect);
                Assert.assertEquals("Rectangle", obstacleArray.get(0).getName());
                obstacleArray.clear();
            }
        };
        super.runTests(run);
    }
}
