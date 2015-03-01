package com.mygdx.game.SpecificObjects;

import com.mygdx.game.Collidable;
import com.mygdx.game.Entity;
import com.mygdx.game.Game;

public class Bullet extends Entity{

    float deathTimer = 40;
    float waitForCollide = 3;
    
    public Bullet(Game game, float x, float y, float speed) {
        super(game, x, y, 4);
        changeImage(game.resourceManager.getTexture("bullet.png"));
        dx = speed;
    }

    @Override
    public void update(Game game) {
        super.update(game);
        
        dy = 0;
        deathTimer -= 1;
        waitForCollide -= 1;
        
        if (deathTimer < 1) {
            toBeDestroyed = true;
        }
    }
    
    @Override
    public void onCollision(Game game, Collidable object) {
        
        if (waitForCollide < 0) {
            if (object instanceof GroundTile) {
                destroy(game);
            }

            if (object instanceof Player) {
                Player p = (Player) object;
                p.destroy(game);
            }
        }
    }
}
