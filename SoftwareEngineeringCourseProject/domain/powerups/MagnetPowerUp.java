package domain.powerups;

import domain.handlers.RunHandler;

public class MagnetPowerUp extends PowerUp {

    RunHandler rhand = RunHandler.getInstance();
    public MagnetPowerUp() {
        this.type = "Magnet";
        this.remainingAttemps = 1;
        this.length = 10;
    }

    @Override
    public void usePowerUp() {
        rhand.catchBall();
        
    }

    @Override
    public void endPowerUp() {
        
    }
    
}
