package domain.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import domain.aliens.Alien;
import domain.aliens.AlienFactory;
import domain.aliens.AlienList;
import domain.aliens.CooperativeAlien;
import domain.aliens.RepairingAlien;
import domain.aliens.SurprisingAlien;
import domain.aliens.TimeWastingAlien;
import domain.asteroids.Asteroid;
import domain.asteroids.AsteroidList;
import domain.objects.Ball;
import domain.objects.Laser;
import domain.objects.LaserGun;
import domain.objects.Paddle;
import domain.objects.Player;
import domain.objects.Time;
import domain.powerups.PowerUp;
import domain.powerups.PowerUpFactory;
import domain.powerups.PowerUpList;
import domain.powerups.TallerPaddlePowerUp;
import domain.powerups.WrapPowerUp;
import domain.timer.AsteroidMoveTimer;
import domain.timer.BallMoveTimer;
import domain.timer.ConfusedAlienTimer;
import domain.timer.CooperativeEndTimer;
import domain.timer.EndLaserUse;
import domain.timer.MovePowerUpTimer;
import domain.timer.PowerUpEndTimer;
import domain.timer.ProtectingAlienTimer;
import domain.timer.RepairingAlienTimer;
import domain.timer.TimeWastingAlienTimer;

public class RunHandler {

    private AlienList alienList = AlienList.getInstance();
    private AlienFactory alienFactory = AlienFactory.getInstance();
    private AsteroidList asterList = AsteroidList.getInstance();
    private PowerUpList powList = PowerUpList.getInstance();
    private Time gametime = Time.getInstance();
    private Paddle paddle = Paddle.getInstance();
    private Player player = Player.getInstance();
    private LaserGun laserGun1 = new LaserGun();
    private LaserGun laserGun2 = new LaserGun();
    private Laser laser1 = new Laser();
    private Laser laser2 = new Laser();

    private static PowerUpFactory powFact = PowerUpFactory.getInstance();

    CooperativeAlien cooperativeAlien = new CooperativeAlien();
    RepairingAlien repairingAlien = new RepairingAlien();
    SurprisingAlien surprisingAlien;
    TimeWastingAlien timeWastingAlien = new TimeWastingAlien();

    private Timer timer = new Timer(true); 
    private Timer lasertimer = new Timer(true);
    private Timer powerUptimer = new Timer(true);
    private Timer coopAlienTimer = new Timer(true);
    private Timer repairingAlienTimer = new Timer(true);
    private Timer protectingMoveTimer = new Timer(true);
    private Timer timeWastingAlienTimer = new Timer(true);
    private Timer confusedAlienTimer = new Timer(true);
    
    private MovePowerUpTimer powUpMove = new MovePowerUpTimer();;
    private BallMoveTimer ballMovement = new BallMoveTimer(this);;
    private AsteroidMoveTimer asteroidMovement = new AsteroidMoveTimer(this);    
    private RepairingAlienTimer alienRepairs = new RepairingAlienTimer(repairingAlien);
    private ProtectingAlienTimer patimer = new ProtectingAlienTimer(this);

    private TimeWastingAlienTimer unfreezeAlien = new TimeWastingAlienTimer(timeWastingAlien);
    private ConfusedAlienTimer getConfused;
    
    private int maxLoc;
    private int screenHeight, screenWidth;
    private int laserList[] = new int[8];

    private ArrayList<Asteroid> removalList = new ArrayList<>();
    private ArrayList<Ball> removalBallList = new ArrayList<>();
    private ArrayList<Ball> balllist = new ArrayList<>();
    private ArrayList<PowerUp> powerUpRemoval = new ArrayList<>();
    private ArrayList<Alien> removalListOfAlien = new ArrayList<>(); 

    private boolean gameIsOver = false;
    public boolean intersected;
    private boolean magnet;
    public boolean timerRunning = true;
    private boolean usingPowerUP;
    private boolean isCoopAlienHit = false; //if a cooperative alien is hit, no cooperative alien should appear again
    public boolean isRepairingAlienHit = false; 
    public boolean wpreesed = false;
    public boolean laserUse;

    private static RunHandler instance;
   
    private RunHandler() {}

