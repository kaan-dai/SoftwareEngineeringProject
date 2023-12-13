package domain.asteroids;

import java.util.ArrayList;

import ui.buildingmodescreen.BuildingFrame;

/**
 * AsteroidList
 */
public class AsteroidList {

    //Overview: AsteoidList is class which is used for storing asteroids in a general asteroid list
    // it can be modified by AsteroidFactory and RunHandler

    //The abstraction function is
    //AF(C) = {C.asteroids[i] | 0<= i <= C.asteroids.size}

    //The rep invarient is
    //C.asteroids is not null &&
    //all elements in C.asteroids are Asteroid &&
    // life of all elements in C.asteroids have life bigger than 0
    



    private int simple;
    private int firm;
	private int explosive;
	private int gift;
    private static ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>(); // the rep

    private static AsteroidList instance; 


    private AsteroidList() {}
    
	
    public static AsteroidList getInstance() {
        if (instance == null) {
            instance = new AsteroidList();
            return instance;
        }
        return instance;
    }

    public void removeAll() {
        asteroids.removeAll(asteroids);
    }

    public void addAsteroid(Asteroid a) throws NullPointerException {
        if(a == null) {
            throw new NullPointerException();
        }
        asteroids.add(a);
    }

    public  ArrayList<Asteroid> getAsteroids() {
        return asteroids;
        
    }

    public void remove(Asteroid a) throws NullPointerException {
        if(a == null) {
            throw new NullPointerException();
        }
        asteroids.remove(a);
    }

    public int getListSize() {
        return asteroids.size();
    }

    public void getNumber() {
        //@Effects: this function increments the number of different asteroid types by iterating over the asteroids list

        for (Asteroid a : asteroids) {
            if (a.name == "Simple") {
                this.simple ++;

            } else if (a.name == "Firm") {
                this.firm++;

            } else if (a.name == "Explosive") {
                this.explosive++;

            } else if (a.name == "Gift") {
                this.gift++;
            }
            
        }

    }

    public boolean repOk() {
        //@Effects: this checks if the representation of the AsteroidList and the asteroids it contains 
        //is valid with respect to requirements such as asteroidsList shoul not be null and differeent asteroid
        // types has different size measurements
        if(instance == null) {
            return false;
        } 

        for (Asteroid a : asteroids) {
            if (a.life <= 0) {
                return false; 
            } else if (a.name == "Simple" && a.len != 20) {
                return false;
            } else if (a.name == "Firm" && a.width != a.life + 10) {
                return false;
            } else if (a.name == "Explosive" && a.getWidth() != 10) {
                return false;
            } else if (a.name == "Gift" && a.len != 20) {
                return false;
            }
        }
        return true;
    }

    public boolean inList(Asteroid ast){
        return asteroids.contains(ast);
    }

    public boolean isInFrame(Asteroid ast){
        if(ast.getX() < 0 || ast.getX() > BuildingFrame.width || ast.getY() < 0 || ast.getY() > BuildingFrame.height) {
            return false;
        }
        else {
            return true;
        }
    }



    //Getters and Setters 
    public int getSimple() {
        return simple;
    }

    public int getNumSimple() {
        int i = 0;

        for (int k = 0; k < asteroids.size(); k++) {
            if (asteroids.get(k).getName().equals("Simple")) {
                i++;
            }
        }
        return i;
    }
    public int getNumFirm() {
        int i = 0;

        for (int k = 0; k < asteroids.size(); k++) {
            if (asteroids.get(k).getName().equals("Firm")) {
                i++;
            }
        }
        return i;
    }
    public int getNumExplosive() {
        int i = 0;

        for (int k = 0; k < asteroids.size(); k++) {
            if (asteroids.get(k).getName().equals("Explosive")) {
                i++;
            }
        }
        return i;
    }
    public int getNumGift() {
        int i = 0;

        for (int k = 0; k < asteroids.size(); k++) {
            if (asteroids.get(k).getName().equals("Gift")) {
                i++;
            }
        }
        return i;
    }

    public void setSimple(int simple) {
        this.simple = simple;
    }

    public int getFirm() {
        return firm;
    }

    public void setFirm(int firm) {
        this.firm = firm;
    }

    public int getExplosive() {
        return explosive;
    }

    public void setExplosive(int explosive) {
        this.explosive = explosive;
    }

    public int getGift() {
        return gift;
    }

    public void setGift(int gift) {
        this.gift = gift;
    }

    
    
}