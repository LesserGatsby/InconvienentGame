package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Entity extends Tile{

    public Sprite displayImage = new Sprite(new Texture(2, 2, Pixmap.Format.Alpha));
    
    public Entity(Game game, float x, float y, int depth) {
        super(game, x, y, depth);
        
    }

    @Override
    public void render(Game game) {
        displayImage.setPosition(x, y);
        
        if (onScreen(game)) {
            displayImage.draw(game.render);
        }
    }
}
