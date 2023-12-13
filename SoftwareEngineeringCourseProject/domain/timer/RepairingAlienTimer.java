package domain.timer;

import java.util.TimerTask;

import domain.aliens.RepairingAlien;

public class RepairingAlienTimer extends TimerTask {

    RepairingAlien alien;

    private boolean isRunning = false;

    public RepairingAlienTimer(RepairingAlien  alien){
        this.alien = alien;
    }

    @Override
    public void run() {
            isRunning = true;
            System.out.println("repairing alien is working");
            alien.applyFunction();  
    }

    public boolean isRunning(){
        return isRunning;
    }
    
}
