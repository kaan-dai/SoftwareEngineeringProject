package domain.powerups;

/**
 * PowerUp
 */

public abstract class PowerUp {
    String type;
    int x;
    int y;
    int deltaY = 1;
    int remainingAttemps;
    int length;
    boolean isMoving;

    public boolean isMoving() {
        return isMoving;
    }
    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
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
    public int getRemainingAttemps() {
        return remainingAttemps;
    }
    public void setRemainingAttemps(int remainingAttemps) {
        this.remainingAttemps = remainingAttemps;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }

    public void movePowerUp() {
        this.y += deltaY;
        
    }

    public abstract void usePowerUp();
    public abstract void endPowerUp();
    
}