package com.world.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import java.util.ArrayList;

public class CoinBox extends WorldObject implements ScoreObject{

    private ArrayList<Coin> coins;
    private Rectangle bounds;
    private Vector2 position;
    private Vector2 size;

    public CoinBox(Vector2 pos){

        super(new Texture(Gdx.files.internal("wooden_crate.png")));
        this.position = pos;
        this.size = new Vector2(50, 50);
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

        // Create non visual coins to store in coin box
        coins = new ArrayList<Coin>();
        Coin c;
        for(int x = 0; x < 10; x++){
            c = new Coin();
            coins.add(c);
        }
    }

    public void addCoin(Coin c){
        coins.add(c);
    }

    public void removeCoin(Coin c){
        coins.remove(c);
    }

    @Override
    public void onCollision(){
        System.out.println("Collision with score box");
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
    public int getScore(){
        int totalBoxScore = 0;
        for(Coin c : coins){
            totalBoxScore += c.getScore();
        }
        return totalBoxScore;
    }

    @Override
    public Rectangle getCoinBounds() {
        return bounds;
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
