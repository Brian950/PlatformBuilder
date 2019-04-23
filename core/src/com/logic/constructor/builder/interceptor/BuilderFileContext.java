package com.logic.constructor.builder.interceptor;

import com.logic.constructor.builder.LevelDirector;
import com.world.levels.Level;

public class BuilderFileContext {

    private String data;
    private String filepath;
    private String fileType;
    private String levelType;
    private Level level;

    public BuilderFileContext(String filepath, String type, String levelType){
        this.filepath = filepath;
        this.fileType = type;
        this.levelType = levelType;
    }

    public String getdata(){
        return data;
    }

    public void setData(String data){
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
