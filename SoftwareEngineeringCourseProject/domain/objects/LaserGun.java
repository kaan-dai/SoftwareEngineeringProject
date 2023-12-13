package domain.objects;

public class LaserGun {

    private int x;
    private int y;
    private int width;
    private int height;
    private int ammo;

    private boolean isVisible;

    public LaserGun() {
        this.ammo = 5;
    }

    public void shoot() {
        this.ammo--;
    }
    public boolean isVisible() {
        return isVisible;
    }


    public void setVisible(boolean isVisable) {
        this.isVisible = isVisable;
    }
    

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
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

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    

    
    
    
}
