package domain.timer;

import java.util.TimerTask;

import domain.aliens.Alien;

public class SurprisingMoveTimer extends TimerTask {

    Alien ali;
    private boolean isRunning = false;
    public SurprisingMoveTimer(Alien ali){
        this.ali = ali;
    }

    @Override
    public void run() {
        isRunning = true;
        ali.applyFunction();
    }

    public boolean isRunning(){
        return isRunning;
    }
    
}