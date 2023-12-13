package domain.timer;

import java.util.TimerTask;

import domain.aliens.Alien;

public class ConfusedAlienTimer extends TimerTask {

    Alien alien;

    private boolean isRunning = false;

    public ConfusedAlienTimer(Alien alien){
      this.alien = alien;
    }

    @Override
    public void run() {
      isRunning = true;
      alien.applyFunction();
      System.out.println("surprising alien is acting as confused");
    }

    public boolean isRunning(){
      return isRunning;
    }
    
}
