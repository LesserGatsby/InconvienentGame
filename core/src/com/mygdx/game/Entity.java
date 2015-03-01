package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.SpecificObjects.GroundTile;

public class Entity extends Tile {

    public Sprite displayImage = new Sprite(new Texture(2, 2, Pixmap.Format.Alpha));
    public float dy = 0;
    public float dx = 0;

    public Entity(Game game, float x, float y, int depth) {
        super(game, x, y, depth);

    }

    @Override
    public void update(Game game) {
        dy -= game.gravity;
    }

    @Override
    public void render(Game game) {
        displayImage.setPosition(x, y);

        if (onScreen(game)) {
            displayImage.draw(game.render);
        }
    }

    @Override
    public boolean onScreen(Game game) {
        return (getHitBox().overlaps(new Rectangle(game.camera.position.x - game.camera.viewportWidth / 2, game.camera.position.y - game.camera.viewportHeight / 2, game.camera.viewportWidth, game.camera.viewportHeight)));
    }

    public void changeImage(Texture texture) {
        displayImage.setTexture(texture);
        displayImage.setSize(texture.getWidth(), texture.getHeight());
        displayImage.setOrigin(0, 0);
        displayImage.setCenter(0, 0);
    }

    @Override
    public void prePreCollision(Game game) {
        x += dx;
        y += dy;

        preCollision(game);
    }

    @Override
    public void preCollision(Game game) {
        displayImage.setPosition(x, y);
        super.hitBox = displayImage.getBoundingRectangle();
    }

    @Override
    public Rectangle getHitBox() {
        return hitBox;
    }

    @Override
    public void onCollision(Game game, Collidable object) {
        if (object instanceof GroundTile) {
            dy = 0;
            Tile tile = (Tile) object;
            
            float augX = x - (tile.x - tile.displayImage.getWidth() / 2);
            float augY = y - (tile.y - tile.displayImage.getHeight() / 2);
            
            float nx = 0;
            float ny = 0;
            
            if (augY > tile.displayImage.getHeight()/16) {
                ny = 1;
            }
            else if (augY < -tile.displayImage.getHeight()/16) {
                ny = -1;
            }
            else if (augX > tile.displayImage.getWidth()/16) {
                nx = 1;
            }
            else if (augX < -tile.displayImage.getWidth()/16) {
                nx = -1;
            }
            
            for (int i = 0; i < 60; i++) {
                preCollision(game);
                if (hitBox.overlaps(object.getHitBox())) {
                    x += nx;
                    y += ny;
                }
                else {
                    break;
                }
            }
        }
    }
}
