package com.logic.constructor.builder;

import com.world.levels.Level;

import java.util.ArrayList;

public class ReverseLevelBuilder implements IBuilder{

    private Level level;

    public ReverseLevelBuilder(ArrayList<String> data){
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
