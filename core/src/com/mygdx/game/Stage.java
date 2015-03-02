package com.mygdx.game;

import com.mygdx.game.SpecificObjects.GroundTile;
import com.mygdx.game.SpecificObjects.Player;
import com.mygdx.game.SpecificObjects.Russian;

public class Stage {

    public Stage(Game game) {
        
    }

    public void begin(Game game) {
        
    }
    
    public void restart(Game game) {
        while (game.objects.size() > 0) {
            game.removeObject(game.objects.get(0));
        }
        
        begin(game);
    }

}