    public static RunHandler getInstance(){
        if(instance == null){
            instance = new RunHandler();
        }
        return instance;
    } 

    
    public void StartTimer() {
        if(!ballMovement.timerStarted()){
            timer = new Timer(true);
            timer.scheduleAtFixedRate(ballMovement, 0, 6);
            timer.scheduleAtFixedRate(asteroidMovement, 0, 20);
            gametime.start();
        }
        if(!powUpMove.runStarted() && powList.getListSize() > 0){
            powUpMove = new MovePowerUpTimer();
            powerUptimer = new Timer(true);
            powerUptimer.schedule(powUpMove, 0, 10);
        } 
       if(!alienRepairs.isRunning() && alienList.hasRepairing()) {
            alienRepairs = new RepairingAlienTimer(repairingAlien);
            repairingAlienTimer = new Timer(true);
            //alienRepairs.isRunning = true;
            repairingAlienTimer.scheduleAtFixedRate(alienRepairs, 0, 5000);
            System.out.println("timers are restarted after clicked on resume");
        }
        if(!unfreezeAlien.isRunning() && alienList.hasTimeWasting()) {
            unfreezeAlien = new TimeWastingAlienTimer(timeWastingAlien);
            timeWastingAlienTimer = new Timer(true);
            timeWastingAlienTimer.schedule(unfreezeAlien, 15000);
        }
        if(!patimer.isRunning() && (alienList.hasProtecting() || alienList.hasSurprising())){
            protectingMoveTimer.schedule(patimer, 0, 10);
        }
     
    }

    public void StopTimer() {
        gametime.stopTime();
        timer.cancel();
        timer.purge();
        if(powUpMove.runStarted() && powList.getListSize()>0){
            powerUptimer.cancel();
            powerUptimer.purge();
            powUpMove = new MovePowerUpTimer();
            powerUptimer = new Timer(true);
            System.out.println(alienList.getListSize());
        }
        ballMovement = new BallMoveTimer(this);
        asteroidMovement = new AsteroidMoveTimer(this);
        if (alienRepairs.isRunning() && alienList.hasRepairing()){
            repairingAlienTimer.cancel();
            repairingAlienTimer.purge();
            //alienRepairs.isRunning = false;
            System.out.println("timers cancelled because game is paused");
        } 
        if (unfreezeAlien.isRunning() && alienList.hasTimeWasting()) {
            timeWastingAlienTimer.cancel();
            timeWastingAlienTimer.purge();
        }
        if(patimer.isRunning()){
            protectingMoveTimer.cancel();
            protectingMoveTimer.purge();
            protectingMoveTimer = new Timer(true);
            patimer = new ProtectingAlienTimer(this);
        }
        
      
    }

    public long getTime() {
        if(!timerRunning) gametime.stopTime();
        return gametime.getElapsedTime();
    }

   public void setTime(int time) {
       gametime.setStopTime(time);
   }

    public int playerScoreKeeper() {
        return player.getScore(); 
    }

    public int playerHealthKeeper(){
        if(player.getHealth() <= 0 && timerRunning) {
            gameIsOver = true;
            timerRunning = false;
            return 0;
        }
        return player.getHealth();
    }

    
    
    public void setBallandPaddle(int screenWidth, int screenHeight){
        Ball ball = new Ball();
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        maxLoc = screenWidth * 27/32;
        if (balllist.isEmpty()) {

            balllist.add(ball);
            balllist.get(0).setBall(screenWidth, screenHeight);
        }

        paddle.setPaddle(screenWidth, screenHeight, maxLoc);   
    }

    public Paddle getPaddle() {
        return this.paddle;
    }

    public void setPaddle(Paddle paddle) {
        this.paddle = paddle;
    }

    //Checks the intersection between ball and paddle
    public boolean checkPaddleIntersection(Ball ball) {
        double checkX = ball.getX();
        double checkY = ball.getY();
        if (ball.getX() < paddle.getPaddleX()) checkX = paddle.getPaddleX();
        else if (ball.getX() > paddle.getPaddleX()+paddle.getWidth()) checkX = paddle.getPaddleX() + paddle.getWidth();
        if (ball.getY() < paddle.getPaddleY()) checkY = paddle.getPaddleY();
        else if (ball.getY() > paddle.getPaddleY()+paddle.getHeight()) checkY = ball.getY()+paddle.getHeight();
        double distX = ball.getX()-checkX;
        double distY = ball.getY()-checkY;
        double distance =  Math.sqrt((distX*distX) + (distY*distY));
        if (distance <= ball.getRadius()) return true;
        return false;
    }
    
