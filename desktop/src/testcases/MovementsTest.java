package testcases;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.logic.command.Movements;
import org.junit.Assert;
import org.junit.Test;

public class MovementsTest extends TestSetUp
{

    private TextureRegion player;
    private Vector2 playerPos;
    private Rectangle playerBounds;
    private Movements moveTest;

    private Vector2 velocity;
    private Vector2 position;

    public MovementsTest(){
        super();
        playerPos = new Vector2();
        playerBounds = new Rectangle();
        velocity = new Vector2();
        position = new Vector2();
        moveTest = new Movements(playerPos, playerBounds);

    }

    @Test
    public void testJump()
    {
        moveTest.jump();
        velocity = moveTest.getVelocity();
        Assert.assertEquals(500, velocity.y, 0.0);
        position = moveTest.getPosition();
        Assert.assertEquals(0, position.x, 0.0);
    }

    @Test
    public void testMoveRight()
    {
        moveTest.moveRight();
        velocity = moveTest.getVelocity();
        Assert.assertEquals(0, velocity.y, 0.0);
        position = moveTest.getPosition();
        Assert.assertEquals(5, position.x, 0.0);
    }

    @Test
    public void testMoveLeft()
    {
        moveTest.moveLeft();
        velocity = moveTest.getVelocity();
        Assert.assertEquals(0, velocity.y, 0.0);
        position = moveTest.getPosition();
        Assert.assertEquals(position.x, -5, 0.0);
    }


}
