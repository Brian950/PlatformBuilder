package com.world.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class RectangleObstacle extends WorldObject {

    // Properties
    private Vector2 position;
    private Vector2 size;
    private Rectangle bounds;
    private boolean colliding;
    private Texture texture;
    private Texture invalidTexture;
    private Drawable drawable;

    public RectangleObstacle(Vector2 position, Vector2 size) {
        super(new Texture(Gdx.files.internal("red_tile.jpg")));
        this.texture = new Texture(Gdx.files.internal("red_tile.jpg"));
        this.invalidTexture = new Texture(Gdx.files.internal("quantum/skin/window.9.png"));

        this.colliding = false;
        this.position = position;
        this.size = size;
        bounds = new Rectangle(position.x, position.y, size.x, size.y);
        setBounds(position.x, position.y, size.x, size.y);

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
    }

    @Override
    public void moveBy(float x, float y){

        if(getX()+x > 0 && getX()+x < 900) {
            float move = getX() + x;

            if(x % 5 > 0)
                move = 5*(Math.round(move/5));

            setX(move);
            bounds.x += x;
        }
        if(getY()+y > 0 && getY()+y < 600) {
            setY(getY() + y);
            bounds.y += y;
        }
    }

    public Rectangle getBounds(){
        return bounds;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public Vector2 getSize() {
        return size;
    }

    public boolean collidesWith(WorldObject obj){
        Rectangle otherBounds = obj.getBounds();
        if(bounds.overlaps(otherBounds)){
            return true;
        }
        if(colliding){
            drawable = new TextureRegionDrawable(new TextureRegion(texture));
            super.setDrawable(drawable);
        }
        colliding = false;
        return false;
    }

    public void onCollision(){
        if(!colliding) {
            drawable = new TextureRegionDrawable(new TextureRegion(invalidTexture));
            super.setDrawable(drawable);
        }
        colliding = true;
        System.out.println("Collision");
    }
}