    //Moves the ball in the direction of: 1 = right, 2 = left, 3 = down, 4 = up
    // 
    public void moveBall() {     
        for (Ball ballInList : balllist) {
            if(!gameIsOver) {
                ballInList.move(this);
            }
        }
    }

    public void moveProtecting() {     
        for (Alien ali : alienList.getAliens()) {
            ali.setScreenWidth(screenWidth);
            if(!gameIsOver && ali.getType().equals("Protecting")) {
                ali.applyFunction();
            } 
            try {
                if (!gameIsOver && ali.getSurprisingAlienState().equals("protecting")) {
                    ali.applyFunction();
                }
            } catch (NullPointerException e) {

            }
         
           
        }
    }

    public void asteroidBounce(Ball initBall, Asteroid aster){
               // for (Asteroid aster : AsteroidList.getInstance().getAsteroids()) {
                if (aster.type.equals("rectangle") && rectangleCheck(aster, initBall)) {
                    //Checking the distances between x's and y's to determining the direction of the ball movement
                    if(initBall.getCenterX()+initBall.getRadius() < aster.getCenterX()- aster.width/2 
                    || initBall.getCenterX()-initBall.getRadius() > aster.getCenterX() + aster.width/2)
                    {
                        initBall.setDeltaX(-(initBall.getDeltaX()));
                    } else  {
                        initBall.setDeltaY(-(initBall.getDeltaY()));
                    }
                    long time = gametime.getElapsedTime();
                    if(gametime.getElapsedTime() != 0){
                        player.setScore(time);
                    }
                    if(!aster.getIsFrozen()){
                        removalList.add(aster);
                    } else {
                        //System.out.println("this asteroid is frozen");
                    }
                   
                 
                } else if(aster.type.equals("circle") && circleCheck(aster, initBall)){
                    if(!aster.name.equals("Firm") && !aster.getIsFrozen()) {
                            removalList.add(aster);
                        long time = gametime.getElapsedTime();
                        if(gametime.getElapsedTime() != 0){
                            player.setScore(time);
                        }
                    } else if(aster.life <= 0) {
                        long time = gametime.getElapsedTime();
                        if(gametime.getElapsedTime() != 0){
                            player.setScore(time);
                        }
                        if(!aster.getIsFrozen()) {
                            removalList.add(aster);
                        }
                    } else {
                        aster.life -= aster.width/10;
                        aster.width -= 1;
                    }
    
                    if(initBall.getCenterX()+initBall.getRadius() < aster.getCenterX()- aster.width/2 
                    || initBall.getCenterX()-initBall.getRadius() > aster.getCenterX() + aster.width/2)
                    {
                        initBall.setDeltaX(-(initBall.getDeltaX()));
                    } else  {
                        initBall.setDeltaY(-(initBall.getDeltaY()));
                    }
    
    
                    //Checking the distances between x's and y's to determining the direction of the initBall movement
                    // if(Math.abs(aster.getCenterX() - initBall.getCenterX()) < Math.abs(aster.getCenterY() - initBall.getCenterY())) {              
                    //     //Checking the if the initBall is above or below the asteroid
                    //     initBall.setDeltaY(-(initBall.getDeltaY()));
                    // } else if (Math.abs(aster.getCenterX() - initBall.getCenterX()) > Math.abs(aster.getCenterY() - initBall.getCenterY()) ){
                    //     //Checking the if the initBall is on the left or right of the asteroid
                    //     initBall.setDeltaX(-(initBall.getDeltaX()));
                    // } else {
                    //     initBall.setDeltaX(bdh.calculateCornerDelta(initBall.getDeltaX()));
                    //     initBall.setDeltaY(bdh.calculateCornerDelta(initBall.getDeltaY()));
                    // }
    
                    
                }
            //System.out.println("no  using");
        }

  
    

