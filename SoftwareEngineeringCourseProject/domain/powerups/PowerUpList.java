package domain.powerups;

import java.util.ArrayList;

import domain.handlers.RunHandler;


/**
 * AsteroidList
 */
public class PowerUpList {

    //Overview: PowerUpList is class which is used for storing powerups for player to use in the game
    // it can be modified by RunHandler and powerupfactory


    private int magnet;
    private int gob;
	private int chance;
	private int wrap;
	private int tallerP;
    private int dlg;

    public static void setPowerUps(ArrayList<PowerUp> powerUps) {
        PowerUpList.powerUps = powerUps;
    }

    public static void setInstance(PowerUpList instance) {
        PowerUpList.instance = instance;
    }


    private static ArrayList<PowerUp> powerUps = new ArrayList<PowerUp>(); // the rep

    private static PowerUpList instance; 
    private PowerUpList() {}

    
	
    public static PowerUpList getInstance() {
        if (instance == null) {
            instance = new PowerUpList();
        }
        return instance;
    }
    

    public void removeAll() {
        powerUps.removeAll(powerUps);
    }

    public void addPowerUp(PowerUp pow) throws NullPointerException {
        if(pow == null) {
            throw new NullPointerException();
        }
        powerUps.add(pow);
    }

    public  ArrayList<PowerUp> getPowerUps() {
        return powerUps;
        
    }

    public void remove(PowerUp a) throws NullPointerException {
        if(a == null) {
            throw new NullPointerException();
        }
        powerUps.remove(a);
    }

    public int getListSize() {
        return powerUps.size();
    }

    public void getNumber() {
        //@Effects: this function increments the number of different asteroid types by iterating over the asteroids list
        for (PowerUp a : powerUps) {
            if (a.type == "Magnet") {
                this.magnet ++;

            } else if (a.type == "Wrap") {
                this.gob++;

            } else if (a.type == "GOB") {
                this.chance++;

            } else if (a.type == "DLG") {
                this.wrap++;
            }
        }
    }

    public boolean inList(PowerUp pow){
        return powerUps.contains(pow);
    }

    public boolean isInFrame(PowerUp pow){
        if(pow.getX() < 0 || pow.getX() > RunHandler.getInstance().getScreenWidth() || pow.getY() < 0 || pow.getY() > RunHandler.getInstance().getScreenHeight()) {
            return false;
        }
        else {
            return true;
        }
    }



    //Getters and Setters 
    
	public int getMagnet() {
        return magnet;
    }

    public void setMagnet(int magnet) {
        this.magnet = magnet;
    }

    public int getGob() {
        return gob;
    }

    public void setGob(int gob) {
        this.gob = gob;
    }

    public int getChance() {
        return chance;
    }

    public void setChance(int chance) {
        this.chance = chance;
    }

    public int getWrap() {
        return wrap;
    }

    public void setWrap(int wrap) {
        this.wrap = wrap;
    }

    public int getTallerP() {
        return tallerP;
    }

    public void setTallerP(int tallerP) {
        this.tallerP = tallerP;
    }

    public int getDlg() {
        return dlg;
    }

    public void setDlg(int dlg) {
        this.dlg = dlg;
    }


}