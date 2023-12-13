package domain.aliens;

import java.util.ArrayList;
import java.util.Random;

import domain.asteroids.Asteroid;
import domain.asteroids.AsteroidList;
import domain.handlers.BuildingHandler;

public class CooperativeAlien extends Alien {
    
    static Random rand = new Random();
    public static int maxY = BuildingHandler.height/2 + 200;
    public static int rowLength = maxY / 10;

    AsteroidList asterList = AsteroidList.getInstance();
    private boolean willAppear = true;

    public CooperativeAlien(){
        this.type = "Cooperative";
        this.isMoving = false;
        this.isHit = false;
    }

    //finds a random y coordinate
    public static int findRandomY() {
        return rand.nextInt(maxY);
    }

    //returns a y coordinate
    public int pickY1() {
        int y = findRandomY();
        while (y + rowLength > maxY) {
            y = findRandomY();
        }
        return y; 
    }

    /* counts number of asteroids between y1 and y2 coordinates. 
     * If there is at least 1 asteroid in the picked row,
     * it returns true, otherwise false.
     */
    public boolean checkAsteroidNumberInRow(int y1, int y2) {
        int count = 0;
        for(Asteroid ast : asterList.getAsteroids()) {
            int upperLeftCornerOfAst = ast.getY();
            int lowerLeftCornerOfAst = upperLeftCornerOfAst + ast.getLen();
            if((lowerLeftCornerOfAst > y1 && lowerLeftCornerOfAst < y2) ||
                (upperLeftCornerOfAst > y1 && upperLeftCornerOfAst <y2)) {
                    count++;
                }
        } 
        if(count < 1) {
            return false;
        }
        return true;
    }


    /* Picks the row with at least 1 asteroid.  
     * Deletes the asteroids in the picked row.
     */
    public ArrayList<Asteroid> pickRow(){
        ArrayList<Asteroid> removalList = new ArrayList<>();
        int y1 = pickY1();
        int y2 = y1 + rowLength;
        while (!checkAsteroidNumberInRow(y1, y2)){
            y1 = pickY1();
            y2 = y1 + rowLength;
        }
        for(Asteroid ast : asterList.getAsteroids()) {
            int upperLeftCornerOfAst = ast.getY();
            int lowerLeftCornerOfAst = upperLeftCornerOfAst + ast.getLen();
            if((lowerLeftCornerOfAst > y1 && lowerLeftCornerOfAst < y2) ||
                (upperLeftCornerOfAst > y1 && upperLeftCornerOfAst <y2)) {
                removalList.add(ast);
            }         
        }  
        return removalList;
    }

    //removes the chosen asteroids from the asteroid list
    public void destroyPickedRow() {
        ArrayList<Asteroid> removalListOfAlien = pickRow();
        for (Asteroid asteroid : removalListOfAlien) {
            asterList.remove(asteroid);
        }
    }

    /* checks whether the cooperative alien is hit,
     * if it is hit, it sets willAppear to false,
     * so that no more cooperative alien will appear.
     */
    public void disappear() {
        if(isHit == true) {
            willAppear = false;
        }
    }

    public boolean getWillAppear() {
        return willAppear;
    }

    public void setWillAppear(boolean b) {
        willAppear = b;
    }

    @Override
    public void applyFunction() {
        destroyPickedRow();
        //System.out.println("cooperative alien is working");
       
    }


}
