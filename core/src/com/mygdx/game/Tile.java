<<<<<<< HEAD
<<<<<<< HEAD
package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import static com.mygdx.game.Collidable.hitBox;
import com.mygdx.game.SpecificObjects.HugBox;
import com.mygdx.game.SpecificObjects.Russian;

public class Tile extends BaseObject implements Drawable, Collidable {

    public float x;
    public float y;
    public Texture displayImage = new Texture(2, 2, Pixmap.Format.Alpha);
    public Rectangle hitBox = new Rectangle(0, 0, 0, 0);

    public Tile(Game game, float x, float y, int depth) {
        super(game);
        this.x = x;
        this.y = y;
        this.depth = depth;
    }

    @Override
    public void update(Game game) {

    }

    @Override
    public void render(Game game) {
        if (onScreen(game)) {
            game.render.draw(displayImage, x - displayImage.getWidth() / 2, y - displayImage.getHeight() / 2);
        }
    }

    public boolean onScreen(Game game) {
        if (x + displayImage.getWidth() / 2 > game.camera.position.x - game.camera.viewportWidth / 2 && x - displayImage.getWidth() / 2 < game.camera.position.x + game.camera.viewportWidth / 2) {
            if (y + displayImage.getHeight() / 2 > game.camera.position.y - game.camera.viewportHeight / 2 && y - displayImage.getHeight() / 2 < game.camera.position.y + game.camera.viewportHeight / 2) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void preCollision() {
        hitBox = new Rectangle(x - displayImage.getWidth() / 2, y - displayImage.getHeight() / 2, displayImage.getWidth(), displayImage.getHeight());
    }

    @Override
    public void onCollision(Game game, Collidable object) {
        if (game.debug) {
            game.shape.setColor(Color.BLACK);
            game.shape.rect(getHitBox().x, getHitBox().y, getHitBox().width, getHitBox().height);
        }
    }

    @Override
    public Rectangle getHitBox() {
        return hitBox;
    }

    @Override
    public void collisionGrouping(Game game) {
        if (game.debug) {
            game.shape.setColor(Color.RED);
            game.shape.rect(getHitBox().x, getHitBox().y, getHitBox().width, getHitBox().height);
        }
        
        TileEntityCode(game);
        
        game.collidableObjects.remove(this);    
    }
    
    private void TileEntityCode (Game game) {
        for (int i = 0; i < game.collidableObjects.size(); i++) {
            if (game.collidableObjects.get(i) != this) {
                
                preCollision();
                game.collidableObjects.get(i).preCollision();
                
                if (getHitBox().overlaps(game.collidableObjects.get(i).getHitBox())) {
                    game.collidableObjects.get(i).onCollision(game, this);
                    onCollision(game, game.collidableObjects.get(i));
                    
                    if (game.debug) {
                        game.shape.setColor(Color.BLUE);
                        game.shape.line(getHitBox().x + getHitBox().width/2, getHitBox().y + getHitBox().height/2, game.collidableObjects.get(i).getHitBox().x + game.collidableObjects.get(i).getHitBox().width/2, game.collidableObjects.get(i).getHitBox().y + game.collidableObjects.get(i).getHitBox().height/2);
                    }
                }
            }
        }
    }
}
=======
=======
>>>>>>> Main
package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import static com.mygdx.game.Collidable.hitBox;
import com.mygdx.game.SpecificObjects.HugBox;
import com.mygdx.game.SpecificObjects.Russian;

public class Tile extends BaseObject implements Drawable, Collidable {

    public float x;
    public float y;
    public Texture displayImage = new Texture(2, 2, Pixmap.Format.Alpha);
    public Rectangle hitBox = new Rectangle(0, 0, 0, 0);

    public Tile(Game game, float x, float y, int depth) {
        super(game);
        this.x = x;
        this.y = y;
        this.depth = depth;
    }

    @Override
    public void update(Game game) {

    }

    @Override
    public void render(Game game) {
        if (onScreen(game)) {
            game.render.draw(displayImage, x - displayImage.getWidth() / 2, y - displayImage.getHeight() / 2);
        }
    }

    public boolean onScreen(Game game) {
        if (x + displayImage.getWidth() / 2 > game.camera.position.x - game.camera.viewportWidth / 2 && x - displayImage.getWidth() / 2 < game.camera.position.x + game.camera.viewportWidth / 2) {
            if (y + displayImage.getHeight() / 2 > game.camera.position.y - game.camera.viewportHeight / 2 && y - displayImage.getHeight() / 2 < game.camera.position.y + game.camera.viewportHeight / 2) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void prePreCollision(Game game) {
        preCollision(game);
    }
    
    @Override
    public void preCollision(Game game) {
        hitBox = new Rectangle(x - displayImage.getWidth() / 2, y - displayImage.getHeight() / 2, displayImage.getWidth(), displayImage.getHeight());
    }

    @Override
    public void onCollision(Game game, Collidable object) {
        if (game.debug) {
            game.shape.setColor(Color.BLACK);
            game.shape.rect(getHitBox().x, getHitBox().y, getHitBox().width, getHitBox().height);
        }
    }

    @Override
    public Rectangle getHitBox() {
        return hitBox;
    }

    @Override
    public void collisionGrouping(Game game) {
        if (game.debug) {
            game.shape.setColor(Color.RED);
            game.shape.rect(getHitBox().x, getHitBox().y, getHitBox().width, getHitBox().height);
        }
        
        for (int i = 0; i < game.collidableObjects.size(); i++) {
            if (game.collidableObjects.get(i) != this) {
                
                if (getHitBox().overlaps(game.collidableObjects.get(i).getHitBox())) {
                    game.collidableObjects.get(i).onCollision(game, this);
                    onCollision(game, game.collidableObjects.get(i));

                    if (game.debug) {
                        game.shape.setColor(Color.BLUE);
                        game.shape.line(getHitBox().x + getHitBox().width/2, getHitBox().y + getHitBox().height/2, game.collidableObjects.get(i).getHitBox().x + game.collidableObjects.get(i).getHitBox().width/2, game.collidableObjects.get(i).getHitBox().y + game.collidableObjects.get(i).getHitBox().height/2);
                    }
                }
            }
        }
        
        game.collidableObjects.remove(this);    
    }
}
<<<<<<< HEAD
>>>>>>> origin/Main
=======
>>>>>>> Main
