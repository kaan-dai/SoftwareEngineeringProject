package domain.timer;

import java.util.TimerTask;

import domain.powerups.PowerUp;
import domain.powerups.PowerUpList;

public class MovePowerUpTimer extends TimerTask {
    
    private PowerUpList powList = PowerUpList.getInstance();
    private boolean running = false;

    @Override
    public void run() {
        this.running = true;
        // runhandler.movePowerUp();
        for(PowerUp pow: powList.getPowerUps()){
            // System.out.println(pow.getY());
            pow.movePowerUp();
        }
    }

    public boolean runStarted() { 
        return running;
    }
}