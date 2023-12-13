package domain.aliens;


public class Protecting implements AlienState {

    SurprisingAlien surprisingAlien;
    ProtectingAlien protectingAlien = new ProtectingAlien();
    int speed = protectingAlien.getSpeed();
      
    public Protecting(SurprisingAlien newSurprisingAlien) {
        surprisingAlien = newSurprisingAlien;
    }
    
    @Override
    public void perform(SurprisingAlien surprisingAlien) {
      if(surprisingAlien.getX()>protectingAlien.getScreenWidth()) speed = -speed;
      if(surprisingAlien.getX()<0) speed = -speed;
      surprisingAlien.setX(surprisingAlien.getX()+speed);
    }
}
