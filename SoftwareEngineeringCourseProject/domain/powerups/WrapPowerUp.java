package domain.powerups;

import domain.objects.Paddle;

public class WrapPowerUp extends PowerUp{

    Paddle paddle = Paddle.getInstance();

    public WrapPowerUp() {
        this.type = "Wrap";
        this.remainingAttemps = 1;
        this.length = 10;
    }

    @Override
    public void usePowerUp() {
        
    }

    @Override
    public void endPowerUp() {
        paddle.setIsWrapped(false);
    }
    
}
