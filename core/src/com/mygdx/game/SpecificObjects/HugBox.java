package com.mygdx.game.SpecificObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Collidable;
import com.mygdx.game.Entity;
import com.mygdx.game.Game;
import com.mygdx.game.Timer;

public class HugBox extends Entity{
    
    float hugSize = 10;
    boolean waitOneFrame = true;
    Timer hugTimer;
    
    public HugBox(Game game, Timer hugTimer, float x, float y, int depth, float scale) {
        super(game, x, y, depth);
        
        changeImage(game.resourceManager.getTexture("SGTHugArm.png"));
        displayImage.setScale(scale, 1);
        this.hugTimer = hugTimer;
    }

    @Override
    public void changeImage(Texture texture) {
        super.changeImage(texture); //To change body of generated methods, choose Tools | Templates.
        displayImage.setOrigin(displayImage.getWidth()/2, 0);
        displayImage.setCenter(displayImage.getWidth()/2, 0);
    }
    
    @Override
    public void update(Game game) {
        if (hugTimer.triggered && !waitOneFrame) {
            toBeDestroyed = true;
        }
        
        waitOneFrame = !waitOneFrame;
    }

    @Override
    public void preCollision() {
        super.preCollision(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void render(Game game) {
        displayImage.draw(game.render);
    }

    @Override
    public void onCollision(Game game, Collidable object) {
        if (object instanceof Russian) {
            Russian rus = (Russian) object;
            
            rus.getAttacked(hugTimer);
        }
    }
}
