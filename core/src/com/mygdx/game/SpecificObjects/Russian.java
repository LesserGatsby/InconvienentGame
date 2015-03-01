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
    boolean alive = true;
    Timer hugTimer;
    Rifle rifle;
    
    Timer fireTimer;
    
    int direction = 1;
    
    public Russian(Game game, float x, float y) {
        super(game, x - game.resourceManager.getTexture("Rus.png").getWidth()/2, y, 2);
        changeImage(game.resourceManager.getTexture("Rus.png"));
        
        rifle = new Rifle(game, this);
        game.addObject(rifle);
        
        fireTimer = new Timer(game, 120, true, true);
        game.addObject(fireTimer);
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
        
        if (alive) {
            if (game.player.x < x) {
                direction = -1;
            }
            else {
                direction = 1;
            }
            
            if (fireTimer.triggered) {
                game.addObject(new Bullet(game, x + displayImage.getWidth()/2, y + displayImage.getHeight()/2, direction * 16));
            }
        }
        
        if (hugTimer != null && willDie) {
            if (hugTimer.triggered) {
                alive = false;
                changeImage(game.resourceManager.getTexture("RusDed.png"));
                dx = (float) (-90 + (Math.random() *180));
                dy = (float) ((Math.random() * 40));
                
                rifle.held = false;
                dx = (float) (-90 + (Math.random() * 180));
                dy = (float) ((Math.random() * 40));
                
                
                willDie = false;
            }
        }
    }
    
    public void getAttacked (Timer timer) {
        hugTimer = timer;
    }
    
    @Override
    public void onCollision(Game game, Collidable object) {
        super.onCollision(game, object);
        
        displayImage.setScale(direction, 1);
        rifle.displayImage.setScale(direction, 1);
        
        if (rifle.held) {
            if (direction == -1) {
                rifle.x += 30;
            }
        }
        
        if (object instanceof HugBox) {
            willDie = true;
        }
    }

    @Override
    public void destroy(Game game) {
        toBeDestroyed = true;
    }
}