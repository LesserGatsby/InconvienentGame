package com.mygdx.game;

public class Timer extends BaseObject{
    public int maxTime = 0;
    public int currentTime = 0;
    public boolean repeat = false;
    public boolean triggered = false;
    
    public Timer(Game game, int maxTime, boolean alreadyTriggered, boolean repeat) {
        super(game);
        
        this.maxTime = maxTime;
        this.repeat = repeat;
        if (alreadyTriggered) {
            resetTimer();
        }
    }
    
    public void resetTimer() {
        currentTime = maxTime;
    }
    
    @Override
    public void update(Game game) {
        currentTime -= 1;
        
        if (currentTime < 0) {
            currentTime = 0;
        }
        
        if (currentTime == 0) {
            triggered = true;
        }
        else {
            triggered = false;
        }
        
        if (repeat) {
            resetTimer();
        }
    }
}
