package domain.asteroids;

public class GiftAsteroid extends Asteroid {
  
    public GiftAsteroid() {
        this.color = "yellow";
        this.width = Asteroid.paddleWidth / 5;
        this.life = 1;
        this.len = 20;
        this.type = "rectangle";
        this.range = 0;
        this.name = "Gift";
        this.isMoving = false;
        this.isSelected = false;     
        this.isFrozen = false;   
    }

    public int getWidth() {
        return this.width;
    }
}
