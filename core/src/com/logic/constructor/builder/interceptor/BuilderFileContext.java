package com.logic.constructor.builder.interceptor;

import com.logic.constructor.builder.LevelDirector;
import com.world.levels.Level;

import java.util.ArrayList;

public class BuilderFileContext {

    private ArrayList<String> data;
    private String filepath;
    private String fileType;
    private String levelType;
    private Level level;

    public BuilderFileContext(String filepath, String fileType, String levelType){
        this.filepath = filepath;
        this.fileType = fileType;
        this.levelType = levelType;
    }

    public ArrayList<String> getdata(){
        return data;
    }

    public void setData(ArrayList<String> data){
        this.data = data;
    }

    public String getFilepath(){
        return filepath;
    }

    public String getFileType(){
        return fileType;
    }

    public String getLevelType(){
        return levelType;
    }

    public void setLevel(Level level){
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

}
