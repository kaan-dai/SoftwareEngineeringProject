package domain.aliens;

import java.util.Timer;

import domain.timer.TimeWastingAlienTimer;


public class Challenging implements AlienState {

    SurprisingAlien surprisingAlien;

    public Challenging(SurprisingAlien newSurprisingAlien) {
        surprisingAlien = newSurprisingAlien;
    }

    @Override
    public void perform(SurprisingAlien surprisingAlien) {
        RepairingAlien repairingAlien = new RepairingAlien();
        repairingAlien.buildRow();
        TimeWastingAlien timeWastingAlien = new TimeWastingAlien();
        timeWastingAlien.chooseAsteroidsAndFreeze();
        Timer timer = new Timer(true);
        TimeWastingAlienTimer tTimer = new TimeWastingAlienTimer(timeWastingAlien);
        timer.schedule(tTimer, 15000);
        System.out.println("surprising alien is acting challenging");
        
    }

    
}
