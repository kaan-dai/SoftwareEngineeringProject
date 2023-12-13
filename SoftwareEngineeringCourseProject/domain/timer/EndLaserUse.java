package domain.timer;

import java.util.TimerTask;

import domain.handlers.RunHandler;

public class EndLaserUse extends TimerTask{
    
    private RunHandler rhand = RunHandler.getInstance();
    private boolean timerStart = false;
  

    @Override
    public void run() {
        timerStart = true;
        rhand.setLaserUse(false);
    }

    public boolean timerStarted(){
        return this.timerStart;
    }
}
