package domain.timer;

import java.util.TimerTask;

import domain.handlers.RunHandler;
import domain.powerups.PowerUp;

public class PowerUpEndTimer extends TimerTask {

    RunHandler rh = RunHandler.getInstance();
    PowerUp pow;
    public PowerUpEndTimer(PowerUp pow){
        this.pow = pow;
    }

    @Override
    public void run() {
        rh.setUsingPowerUP(false);
        pow.endPowerUp();        
    }
    
}
