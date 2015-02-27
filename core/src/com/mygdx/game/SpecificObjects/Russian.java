package com.mygdx.game.SpecificObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Collidable;
import com.mygdx.game.Entity;
import com.mygdx.game.Game;
import com.mygdx.game.Tile;
import com.mygdx.game.Timer;

public class Russian extends Entity{

    public float speed = 5;
    boolean allowJump = true;
    boolean grounded = true;
    boolean willDie = false;
    Timer hugTimer;
    
    public Russian(Game game, float x, float y) {
        super(game, x - game.resourceManager.getTexture("Rus.png").getWidth()/2, y, 2);
        changeImage(game.resourceManager.getTexture("Rus.png"));
    }

    @Override
    public void changeImage(Texture texture) {
        super.changeImage(texture); //To change body of generated methods, choose Tools | Templates.
        displayImage.setOrigin(displayImage.getWidth()/2, 0);
        displayImage.setCenter(displayImage.getWidth()/2, 0);
    }
    
    @Override
    public void update(Game game) {
        super.update(game);
        
        if (grounded) {
            allowJump = true;
            dx *= .5f;
        }
        
        if (hugTimer != null && willDie) {
            if (hugTimer.triggered) {
                changeImage(game.resourceManager.getTexture("RusDed.png"));
                dx = (float) (-45 + (Math.random() * 90));
                dy = (float) ((Math.random() * 40));
                
                willDie = false;
            }
        }
        
        y += dy;
        x += dx;
    }
    
    public void getAttacked (Timer timer) {
        hugTimer = timer;
    }
    
    @Override
    public void onCollision(Game game, Collidable object) {
        super.onCollision(game, object);
        if (object instanceof HugBox) {
            willDie = true;
        }
    }

    @Override
    public void destroy(Game game) {
        toBeDestroyed = true;
    }
}