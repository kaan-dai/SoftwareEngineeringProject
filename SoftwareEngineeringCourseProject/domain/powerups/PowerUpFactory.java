package domain.powerups;

import java.util.Random;

import domain.asteroids.Asteroid;
import domain.asteroids.GiftAsteroid;
import domain.handlers.RunHandler;
import domain.objects.Player;

public class PowerUpFactory {
    
   
    private static PowerUpFactory instance;
    RunHandler rHandler = RunHandler.getInstance();
    PowerUpList powerUpList = PowerUpList.getInstance();

    private GiftAsteroid forWidth = new GiftAsteroid();
    private int length = forWidth.getWidth() / 2;

    public static PowerUpFactory getInstance() {
        if (instance == null) {
            instance = new PowerUpFactory();
        }
        return instance;
    }

    private PowerUpFactory() {}

    public PowerUp createMagnet(Asteroid ast) {
        PowerUp pow = new MagnetPowerUp();
        pow.setLength(ast.getWidth() / 2);
        pow.setX(ast.getX() + pow.length);
        pow.setY(ast.getY());
        powerUpList.addPowerUp(pow);
        return pow;
    }

    public PowerUp createChance(Asteroid ast) {
        PowerUp pow = new ChancePowerUp();
        pow.setLength(ast.getWidth() / 2);
        pow.setX(ast.getX() + pow.length);
        pow.setY(ast.getY());
        powerUpList.addPowerUp(pow);
        return pow;
    }

    public PowerUp createTallerPaddle(Asteroid ast) {
        PowerUp pow = new TallerPaddlePowerUp();
        pow.setLength(ast.getWidth() / 2);
        pow.setX(ast.getX() + pow.length);
        pow.setY(ast.getY());
        powerUpList.addPowerUp(pow);
        return pow;
    }

    public PowerUp createWrap(Asteroid ast) {
        PowerUp pow = new WrapPowerUp();
        pow.setLength(ast.getWidth() / 2);
        pow.setX(ast.getX() + pow.length);
        pow.setY(ast.getY());
        powerUpList.addPowerUp(pow);
        return pow;
    }

    public PowerUp createGangOfBalls(Asteroid ast) {
        PowerUp pow = new GangOfBalls(rHandler);
        pow.setLength(ast.getWidth() / 2);
        pow.setX(ast.getX() + pow.length);
        pow.setY(ast.getY());
        powerUpList.addPowerUp(pow);
        return pow;
    }

    public PowerUp createDestructiveGun(Asteroid ast){
        PowerUp pow = new DestructiveLaserGun();
        pow.setLength(ast.getWidth() / 2);
        pow.setX(ast.getX() + pow.length);
        pow.setY(ast.getY());
        powerUpList.addPowerUp(pow);
        return pow;
    } 

    public void createChanceFromLoad (int x, int y, String inbag) {

        PowerUp  pow = new ChancePowerUp();

       if(inbag.equals("Chance")) {
        pow.setLength(this.length);
        pow.setX(x);
        pow.setY(y);
        powerUpList.addPowerUp(pow);

       } else if (inbag.equals("InBagChance")) {
           Player.getInstance().getPowerUpBag().put("Chance", pow);
       }

    }
    public void createMagnetFromLoad (int x, int y, String inbag) {

        PowerUp  pow = new MagnetPowerUp();

       if(inbag.equals("Magnet")) {
        pow.setLength(this.length);
        pow.setX(x);
        pow.setY(y);
        powerUpList.addPowerUp(pow);

       } else if (inbag.equals("InBagMagnet")) {
           Player.getInstance().getPowerUpBag().put("M", pow);
       }

    }
    public void createWrapFromLoad (int x, int y, String inbag) {

        PowerUp  pow = new WrapPowerUp();

       if(inbag.equals("Wrap")) {
        pow.setLength(this.length);
        pow.setX(x);
        pow.setY(y);
        powerUpList.addPowerUp(pow);

       } else if (inbag.equals("InBagWrap")) {
           Player.getInstance().getPowerUpBag().put("P", pow);
       }

    }
    public void createTallerPaddleFromLoad (int x, int y, String inbag) {

        PowerUp  pow = new TallerPaddlePowerUp();

       if(inbag.equals("TallerP")) {
        pow.setLength(this.length);
        pow.setX(x);
        pow.setY(y);
        powerUpList.addPowerUp(pow);

       } else if (inbag.equals("InBagTallerP")) {
           Player.getInstance().getPowerUpBag().put("T", pow);
       }

    }
    public void createGangOfBallsFromLoad (int x, int y, String inbag) {

        PowerUp  pow = new GangOfBalls(rHandler);

       if(inbag.equals("GOB")) {
        pow.setLength(this.length);
        pow.setX(x);
        pow.setY(y);
        powerUpList.addPowerUp(pow);

       } else if (inbag.equals("InBagGOB")) {
           Player.getInstance().getPowerUpBag().put("G", pow);
       }

    }
    public void createDestructiveLaserGunFromLoad (int x, int y, String inbag) {

        PowerUp  pow = new DestructiveLaserGun();

       if(inbag.equals("DLG")) {
        pow.setLength(this.length);
        pow.setX(x);
        pow.setY(y);
        powerUpList.addPowerUp(pow);

       } else if (inbag.equals("InBagDLG")) {
           Player.getInstance().getPowerUpBag().put("L", pow);
       }

    }

    public void placePowerup(Asteroid ast) {
        Random rand = new Random();
        int num = rand.nextInt(6);
        switch(num) {
            case 0:
                createMagnet(ast);
                break;
            case 1: 
                createChance(ast);
                break;
            case 2:
                createGangOfBalls(ast);
                break;
            case 3: 
                createTallerPaddle(ast);
                break;
            case 4: 
                createWrap(ast);
                break;
            case 5:
                createDestructiveGun(ast);
                break;
            default:
                break;
        }
    }

}
