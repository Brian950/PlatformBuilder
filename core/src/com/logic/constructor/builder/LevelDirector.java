package com.logic.constructor.builder;

import com.world.levels.Level;

public class LevelDirector {

    private IBuilder builder;

    public LevelDirector(IBuilder builder){
        this.builder = builder;
    }

    public void buildLevel(){
        this.builder.buildLevel();
    }

    public Level getLevel(){
        return this.builder.getLevel();
    }

}
