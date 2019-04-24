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
    private ObjectAttributes[] obstacles;
    private File file;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public SaveLevel(String name, Array<WorldObject> obstacleArray){
        levelName = name;
        this.obstacleArray = obstacleArray;

        if(System.getProperty("os.name").equals("Linux"))
            file = new File("levels/"+levelName+".json");
        else if(System.getProperty("os.name").contains("Windows"))
            file = new File("levels\\"+levelName+".json");

    }

    public void saveToFile(){
        obstacles = new ObjectAttributes[obstacleArray.size];
        for(int i =0; i < obstacleArray.size; i++)
        {
            obstacles[i] = new ObjectAttributes(obstacleArray.get(i).toString(), obstacleArray.get(i).getX(), obstacleArray.get(i).getY(), obstacleArray.get(i).getSize());
        }
        try {
            String jsonData = gson.toJson(obstacles);
            FileWriter fw = new FileWriter(file);
            fw.write(jsonData);
            fw.close();
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    }
}
