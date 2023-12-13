package domain.asteroids;

public class SimpleAsteroid  extends Asteroid {
    
    public SimpleAsteroid() {

        this.color = "gray";
        this.life = 1;
        this.width = Asteroid.paddleWidth / 5;
        this.len = 20;
        this.type = "rectangle";
        this.range = 0;
        this.name = "Simple";
        this.isSelected = false;
        this.isFrozen = false;

    }
}
