package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public class Tile extends BaseObject implements Drawable{
    
    public float x;
    public float y;
    public Texture displayImage = new Texture(2, 2, Pixmap.Format.Alpha);
    
    public Tile(Game game, float x, float y, int depth) {
        super(game);
        this.x = x;
        this.y = y;
        this.depth = depth;
    }
    
    @Override
    public void update(Game game) {
        
    }

    @Override
    public void render(Game game) {
        if (onScreen(game)) {
            game.render.draw(displayImage, x - displayImage.getWidth()/2, y - displayImage.getHeight()/2);
        }
    }
    
    public boolean onScreen(Game game) {
        if (x + displayImage.getWidth()/2 > game.camera.position.x - game.camera.viewportWidth/2 && x - displayImage.getWidth()/2 < game.camera.position.x + game.camera.viewportWidth/2) {
            if (y + displayImage.getHeight()/2 > game.camera.position.y - game.camera.viewportHeight/2 && y - displayImage.getHeight()/2 < game.camera.position.y + game.camera.viewportHeight/2) {
                return true;
            }
        }
        
        return false;
    }
}
