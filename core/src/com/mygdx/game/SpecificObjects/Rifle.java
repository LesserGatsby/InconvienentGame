package com.mygdx.game.SpecificObjects;

import com.mygdx.game.Entity;
import com.mygdx.game.Game;

public class Rifle extends Entity{
    
    public boolean held = true;
    Entity rus;
    
    public Rifle(Game game, Entity rus) {
        super(game, 0, 0, 3);
        
        changeImage(game.resourceManager.getTexture("Rus Rifle.png"));
        this.rus = rus;
    }

    @Override
    public void update(Game game) {
        super.update(game);
        
        if (held) {
            dy = 0;
            x = rus.x;
            y = rus.y + rus.displayImage.getHeight()/2;
        }
    }
    
    public void release() {
        held = false;
    }
    
    public void fire(int direction) {
        
    }
    
}
