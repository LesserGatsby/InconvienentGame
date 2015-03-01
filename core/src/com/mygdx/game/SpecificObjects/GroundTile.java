package com.mygdx.game.SpecificObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Game;
import com.mygdx.game.Tile;

public class GroundTile extends Tile{

    public GroundTile(Game game, String dir, float x, float y, int depth) {
        super(game, x, y, depth);
        
        displayImage = game.resourceManager.getTexture(dir);
        hitBox = new Rectangle(x - displayImage.getWidth()/2, y - displayImage.getHeight()/2, displayImage.getWidth(), displayImage.getHeight());
    }
    
    @Override
    public void render(Game game) {
        if (onScreen(game)) {
            game.render.draw(displayImage, x - displayImage.getWidth()/2, y - displayImage.getHeight()/2);
        }
    }
}