    public void moveAsteroid() {
        // for (int i = 0; i < asterList.getListSize(); i++) {
        //     if(asterList.getAsteroids().get(i).isMoving()){
        //         if(checkAsterToAster(asterList.getAsteroids().get(i), adx)) adx = -adx;
        //         asterList.getAsteroids().get(i).setX(asterList.getAsteroids().get(i).getX() + adx);
        //     }
        // }

        for (Asteroid aster : asterList.getAsteroids()) {
            if(aster.isMoving()){
                if(checkAsterToAster(aster, aster.deltaX)) aster.deltaX = -aster.deltaX;
                if(aster.getX() < 0 || aster.getX() + aster.width > maxLoc) aster.deltaX = -aster.deltaX;
                aster.setX(aster.getX() + aster.deltaX);
            }
        }
    }
    public boolean checkAsterToAster(Asteroid a1, int dx){
        for (Asteroid aster : asterList.getAsteroids()) {
            if(!a1.equals(aster)){
                if((a1.getX() + a1.getWidth() >= aster.getX() 
                && a1.getX() + a1.getWidth() <= aster.getX() + a1.getWidth()) 
                || (a1.getX()>= aster.getX() &&a1.getX() <= aster.getX()+ aster.getWidth())){
                    return true;
                }
            }
            // if(aster.getX() < 3 + a1.getX() + a1.getWidth() && aster.getX() +aster.getWidth() >= a1.getX() &&
            //  a1.getY() + a1.getLen() >= aster.getY() && aster.getY() + aster.getLen() <= a1.getY()){
            //     // if(aster.getY())
            // }
        }
        return false;
    }

    public boolean checkAsterToAster2(int x,int y, Asteroid a1){
        boolean a = false;
        for (Asteroid aster : asterList.getAsteroids()) {
            if(x > (aster.getX() + aster.getWidth())  
            || x + a1.getWidth() < aster.getX() 
            || y + a1.getLen() < aster.getY() 
            || y > aster.getY() + aster.getLen())
            {
                return false;
            } else {
                a = true;
            }
        }
        return a;
    }

    //Checks the intersection between ball and circle typed asteroids
    public boolean circleCheck(Asteroid ast, Ball ball) {
        double distX = Math.abs(ball.getCenterX() - ast.getCenterX());
        double distY = Math.abs(ball.getCenterY() - ast.getCenterY());
        double dist = Math.sqrt((distX * distX) + (distY * distY));
        double totRadi = ball.getRadius() + ast.getLen();
        if(Math.abs(dist - totRadi) < 1) return true;
        return false;
    }

    //Checks the intersection between ball and rectangle typed asteroids
    public boolean rectangleCheck(Asteroid Ast, Ball ball) {
        int aWidth = Ast.width;
        int aHeight = Ast.len;
        int astX = Ast.x;
        int astY = Ast.y;
        if((ball.getX() <= astX + aWidth && ball.getY()<= astY + aHeight) &&
        (ball.getX() + ball.getRadius() >= astX && ball.getY() + ball.getRadius() >= astY))

        {
                return true;
        }
        return false;
    }
    

    public void removeDestroyedAsteroids() {
        for (Asteroid asteroid : removalList) {
            if(asteroid.getName().equals("Explosive")){
               // destroyPerimeter();
            } else if (asteroid.getName().equals("Gift")){
                if(asteroid.getGiftType().equals("powerup")) {
                    if(!asteroid.giftCreated) {
                        asteroid.giftCreated = true;
                        powFact.placePowerup(asteroid);
                    }
                    if(!powUpMove.runStarted()){
                        powerUptimer.schedule(powUpMove, 0, 10);
                    }
                } else if (asteroid.getGiftType().equals("alien")) {
                    if(!asteroid.giftCreated) {
                        asteroid.giftCreated = true;
                        int initSize = alienList.getListSize();
                        Alien ali = alienFactory.createAlien();
                        int currentSize = alienList.getListSize();
                        if((initSize != currentSize) && (ali != null)) { //alien appears if an alien of the same type is not present
                            alienPerforms(ali);
                        }       
                    }
                          
                }
            }
            asterList.remove(asteroid);
        }
    }

