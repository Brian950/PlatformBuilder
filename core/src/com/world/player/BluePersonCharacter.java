package com.world.player;


import com.badlogic.gdx.math.Vector2;

public class BluePersonCharacter extends Character {


    public BluePersonCharacter(String characterName, Vector2 characterPosition, Vector2 characterSize) {
        super(characterName, characterPosition, characterSize);
        super.setJumpHeight(250);
    }


    @Override
    public CharacterExtension getCharacterExtension(String extensionName) {
        if(extensionName.equals("BluePerson")) {
            if(characterExtension == null) {
                characterExtension = new BluePerson(this);
            }
            return  characterExtension;
        }
        return super.getCharacterExtension(extensionName);
    }
}
