package domain.asteroids;

public class ExplosiveAsteroid extends Asteroid {

    public ExplosiveAsteroid() {
        this.color = "red";
        this.width = 20;
        this.life = 1;
        this.len = width;
        this.type = "circle";
        this.range = Asteroid.paddleWidth * 2;
        this.name = "Explosive";
        this.isMoving = true;
        this.isSelected = false; 
        this.isFrozen = false;  
    }
    
}