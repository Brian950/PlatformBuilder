package com.logic.constructor.builder;

import com.badlogic.gdx.math.Vector2;
import com.world.levels.Level;
import com.world.objects.*;

import java.util.ArrayList;

public class LevelBuilder implements IBuilder {

    private Level level;
    private ArrayList<WorldObject> worldObjects;
    private ArrayList<Coin> coinObjects;
    private ArrayList<CoinBox> coinBoxObjects;
    private ArrayList<String> data;

    public LevelBuilder(ArrayList<String> data){
        this.data = data;
        this.coinObjects = new ArrayList<Coin>();
        this.coinBoxObjects = new ArrayList<CoinBox>();
        this.worldObjects = new ArrayList<WorldObject>();
    }

    @Override
    public void buildLevel() {
        String items[];
        for(int x = 0; x < data.size(); x++){
            items = data.get(x).split(",");
            String type = items[0];
            float xPos = Float.parseFloat(items[1]);
            float yPos = Float.parseFloat(items[2]);
            float w = Float.parseFloat(items[3]);
            float h = Float.parseFloat(items[4]);

            Vector2 position = new Vector2(xPos, yPos);
            Vector2 size = new Vector2(w, h);

            if(type.equals("RectangleObstacle")){
                WorldObject rect = new RectangleObstacle(position, size);
                worldObjects.add(rect);
            }
            else if(type.equals("Coin")){
                Coin coin = new Coin(position);
                coinObjects.add(coin);
            }
            else if(type.equals("CoinBox")){
                CoinBox coinBox = new CoinBox(position);
                coinBoxObjects.add(coinBox);
            }
            createLevel();
        }
    }

    public void createLevel(){
        level = new Level(worldObjects, coinObjects, coinBoxObjects);
    }

    @Override
    public Level getLevel() {
        return this.level;
    }
}
