package domain.objects;

public class Paddle {


    int width, height;
    int paddleX, paddleY, maxLoc;
    int minLoc = 0;

    private boolean isWraped;

    private static Paddle instance;

    public Paddle() {}

    public void setPaddle(int screenWidth, int screenHeight, int maxloc) {
        this.width = screenWidth/10;
        this.height = 20;
        this.paddleX = screenWidth*1/2 - width;
        this.paddleY = screenHeight*13/16;
        this.maxLoc = maxloc;
    }

    public static Paddle getInstance() {
        if (instance == null) {
            instance =  new Paddle();
        }
        return instance;
    }

    public boolean isWraped() {
        return this.isWraped;
    }
    public void setIsWrapped(Boolean iswrapped) {
        this.isWraped = iswrapped;
    }
    
    public boolean checkRightBoundary(){
        if(paddleX + width >maxLoc){
            return true;
        }
        return false;
    }
    
    public boolean checkLeftBoundary(){
        if(paddleX-2<0){
            return true;
        }
        return false;
    }

    public double getCenterX() {
        return paddleX+(getWidth()/2);
    }
    
    public double getCenterY() {
        return paddleY+(getHeight()/2);
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPaddleX() {
        return this.paddleX;
    }

    public void setPaddleX(int paddleX) {
        this.paddleX = paddleX;
    }

    public int getPaddleY() {
        return this.paddleY;
    }

    public void setPaddleY(int paddleY) {
        this.paddleY = paddleY;
    }
    	
    public void move(int dir) {
        //@Effects: This function moves the paddle acording to given input "dir"
        //If dir is 1 paddle moves to left 15 units
        //If dir is 2 paddle moves to right 15 units

        //@Modifies: This function changes the x value of the paddle instance

        //@Requires: For this function to work properly dir should be 1 or 2 otherwise paddle does not move
        // Paddle instance must be initilized 

        switch (dir) {
            case 1:
                if(checkLeftBoundary()) return;
                this.paddleX -=5;
                break;
            case 2:
                if(checkRightBoundary()) return;
                this.paddleX +=5;
                break;
            default:
                // System.err.println("Invalid direction");
                break;
        }
    }

    public void moveWithWrap(int dir) {

        switch (dir) {
            case 1:
                
                this.paddleX -=5;
                if (this.getPaddleX() + this.getWidth() < 0) {
                    this.setPaddleX(maxLoc- (int)this.getWidth());
                }
                break;
                case 2:
                
                this.paddleX +=5;
                if(this.getPaddleX() > maxLoc) {
                    this.setPaddleX(0);
                }
                break;
            default:
                // System.err.println("Invalid direction");
                break;
        }

    }
}