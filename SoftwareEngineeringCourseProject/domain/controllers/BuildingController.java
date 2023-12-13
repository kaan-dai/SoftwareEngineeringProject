package domain.controllers;

public class BuildingController {

   

    private int maxSimple = 200;
    private int maxFirm = 120;
    private int maxExplosive = 80;
    private int maxGift = 80;

    private int maxAsteroid = 480;


    private int minSimple = 75;
    private int minFirm = 10;
    private int minExplosive = 5;
    private int minGift = 10;

    private int initialAsteroidNum;

    private BuildingController() {}

    private static BuildingController instance;

    public static BuildingController getInstance() {

		if (instance == null) {
			instance = new BuildingController();
		}
		return instance;
	}

    public boolean check(String simple, String firm, String explosive, String gift) {
        //EFFECTS if an error is thrown while parsing the inputs, returns false, 
        //otherwise checks for the minimum number of asteroids, and returns true if the inputs are correct
        boolean a = false;
        try {
            if(Integer.parseInt(simple) >= minSimple &&  Integer.parseInt(firm) >= minFirm && 
             Integer.parseInt(explosive) >= minExplosive && Integer.parseInt(gift) >= minGift &&
             Integer.parseInt(simple) <= maxSimple &&  Integer.parseInt(firm) <= maxFirm && 
             Integer.parseInt(explosive) <= maxExplosive && Integer.parseInt(gift) <= maxGift && (Integer.parseInt(simple) + 
             Integer.parseInt(firm) + Integer.parseInt(explosive) + Integer.parseInt(gift)) <= 200)  {
                a = true;
            } 
        } catch (NullPointerException e) {
            a = false;

        } catch (NumberFormatException n){
            a = false;
        }
        return a;
    }
    
    public int getInitialAsteroidNum() {
        return initialAsteroidNum;
    }

    public void setInitialAsteroidNum(int num) {
        this.initialAsteroidNum = num;
    }

    public int getminSimple() {
        return this.minSimple;
    }
    public int getminFirm() {
        return this.minFirm;
    }
    public int getminExplosive() {
        return this.minExplosive;
    }
    public int getminGift() {
        return this.minGift;
    }

    public int getmaxSimple() {
        return this.maxSimple;
    }
    public int getmaxFirm() {
        return this.maxFirm;
    }
    public int getmaxExplosive() {
        return this.maxExplosive;
    }
    public int getmaxGift() {
        return this.maxGift;
    }
    public int getMaxAsteroid() {
        return this.maxAsteroid;
    }
}
