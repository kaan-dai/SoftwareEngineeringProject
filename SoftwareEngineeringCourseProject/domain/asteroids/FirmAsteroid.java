package domain.asteroids;

import java.util.Random;

public class FirmAsteroid extends Asteroid {

    public FirmAsteroid() {

        Random rand = new Random();

        this.color = "purple";
        this.life = rand.nextInt(20);
        this.width = 10 + life;
        this.len = width;
        this.type = "circle";
        this.range = 0;
        this.name = "Firm";
        this.isMoving = false;
        this.isSelected = false;
        this.isFrozen = false;
        
    }
    
}
