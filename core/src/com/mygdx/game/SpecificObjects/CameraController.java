package com.mygdx.game.SpecificObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.Entity;
import com.mygdx.game.Game;

public class CameraController extends Entity{

    public CameraController(Game game, float x, float y, int depth) {
        super(game, x, y, depth);
    }

    @Override
    public void update(Game game) {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += 1;
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= 1;
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= 1;
        }
        
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += 1;
        }
        
        game.camera.position.set(x, y, 0);
    }
}
