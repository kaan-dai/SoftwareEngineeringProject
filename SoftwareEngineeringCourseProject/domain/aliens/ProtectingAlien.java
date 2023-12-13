package domain.aliens;

public class ProtectingAlien extends Alien {

    int speed = 3 * Alien.paddleWidth/100; 

    public int getSpeed() {
        return speed;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public ProtectingAlien(){
        this.type = "Protecting";
        this.isMoving = true;
        this.isHit = false;
    }


    public void move() {
        if(this.getX()>screenWidth) speed = -speed;
        if(this.getX()<0) speed = -speed;
        this.setX(this.getX()+speed);

    }


    @Override
    public void applyFunction() {
        move();
    }
}
