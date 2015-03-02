package com.mygdx.game.SpecificObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Collidable;
import com.mygdx.game.Entity;
import com.mygdx.game.Game;
import com.mygdx.game.Tile;
import com.mygdx.game.Timer;

public class Player extends Entity{

    public float speed = 10;
    public float cappedSpeed = 30;
    boolean allowJump = true;
    boolean grounded = true;
    boolean readyToHug = false;
    boolean hugging = false;
    
    Timer hugTimer;
    
    public Player(Game game, float x, float y) {
        super(game, x - game.resourceManager.getTexture("SGT.png").getWidth()/2, y, 1);
        changeImage(game.resourceManager.getTexture("SGT.png"));
        
        hugTimer = new Timer(game, 5, false, false);
        game.addObject(hugTimer);
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
        
        if (hugTimer.triggered) { 
            hugging = false;
            displayImage.setTexture(game.resourceManager.getTexture("SGT.png"));
        }
        
        if (grounded && !readyToHug && !hugging) {
            allowJump = true;
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (allowJump) {
                dy += 20;

                allowJump = false;
                grounded = false;
            }
        }
        
        if (grounded) {
            dx = 0;
            
            if (!hugging) {
              if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
                    displayImage.setTexture(game.resourceManager.getTexture("SGTPrehug.png"));
                    readyToHug = true;
                }
                else {
                    if (readyToHug) {
                        
                        if (!hugging) {
                            Entity hugBox = new HugBox(game, hugTimer, x, y, 3, displayImage.getScaleX());
                            game.addObject(hugBox);
                        }
                        
                        displayImage.setTexture(game.resourceManager.getTexture("SGTHug.png"));
                        hugging = true;
                        readyToHug = false;
                        hugTimer.resetTimer();
                    }
                }
            }
        }
        
        if (!hugging) {
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                displayImage.setScale(-1, 1);
                dx = -speed;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                displayImage.setScale(1, 1);
                dx = speed;
            }
        }
    }
    
    
    @Override
    public void preCollision(Game game) {
        super.preCollision(game);
        
        hitBox = new Rectangle(x + 20, y, displayImage.getWidth() - 40, displayImage.getHeight());
        game.camera.position.set(x + displayImage.getTexture().getWidth()/2, y + displayImage.getTexture().getHeight()/2, 0);
    }
    
    @Override
    public void onCollision(Game game, Collidable object) {
        super.onCollision(game, object);
        if (object instanceof GroundTile) {
            
            Tile tile = (Tile) object;
            
            float augX = x - (tile.x - tile.displayImage.getWidth() / 2);
            float augY = y - (tile.y - tile.displayImage.getHeight() / 2);
            
            float nx = 0;
            float ny = 0;
            
            if (augY > tile.displayImage.getHeight()/2) {
                grounded = true;
            }
        }
    }

    @Override
    public void destroy(Game game) {
        toBeDestroyed = true;
        hugTimer.toBeDestroyed = true;
    }
    
    
}
