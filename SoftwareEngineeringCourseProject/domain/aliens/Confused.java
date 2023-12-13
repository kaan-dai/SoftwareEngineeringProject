package domain.aliens;

import domain.handlers.RunHandler;

public class Confused implements AlienState {

    Alien alien;
    //SurprisingAlien surprisingAlien;

    public Confused(Alien newAlien) {
        alien = newAlien;
    }

    @Override
    public void perform(SurprisingAlien surprisingAlien) {
        //RunHandler.getInstance().getRemovalListOfAlien().add(surprisingAlien);
        //RunHandler.getInstance().deleteAlien();
        //AlienList.getInstance().getAliens().remove(surprisingAlien);
        RunHandler.getInstance().getRemovalListOfAlien().add(alien);
    }

}
