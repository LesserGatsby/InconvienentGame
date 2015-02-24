package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

public interface Drawable{
    
    public Texture displayImage = new Texture(2, 2, Pixmap.Format.Alpha);
    
    public void render(Game game);
}
