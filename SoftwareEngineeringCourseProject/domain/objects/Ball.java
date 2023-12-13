package domain.objects;

import domain.handlers.RunHandler;

public class Ball {
    private double initX;
    private double initY;
	private double ballX;
    private double ballY; 
    
    private boolean canMove = true;
    private double deltaX = 1;
    private double deltaY = -(1);
    
    private double centerX;
    private double centerY;
    
    private int radius = 17;
    private int screenWidth;
    private int screenHeight;

    
	public void setBall(int screenWidth, int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        init();
	}

    public void setBallsFromLoad (int x, int y, int screenHeight, int screenWidth) {
        
        // this.screenHeight = screenHeight;
        // this.screenWidth = screenWidth;
        initX = x;
        initY = y;
        this.ballX = initX;
        this.ballY = initY;

    }

    
    
    public double getCenterX() {
        return centerX;
    }
    
    public void setCenterX(double centerX) {
        this.centerX = ballX + (1/2*radius);
        this.centerX = centerX;
    }
    
    public double getCenterY() {
        this.centerY = ballY + (1/2*radius);
        return centerY;
    }
    
    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }
    
    private void init() {
        this.initX= screenWidth*1/2;
        this.initY = (screenHeight*13/16)-20;
        ballX = initX;
        ballY = initY;
    }

    
    public void resetLocation() {
        this.ballX = this.initX;
        this.ballY = this.initY;
    }
    
    public void initLocation(double x, double y) {
        this.initX = x;
        this.initY = y;
    }
    
    public double getX() {
        return this.ballX;
    }
    
    public void setX(double x) {
        this.ballX = x;
        this.centerX = ballX +(1/2*radius);
    }
    
    public double getY() {
        return this.ballY;
    }
    
    public void setY(double y) {
        this.ballY = y;
    }
    
    public int getRadius() {
        return this.radius;
    }
    
    public void setRadius(int radius) {
        this.radius = radius;
    }
    public double getDeltaX() {
        return deltaX;
    }
    
    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }
    
    public double getDeltaY() {
        return deltaY;
    }
    
    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public void move(RunHandler rhand) {
        Paddle p = Paddle.getInstance();
        // rhand.asteroidBounce(this);
        if (this.getX() < this.getRadius())	deltaX = Math.abs(deltaX);// dir=0
        if (this.getX() > rhand.getMaxloc() - this.getRadius()) deltaX = -Math.abs(deltaX); //dir = 0;
        if (this.getY() < this.getRadius()){
                deltaY = Math.abs(deltaY);
        } if (this.getY() > (screenHeight - this.getRadius())) {
            rhand.removeBallFromList(this);
        } 
        if(rhand.checkPaddleIntersection(this)) {
            if(rhand.getPaddle().getPaddleX() > this.getX() + 2 || rhand.getPaddle().getPaddleX() + rhand.getPaddle().getWidth() < this.getX() - 2)
                deltaX = - (deltaX);
            else
                deltaY = -(deltaY); 
          
        }
        if(rhand.getMagnet()) {
            // canMove = false;
            rhand.setUsingPowerUP(true);
            this.ballX =  (int)(p.getCenterX());
            this.ballY =  (int)(p.getCenterY() - (p.getHeight() + 10));
            // rhand.setMagnet(false);
            if(rhand.getWPressed()){
                System.out.println("Wpress");
                // canMove = true;
                rhand.setMagnet(false);
                rhand.setWPressed(false);
                rhand.setUsingPowerUP(false);
            }
            Player.getInstance().getPowerUpBag().remove("M");
        }
        double newX = this.getX();
        double newY = this.getY();
        if(canMove){
            newX += deltaX;
            newY += deltaY;
            this.setX(newX);
            this.setY(newY);
        }
    }
}