    public void catchPowerUp(PowerUp pow) {
        if(paddle.getPaddleX() + paddle.getWidth() > pow.getX() && paddle.getPaddleX() < pow.getX() + pow.getLength() && pow.getY() > paddle.getPaddleY()){
            player.addToBag(pow);
            powerUpRemoval.add(pow);
        } else if(pow.getY() + pow.getLength() > screenHeight){
            powerUpRemoval.add(pow);
        }
    }


    public void removePowerUp() {
        for (PowerUp power : powerUpRemoval) {
            powList.remove(power);
        }
    }

    public void alienPerforms(Alien currentAlien) {
        if(currentAlien.getType().equals("Cooperative")) {
            CooperativeEndTimer cet = new CooperativeEndTimer(cooperativeAlien);
            coopAlienTimer = new Timer(true);
            System.out.println("cooperative alien has appeared");
            if(!cet.isRunning()){
                coopAlienTimer.schedule(cet, 10000);
            }

        } else if (currentAlien.getType().equals("Protecting")) {
            System.out.println("protecying alien has appeared");
            if(!patimer.isRunning()) {
                protectingMoveTimer.schedule(patimer, 0, 10);
            }
        } else if (currentAlien.getType().equals("Repairing")) {
            System.out.println("repairing alien has appeared");
            if(!alienRepairs.isRunning()){
                repairingAlienTimer = new Timer(true);
                alienRepairs = new RepairingAlienTimer(repairingAlien);
                repairingAlienTimer.scheduleAtFixedRate(alienRepairs, 0, 5000);
            } 

        } else if (currentAlien.getType(). equals("Time Wasting")){
            System.out.println("time wasting alien has appeared");
            timeWastingAlien.chooseAsteroidsAndFreeze();
            if(!unfreezeAlien.isRunning()) {
                unfreezeAlien = new TimeWastingAlienTimer(currentAlien);
                timeWastingAlienTimer.schedule(unfreezeAlien, 15000);
            }
           

        } else if (currentAlien.getType().equals("Surprising")) {
            System.out.println("surprising alien has appeared");
            surprisingAlien = new SurprisingAlien();
            try {
                if(currentAlien.getSurprisingAlienState().equals("confused")) {
                    getConfused = new ConfusedAlienTimer(currentAlien);
                    if(!getConfused.isRunning()) {
                        confusedAlienTimer.schedule(getConfused, 5000);
                    }
                } else if (currentAlien.getSurprisingAlienState().equals("challenging")) {
                    currentAlien.applyFunction();
                } else if (currentAlien.getSurprisingAlienState().equals("protecting")) {
                    if(!patimer.isRunning()) {
                        protectingMoveTimer.schedule(patimer, 0, 10);
                    }
                } else if  (currentAlien.getSurprisingAlienState().equals("repairing")) {
                    RepairingAlienTimer repairingAli = new RepairingAlienTimer(repairingAlien);
                    if(!repairingAli.isRunning())
                        repairingAlienTimer.schedule(repairingAli, 0, 5000);
    
                } else if  (currentAlien.getSurprisingAlienState().equals("cooperative")) {
                    CooperativeEndTimer cet = new CooperativeEndTimer(cooperativeAlien);
                    coopAlienTimer = new Timer(true);
                    System.out.println("surprising-cooperative alien has appeared");
                    if(!cet.isRunning()){
                        coopAlienTimer.schedule(cet, 10000);
                    }
                }
            } catch (NullPointerException e) {

            }
           
        }
    }

