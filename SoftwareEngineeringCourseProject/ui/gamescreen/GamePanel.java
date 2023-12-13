package ui.gamescreen;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import domain.aliens.Alien;
import domain.asteroids.Asteroid;
import domain.handlers.RunHandler;
import domain.powerups.PowerUp;
import ui.Images;


public class GamePanel extends JPanel implements KeyListener, ActionListener {

    static final int framesPerSecond = 60;
    static final int delay = 1000 / framesPerSecond;
    private boolean paused = false;
    private javax.swing.Timer timer;
    private RunHandler rHandler = RunHandler.getInstance();
    int width, height;
    // BuildingHandler gh = BuildingHandler.getInstance();

    
    Rectangle ballRect; 
    Rectangle paddle; 
    Rectangle asteroid;

    Rectangle laserGun1;
    Rectangle laserGun2;
    
    JLabel playerScore;
    JLabel playerHealth;
    JLabel time;
    JLabel warning;
    JButton pauseButton;
    
    PausePanel pausePanel;
    GameFrame frame;
    
    static BufferedImage background;
    static BufferedImage simpleImage;
    static BufferedImage firmImage;
    static BufferedImage expImage;
    static BufferedImage giftImage;
    static BufferedImage generalPowerUp;
    static BufferedImage heart;
    static BufferedImage laserGun;
    static BufferedImage wrap;
    static BufferedImage magnet;
    static BufferedImage longPaddle;
    static BufferedImage gob;
    static BufferedImage tallerp;
    static BufferedImage alien1;
    static BufferedImage alien2;
    static BufferedImage alien3;
    static BufferedImage alien4;
    static BufferedImage alien5;
    static BufferedImage laser1;
    static BufferedImage laser2;
    static BufferedImage iceImage;
    static BufferedImage iceImageCirc;
    static BufferedImage gameOver;




    int paddleDir;
    boolean moveOverRidden = false;
    
    public GamePanel(Dimension d, GameFrame gameFrame) {        
        this.width = (int)d.getWidth();
        this.height = (int)d.getHeight();
        this.setSize(d);
        initialize();
        setImages();        
        setPause(d, gameFrame);
    }

    public void initialize(){
        this.time = new JLabel();
        this.playerHealth = new JLabel();
        this.playerScore = new JLabel();
        this.time.setForeground(Color.CYAN);
        this.playerHealth.setForeground(Color.CYAN);
        this.playerScore.setForeground(Color.CYAN);
        this.add(time);
        this.add(playerHealth);
        this.add(playerScore);
        rHandler.timerRunning = false;
        rHandler.setBallandPaddle(width, height);
        timer = new javax.swing.Timer(delay, this);
        timer.addActionListener(this);
        timer.start();
        this.addKeyListener(this);
        // rHandler.StartTimer();
    }

    public void setPause(Dimension d, GameFrame g){
        this.frame = g;
        this.pausePanel = new PausePanel(d, g);
        this.pausePanel.setBounds(width/3,height/3,width/3,height/3);
        this.pausePanel.setFocusable(false);
        this.pauseButton = new JButton();
        this.pauseButton.setFocusable(false);
        this.pauseButton.setText("Pause");
        this.pauseButton.addActionListener(this);
        this.warning = new JLabel();
        this.warning.setForeground(Color.WHITE);
        this.warning.setFocusable(false);
        this.warning.setVisible(false);
        this.warning.setText("WARNING: THE GAME IS PAUSED \n PLEASE PRESS W TO CONTINUE!");
        this.add(pauseButton);
    }

    public void pauseGame() {
        timer.stop();
        paused = true;
        rHandler.StopTimer();
        rHandler.timerRunning = false; 
        this.frame.removePanel(this);

    }

    public void resumeGame() {
        timer.restart();
        rHandler.timerRunning = true;
        paused = false;
        // frame.addPanel();
        this.warning.setVisible(false);
        this.remove(pausePanel);
    }

    public void setImages(){
        Images.setImages();
        background = Images.getImageFileList(0);
        simpleImage = Images.getImageFileList(1);
        firmImage = Images.getImageFileList(2);
        expImage = Images.getImageFileList(3);
        giftImage = Images.getImageFileList(4);
        generalPowerUp = Images.getImageFileList(10);
        wrap = Images.getImageFileList(11);
        heart = Images.getImageFileList(12);
        laserGun = Images.getImageFileList(13);
        magnet = Images.getImageFileList(14);
        gob = Images.getImageFileList(15);
        tallerp = Images.getImageFileList(16);
        alien1 =Images.getImageFileList(17);
        alien2 =Images.getImageFileList(18);
        alien3 =Images.getImageFileList(19);
        alien4 =Images.getImageFileList(20);
        alien5 =Images.getImageFileList(21);
        laser1 =Images.getImageFileList(22);
        laser2 =Images.getImageFileList(23);
        iceImage = Images.getImageFileList(24);
        iceImageCirc = Images.getImageFileList(25);
        gameOver = Images.getImageFileList(26);
    }
    
