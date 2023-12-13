package domain.timer;

import java.util.TimerTask;

import domain.handlers.RunHandler;

public class ProtectingAlienTimer extends TimerTask {


    private boolean isRunning = false;
    private RunHandler r;
    public ProtectingAlienTimer(RunHandler r){
        this.r = r;
    }

    @Override
    public void run() {
        isRunning = true;
        r.moveProtecting();
    }

    public boolean isRunning(){
        return isRunning;
    }
    
}