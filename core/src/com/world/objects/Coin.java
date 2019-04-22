package com.world.objects;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import java.util.Random;

public class Coin extends WorldObject implements ScoreObject {

    private int score;
    private Vector2 position;
    private Vector2 size;
    private Rectangle bounds;

    // Constructor for coins stored in a CoinBox
    public Coin(){
        // Randomly assigns a score to the coin
        Random rand = new Random();
        // Random score value between 1 and 50
        score = rand.nextInt(50)+1;
    }

    // Constructor for placing visual coins in the level
    public Coin(Vector2 pos){

        super(new Texture(Gdx.files.internal("yellow_circle.png")));
        this.position = pos;
        this.size = new Vector2(30, 30);
        this.bounds = new Rectangle(position.x, position.y, size.x, size.y);
        setBounds(position.x, position.y, size.x, size.y);

        // Listener for drag events
        addListener(new DragListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                moveBy(x - getWidth()/2, y - getHeight()/2);
            }
        });

        // Randomly assigns a score to the coin
        Random rand = new Random();
        // Random score value between 1 and 50
        score = rand.nextInt(50)+1;
    }

    @Override
    public void onCollision() {

    }

    @Override
    public boolean collidesWith(WorldObject obj) {
        return false;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void moveBy(float x, float y){

        if(getX()+x > 0 && getX()+x < 900) {
            setX(getX() + x);
            bounds.x += x;
        }
        if(getY()+y > 0 && getY()+y < 600) {
            setY(getY() + y);
            bounds.y += y;
        }
    }

}
