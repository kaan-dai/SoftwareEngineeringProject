package domain.asteroids;

import domain.handlers.BuildingHandler;

public class Asteroid {

    public int x;
    public int y;
    public int len;
    public int width;
    public int life;
    
    public int range;
    
    public String type;
    public String color;
    public String name;
  
    public String giftType;
    public boolean giftCreated = false;

    public boolean isMoving;
    public boolean isSelected;
    public boolean isFrozen;
    public int deltaX = 1;

    public static int paddleWidth = BuildingHandler.getInstance().getWidth() / 10;


    private int centerX;
    private int centerY;


    public int getCenterX() {
        this.centerX = this.x+this.width;
        return centerX;
    }

    public void setCenterX() {
        this.centerX = this.x + this.width;
    }

    public int getCenterY() {
        this.centerY = this.y+this.width;
        return centerY;
    }

    public void setCenterY() {
        this.centerY = this.y+this.width;
    }
    
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getPaddleWidth() {
        return paddleWidth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }


    public Asteroid() {

    }

    public void action() {

    }

    public void disappear() {

    }


    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsFrozen() {
        return isFrozen;
    }

    public void setIsFrozen(boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

}
