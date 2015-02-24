package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public interface Collidable {
    
    public Rectangle hitBox = new Rectangle();
    
    public void onCollision (Game game);
    
}
