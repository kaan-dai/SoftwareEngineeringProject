package domain.aliens;

public class Cooperative implements AlienState {

    SurprisingAlien surprisingAlien;

    public Cooperative(SurprisingAlien newSurprisingAlien) {
        surprisingAlien = newSurprisingAlien;
    }

    @Override
    public void perform(SurprisingAlien surprisingAlien) {
        CooperativeAlien cooperativeAlien = new CooperativeAlien();
        cooperativeAlien.applyFunction();
        System.out.println("surprising alien is acting as cooperative");
    }

}