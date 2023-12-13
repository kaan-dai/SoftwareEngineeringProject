package domain.handlers;

import java.util.Random;

public class BallDeltaHandler {
    
    Random rand = new Random();
    double newDelt = 1;

    public void calculateDeltaAngle(long deltaX, long deltaY) {

    }

    public double calculateCornerDelta(double delta) {
        // if(delta > 0) newDelt = rand.nextDouble();
        // else  newDelt= rand.nextDouble() - delta;
        return newDelt;
    }

}
