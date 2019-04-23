package com.logic.constructor.builder;

import com.world.levels.Level;

public class LevelBuilder implements IBuilder {

    private Level level;

    public LevelBuilder(String data){
        this.level = new Level();
    }

    @Override
    public void buildLevel() {

    }

    @Override
    public Level getLevel() {
        return this.level;
    }
}
