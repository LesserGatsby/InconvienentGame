package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.SpecificObjects.Player;
import com.mygdx.game.SpecificObjects.GroundTile;
import com.mygdx.game.SpecificObjects.Russian;
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
        
        player = new Player(this, 0, 47);
        addObject(player);
        
        for (int i = -60; i < 60; i++) {
            addObject(new GroundTile(this, "BaseTile.png", resourceManager.getTexture("BaseTile.png").getWidth() * i, 0, 0));
            
            if (i % 10 == 0 && i != 0) {
                addObject(new Russian(this, resourceManager.getTexture("BaseTile.png").getWidth() * i, 47));
            }
        }
        
        for (int i = 1; i < 5; i++) {
            addObject(new GroundTile(this, "BaseTile.png", resourceManager.getTexture("BaseTile.png").getHeight()*-3, resourceManager.getTexture("BaseTile.png").getHeight()* i, 0));
        }
        
        for (int i = 1; i < 5; i++) {
            addObject(new GroundTile(this, "BaseTile.png", resourceManager.getTexture("BaseTile.png").getWidth() * i, resourceManager.getTexture("BaseTile.png").getHeight()* 5, 0));
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.8f, 1, 0.8f, 1);
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
