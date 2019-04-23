package com.world.player;

import com.badlogic.gdx.math.Vector2;

public class RedPersonCharacter extends Character {
    public RedPersonCharacter(String characterName, Vector2 characterPosition, Vector2 characterSize) {
        super(characterName, characterPosition, characterSize);
        super.setJumpHeight(100);
    }


    @Override
    public CharacterExtension getCharacterExtension(String extensionName) {
        if(extensionName.equals("RedPerson")) {
            if(characterExtension == null) {
                characterExtension = new RedPerson(this);
            }
            return  characterExtension;
        }
        return super.getCharacterExtension(extensionName);
    }

}
