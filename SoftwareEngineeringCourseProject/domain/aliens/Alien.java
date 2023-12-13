package domain.aliens;

import domain.handlers.BuildingHandler;

public abstract class Alien {

    public String type;
    public String surprisingAlienState;

    public boolean isMoving;
    public boolean isHit;

    public int x;
    public int y;
    public int centerX;
    public int centerY;
    public int deltaX = 1;
    public int width = 50;
    public int screenWidth = BuildingHandler.width; 

    public static int paddleWidth = BuildingHandler.width / 10; //to use in protecting alien's speed:
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width= width;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public boolean getIsHit() {
        return isHit;
    }

    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.centerX = x + getWidth() / 2;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.centerY = y + getWidth() / 2;
        this.y = y;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public String getSurprisingAlienState() {
        return surprisingAlienState;
    }

    public void setSurprisingAlienState(String surprisingAlienState) {
        this.surprisingAlienState = surprisingAlienState;
    }

    public abstract void applyFunction();



   

    

    
    
}