    public void hitAlien(Ball ball) {
        for (Alien ali : alienList.getAliens()) {
            if(ali.getType().equals("Protecting")) {
                if(aliHit(ali, ball) && ali.getY() >= ball.getCenterY() + ball.getRadius()){ //will run away if it is hit by the upper side of its body
                    System.out.println("upper part hit");
                    removalListOfAlien.add(ali);
                    ali.setHit(true);
                } 
            } else if (ali.getType().equals("Repairing")) { 
                    if(aliHit(ali, ball)) {
                        removalListOfAlien.add(ali);
                        ali.setHit(true);
                        isRepairingAlienHit = true;
                    }
            } else if(ali.getType().equals("Cooperative")) {
                if(aliHit(ali, ball)) {
                    if(alienList.getAliens().get(alienList.getAliens().size() - 1).getType().equals("Cooperative")) {
                        removalListOfAlien.add(ali);
                        ali.setHit(true);
                        isCoopAlienHit =  true; //no more cooperative alien will appear
                    }
                }
            } else if (ali.getType().equals("Time Wasting")) {
                if (aliHit(ali, ball)) {
                    // if(ali.getY() <= initBall.getCenterY() + initBall.getRadius()) {
                    if(ball.getCenterX()+ball.getRadius() < ali.getCenterX()- ali.width/2 
                    || ball.getCenterX()-ball.getRadius() > ali.getCenterX() + ali.width/2)
                    {
                        ball.setDeltaX(-(ball.getDeltaX()));
                    } 
                    if(ball.getCenterY()+ball.getRadius() < ali.getCenterY() - ali.width/2 
                    || ball.getCenterY()-ball.getRadius() > ali.getCenterY() + ali.width/2) {
                        ball.setDeltaY(-(ball.getDeltaY()));
                    }
                }
    
            } else if(ali.getType().equals("Surprising")) {
                if(aliHit(ali, ball)) {
                    removalListOfAlien.add(ali);
                    ali.setHit(true);
                }
            }
        }
    }

    public boolean aliHit(Alien ali, Ball ball) {
        double checkX = ball.getX();
        double checkY = ball.getY();
        if (ball.getX() < ali.getX()) checkX = ali.getX();
        else if (ball.getX() > ali.getX()+ali.getWidth()) checkX = ali.getX() + ali.getWidth();
        if (ball.getY() < ali.getY()) checkY = ali.getY();
        else if (ball.getY() > ali.getY()+ali.getWidth()) checkY = ali.getY()+ali.getWidth();
        double distX = ball.getX()-checkX;
        double distY = ball.getY()-checkY;
        double distance =  Math.sqrt((distX*distX) + (distY*distY));
        if (distance <= ball.getRadius()) return true;
        return false;
    }   

    public void deleteAlien() {
        for (Alien ali : removalListOfAlien) {
            alienList.remove(ali);
            try {
                if(ali.getType().equals("Repairing")) {
                    repairingAlienTimer.cancel();
                    repairingAlienTimer.purge();
                    repairingAlienTimer = new Timer(true);
                    alienRepairs = new RepairingAlienTimer(repairingAlien);
                } else if(ali.getType().equals("Time Wasting")){
                    timeWastingAlienTimer.cancel();
                    timeWastingAlienTimer.purge();
                    timeWastingAlienTimer = new Timer(true);
                    unfreezeAlien = new TimeWastingAlienTimer(timeWastingAlien);
                } else if(ali.getType().equals("Protecting")){
                    protectingMoveTimer.cancel();
                    protectingMoveTimer.purge();
                    protectingMoveTimer = new Timer(true);
                    patimer = new ProtectingAlienTimer(this);
                } else if (ali.getType().equals("Surprising")) {
                    if(ali.getSurprisingAlienState().equals("confused")) {
                        confusedAlienTimer.cancel();
                        confusedAlienTimer.purge();
                        confusedAlienTimer = new Timer(true);
                        getConfused = new ConfusedAlienTimer(surprisingAlien);
                    } else if (ali.getSurprisingAlienState().equals("repairing")) {
                        repairingAlienTimer.cancel();
                        repairingAlienTimer.purge();
                        repairingAlienTimer = new Timer(true);
                    } else if (ali.getSurprisingAlienState().equals("protecting")) {
    
                    }  
                }
            } catch (NullPointerException e) {

            } 
        }
        removalListOfAlien.clear();
    }

    public void destroyPerimeter(Ball ball) {
        for(Asteroid ast: asterList.getAsteroids()){
            double distX = Math.abs(ball.getCenterX() - ast.getCenterX());
            double distY = Math.abs(ball.getCenterY() - ast.getCenterY());
            double dist = Math.sqrt((distX * distX) + (distY * distY));
            double totRadi = ball.getRadius() + ast.getLen();
            if(Math.abs(dist - totRadi) < paddle.getWidth()/2) removalList.add(ast);
        }
    }

