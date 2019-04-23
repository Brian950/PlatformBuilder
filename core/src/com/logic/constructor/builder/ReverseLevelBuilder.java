package com.logic.constructor.builder;

import com.world.levels.Level;

public class ReverseLevelBuilder implements IBuilder{

    private Level level;

    public ReverseLevelBuilder(String data){
        this.level = new Level();
    }

    @Override
    public void buildLevel() {
        // Builds level with mirrored object positions
    }

    @Override
    public Level getLevel() {
        return this.level;
    }

}
