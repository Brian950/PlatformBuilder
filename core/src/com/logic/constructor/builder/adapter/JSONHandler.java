package com.logic.constructor.builder.adapter;

import com.world.levels.LoadLevelJSON;

import java.util.ArrayList;

public class JSONHandler implements IHandler{

    private LoadLevelJSON loadLevel;

    public JSONHandler(){
    }

    @Override
    public ArrayList<String> getData(String data) {
        return this.convertJSON(data);
    }

    public ArrayList<String> convertJSON(String filePath){
        loadLevel = new LoadLevelJSON(filePath);
        ArrayList<String> data = loadLevel.getData();
        return data;
    }

}
