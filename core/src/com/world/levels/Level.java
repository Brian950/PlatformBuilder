package com.world.levels;

import com.world.objects.Coin;
import com.world.objects.CoinBox;
import com.world.objects.ScoreObject;
import com.world.objects.WorldObject;

import java.util.ArrayList;

public class Level {

    private ArrayList<WorldObject> worldObjects;
    private ArrayList<Coin> coinObjects;
    private ArrayList<CoinBox> coinBoxObjects;

    public Level(ArrayList<WorldObject> worldObjects, ArrayList<Coin> coinObjects, ArrayList<CoinBox> coinBoxObjects){
        this.worldObjects = worldObjects;
        this.coinObjects = coinObjects;
        this.coinBoxObjects = coinBoxObjects;
    }

    public ArrayList<WorldObject> getWorldObjects(){
        return worldObjects;
    }

    public ArrayList<Coin> getCoinObjects(){
        return coinObjects;
    }

    public ArrayList<CoinBox> getCoinBoxObjects(){
        return coinBoxObjects;
    }
}