    public void paintComponent (Graphics g) { 
        super.paintComponent(g); 
        if(!rHandler.isGameIsOver()){
            rHandler.removeDestroyedAsteroids();
            this.time.setBounds(width-200, 20, 200, 30);
            this.playerScore.setBounds(width-200, 50, 200, 30);
            this.playerHealth.setBounds(width-200, 80, 200, 30); 
            this.pauseButton.setBounds(width - 200, 110, 100, 30);
            this.warning.setBounds(rHandler.getPaddle().getPaddleX(), rHandler.getPaddle().getPaddleY() + 50, 100, 100);
            Graphics2D g2 = (Graphics2D) g;
            g2.drawImage(background, 0, 0,width,height, null);
            drawPaddleAndBall(g2);
            drawAsteroids(g2);
            drawPowerUps(g2);
            drawAlien(g2);
            drawLaserGuns(g2);
        } else {
            g.drawImage(gameOver, 0, 0, width, height,null);
        }
    }

    public void drawPaddleAndBall(Graphics2D g2){
        paddle = new Rectangle(rHandler.getPaddle().getPaddleX(), rHandler.getPaddle().getPaddleY(), (int) rHandler.getPaddle().getWidth(), (int) rHandler.getPaddle().getHeight());
        g2.setColor(Color.RED);
        g2.fillRect(rHandler.getPaddle().getPaddleX(), rHandler.getPaddle().getPaddleY(), (int) rHandler.getPaddle().getWidth(), (int) rHandler.getPaddle().getHeight());
    }

    public void drawLaserGuns(Graphics2D g2) {
        if(rHandler.getLaserGun1().isVisible() && rHandler.getLaserGun2().isVisible() && rHandler.getLaserGun1().getAmmo()>0 && rHandler.getLaserGun2().getAmmo()>0) {
            rHandler.setGunAndLaser();
            laserGun1 = new Rectangle(rHandler.getLaserGun1().getX(),rHandler.getLaserGun1().getY(),(int) rHandler.getLaserGun1().getWidth(),(int) rHandler.getLaserGun1().getHeight());
            g2.setColor(Color.BLUE);
            g2.fillRect(rHandler.getLaserGun1().getX(),rHandler.getLaserGun1().getY(),(int) rHandler.getLaserGun1().getWidth(),(int) rHandler.getLaserGun1().getHeight());
            rHandler.setGunAndLaser();
            laserGun2 = new Rectangle(rHandler.getLaserGun2().getX(),rHandler.getLaserGun2().getY(),(int) rHandler.getLaserGun2().getWidth(),(int) rHandler.getLaserGun2().getHeight());
            g2.setColor(Color.BLUE);
            g2.fillRect(rHandler.getLaserGun2().getX(),rHandler.getLaserGun2().getY(),(int) rHandler.getLaserGun2().getWidth(),(int) rHandler.getLaserGun2().getHeight());
        }
    }



    public void drawPowerUps(Graphics g2){
        for (PowerUp pow : rHandler.getPowList().getPowerUps()) {
            // g2.drawImage(generalPowerUp, ,10,10,  null);
            switch(pow.getType()) {
                case "Magnet":
                    g2.drawImage(magnet, pow.getX(), pow.getY(), pow.getLength(),pow.getLength(), null);
                    break;
                case "DLG":
                    g2.drawImage(laserGun, pow.getX(), pow.getY(), pow.getLength(),pow.getLength(), null);
                    break;
                case "GOB":
                    g2.drawImage(gob ,pow.getX(), pow.getY(), pow.getLength(),pow.getLength(), null);
                    break;
                case "Wrap":
                    g2.drawImage(wrap, pow.getX(), pow.getY(), pow.getLength(),pow.getLength(), null);
                    break; 
                case "Chance":
                    g2.drawImage(heart, pow.getX(), pow.getY(), pow.getLength(),pow.getLength(), null);
                    break;
                case "TallerP":
                    g2.drawImage(tallerp, pow.getX(), pow.getY(), pow.getLength(),pow.getLength(), null);
                    break;  
                default:
                    g2.drawImage(generalPowerUp, pow.getX(), pow.getY(), pow.getLength(),pow.getLength(), null);
                    break;
            }
            rHandler.catchPowerUp(pow);
        }
        rHandler.removePowerUp();

        for (PowerUp pow: rHandler.getBag().values()) {
            switch(pow.getType()) {
                case "Magnet":
                    g2.drawImage(magnet, width- (width/20), height/2, 50, 50, null);
                    break;
                case "DLG":
                    g2.drawImage(laserGun, width- (width/20), height/2 + 50, 50, 50, null);
                    break;
                case "GOB":
                    g2.drawImage(gob, width- (width/20), height/2 + 100, 50, 50, null);
                    break;
                case "Wrap":
                    g2.drawImage(wrap, width- (width/20), height/2 + 150, 50, 50, null);
                    break; 
                case "TallerP":
                    g2.drawImage(tallerp, width- (width/20), height/2 + 200, 50, 50, null);
                    break;
                default:
                    g2.drawImage(generalPowerUp, width- (width/20), height/2 + 250, 50, 50, null);
                    break;
            }

        }
    }

