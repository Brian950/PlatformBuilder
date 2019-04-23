package com.world.player;

public class BluePerson implements BluePersonExtension {

    private BluePersonCharacter character;

    public BluePerson(BluePersonCharacter bluePersonCharacter) {
        this.character = bluePersonCharacter;
    }

    @Override
    public void bluePersonReady() {
        System.out.println("BluePerson picked");
    }
}
