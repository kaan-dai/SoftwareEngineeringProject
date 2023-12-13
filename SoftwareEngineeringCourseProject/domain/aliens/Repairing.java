package domain.aliens;

public class Repairing implements AlienState {

    SurprisingAlien surprisingAlien;

    public Repairing(SurprisingAlien newSurprisingAlien) {
        surprisingAlien = newSurprisingAlien;
    }

    @Override
    public void perform(SurprisingAlien surprisingAlien) {
        RepairingAlien repairingAlien = new RepairingAlien();
        repairingAlien.applyFunction();
        System.out.println("surprising alien is acting as repairing");
        
    }
}