    public void drawAsteroids(Graphics g2) {
        try{
            for (Asteroid a : rHandler.getAstList().getAsteroids()) {
                
                for (domain.objects.Ball ball : rHandler.getBalllist()) {
                    // if(rHandler.getAliList().getAliens().isEmpty()){
                        ballRect = new Rectangle((int)ball.getX(),(int) ball.getY(), ball.getRadius(), ball.getRadius());
                        g2.setColor(Color.MAGENTA);        
                        g2.fillOval((int)ball.getX(),(int) ball.getY(), ball.getRadius(), ball.getRadius());
                        rHandler.asteroidBounce(ball, a);
                        rHandler.hitAlien(ball);
                    // }
                    rHandler.hitAlien(ball);
                }
                rHandler.deleteAlien();
                // Checks the intersection of asteroids while the ball is moving 
                asteroid = new Rectangle(a.getX(), a.getY(),a.getWidth(),a.getLen()); 
                rHandler.clearBalls();
                if(rHandler.getLaserUse()) {
                    // rHandler.useLaserGun();
                    int[] laserList = rHandler.getLaserList();
                    g2.drawImage(laser1, laserList[0],  laserList[1] - laserList[3],  laserList[2],  laserList[3], null);
                    g2.drawImage(laser1, laserList[4],  laserList[5] - laserList[7],  laserList[6],  laserList[7], null);
                }
                // if(asteroid.intersects(ball)) {rHandler.intersected = true; System.out.println(rHandler.checkAstroidIntersection(a));}
                // else  rHandler.intersected = false;
                // Defines which image to draw asteroids according to their types
                if(!a.getIsFrozen()){ 
                    switch (a.color) {
                        // Creates the objects for their types with the given asteroid dimensions
                        case "red":
                            g2.drawImage(expImage, a.getX(), a.getY(),a.getWidth(),a.getWidth(), null);
                            break;
                        case "gray":
                            g2.drawImage(simpleImage, a.getX(), a.getY(),a.getWidth(),a.getLen(), null);
                            break;
                        case "purple":
                            g2.drawImage(firmImage, a.getX(), a.getY(),a.getWidth(),a.getWidth(), null);
                            break;
                        case "yellow":
                            g2.drawImage(giftImage, a.getX(), a.getY(),a.getWidth(), a.getLen(), null);
                        default: 
                            break;
                    }
                } else if(a.getType().equals("rectangle")) {
                    g2.drawImage(iceImage, a.getX(), a.getY(),a.getWidth(), a.getLen(), null);
                } else {
                    g2.drawImage(iceImageCirc, a.getX(), a.getY(),a.getWidth(), a.getLen(), null);
                }
                
            }
        } catch(ConcurrentModificationException e){

        }
    }


