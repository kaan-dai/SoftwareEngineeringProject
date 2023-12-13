package domain.powerups;

import domain.handlers.RunHandler;

public class GangOfBalls extends PowerUp {

    RunHandler rHandler;
    
    public GangOfBalls(RunHandler runHandler) {
        this.rHandler = runHandler;
        this.type = "GOB";
        this.remainingAttemps = 1;
        this.length = 10;
    }

    @Override
    public void usePowerUp() {
        rHandler.generateMoreBalls();
    }

    public void endPowerUp() {
    }
    
}

