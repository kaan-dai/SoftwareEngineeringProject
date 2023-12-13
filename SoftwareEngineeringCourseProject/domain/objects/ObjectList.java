package domain.objects;

import java.util.ArrayList;

import domain.aliens.Alien;
import domain.aliens.AlienList;
import domain.asteroids.Asteroid;
import domain.asteroids.AsteroidList;
import domain.controllers.BuildingController;
import domain.handlers.RunHandler;
import domain.powerups.PowerUp;
import domain.powerups.PowerUpList;

public class ObjectList {

    private static ObjectList instance;
    private ObjectList(){}

    private static ArrayList<Asteroid> asteroidList;
    private static ArrayList<Alien> alienList;
    private static ArrayList<PowerUp> powerUpList;
    private static ArrayList<Object> otherObjects;




    public static ArrayList<Object> getOtherObjects() {
        return otherObjects;
    }

    public static void setOtherObjects(ArrayList<Object> otherObjects) {
        ObjectList.otherObjects = otherObjects;
    }

    public static ObjectList getInstance() {
        if (instance == null) {
            instance = new ObjectList();
            initializeObjectList();
        }
        return instance;
    }

    private static void initializeObjectList() {
        otherObjects = new ArrayList<Object>();
        otherObjects.add(Paddle.getInstance());
        otherObjects.add(Time.getInstance());
        otherObjects.add(Player.getInstance());
        asteroidList = AsteroidList.getInstance().getAsteroids();
        alienList = AlienList.getInstance().getAliens();
        powerUpList = PowerUpList.getInstance().getPowerUps();
        otherObjects.add(asteroidList);
        otherObjects.add(alienList);
        otherObjects.add(powerUpList);

    }

    @Override
    public String toString() {
        String result = "";
        result += BuildingController.getInstance().getInitialAsteroidNum() + "\n";
        result += Time.getInstance().getStopTime()+ "\n";
        result += Player.getInstance().getScore() + "\n";
        result += Player.getInstance().getHealth() + "\n";
        result += Paddle.getInstance().getPaddleX()+ "\n";
        result += Paddle.getInstance().getPaddleY() + "\n";
        
       
        for (Asteroid aster: AsteroidList.getInstance().getAsteroids()) {
            result += aster.name + "\n";
            result += aster.isMoving + "\n";
            result += aster.giftType + "\n";
            result += aster.life+ "\n";            
            result += aster.getX() + "\n";
            result += aster.getY() + "\n";
        }
        if(!alienList.isEmpty()){
            for (Alien ali : alienList) {
                result += ali.getType() + "\n";
                result += ali.getX() + "\n";
                result += ali.getY() + "\n";
            }
        }
        
        if(!powerUpList.isEmpty()){
            for (PowerUp pow : powerUpList) {
                //result += pow.getRemainingAttemps() + "\n";
                result += pow.getType() + "\n";
                result += pow.getX() + "\n";
                result += pow.getY() + "\n";
            }
        }
        
        
            
        if (RunHandler.getInstance().getBag().size() > 0) {

            for (PowerUp pow : Player.getInstance().getPowerUpBag().values()) {
                result += "InBag";
                result += pow.getType() + "\n";
                result += pow.getX() + "\n";
                result += pow.getY() + "\n";
                
            }
        }
        

        for (Ball ball : RunHandler.getInstance().getBalllist()) {
            result += "Ball" + "\n";
            result += ball.getX() + "\n";
            result += ball.getY() + "\n";
        }
        return result;
    }
}
