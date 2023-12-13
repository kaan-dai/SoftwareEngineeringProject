package domain.timer;

import java.util.TimerTask;

import domain.aliens.Alien;
import domain.handlers.RunHandler;

public class TimeWastingAlienTimer extends TimerTask {

    Alien alien;

    private boolean isRunning = false;

    public TimeWastingAlienTimer(Alien  currentAlien){
        this.alien = currentAlien;
    }

    @Override
    public void run() {
        isRunning = true;
        alien.applyFunction();
        RunHandler.getInstance().getRemovalListOfAlien().add(alien);
    }
    
    public boolean isRunning(){
        return isRunning;
    }
}
