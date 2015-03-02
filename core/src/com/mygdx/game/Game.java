package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.SpecificObjects.GroundTile;
import com.mygdx.game.SpecificObjects.Player;
import com.mygdx.game.SpecificObjects.Russian;
import com.mygdx.game.SpecificObjects.TestStage;
import java.util.ArrayList;
import java.util.Collections;

public class Game extends ApplicationAdapter {
    
    public float gravity = 1;
    
    public SpriteBatch render;
    ShapeRenderer shape;
    public OrthographicCamera camera;
    public ResourceManager resourceManager;
    
    public boolean debug = false;
    public Player player;
    public Stage stage;
    
    private boolean restartProtect = true;
    
    ArrayList<BaseObject> objects = new ArrayList<BaseObject>();
    ArrayList<BaseObject> drawableObjects = new ArrayList<BaseObject>();
    ArrayList<Collidable> collidableObjects = new ArrayList<Collidable>();
    ArrayList<BaseObject> toBeDestroyed = new ArrayList<BaseObject>();
    
    @Override
    public void create() {
        render = new SpriteBatch();
        shape = new ShapeRenderer();
        camera = new OrthographicCamera(100, 100);
        resourceManager = new ResourceManager();
        
        stage = new TestStage(this);
        stage.begin(this);
    }

    @Override
    public void render() {
        
        if (Gdx.input.isKeyPressed(Input.Keys.R)) {
            if (restartProtect) {
                stage.restart(this);
                restartProtect = false;
            }
        } else {
            restartProtect = true;
        }
        
        if (Gdx.input.isButtonPressed(Input.Keys.R)) {
            debug = true;
        }        else {
            debug = false;
        }
        
        Gdx.gl.glClearColor(0.3f, 0.0f, 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        toBeDestroyed.clear();
        collidableObjects.clear();
        Collections.sort(objects);
        
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).update(this);
            if (objects.get(i).toBeDestroyed) {
                toBeDestroyed.add(objects.get(i));
            }
            if (objects.get(i) instanceof Collidable) {
                Collidable col = (Collidable) objects.get(i);
                col.preCollision(this);
                collidableObjects.add(col);
            }
        }
        
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setProjectionMatrix(camera.combined);
        
        if (debug) {
            System.out.println(collidableObjects.size());
        }
        
        for (Collidable col : collidableObjects) {
            col.prePreCollision(this);
        }
        
        while (collidableObjects.size() > 0) {            
            collidableObjects.get(0).collisionGrouping(this);
        }
        shape.end();
        
        for (BaseObject object : toBeDestroyed) {
            removeObject(object);
        }
        
        camera.update();
        if (!debug) {
            render.setProjectionMatrix(camera.combined);
            render.begin();

            Collections.sort(drawableObjects);
            for (BaseObject object : drawableObjects) {
                Drawable o = (Drawable) object;
                o.render(this);
            }

            render.end();
        }
        
        
        for (int i = 0; i < toBeDestroyed.size(); i++) {
            removeObject(toBeDestroyed.get(i));
        }
    }
    
    public void addObject(BaseObject object) {
        objects.add(object);
        
        if (object instanceof Drawable) { drawableObjects.add(object); }
    }
    
    public void removeObject(BaseObject object) {
        objects.remove(object);
        
        if (object instanceof Drawable) { drawableObjects.remove(object); }
        if (object instanceof Collidable) { collidableObjects.remove(object); }
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
    }
}
