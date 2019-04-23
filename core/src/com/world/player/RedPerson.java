package com.world.player;

public class RedPerson implements RedPersonExtension {

    private RedPersonCharacter character;

    public RedPerson(RedPersonCharacter RedPersonCharacter) {
        this.character = RedPersonCharacter;
    }

    @Override
    public void RedPersonReady() {
        System.out.println("RedPerson picked");
    }
}
