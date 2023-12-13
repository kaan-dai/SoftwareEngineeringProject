package domain.asteroids;
import java.util.ArrayList;
import java.util.Random;

import domain.handlers.BuildingHandler;

public class AsteroidFactory {

    //Overview:
    // Asteroid factory is a singleton class which is responsable for creating different asteroids and adding 
    // them to the asteroid list
    // The asteroids list is mutable and can be modified by asteroid factory class

    static Random rand = new Random();
    AsteroidList aList = AsteroidList.getInstance();
    

    private static AsteroidFactory instance;

    public static AsteroidFactory getInstance() {
        if (instance == null) {
            instance =  new AsteroidFactory();
        }
        return instance;
    }

    private AsteroidFactory() {}

    public Asteroid createSimple() {

        Asteroid a = new SimpleAsteroid();
        int x = findRandomX();
        int y = findRandomY();
        while(checkAsteroidBoundaries(a, x, y)){
            x = findRandomX();
            y = findRandomY();
        }
        a.setMoving(isMovingProb());
        a.setX(x);
        a.setY(y);
        a.setCenterX();
        a.setCenterY();  
        aList.addAsteroid(a);
        return a;
    }

    public void createSimpleFromLoad(Boolean isMoving, String giftType, int life, int x, int y) {
        Asteroid a = new SimpleAsteroid();
        a.setMoving(isMoving);
        a.setGiftType(null);
        a.setLife(life);
        a.setX(x);
        a.setY(y);
        aList.addAsteroid(a);

    }

    public void createFirmFromLoad(Boolean isMoving, String giftType, int life, int x, int y) {
        Asteroid a = new FirmAsteroid();
        a.setMoving(isMoving);
        a.setGiftType(null);
        a.setLife(life);
        a.setX(x);
        a.setY(y);
        aList.addAsteroid(a);
    }
    public void createExplosiveFromLoad(Boolean isMoving, String giftType, int life, int x, int y) {
        Asteroid a = new ExplosiveAsteroid();
        a.setMoving(isMoving);
        a.setGiftType(null);
        a.setLife(life);
        a.setX(x);
        a.setY(y);
        aList.addAsteroid(a);
    }
    public void createGiftFromLoad(Boolean isMoving, String giftType, int life, int x, int y) {
        Asteroid a = new GiftAsteroid();
        a.setMoving(isMoving);
        a.setGiftType(giftType);
        a.setLife(life);
        a.setX(x);
        a.setY(y);
        aList.addAsteroid(a);
    }

    public void createFromLoad(int simple, int firm, int explosive, int gift, ArrayList<String> coordinates) {
        int i = 0;
        int s = 1;
        
        // System.out.println("simple: "  + coordinates.get(s));
        for(i = 0; i < simple; i++) {
            Asteroid a = new SimpleAsteroid();
            a.setLife(Integer.parseInt(coordinates.get(s)));
            s++;
            a.setX(Integer.parseInt(coordinates.get(s)));
            s++;
            a.setY(Integer.parseInt(coordinates.get(s)));
            s++;
            a.setCenterX();
            a.setCenterY();  
            s++;
            aList.addAsteroid(a);
        }
    
        //  System.out.println("firm: "  + coordinates.get(s));
        for (i = 0; i < firm; i ++) {
            Asteroid a = new FirmAsteroid();
            a.setLife(Integer.parseInt(coordinates.get(s)));
            s++;
            a.setX(Integer.parseInt(coordinates.get(s)));
            s++;
            a.setY(Integer.parseInt(coordinates.get(s)));
            s++;
            a.setCenterX();
            a.setCenterY();  
            s++;
            aList.addAsteroid(a);
        }
        // System.out.println("exp: "  + coordinates.get(s));
        for (i = 0; i < explosive; i++) {
            Asteroid a = new ExplosiveAsteroid();
            a.setLife(Integer.parseInt(coordinates.get(s)));
            s++;
            a.setX(Integer.parseInt(coordinates.get(s)));
            s++;
            a.setY(Integer.parseInt(coordinates.get(s)));
            s++;
            a.setCenterX();
            a.setCenterY();  
            s++;
            aList.addAsteroid(a);
    
        }
        s--;
            
        // System.out.println("gift: "  + coordinates.get(s));
        for (i = 0; i < gift; i++) {
            Asteroid a = new GiftAsteroid();
            a.setGiftType(coordinates.get(s));
            s++;
            a.setLife(Integer.parseInt(coordinates.get(s)));
            s++;
            a.setX(Integer.parseInt(coordinates.get(s)));
            s++;
            a.setY(Integer.parseInt(coordinates.get(s)));
            s++;
            a.setCenterX();
            a.setCenterY();  
            aList.addAsteroid(a);
        }
    }


    public Asteroid createFirm() {
        Asteroid a = new FirmAsteroid();
        int x = findRandomX();
        int y = findRandomY();
        while(checkAsteroidBoundaries(a, x, y)){
            x = findRandomX();
            y = findRandomY();
        }
        a.setX(x);
        a.setY(y);
        a.setCenterX();
        a.setCenterY(); 
        a.setMoving(false);
        aList.addAsteroid(a);
        return a;
    }

    public Asteroid createExplosive() {
        Asteroid a = new ExplosiveAsteroid();
        int x = findRandomX();
        int y = findRandomY();
        while(checkAsteroidBoundaries(a, x, y)){
            x = findRandomX();
            y = findRandomY();
        }
        a.setMoving(false);
        a.setX(x);
        a.setY(y);       
        a.setCenterX();
        a.setCenterY(); 
        aList.addAsteroid(a);
        return a;   
    }

    public Asteroid createGift() {
        Asteroid a = new GiftAsteroid();
        int x = findRandomX();
        int y = findRandomY();
        while(checkAsteroidBoundaries(a, x, y)){
            x = findRandomX();
            y = findRandomY();
        }
        a.setMoving(false);
        a.setX(x);
        a.setY(y);       
        a.setCenterX();
        a.setCenterY(); 
        aList.addAsteroid(a);
        return a;   
    }



    public boolean checkAsteroidBoundaries(Asteroid newelement, int x, int y) {
        boolean check = false;
        for(Asteroid asteroid: aList.getAsteroids()){
            int elementW = newelement.getWidth()/2;
            int elementH = newelement.getLen()/2;
            int elementX = x + elementW;
            int elementY = y + elementH;
            int asteroidY = asteroid.getCenterY();
            int asteroidX = asteroid.getCenterX();
            int asteroidW = asteroid.width/2;
            int asteroidH = asteroid.len/2;
            double distX = elementX-asteroidX;
            double distY = elementY-asteroidY;
            double distance =  Math.sqrt((distX*distX) + (distY*distY));
            double lenSum = 20 +Math.sqrt((elementH*elementH) + (elementW*elementW)) +  Math.sqrt((asteroidW*asteroidW) + (asteroidH*asteroidH));
            if (distance < lenSum) check = true; 
        }
        return check;
    }

    public boolean isMovingProb() {
        int isMoving = rand.nextInt(10);
        if(isMoving > 1) return false;
        return true;
    }

    public static int findRandomX() {
        int x = rand.nextInt(BuildingHandler.width - 300) ;
        return x;
    }

    public static int randomXSecond() {
        int x =  rand.nextInt(BuildingHandler.width/2+ 150);
        return x;
    }

    public static int findRandomY() {
        int y = rand.nextInt(BuildingHandler.height/2 + 200);
        return y;
    }

    public static int randomYSecond() {
        int y = rand.nextInt(BuildingHandler.height - 400);
        return y;
    }

}
