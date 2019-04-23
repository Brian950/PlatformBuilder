package com.world.levels;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class LoadLevelJSON {

    private String levelName;
    private File file;
    private ArrayList<String> data = new ArrayList<String>();
    private JSONObject jsob;
    private BufferedReader bufferedReader;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type objectType = new TypeToken<ArrayList<String>>() {
    }.getType();

    public LoadLevelJSON(String filePath){
        JSONParser jsonParser = new JSONParser();

        try
        {
            FileReader reader = new FileReader(filePath);
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray objectList = (JSONArray) obj;
            System.out.println(objectList);

            //Iterate over employee array
            objectList.forEach( object -> getMapObjects( (JSONObject) object ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void getMapObjects(JSONObject obj )
    {
        String mapObject = (String) obj.get("object");
        Double xPos = (Double) obj.get("x");
        Double yPos = (Double) obj.get("y");
        JSONObject size = (JSONObject) obj.get("size");
        data.add(mapObject+", "+xPos+", "+yPos+", "+size.get("x")+", "+size.get("y"));
    }

    public ArrayList<String> getData(){
        return  data;
    }
}