    public void useTallerPaddle() {
        TallerPaddlePowerUp x = new TallerPaddlePowerUp();
        x.usePowerUp();
        player.getPowerUpBag().remove("T");
        TimerTask endTallerPaddle = new PowerUpEndTimer(x);
        timer.schedule(endTallerPaddle, 30000);
        usingPowerUP = true;
        

    }

    public void useWrap() {
        WrapPowerUp w = new WrapPowerUp();
        w.usePowerUp();
        player.getPowerUpBag().remove("P");
        TimerTask endWrap = new PowerUpEndTimer(w);
        timer.schedule(endWrap, 10000);
        usingPowerUP = true;
        

    }

    public void setBallsFromLoad(double x, double y) {
        Ball ball = new Ball();
        ball.setX(x);
        ball.setY(y);
        // ball.setBall( screenWidth,screenHeight);
        // moveBall();
        ball.setBall((int)x, (int)paddle.getPaddleY() + 15);
        balllist.add(ball);
        if (balllist.size()> 1) {
            setUsingPowerUP(true);
        }        
    }

    public void generateMoreBalls() {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int x = rand.nextInt(screenWidth - 200);
            Ball ball = new Ball();
            ball.setBall(x, screenHeight);
            balllist.add(ball);
        }
        player.getPowerUpBag().remove("G");
        usingPowerUP = true;
    }

    public void useLaserGun() {
        
        laser1.setX(laserGun1.getX());
        laser1.setY(laserGun1.getY());
        laser1.setWidth( laserGun1.getWidth());
        laser1.setHeight(screenHeight - (laser2.getY() - screenHeight));
        laser2.setX(laserGun2.getX());
        laser2.setY(laserGun2.getY());
        laser2.setWidth( laserGun2.getWidth());
        laser2.setHeight(screenHeight - (laser2.getY() - screenHeight));
        laserCollision(laser1, laser2);
        lasertimer = new Timer();
        EndLaserUse endlaser = new EndLaserUse();
        lasertimer.schedule(endlaser, 200);
    }   

    public void laserCollision(Laser l, Laser l2) {
        for (Asteroid aster : asterList.getAsteroids()) {
            if((l.getX() + l.getWidth() >= aster.getX() &&
            l.getX() + l.getWidth() <= aster.getX() + l.getWidth()) 
            || (l.getX()>= aster.getX() &&l.getX() <= aster.getX()+ aster.getWidth())){
                removalList.add(aster);
            }
            if((l2.getX() + l2.getWidth() >= aster.getX() &&
            l2.getX() + l2.getWidth() <= aster.getX() + l2.getWidth()) 
            || (l2.getX()>= aster.getX() &&l2.getX() <= aster.getX()+ aster.getWidth())) {
                removalList.add(aster);
            }
        }
        if (alienList.getListSize() > 0) {

            for (Alien alien : alienList.getAliens()) {
                if((l.getX() + l.getWidth() >= alien.getX() &&
                l.getX() + l.getWidth() <= alien.getX() + l.getWidth()) 
                || (l.getX()>= alien.getX() &&l.getX() <= alien.getX()+ alien.getWidth())){
                    if(alien.getType().equals("Cooperative")) isCoopAlienHit =  true; 
                    removalListOfAlien.add(alien);
                }
                if((l2.getX() + l2.getWidth() >= alien.getX() &&
                l2.getX() + l2.getWidth() <= alien.getX() + l2.getWidth()) 
                || (l2.getX()>= alien.getX() &&l2.getX() <= alien.getX()+ alien.getWidth())) {
                    if(alien.getType().equals("Cooperative")) isCoopAlienHit =  true; 
                    removalListOfAlien.add(alien);
                }
            }
        }
    }

    // public void usingLaserGun(Asteroid aster){
       
    //     return true;
    // }



    public void setLaserGun() {
        usingPowerUP = true;
        player.getPowerUpBag().remove("L");
        laserGun1.setAmmo(5);
        laserGun2.setAmmo(5);
    }

    public void removeBallFromList(Ball ball) { 
        this.removalBallList.add(ball);
    }


    public void decreaseLife() {
        player.setHealth(player.getHealth() - 1);
    }

    public void clearBalls(){
        int initBallnum = balllist.size();
        this.balllist.removeAll(removalBallList);
        if(balllist.isEmpty()) {
            setBallandPaddle(screenWidth, screenHeight);
            StopTimer();
            decreaseLife();
            timerRunning = false;
        }
        if (balllist.size()!= initBallnum && balllist.size() == 1 && usingPowerUP) {
            setUsingPowerUP(false);
        }
    }

    public void catchBall() {
        magnet = true;
        usingPowerUP = true;
    }

    public void setGunAndLaser() {
        this.laserGun1.setHeight((int)this.paddle.getWidth()/10);
        this.laserGun1.setWidth((int)this.paddle.getWidth()/25);
        this.laserGun1.setX(this.paddle.getPaddleX());
        this.laserGun1.setY(this.paddle.getPaddleY() - this.laserGun1.getHeight());

        this.laserGun2.setHeight((int)this.paddle.getWidth()/10);
        this.laserGun2.setWidth((int)this.paddle.getWidth()/25);
        this.laserGun2.setX(this.paddle.getPaddleX() + (int)this.paddle.getWidth() - (int)this.laserGun2.getWidth());
        this.laserGun2.setY(this.paddle.getPaddleY() - this.laserGun2.getHeight());
    }

    //getters and setters:

    public int[] getLaserList() {
        laserList[0] = laser1.getX();
        laserList[1] = laser1.getY();
        laserList[2] = laser1.getWidth();
        laserList[3] = laser1.getHeight();
        laserList[4] = laser2.getX();
        laserList[5] = laser2.getY();
        laserList[6] = laser2.getWidth();
        laserList[7] = laser2.getHeight();
        return laserList;

    }

    public LaserGun getLaserGun1() {
        return laserGun1;
    }

    public void setLaserGun1(LaserGun laserGun1) {
        this.laserGun1 = laserGun1;
    }

    public LaserGun getLaserGun2() {
        return laserGun2;
    }

    public void setLaserGun2(LaserGun laserGun2) {
        this.laserGun2 = laserGun2;
    }

    public boolean isUsingPowerUP() {
        return usingPowerUP;
    }

    public void setUsingPowerUP(boolean usingPowerUP) {
        this.usingPowerUP = usingPowerUP;
    }


    public HashMap<String, PowerUp> getBag() {
        return player.getPowerUpBag();
    }

    public PowerUpList getPowList() {
        return this.powList;
    }

    public AsteroidList getAstList() {
        return this.asterList;
    }

    public AlienList getAliList() {
        return this.alienList;
    }

    public int getMaxloc(){
        return this.maxLoc;
    }
    
    public boolean getMagnet(){
        return this.magnet;
    }

    public void setMagnet(boolean b){
        this.magnet = b;
    }

    public boolean getIsCoopAlienHit() {
        return isCoopAlienHit;
    }

    public void setCoopAlienHit(boolean isCoopAlienHit) {
        this.isCoopAlienHit = isCoopAlienHit;
    }

    public ArrayList<Ball> getBalllist() {
        return balllist;
    }

    public void setBalllist(ArrayList<Ball> balllist) {
        this.balllist = balllist;
    }

    public ArrayList<Alien> getRemovalListOfAlien() {
        return removalListOfAlien;
    }

    public void setRemovalListOfAlien(ArrayList<Alien> removalListOfAlien) {
        this.removalListOfAlien = removalListOfAlien;
    }

    public boolean getIsRepairingAlienHit() {
        return isRepairingAlienHit;
    }

    public void setIsRepairingAlienHit(boolean isRepairingAlienHit) {
        this.isRepairingAlienHit = isRepairingAlienHit;
    }

    public boolean getWPressed(){
        return wpreesed;
    }

    public void setWPressed(boolean wpres){
        wpreesed = wpres;
    }

	public boolean getLaserUse() {
		return laserUse;
	}
    
    public void setLaserUse(boolean laseUse) {
		this.laserUse = laseUse;
	}

    public boolean isGameIsOver() {
        return gameIsOver;
    }

    public void setGameIsOver(boolean gameIsOver) {
        this.gameIsOver = gameIsOver;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }
   
}