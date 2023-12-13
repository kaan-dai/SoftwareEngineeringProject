package domain.aliens;

import java.util.Random;

import domain.asteroids.AsteroidList;

public class TimeWastingAlien extends Alien {
    
    private static Random rand = new Random();
    private static int asteroidNumberToFreeze = 8;

    private AsteroidList asterList = AsteroidList.getInstance();
   
    //private int asterListSize = asterList.getListSize();
    
    public TimeWastingAlien(){
        this.type = "Time Wasting";
        this.isMoving = false;
        this.isHit = false;
    }

    //will choose 8 asteroids randomly to freeze for 15 seconds
    public void chooseAsteroidsAndFreeze() {
        int count = 0;
        int index;
        //if the remaining asteroid number is less than 8, all of them are chosen:
        if(asterList.getListSize() <= asteroidNumberToFreeze){
            for(int i = 0; i < asterList.getListSize(); i++){
                asterList.getAsteroids().get(i).setIsFrozen(true);
            }
        } else {
            while(count < asteroidNumberToFreeze) {
                index = rand.nextInt(asterList.getListSize());
                asterList.getAsteroids().get(index).setIsFrozen(true);
                count++;
            }
        }
        
    }

    public void unfreeze() {
        try {
            for(int i = 0; i < asterList.getListSize(); i++ ) {
                if(asterList.getAsteroids().get(i).getIsFrozen()) {
                    asterList.getAsteroids().get(i).setIsFrozen(false); 
                }
            }   
        } catch (IndexOutOfBoundsException e) {
            System.out.println("invalid index");
        }
       
    }

    @Override
    public void applyFunction() {
        unfreeze();
        System.out.println("time wasting alien is working");
    }


}
