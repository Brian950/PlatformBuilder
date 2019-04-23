package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.gui.menu.MainMenuScreen;

public class PlatformBuilder extends Game {
    @Override
    public void create() {
        this.setScreen(new MainMenuScreen(this));
    }

    public void dispose() {

    }
}
