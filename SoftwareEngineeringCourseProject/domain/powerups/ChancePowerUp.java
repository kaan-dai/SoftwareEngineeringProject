package domain.powerups;

import domain.objects.Player;

public class ChancePowerUp extends PowerUp {
    

    public ChancePowerUp() {
        this.type = "Chance";
        this.remainingAttemps = 1;
        this.length = 10;
    }

    @Override
    public void usePowerUp() {
        Player.getInstance().increaseLife();
        
    }

    @Override
    public void endPowerUp() {
     
        
    }

    
    
    
}
