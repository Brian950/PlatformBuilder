/*
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
   */
/* ConstructorScreen screen;
    TextButton button;*//*


    private Array<WorldObject> obstacleArray;
    private Stage stage;
    private ObjectFactory obj;


    public CreateLevelTest()
    {
        super(0);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        obstacleArray = new Array<WorldObject>();
    }

    @Test
    public void TestCoinButton(){
        obj = FactoryProducer.getFactory(true);
        final WorldObject coin = obj.getObject("Coin");
        Assert.assertEquals("Coin", coin.getName());
        stage.addActor(coin);
        obstacleArray.add(coin);
        Assert.assertEquals("Coin", stage.getActors().get(0).getName());
        Assert.assertEquals("Coin", obstacleArray.get(0).getName());
        obstacleArray.clear();
        stage.clear();
    }

    @Test
    public void TestCoinBoxButton(){
        obj = FactoryProducer.getFactory(true);
        final WorldObject box = obj.getObject("CB");
        Assert.assertEquals("CB", box.getName());
        stage.addActor(box);
        obstacleArray.add(box);
        Assert.assertEquals("CB", stage.getActors().get(0).getName());
        Assert.assertEquals("CB", obstacleArray.get(0).getName());
        obstacleArray.clear();
        stage.clear();
    }

    @Test
    public void TestSquareButton(){
        obj = FactoryProducer.getFactory(false);
        final WorldObject rect = obj.getObject("Square");
        Assert.assertEquals("Square", rect.getName());
        stage.addActor(rect);
        obstacleArray.add(rect);
        Assert.assertEquals("Square", stage.getActors().get(0).getName());
        Assert.assertEquals("Square", obstacleArray.get(0).getName());
        obstacleArray.clear();
        stage.clear();
    }

    @Test
    public void TestRectangleButton(){
        obj = FactoryProducer.getFactory(false);
        final WorldObject rect = obj.getObject("Rectangle");
        Assert.assertEquals("Rectangle", rect.getName());
        stage.addActor(rect);
        obstacleArray.add(rect);
        Assert.assertEquals("Rectangle", stage.getActors().get(0).getName());
        Assert.assertEquals("Rectangle", obstacleArray.get(0).getName());
        obstacleArray.clear();
        stage.clear();
    }
}
*/
