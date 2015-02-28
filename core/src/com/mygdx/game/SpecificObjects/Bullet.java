package com.mygdx.game.SpecificObjects;

import com.mygdx.game.Collidable;
import com.mygdx.game.Entity;
import com.mygdx.game.Game;

public class Bullet extends Entity{

    public Bullet(float x, float y, float speed) {
        super(null, x, y, 4);
        dx = speed;
    }

    @Override
    public void onCollision(Game game, Collidable object) {
        destroy(game);
    }
}
