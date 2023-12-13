package domain.aliens;

import domain.asteroids.Asteroid;
import domain.asteroids.AsteroidFactory;
import domain.asteroids.AsteroidList;
import domain.controllers.BuildingController;
import domain.handlers.BuildingHandler;

public class RepairingAlien extends Alien {

    AsteroidList alist = AsteroidList.getInstance();
    AsteroidFactory astFactory = AsteroidFactory.getInstance();
    BuildingHandler buildingHandler = BuildingHandler.getInstance();


    public RepairingAlien() {
        this.type = "Repairing";
        this.isMoving = false;
        this.isHit = false;
    }

    //will create a simple asteroid in each 5 seconds
    public void repair() {
        //System.out.println("Inseide of repair");
        if (alist.getListSize() <  BuildingController.getInstance().getInitialAsteroidNum()) {
            Asteroid a = AsteroidFactory.getInstance().createSimple();
            a.setMoving(false);
        } 
    }

    //surprising alien's repairing state builds a row of simple asteroids.
    public void buildRow() {
        int x = 0;
        while(x < buildingHandler.getWidth() - 300) {
            Asteroid a = astFactory.createSimple();
            a.setX(x);
            a.setY(buildingHandler.getHeight() / 2);
            a.setMoving(false);
            x += a.getWidth() + (a.getWidth() / 10);
        }
    }

    @Override
    public void applyFunction() {
        repair();
    }
}

