package com.mygdx.game.SpecificObjects;

import com.mygdx.game.Game;
import com.mygdx.game.Stage;

public class TestStage extends Stage{

    public TestStage(Game game) {
        super(game);
    }

    @Override
    public void begin(Game game) {
        game.player = new Player(game, 0, 47);
        game.addObject(game.player);
        
        for (int i = -60; i < 60; i++) {
            game.addObject(new GroundTile(game, "BaseTile.png", game.resourceManager.getTexture("BaseTile.png").getWidth() * i, 0, 0));
            
            if (i % 10 == 0 && i != 0) {
                game.addObject(new Russian(game, game.resourceManager.getTexture("BaseTile.png").getWidth() * i, 47));
            }
        }
        
        for (int i = 1; i < 5; i++) {
            game.addObject(new GroundTile(game, "BaseTile.png", game.resourceManager.getTexture("BaseTile.png").getHeight()*-3, game.resourceManager.getTexture("BaseTile.png").getHeight()* i, 0));
        }
        
        for (int i = 1; i < 5; i++) {
            game.addObject(new GroundTile(game, "BaseTile.png", game.resourceManager.getTexture("BaseTile.png").getWidth() * i, game.resourceManager.getTexture("BaseTile.png").getHeight()* 5, 0));
        }
    }
}
