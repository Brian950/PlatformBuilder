package com.world.levels;

import com.badlogic.gdx.utils.Array;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.world.objects.WorldObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveLevel {

    private String levelName;
    private Array<WorldObject> obstacleArray;
    private File file;
    private Gson gsonBuilder = new GsonBuilder().create();

    public SaveLevel(String name, Array<WorldObject> obstacleArray){
        levelName = name;
        this.obstacleArray = obstacleArray;
        file = new File(levelName+".json");
        try {
            gsonBuilder.toJson(obstacleArray, new FileWriter(file));
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
}
