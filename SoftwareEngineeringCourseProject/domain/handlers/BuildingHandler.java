package domain.handlers;

import java.util.ArrayList;
import java.util.Random;

import domain.asteroids.Asteroid;
import domain.asteroids.AsteroidFactory;
import domain.asteroids.AsteroidList;


public class BuildingHandler {

    AsteroidList aList = AsteroidList.getInstance();

    String numSimple;
    String numFirm;
    String numExp;
    String numGif;

   // public boolean selectedCheck = true;
    public static int width;
    public static int height;

    

    //public static ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();

    private static BuildingHandler instance;
    
    AsteroidFactory astFactory = AsteroidFactory.getInstance();

    private BuildingHandler() {}

    public static BuildingHandler getInstance() {
        if(instance == null){
            return new BuildingHandler();
        }
        return instance; 
    }


    public void setNumbers(String string1, String string2, String string3,String string4) {
        numSimple = string1;
        numFirm = string2;
        numExp = string3;
        numGif = string4;   
    }

    public  void setWidthAndHeight(int w, int h) {
        width = w;
        height = h;
    }

    
    

    public String getnumSimple() {
        return this.numSimple;
    }
    public String getnumFirm() {
        return this.numFirm;
    }
    public String getnumExp() {
        return this.numExp;
    }
    public String getnumGif() {
        return this.numGif;
    }

    public ArrayList<Asteroid> getAsteroidList() {
        return aList.getAsteroids();
    }

    public void createSimpleH() {
      for(int i = 0; i < Integer.parseInt(getnumSimple()); i++) {
            astFactory.createSimple(); 
      }
    }

    public void createFirmH() {
        for(int i = 0; i < Integer.parseInt(getnumFirm()); i++) {
            astFactory.createFirm();
        }  
    }

    public void createExplosiveH() {
        for(int i = 0; i < Integer.parseInt(getnumExp()); i++) {
            astFactory.createExplosive(); 
        }
    }

    public void createGiftH() {
        int giftAstNum = (int) Integer.parseInt(getnumGif());
        int currPowerUpNum = 0;
        int powerUpNum = (int) (Integer.parseInt(getnumGif())  * 0.6);
        for(int i = 0; i < giftAstNum; i++) {
            Asteroid ast = astFactory.createGift(); 
            if(currPowerUpNum < powerUpNum) {
                ast.setGiftType("powerup");
                currPowerUpNum++;
            } else {
                ast.setGiftType("alien");
            }
        }
    }

    public void createOneAsteroid(String string) {
        switch(string){
            case "simple":
                astFactory.createSimple();
                break;
            case "firm":
                astFactory.createFirm();
                break;
            case "gift":
                Asteroid ast = astFactory.createGift();
                Random rand = new Random();
                int randomNum = rand.nextInt(2);
                if(randomNum == 0){
                    ast.setGiftType("powerup");
                } else {
                    ast.setGiftType("alien");
                }
                break;
            case "explosive":
                astFactory.createExplosive();
                break;
            default:
                break;     
        }
    }

    // //false
    // public static boolean checkOverlap(Asteroid newAst, int newX, int newY) {
    //     boolean checked = false;
    //     int aWidth = newAst.width;
    //     int aHeight = newAst.len;
    //     for (Asteroid ast: asteroids) {
    //         int checkX = ast.x;
    //         int checkY = ast.y;
    //         if (ast.x < newX) checkX = newX;
    //         else if (ast.x > newX+aWidth) checkX = newX + aWidth;
    //         if (ast.y < newY) checkY = newY;
    //         else if (ast.y > newY+aHeight) checkY = ast.y + aHeight;
    //         int distX = ast.x-checkX;
    //         int distY = ast.y-checkY;
    //         int distance = (int)  Math.sqrt((distX*distX) + (distY*distY));
    //         if (distance <= ast.width + 20) {
    //             return true;
    //         } 
    //     }
    //     return checked;
    // }

    // public static boolean checkOverlapRect(Asteroid newAst, int newX, int newY) {
    //     boolean checked = true;
    //     int aWidth = newAst.width;
    //     // int aHeight = newAst.len;
    //     for (Asteroid ast: asteroids) {
    //         if(newX < ast.x + (2*ast.width)  && newX + (aWidth*2) > ast.getX() &&
    //             newY < ast.y + (2*ast.width) && newY + (2*width) > ast.getY()){
    //             checked = false;
    //         }
    //     }
    //     return checked;
    // }

    public boolean checkMouseOverlap(Asteroid newAst, int mouseX, int mouseY) {
        //@Effects: This function tells whether the asteroids are present or not by mouse click according to given input "distance".
        //If distance is smaller or equal to 2, an asteroid is present in the adress.
        //If distance is bigger than 2, an asteroid is not present in the adress.

        //@Modifies: This function changes the value of astX and astY in order to check.

        //Requires: For this function to work properly, all of the inputs should be different from null.
        int aWidth = newAst.width;
        int aHeight = newAst.len;
        int astX = newAst.getX();
        int astY = newAst.getY();
        double checkX = mouseX;
        double checkY = mouseY;
        if (mouseX < astX) checkX = astX;
        else if (mouseX > astX+aWidth) checkX = astX + aWidth;
        if (mouseY < astY) checkY = astY;
        else if (mouseY > astY+aHeight) checkY = mouseY+aHeight;
        double distX = mouseX-checkX;
        double distY = mouseY-checkY;
        double distance =  Math.sqrt((distX*distX) + (distY*distY));
        if (distance <= 2) return true;
        return false;
    }

    public boolean asteroidIntersection(int x,int y, Asteroid a1){
        boolean a = false;
        for (Asteroid aster : aList.getAsteroids()) {
            if(x > (aster.getX() + aster.getWidth())  
            || x + a1.getWidth() < aster.getX() 
            || y + a1.getLen() < aster.getY() 
            || y > aster.getY() + aster.getLen())
            {
                a = true;
            } else {
               return false;
            }
        }
        return a;
    }



    public static void move (Asteroid ast, int mouseX, int mouseY) {
        ast.setX(mouseX);
        ast.setY(mouseY);

    }

    public boolean selectedCheck() {
        //@Effeects checks if one asteroid is selected. If so another asteroid can not be moved in the building mode
        for(Asteroid ast : aList.getAsteroids()){
            if(ast.getIsSelected() == true) return false; 
        }
        return true;
    }

    public int getWidth() {
        return width;
    }


    public static void setWidth(int width) {
        BuildingHandler.width = width;
    }


    public int getHeight() {
        return height;
    }


    public static void setHeight(int height) {
        BuildingHandler.height = height;
    }


}