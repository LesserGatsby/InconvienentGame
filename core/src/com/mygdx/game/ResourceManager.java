package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;

public class ResourceManager {

    private HashMap<String, Texture> textures = new HashMap<String, Texture>();
    private HashMap<String, Sound> sounds = new HashMap<String, Sound>();
    
    public void loadTexture(String dir) {
        textures.put(dir, new Texture(dir));
    }
    
    public Texture getTexture(String dir) {
        
        if (!textures.containsKey(dir)) {
            loadTexture(dir);
        }
        
        return textures.get(dir);
    }
}