    public void drawAlien(Graphics g2){
        for (Alien ali : rHandler.getAliList().getAliens()) {
            switch (ali.getType()) {
                case "Cooperative":
                    g2.drawImage(alien1, ali.getX(), ali.getY(),ali.getWidth(),ali.getWidth(), null);
                    break;
                case "Surprising":
                    g2.drawImage(alien2, ali.getX(), ali.getY(),ali.getWidth(),ali.getWidth(), null);
                    break;
                case "Repairing":
                    g2.drawImage(alien4, ali.getX(), ali.getY(),ali.getWidth(),ali.getWidth(), null);
                    break;
                case "Time Wasting":
                    g2.drawImage(alien5, ali.getX(), ali.getY(),ali.getWidth(),ali.getWidth(), null);
                    break;
                default:
                    g2.drawImage(alien3, ali.getX(), ali.getY(),ali.getWidth(),ali.getWidth(), null);
                    break;
            }
            // ballRect = new Rectangle((int)ball.getX(), (int)ball.getY(), ball.getRadius(), ball.getRadius());
            // g2.setColor(Color.MAGENTA);        
            // g2.fillOval((int)ball.getX(), (int)ball.getY(), ball.getRadius(), ball.getRadius());
            // rHandler.asteroidBounce(ball, a, ali);
        }

    }
    
    public void drawTimeandScore(){
        this.time.setText("Time = " + rHandler.getTime());
        this.playerHealth.setText("Health = " + rHandler.playerHealthKeeper());
        this.playerScore.setText("Score = " + rHandler.playerScoreKeeper());
    }
    
    public void keyPressed(KeyEvent e)  {
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT && rHandler.timerRunning) {
            paddleDir = 1;

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT && rHandler.timerRunning) {
            paddleDir = 2;

        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
            if(!paused || rHandler.timerRunning){
                pauseGame();  
            } else {
                resumeGame();
                rHandler.StartTimer();
            }
        } else if(e.getKeyCode() == KeyEvent.VK_W){
            if(!rHandler.getMagnet()) {
                resumeGame();
                rHandler.StartTimer();
            }
        } else if(e.getKeyCode() == KeyEvent.VK_T){
            if(rHandler.getBag().containsKey("T")&& !rHandler.isUsingPowerUP()){
                rHandler.useTallerPaddle();
            }
        } else if(e.getKeyCode() == KeyEvent.VK_G){
            if(rHandler.getBag().containsKey("G")&& !rHandler.isUsingPowerUP()){
                rHandler.generateMoreBalls();
            }
        } else if(e.getKeyCode() == KeyEvent.VK_M){
            if(rHandler.getBag().containsKey("M")&& !rHandler.isUsingPowerUP()){
                rHandler.setMagnet(true);
            }
        } else if(e.getKeyCode() == KeyEvent.VK_L) {
            if(rHandler.getBag().containsKey("L")&& !rHandler.isUsingPowerUP()){
                rHandler.getLaserGun1().setVisible(true);
                rHandler.getLaserGun2().setVisible(true);
                rHandler.setLaserGun();
            }

        } else if(e.getKeyCode() == KeyEvent.VK_V) {
            if(rHandler.getBag().containsKey("P")&& !rHandler.isUsingPowerUP()){
                rHandler.getPaddle().setIsWrapped(true);
                rHandler.setUsingPowerUP(true);
                rHandler.useWrap();
            }

        }else if(e.getKeyCode() == KeyEvent.VK_E) {
            if(rHandler.getLaserGun1().getAmmo() > 0 && rHandler.getLaserGun2().getAmmo() > 0) {
                rHandler.getLaserGun1().shoot();
                rHandler.getLaserGun2().shoot();
                rHandler.useLaserGun();
                rHandler.setLaserUse(true);
            }
            if (rHandler.getLaserGun1().getAmmo() < 1) {
                rHandler.getLaserGun1().setVisible(false);
                 rHandler.setUsingPowerUP(false);
            }
        } else if(e.getKeyCode() == KeyEvent.VK_Q) {
            if(rHandler.isUsingPowerUP() && rHandler.getMagnet()){
                rHandler.setWPressed(true);
            }
        }
       
        if (!paused) {
            repaint();
        }
    }

    public void keyReleased (KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            paddleDir = 0;
            // rHandler.getPaddle().move(1);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paddleDir = 0;
            // rHandler.getPaddle().move(2);
        }


    }

    public void keyTyped (KeyEvent e) {}
    
    public void actionPerformed(ActionEvent e){
            if(!rHandler.timerRunning || paused) {
                rHandler.timerRunning = false;
                timer.stop();
            } else if (!paused) { 

                if(!rHandler.getPaddle().isWraped()){
                repaint();
                rHandler.getPaddle().move(paddleDir);
                drawTimeandScore();

                } else if (rHandler.getPaddle().isWraped()) {
                    repaint();
                    rHandler.getPaddle().moveWithWrap(paddleDir);
                    drawTimeandScore();
                }
                
            }

            if(!rHandler.timerRunning) {
                paused = true;
            }
            
            if(e.getSource() == this.pauseButton){
                pauseGame();
                rHandler.StopTimer();
            }
    }
}