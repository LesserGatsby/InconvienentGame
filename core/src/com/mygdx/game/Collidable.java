<<<<<<< HEAD
package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public interface Collidable {
    
    public Rectangle hitBox = new Rectangle(-5, -5, 10, 10);
    public boolean collidable = true;
    public float x = 0;
    public float y = 0;
    
    public void preCollision ();
    
    public Rectangle getHitBox();
    
    public void onCollision (Game game, Collidable object);
    
    public void collisionGrouping (Game game);
    
}
=======
package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public interface Collidable {
    
    public Rectangle hitBox = new Rectangle(-5, -5, 10, 10);
    public boolean collidable = true;
    public float x = 0;
    public float y = 0;
    
    public void prePreCollision(Game game);
    
    public void preCollision (Game game);
    
    public Rectangle getHitBox();
    
    public void onCollision (Game game, Collidable object);
    
    public void collisionGrouping (Game game);
    
}
>>>>>>> origin/Main
