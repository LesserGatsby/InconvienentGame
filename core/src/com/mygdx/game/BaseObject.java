package com.mygdx.game;

public class BaseObject implements Comparable<BaseObject>{
    int depth = -10;
    public boolean toBeDestroyed = false;

    public BaseObject(Game game) {
    }
    
    public void update(Game game){
        
    }

    @Override
    public int compareTo(BaseObject o) {
        return depth - o.depth;
    }
    
    public void destroy(Game game) {
        
    }
}
