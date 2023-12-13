package domain.aliens;

import java.util.Random;

import domain.handlers.BuildingHandler;
import domain.handlers.RunHandler;

public class AlienFactory {
    
    
    AlienList alienList = AlienList.getInstance();
    Random rand = new Random();

    private static AlienFactory instance;
    private AlienFactory() {}

    public static AlienFactory getInstance() {
        if (instance == null) {
            instance = new AlienFactory();
        }
        return instance;
    }

    public Alien createAlien() {
        int num = rand.nextInt(5);
        Alien cooperative = createCooperative();
        Alien repairing = createRepairing();
        Alien protecting = createProtecting();
        Alien timeWasting = createTimeWasting();
        Alien surprisingAlien = createSurprising();
       
        switch (num) {
            case 0:
                if(!alienListContains(cooperative) && !RunHandler.getInstance().getIsCoopAlienHit()){
                    alienList.addAlien(cooperative);
                    return cooperative;
                } else if (RunHandler.getInstance().getIsCoopAlienHit()) {
                    System.out.println("sorry cannot add a cooperative alien, because you hit a cooperative alien previously");
                }
                break;
                
            case 1:
                if(!alienListContains(repairing)) {
                    alienList.addAlien(repairing);
                    return repairing;
                }
                break;
                
            case 2:
                if(!alienListContains(protecting)) {
                   alienList.addAlien(protecting);
                   return protecting;
                } 
                break;
            case 3:
                if(!alienListContains(timeWasting)) {
                    alienList.addAlien(timeWasting);
                    return timeWasting;
                } 
                break;
            case 4:
                if(!alienListContains(surprisingAlien)) {
                    alienList.addAlien(surprisingAlien);
                    return surprisingAlien;
                } 
                break;
            default:
                break;
        }
        return null;
    }

    public Alien createRepairing() {
        Alien alien = new RepairingAlien(); 
        alien.setX(findRandomX());
        alien.setY(findRandomY());
        return alien;

    }

    public Alien createProtecting() {
        Alien alien = new ProtectingAlien();
        alien.setX(findRandomX());
        alien.setY(findRandomY()); 
        return alien;

    }

    public Alien createCooperative() {
        Alien alien = new CooperativeAlien();
        alien.setX(findRandomX());
        alien.setY(findRandomY());
        return alien;

    }

    public Alien createTimeWasting() {
        Alien alien = new TimeWastingAlien();
        alien.setX(findRandomX());
        alien.setY(findRandomY());
        return alien;

    }

    public Alien createSurprising() {
        Alien alien = new SurprisingAlien(); 
        alien.setX(findRandomX());
        alien.setY(findRandomY());
        return alien;

    }

    public int findRandomX() {
        int x = rand.nextInt(BuildingHandler.width - 300);
        return x;
    }

    public int findRandomY() {
        int y = rand.nextInt(BuildingHandler.height/2 + 200);
        return y;
    }

    public boolean alienListContains(Alien al) {
        for(Alien present : alienList.getAliens()){
            if(present.getType().equals(al.getType())) return true;
        }
        return false;
    }

    public void createCooperativeFromLoad (int x, int y) {

        Alien alien = new CooperativeAlien();
        RunHandler.getInstance().alienPerforms(alien);
        alien.setX(x);
        alien.setY(y);   
        alienList.addAlien(alien);
        
    }
    public void createProtectingFromLoad (int x, int y) {

        Alien alien = new ProtectingAlien();
        RunHandler.getInstance().alienPerforms(alien);
        alien.setX(x);
        alien.setY(y);   
        alienList.addAlien(alien);
        
    }
    public void createSurprisingFromLoad (int x, int y) {

        Alien alien = new SurprisingAlien();
        RunHandler.getInstance().alienPerforms(alien);
        alien.setX(x);
        alien.setY(y);   
        alienList.addAlien(alien);
        
    }
    public void createRepairingFromLoad (int x, int y) {

        Alien alien = new RepairingAlien();
        RunHandler.getInstance().alienPerforms(alien);
        alien.setX(x);
        alien.setY(y);   
        alienList.addAlien(alien);
        
    }
    public void createTimeWastingFromLoad (int x, int y) {

        Alien alien = new TimeWastingAlien();
        RunHandler.getInstance().alienPerforms(alien);
        alien.setX(x);
        alien.setY(y);   
        alienList.addAlien(alien);
        
    }
}
