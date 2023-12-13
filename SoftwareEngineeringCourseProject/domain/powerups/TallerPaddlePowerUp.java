package domain.powerups;

import domain.objects.Paddle;

public class TallerPaddlePowerUp extends PowerUp {

    Paddle paddle = Paddle.getInstance();
    private int initialWidth;
    
    
    public TallerPaddlePowerUp() {
        this.type = "TallerP";
        this.remainingAttemps = 1;
        this.length = 10;
    }

    @Override
    public void usePowerUp() {
        initialWidth = (int) paddle.getWidth();
        paddle.setWidth( (int) paddle.getWidth() + (int) paddle.getWidth()/2);
    }

    @Override
    public void endPowerUp() {
        paddle.setWidth(initialWidth);;
    }
    
}
