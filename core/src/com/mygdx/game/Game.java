package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpecificObjects.CameraController;
import com.mygdx.game.SpecificObjects.GroundTile;
import java.util.ArrayList;
import java.util.Collections;

public class Game extends ApplicationAdapter {

    public SpriteBatch render;
    public OrthographicCamera camera;
    public ResourceManager resourceManager;
    
    ArrayList<BaseObject> objects = new ArrayList<BaseObject>();
    ArrayList<BaseObject> drawableObjects = new ArrayList<BaseObject>();
    ArrayList<BaseObject> collidableObjects = new ArrayList<BaseObject>();
    ArrayList<BaseObject> toBeDestroyed = new ArrayList<BaseObject>();
    
    @Override
    public void create() {
        render = new SpriteBatch();
        camera = new OrthographicCamera(100, 100);
        resourceManager = new ResourceManager();
        
        addObject(new CameraController(this, 0, 0, 1));
        addObject(new GroundTile(this, "Image.png", 0, 0, 0));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        toBeDestroyed.clear();
        Collections.sort(objects);
        for (BaseObject object : objects) {
            object.update(this);
            if (object.toBeDestroyed) {
                toBeDestroyed.add(object);
            }
        }
        
        for (BaseObject object : toBeDestroyed) {
            removeObject(object);
        }
        
        camera.update();
        render.setProjectionMatrix(camera.combined);
        render.begin();

        Collections.sort(drawableObjects);
        for (BaseObject object : drawableObjects) {
            Drawable o = (Drawable) object;
            o.render(this);
        }
        
        render.end();
    }
    
    public void addObject(BaseObject object) {
        objects.add(object);
        
        if (object instanceof Drawable) { drawableObjects.add(object); }
        if (object instanceof Collidable) { collidableObjects.add(object); }
    }
    
    public void removeObject(BaseObject object) {
        objects.add(object);
        
        if (object instanceof Drawable) { drawableObjects.remove(object); }
        if (object instanceof Collidable) { collidableObjects.remove(object); }
    }
}
